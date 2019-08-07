package org.generation.jogo.Quiz.pergunta;

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
public class PerguntaController {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @ApiOperation(value = "Lista todas as pergunta", notes = "Lista todas as pergunta", response = Pergunta.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consulta de todas as pergunta com sucesso!!")
    })
    // READ
    @GetMapping("/perguntas")
    public List<Pergunta> findAll() {
        return perguntaRepository.findAll();
    }

    @ApiOperation(value = "Lista uma pargunta especifica", notes = "Lista uma pargunta especifica", response = Pergunta.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Esta pargunta existe")
    })
    // READ BY ID
    @GetMapping("/perguntas/{id}")
    public Optional<Pergunta> findById(@PathVariable Long id) {
        return perguntaRepository.findById(id);
    }

    @ApiOperation(value = "Insere uma nova pargunta", notes = "Insere uma nova pargunta", response = Pergunta.class )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Inclusão com sucesso de uma nova pargunta")
    })
    // CREATE
    @PostMapping("/perguntas")
    @ResponseStatus(HttpStatus.CREATED)
    public Pergunta save (@RequestBody Pergunta pergunta) {
        return perguntaRepository.save(pergunta);
    }

    @ApiOperation(value = "Atualiza uma  pargunta existente", notes = "Atualizar uma  pargunta existente", response = Pergunta.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Atualização com sucesso de uma  pargunta")
    })
    // UPDATE
    @PutMapping("/perguntas/{id}")
    public Pergunta update (@PathVariable Long id, @RequestBody Pergunta pergunta) throws ResourceNotFoundException {
        return perguntaRepository.findById(id).map(p -> {
            p.setNumero(pergunta.getNumero());
            p.setDescricao(pergunta.getDescricao());
            p.setValorPontuacao(pergunta.getValorPontuacao());
            return perguntaRepository.save(p);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe pergunta cadastrada com o id" + id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Exclui uma  pargunta existente", notes = "Exclui uma  pargunta existente", response = Pergunta.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Exclusão com sucesso de uma  pargunta")
    })
    //DELETE
    @DeleteMapping("/perguntas/{id}")
    public void delete (@PathVariable Long id) {
        perguntaRepository.deleteById(id);
    }

}
