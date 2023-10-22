package edu.digitalinnovationone.entregas.service.impl;

import edu.digitalinnovationone.entregas.model.Cliente;
import edu.digitalinnovationone.entregas.model.Endereco;
import edu.digitalinnovationone.entregas.repository.ClienteRepository;
import edu.digitalinnovationone.entregas.repository.EnderecoRepository;
import edu.digitalinnovationone.entregas.service.ClienteService;
import edu.digitalinnovationone.entregas.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClienteServiceimpl implements ClienteService {


    private ClienteRepository clienteRepository;

    private EnderecoRepository enderecoRepository;

    private ViaCepService viaCepService;

    public ClienteServiceimpl(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, ViaCepService viaCepService) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    @Override
    public Iterable<Cliente> buscarTodos() {

        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {// Caso não exista, integrar com o ViaCEP e persistir o retorno.

            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);
    }
}
