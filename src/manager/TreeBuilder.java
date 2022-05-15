package manager;

import java.util.HashMap;
import java.util.List;

import models.Distance;
import models.HaversineDistance;
import models.Location;
import models.Restaurant;
import models.TreeNode;

public class TreeBuilder {

    Distance distanceCalculator;

    public TreeBuilder() {
        distanceCalculator = new HaversineDistance();
    }

    public TreeNode getTree(List<List<String>> validPathsList, HashMap<String, Restaurant> restaurantMap, HashMap<String, Location> locationMap) {
        TreeNode root = getTreeNode(false, "A");
        root.setCost(0.0);
        root.setLocation(locationMap.get("A"));
        TreeNode parent = root;
        boolean isTerminating;

        for(List<String> path: validPathsList) {
            isTerminating = false;
            parent = root;
            for(int i=1; i<path.size(); i++) {
                // Check if it is terminating node
                if (i == path.size() - 1) {
                    isTerminating = true;
                }
                // Create node if it does not exist
                HashMap<String, TreeNode> children = parent.getChildren();
                if(!children.containsKey(path.get(i))) {
                    children.put(path.get(i), getTreeNode(isTerminating, path.get(i)));
                    // Go to the newly created node & update it's cost
                    TreeNode newNode = children.get(path.get(i));
                    newNode.setLocation(locationMap.get(path.get(i)));
                    newNode.setCost(getNodeCost(parent, newNode, restaurantMap));
                }
                // Update parent
                parent = children.get(path.get(i));
            }
        }

        return root;
    }

    public TreeNode getTreeNode(boolean isTerminating, String nodeValue) {
        return new TreeNode(isTerminating, nodeValue);
    }

    public double getNodeCost(TreeNode parent, TreeNode child, HashMap<String, Restaurant> restaurantMap) {
        double cost = 0.0;
        double parentNodeCost = parent.getCost();
        if (child.getNodeName().charAt(0) == 'C') {
            cost = parentNodeCost + distanceCalculator.calculateDistance(parent.getLocation(), child.getLocation());
        } else if (child.getNodeName().charAt(0) == 'R') {
            cost = Math.max(parentNodeCost + distanceCalculator.calculateDistance(parent.getLocation(), child.getLocation()),
            restaurantMap.get(child.getNodeName()).getPreparationTime());
        }
        return cost;
    }

}
