package com.guilherme.demo.controller;

import com.guilherme.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SiteController {

    @Autowired private ClientRepository clientRepository;

    @GetMapping
    public String showUpdateForm(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "index";
    }

}
