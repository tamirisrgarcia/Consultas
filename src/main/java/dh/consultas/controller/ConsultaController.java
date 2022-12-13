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
@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @PostMapping()
    public ResponseEntity salvar(@RequestBody @Valid ConsultaDTO consultaDTO) {
        consultaService.salvar(consultaDTO);
        return new ResponseEntity ("Consulta salva", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity buscar() throws ResourceNotFoundException {
        return new ResponseEntity (consultaService.listar(), HttpStatus.OK);
    }

    @GetMapping("{codigo}")
    public ResponseEntity buscarPorCodigo(@PathVariable String codigo) throws ResourceNotFoundException {
        return new ResponseEntity(consultaService.buscarPorCodigo(codigo), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deletar(@RequestParam("codigo") String codigo){
        return consultaService.deletar(codigo);
    }

    @PatchMapping("{codigo}")
    public ResponseEntity alteracaoParcial(@PathVariable String codigo, @RequestBody @Valid ConsultaDTO consultaDTO) {
        consultaService.alteracaoParcial(codigo, consultaDTO);
        return new ResponseEntity("Consulta atualizada", HttpStatus.OK);
    }
}