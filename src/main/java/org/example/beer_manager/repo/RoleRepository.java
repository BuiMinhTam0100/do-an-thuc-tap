package org.example.beer_manager.repo;

import org.example.beer_manager.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}