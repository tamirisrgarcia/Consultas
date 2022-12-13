package dh.consultas.controller;

import dh.consultas.entity.Dentista;
import dh.consultas.entity.dto.DentistaDTO;
import dh.consultas.exception.ResourceNotFoundException;
import dh.consultas.service.DentistaService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j
@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    DentistaService service;

    @GetMapping()
    public List<DentistaDTO> buscar() {
        log.info("Buscando dentista");
        return service.buscar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaDTO> buscarId(@PathVariable Long id) throws ResourceNotFoundException {
        log.info("Buscando dentista por id");
        return service.buscarId(id);
    }

    @PostMapping
    public ResponseEntity<DentistaDTO> salvar(@RequestBody @Valid Dentista dentista) {
        log.info("Salvando dentista");
        return service.salvar(dentista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistaDTO> editar(@RequestBody @Valid Dentista dentista, @PathVariable Long id) throws ResourceNotFoundException {
        log.info("Editando dentista por id");
        return service.editar(id, dentista);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DentistaDTO> editarParcial(@RequestBody @Valid Dentista dentista, @PathVariable Long id) {
        log.info("Editando dentista parcialmente por id");
        return service.editarParcial(id, dentista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DentistaDTO> deletar(@PathVariable Long id) {
        log.info("Deletando dentista por id");
        return service.deletar(id);
    }


}