package dh.consultas.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import dh.consultas.entity.Consulta;
import dh.consultas.entity.dto.ConsultaDTO;
import dh.consultas.exception.ResourceNotFoundException;
import dh.consultas.repository.ConsultaRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j
@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository consultaRepository;

    public ResponseEntity salvar(ConsultaDTO consultaDTO) {
        ObjectMapper mapper = new ObjectMapper();
        Consulta consulta = mapper.convertValue(consultaDTO, Consulta.class);
        try {
            log.info("salvando consulta");
            consultaRepository.save(consulta);
            return new ResponseEntity("Consulta salva", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Erro ao salvar a consulta");
            e.printStackTrace();
            return new ResponseEntity("Erro ao salvar a consulta", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deletar(Long id) {
        try {
            log.info("deletando consulta");
            consultaRepository.deleteById(id);
            return new ResponseEntity("Consulta apagada.", HttpStatus.OK);

        } catch (Exception e) {
            log.error("erro ao deletar consulta");
            e.printStackTrace();
            return new ResponseEntity("Erro! A consulta não foi excluída.", HttpStatus.BAD_REQUEST);
        }
    }

    public List<ConsultaDTO> listar() throws ResourceNotFoundException{
        log.info("buscando todas as consultas");
        List<Consulta> consultaList = consultaRepository.findAll();
        List<ConsultaDTO> consultaDTOList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Consulta c : consultaList) {
            consultaDTOList.add(mapper.convertValue(c, ConsultaDTO.class));
        }

        if (consultaDTOList.isEmpty()) {
            log.error("Nenhuma consulta encontrada");
            throw new ResourceNotFoundException("Nenhuma consulta agendada");
        }

        return consultaDTOList;
    }

    public ResponseEntity buscarPorID(Long id) throws ResourceNotFoundException {
        log.info("buscando consulta por id");
        ObjectMapper mapper = new ObjectMapper();
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);

        if (optionalConsulta.isEmpty()) {
            log.error("nenhuma consulta encontrada");
            throw new ResourceNotFoundException("Consulta não encontrada");
        }

        Consulta c = optionalConsulta.get();
        ConsultaDTO consultaDTO = mapper.convertValue(c, ConsultaDTO.class);
        return new ResponseEntity(consultaDTO, HttpStatus.OK);
    }

    public ResponseEntity alteracaoParcial(Long id, ConsultaDTO consultaDTO) {

        ObjectMapper mapper = new ObjectMapper();
        Optional<Consulta> consultaOptional = consultaRepository.findById(id);

        if (consultaOptional.isEmpty()) {
            log.info("Erro ao atualizar consulta");
            return new ResponseEntity("Consulta não existe", HttpStatus.NOT_FOUND);
        }

        Consulta c = consultaOptional.get();

        if (consultaDTO.getDentista() != null) {
            c.setDentista(consultaDTO.getDentista());
        }
        if (consultaDTO.getPaciente() != null) {
            c.setPaciente(consultaDTO.getPaciente());
        }
        if (consultaDTO.getDataHoraConsulta() != null) {
            c.setDataHoraConsulta(consultaDTO.getDataHoraConsulta());
        }

        ConsultaDTO consultaAtualizada = mapper.convertValue(consultaRepository.save(c), ConsultaDTO.class);
        log.info("Consulta alterada");
        return new ResponseEntity(consultaAtualizada, HttpStatus.CREATED);
    }
}