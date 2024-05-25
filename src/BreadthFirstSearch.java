import java.util.*;

public class BreadthFirstSearch<V> {
    public List<V> bfs(WeightedGraph<V> graph, V start) {
        List<V> result = new ArrayList<>();
        Set<V> visited = new HashSet<>();
        Queue<Vertex<V>> queue = new LinkedList<>();

        Vertex<V> startVertex = graph.getVertex(start);
        queue.add(startVertex);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            result.add(current.getData());

            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor.getData())) {
                    visited.add(neighbor.getData());
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }
}
