package org.generation.jogo.Quiz.pergunta;


import org.generation.jogo.Quiz.QuizApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuizApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PerguntaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api_quiz/v1" + path;
    }

    @Test
    public void save() {
        ResponseEntity<Pergunta> postResponse = testRestTemplate.postForEntity(getRootUrl("/perguntas"), PerguntaMock.getPergunta(), Pergunta.class);
        assertNotNull(postResponse);
        assertEquals(201, postResponse.getStatusCodeValue());
    }

    @Test
    public void read() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/perguntas"), HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void readById() {
        Pergunta pergunta = testRestTemplate.getForObject(getRootUrl("/perguntas/1"), Pergunta.class);
        assertNotNull(pergunta);
    }

    @Test
    public void update() {
        int id = 1;

        Pergunta pergunta = testRestTemplate.getForObject(getRootUrl("/perguntas/" + id), Pergunta.class);

        Integer novoNumero = PerguntaMock.getPergunta().getNumero();
        String novoDescricao = PerguntaMock.getPergunta().getDescricao();
        Integer novoValorPontuacao = PerguntaMock.getPergunta().getValorPontuacao();

        pergunta.setNumero(novoNumero);
        pergunta.setDescricao(novoDescricao);
        pergunta.setValorPontuacao(novoValorPontuacao);

        testRestTemplate.put(getRootUrl("/perguntas/" + id), pergunta);

        Pergunta newPergunta = testRestTemplate.getForObject(getRootUrl("/perguntas/" + id), Pergunta.class);
        assertEquals(novoNumero, newPergunta.getNumero());
        assertEquals(novoDescricao, newPergunta.getDescricao());
        assertEquals(novoValorPontuacao, newPergunta.getValorPontuacao());
    }

    @Test
    public void delete() {
        int id = 2;
        Pergunta pergunta = testRestTemplate.getForObject(getRootUrl("/perguntas/" + id), Pergunta.class);
        assertNotNull(pergunta);
        testRestTemplate.delete(getRootUrl("/perguntas/" + id));
        try {
            pergunta = testRestTemplate.getForObject(getRootUrl("/pergunta/" + id), Pergunta.class);
        } catch(final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);

        }

    }
}
