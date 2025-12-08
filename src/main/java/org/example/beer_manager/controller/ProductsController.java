package org.example.beer_manager.controller;

import lombok.RequiredArgsConstructor;
import org.example.beer_manager.dto.request.CategoriesRequest;
import org.example.beer_manager.dto.request.ProductRequest;
import org.example.beer_manager.service.CategoriesService;
import org.example.beer_manager.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductsController {

    private final ProductService productService;
    protected final CategoriesService categoriesService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("get_product", productService.findAll());
        return "views/products/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("new_product", new ProductRequest(
                null, null, null, null, null, null ));
        model.addAttribute("new_categories", categoriesService.findAll());
        return "views/products/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("new_product") ProductRequest request) {
        productService.create(request);
        return "redirect:/admin/products";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("detail_product", productService.findByID(id));
        return "views/products/detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("edit_product", productService.findByID(id));
        model.addAttribute("list_categories", categoriesService.findAll());
        return "views/products/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute("edit_product") ProductRequest request) {
        productService.update(id, request);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }
}
