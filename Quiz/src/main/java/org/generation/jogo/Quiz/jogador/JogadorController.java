package org.generation.jogo.Quiz.jogador;

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
public class JogadorController {

    @Autowired
    private JogadorRepository jogadorRepository;

    @ApiOperation(value = "Lista todos os jogadores", notes = "Lista todos os jogadores", response = Jogador.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consulta de todos os jogadores com sucesso!!")
    })
    // READ
    @GetMapping("/jogadores")
    public List<Jogador> findAll() {
        return jogadorRepository.findAll();
    }

    @ApiOperation(value = "Lista um jogador especifico", notes = "Lista um jogador especifico", response = Jogador.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Este jogador existe")
    })
    @GetMapping("/jogadores/{id}")
    public Optional<Jogador> findById(@PathVariable Long id) {
        return jogadorRepository.findById(id);
    }

    @ApiOperation(value = "Insere um novo jogador", notes = "Insere um novo jogador", response = Jogador.class )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Inclusão com sucesso de um novo jogador")
    })
    // CREATE
    @PostMapping("/jogadores")
    @ResponseStatus(HttpStatus.CREATED)
    public Jogador save (@RequestBody Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    @ApiOperation(value = "Atualiza um jogador existente", notes = "Atualizar um jogador existente", response = Jogador.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Atualização com sucesso de um jogador")
    })
    // UPDATE
    @PutMapping("/jogadores/{id}")
    public Jogador update (@PathVariable Long id, @RequestBody Jogador jogador) throws ResourceNotFoundException {
        return jogadorRepository.findById(id).map(j -> {
            j.setNome(jogador.getNome());
            j.setNivel(jogador.getNivel());
            j.setPontuacao(jogador.getPontuacao());
            return jogadorRepository.save(j);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe jogador cadastrado com o id" + id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Exclui um jogador existente", notes = "Exclui um jogador existente", response = Jogador.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Exclusão com sucesso de um jogador")
    })
    //DELETE
    @DeleteMapping("/jogadores/{id}")
    public void delete (@PathVariable Long id) {
        jogadorRepository.deleteById(id);
    }
}
