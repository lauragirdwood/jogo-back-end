package org.generation.jogo.Quiz.Jogador;

import org.generation.jogo.Quiz.QuizApplication;
import org.generation.jogo.Quiz.jogador.Jogador;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

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

    /*@Test
    public void update() {
        int id = 6;

        Jogador jogador = testRestTemplate.getForObject(getRootUrl("/jogadors/" + id), Jogador.class);

        Integer novoNumero = JogadorMock.getJogador().getNumero();
        String novoDescricao = JogadorMock.getJogador().getDescricao();
        Integer novoValor_pontuacao = JogadorMock.getJogador().getValor_pontuacao();

        jogador.setNumero(novoNumero);
        jogador.setDescricao(novoDescricao);
        jogador.setValor_pontuacao(novoValor_pontuacao);

        testRestTemplate.put(getRootUrl("/jogadors/" + id), jogador);

        Jogador newJogador = testRestTemplate.getForObject(getRootUrl("/jogadors/" + id), Jogador.class);
        assertEquals(novoNumero, newJogador.getNumero());
        assertEquals(novoDescricao, newJogador.getDescricao());
        assertEquals(novoValor_pontuacao, newJogador.getValor_pontuacao());
    }

    @Test
    public void delete() {
        int id = 5;
        Jogador jogador = testRestTemplate.getForObject(getRootUrl("/jogadors/" + id), Jogador.class);
        assertNotNull(jogador);
        testRestTemplate.delete(getRootUrl("/jogadors/" + id));
        try {
            jogador = testRestTemplate.getForObject(getRootUrl("/jogador/" + id), Jogador.class);
        } catch(final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);

        }

    }*/

}
