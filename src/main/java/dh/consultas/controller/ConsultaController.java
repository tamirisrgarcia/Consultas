package dh.consultas.controller;

import dh.consultas.entity.dto.ConsultaDTO;
import dh.consultas.exception.ResourceNotFoundException;
import dh.consultas.service.ConsultaService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j
@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @PostMapping()
    public ResponseEntity salvar(@Valid @RequestBody ConsultaDTO consultaDTO) {
        log.info("salvando consulta... CONTROLLER");
        consultaService.salvar(consultaDTO);
        return new ResponseEntity ("Consulta salva", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity buscar() throws ResourceNotFoundException {
        log.info("listando todas as consultas... CONTROLLER");
        return new ResponseEntity (consultaService.listar(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        log.info("buscando consulta... CONTROLLER");
        return new ResponseEntity(consultaService.buscarPorID(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        log.warn("excluindo consulta... CONTROLLER");
        consultaService.deletar(id);
        return new ResponseEntity("Consulta excluída", HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity alteracaoParcial(@PathVariable Long id, @RequestBody @Valid ConsultaDTO consultaDTO) {
        log.info("alterando consulta... CONTROLLER");
        consultaService.alteracaoParcial(id, consultaDTO);
        return new ResponseEntity("Consulta atualizada", HttpStatus.OK);
    }

}