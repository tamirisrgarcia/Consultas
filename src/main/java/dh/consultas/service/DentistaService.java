package dh.consultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dh.consultas.entity.Dentista;
import dh.consultas.entity.Paciente;
import dh.consultas.entity.dto.DentistaDTO;
import dh.consultas.entity.dto.PacienteDTO;
import dh.consultas.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<DentistaDTO> buscarId(Long id) {

        Optional<Dentista> optionalDentista = dentistaRepository.findById(id);

        if (optionalDentista.isEmpty())
            return ResponseEntity.notFound().build();

        ObjectMapper mapper = new ObjectMapper();
        Dentista dentista = optionalDentista.get();
        DentistaDTO dto = mapper.convertValue(dentista, DentistaDTO.class);

        return ResponseEntity.ok(dto);

    }

    public ResponseEntity salvar(Dentista dentista) {
        try{
            Dentista dentistaSalvo = dentistaRepository.save(dentista);
            return new ResponseEntity( "Dentista "+dentistaSalvo.getNome()+" criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Erro ao cadastrar dentista", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity editar(Long id, Dentista dentista) {
        Optional<Dentista> optionalDentista = dentistaRepository.findById(id);

        if(optionalDentista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        dentistaRepository.delete(optionalDentista.get());

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(dentistaRepository.save(dentista));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dentista);
        }
    }
}