package org.generation.jogo.Quiz.partida;

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
public class PartidaCrontrollerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api_quiz/v1" + path;
    }

    @Test
    public void save() {
        ResponseEntity<Partida> postResponse = testRestTemplate.postForEntity(getRootUrl("/partidas"), PartidaMock.getPartida(), Partida.class);
        assertNotNull(postResponse);
        assertEquals(201, postResponse.getStatusCodeValue());
    }

    @Test
    public void read() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/partidas"), HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void readById() {
        Partida partidas = testRestTemplate.getForObject(getRootUrl("/partidas/2"), Partida.class);
        assertNotNull(partidas);
    }

    @Test
    public void update() {
        int id = 1;

        Partida partidas = testRestTemplate.getForObject(getRootUrl("/partidas/" + id), Partida.class);

        Boolean novoCompleta = PartidaMock.getPartida().getCompleta();

        partidas.setCompleta(novoCompleta);

        testRestTemplate.put(getRootUrl("/partidas/" + id), partidas);

        Partida newPartida = testRestTemplate.getForObject(getRootUrl("/partidas/" + id), Partida.class);
        assertEquals(novoCompleta, newPartida.getCompleta());

    }

    @Test
    public void delete() {
        int id = 2;
        Partida partidas = testRestTemplate.getForObject(getRootUrl("/partidas/" + id), Partida.class);
        assertNotNull(partidas);
        testRestTemplate.delete(getRootUrl("/partidas/" + id));
        try {
            partidas = testRestTemplate.getForObject(getRootUrl("/partidas/" + id), Partida.class);
        } catch(final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);

        }
    }
}
