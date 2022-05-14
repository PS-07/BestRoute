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

    public void setAgentId(final String agentId) {
        this.agentId = agentId;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(final Location startLocation) {
        this.startLocation = startLocation;
    }
}
