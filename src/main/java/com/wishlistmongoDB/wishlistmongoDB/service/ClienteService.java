package com.wishlistmongoDB.wishlistmongoDB.service;

import com.wishlistmongoDB.wishlistmongoDB.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wishlistmongoDB.wishlistmongoDB.repository.ClienteRepository;

@Service
public class ClienteService  {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente adicionarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente buscarCliente(String id)
    {
        return clienteRepository.findById(id).get();
    }

    public Cliente atualizarCliente(Cliente cliente)
    {
        return clienteRepository.save(cliente);
    }

    public long quantidadeDeClientes(){
        return clienteRepository.count();
    }

    public Cliente findById(String id) {
        return clienteRepository.findById(id).get();
    }
}
