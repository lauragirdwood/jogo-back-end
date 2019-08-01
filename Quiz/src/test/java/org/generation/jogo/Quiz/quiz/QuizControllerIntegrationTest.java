package org.generation.jogo.Quiz.quiz;

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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuizApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuizControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api_quiz/v1" + path;
    }

    @Test
    public void save() {
        ResponseEntity<Quiz> postResponse = testRestTemplate.postForEntity(getRootUrl("/quiz"), QuizMock.getQuiz(), Quiz.class);
        assertNotNull(postResponse);
        assertEquals(201, postResponse.getStatusCodeValue());
    }

    @Test
    public void read() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/quiz"), HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void readById() {
        Quiz quiz = testRestTemplate.getForObject(getRootUrl("/quiz/3"), Quiz.class);
        assertNotNull(quiz);
    }

    @Test
    public void update() {
        int id = 3;

        Quiz quiz = testRestTemplate.getForObject(getRootUrl("/quiz/" + id), Quiz.class);

        String novoNome = QuizMock.getQuiz().getNome();
        String novoTema = QuizMock.getQuiz().getTema();

        quiz.setNome(novoNome);
        quiz.setTema(novoTema);

        testRestTemplate.put(getRootUrl("/quiz/" + id), quiz);

        Quiz newQuiz = testRestTemplate.getForObject(getRootUrl("/quiz/" + id), Quiz.class);
        assertEquals(novoNome, newQuiz.getNome());
        assertEquals(novoTema, newQuiz.getTema());
    }

    @Test
    public void delete() {
        int id = 2;
        Quiz quiz = testRestTemplate.getForObject(getRootUrl("/quiz/" + id), Quiz.class);
        assertNotNull(quiz);
        testRestTemplate.delete(getRootUrl("/quiz/" + id));
        try {
            quiz = testRestTemplate.getForObject(getRootUrl("/quiz/" + id), Quiz.class);
        } catch(final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);

        }
    }
}
