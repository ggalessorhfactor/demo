package com.guilherme.demo.services;

import com.guilherme.demo.model.Client;
import com.guilherme.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired private ClientRepository clientRepository;


    public Client getClient(Long id) {
        return clientRepository.findById( id ).orElse(null);
    }

    public Client save(Client client) {

        if( client.getId() != null && client.getId() > 0 ){
            // UPDATE : Busca no banco

            Client clientDB = clientRepository.findById( client.getId() )
                    .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + client.getId() ));

            // Atualiza os dados da entidade do banco

            clientDB.setEmail( client.getEmail() );
            clientDB.setName( client.getName() );
            clientDB.setPhone( client.getPhone() );

            return clientRepository.save(clientDB);
        }else{
            return clientRepository.save(client);
        }

    }

    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        clientRepository.delete(client);
    }

    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
    }
}
