package dh.consultas.controller;

import dh.consultas.entity.dto.ConsultaDTO;
import dh.consultas.service.ConsultaService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j
@RestController
@RequestMapping("consulta")
@CrossOrigin("*")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @PostMapping()
    public ResponseEntity salvar(@Valid @RequestBody ConsultaDTO consultaDTO) {
        log.info("salvando consulta...");
        consultaService.salvar(consultaDTO);
        return new ResponseEntity("Consulta salva", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        consultaService.deletar(id);
        return new ResponseEntity("Consulta excluída", HttpStatus.OK);
    }

}