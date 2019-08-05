package org.generation.jogo.Quiz.partida;

import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_quiz/v1")
public class PartidaController {

    @Autowired
    private PartidaRepository partidaRepository;

    // READ
    @GetMapping("/partidas")
    public List<Partida> findAll() {
        return partidaRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/partidas/{id}")
    public Optional<Partida> findById(@PathVariable Long id) {
        return partidaRepository.findById(id);
    }

    // CREATE
    @PostMapping("/partidas")
    @ResponseStatus(HttpStatus.CREATED)
    public Partida save (@RequestBody Partida partida) {
        return partidaRepository.save(partida);
    }

    // UPDATE
    @PutMapping("/partidas/{id}")
    public Partida update (@PathVariable Long id, @RequestBody Partida partida) throws ResourceNotFoundException {
        return partidaRepository.findById(id).map(p -> {
            p.setCompleta(partida.getCompleta());
            return partidaRepository.save(p);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe partida cadastrado com o id" + id));
    }

    //DELETE
    @DeleteMapping("/partidas/{id}")
    public void delete (@PathVariable Long id) {
        partidaRepository.deleteById(id);
    }
}
