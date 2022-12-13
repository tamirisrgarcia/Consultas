package dh.consultas.service;

import dh.consultas.entity.Paciente;
import dh.consultas.entity.dto.PacienteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    @Test
    void listar() {
        List<PacienteDTO> pacientes = pacienteService.listar();
        Assertions.assertNotNull(pacientes);
    }

    @Test
    void salvar() {
        Paciente paciente = new Paciente();
        paciente.setNome("Maria");
        paciente.setSobrenome("Silva");
        paciente.setRg("8561579401");
        pacienteService.salvar(paciente);
        Assertions.assertNotNull(paciente);
    }


}