package org.example.beer_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/receipts")
public class ReceiptsController {

    @GetMapping
    public String index() {
        return "views/receipts/index";
    }
}
