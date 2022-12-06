package dh.consultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dh.consultas.entity.Consulta;
import dh.consultas.entity.dto.ConsultaDTO;
import dh.consultas.repository.ConsultaRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j
@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;

    public ResponseEntity salvar(ConsultaDTO consultaDTO) {
        ObjectMapper mapper = new ObjectMapper();
        Consulta consulta = mapper.convertValue(consultaDTO, Consulta.class);
        try {
            consultaRepository.save(consulta);
            log.info("salvando consulta...");
            return new ResponseEntity("Consulta salva", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Erro ao salvar a consulta");
            return new ResponseEntity("Erro ao salvar a consulta", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deletar(Long id) {
        log.info("deletando consulta... SERVICE");

        try {
            consultaRepository.deleteById(id);
            return new ResponseEntity("Consulta apagada.", HttpStatus.OK);

        } catch (Exception e) {
            log.error("erro ao deletar consulta... SERVICE");
            return new ResponseEntity("Erro! A consulta não foi excluída.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity listar() {
        log.info("buscando todas as consultas... SERVICE");

        List<Consulta> listaConsultas = consultaRepository.findAll();
        List<ConsultaDTO> consultaDTOList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Consulta c : listaConsultas) {
            consultaDTOList.add(mapper.convertValue(c, ConsultaDTO.class));
        }

        if (consultaDTOList.isEmpty()) {
            log.warn("lista de consultas vazia... SERVICE");
            return new ResponseEntity("Não há consultas cadastradas.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(consultaDTOList, HttpStatus.OK);
    }

    public ResponseEntity buscarPorID(Long id) {
        log.info("buscando consulta por id... SERVICE");

        ObjectMapper mapper = new ObjectMapper();
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);

        if (optionalConsulta.isEmpty()) {
            return new ResponseEntity("Não foi possível encontrar a consulta.", HttpStatus.NOT_FOUND);
        }

        Consulta c = optionalConsulta.get();
        ConsultaDTO consultaDTO = mapper.convertValue(c, ConsultaDTO.class);
        return new ResponseEntity(consultaDTO, HttpStatus.OK);
    }
}