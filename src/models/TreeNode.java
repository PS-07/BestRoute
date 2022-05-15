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
    

    public boolean isTerminating() {
        return this.terminating;
    }

    public boolean getTerminating() {
        return this.terminating;
    }

    public void setTerminating(boolean terminating) {
        this.terminating = terminating;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
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

    public void setChildren(HashMap<String,TreeNode> children) {
        this.children = children;
    }

    public TreeNode getParent() {
        return this.parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }


    @Override
    public String toString() {
        return "{" +
            ", children='" + getChildren() + "'" +
            "}";
    }
    
}
