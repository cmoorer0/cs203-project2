public class SortSearchAnalysis
{

   public static void BuildArray(int inputSize, int inputBalance, boolean sorted, boolean duplicates)
   {
   }
   
   public static void SelectionSort( int[] A ) {
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
   
   }
   
   public static void BubbleSort( int[] A ) {
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
   }
   
   public static void BubbleSortOptimized (int [] A) {
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
      }
   }
   
   public static void InsertionSort (int [] A ) {
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
   }
   
   public static void MergeSortedArrays(int[]B, int[]C, int[]A) {
      int i = 0; int j = 0; int k = 0; // Init temp variables
      int MergeSortedArraysComparisonCounter = 0;
      int MergeSortedArraysCopyCounter = 0;
      // Scanning sorted arrays B and C, while inserting in A
      while( (i < B.length) && (j < C.length) ) {
         MergeSortedArraysComparisonCounter++;
         if( B[i] <= C[j] ) {A[k] = B[i]; i++; MergeSortedArraysCopyCounter++;}
         else { A[k] = C[j]; j++; MergeSortedArraysCopyCounter++; }
         k++;
      }
      // One scan has terminted, transfer remaining sorted data in A
      if( i == B.length) {System.arraycopy(C, j, A, k, C.length - j); MergeSortedArraysCopyCounter += (C.length - j);}
      else {System.arraycopy(B, i, A, k, B.length - i); MergeSortedArraysCopyCounter += (B.length - i);} 
   }
   
   
   public static void MergeSort(int [] A) {
      if(A.length > 1) { //Check if sorting is really necessary
         int h = (int) Math.floor(A.length/2); //Determine halves size.
         // Init half 1 and 2
         int B[] = new int[h]; System.arraycopy(A,0,B,0,h);
         int C[] = new int[A.length-h]; System.arraycopy(A,h,C,0,A.length-h);
         // Sort (recursively) halves 1 and 2.
         MergeSort(B); MergeSort(C);
         //Merge sorted halves (arrays B and C) into final sorted arrays
         MergeSortedArrays(B,C,A); 
      }
   }
   
   
   public static int LumotoPartition(int[] array, int low, int high) {
     int pivot = array[high];   // choose last element as pivot
     int i = low;               // place for next smaller element
     int LumotoComparisonCounter = 0;
     int LumotoSwapCounter = 0;

     for (int j = low; j < high; j++) {
         LumotoComparisonCounter++;
         if (array[j] < pivot) {
             // swap array[i] and array[j]
             int temp = array[i];
             array[i] = array[j];
             array[j] = temp;
             i++;
             LumotoSwapCounter++;
         }
     }

     // put pivot in its final place
     int temp = array[i];
     array[i] = array[high];
     array[high] = temp;
     LumotoSwapCounter++;

     return i; // pivot index
    }
    
    public static int HoarePartition(int[] array, int low, int high) {
     int pivot = array[low];  // Hoare usually uses the first element as pivot
     int i = low - 1;
     int j = high + 1;
     int HoareComparisonCounter = 0;
     int HoareSwapCounter = 0;
     
     while (true) {
         // move i right until we find an element >= pivot
         do {
             i++;
             HoareComparisonCounter++;
         } while (array[i] < pivot);

         // move j left until we find an element <= pivot
         do {
             j--;
             HoareComparisonCounter++;
         } while (array[j] > pivot);

         // if pointers cross, return j as the partition index
         if (i >= j) {
             return j;
         }

         // otherwise, swap elements at i and j
         int temp = array[i];
         array[i] = array[j];
         array[j] = temp;
         HoareSwapCounter++;
     }
    }


   public static void QuickSortLumoto(int[]A, int left, int right) {
      //Check if any sorting is really necessary.
      if(left < right) {
         //Array partitioning (any partitioning algorithm can be used here).
         int pivot = LumotoPartition(A,left,right);
         //Recursive application of quicksort to left-part and right-part
         QuickSortLumoto(A,left,pivot-1);
         QuickSortLumoto(A,pivot+1, right);
      }
   }
   
   
   public static void QuickSortHoare(int[]A, int left, int right) {
      //Check if any sorting is really necessary.
      if(left < right) {
         //Array partitioning (any partitioning algorithm can be used here).
         int pivot = HoarePartition(A,left,right);
         //Recursive application of quicksort to left-part and right-part
         QuickSortHoare(A,left,pivot-1);
         QuickSortHoare(A,pivot+1, right);
      }
   }
   
   public static int SequentialSearch1(int[] A, int k) {
      int i = 0; //Scan index.
      int sequentialSearchCounter = 0;
      // Sequential lst scan: stop at list end or when key is found.
      while( (i < A.length ) && (A[i] != k ) ) {i++; sequentialSearchCounter++;}
      // Check if search is successful.
      if (i < A.length) {return i;}
      else {return -1;}
   }
   
   public static int BinarySearchIterative(int[] A, int k) {
      // Init temp variables (search partition).
      int left = 0;
      int middle = 0;
      int right = A.length - 1;
      // Search until range (search partition) is invalid.
      while( left <= right) {
         middle = (int)Math.floor( (left + right)
      }
   }
}