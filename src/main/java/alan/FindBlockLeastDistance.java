package alan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 * This is an exercise from the Google Coding Interview from https://youtu.be/rw4s4M3hFfs
 * Also can be found in https://leetcode.com/discuss/interview-question/algorithms/285144/interview-question-minimize-the-distance-to-the-farthest-point
 *
 * Given input as such.  Find the block has the short distance to all elements in the Reqs.
 * The number of elements in block and in the Reqs can vary.  Here is only an example.
 * Assume input data is well formatted.
 *
 * blocks = [
 * {
 *     "gym" : false,
 *     "school" : true,
 *     "store" : false
 * },
 * {
 *     "gym": true,
 *     "school": false,
 *     "store": false
 * },
 * {
 *     "gym": true,
 *     "school": true,
 *     "store": false
 * },
 * {
 *     "gym": false,
 *     "school": true,
 *     "store": false
 * },
 * {
 *     "gym": false,
 *     "school": true,
 *     "store": true
 * }
 * ]
 * Reqs = ["gym", "school", "store"]
 *
 * The answer above is block[3] (zero based index)
 */

public class FindBlockLeastDistance {

    public static void main(String[] args) {
        int result = findBlockIndexLeastMaxDistanceArrayBruteForce();
        System.out.println("findBlockIndexLeastMaxDistanceArrayBruteForce Index=" + result);
        System.out.println("findBlockIndexLeastMaxDistance Index=" + findBlockIndexLeastMaxDistance());
    }

    // Not part of the question, but set this up so we can use this in the code
    // setup block as 2 dimension array of integer.  0 = false, 1 = true
    public static int[][] getBlockAsArray() {
        int block[][] = {{0, 1, 0}, {1, 0, 0}, {1, 1, 0}, {0, 1, 0}, {0, 1, 1}};
        return block;
    }

    public static int[][] initializeDistanceArray(int x, int y) {
        int[][] distance = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        return distance;
    }

    // return the index in the block with the least distance to all elements on the list
    public static int findBlockIndexLeastMaxDistanceArrayBruteForce() {
        int[][] block = getBlockAsArray();
        // Define the data structure to use for calculate distance
        int[][] distance = initializeDistanceArray(block.length, block[0].length);
        // Loop over each Block forward to find the distance of each Req within the block
        for (int m = 0; m < block.length; m++) {
            for (int i = m; i < block.length; i++) {
                // Loop over each element inside the block to check distance
                for (int e = 0; e < block[i].length; e++) {
                    if (block[i][e] == 1 && distance[m][e] > (i - m)) {
                        distance[m][e] = i - m;
                    }
                }
                // TODO: Loop forward, as soon as all elements are not Max Integer, can break out of the loop
            }
        }
        // Loop over each Block backward to find the distance of each Req within the block
        for (int m = block.length - 1; m > 0; m--) {
            for (int i = m - 1; i >= 0; i--) {
                // Loop over each element inside the block to check distance
                for (int e = 0; e < block[i].length; e++) {
                    if (block[i][e] == 1 && distance[m][e] > (m - i)) {
                        distance[m][e] = m - i;
                    }
                }
            }
        }
        // Find the minimum of the maximum distance
        int result = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < distance.length; i++) {
            int max = distance[i][0];
            for (int j = 0; j < distance[i].length; j++) {
                if (distance[i][j] > max) {
                    max = distance[i][j];
                }
            }
            if (max < min) {
                result = i;
                min = max;
            }
        }
        return result;
    }

    // Blocks input
    public static List<Map<String, Boolean>> getBlockAsList() {
        List<Map<String, Boolean>> blocks = new ArrayList<>();
        HashMap<String, Boolean> map1 = new HashMap<>();
        map1.put("gym", false);
        map1.put("school", true);
        map1.put("store", false);
        blocks.add(map1);
        HashMap<String, Boolean> map2 = new HashMap<>();
        map2.put("gym", true);
        map2.put("school", false);
        map2.put("store", false);
        blocks.add(map2);
        HashMap<String, Boolean> map3 = new HashMap<>();
        map3.put("gym", true);
        map3.put("school", true);
        map3.put("store", false);
        blocks.add(map3);
        HashMap<String, Boolean> map4 = new HashMap<>();
        map4.put("gym", false);
        map4.put("school", true);
        map4.put("store", false);
        blocks.add(map4);
        HashMap<String, Boolean> map5 = new HashMap<>();
        map5.put("gym", false);
        map5.put("school", true);
        map5.put("store", true);
        blocks.add(map5);
        return blocks;
    }
    // Requirement input
    public static List<String> amenities = Arrays.asList("gym", "school", "store");

    public static int findBlockIndexLeastMaxDistance() {
        List<Map<String, Boolean>> blocks = getBlockAsList();
        List<String> requirements = amenities;
        // Use 2D int array to store the distance to each amenity for each block
        int[][] distances = initializeDistanceArray(requirements.size(), blocks.size());
        // initialize result List with minimum value
        List<Integer> result = new ArrayList<>(blocks.size());
        for (int i = 0; i < blocks.size(); i++) {
            result.add(Integer.MIN_VALUE);
        }
        // Loop over each requirement to find get the farthest distance for each block
        for (int r=0; r < requirements.size(); r++) {

            // Loop over each block forward.  When a block has the amenity, mark the block idx as nearestReqIdx
            Integer nearestReqIdx = null;
            for (int i=0; i < blocks.size(); i++) {
                if (blocks.get(i).get(requirements.get(r))) {
                    nearestReqIdx = i;
                }
                if (nearestReqIdx != null) {
                    distances[r][i] = Math.min(distances[r][i], i-nearestReqIdx);
                }
            }
            nearestReqIdx = null;
            // Loop over each block backward
            for (int i=blocks.size()-1; i >=0; i--) {
                if (blocks.get(i).get(requirements.get(r))) {
                    nearestReqIdx = i;
                }
                if (nearestReqIdx != null) {
                    distances[r][i] = Math.min(distances[r][i], nearestReqIdx-i);
                }
            }
            for (int i=0; i < blocks.size(); i++) {
                result.set(i, Math.max(result.get(i), distances[r][i]));
            }

        }
//        int min = Collections.min(result);
//        return result.indexOf(min);
        int min = result.get(0);
        int b = 0;
        for (int i=1; i < result.size(); i++) {
            if (result.get(i) < min) {
                min = result.get(i);
                b = i;
            }
        }
        return b;
    }

    // Get json into List Map
    private static String json = "[{\"gym\":false,\"school\":true,\"store\":false},{\"gym\":true,\"school\":false,\"store\":false},{\"gym\":true,\"school\":true,\"store\":false},{\"gym\":false,\"school\":true,\"store\":false},{\"gym\":false,\"school\":true,\"store\":true}]";
    private static List<Map<String, Boolean>> getJsonAsListMap() {
        List<Map<String, Boolean>> data = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(json, new TypeReference<List<Map<String, Boolean>>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return data;
    }

}
