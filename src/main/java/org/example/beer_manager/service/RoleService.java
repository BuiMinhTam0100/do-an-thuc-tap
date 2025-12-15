package org.example.beer_manager.service;
import lombok.RequiredArgsConstructor;
import org.example.beer_manager.entity.Role;
import org.example.beer_manager.repo.RoleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}
