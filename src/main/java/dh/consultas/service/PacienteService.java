package dh.consultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dh.consultas.entity.Paciente;
import dh.consultas.entity.dto.PacienteDTO;
import dh.consultas.repository.PacienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
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
        return dto;
    }

    public ResponseEntity<PacienteDTO> buscar(Long id) {

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isEmpty())
            return ResponseEntity.notFound().build();

        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = optionalPaciente.get();
        PacienteDTO dto = mapper.convertValue(paciente, PacienteDTO.class);

        return ResponseEntity.ok(dto);

    }

    public ResponseEntity<Paciente> salvar(Paciente paciente) {
        try {
            pacienteRepository.save(paciente);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(paciente);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<Paciente> substituir(Long id, Paciente paciente) {

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isEmpty())
            return ResponseEntity.notFound().build();

        pacienteRepository.delete(optionalPaciente.get());

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pacienteRepository
                            .save(paciente));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(paciente);
        }

    }

    public ResponseEntity<Paciente> alterar(Long id, Paciente paciente) {

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isEmpty())
            return ResponseEntity.notFound().build();

        Paciente pacienteSalvo = optionalPaciente.get();

        pacienteRepository.delete(pacienteSalvo);

        if (!pacienteSalvo.getNome().equals(paciente.getNome()))
            pacienteSalvo.setNome(paciente.getNome());

        if (!pacienteSalvo.getSobrenome().equals(paciente.getSobrenome()))
            pacienteSalvo.setSobrenome(paciente.getSobrenome());

        if (!pacienteSalvo.getRg().equals(paciente.getRg()))
            pacienteSalvo.setRg(paciente.getRg());

        if (!pacienteSalvo.getEndereco().equals(paciente.getEndereco()))
            pacienteSalvo.setEndereco(paciente.getEndereco());

        if (!Objects.equals(pacienteSalvo.getAlta(), paciente.getAlta()))
            pacienteSalvo.setAlta(paciente.getAlta());


        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pacienteRepository
                            .save(pacienteSalvo));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(pacienteSalvo);
        }
    }

    public ResponseEntity<Paciente> deletar(Long id, Paciente paciente) {

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isEmpty())
            return ResponseEntity.notFound().build();

        pacienteRepository.delete(optionalPaciente.get());

        return ResponseEntity.ok(paciente);
    }

}