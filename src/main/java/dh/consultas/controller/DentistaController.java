package dh.consultas.controller;

import dh.consultas.entity.Dentista;
import dh.consultas.entity.dto.DentistaDTO;
import dh.consultas.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    DentistaService service;

    @GetMapping()
    public List<DentistaDTO> buscar() {
        return service.buscar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaDTO> buscarId(@PathVariable Long id) {
        return service.buscarId(id);
    }

    @PostMapping
    public ResponseEntity<DentistaDTO> salvar(@RequestBody @Valid Dentista dentista) {
        return service.salvar(dentista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistaDTO> editar(@RequestBody Dentista dentista, @PathVariable Long id) {
        return service.editar(id, dentista);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DentistaDTO> editarParcial(@RequestBody Dentista dentista, @PathVariable Long id) {
        return service.editarParcial(id, dentista);
    }


}