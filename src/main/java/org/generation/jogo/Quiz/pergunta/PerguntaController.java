package org.generation.jogo.Quiz.pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PerguntaController {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @GetMapping("/perguntas")
    public List<Pergunta> findAll() {
        return perguntaRepository.findAll();
    }

}
