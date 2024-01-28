package infnet.edu.br.Crud_PreHistorico.service;

import infnet.edu.br.Crud_PreHistorico.exception.ResourceNotFoundException;
import infnet.edu.br.Crud_PreHistorico.model.Dinossauro;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;



@Service
public class DinossauroService {

    private Map<String, Dinossauro> dinossauros = initDinossauros();

    public String criandoID(String nome) {
        Date data = new Date();
        LocalDateTime localDateTime = LocalDateTime.now();
        int dia = data.getDate();
        int mes = data.getMonth();
        int minuto = localDateTime.getMinute();
        int hora = localDateTime.getHour();
        int segundo = localDateTime.getSecond();


        return (nome.substring(0, 1) + dia + nome.charAt(1)+ mes + nome.charAt(2) + hora + nome.charAt(3) + minuto).toLowerCase() + "S" + segundo;
    }

    public Map<String, Dinossauro> initDinossauros() {

        Dinossauro tyranosaurusRex = new Dinossauro(criandoID("TyranosaurusRex" ), "TyranosaurusRex", 4.0, new ArrayList<>() {{
            add("Carnívoro");
            add("Terrestre");
        }});

        Dinossauro braquiossauro = new Dinossauro(criandoID("Braquiossauro" ), "Braquiossauro", 12.0, new ArrayList<>() {{
            add("Herbívoro");
            add("Terrestre");
        }});

        Dinossauro pterodactilo = new Dinossauro(criandoID("Pterodáctilo" ), "Pterodáctilo", 1.0, new ArrayList<>() {{
            add("Carnívoro");
            add("Aereo");
        }});

        Map<String, Dinossauro> dinossauros = new HashMap<>();
        dinossauros.put(tyranosaurusRex.getId(), tyranosaurusRex);
        dinossauros.put(braquiossauro.getId(), braquiossauro);
        dinossauros.put(pterodactilo.getId(), pterodactilo);
        return dinossauros;
    }

    public void setup()
    {
        this.dinossauros = initDinossauros();
    }
    public void destroi()
    {
        dinossauros.clear();
    }

    public void create(Dinossauro dinossauro)
    {
        dinossauro.setId(criandoID(dinossauro.getNome()));
        dinossauros.put(dinossauro.getId(), dinossauro);
    }

    public Dinossauro getBy(String name, String id){
        if (name != null) {
            for (var key : dinossauros.keySet()) {
                if (dinossauros.get(key).getNome().equals(name)) return dinossauros.get(key);
            }
        } else if (id != null) {
            for (var key : dinossauros.keySet()) {
                if (dinossauros.get(key).getId().equals(id)) return dinossauros.get(key);
            }
        }

        throw new ResourceNotFoundException("NAO ACHOU");
    }

    public List<Dinossauro> getAll()
    {
        return dinossauros.values().stream().toList();
    }

    public void updateByID(String id, Dinossauro dinossauro)
    {
        if(clienteNaoExiste(id)) throw new ResourceNotFoundException("Dinossauro inexistente!");
        dinossauro.setId(id);
        dinossauros.replace(id, dinossauro);
    }

    public void deleteByID(String nome)
    {
        if(clienteNaoExiste(nome)) throw new ResourceNotFoundException("Dinossauro inexistente!");
        dinossauros.remove(nome);
    }

    private boolean clienteNaoExiste(String nome)
    {
        return !dinossauros.containsKey(nome);
    }



}