package org.generation.jogo.Quiz.resposta;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_quiz/v1")
@CrossOrigin("*")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    @ApiOperation(value = "Lista todas as respostas", notes = "Lista todas as respostas", response = Resposta.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consulta de todas as respostas com sucesso!!")
    })
    // READ
    @GetMapping("/respostas")
    public List<Resposta> findAll() {
        return respostaRepository.findAll();
    }

    @ApiOperation(value = "Lista uma resposta especifica", notes = "Lista uma resposta especifica", response = Resposta.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Esta resposta existe")
    })
    // READ BY ID
    @GetMapping("/respostas/{id}")
    public Optional<Resposta> findById(@PathVariable Long id) {
        return respostaRepository.findById(id);
    }

    @ApiOperation(value = "Insere uma nova resposta", notes = "Insere uma nova resposta", response = Resposta.class )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Inclusão com sucesso de uma nova resposta")
    })
    // CREATE
    @PostMapping("/respostas")
    @ResponseStatus(HttpStatus.CREATED)
    public Resposta save (@RequestBody Resposta resposta) {
        return respostaRepository.save(resposta);
    }

    @ApiOperation(value = "Atualiza uma resposta existente", notes = "Atualizar uma resposta existente", response = Resposta.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Atualização com sucesso de uma resposta")
    })
    // UPDATE
    @PutMapping("/respostas/{id}")
    public Resposta update (@PathVariable Long id, @RequestBody Resposta resposta) throws ResourceNotFoundException {
        return respostaRepository.findById(id).map(r -> {
            r.setAlternativa(resposta.getAlternativa());
            r.setDescricao(resposta.getDescricao());
            r.setCerta(resposta.isCerta());
            return respostaRepository.save(r);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe resposta cadastrada com o id" + id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Exclui uma resposta existente", notes = "Exclui uma resposta existente", response = Resposta.class)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Exclusão com sucesso de uma resposta")
    })
    //DELETE
    @DeleteMapping("/respostas/{id}")
    public void delete (@PathVariable Long id) {
        respostaRepository.deleteById(id);
    }

}
