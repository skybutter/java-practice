package alan;

/**
 * Find peak element in an array
 * https://leetcode.com/problems/find-peak-element/solution/
 * https://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
 * Peak is if a[i] >= a[i-1] and a[i] >= a[i+1]
 * array could have multiple peak
 */
public class FindPeakElement {

    public static void main(String[] args) {
        System.out.println("findPeakLinearScan Index=" + findPeakLinearScan(arr));
        System.out.println("findPeakBinarySearch Index=" + findPeakBinarySearch(arr, 0 , arr.length-1));
        System.out.println("findPeakBinarySearchIter Index=" + findPeakBinarySearchIter(arr));
        System.out.println("========");
        System.out.println("findPeakLinearScan Index=" + findPeakLinearScan(arr1));
        System.out.println("findPeakBinarySearch Index=" + findPeakBinarySearch(arr1, 0 , arr1.length-1));
        System.out.println("findPeakBinarySearchIter Index=" + findPeakBinarySearchIter(arr1));
        System.out.println("========");
        System.out.println("findPeakLinearScan Index=" + findPeakLinearScan(arr2));
        System.out.println("findPeakBinarySearch Index=" + findPeakBinarySearch(arr2, 0 , arr2.length-1));
        System.out.println("findPeakBinarySearchIter Index=" + findPeakBinarySearchIter(arr2));
        System.out.println("========");
        System.out.println("findPeakLinearScan Index=" + findPeakLinearScan(arr3));
        System.out.println("findPeakBinarySearch Index=" + findPeakBinarySearch(arr3, 0 , arr3.length-1));
        System.out.println("findPeakBinarySearchIter Index=" + findPeakBinarySearchIter(arr3));
        System.out.println("========");
        System.out.println("findPeakLinearScan Index=" + findPeakLinearScan(arr4));
        System.out.println("findPeakBinarySearch Index=" + findPeakBinarySearch(arr4, 0 , arr4.length-1));
        System.out.println("findPeakBinarySearchIter Index=" + findPeakBinarySearchIter(arr4));
        System.out.println("========");
    }

    static int[] arr = { 1, 3, 20, 4, 1, 0 };
    static int[] arr1 = {5, 10, 20, 15};
    static int[] arr2 = {10, 20, 15, 2, 23, 90, 67};
    static int[] arr3 = { 0, 1, 2, 3, 4};
    static int[] arr4 = { 0, 1, 2, 3};

    // Solution 1 : Linear Scan
    public static int findPeakLinearScan(int[] a) {
        int n = a.length;
        // Handle edge case
        if (n == 1) { // only 1 element in array
            return 0;
        } else if (a[0]>a[1] || a[n-1]>a[n-2]) {
            return n-1;
        }
        for (int i=1; i < a.length-1; i++) {
            if (a[i]>=a[i-1] && a[i]>=a[i+1]) {
                return i;
            }
        }
        return -1;
    }

    // Solution 2 : Binary Search Recursive (Divide and Conquer)
    public static int findPeakBinarySearch(int[] a, int startPos, int endPos) {
        if (startPos == endPos)
            return startPos;
        // Find the middle element in the array
        int m = (endPos-startPos) / 2 + startPos;     // integer division always truncate (not round)
        if (a[m-1]>a[m]) { // Go left
            return findPeakBinarySearch(a, startPos, m-1);
        } else if (a[m+1]>a[m]) { // Go right
            return findPeakBinarySearch(a, m+1, endPos);
        } else if (a[m] >= a[m-1] && a[m]>=a[m+1]) { // mid is peak
            return m;
        } else
            return -1;
    }

    // Solution 3 : Binary Search Iterative
    public static int findPeakBinarySearchIter(int[] a) {
        // Find the middle element in the array
        boolean eval = true;
        int startPos = 0;
        int endPos = a.length-1;
        int m = -1;
        while (eval) {
            if (startPos == endPos) return startPos;
            m = (startPos+endPos)/2;
            if (a[m-1]>a[m]) { // Go left
                endPos = m-1;
            } else if (a[m+1]>a[m]) { // Go right
                startPos = m+1;
            } else if (a[m] >= a[m-1] && a[m]>=a[m+1]) { // mid is peak
                eval = false;   // break
            } else {
                m = -1;
                eval = false;   // break
            }
        }
        return m;
    }

}
