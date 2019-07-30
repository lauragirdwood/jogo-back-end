/*package org.generation.jogo.Quiz.resposta;


import org.generation.jogo.Quiz.QuizApplication;
import org.generation.jogo.Quiz.pergunta.Pergunta;
import org.generation.jogo.Quiz.pergunta.PerguntaMock;
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
public class RespostaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api_quiz/v1" + path;
    }

    @Test
    public void save() {
        ResponseEntity<Resposta> postResponse = testRestTemplate.postForEntity(getRootUrl("/respostas"), RespostaMock.getResposta(), Resposta.class);
        assertNotNull(postResponse);
        assertEquals(201, postResponse.getStatusCodeValue());
    }

    @Test
    public void read() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/respostas"), HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void readById() {
        Resposta resposta = testRestTemplate.getForObject(getRootUrl("/respostas/4"), Resposta.class);
        assertNotNull(resposta);
    }

    @Test
    public void update() {
        int id = 4;

        Resposta resposta = testRestTemplate.getForObject(getRootUrl("/respostas/" + id), Resposta.class);

        String novoAlternativa = RespostaMock.getResposta().getAlternativa();
        String novoDescricao = RespostaMock.getResposta().getDescricao();
        Boolean novoCerta = RespostaMock.getResposta().isCerta();

        resposta.setAlternativa(novoAlternativa);
        resposta.setDescricao(novoDescricao);
        resposta.setCerta(novoCerta);

        testRestTemplate.put(getRootUrl("/respostas/" + id), resposta);

        Resposta newResposta = testRestTemplate.getForObject(getRootUrl("/respostas/" + id), Resposta.class);
        assertEquals(novoAlternativa, newResposta.getAlternativa());
        assertEquals(novoDescricao, newResposta.getDescricao());
        assertEquals(novoCerta, newResposta.isCerta());
    }

    @Test
    public void delete() {
        int id = 3;
        Resposta resposta = testRestTemplate.getForObject(getRootUrl("/respostas/" + id), Resposta.class);
        assertNotNull(resposta);
        testRestTemplate.delete(getRootUrl("/respostas/" + id));
        try {
            resposta = testRestTemplate.getForObject(getRootUrl("/respostas/" + id), Resposta.class);
        } catch(final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);

        }
    }
}*/
