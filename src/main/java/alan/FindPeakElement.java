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
        System.out.println("findPeakBinarySearchCopyArray Index=" + findPeakBinarySearchCopyArray(arr));
        System.out.println("findPeakBinarySearch Index=" + findPeakBinarySearch(arr, 0 , arr.length-1));
        System.out.println("========");
        System.out.println("findPeakLinearScan Index=" + findPeakLinearScan(arr1));
        System.out.println("findPeakBinarySearchCopyArray Index=" + findPeakBinarySearchCopyArray(arr1));
        System.out.println("findPeakBinarySearch Index=" + findPeakBinarySearch(arr1, 0 , arr1.length-1));
        System.out.println("========");
        System.out.println("findPeakLinearScan Index=" + findPeakLinearScan(arr2));
        System.out.println("findPeakBinarySearchCopyArray Index=" + findPeakBinarySearchCopyArray(arr2));
        System.out.println("findPeakBinarySearch Index=" + findPeakBinarySearch(arr2, 0 , arr2.length-1));
        System.out.println("========");
    }

    static int arr[] = { 1, 3, 20, 4, 1, 0 };
    static int arr1[] = {5, 10, 20, 15};
    static int arr2[] = {10, 20, 15, 2, 23, 90, 67};

    // Solution 1 : Linear Scan
    public static int findPeakLinearScan(int a[]) {
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

    // Solution 2 : Binary Search Recursive (Divide and Conquer) copy array
    public static int findPeakBinarySearchCopyArray(int a[]) {
        // Find the middle element in the array
        int m = a.length / 2;     // integer division always truncate (not round)
        if (m<1) {
            return 0;
        }
        if (a[m-1]>a[m]) { // Go left
            // Copy array is not good, incur cost to time and space
            int[] copied = new int[m];
            System.arraycopy(a, 0, copied, 0, m);
            return findPeakBinarySearchCopyArray(copied);
        } else if (a[m+1]>a[m]) { // Go right
            // Copy array is not good, incur cost to time and space
            int[] copied = new int[a.length-m-1];
            System.arraycopy(a, m+1, copied, 0, m);
            return m + 1 + findPeakBinarySearchCopyArray(copied);
        } else if (a[m] >= a[m-1] && a[m]>=a[m+1]) { // mid is peak
            return m;
        } else
            return -1;
    }

    // Solution 3 : Binary Search Recursive (Divide and Conquer)
    public static int findPeakBinarySearch(int a[], int startPos, int endPos) {
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

}
