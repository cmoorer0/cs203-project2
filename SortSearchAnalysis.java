public class SortSearchAnalysis
{

   public static void BuildArray(int inputSize, int inputBalance, boolean sorted, boolean duplicates)
   {
   }
   
   public static long[] SelectionSort( int[] A ) {
      //Temp variables
      int min; //Index of current partition minimum.
      int temp; //Buffer variable to perform swap
      long selectionComparisonCounter = 0; //counter for number of comparisons
      long selectionSwapCounter = 0; //counter for number of swaps
      // Loop to sort 1 item at a time (except last).
      for(int i = 0; i < A.length; i++) {
         min = i; //Init/reset index of current partition minimm
         // Scan array partition [i+1, n-1] to find partition minimum.
         for(int j = i+1; j < A.length; j++) {
            //Compare current partition minimum against current partition to scan item
            selectionComparisonCounter++;
            if( A[j] < A[min] ) {min = j;}
         }
         // Put partition minimum in its final sorted spot (swap A[i] and A[min]).
         temp = A[i];
         A[i] = A[min];
         A[min] = temp;
         selectionSwapCounter++;
      }
      return new long [] {selectionComparisonCounter, selectionSwapCounter};
   }
   
   public static long[] BubbleSort( int[] A ) {
      long bubbleComparisonCounter = 0; //counter for number of comparisons
      long bubbleSwapCounter = 0; //counter for number of swaps
      for (int i = 0; i < A.length - 1; i++ ) {
         // Bubble up items (partial scan).
         for (int j = 0; j < A.length - 1 - i; j++) {
            //Check if adjacent items are out of order.
            bubbleComparisonCounter++;
            if( A[j+1] < A[j] ) {
               //Swap adjacent items.
               int temp = A[j];
               A[j] = A[j+1];
               A[j+1] = temp;
               bubbleSwapCounter++;
            }
         }
      }
      return new long[] {bubbleComparisonCounter, bubbleSwapCounter};
   }
   
   public static long[] BubbleSortOptimized (int [] A) {
      long bubbleOptimizedComparisonCounter = 0; //counter for number of comparisons
      long bubbleOptimizedSwapCounter = 0; //counter for number of swaps
      // Loop to perform n-2 scans
      for (int i = 0; i < A.length - 1; i++) {
         //Bubble up items (partial scan)
         boolean swapMade = false; // Init flag to track swaps made.
         for (int j = 0; j < A.length - 1 - i; j++) {
            // Check if adj items are out of order
            bubbleOptimizedComparisonCounter++;
            if( A[j+1] < A[j] ) {
               //Swap adj items
               int temp = A[j];
               A[j] = A[j+1];
               A[j+1] = temp;
               swapMade = true; //Update flag to track swaps made.
               bubbleOptimizedSwapCounter++;
            }
         }
         // If no swap was made, early return (list sorted).
         if (!swapMade) {break;}
      }
      return new long[] {bubbleOptimizedComparisonCounter, bubbleOptimizedSwapCounter};
   }
   
   public static long[] InsertionSort (int [] A ) {
      int val = 0; // Init temp variale
      long insertionComparisonCounter = 0;
      long insertionSwapCounter = 0; //counter for number of swaps
      int j = 0; //Init temp variable
      for (int i = 1; i<A.length; i++) { // Iterate array items from 2nd to last
         val = A[i]; //Update temp variable
         j = i-1; //Update temp variable.
         insertionComparisonCounter++;
         //Scan array from before-curr to first, right-shifting when needed.
         while( (j >= 0) && (A[j] > val) ) {
            A[j+1] = A[j]; //right shift to order items
            j = j-1; //Decrement index for right-to-left scan.
            insertionComparisonCounter++;
         }
         A[j+1] = val; //Store current items at proper cell after right shift
         insertionSwapCounter++;
      }
      return new long[] {insertionComparisonCounter, insertionSwapCounter};
   }
   
   public static long[] MergeSortedArrays(int[] B, int[] C, int[] A) {
       int i = 0, j = 0, k = 0;
       long comparisons = 0;
       long copies = 0;
   
       while (i < B.length && j < C.length) {
           comparisons++;
   
           if (B[i] <= C[j]) {
               A[k++] = B[i++];
           } else {
               A[k++] = C[j++];
           }
           copies++;
       }
   
       // copy leftover elements
       if (i == B.length) {
           System.arraycopy(C, j, A, k, C.length - j);
           copies += (C.length - j);
       } else {
           System.arraycopy(B, i, A, k, B.length - i);
           copies += (B.length - i);
       }
   
       return new long[]{comparisons, copies};
   }

   
   
   public static long[] MergeSort(int[] A) {
       if (A.length <= 1) {
           return new long[]{0, 0}; // no work needed
       }
   
       // split the array
       int h = A.length / 2;
       int[] B = new int[h];
       int[] C = new int[A.length - h];
       System.arraycopy(A, 0, B, 0, h);
       System.arraycopy(A, h, C, 0, A.length - h);
   
       // recursive calls
       long[] leftCounts = MergeSort(B);
       long[] rightCounts = MergeSort(C);
   
       // merge step
       long[] mergeCounts = MergeSortedArrays(B, C, A);
   
       // accumulate totals
       return new long[] {
           leftCounts[0] + rightCounts[0] + mergeCounts[0], // comparisons
           leftCounts[1] + rightCounts[1] + mergeCounts[1]  // copies
       };
   }

   
   
   public static long[] LomutoPartition(int[] array, int low, int high) {
       int pivot = array[high];
       int i = low;
       long comparisons = 0L;
       long swaps = 0L;
   
       for (int j = low; j < high; j++) {
           comparisons++;
           if (array[j] < pivot) {
               // swap A[i] and A[j]
               int temp = array[i];
               array[i] = array[j];
               array[j] = temp;
   
               i++;
               swaps++;
           }
       }
   
       // final pivot swap
       int temp = array[i];
       array[i] = array[high];
       array[high] = temp;
       swaps++;
   
       // [pivotIndex, comparisons, swaps]
       return new long[] { i, comparisons, swaps };
   }
   
   public static long[] QuickSort(int[] A, int left, int right) {
   
       // Base case: no work
       if (left >= right) {
           // [pivotIndexOrFlag, comparisons, swaps]
           return new long[] { -1L, 0L, 0L };
       }
   
       // Partition step (returns pivot + counters)
       long[] part = LomutoPartition(A, left, right);
       int pivot = (int) part[0];   // index is still an int, just stored in long[0]
       long comp = part[1];
       long swap = part[2];
   
       // Recurse left
       long[] leftResult  = QuickSort(A, left,       pivot - 1);
   
       // Recurse right
       long[] rightResult = QuickSort(A, pivot + 1,  right);
   
       // Total the comparisons + swaps
       long totalComparisons = comp
                             + leftResult[1]
                             + rightResult[1];
   
       long totalSwaps = swap
                       + leftResult[2]
                       + rightResult[2];
   
       // We still don’t really care about the pivot in the final result,
       // but keep it in slot 0 to match the old layout.
       return new long[] { pivot, totalComparisons, totalSwaps };
   }


   
   
   
   public static long[] HeapSort(int[] A) {
       long comparisonCount = 0L; 
       long swapCount = 0L;         
   
       int n = A.length;
   
       // Build max heap
       for (int i = n / 2 - 1; i >= 0; i--) {
           comparisonCount += heapify(A, n, i);
       }
   
       // Extract elements from heap one by one
       for (int i = n - 1; i > 0; i--) {
           int temp = A[0];
           A[0] = A[i];
           A[i] = temp;
           swapCount++;
   
           comparisonCount += heapify(A, i, 0);
       }
   
       // [comparisons, swaps]
       return new long[] { comparisonCount, swapCount };
   }
   
   private static long heapify(int[] A, int heapSize, int i) {
       long comparisons = 0L;
   
       int largest = i;
       int left = 2 * i + 1;
       int right = 2 * i + 2;
   
       if (left < heapSize) {
           comparisons++;
           if (A[left] > A[largest]) {
               largest = left;
           }
       }
   
       if (right < heapSize) {
           comparisons++;
           if (A[right] > A[largest]) {
               largest = right;
           }
       }
   
       if (largest != i) {
           int temp = A[i];
           A[i] = A[largest];
           A[largest] = temp;
           // add comparisons from recursive call
           return comparisons + heapify(A, heapSize, largest);
       }
   
       return comparisons;
   }


   
   public static long[] SequentialSearch(int[] A, int k) {
       int i = 0;  // scan index
       long comparisonCount = 0L;
   
       // Scan until end or key found
       while (i < A.length && A[i] != k) {
           i++;
           comparisonCount++;
       }
   
       // Found?
       if (i < A.length) {
           return new long[] { comparisonCount, i };
       } else {
           return new long[] { comparisonCount, -1L };
       }
   }

   
   public static long[] BinarySearchIterative(int[] A, int k) {
       long comparisonCount = 0L;
       int left = 0;
       int right = A.length - 1;
       while (left <= right) {
           comparisonCount++;
           int middle = (left + right) / 2;
   
           if (k == A[middle]) {
               return new long[] { comparisonCount, middle };
           }
           else if (k < A[middle]) {
               right = middle - 1;
           }
           else {
               left = middle + 1;
           }
       }
       return new long[] { comparisonCount, -1L };
   }

   

   public static long[] BinarySearchRecursive(int[] A, int k, int left, int right) {
       return BinarySearchRecursiveHelper(A, k, left, right, 0L);
   }
   
   private static long[] BinarySearchRecursiveHelper(int[] A, int k,
                                                     int left, int right,
                                                     long countSoFar) {
       if (left > right) {
           return new long[] { countSoFar, -1L };
       }
   
       int middle = (left + right) / 2;
       countSoFar++;  // one comparison (k vs A[middle])
   
       if (k == A[middle]) {
           return new long[] { countSoFar, middle };
       }
       else if (k < A[middle]) {
           return BinarySearchRecursiveHelper(A, k, left, middle - 1, countSoFar);
       }
       else {
           return BinarySearchRecursiveHelper(A, k, middle + 1, right, countSoFar);
       }
   }


        
}
   
