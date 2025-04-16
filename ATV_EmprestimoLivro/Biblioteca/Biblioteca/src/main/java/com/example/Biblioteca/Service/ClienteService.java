package com.example.Biblioteca.Service;

import com.example.Biblioteca.DTO.ClienteDTO;
import com.example.Biblioteca.Entity.Cliente;
import com.example.Biblioteca.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Converte de ClienteDTO para Cliente
    public Cliente fromDTO(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf()); // Corrigido aqui
        cliente.setSobrenome(clienteDTO.getSobrenome()); // Adicionando o sobrenome

        return cliente;
    }

    // Converte Cliente para ClienteDTO
    public ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setSobrenome(cliente.getSobrenome());
        clienteDTO.setCpf(cliente.getCpf());

        return clienteDTO;
    }

    // Busca todos os clientes
    public List<ClienteDTO> getAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::toDTO) // Converte cada cliente para ClienteDTO
                .collect(Collectors.toList());
    }

    // Busca um cliente pelo ID e retorna o DTO
    public Optional<ClienteDTO> getById(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            return Optional.of(this.toDTO(optionalCliente.get())); // Corrigido
        } else {
            return Optional.empty(); // Retorna Optional vazio caso não encontre
        }
    }

    // Salva um novo cliente
    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = this.fromDTO(clienteDTO); // Converte o DTO para entidade
        Cliente clienteBd = clienteRepository.save(cliente); // Salva a entidade no banco
        return this.toDTO(clienteBd); // Retorna o DTO do cliente salvo
    }

    // Atualiza um cliente existente
    public Optional<ClienteDTO> updateCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNome(clienteDTO.getNome());
            cliente.setSobrenome(clienteDTO.getSobrenome()); // Corrigido
            cliente.setCpf(clienteDTO.getCpf());

            Cliente clienteUpdated = clienteRepository.save(cliente); // Atualiza no banco
            return Optional.of(this.toDTO(clienteUpdated)); // Retorna o DTO do cliente atualizado
        } else {
            return Optional.empty(); // Retorna Optional vazio caso não encontre o cliente
        }
    }

    // Deleta um cliente pelo ID
    public boolean delete(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id); // Deleta o cliente no banco
            return true;
        } else {
            return false; // Retorna false caso o cliente não seja encontrado
        }
    }
}
