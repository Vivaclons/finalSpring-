package kz.java.spring.music.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name, password, subscribe, type;

    public Users() {
    }

    public Users(String name, String password, String subscribe, String type) {
        this.name = name;
        this.password = password;
        this.subscribe = subscribe;
        this.type = type;
    }
}
