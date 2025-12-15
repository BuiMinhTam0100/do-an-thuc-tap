package org.example.beer_manager.controller;
import lombok.RequiredArgsConstructor;
import org.example.beer_manager.dto.request.CreateUserRequest;
import org.example.beer_manager.dto.request.UpdatePasswordRequest;
import org.example.beer_manager.dto.request.UpdateUserRequest;
import org.example.beer_manager.service.RoleService;
import org.example.beer_manager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UsersController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("get_users", userService.findAll());
        model.addAttribute("new_user", new CreateUserRequest(
                null, null, null, null, null));
        model.addAttribute("new_role", roleService.findAll());
        model.addAttribute("update_password", new UpdatePasswordRequest(null, null));
        return "views/users/index";
    }


    @PostMapping("/create")
    public String create(@ModelAttribute("new_user") CreateUserRequest request) {
        userService.create(request);
        return "redirect:/admin/users";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("detail_user", userService.findByID(id));
        return "views/users/detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("edit_user", userService.findByID(id));
        model.addAttribute("list_role", roleService.findAll());
        return "views/users/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute("edit_user") UpdateUserRequest request) {
        userService.update(id, request);
        return "redirect:/admin/users";
    }

    @PostMapping("/update-password/{id}")
    public String updatePassword(@PathVariable Integer id, @ModelAttribute("update_password") UpdatePasswordRequest request) {
        userService.updatePassword(id,request);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
