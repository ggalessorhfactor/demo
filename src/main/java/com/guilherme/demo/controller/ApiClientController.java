package com.guilherme.demo.controller;

import com.guilherme.demo.model.Client;
import com.guilherme.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/clients")
public class ApiClientController {

    @Autowired private ClientService clientService;

    @GetMapping
    public List<Client> clientForm(@ModelAttribute Client client) {
        return  clientService.listAll();
    }

    @GetMapping("{id}")
    public Client clientEditForm(@PathVariable("id") Long id) {
        return clientService.findById( id );
    }

    @PostMapping
    public Client processForm(@Valid Client client) {
        return clientService.save( client );
    }

    @GetMapping("delete/{id}")
    public Boolean processDelete(@PathVariable("id") Long id) {
        clientService.delete(id);
        return true;
    }
}

