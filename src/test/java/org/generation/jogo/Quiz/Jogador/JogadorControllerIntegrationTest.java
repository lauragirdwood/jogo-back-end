package org.generation.jogo.Quiz.Jogador;

import org.generation.jogo.Quiz.QuizApplication;
import org.generation.jogo.Quiz.jogador.Jogador;
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
public class JogadorControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api_quiz/v1" + path;
    }

    @Test
    public void save() {
        ResponseEntity<Jogador> postResponse = testRestTemplate.postForEntity(getRootUrl("/jogadores"), JogadorMock.getJogador(), Jogador.class);
        assertNotNull(postResponse);
        assertEquals(201, postResponse.getStatusCodeValue());
    }

   @Test
    public void read() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/jogadores"), HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void readById() {
        Jogador jogador = testRestTemplate.getForObject(getRootUrl("/jogadores/1"), Jogador.class);
        assertNotNull(jogador);
    }

    @Test
    public void update() {
        int id = 1;

        Jogador jogador = testRestTemplate.getForObject(getRootUrl("/jogadores/" + id), Jogador.class);

        String novoNome = JogadorMock.getJogador().getNome();
        Integer novoNivel = JogadorMock.getJogador().getNivel();
        Integer novoPontuacao = JogadorMock.getJogador().getPontuacao();

        jogador.setNome(novoNome);
        jogador.setNivel(novoNivel);
        jogador.setPontuacao(novoPontuacao);

        testRestTemplate.put(getRootUrl("/jogadores/" + id), jogador);

        Jogador newJogador = testRestTemplate.getForObject(getRootUrl("/jogadores/" + id), Jogador.class);
        assertEquals(novoNome, newJogador.getNome());
        assertEquals(novoNivel, newJogador.getNivel());
        assertEquals(novoPontuacao, newJogador.getPontuacao());
    }

    @Test
    public void delete() {
        int id = 1;
        Jogador jogador = testRestTemplate.getForObject(getRootUrl("/jogadores/" + id), Jogador.class);
        assertNotNull(jogador);
        testRestTemplate.delete(getRootUrl("/jogadores/" + id));
        try {
            jogador = testRestTemplate.getForObject(getRootUrl("/jogadores/" + id), Jogador.class);
        } catch(final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);

        }

    }

}
