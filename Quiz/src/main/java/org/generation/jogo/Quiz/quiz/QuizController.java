package org.generation.jogo.Quiz.quiz;

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
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @ApiOperation(value = "Lista todos os quiz", notes = "Lista todos os quiz", response = Quiz.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consulta de todos os quiz com sucesso!!")
    })
    // READ
    @GetMapping("/quiz")
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @ApiOperation(value = "Lista um quiz especifico", notes = "Lista um quiz especifico", response = Quiz.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Este quiz existe")
    })
    // READ BY ID
    @GetMapping("/quiz/{id}")
    public Optional<Quiz> findById(@PathVariable Long id) {
        return quizRepository.findById(id);
    }

    @ApiOperation(value = "Insere um novo quiz", notes = "Insere um novo quiz", response = Quiz.class )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Inclusão com sucesso de um novo quiz")
    })
    // CREATE
    @PostMapping("/quiz")
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz save (@RequestBody Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @ApiOperation(value = "Atualiza um quiz existente", notes = "Atualizar um quiz existente", response = Quiz.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Atualização com sucesso de um quiz")
    })
    // UPDATE
    @PutMapping("/quiz/{id}")
    public Quiz update (@PathVariable Long id, @RequestBody Quiz quiz) throws ResourceNotFoundException {
        return quizRepository.findById(id).map(q -> {
            q.setNome(quiz.getNome());
            q.setTema(quiz.getTema());
            return quizRepository.save(q);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe quiz cadastrado com o id: " + id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Exclui um quiz existente", notes = "Exclui um quiz existente", response = Quiz.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Exclusão com sucesso de um quiz")
    })
    //DELETE
    @DeleteMapping("/quiz/{id}")
    public void delete (@PathVariable Long id) {
        quizRepository.deleteById(id);
    }

}
