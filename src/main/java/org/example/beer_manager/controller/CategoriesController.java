package org.example.beer_manager.controller;
import lombok.RequiredArgsConstructor;
import org.example.beer_manager.dto.request.CategoriesRequest;
import org.example.beer_manager.service.CategoriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("get_categories", categoriesService.findAll());
        model.addAttribute("new_categories", new CategoriesRequest(null));
        return "views/categories/index";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("new_categories") CategoriesRequest request) {
        categoriesService.create(request);
        return "redirect:/admin/categories";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("detail_categories", categoriesService.findCategoriesByID(id));
        return "views/categories/detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("edit_categories", categoriesService.findCategoriesByID(id));
        return "views/categories/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute("edit_categories") CategoriesRequest request) {
        categoriesService.update(id, request);
        return "redirect:/admin/categories";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        categoriesService.delete(id);
        return "redirect:/admin/categories";
    }
}
