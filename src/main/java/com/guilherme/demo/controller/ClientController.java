package com.guilherme.demo.controller;

import com.guilherme.demo.model.Client;
import com.guilherme.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("clients")
public class ClientController {

    @Autowired private ClientService clientService;

    @GetMapping
    public String clientForm(@ModelAttribute Client client) {
        return "form-client";
    }

    @GetMapping("{id}")
    public String clientEditForm(@PathVariable("id") long id, Model model) {
        Client client = clientService.getClient(id);

        if( client == null ){
            return "redirect:/";
        }

        model.addAttribute("client", client);
        return "form-client";
    }

    @PostMapping
    public String processForm(@Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "form-client";
        }
        clientService.save( client );
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String processDelete(@PathVariable("id") Long id) {
        clientService.delete(id);
        return "redirect:/";
    }
}

