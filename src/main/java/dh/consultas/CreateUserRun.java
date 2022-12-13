package dh.consultas;

import dh.consultas.entity.Usuario;
import dh.consultas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Configuration
public class CreateUserRun implements ApplicationRunner {

    @Autowired
    UsuarioRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder brCrypt = new BCryptPasswordEncoder();
        Optional<Usuario> username = repository.findByUsername("dois");
        Usuario usuario = new Usuario();
        usuario.setPassword(brCrypt.encode("dois"));
        usuario.setUsername("dois");
        if (username.isEmpty()) {
            repository.save(usuario);
        }

    }
}