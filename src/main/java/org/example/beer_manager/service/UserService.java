package org.example.beer_manager.service;
import lombok.RequiredArgsConstructor;
import org.example.beer_manager.dto.request.CreateUserRequest;
import org.example.beer_manager.dto.request.UpdatePasswordRequest;
import org.example.beer_manager.dto.request.UpdateUserRequest;
import org.example.beer_manager.dto.response.UserResponse;
import org.example.beer_manager.entity.Role;
import org.example.beer_manager.entity.User;
import org.example.beer_manager.mapper.UserMapper;
import org.example.beer_manager.repo.RoleRepository;
import org.example.beer_manager.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    public UserResponse create(CreateUserRequest request) {
        User new_user = mapper.createToEntity(request);
        new_user.setPassword(passwordEncoder.encode(request.password()));
        new_user.setRole(findRoleByID(request.roleId()));
        userRepository.save(new_user);
        return mapper.toResponse(new_user);
    }

    public UserResponse findByID(Integer id) {
        User find_user_by_id = findUserById(id);
        return mapper.toResponse(find_user_by_id);
    }

    public UserResponse update(Integer id, UpdateUserRequest request) {
        User update_user_by_id = findUserById(id);
        mapper.updateUser(update_user_by_id, request);
        update_user_by_id.setRole(findRoleByID(request.roleId()));
        userRepository.save(update_user_by_id);
        return mapper.toResponse(update_user_by_id);
    }

    public UserResponse delete(Integer id) {
        User delete_user_by_id = findUserById(id);
        delete_user_by_id.setStatus((byte) 0);
        delete_user_by_id.setDeletedAt(Instant.now());
        userRepository.save(delete_user_by_id);
        return mapper.toResponse(delete_user_by_id);
    }

    public UserResponse updatePassword(Integer id, UpdatePasswordRequest updatePasswordRequest) {
        if (!updatePasswordRequest.password().equals(updatePasswordRequest.confirmPassword()))
            throw new RuntimeException("Mật khẩu không khớp!");
        User update_password_user_by_id = findUserById(id);
        update_password_user_by_id.setPassword(passwordEncoder.encode(updatePasswordRequest.password()));
        userRepository.save(update_password_user_by_id);
        return mapper.toResponse(update_password_user_by_id);
    }

    public Role findRoleByID(Integer id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy quyền này!"));
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy người dùng này"));
    }

}
