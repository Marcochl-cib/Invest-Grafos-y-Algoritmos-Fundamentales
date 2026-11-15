import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GrafoLista {
    public record Arista(char destino, int peso) {}

    private final Map<Character, List<Arista>> adyacencias = new LinkedHashMap<>();

    public void agregarVertice(char vertice) {
        adyacencias.putIfAbsent(vertice, new ArrayList<>());
    }

    public void agregarArista(char origen, char destino, int peso) {
        agregarVertice(origen);
        agregarVertice(destino);
        adyacencias.get(origen).add(new Arista(destino, peso));
        adyacencias.get(destino).add(new Arista(origen, peso));
    }

    public List<Character> vertices() {
        return List.copyOf(adyacencias.keySet());
    }

    public List<Arista> vecinosDe(char vertice) {
        List<Arista> vecinos = adyacencias.get(vertice);
        if (vecinos == null) {
            throw new IllegalArgumentException("Vértice inexistente: " + vertice);
        }
        return List.copyOf(vecinos);
    }
}
