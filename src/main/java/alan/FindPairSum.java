package alan;

/**
In a technical interview, you've been given an array of numbers and you need to find a pair of numbers that are equal to the given target value. Numbers can be either positive, negative, or both. Can you design an algorithm that works in O(n)—linear time or greater?

 let sequence = [8, 10, 2, 9, 7, 5]
 let results = pairValues(sum: 11) = //returns (9, 2)

 https://stackoverflow.blog/2022/01/31/the-complete-beginners-guide-to-dynamic-programming/
*/

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindPairSum {

    public static void main(String[] args) {
        int[] a = new int[] {8, 10, 2, 9, 7, 5};
        int sum = 11;
        int[] r = findPairSum(sum, a);
        System.out.println("findPairSum " + Arrays.toString(r));
    }
    public static int[] findPairSum(int sum, int[] a) {
        Set<Integer> remainSet = new HashSet<>(a.length);
        int[] result = null;
        for (int i=0; i < a.length; i++) {
            int remain = sum - a[i];
            if (remainSet.contains(remain)) {
                result = new int[] {a[i], remain};
                break;
            } else {
                remainSet.add(a[i]);
            }
        }
        return result;
    }
}
