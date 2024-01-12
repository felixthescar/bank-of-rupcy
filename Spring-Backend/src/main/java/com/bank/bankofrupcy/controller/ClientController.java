package com.bank.bankofrupcy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankofrupcy.model.Client;
import com.bank.bankofrupcy.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	// Get all clients 
	@GetMapping("/getAllClients")  
	public ResponseEntity<List<Client>> getAllClients() 
	{ 
	    return clientService.getAllClients(); 
	}

	// Get a client by ID
	@GetMapping("/getClientById/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable Long id) {
		ResponseEntity<Client> client = clientService.findClientById(id);
		return client.hasBody() ? client : new ResponseEntity<Client>(new Client(), HttpStatus.NOT_FOUND);
	}

	// Create a new client
	@PostMapping("/createClient")
	public ResponseEntity<Client> saveClient(@RequestBody Client client) {
		return client.getNpc() != null ? clientService.saveClient(client)
				: new ResponseEntity<Client>(new Client(), HttpStatus.FORBIDDEN);
	}

	// Modify a client
	@PostMapping("/modifyClient")
	public ResponseEntity<Client> modifyClient(@RequestBody Client client) {
		return client.getId() != null ? clientService.saveClient(client)
				: new ResponseEntity<Client>(new Client(), HttpStatus.NOT_FOUND);
	}

	// Delete a Client
	@DeleteMapping(value = "/deleteClient/{id}")
	public String deleteProduct(@PathVariable Long id) {
		ResponseEntity<Client> client = clientService.findClientById(id);
		if (client.hasBody()) {
			clientService.deleteClient(id);
			return "Client Deleted Successfully with id " + id + " ";
		}
		return "Client Not Found";
	}

}
