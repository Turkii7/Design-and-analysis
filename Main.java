import com.google.gson.*;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(new FileReader("airports.json"), JsonObject.class);

            Map<String, Airport> airports = new HashMap<>();

            for (String id : jsonObject.keySet()) {
                JsonObject airportJson = jsonObject.getAsJsonObject(id);
                String name = airportJson.get("Name").getAsString();

                JsonObject destJson = airportJson.getAsJsonObject("Destinations");
                Map<String, Integer> destinations = new HashMap<>();
                for (String destId : destJson.keySet()) {
                    destinations.put(destId, destJson.get(destId).getAsInt());
                }

                airports.put(id, new Airport(id, name, destinations));
            }

            Scanner scanner = new Scanner(System.in);

            System.out.println("Available airports:");
            for (Airport a : airports.values()) {
                System.out.println("ID: " + a.getId() + " - " + a.getName());
            }

            System.out.print("\nEnter start airport ID: ");
            String start = scanner.nextLine();
            System.out.print("Enter destination airport ID: ");
            String end = scanner.nextLine();

            Map<String, String> prev = new HashMap<>();
            Map<String, Integer> distances = Dijkstra.calculateShortestPath(airports, start, prev);
            List<String> path = Dijkstra.getPath(start, end, prev);

            if (path.isEmpty()) {
                System.out.println("No path found.");
            } else {
                System.out.println("\nShortest path:");
                for (String id : path) {
                    System.out.print(airports.get(id).getName());
                    if (!id.equals(end)) System.out.print(" -> ");
                }
                System.out.println("\nTotal distance: " + distances.get(end));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
