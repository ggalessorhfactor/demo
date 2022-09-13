package com.guilherme.demo.controller;

import com.guilherme.demo.model.Client;
import com.guilherme.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/clients/")
public class ClientController {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("signup")
    public String showSignUpForm(Client client) {
        return "add-client";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addClient(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-client";
        }

        clientRepository.save(client);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        model.addAttribute("client", client);
        return "update-client";
    }

    @PostMapping("update/{id}")
    public String updateClient(@PathVariable("id") long id, @Valid Client client, BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            client.setId(id);
            return "update-client";
        }

        clientRepository.save(client);
        model.addAttribute("students", clientRepository.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteClient(@PathVariable("id") long id, Model model) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        clientRepository.delete(client);
        model.addAttribute("clients", clientRepository.findAll());
        return "index";
    }
}

