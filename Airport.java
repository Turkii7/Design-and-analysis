import java.util.Map;

public class Airport {
    private String id;
    private String name;
    private Map<String, Integer> destinations;

    public Airport(String id, String name, Map<String, Integer> destinations) {
        this.id = id;
        this.name = name;
        this.destinations = destinations;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getDestinations() {
        return destinations;
    }
}
