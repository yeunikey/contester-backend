package me.yeunikey.contester.repositories;

import me.yeunikey.contester.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {
}
