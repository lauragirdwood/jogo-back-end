package org.generation.jogo.Quiz.jogador;

import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.generation.jogo.Quiz.usuario.Usuario;
import org.generation.jogo.Quiz.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_quiz/v1")
public class JogadorController {

    @Autowired
    private JogadorRepository jogadorRepository;

    // READ
    @GetMapping("/jogadores")
    public List<Jogador> findAll() {
        return jogadorRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/jogadores/{idJogador}")
    public Optional<Jogador> findById(@PathVariable Long idJogador) {
        return jogadorRepository.findById(idJogador);
    }

    // CREATE
    @PostMapping("/jogadores")
    @ResponseStatus(HttpStatus.CREATED)
    public Jogador save (@RequestBody Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    // UPDATE
    @PutMapping("/jogadores/{idJogador}")
    public Jogador update (@PathVariable Long idJogador, @RequestBody Jogador jogador) throws ResourceNotFoundException {
        return jogadorRepository.findById(idJogador).map(j -> {
            j.setNome(jogador.getNome());
            j.setNivel(jogador.getNivel());
            j.setPontuacao(jogador.getPontuacao());
            return jogadorRepository.save(j);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe jogador cadastrado com o id" + idJogador));
    }

    //DELETE
    @DeleteMapping("/jogadores/{idJogador}")
    public void delete (@PathVariable Long idJogador) {
        jogadorRepository.deleteById(idJogador);
    }
}
