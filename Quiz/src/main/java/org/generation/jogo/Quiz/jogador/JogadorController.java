package org.generation.jogo.Quiz.jogador;

import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class JogadorController {

    @Autowired
    private JogadorRepository jogadorRepository;

    // READ
    @GetMapping("/jogadores")
    public List<Jogador> findAll() {
        return jogadorRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/jogadores/{id_jogador}")
    public Optional<Jogador> findById(@PathVariable Long id_jogador) {
        return jogadorRepository.findById(id_jogador);
    }

    // CREATE
    @PostMapping("/jogadores")
    @ResponseStatus(HttpStatus.CREATED)
    public Jogador save (@RequestBody Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    // UPDATE
    @PutMapping("/jogador/{id_jogador}")
    public Jogador update (@PathVariable Long id_jogador, @RequestBody Jogador jogador) throws ResourceNotFoundException {
        return jogadorRepository.findById(id_jogador).map(j -> {
            j.setNome(jogador.getNome());
            j.setNivel(jogador.getNivel());
            j.setPontuacao(jogador.getPontuacao());
            return jogadorRepository.save(j);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe jogador cadastrado com o id_jogador" + id_jogador));
    }

    //DELETE
    @DeleteMapping("/jogador/{id_jogador}")
    public void delete (@PathVariable Long id_jogador) {
        jogadorRepository.deleteById(id_jogador);
    }

}
