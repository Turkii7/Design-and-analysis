import java.util.*;

public class Dijkstra {
    public static Map<String, Integer> calculateShortestPath(Map<String, Airport> graph, String startId, Map<String, String> prev) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (String id : graph.keySet()) {
            distances.put(id, Integer.MAX_VALUE);
        }

        distances.put(startId, 0);
        queue.add(startId);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            Airport currentAirport = graph.get(current);

            for (Map.Entry<String, Integer> entry : currentAirport.getDestinations().entrySet()) {
                String neighbor = entry.getKey();
                int newDist = distances.get(current) + entry.getValue();

                if (newDist < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    distances.put(neighbor, newDist);
                    prev.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return distances;
    }

    public static List<String> getPath(String start, String end, Map<String, String> prev) {
        LinkedList<String> path = new LinkedList<>();
        for (String at = end; at != null; at = prev.get(at)) {
            path.addFirst(at);
        }
        if (!path.isEmpty() && path.getFirst().equals(start)) {
            return path;
        }
        return Collections.emptyList();
    }
}
