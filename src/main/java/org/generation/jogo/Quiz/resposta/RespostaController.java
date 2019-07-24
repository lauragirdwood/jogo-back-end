package org.generation.jogo.Quiz.resposta;

import org.generation.jogo.Quiz.pergunta.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

}
