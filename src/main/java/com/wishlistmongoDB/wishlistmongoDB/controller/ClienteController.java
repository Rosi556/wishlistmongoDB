package com.wishlistmongoDB.wishlistmongoDB.controller;

import com.wishlistmongoDB.wishlistmongoDB.entity.Cliente;
import com.wishlistmongoDB.wishlistmongoDB.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //Adicionar um cliente

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente respostaCliente = clienteService.adicionarCliente(cliente);
            return new ResponseEntity<>(clienteService.adicionarCliente(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar cliente pelo Id

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable String id) {
        try {
            return new ResponseEntity<>(clienteService.buscarCliente(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Atualizar dados do Cliente

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente pessoa, @PathVariable(value = "id") String id) {
        try {
            Cliente cliente = clienteService.findById(id);
            if (cliente != null) {
                pessoa.setID(cliente.getID());
                return new ResponseEntity<>((Cliente) clienteService.atualizarCliente(pessoa), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Exibir total de clientes cadastrados
    @GetMapping("/qtdeclientes")
    public long  qtdeClientes() {
        return clienteService.quantidadeDeClientes();
    }
}
