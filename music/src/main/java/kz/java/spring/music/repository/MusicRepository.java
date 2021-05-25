package kz.java.spring.music.repository;

import kz.java.spring.music.models.Music;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepository extends CrudRepository<Music, Long> {
}
