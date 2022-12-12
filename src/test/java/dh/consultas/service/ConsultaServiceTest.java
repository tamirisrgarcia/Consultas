package dh.consultas.service;
import dh.consultas.entity.Dentista;
import dh.consultas.entity.Endereco;
import dh.consultas.entity.Paciente;
import dh.consultas.entity.dto.ConsultaDTO;
import dh.consultas.entity.dto.DentistaDTO;
import dh.consultas.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j;
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

    static ConsultaDTO consultaDTO;

    @BeforeEach
    public void doBefore() {

        log.info("Criando novo dentista");
        Dentista dentista = new Dentista(1L, "Maria", "Aquino", "123456" );
        dentistaService.salvar(dentista);

        log.info("Criando novo paciente");
        Paciente paciente = new Paciente(1L, "Felipe", "Arruda", "1234567890");
        log.info("Criando novo endereço");
        Endereco endereco = new Endereco(1L, "Rua F", 1234, null, "Medianeira", "Santa Maria", "RS", "97015230");
        paciente.setEndereco(endereco);
        pacienteService.salvar(paciente);

        log.info("Criando nova consulta");
        consultaDTO = new ConsultaDTO();
        consultaDTO.setDentista(dentista);
        consultaDTO.setPaciente(paciente);
        consultaDTO.setDataHoraConsulta(Timestamp.valueOf(LocalDateTime.of(2022, 12, 30, 9, 30)));

        consultaService.salvar(consultaDTO);
    }

    @Test
    void salvar() {
        log.info("salvando consulta");
        assertEquals("Felipe", consultaDTO.getPaciente().getNome());
    }

    @Test
    void listar() throws ResourceNotFoundException {
        log.info("listando todas as consultas");
        List<ConsultaDTO> consultaDTOList = consultaService.listar();
        assertTrue(consultaDTOList.size() > 0);
    }

    @Test
    void buscarPorID() throws ResourceNotFoundException {
        log.info("buscando consulta pelo ID");
        assertEquals(HttpStatus.OK, consultaService.buscarPorID(1L).getStatusCode());
    }

    /*
    @Test
    void alteracaoParcial() {
    }
    */

    @Test
    void deletar() throws ResourceNotFoundException {
        log.warn("excluindo consulta");
        assertEquals(HttpStatus.OK, consultaService.deletar(1L).getStatusCode());
    }
}