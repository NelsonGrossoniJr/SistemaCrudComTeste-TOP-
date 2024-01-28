package infnet.edu.br.Crud_PreHistorico;

import infnet.edu.br.Crud_PreHistorico.exception.ResourceNotFoundException;
import infnet.edu.br.Crud_PreHistorico.model.Dinossauro;
import infnet.edu.br.Crud_PreHistorico.service.DinossauroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DinossauroServiceTests {

    @Autowired
    DinossauroService dinossauroService;

    @BeforeEach
    public void setup()
    {
        dinossauroService.destroi();
        dinossauroService.setup();
    }
    @Test
    @DisplayName("Deve Inserir um Dinossauro")
    void testaCreate(){
        Dinossauro triceratops = Dinossauro.builder()
                .nome("Triceratops")
                .altura(3.0)
                .caracteristica(new ArrayList<>(Arrays.asList("Herbívoro", "Terrestre")))
                .build();
        Dinossauro velociraptor = Dinossauro.builder()
                .nome("Velociraptor")
                .altura(0.5)
                .caracteristica(new ArrayList<>(Arrays.asList("Carnívoro", "Terrestre")))
                .build();

        dinossauroService.create(triceratops);
        List<Dinossauro> all = dinossauroService.getAll();
        Dinossauro retornado = dinossauroService.getBy(null, triceratops.getId());
        assertEquals(4, all.size());
        assertEquals("Triceratops", retornado.getNome());
        assertEquals(3.0, retornado.getAltura());

        assertEquals(retornado.getCaracteristica(), Arrays.asList("Herbívoro", "Terrestre"));


        dinossauroService.create(velociraptor);
        all = dinossauroService.getAll();
        retornado = dinossauroService.getBy(null, velociraptor.getId());
        assertEquals(5, all.size());
        assertEquals("Velociraptor", retornado.getNome());
        assertEquals(0.5, retornado.getAltura());

        assertEquals(retornado.getCaracteristica(), Arrays.asList("Carnívoro", "Terrestre"));

    }

    @Test
    @DisplayName("Deve retornar um Dinossauro pelo ID ou Name")
    void testaGetBy(){
        Dinossauro velociraptor = Dinossauro.builder()
                .id(dinossauroService.criandoID("Velociraptor"))
                .nome("Velociraptor")
                .altura(0.5)
                .caracteristica(new ArrayList<>(Arrays.asList("Carnívoro", "Terrestre")))
                .build();
        dinossauroService.create(velociraptor);

        Dinossauro dinossauro = dinossauroService.getBy(null,velociraptor.getId());
        assertEquals(dinossauro.getNome(), "Velociraptor");
        assertEquals(dinossauro.getAltura(), 0.5);
        assertThrows(ResourceNotFoundException.class, ()-> dinossauroService.getBy(null,""));

        dinossauro = dinossauroService.getBy(velociraptor.getNome(),null);
        assertEquals(dinossauro.getNome(), "Velociraptor");
        assertEquals(dinossauro.getAltura(), 0.5);
        assertThrows(ResourceNotFoundException.class, ()-> dinossauroService.getBy("",null));
    }


    @Test
    @DisplayName("Deve retornar todos os Dinossauros")
    void testaGetAll(){
        List<Dinossauro> dinossauros =  dinossauroService.getAll();
        assertEquals(3, dinossauros.size());
    }


    @Test
    @DisplayName("Deve atualizar um Dinossauro no Map")
    void testaUpdateByID(){
        Dinossauro triceratops = Dinossauro.builder()
                .id(dinossauroService.criandoID("Triceratops"))
                .nome("Triceratops")
                .altura(3.0)
                .caracteristica(new ArrayList<>(Arrays.asList("Herbívoro", "Terrestre")))
                .build();
        dinossauroService.create(triceratops);

        Dinossauro velociraptor = Dinossauro.builder()
                .id(dinossauroService.criandoID("Velociraptor"))
                .nome("Velociraptor")
                .altura(0.5)
                .caracteristica(new ArrayList<>(Arrays.asList("Carnívoro", "Terrestre")))
                .build();
        dinossauroService.updateByID(triceratops.getId(), velociraptor);
        Dinossauro atualizado = dinossauroService.getBy(null, triceratops.getId());
        assertEquals("Velociraptor", atualizado.getNome());
        assertEquals( velociraptor.getId(), atualizado.getId());
    }


    @Test
    @DisplayName("Deve remover um Dinossauro pelo ID")
    void testaDeleteByID(){
        Dinossauro triceratops = Dinossauro.builder()
                .id(dinossauroService.criandoID("Triceratops"))
                .nome("Triceratops")
                .altura(3.0)
                .caracteristica(new ArrayList<>(Arrays.asList("Herbívoro", "Terrestre")))
                .build();
        dinossauroService.create(triceratops);

        dinossauroService.deleteByID(triceratops.getId());
        List<Dinossauro> clientes = dinossauroService.getAll();
        assertEquals(3, clientes.size());
    }




}
