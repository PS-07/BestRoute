package manager;

import java.util.ArrayList;
import java.util.List;

public class PathsGenerator {

    public PathsGenerator() {
    }

    public static void allPermutations(List<String> basePath, int left, int right, List<List<String>> allPermuationList) {
        if (left == right) {
            List<String> basePathCopy = new ArrayList<>();
            basePathCopy.addAll(basePath);
            if (isValid(basePathCopy)) {
                allPermuationList.add(basePathCopy);
            }
        } else {
            for(int i=left; i<=right; i++) {
                swap(basePath, left, i);
                allPermutations(basePath, left+1, right, allPermuationList);
                swap(basePath, left, i);
            }
        }
    }

    private static void swap(List<String> str, int x, int y) {
        String temp = str.get(x);
        str.set(x, str.get(y));
        str.set(y, temp);
    }
     
    public List<List<String>> getValidPaths(int n) {
        List<String> basePath = new ArrayList<>();
        basePath.add("A");
        for(int i=1; i<=n; i++) {
            String r = "R" + i;
            String c = "C" + i;
            basePath.add(r);
            basePath.add(c);
        }

        List<List<String>> allPathsList = new ArrayList<>();

        allPermutations(basePath, 1, basePath.size()-1, allPathsList);
        
        return allPathsList;
    }
    
    public static boolean isValid(final List<String> path) {
        final int n = path.size()/2;
        List<Integer> positionCustomer = new ArrayList<>(n+1);
        List<Integer> positionRestaurant = new ArrayList<>(n+1);

        for(int i=0; i<=n; i++) {
            positionCustomer.add(0, -1);
            positionRestaurant.add(0, -1);
        }

        for(int i=0; i<path.size(); i++) {
            if (!path.get(i).equalsIgnoreCase("A")) {
                char identifyNode = path.get(i).charAt(0);
                int nodeIndex = Character.getNumericValue(path.get(i).charAt(1));
                if (identifyNode == 'C') {
                    positionCustomer.set(nodeIndex, i);
                } else if (identifyNode == 'R') {
                    positionRestaurant.set(nodeIndex, i);
                }  
            }
        }

        for(int i=1; i<=n; i++) {
            if (positionCustomer.get(i)<positionRestaurant.get(i)) {
                return false;
            }
        }
        return true;
    }
}
