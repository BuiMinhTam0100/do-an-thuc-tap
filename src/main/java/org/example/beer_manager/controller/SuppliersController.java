package org.example.beer_manager.controller;
import lombok.RequiredArgsConstructor;
import org.example.beer_manager.dto.request.SupplierRequest;
import org.example.beer_manager.entity.Supplier;
import org.example.beer_manager.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/suppliers")
public class SuppliersController {

    private final SupplierService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("get_suppliers", service.findAll());
        return "views/suppliers/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("new_suppliers", new Supplier());
        return "views/suppliers/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("new_suppliers") SupplierRequest request) {
        service.create(request);
        return "redirect:/admin/suppliers";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("detail_suppliers", service.findByID(id));
        return "views/suppliers/detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("edit_suppliers", service.findByID(id));
        return "views/suppliers/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute("edit_suppliers") SupplierRequest request) {
        service.update(id, request);
        return "redirect:/admin/suppliers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/admin/suppliers";
    }
}
