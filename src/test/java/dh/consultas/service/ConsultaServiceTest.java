package dh.consultas.service;
import dh.consultas.entity.Consulta;
import dh.consultas.entity.Dentista;
import dh.consultas.entity.Endereco;
import dh.consultas.entity.Paciente;
import dh.consultas.entity.dto.ConsultaDTO;
import dh.consultas.entity.dto.DentistaDTO;
import dh.consultas.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Log4j
@SpringBootTest
class ConsultaServiceTest {

    @Autowired
    ConsultaService consultaService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    DentistaService dentistaService;

    @Test
    void salvar() {
        log.info("Criando novo dentista");
        Dentista dentista1 = new Dentista("Diego", "Martins", "234567");
        dentistaService.salvar(dentista1);

        log.info("Criando novo paciente");
        Paciente paciente1 = new Paciente("Laura", "Arruda", "0987654321");
        log.info("Criando novo endereço");
        Endereco endereco = new Endereco("Rua F", 1800, null, "Medianeira", "Santa Maria", "RS", "97015230");
        paciente1.setEndereco(endereco);
        pacienteService.salvar(paciente1);

        log.info("Criando nova consulta");
        ConsultaDTO consulta1 = new ConsultaDTO("cod1", paciente1, dentista1, Timestamp.valueOf("2023-12-10 10:00:00"));

        assertEquals(201, consultaService.salvar(consulta1).getStatusCodeValue());
    }

    @Test
    void buscar() throws ResourceNotFoundException {
        log.info("Criando novo dentista");
        Dentista dentista2 = new Dentista("Maria", "Aquino", "123456");
        dentistaService.salvar(dentista2);

        log.info("Criando novo paciente");
        Paciente paciente2 = new Paciente("Felipe", "Arruda", "1234567890");
        log.info("Criando novo endereço");
        Endereco endereco = new Endereco("Rua F", 1234, null, "Medianeira", "Santa Maria", "RS", "97015230");
        paciente2.setEndereco(endereco);
        pacienteService.salvar(paciente2);

        log.info("Criando nova consulta");
        ConsultaDTO consulta2 = new ConsultaDTO("cod2", paciente2, dentista2, Timestamp.valueOf("2023-12-10 08:00:00"));
        consultaService.salvar(consulta2);

        assertTrue(consultaService.listar().size() > 0);
    }

    @Test
    void buscarPorCodigo() throws ResourceNotFoundException {
        log.info("Criando novo dentista");
        Dentista dentista3 = new Dentista("Vera", "Berriel", "103956");
        dentistaService.salvar(dentista3);

        log.info("Criando novo paciente");
        Paciente paciente3 = new Paciente("Luiza", "Martins", "1834564890");
        log.info("Criando novo endereço");
        Endereco endereco = new Endereco("Rua F", 7501, null, "Medianeira", "Santa Maria", "RS", "97015230");
        paciente3.setEndereco(endereco);
        pacienteService.salvar(paciente3);

        log.info("Criando nova consulta");
        ConsultaDTO consulta2 = new ConsultaDTO("cod3", paciente3, dentista3, Timestamp.valueOf("2023-12-10 11:00:00"));
        consultaService.salvar(consulta2);


        assertEquals(200,consultaService.buscarPorCodigo("cod3").getStatusCodeValue());
        }

    @Test
    void deletar() {
        assertEquals(200, consultaService.deletar("cod1").getStatusCode().value());
    }
    
}