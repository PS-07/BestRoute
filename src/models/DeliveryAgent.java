package models;

public class DeliveryAgent {

    private String agentId;

    private Location startLocation;

    public DeliveryAgent(final String agentId, final Location startLocation) {
        this.agentId = agentId;
        this.startLocation = startLocation;
    }

    public String getAgentId() {
        return agentId;
    }

    public Location getStartLocation() {
        return startLocation;
    }
}
