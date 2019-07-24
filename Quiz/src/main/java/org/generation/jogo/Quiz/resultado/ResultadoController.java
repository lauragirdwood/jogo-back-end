package org.generation.jogo.Quiz.resultado;

/*import org.generation.jogo.Quiz.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResultadoController {

    @Autowired
    private ResultadoRepository resultadoRepository;

    // READ
    @GetMapping("/resultados")
    public List<Resultado> findAll() {
        return resultadoRepository.findAll();
    }

    // CREATE

    @PostMapping("/resultados")
    public Resultado save (@RequestBody Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    @PutMapping("/resultados/{id_resultado}")
    public Resultado update (@PathVariable Long id_resultado, @RequestBody Resultado resultado) throws ResourceNotFoundException {
        return resultadoRepository.findById(id_resultado).map(r -> {
            r.setCompleto(resultado.getCompleto());


            return resultadoRepository.save(r);
        }) .orElseThrow(() -> new ResourceNotFoundException("Não existe pergunta cadastrada com o id_resultado" + id_resultado));
    }

    @DeleteMapping("/resultados/{id_resultado}")
    public void delete (@PathVariable Long id_resultado) {
        resultadoRepository.deleteById(id_resultado);
    }
}
 */
