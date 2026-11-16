import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ArbolExpansionMinima {
    public record Arista(char origen, char destino, int peso) {
        @Override
        public String toString() {
            return origen + "-" + destino + " (" + peso + ")";
        }
    }

    private static final Comparator<Arista> POR_PESO = Comparator.comparingInt(Arista::peso);

    public static List<Arista> prim(GrafoLista grafo, char inicio) {
        Set<Character> visitados = new HashSet<>();
        PriorityQueue<Arista> candidatas = new PriorityQueue<>(POR_PESO);
        List<Arista> arbol = new ArrayList<>();
        int cantidadVertices = grafo.vertices().size();

        visitar(grafo, inicio, visitados, candidatas);
        while (!candidatas.isEmpty() && arbol.size() < cantidadVertices - 1) {
            Arista arista = candidatas.remove();
            if (!visitados.contains(arista.destino())) {
                arbol.add(arista);
                visitar(grafo, arista.destino(), visitados, candidatas);
            }
        }

        verificarExpansion(arbol, cantidadVertices);
        return arbol;
    }

    private static void visitar(
            GrafoLista grafo, char vertice, Set<Character> visitados,
            PriorityQueue<Arista> candidatas) {
        visitados.add(vertice);
        for (GrafoLista.Arista vecina : grafo.vecinosDe(vertice)) {
            if (!visitados.contains(vecina.destino())) {
                candidatas.add(new Arista(vertice, vecina.destino(), vecina.peso()));
            }
        }
    }

    public static List<Arista> kruskal(GrafoLista grafo) {
        List<Character> vertices = grafo.vertices();
        List<Arista> aristas = new ArrayList<>();
        for (char origen : vertices) {
            for (GrafoLista.Arista vecina : grafo.vecinosDe(origen)) {
                // La lista guarda cada arista no dirigida en ambos sentidos.
                if (origen < vecina.destino()) {
                    aristas.add(new Arista(origen, vecina.destino(), vecina.peso()));
                }
            }
        }
        aristas.sort(POR_PESO);

        ConjuntosDisjuntos componentes = new ConjuntosDisjuntos(vertices);
        List<Arista> arbol = new ArrayList<>();
        for (Arista arista : aristas) {
            if (componentes.unir(arista.origen(), arista.destino())) {
                arbol.add(arista);
                if (arbol.size() == vertices.size() - 1) {
                    break;
                }
            }
        }

        verificarExpansion(arbol, vertices.size());
        return arbol;
    }

    private static void verificarExpansion(List<Arista> arbol, int cantidadVertices) {
        if (arbol.size() != cantidadVertices - 1) {
            throw new IllegalArgumentException("Se requiere un grafo conexo con al menos un vertice.");
        }
    }

    private static class ConjuntosDisjuntos {
        private final Map<Character, Character> padre = new HashMap<>();
        private final Map<Character, Integer> tamano = new HashMap<>();

        ConjuntosDisjuntos(List<Character> vertices) {
            for (char vertice : vertices) {
                padre.put(vertice, vertice);
                tamano.put(vertice, 1);
            }
        }

        private char raiz(char vertice) {
            char representante = padre.get(vertice);
            if (representante != vertice) {
                representante = raiz(representante);
                padre.put(vertice, representante);
            }
            return representante;
        }

        boolean unir(char primero, char segundo) {
            char raizPrimero = raiz(primero);
            char raizSegundo = raiz(segundo);
            if (raizPrimero == raizSegundo) {
                return false;
            }

            if (tamano.get(raizPrimero) < tamano.get(raizSegundo)) {
                char temporal = raizPrimero;
                raizPrimero = raizSegundo;
                raizSegundo = temporal;
            }
            padre.put(raizSegundo, raizPrimero);
            tamano.put(raizPrimero, tamano.get(raizPrimero) + tamano.get(raizSegundo));
            return true;
        }
    }
}
