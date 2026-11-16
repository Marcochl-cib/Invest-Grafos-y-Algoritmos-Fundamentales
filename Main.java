import java.util.List;
public class Main {
    public static void main(String[] args) {
        GrafoLista grafo = new GrafoLista();
        grafo.agregarArista('A', 'B', 2);
        grafo.agregarArista('A', 'C', 5);
        grafo.agregarArista('B', 'C', 1);
        grafo.agregarArista('B', 'D', 4);
        grafo.agregarArista('C', 'E', 6);
        grafo.agregarArista('D', 'E', 3);

        GrafoMatriz matriz = new GrafoMatriz(grafo);
        System.out.println("Matriz de adyacencia (- = sin arista)");
        System.out.println(matriz);
        System.out.println();

        System.out.println("BFS desde A: " + Recorridos.bfs(grafo, 'A'));
        System.out.println("DFS desde A: " + Recorridos.dfs(grafo, 'A'));

        System.out.println();
        mostrarArbol("Prim desde A", ArbolExpansionMinima.prim(grafo, 'A'));
        mostrarArbol("Kruskal", ArbolExpansionMinima.kruskal(grafo));
    }
        private static void mostrarArbol(String nombre, List<ArbolExpansionMinima.Arista> arbol) {
        long pesoTotal = 0;
        for (ArbolExpansionMinima.Arista arista : arbol) {
            pesoTotal += arista.peso();
        }
        System.out.println(nombre + ": " + arbol + " | Peso total: " + pesoTotal);
    }
}
