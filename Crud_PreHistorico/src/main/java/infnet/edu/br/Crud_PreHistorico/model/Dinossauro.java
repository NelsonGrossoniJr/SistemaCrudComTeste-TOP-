package infnet.edu.br.Crud_PreHistorico.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class Dinossauro {
    private String id;
    private String nome;
    private Double altura;
    private ArrayList<String> caracteristica;
}