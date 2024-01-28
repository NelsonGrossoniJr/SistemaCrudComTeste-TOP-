package infnet.edu.br.Crud_PreHistorico;

import infnet.edu.br.Crud_PreHistorico.service.DinossauroExternoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class DinossauroExternoServiceTests
{
    @Autowired
    DinossauroExternoService dinossauroExternoService;

    @Test
    @DisplayName("Não deve retornar nulo na chamada pra API Externa")
    public void testaGetRandomDinosaur()
    {
        assertNotEquals(null, dinossauroExternoService.getRandomDinosaur());
    }
}
