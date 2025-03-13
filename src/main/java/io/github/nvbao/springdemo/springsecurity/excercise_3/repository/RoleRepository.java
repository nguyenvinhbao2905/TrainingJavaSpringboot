package io.github.nvbao.springdemo.springsecurity.excercise_3.repository;

import io.github.nvbao.springdemo.springsecurity.excercise_3.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
