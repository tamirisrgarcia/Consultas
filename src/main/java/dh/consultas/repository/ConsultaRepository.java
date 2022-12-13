package dh.consultas.repository;
import dh.consultas.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Optional<Consulta> findByCodigo(String codigo);
}