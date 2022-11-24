package dh.consultas.controller;

import dh.consultas.entity.Dentista;
import dh.consultas.entity.dto.DentistaDTO;
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

    @PostMapping
    public ResponseEntity salvar(@RequestBody Dentista dentista) {
        return service.salvar(dentista);
    }

    //@PatchMapping
    //public ResponseEntity editar(@RequestBody Dentista dentista, Long id) {
    //    return service.editar(id, dentista);
    //}



}