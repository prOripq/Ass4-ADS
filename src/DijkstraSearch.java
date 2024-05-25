import java.util.*;

public class DijkstraSearch<V> {
    public Map<V, Double> dijkstra(WeightedGraph<V> graph, V start) {
        Map<V, Double> distances = new HashMap<>();
        Map<V, Vertex<V>> vertices = graph.getVertices();
        PriorityQueue<VertexDistance<V>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(VertexDistance::getDistance));

        for (V vertex : vertices.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        priorityQueue.add(new VertexDistance<>(vertices.get(start), 0.0));

        while (!priorityQueue.isEmpty()) {
            VertexDistance<V> current = priorityQueue.poll();
            Vertex<V> currentVertex = current.getVertex();

            for (Map.Entry<Vertex<V>, Double> entry : currentVertex.getAdjacentVertices().entrySet()) {
                Vertex<V> adjacentVertex = entry.getKey();
                double edgeWeight = entry.getValue();
                double newDist = distances.get(currentVertex.getData()) + edgeWeight;

                if (newDist < distances.get(adjacentVertex.getData())) {
                    distances.put(adjacentVertex.getData(), newDist);
                    priorityQueue.add(new VertexDistance<>(adjacentVertex, newDist));
                }
            }
        }

        return distances;
    }
}

class VertexDistance<V> {
    private Vertex<V> vertex;
    private double distance;

    public VertexDistance(Vertex<V> vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public Vertex<V> getVertex() {
        return vertex;
    }

    public double getDistance() {
        return distance;
    }
}
