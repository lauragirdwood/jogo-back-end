package org.generation.jogo.Quiz.quiz;

import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_quiz/v1")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    // READ
    @GetMapping("/quiz")
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/quiz/{id_quiz}")
    public Optional<Quiz> findById(@PathVariable Long id_quiz) {
        return quizRepository.findById(id_quiz);
    }

    // CREATE
    @PostMapping("/quiz")
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz save (@RequestBody Quiz quiz) {
        return quizRepository.save(quiz);
    }

    // UPDATE
    @PutMapping("/quiz/{id_quiz}")
    public Quiz update (@PathVariable Long id_quiz, @RequestBody Quiz quiz) throws ResourceNotFoundException {
        return quizRepository.findById(id_quiz).map(p -> {
            p.setNome(quiz.getNome());
            p.setTema(quiz.getTema());
            return quizRepository.save(p);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe quiz cadastrado com o id: " + id_quiz));
    }

    //DELETE
    @DeleteMapping("/quiz/{id_quiz}")
    public void delete (@PathVariable Long id_quiz) {
        quizRepository.deleteById(id_quiz);
    }

}
