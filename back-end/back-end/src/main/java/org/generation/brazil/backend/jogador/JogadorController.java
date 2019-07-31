package org.generation.brazil.backend.jogador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.generation.brazil.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class JogadorController {

  private static final String NOT_FOUND = "Não foi encontrado um jogador com o id: ";

  @Autowired
  private JogadorRepository jogadorRepository;

  // 'C' DO CRUD - CREATE
  @PostMapping("/jogadores")
  public Jogador createJogador(@Valid @RequestBody Jogador jogador) {
    return jogadorRepository.save(jogador);
  }

  // 'R' DO CRUD - READ/RETRIEVE 'LISTA TODOS'
  @GetMapping("/jogadores")
  public List<Jogador> getAllJogadores() {
    return jogadorRepository.findAll();
  }

  // 'R' DO CRUD - READ POR ID
  @GetMapping("/jogadores/{id}")
  public ResponseEntity<Jogador> getJogadorById(@PathVariable(value = "id") Long jogadorId) {
    Jogador jogador = jogadorRepository.findById(jogadorId)
        .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + jogadorId));
    return ResponseEntity.ok().body(jogador);
  }

  // 'U' DO CRUD - UPDATE 'ATUALIZA TODOS OS CAMPOS DO ID'
  @PutMapping("/jogadores/{id}")
  public ResponseEntity<Jogador> updateJogador(@PathVariable(value = "id") Long jogadorId, @Valid @RequestBody Jogador jogadorDetails) {

    Jogador jogador = jogadorRepository.findById(jogadorId).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + jogadorId));

    jogador.jogadorDetails.getNome());
    jogador.setSobrenome(jogadorDetails.getSobrenome());
    jogador.setCidade(jogadorDetails.getCidade());

    final Jogador updatedJogador = jogadorRepository.save(jogador);

    return ResponseEntity.ok(updatedJogador);
  }

  // 'D' DO CRUD - DELETE
  @DeleteMapping("/jogadores/{id}")
  public Map<String, Boolean> deleteJogador(@PathVariable(value = "id") Long jogadorId) {

    Jogador jogador = jogadorRepository.findById(jogadorId).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + jogadorId));

    jogadorRepository.delete(jogador);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);

    return response;
  }

}
