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
    @GetMapping("/respostas/{id_resposta}")
    public Optional<Resposta> findById(@PathVariable Long id_resposta) {
        return respostaRepository.findById(id_resposta);
    }

    // CREATE
    @PostMapping("/respostas")
    @ResponseStatus(HttpStatus.CREATED)
    public Resposta save (@RequestBody Resposta resposta) {
        return respostaRepository.save(resposta);
    }

    // UPDATE
    @PutMapping("/respostas/{id_resposta}")
    public Resposta update (@PathVariable Long id_resposta, @RequestBody Resposta resposta) throws ResourceNotFoundException {
        return respostaRepository.findById(id_resposta).map(r -> {
            r.setAlternativa(resposta.getAlternativa());
            r.setDescricao(resposta.getDescricao());
            r.setCerta(resposta.isCerta());
            return respostaRepository.save(r);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe pergunta cadastrada com o id_resposta" + id_resposta));
    }

    //DELETE
    @DeleteMapping("/respostas/{id_resposta}")
    public void delete (@PathVariable Long id_resposta) {
        respostaRepository.deleteById(id_resposta);
    }

}
