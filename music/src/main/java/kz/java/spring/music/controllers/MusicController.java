package kz.java.spring.music.controllers;

import kz.java.spring.music.exception.RequestException;
import kz.java.spring.music.models.Music;
import kz.java.spring.music.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MusicController {

    @Autowired
    private MusicRepository musicRepository;

    @GetMapping("/user")
    public String user(Model model) {
        Iterable<Music> musics = musicRepository.findAll();
        model.addAttribute("musics", musics);
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        Iterable<Music> musics = musicRepository.findAll();
        model.addAttribute("musics", musics);
        return "admin";
    }

    @GetMapping("/music/add")
    public String musicAdd(Model model) {
        return "music_add";
    }

    @PostMapping("/music/add")
    public String musicAddAdmin(@RequestParam String author, @RequestParam String title, @RequestParam String min, @RequestParam int year, Model model){
        Music music = new Music(author, title, min, year);
        musicRepository.save(music);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String musicDet(@PathVariable(value = "id") long id, Model model) {
        if(musicRepository.existsById(id)){
            return "redirect:/admin";
        }
        Optional<Music> music = musicRepository.findById(id);
        ArrayList<Music> res = new ArrayList<>();
        music.ifPresent(res::add);
        model.addAttribute("music", res);
        return "music_edit";
    }

    @GetMapping("/admin/{id}/edit")
    public String musicEdit(@PathVariable(value = "id") long id, Model model) {
        if(!musicRepository.existsById(id)){
            return "redirect:/admin";
        }
        Optional<Music> music = musicRepository.findById(id);
        ArrayList<Music> res = new ArrayList<>();
        music.ifPresent(res::add);
        model.addAttribute("music", res);
        return "music_edit";
    }

    @PostMapping("/admin/{id}/edit")
    public String musicUpdate(@PathVariable(value = "id") long id, @RequestParam String author, @RequestParam String title, @RequestParam String min, @RequestParam int year, Model model){
        Music music = musicRepository.findById(id).orElseThrow();
        music.setAuthor(author);
        music.setMin(min);
        music.setTitle(title);
        music.setYear(year);
        musicRepository.save(music);
        return "redirect:/admin";
    }

    @PostMapping("/admin/{id}/remove")
    public String musicDelete(@PathVariable(value = "id") long id, Model model){
        Music music = musicRepository.findById(id).orElseThrow();
        musicRepository.delete(music);
        return "redirect:/admin";
    }
}
