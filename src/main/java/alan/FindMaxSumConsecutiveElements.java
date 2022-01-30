package alan;

/**
 * This problem is from the page https://www.geeksforgeeks.org/window-sliding-technique/
 * Find the maximum sum of k consecutive elements in the array.
 * Example 1:
 * Input  : arr[] = {100, 200, 300, 400}
 *          k = 2
 * Output : 700
 * Example 2
 * Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
 *          k = 4
 * Output : 39
 * We get maximum sum by adding subarray {4, 2, 10, 23} of size 4.
 *
 */
public class FindMaxSumConsecutiveElements {

    public static void main(String[] args) {
        int arr[] = { 1, 4, 2, 10, 2, 3, 1, 0, 20 };
        int k = 4;
        System.out.println("maxSumBruteForce=" + maxSumBruteForce(arr, k));
        System.out.println("maxSumSlidingWindow=" + maxSumSlidingWindow(arr, k));
        int arr1[] = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        k = 4;
        System.out.println("maxSumBruteForce=" + maxSumBruteForce(arr1, k));
        System.out.println("maxSumSlidingWindow=" + maxSumSlidingWindow(arr1, k));
        int arr2[] = {100, 200, 300, 400};
        k = 2;
        System.out.println("maxSumBruteForce=" + maxSumBruteForce(arr2, k));
        System.out.println("maxSumSlidingWindow=" + maxSumSlidingWindow(arr2, k));
    }

    public static int maxSumBruteForce(int arr[], int k) {
        int maxSum = Integer.MIN_VALUE;
        for (int i=0; i < arr.length-k+1; i++) {
            int sum = arr[i];
            for (int j=1; j < k; j++) {
                sum = sum + arr[i+j];
            }
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }

    public static int maxSumSlidingWindow(int arr[], int k) {
        int currentSum = 0;
        // Compute sum of first window of size k
        for (int i=0; i < k; i++) {
            currentSum = currentSum + arr[i];
        }
        int maxSum = currentSum;
        // compute sum of remaining windows by removing first element of previous window
        // and adding next element of current window

        // option 1 - index i start differently than option 2
//        for (int i=1; i < arr.length-k+1; i++) {
//            currentSum = currentSum - arr[i-1] + arr[i+k-1];
//            if (currentSum > maxSum) {
//                maxSum = currentSum;
//            }
//        }
        // option 2
        for (int i=k; i < arr.length; i++) {
            currentSum = currentSum - arr[i-k] + arr[i];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }
}
