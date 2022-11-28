package dh.consultas.service;
import dh.consultas.entity.Endereco;
import dh.consultas.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    public Endereco salvarEndereco(Endereco e){
        return repository.save(e);
    }

    public List<Endereco> listarEnderecos(){
        List<Endereco> enderecoList = repository.findAll();
        return enderecoList;
    }

    public Endereco atualizarEndereco(Endereco e){
        return repository.save(e);
    }

    public void excluirEndereco (Long id) {}
}