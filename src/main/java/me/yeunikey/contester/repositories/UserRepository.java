package me.yeunikey.contester.repositories;

import me.yeunikey.contester.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
