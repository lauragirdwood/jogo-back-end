package org.generation.jogo.Quiz.usuario;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Lista todos os usuários", notes = "Lista todos os usuários", response = Usuario.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consulta de todos os usuários com sucesso!!")
    })
    // READ
    @GetMapping("/usuarios")
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @ApiOperation(value = "Lista um usuário especifico", notes = "Lista um usuário especifico", response = Usuario.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Este usuário existe")
    })
    // READ BY ID
    @GetMapping("/usuarios/{id}")
    public Optional<Usuario> findById(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }

    @ApiOperation(value = "Insere um novo usuário", notes = "Insere um novo usuário", response = Usuario.class )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Inclusão com sucesso de um novo usuário")
    })
    // CREATE
    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save (@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @ApiOperation(value = "Atualiza um usuário existente", notes = "Atualizar um usuário existente", response = Usuario.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Atualização com sucesso de um usuário")
    })
    // UPDATE
    @PutMapping("/usuarios/{id}")
    public Usuario update (@PathVariable Long id, @RequestBody Usuario usuario) throws ResourceNotFoundException {
        return usuarioRepository.findById(id).map(u -> {
            u.setUsername(usuario.getUsername());
            u.setSenha(usuario.getSenha());
            return usuarioRepository.save(u);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe usuario cadastrada com o id" + id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Exclui um usuário existente", notes = "Exclui um usuário existente", response = Usuario.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Exclusão com sucesso de um usuário")
    })
    //DELETE
    @DeleteMapping("/usuarios/{id}")
    public void delete (@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

}
