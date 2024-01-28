package infnet.edu.br.Crud_PreHistorico.controller;

import infnet.edu.br.Crud_PreHistorico.exception.ResourceNotFoundException;
import infnet.edu.br.Crud_PreHistorico.model.Dinossauro;
import infnet.edu.br.Crud_PreHistorico.model.ResponsePayload;
import infnet.edu.br.Crud_PreHistorico.service.DinossauroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dinossauro")
public class DinossauroController {

    @Autowired
    DinossauroService dinossauroService;

    @PostMapping
    public ResponseEntity<ResponsePayload> create(@RequestBody Dinossauro dinossauro)
    {
        dinossauroService.create(dinossauro);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponsePayload("Dinossauro cadastrado com sucesso!"));
    }

    @GetMapping("/getBy")
    public ResponseEntity getBy(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String id)
    {
        try{
            Dinossauro dinossauro = dinossauroService.getBy(name, id);
            return ResponseEntity.ok().body(dinossauro);
        }
        catch (ResourceNotFoundException ex)
        {
            ResponsePayload responsePayload = new ResponsePayload(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responsePayload);
        }
    }

    @GetMapping
    public List<Dinossauro> getAll()
    {
        return dinossauroService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePayload> update(@PathVariable String id, @RequestBody Dinossauro atualizado)
    {
        try{
        dinossauroService.updateByID(id,atualizado);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponsePayload("Dinossauro alterado com sucesso!"));

        } catch (ResourceNotFoundException ex){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponsePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePayload> delete(@PathVariable String id)
    {
        try {
        dinossauroService.deleteByID(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body((new ResponsePayload("Dinossauro deletado com sucesso!")));
        } catch (ResourceNotFoundException ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponsePayload(ex.getMessage()));
        }
    }
}
