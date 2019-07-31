package org.generation.jogo.Quiz.usuario;

import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_quiz/v1")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // READ
    @GetMapping("/usuarios")
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/usuarios/{id}")
    public Optional<Usuario> findById(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }

    // CREATE
    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save (@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // UPDATE
    @PutMapping("/usuarios/{id}")
    public Usuario update (@PathVariable Long id, @RequestBody Usuario usuario) throws ResourceNotFoundException {
        return usuarioRepository.findById(id).map(u -> {
            u.setUsername(usuario.getUsername());
            u.setSenha(usuario.getSenha());
            return usuarioRepository.save(u);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe usuario cadastrada com o id" + id));
    }

    //DELETE
    @DeleteMapping("/usuarios/{id}")
    public void delete (@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

}
