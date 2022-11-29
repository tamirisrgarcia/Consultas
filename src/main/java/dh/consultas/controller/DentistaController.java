package dh.consultas.controller;

import dh.consultas.entity.Dentista;
import dh.consultas.entity.Paciente;
import dh.consultas.entity.dto.DentistaDTO;
import dh.consultas.entity.dto.PacienteDTO;
import dh.consultas.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    DentistaService service;

    @GetMapping()
    public List<DentistaDTO> buscar() { return service.buscar();}

    @GetMapping("/{id}")
    public ResponseEntity<DentistaDTO> buscarId(@PathVariable Long id) {
        return service.buscarId(id);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Dentista dentista) {
        return service.salvar(dentista);
    }

    @PatchMapping( "/{id}")
    public ResponseEntity editar(@RequestBody Dentista dentista, @PathVariable Long id) {
        return service.editar(id, dentista);
    }



}