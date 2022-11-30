package dh.consultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dh.consultas.entity.Dentista;
import dh.consultas.entity.dto.DentistaDTO;
import dh.consultas.repository.DentistaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    private static final Logger logger = LogManager.getLogger();

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

        logger.info("Buscando todos dentistas");
        return listDentistaDto;
    }

    public ResponseEntity<DentistaDTO> buscarId(Long id) {

        Optional<Dentista> optionalDentista = dentistaRepository.findById(id);

        if (optionalDentista.isEmpty())
            return ResponseEntity.notFound().build();

        ObjectMapper mapper = new ObjectMapper();
        Dentista dentista = optionalDentista.get();
        DentistaDTO dto = mapper.convertValue(dentista, DentistaDTO.class);

        logger.info("Buscando dentista por id");
        return ResponseEntity.ok(dto);

    }

    public ResponseEntity salvar(Dentista dentista) {
        try{
            logger.info("Novo dentista inserido com sucesso");
            Dentista dentistaSalvo = dentistaRepository.save(dentista);
            return new ResponseEntity( "Dentista "+dentistaSalvo.getNome()+" criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar dentista");
            return new ResponseEntity("Erro ao cadastrar dentista", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity editar(Long id, Dentista dentista) {
        Optional<Dentista> optionalDentista = dentistaRepository.findById(id);

        if(optionalDentista.isEmpty()) {
            return new ResponseEntity("Dentista informado não existe", HttpStatus.NOT_FOUND);
        }

        Dentista dentistaSalvo = optionalDentista.get();
        dentistaSalvo.setNome(dentista.getNome());
        dentistaSalvo.setSobrenome(dentista.getSobrenome());
        dentistaSalvo.setMatricula(dentista.getMatricula());

        try{
            logger.info("Dentista alterado com sucesso");
            return ResponseEntity.status(HttpStatus.CREATED).body(dentistaRepository.save(dentistaSalvo));
        } catch (Exception e) {
            logger.error("Erro ao editar dentista");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity editarParcial(Long id, Dentista dentistaDTO){
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> optionalDentista = dentistaRepository.findById((id));

        if(optionalDentista.isEmpty()) {
            logger.error("Dentista não encontrado");
            return new ResponseEntity("Dentista informado não existe", HttpStatus.NOT_FOUND);
        }

        Dentista dentista = optionalDentista.get();

        if(dentistaDTO.getNome() != null) {
            dentista.setNome(dentistaDTO.getNome());
        }
        if(dentistaDTO.getSobrenome() != null) {
            dentista.setSobrenome(dentistaDTO.getSobrenome());
        }
        if(dentistaDTO.getMatricula() != null) {
            dentista.setMatricula(dentistaDTO.getMatricula());
        }


        DentistaDTO dentistaAlterado = mapper.convertValue(dentistaRepository.save(dentista), DentistaDTO.class);
        logger.info("Dentista alterado com sucesso");
        return new ResponseEntity(dentistaAlterado, HttpStatus.CREATED);

    }
}