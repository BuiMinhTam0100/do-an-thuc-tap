package org.example.beer_manager.controller;
import lombok.RequiredArgsConstructor;
import org.example.beer_manager.dto.request.*;
import org.example.beer_manager.service.ReceiptService;
import org.example.beer_manager.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/receipts")
public class ReceiptsController {

    private final ReceiptService receiptService;
    private final SupplierService supplierService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("get_receipts", receiptService.findAll());
        model.addAttribute("get_supplier", supplierService.findAll());
        model.addAttribute("new_receipt", new ReceiptRequest(
                null, null, null
        ));
        return "views/receipts/index";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("new_receipt") ReceiptRequest request) {
        receiptService.create(request);
        return "redirect:/admin/receipts";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("detail_receipt", receiptService.findByID(id));
        return "views/receipts/detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("edit_receipt", receiptService.findByID(id));
        model.addAttribute("get_supplier", supplierService.findAll());
        return "views/receipts/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute("edit_receipt") ReceiptRequest request) {
        receiptService.update(id, request);
        return "redirect:/admin/receipts";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        receiptService.delete(id);
        return "redirect:/admin/receipts";
    }
}
