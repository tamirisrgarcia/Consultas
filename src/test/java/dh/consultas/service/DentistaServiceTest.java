package dh.consultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dh.consultas.entity.Dentista;
import dh.consultas.entity.dto.DentistaDTO;
import dh.consultas.exception.ResourceNotFoundException;
import dh.consultas.repository.DentistaRepository;
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

    @Autowired
    DentistaRepository dentistaRepository;

    //ObjectMapper mapper = new ObjectMapper();


    @Test
    void salvar(){
        Dentista dentista = new Dentista();

        dentista.setNome("Joyce");
        dentista.setSobrenome("Gomes");
        dentista.setMatricula("MAT456897");

        dentistaService.salvar(dentista);

        Assertions.assertNotNull(dentista.getId());

    }

    @Test
    void editar(){
        Dentista dentista = new Dentista();

        dentista.setNome("Julio");
        dentista.setSobrenome("Gomes");
        dentista.setMatricula("MAT43215");
        dentistaService.salvar(dentista);

        Dentista dentistaNovo = dentista;

        dentistaNovo.setMatricula("MAT78901");

        dentistaService.editarParcial(dentista.getId(), dentista);

        assertEquals("MAT78901", dentista.getMatricula());

    }

    @Test
    void buscarId() throws ResourceNotFoundException {

        Dentista dentista = new Dentista();

        dentista.setNome("Roberta");
        dentista.setSobrenome("Carolina");
        dentista.setMatricula("MAT214563");
        var dentistaSalvo = dentistaRepository.save(dentista);

        var dentistaNovo = dentistaService.buscarId(dentistaSalvo.getId());

        assertEquals(200, dentistaNovo.getStatusCodeValue());
    }

    @Test
    void deletar() throws ResourceNotFoundException {

        Dentista dentista = new Dentista();

        dentista.setNome("Caroline");
        dentista.setSobrenome("Ribeiro");
        dentista.setMatricula("MAT456321");
        var dentistaSalvo = dentistaRepository.save(dentista);

        var dentistaNovo = dentistaService.deletar(dentistaSalvo.getId());

        assertEquals(200, dentistaNovo.getStatusCodeValue());

    }

}