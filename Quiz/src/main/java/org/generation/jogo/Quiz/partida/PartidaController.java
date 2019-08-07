package org.generation.jogo.Quiz.partida;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_quiz/v1")
@CrossOrigin("*")
public class PartidaController {

    @Autowired
    private PartidaRepository partidaRepository;

    @ApiOperation(value = "Lista todas as partidas", notes = "Lista todas as partidas", response = Partida.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consulta de todas as partidas com sucesso!!")
    })
    // READ
    @GetMapping("/partidas")
    public List<Partida> findAll() {
        return partidaRepository.findAll();
    }

    @ApiOperation(value = "Lista uma partida especifica", notes = "Lista uma partida especifica", response = Partida.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Esta partida existe")
    })
    // READ BY ID
    @GetMapping("/partidas/{id}")
    public Optional<Partida> findById(@PathVariable Long id) {
        return partidaRepository.findById(id);
    }

    @ApiOperation(value = "Insere uma novo partida", notes = "Insere uma novo partida", response = Partida.class )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Inclusão com sucesso de uma novo partida")
    })
    // CREATE
    @PostMapping("/partidas")
    @ResponseStatus(HttpStatus.CREATED)
    public Partida save (@RequestBody Partida partida) {
        return partidaRepository.save(partida);
    }

    @ApiOperation(value = "Atualiza uma partida existente", notes = "Atualizar uma partida existente", response = Partida.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Atualização com sucesso de uma partida")
    })
    // UPDATE
    @PutMapping("/partidas/{id}")
    public Partida update (@PathVariable Long id, @RequestBody Partida partida) throws ResourceNotFoundException {
        return partidaRepository.findById(id).map(p -> {
            p.setCompleta(partida.getCompleta());
            return partidaRepository.save(p);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe partida cadastrada com o id" + id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Exclui uma partida existente", notes = "Exclui uma partida existente", response = Partida.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Exclusão com sucesso de uma partida")
    })
    //DELETE
    @DeleteMapping("/partidas/{id}")
    public void delete (@PathVariable Long id) {
        partidaRepository.deleteById(id);
    }
}
