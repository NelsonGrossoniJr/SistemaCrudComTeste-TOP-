package infnet.edu.br.Crud_PreHistorico.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import infnet.edu.br.Crud_PreHistorico.exception.ResourceNotFoundException;
import infnet.edu.br.Crud_PreHistorico.model.DinossauroExterno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DinossauroExternoService {
    private static final Logger logger = LoggerFactory.getLogger(DinossauroExternoService.class);
    public DinossauroExterno getRandomDinosaur(){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .version(HttpClient.Version.HTTP_2)
                    .uri(new URI("https://dinosaur-facts-api.shultzlab.com/dinosaurs/random"))
                    .build();


            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            logger.info("StatusCode de GET:DinossauroExterno: " + response.statusCode());
            if(response.statusCode() == 404){
                throw new ResourceNotFoundException("Dinossauro não encontrado");
            }
            ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

            return mapper.readValue(response.body(), DinossauroExterno.class);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
