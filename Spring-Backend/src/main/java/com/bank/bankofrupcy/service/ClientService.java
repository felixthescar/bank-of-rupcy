package com.bank.bankofrupcy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.bankofrupcy.model.Client;
import com.bank.bankofrupcy.repository.ClientRepository;

@Service
public class ClientService {

	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	//Find all Clients
	public ResponseEntity<List<Client>> getAllClients () {
		List<Client> allClients = clientRepository.findAll();
		return ResponseEntity.ok(allClients);
	}
	
	//Find Client By Id
	public ResponseEntity<Client> findClientById(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		if (client.isPresent()) {
			return ResponseEntity.ok(client.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//Modify or Add Client
	public ResponseEntity<Client> saveClient(@RequestBody Client client) {
		Client newClient= clientRepository.save(client);
		return ResponseEntity.ok(newClient);
	}
	
	//Delete Client
	public ResponseEntity<String> deleteClient(Long id) {
		clientRepository.deleteById(id);
		return ResponseEntity.ok("Client Deleted Successfully");
	}

}
