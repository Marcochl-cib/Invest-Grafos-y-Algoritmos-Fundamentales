import java.util.List;

public class GrafoMatriz {
    private final List<Character> vertices;
    private final Integer[][] pesos;

    public GrafoMatriz(GrafoLista grafo) {
        vertices = grafo.vertices();
        pesos = new Integer[vertices.size()][vertices.size()];
        for (int fila = 0; fila < vertices.size(); fila++) {
            for (GrafoLista.Arista arista : grafo.vecinosDe(vertices.get(fila))) {
                int columna = vertices.indexOf(arista.destino());
                pesos[fila][columna] = arista.peso();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder tabla = new StringBuilder(" ");
        for (char vertice : vertices) {
            tabla.append(String.format("%4s", vertice));
        }

        for (int fila = 0; fila < vertices.size(); fila++) {
            tabla.append(System.lineSeparator()).append(vertices.get(fila));
            for (Integer peso : pesos[fila]) {
                tabla.append(String.format("%4s", peso == null ? "-" : peso));
            }
        }
        return tabla.toString();
    }
}
