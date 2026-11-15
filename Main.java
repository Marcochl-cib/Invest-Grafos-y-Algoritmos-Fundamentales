public class Main {
    public static void main(String[] args) {
        GrafoLista grafo = new GrafoLista();
        grafo.agregarArista('A', 'B', 2);
        grafo.agregarArista('A', 'C', 5);
        grafo.agregarArista('B', 'C', 1);
        grafo.agregarArista('B', 'D', 4);
        grafo.agregarArista('C', 'E', 6);
        grafo.agregarArista('D', 'E', 3);

        System.out.println("BFS desde A: " + Recorridos.bfs(grafo, 'A'));
    }
}
