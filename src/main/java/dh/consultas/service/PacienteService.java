package dh.consultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dh.consultas.entity.Paciente;
import dh.consultas.entity.dto.PacienteDTO;
import dh.consultas.exception.ResourceNotFoundException;
import dh.consultas.repository.PacienteRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j
public class PacienteService {
    final
    PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteDTO> listar() {

        List<Paciente> list = pacienteRepository.findAll();
        List<PacienteDTO> dto = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Paciente paciente : list) {
            PacienteDTO pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
            dto.add(pacienteDTO);
        }
        log.info("listando pacientes");
        return dto;
    }

    public ResponseEntity<PacienteDTO> buscar(Long id) throws ResourceNotFoundException {

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isEmpty())
            throw new ResourceNotFoundException("Paciente não encontrado");

        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = optionalPaciente.get();
        PacienteDTO dto = mapper.convertValue(paciente, PacienteDTO.class);
        log.info("buscando paciente");
        return ResponseEntity.ok(dto);

    }

    public ResponseEntity<Paciente> salvar(Paciente paciente) {
        try {
            pacienteRepository.save(paciente);
            log.info("salvando paciente");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(paciente);
        } catch (Exception e) {
            log.error("erro ao salvar paciente");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<Paciente> substituir(Long id, Paciente paciente) throws ResourceNotFoundException {

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isEmpty()) {
            log.error("paciente não encontrado");
            throw new ResourceNotFoundException("Paciente não encontrado");
        }
        try {
            log.info("substituindo paciente");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pacienteRepository
                            .save(paciente));
        } catch (Exception e) {
            log.error("paciente não encontrado");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

    }

    public ResponseEntity<Paciente> alterar(Long id, Paciente paciente) {

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isEmpty()) {
            log.error("paciente não encontrado");
            return ResponseEntity.notFound().build();

        }

        Paciente pacienteSalvo = optionalPaciente.get();


        if (!paciente.getNome().isBlank())
            pacienteSalvo.setNome(paciente.getNome());

        if (!paciente.getSobrenome().isBlank())
            pacienteSalvo.setSobrenome(paciente.getSobrenome());

        if (!paciente.getRg().isBlank())
            pacienteSalvo.setRg(paciente.getRg());

        if (paciente.getEndereco() != null)
            pacienteSalvo.setEndereco(paciente.getEndereco());

        if (paciente.getAlta() != null)
            pacienteSalvo.setAlta(paciente.getAlta());


        try {
            log.info("alterando paciente");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pacienteRepository
                            .save(pacienteSalvo));
        } catch (Exception e) {
            log.error("erro ao alterar paciente");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<Paciente> deletar(Long id) {

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isEmpty()) {
            log.error("paciente não encontrado");
            return ResponseEntity.notFound().build();
        }

        pacienteRepository.delete(optionalPaciente.get());
        log.info("paciente deletado");
        return ResponseEntity.ok().build();
    }

}