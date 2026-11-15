import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Recorridos {
    public static List<Character> bfs(GrafoLista grafo, char inicio) {
        Queue<Character> pendientes = new ArrayDeque<>();
        Set<Character> visitados = new HashSet<>();
        List<Character> orden = new ArrayList<>();

        visitados.add(inicio);
        pendientes.add(inicio);

        while (!pendientes.isEmpty()) {
            char actual = pendientes.remove();
            orden.add(actual);

            for (GrafoLista.Arista arista : grafo.vecinosDe(actual)) {
                char vecino = arista.destino();
                if (visitados.add(vecino)) {
                    pendientes.add(vecino);
                }
            }
        }

        return orden;
    }
}
