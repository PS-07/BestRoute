package models;

import java.util.HashMap;

public class TreeNode {
    boolean terminating;
    String nodeName;
    double cost;
    Location location;
    TreeNode parent;
    HashMap<String, TreeNode> children;

    public TreeNode(boolean terminating, String nodeName) {
        this.terminating = terminating;
        this.nodeName = nodeName;
        this.children = new HashMap<>();
    }

    public boolean getTerminating() {
        return this.terminating;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public HashMap<String,TreeNode> getChildren() {
        return this.children;
    }

    public TreeNode getParent() {
        return this.parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
}
