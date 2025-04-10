package com.example.Biblioteca.Service;


import com.example.Biblioteca.DTO.ClienteDTO;
import com.example.Biblioteca.Entity.Cliente;
import com.example.Biblioteca.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class clienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente fromDTO(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(cliente.getCpf());

        return cliente;
    }

    // converte Cliente para ClienteDTO
    public ClienteDTO toDTO(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setSobrenome(cliente.getSobrenome());
        clienteDTO.setCpf(cliente.getCpf());

        return clienteDTO;
    }

    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }

    public Optional<ClienteDTO> getById(Long id){
        // busca o cliente no banco de dados com base no ID
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if(optionalCliente.isPresent()){// verifica se encontrou algum cliente
            // transforma a entidade cliente para DTO
            // e também coloca dentro de um objeto Optional
            return Optional.of(this.toDTO(Cliente.get()));
        }else {
            return Optional.empty(); // um objeto Optional vazio.
        }
//        return clieenteRepository.findById(id).map(this::toDTO);
    }

    public ClienteDTO save(ClienteDTO clienteDTO){
        // converte de DTO para Entidade
        Cliente cliente = this.fromDTO(clienteDTO);
        // salva no banco de dados a entidade
        Cliente clienteBd = clienteRepository.save(cliente);
        // da return transformando novamente para DTO
        return this.toDTO(clienteBd);
    }

    public Optional<ClienteDTO> updateCliente(Long id, ClienteDTO clienteDTO){
        // busca o cliente no banco de dados com base no ID enviado
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        //verifica se encontrou algum cliente para ser atualizado
        if(optionalCliente.isPresent()){
            // caso encontrar um cliente, instancia uma entidade com o nome "cliente", passando o cliente que esta no banco de dados
            Cliente cliente = Cliente.get();
            // atualizando os dados da entidade "cliente" que veio do banco de dados
            cliente.setNome(clienteDTO.getNome());
            cliente.setCpf(clienteDTO.getCpf());

            // salva no banco dados o cliente com o dados atualizados
            Cliente clienteUpdate = clienteRepository.save(cliente);

            // transforma a entidade cliente que veio como retorno do banco de dados em um DTO para ser retornado
            return Optional.of(this.toDTO(clienteUpdate));
        }else { // se nao encontrar retonar um Objeto Optinal vazio.
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        // funcao verifica se existe se esse id existe no banco de dados
        // se ele existir acontece o delete
        // assim não precisa trazer o objeto inteiro para ser deletado, melhorando o desempenho
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

}
