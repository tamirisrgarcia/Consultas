package dh.consultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dh.consultas.entity.Dentista;
import dh.consultas.entity.dto.DentistaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistaServiceTest {

    @Autowired
    DentistaService dentistaService;
    ObjectMapper mapper = new ObjectMapper();


    @Test
    void salvar(){
        Dentista dentista = new Dentista();

        dentista.setNome("Marco");
        dentista.setSobrenome("Silva");
        dentista.setMatricula("MAT23456");

        dentistaService.salvar(dentista);

        Assertions.assertNotNull(dentista.getId());

    }

    @Test
    void editarParcial(){
        Dentista dentista = new Dentista();

        dentista.setNome("Juliana");
        dentista.setSobrenome("Gomes");
        dentista.setMatricula("MAT54321");
        dentistaService.salvar(dentista);

        Dentista dentistaNovo = dentista;

        dentistaNovo.setMatricula("MAT56789");

        dentistaService.editarParcial(2L, dentista);

        assertEquals("MAT56789", dentista.getMatricula());

    }

    @Test
    void buscarId() {

        Dentista dentista = new Dentista();

        dentista.setNome("Roberto");
        dentista.setSobrenome("Santos");
        dentista.setMatricula("MAT321456");
        dentistaService.salvar(dentista);

        Dentista dentistaNovo = dentista;
        System.out.println(dentistaNovo.getId());

    }


}