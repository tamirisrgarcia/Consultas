package dh.consultas.controller;

import dh.consultas.entity.Paciente;
import dh.consultas.entity.dto.PacienteDTO;
import dh.consultas.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin("*")
public class PacienteController {

    final
    PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<PacienteDTO> listar() {
        return pacienteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscar(@PathVariable Long id) {
        return pacienteService.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente) {
        return pacienteService.salvar(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> substituir(@PathVariable Long id, @RequestBody Paciente paciente) {
        return pacienteService.substituir(id, paciente);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Paciente> alterar(@PathVariable Long id, @RequestBody Paciente paciente) {
        return pacienteService.alterar(id, paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> deletar(@PathVariable Long id) {
        return pacienteService.deletar(id);
    }
}