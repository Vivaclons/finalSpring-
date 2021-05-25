package kz.java.spring.music.repository;

import kz.java.spring.music.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
}
