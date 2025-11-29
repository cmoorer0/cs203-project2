public class SortSearchAnalysis
{

   public static void BuildArray(int inputSize, int inputBalance, boolean sorted, boolean duplicates)
   {
   }
   
   public static int[] SelectionSort( int[] A ) {
      //Temp variables
      int min; //Index of current partition minimum.
      int temp; //Buffer variable to perform swap
      int selectionComparisonCounter = 0; //counter for number of comparisons
      int selectionSwapCounter = 0; //counter for number of swaps
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
      return new int [] {selectionComparisonCounter, selectionSwapCounter};
   }
   
   public static int[] BubbleSort( int[] A ) {
      int bubbleComparisonCounter = 0; //counter for number of comparisons
      int bubbleSwapCounter = 0; //counter for number of swaps
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
      return new int[] {bubbleComparisonCounter, bubbleSwapCounter};
   }
   
   public static int[] BubbleSortOptimized (int [] A) {
      int bubbleOptimizedComparisonCounter = 0; //counter for number of comparisons
      int bubbleOptimizedSwapCounter = 0; //counter for number of swaps
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
      return new int[] {bubbleOptimizedComparisonCounter, bubbleOptimizedSwapCounter};
   }
   
   public static int[] InsertionSort (int [] A ) {
      int val = 0; // Init temp variale
      int insertionComparisonCounter = 0;
      int insertionSwapCounter = 0; //counter for number of swaps
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
      return new int[] {insertionComparisonCounter, insertionSwapCounter};
   }
   
   public static int[] MergeSortedArrays(int[] B, int[] C, int[] A) {
       int i = 0, j = 0, k = 0;
       int comparisons = 0;
       int copies = 0;
   
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
   
       return new int[]{comparisons, copies};
   }

   
   
   public static int[] MergeSort(int[] A) {
       if (A.length <= 1) {
           return new int[]{0, 0}; // no work needed
       }
   
       // split the array
       int h = A.length / 2;
       int[] B = new int[h];
       int[] C = new int[A.length - h];
       System.arraycopy(A, 0, B, 0, h);
       System.arraycopy(A, h, C, 0, A.length - h);
   
       // recursive calls
       int[] leftCounts = MergeSort(B);
       int[] rightCounts = MergeSort(C);
   
       // merge step
       int[] mergeCounts = MergeSortedArrays(B, C, A);
   
       // accumulate totals
       return new int[] {
           leftCounts[0] + rightCounts[0] + mergeCounts[0], // comparisons
           leftCounts[1] + rightCounts[1] + mergeCounts[1]  // copies
       };
   }

   
   
   public static int[] LomutoPartition(int[] array, int low, int high) {
       int pivot = array[high];
       int i = low;
       int comparisons = 0;
       int swaps = 0;
   
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
   
       return new int[] { i, comparisons, swaps };
   }

    
   public static int[] QuickSort(int[] A, int left, int right) {
   
       // Base case: no work
       if (left >= right) {
           return new int[] { -1, 0, 0 };
       }
   
       // Partition step (returns pivot + counters)
       int[] part = LomutoPartition(A, left, right);
       int pivot = part[0];
       int comp = part[1];
       int swap = part[2];
   
       // Recurse left
       int[] leftResult = QuickSort(A, left, pivot - 1);
   
       // Recurse right
       int[] rightResult = QuickSort(A, pivot + 1, right);
   
       // Total the comparisons + swaps
       int totalComparisons = comp + leftResult[1] + rightResult[1];
       int totalSwaps = swap + leftResult[2] + rightResult[2];
   
       return new int[] { pivot, totalComparisons, totalSwaps };
   }

   
   
   
   public static int[] HeapSort(int[] A) {
    int comparisonCount = 0; 
    int swapCount = 0;         

    int n = A.length;

    for (int i = n / 2 - 1; i >= 0; i--) {
        comparisonCount += heapify(A, n, i);
    }

    for (int i = n - 1; i > 0; i--) {
        int temp = A[0];
        A[0] = A[i];
        A[i] = temp;
        swapCount++;

        comparisonCount += heapify(A, i, 0);
    }
    return new int[] {comparisonCount, swapCount};
   }
   
   
   private static int heapify(int[] A, int heapSize, int i) {
    int comparisons = 0;

    int largest = i;
    int left = 2*i + 1;
    int right = 2*i + 2;

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
        return comparisons + heapify(A, heapSize, largest);
    }

    return comparisons;
   }

   
   public static int[]  SequentialSearch(int[] A, int k) {
      int i = 0; //Scan index.
      int sequentialSearchCounter = 0;
      // Sequential lst scan: stop at list end or when key is found.
      while( (i < A.length ) && (A[i] != k ) ) {i++; sequentialSearchCounter++;}
      // Check if search is successful.
      if (i < A.length) {return new int[] {sequentialSearchCounter, i};}
      else {return new int[] {sequentialSearchCounter, -1};}
   }
   
   public static int[] BinarySearchIterative(int[] A, int k) {
      // Init temp variables (search partition).
      int binaryIterativeCounter = 0;
      int left = 0;
      int middle = 0;
      int right = A.length - 1;
      // Search until range (search partition) is invalid.
      while( left <= right) {
         binaryIterativeCounter++;
         middle = (int)Math.floor( (left + right) /2); // Update Pivot (middle)
         if( k == A[middle] ) {return new int[] {binaryIterativeCounter, middle}; } //Key found at pivot (middle).
         else if( k < A[middle] ) {right = middle - 1; } //Update range (left half).
         else { left = middle + 1;} //Update range (right half).
         
      }
      return new int[] {binaryIterativeCounter, -1}; // Loop ended without finding input key: search failed, return -1.
   }
   
//static int binaryRecursiveCounter = 0;

   public static int[] BinarySearchRecursive(int[] A, int k, int left, int right) {
   int binaryRecursiveCounter = 0;
       if (left > right) { return new int[] {binaryRecursiveCounter, -1}; }
   
       binaryRecursiveCounter++; // count this call
   
       int middle = (left + right) / 2;
   
       if (k == A[middle]) {
           return new int[] {binaryRecursiveCounter, middle};
       }
   
       if (k < A[middle]) {
           return BinarySearchRecursive(A, k, left, middle - 1);
       } else {
           return BinarySearchRecursive(A, k, middle + 1, right);
       }
   }

        
}
   
