package org.generation.jogo.Quiz.resposta;

import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_quiz/v1")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    // READ
    @GetMapping("/respostas")
    public List<Resposta> findAll() {
        return respostaRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/respostas/{id}")
    public Optional<Resposta> findById(@PathVariable Long id) {
        return respostaRepository.findById(id);
    }

    // CREATE
    @PostMapping("/respostas")
    @ResponseStatus(HttpStatus.CREATED)
    public Resposta save (@RequestBody Resposta resposta) {
        return respostaRepository.save(resposta);
    }

    // UPDATE
    @PutMapping("/respostas/{id}")
    public Resposta update (@PathVariable Long id, @RequestBody Resposta resposta) throws ResourceNotFoundException {
        return respostaRepository.findById(id).map(r -> {
            r.setAlternativa(resposta.getAlternativa());
            r.setDescricao(resposta.getDescricao());
            r.setCerta(resposta.isCerta());
            return respostaRepository.save(r);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe pergunta cadastrada com o id" + id));
    }

    //DELETE
    @DeleteMapping("/respostas/{id}")
    public void delete (@PathVariable Long id) {
        respostaRepository.deleteById(id);
    }

}
