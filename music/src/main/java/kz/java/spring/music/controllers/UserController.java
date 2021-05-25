package kz.java.spring.music.controllers;

import kz.java.spring.music.models.Users;
import kz.java.spring.music.repository.UserRepository;
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
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/super_admin")
    public String user(Model model) {
        Iterable<Users> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "super_admin";
    }

    @GetMapping("/user/add")
    public String userAdd(Model model) {
        return "user_add";
    }

    @PostMapping("/user/add")
    public String userAddAdmin(@RequestParam String name, @RequestParam String password, @RequestParam String subscribe, @RequestParam String type, Model model){
        Users user = new Users(name, password, subscribe, type);
        userRepository.save(user);
        return "redirect:/super_admin";
    }

    @GetMapping("/super_admin/{id}")
    public String userDet(@PathVariable(value = "id") long id, Model model) {
        if(!userRepository.existsById(id)){
            return "redirect:/admin";
        }
        Optional<Users> user = userRepository.findById(id);
        ArrayList<Users> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "user_edit";
    }

    @GetMapping("/super_admin/{id}/edit")
    public String userEdit(@PathVariable(value = "id") long id, Model model) {
        if(!userRepository.existsById(id)){
            return "redirect:/super_admin";
        }
        Optional<Users> user = userRepository.findById(id);
        ArrayList<Users> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "user_edit";
    }

    @PostMapping("/super_admin/{id}/edit")
    public String userUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String password, @RequestParam String subscribe, @RequestParam String type, Model model){
        Users user = userRepository.findById(id).orElseThrow();
        user.setName(name);
        user.setPassword(password);
        user.setSubscribe(subscribe);
        user.setType(type);
        userRepository.save(user);
        return "redirect:/super_admin";
    }

    @PostMapping("/super_admin/{id}/remove")
    public String userDelete(@PathVariable(value = "id") long id, Model model){
        Users user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/super_admin";
    }
}
