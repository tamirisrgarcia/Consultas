package dh.consultas.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import dh.consultas.entity.Consulta;
import dh.consultas.entity.Dentista;
import dh.consultas.entity.dto.ConsultaDTO;
import dh.consultas.repository.ConsultaRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaService {
    
    @Autowired
    ConsultaRepository repository;

    public Consulta salvar(Consulta consulta) {
        return repository.save(consulta);
    }

    public List<Consulta> listar() {
        return repository.findAll();
    }

    public List<ConsultaDTO> listarDTO() {

        List<Consulta> listaConsulta = repository.findAll();
        List<ConsultaDTO> consultaDTOList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Consulta consulta : listaConsulta) {
            consultaDTOList.add(mapper.convertValue(consulta, ConsultaDTO.class));
        }
        return consultaDTOList;
    }


    public Consulta atualizar (Consulta consulta) {
        return repository.save(consulta);
    }


    public void excluir (Long id) throws Exception{
        repository.findById(id).orElseThrow(
                () -> new Exception("Erro ao excluir consulta. Id informado não existe"));
        repository.deleteById(id);
    }
}