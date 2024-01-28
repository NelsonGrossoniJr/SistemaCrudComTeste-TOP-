package infnet.edu.br.Crud_PreHistorico.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class DinossauroExterno {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
}