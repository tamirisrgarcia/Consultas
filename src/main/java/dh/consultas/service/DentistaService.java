package dh.consultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dh.consultas.entity.Dentista;
import dh.consultas.entity.dto.DentistaDTO;
import dh.consultas.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DentistaService {

    @Autowired
    DentistaRepository dentistaRepository;

    public List<DentistaDTO> buscar() {
        List<Dentista> listDentista = dentistaRepository.findAll();
        List<DentistaDTO> listDentistaDto = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for(Dentista dentista : listDentista) {
            DentistaDTO dentistaDTO = mapper.convertValue(dentista, DentistaDTO.class);
            listDentistaDto.add(dentistaDTO);
        }
        return listDentistaDto;
    }

    public ResponseEntity salvar(Dentista dentista) {
        try{
            Dentista dentistaSalvo = dentistaRepository.save(dentista);
            return new ResponseEntity( "Dentista "+dentistaSalvo.getNome()+" criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Erro ao cadastrar dentista", HttpStatus.BAD_REQUEST);
        }
    }

    //public ResponseEntity editar(Long id, Dentista dentista) {
       // verificar se o id do dentista existe dando um get na base

        // se nao existir lanár excecao com o erro 404
        //se existir com o get que vc fez na base atualize as propriedades com o dentista que ta vindo
        // chamar o metodo para atualizar na base
        // retornar 200
    //}
}