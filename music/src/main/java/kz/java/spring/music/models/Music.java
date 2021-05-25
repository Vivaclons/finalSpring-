package kz.java.spring.music.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author, title, min;
    private int year;

    public Music() {
    }

    public Music(String author, String title, String min, int year) {
        this.author = author;
        this.title = title;
        this.min = min;
        this.year = year;
    }
}
