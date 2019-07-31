package org.generation.jogo.Quiz.usuario;

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
public class UsuarioControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api_quiz/v1" + path;
    }

    @Test
    public void save() {
        ResponseEntity<Usuario> postResponse = testRestTemplate.postForEntity(getRootUrl("/usuarios"), UsuarioMock.getUsuario(), Usuario.class);
        assertNotNull(postResponse);
        assertEquals(201, postResponse.getStatusCodeValue());
    }

    @Test
    public void read() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/usuarios"), HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void readById() {
        Usuario resposta = testRestTemplate.getForObject(getRootUrl("/usuarios/3"), Usuario.class);
        assertNotNull(resposta);
    }

    @Test
    public void update() {
        int id = 3;

        Usuario usuario = testRestTemplate.getForObject(getRootUrl("/usuarios/" + id), Usuario.class);

        String novoUsername = UsuarioMock.getUsuario().getUsername();
        String novoSenha = UsuarioMock.getUsuario().getSenha();

        usuario.setUsername(novoUsername);
        usuario.setSenha(novoSenha);


        testRestTemplate.put(getRootUrl("/usuarios/" + id), usuario);

        Usuario newUsuario = testRestTemplate.getForObject(getRootUrl("/usuarios/" + id), Usuario.class);
        assertEquals(novoUsername, newUsuario.getUsername());
        assertEquals(novoSenha, newUsuario.getSenha());
    }

    @Test
    public void delete() {
        int id = 4;
        Usuario usuario = testRestTemplate.getForObject(getRootUrl("/usuarios/" + id), Usuario.class);
        assertNotNull(usuario);
        testRestTemplate.delete(getRootUrl("/usuarios/" + id));
        try {
            usuario = testRestTemplate.getForObject(getRootUrl("/usuarios/" + id), Usuario.class);
        } catch(final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);

        }
    }
}
