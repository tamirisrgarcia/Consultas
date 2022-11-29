package dh.consultas.controller;
import dh.consultas.entity.Consulta;
import dh.consultas.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consulta")
@CrossOrigin("*")
public class ConsultaController {

    @Autowired
    ConsultaService service;

    @GetMapping
    public ResponseEntity buscarConsulta() {
        List<Consulta> listaConsulta = service.listar();
        if (listaConsulta == null) {
            return new ResponseEntity<>("Não há consultas agendadas", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(listaConsulta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity salvarConsulta (@RequestBody Consulta consulta){
        return new ResponseEntity(service.salvar(consulta), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity atualizarConsulta(@RequestBody Consulta consulta){
        if (consulta.getId() == null) {
            return new ResponseEntity<>("Não foi possível encontrar a consulta", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.atualizar(consulta), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity excluirConsulta (@RequestParam("id") Long id) throws Exception {
        //todo fazer validaçao
        service.excluir(id);
        return new ResponseEntity("Paciente excluído", HttpStatus.OK);
    }


}