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
    @GetMapping("/quiz/{id}")
    public Optional<Quiz> findById(@PathVariable Long id) {
        return quizRepository.findById(id);
    }

    // CREATE
    @PostMapping("/quiz")
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz save (@RequestBody Quiz quiz) {
        return quizRepository.save(quiz);
    }

    // UPDATE
    @PutMapping("/quiz/{id}")
    public Quiz update (@PathVariable Long id, @RequestBody Quiz quiz) throws ResourceNotFoundException {
        return quizRepository.findById(id).map(q -> {
            q.setNome(quiz.getNome());
            q.setTema(quiz.getTema());
            return quizRepository.save(q);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe quiz cadastrado com o id: " + id));
    }

    //DELETE
    @DeleteMapping("/quiz/{id}")
    public void delete (@PathVariable Long id) {
        quizRepository.deleteById(id);
    }

}
