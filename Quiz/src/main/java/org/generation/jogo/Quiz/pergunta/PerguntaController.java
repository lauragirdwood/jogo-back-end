package org.generation.jogo.Quiz.pergunta;

import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_quiz/v1")
public class PerguntaController {

    @Autowired
    private PerguntaRepository perguntaRepository;

    // READ
    @GetMapping("/perguntas")
    public List<Pergunta> findAll() {
        return perguntaRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/perguntas/{id_pergunta}")
    public Optional<Pergunta> findById(@PathVariable Long id_pergunta) {
        return perguntaRepository.findById(id_pergunta);
    }

    // CREATE
    @PostMapping("/perguntas")
    @ResponseStatus(HttpStatus.CREATED)
    public Pergunta save (@RequestBody Pergunta pergunta) {
        return perguntaRepository.save(pergunta);
    }

    // UPDATE
    @PutMapping("/perguntas/{id_pergunta}")
    public Pergunta update (@PathVariable Long id_pergunta, @RequestBody Pergunta pergunta) throws ResourceNotFoundException {
        return perguntaRepository.findById(id_pergunta).map(p -> {
            p.setNumero(pergunta.getNumero());
            p.setDescricao(pergunta.getDescricao());
            p.setValor_pontuacao(pergunta.getValor_pontuacao());
            return perguntaRepository.save(p);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe pergunta cadastrada com o id_pergunta" + id_pergunta));
    }

    //DELETE
    @DeleteMapping("/perguntas/{id_pergunta}")
    public void delete (@PathVariable Long id_pergunta) {
        perguntaRepository.deleteById(id_pergunta);
    }

}
