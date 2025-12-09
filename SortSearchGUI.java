import javax.swing.*;
import java.awt.*;

public class SortSearchGUI extends JFrame {

    public SortSearchGUI() {
        super("Sort / Search Analysis");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 900);
        setLocationRelativeTo(null); // center on screen

        JTabbedPane tabs = new JTabbedPane();

        // Main menu tab
        tabs.addTab("Main Menu", new MainMenuPanel());

        // Report tabs: one per algorithm (CSV name, tab title)
         tabs.addTab("Selection Report",
             new ReportPanel(
                 "selectionsort_results.csv",
                 "Selection Sort",
                 "Selection sort repeatedly scans the unsorted portion of the array to find " //description
                 + "the smallest remaining element and swaps it into its correct position. "
                 + "It always performs the same number of comparisons (O(n^2)) regardless "
                 + "of input order, but performs at most n swaps.",
                 "Comparisons should grow close to n^2/2, forming a smooth quadratic curve. " //analysis
                 + "Because selection sort performs the same work on every input, the graph "
                 + "should show predictable, uniform growth with minimal variation."
             )
         );
         
         tabs.addTab("Bubble Report",
             new ReportPanel(
                 "bubblesort_results.csv",
                 "Bubble Sort",
                 "Bubble sort compares adjacent elements and swaps them when out of order. " //description
                 + "Large values gradually 'bubble' to the end of the array. "
                 + "Worst and average case are O(n^2), with many swaps.",
                 "Comparisons and swaps should grow quadratically. Bubble sort is sensitive " //analysis
                 + "to input order, so random distributions may cause variability. "
                 + "Expect a slower and noisier curve compared to selection sort."
             )
         );
         
         tabs.addTab("Optimized Bubble Report",
             new ReportPanel(
                 "optimizedbubblesort_results.csv",
                 "Optimized Bubble Sort",
                 "An improved bubble sort that stops early if a full pass makes no swaps. " //description
                 + "This significantly speeds up nearly sorted inputs. "
                 + "Worst case remains O(n^2).",
                 "On random arrays, performance looks like normal bubble sort. " //analysis
                 + "On partially sorted data, comparisons and swaps should drop noticeably, "
                 + "producing a curve slightly below standard bubble sort."
             )
         );
         
         tabs.addTab("Insertion Report",
             new ReportPanel(
                 "insertionsort_results.csv",
                 "Insertion Sort",
                 "Insertion sort builds the sorted list one value at a time by inserting " //description
                 + "each new element into its proper position. "
                 + "Worst case is O(n^2), but best case is O(n) on nearly sorted arrays.",
                 "On random data, comparisons show quadratic growth. " //analysis
                 + "However, insertion sort is very sensitive to input order, so skewed or "
                 + "partially sorted distributions may show lower-than-expected comparisons."
             )
         );
         
         tabs.addTab("Merge Report",
             new ReportPanel(
                 "mergesort_results.csv",
                 "Merge Sort",
                 "Merge sort is a divide-and-conquer algorithm that recursively splits the " //description
                 + "array and merges the sorted halves. "
                 + "Guaranteed O(n log n) performance in all cases, but requires extra memory.",
                 "The number of copies grows in proportion to n log n. " //analysis
                 + "Graphs should show a clearly sub-quadratic curve that increases steadily "
                 + "with very little variation between runs."
             )
         );
         
         tabs.addTab("Quick Report",
             new ReportPanel(
                 "quicksortlomuto_results.csv",
                 "Quick Sort (Lomuto)",
                 "Quick sort partitions the array around a pivot (Lomuto uses the last element). " //description
                 + "Average-case performance is O(n log n), but worst case is O(n^2) on already "
                 + "sorted or highly skewed inputs.",
                 "Random data should produce n log n behavior, but skewed distributions may " //analysis
                 + "increase comparisons. The graph may appear less stable compared to merge "
                 + "or heap sort due to pivot-position variability."
             )
         );
         
         tabs.addTab("Heap Report",
             new ReportPanel(
                 "heapsort_results.csv",
                 "Heap Sort",
                 "Heap sort builds a max heap and repeatedly extracts the largest element. " //description
                 + "It guarantees O(n log n) performance without extra memory.",
                 "Heap sort comparisons generally follow a stable n log n curve. " //analysis
                 + "Expect slightly more comparisons than merge sort but more consistency "
                 + "than quick sort on skewed data."
             )
         );
         
         tabs.addTab("Seq Search Report",
             new ReportPanel(
                 "sequentialsearch_results.csv",
                 "Sequential Search",
                 "Sequential search checks each element one by one until the value is found " //description
                 + "or the array ends. Best case is O(1); worst case is O(n).",
                 "Comparisons directly reflect the position of the target value. " //analysis
                 + "Results will vary widely across runs depending on where (or whether) "
                 + "the search key appears."
             )
         );
         
         tabs.addTab("Iterative Bin Search Report",
             new ReportPanel(
                 "binarysearchiterative_results.csv",
                 "Binary Search",
                 "Binary search repeatedly halves the search range by comparing the target " //description
                 + "to the midpoint. Requires a sorted array. Time complexity is O(log n).",
                 "Comparisons should grow logarithmically with input size. " //analysis
                 + "The graph should rise slowly and steadily, forming a very shallow curve "
                 + "compared to all sorting algorithms."
             )
         );
         
         tabs.addTab("Recursive Bin Search Report",
             new ReportPanel(
                 "binarysearchrecursive_results.csv",
                 "Binary Search",
                 "Binary search repeatedly halves the search range by comparing the target " //description
                 + "to the midpoint. Requires a sorted array. Time complexity is O(log n).",
                 "Comparisons should grow logarithmically with input size. " //analysis
                 + "The graph should rise slowly and steadily, forming a very shallow curve "
                 + "compared to all sorting algorithms."
             )
         );

         
         
         

        setContentPane(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SortSearchGUI().setVisible(true);
            }
        });
    }

    static class ExperimentResult {
      //Initialize report variables
      String algorithm;
      String mode;
      int inputSize;
      int balance;
      Integer searchValue;
      Boolean found;
      Boolean duplicates;
      long timeMs;
      long comparisons;
      long swaps;
      long copies;
      
      
      String toCsvRow() {  //csv rows (report variables)
         return String.join(",",
                  algorithm,
                  String.valueOf(inputSize),
                  String.valueOf(balance),
                  mode,
                  (searchValue == null ? "" : searchValue.toString()),
                  (found == null ? "" : found.toString()),
                  (duplicates == null ? "" : duplicates.toString()),
                  String.valueOf(timeMs),
                  String.valueOf(comparisons),
                  String.valueOf(swaps),
                  String.valueOf(copies)
         );
      }
      
      static String csvHeader() {
         return "algorithm,inputSize,balance,mode,searchValue,found,duplicates,timeMs,comparisons,swaps,copies";
      }
      
   }
   
   //main menu layout
   
   static class MainMenuPanel extends JPanel {
      
      private JTextField inputSizeField;
      private JCheckBox allowDuplicatesCheck;
      private JSlider balanceSlider;
      
      private JButton generateButton;
      private JButton loadInputButton;
      private JButton saveInputButton;
      
      private JTextArea currentArrayArea;
      private JComboBox<String> dataOrderBox;
      private JComboBox<String> algorithmBox;
      private JRadioButton sortRadio;
      private JRadioButton searchRadio;
      private JTextField searchValueField;
      private JButton runButton;
      
      private JLabel comparisonsLabel;
      private JLabel swapsLabel;
      private JLabel copiesLabel;
      private JLabel timeLabel;
      
      private int[] currentArray;
      
      public MainMenuPanel() {
         setLayout(new BorderLayout());
         
         //top section w/ 3 rows
         JPanel top = new JPanel(new GridLayout(3,1));
         
         //row1
         JPanel row1 = new JPanel();
         row1.add(new JLabel("Input Size:"));
         inputSizeField = new JTextField("200",5);
         row1.add(inputSizeField);
         
         generateButton = new JButton("Generate");
         row1.add(generateButton);
         loadInputButton = new JButton("Load Input File");
         row1.add(loadInputButton);
         saveInputButton = new JButton("Save Input to File");
         row1.add(saveInputButton);
         
         allowDuplicatesCheck = new JCheckBox("Allow Duplicates", true);
         row1.add(allowDuplicatesCheck);
         
         row1.add(new JLabel("Balance (1=Left -> 5=Right"));
         balanceSlider = new JSlider();   // create an empty slider model
         balanceSlider.setMinimum(1);
         balanceSlider.setMaximum(5);
         balanceSlider.setValue(3);       // initial value between 1 and 5
         balanceSlider.setPaintTicks(true);
         balanceSlider.setPaintLabels(true);
         balanceSlider.setMajorTickSpacing(1);
         row1.add(balanceSlider);
         row1.add(new JLabel("Initial Order:"));
         dataOrderBox = new JComboBox<String>(new String[] {
            "Random (average case)",
            "Ascending (best for some)",
            "Descending (worst for some)"
         });
         row1.add(dataOrderBox);
         
         // row2
         JPanel row2 = new JPanel();
         algorithmBox = new JComboBox<String>(new String[] {
                 "Selection Sort",
                 "Bubble Sort",
                 "Optimized Bubble Sort",
                 "Insertion Sort",
                 "Merge Sort",
                 "Quick Sort (Lomuto)",
                 "Heap Sort",
                 "Sequential Search",
                 "Binary Search (Iterative)",
                 "Binary Search (Recursive)"
         });
         row2.add(new JLabel("Algorithm:"));
         row2.add(algorithmBox);
         
         ButtonGroup group = new ButtonGroup();
         sortRadio = new JRadioButton("Sort", true);
         searchRadio = new JRadioButton("Search");
         group.add(sortRadio);
         group.add(searchRadio);
         row2.add(sortRadio);
         row2.add(searchRadio);
         row2.add(new JLabel("Search Value:"));
         searchValueField = new JTextField(5);
         row2.add(searchValueField);
         
         //row3
         JPanel row3 = new JPanel();
         
         runButton= new JButton("Run");
         row3.add(runButton);
         comparisonsLabel = new JLabel("Comparisons: -");
         row3.add(comparisonsLabel);
         swapsLabel = new JLabel("Swaps: -");
         row3.add(swapsLabel);
         copiesLabel = new JLabel("Copies: -");
         row3.add(copiesLabel);
         timeLabel = new JLabel("Time: -");
         row3.add(timeLabel);
         
         //put together
         top.add(row1);
         top.add(row2);
         top.add(row3);
         
         //middle (text area w/ current array)
         currentArrayArea = new JTextArea(8,80);
         currentArrayArea.setEditable(false);
         JScrollPane scroll = new JScrollPane(currentArrayArea);
         
         add(top, BorderLayout.NORTH);
         add(scroll, BorderLayout.CENTER);
         
         //button actions
         generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
               onGenerate();
            }
         });
         
         saveInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
               onSaveInput();
            }
         });

         loadInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
               onLoadInput();
            }
         });

         runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
               onRun();
            }
         });
         
      }
      
      //show potential error messages
      private void showError(String msg) {
         JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
      }
      

        private void onGenerate() {
            try {
                int n = Integer.parseInt(inputSizeField.getText().trim());
                if (n <= 0) {
                    showError("Input size must be positive.");
                    return;
                }
                boolean allowDup = allowDuplicatesCheck.isSelected();
                int balance = balanceSlider.getValue(); // 1–5
   
                currentArray = generateCustomArray(n, 0, n, allowDup, balance);
                
                // Apply initial order for best/avg/worst cases
                String order = (String) dataOrderBox.getSelectedItem();
                if ("Ascending (best for some)".equals(order)) {
                  java.util.Arrays.sort(currentArray);
                } else if ("Descending (worst for some)".equals(order)) {
                  java.util.Arrays.sort(currentArray);
                  //reverse
                  for (int i = 0; i < currentArray.length / 2; i++) {
                     int tmp = currentArray[i];
                     currentArray[i] = currentArray[currentArray.length - 1 - i];
                     currentArray[currentArray.length - 1 - i] = tmp;
                  }
                } //else "random" = leave as is
                
                showArray(currentArray);
                
            } catch (NumberFormatException ex) {
                showError("Invalid input size.");
            }
        }
   
        private int[] generateCustomArray(int n, int min, int max,
                                          boolean allowDuplicates, int balanceLevel) {
            java.util.Random rand = new java.util.Random();
            int[] arr = new int[n];
   
            // map balanceLevel 1–5 to exponent and direction
            boolean skewRight = false;
            double exponent;
   
            switch (balanceLevel) {
                case 1: // strong left skew
                    exponent = 3.0;
                    skewRight = false;
                    break;
                case 2:
                    exponent = 1.7;
                    skewRight = false;
                    break;
                case 3: // balanced
                    exponent = 1.0;
                    skewRight = false;
                    break;
                case 4:
                    exponent = 1.7;
                    skewRight = true;
                    break;
                case 5: // strong right skew
                    exponent = 3.0;
                    skewRight = true;
                    break;
                default:
                    exponent = 1.0;
            }
   
            if (allowDuplicates) {
                for (int i = 0; i < n; i++) {
                    double u = rand.nextDouble(); // 0..1
                    double u2 = Math.pow(u, exponent);
   
                    double scaled;
                    if (balanceLevel == 3) {
                        scaled = u;               // uniform
                    } else if (!skewRight) {
                        scaled = u2;              // left skew (more small)
                    } else {
                        scaled = 1.0 - u2;        // right skew (more large)
                    }
   
                    int value = min + (int)(scaled * (max - min + 1));
                    if (value > max) value = max;
                    arr[i] = value;
                }
            } else {
                // no duplicates: simple but not super efficient approach
                int rangeSize = max - min + 1;
                if (n > rangeSize) {
                    n = rangeSize;
                }
   
                boolean[] used = new boolean[rangeSize];
                int count = 0;
                while (count < n) {
                    double u = rand.nextDouble();
                    double u2 = Math.pow(u, exponent);
   
                    double scaled;
                    if (balanceLevel == 3) {
                        scaled = u;
                    } else if (!skewRight) {
                        scaled = u2;
                    } else {
                        scaled = 1.0 - u2;
                    }
   
                    int idx = (int)(scaled * rangeSize);
                    if (idx >= rangeSize) idx = rangeSize - 1;
                    if (!used[idx]) {
                        used[idx] = true;
                        arr[count] = min + idx;
                        count++;
                    }
                }
            }
            return arr;
        }
   
        private void showArray(int[] arr) {
            if (arr == null) {
                currentArrayArea.setText("");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int x : arr) {
                sb.append(x).append(" ");
            }
            currentArrayArea.setText(sb.toString());
        }
   
        private void onSaveInput() {
            if (currentArray == null) {
                showError("No array to save.");
                return;
            }
            JFileChooser fc = new JFileChooser();
            int res = fc.showSaveDialog(this);
            if (res == JFileChooser.APPROVE_OPTION) {
                java.io.File file = fc.getSelectedFile();
                try {
                    saveArrayToFile(currentArray, file);
                } catch (Exception ex) {
                    showError("Error saving file: " + ex.getMessage());
                }
            }
        }
   
        private void onLoadInput() {
            JFileChooser fc = new JFileChooser();
            int res = fc.showOpenDialog(this);
            if (res == JFileChooser.APPROVE_OPTION) {
                java.io.File file = fc.getSelectedFile();
                try {
                    currentArray = loadArrayFromFile(file);
                    showArray(currentArray);
                } catch (Exception ex) {
                    showError("Error loading file: " + ex.getMessage());
                }
            }
        }
   
        private void saveArrayToFile(int[] arr, java.io.File file) throws java.io.IOException {
            java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(file));
            for (int i = 0; i < arr.length; i++) {
                pw.print(arr[i]);
                if (i < arr.length - 1) pw.print(" ");
            }
            pw.close();
        }
   
        private int[] loadArrayFromFile(java.io.File file) throws java.io.IOException {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file));
            String line = br.readLine();
            br.close();
            if (line == null || line.trim().isEmpty()) {
                return new int[0];
            }
            String[] parts = line.trim().split("\\s+");
            int[] arr = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                arr[i] = Integer.parseInt(parts[i]);
            }
            return arr;
        }
         
        private void onRun() {
            if (currentArray == null) {
                showError("Generate or load an array first.");
                return;
            }

            String algo = (String) algorithmBox.getSelectedItem();
            boolean isSortMode = sortRadio.isSelected();

            // Work on a copy so original can be reused
            int[] working = currentArray.clone();

            ExperimentResult result = new ExperimentResult();
            result.algorithm = algo;
            result.mode = isSortMode ? "sort" : "search";
            result.inputSize = working.length;
            result.balance = balanceSlider.getValue();
            result.duplicates = allowDuplicatesCheck.isSelected();


            Integer key = null;
            if (!isSortMode) {
                try {
                    key = Integer.parseInt(searchValueField.getText().trim());
                } catch (Exception ex) {
                    showError("Enter a valid search value.");
                    return;
                }
            }
            result.searchValue = key;

            long start = System.currentTimeMillis();

            if (isSortMode) {
                runSortAlgorithm(algo, working, result);
            } else {
                runSearchAlgorithm(algo, working, key.intValue(), result);
            }

            long end = System.currentTimeMillis();
            result.timeMs = end - start;

            // Update labels
            comparisonsLabel.setText("Comparisons: " + result.comparisons);
            swapsLabel.setText("Swaps: " + result.swaps);
            copiesLabel.setText("Copies: " + result.copies);
            timeLabel.setText("Time (ms): " + result.timeMs);

            // Save output array
            saveOutputArray(algo, isSortMode, working);

            // Append CSV
            appendResultToCsv(result, algo);

            // Show resulting array
            showArray(working);
        }

        private void runSortAlgorithm(String algo, int[] A, ExperimentResult result) {
            long[] stats;
            if ("Selection Sort".equals(algo)) {
                stats = SortSearchAnalysis.SelectionSort(A);
                result.comparisons = stats[0];
                result.swaps = stats[1];
            } else if ("Bubble Sort".equals(algo)) {
                stats = SortSearchAnalysis.BubbleSort(A);
                result.comparisons = stats[0];
                result.swaps = stats[1];
            } else if ("Optimized Bubble Sort".equals(algo)) {
                stats = SortSearchAnalysis.BubbleSortOptimized(A);
                result.comparisons = stats[0];
                result.swaps = stats[1];
            } else if ("Insertion Sort".equals(algo)) {
                stats = SortSearchAnalysis.InsertionSort(A);
                result.comparisons = stats[0];
                result.swaps = stats[1];
            } else if ("Merge Sort".equals(algo)) {
                stats = SortSearchAnalysis.MergeSort(A);
                result.comparisons = stats[0];
                result.copies = stats[1];
            } else if ("Quick Sort (Lomuto)".equals(algo)) {
                stats = SortSearchAnalysis.QuickSort(A, 0, A.length - 1);
                // stats[0] is pivot index at top level, ignore
                result.comparisons = stats[1];
                result.swaps = stats[2];
            } else if ("Heap Sort".equals(algo)) {
                stats = SortSearchAnalysis.HeapSort(A);
                result.comparisons = stats[0];
                result.swaps = stats[1];
            } else {
                showError("Sort mode not supported for this algorithm.");
            }
        }

        private void runSearchAlgorithm(String algo, int[] A, int key, ExperimentResult result) {
            long[] stats;
            boolean found = false;

            if ("Sequential Search".equals(algo)) {
                stats = SortSearchAnalysis.SequentialSearch(A, key);
                result.comparisons = stats[0];
                found = (stats[1] != -1);
            } else if ("Binary Search (Iterative)".equals(algo)) {
                // must sort first
                SortSearchAnalysis.MergeSort(A);
                stats = SortSearchAnalysis.BinarySearchIterative(A, key);
                result.comparisons = stats[0];
                found = (stats[1] != -1);
            } else if ("Binary Search (Recursive)".equals(algo)) {
                SortSearchAnalysis.MergeSort(A);
                stats = SortSearchAnalysis.BinarySearchRecursive(A, key, 0, A.length - 1);
                result.comparisons = stats[0];
                found = (stats[1] != -1);
            } else {
                showError("Search mode only makes sense for search algorithms.");
            }
            result.found = found;
        }

        private void saveOutputArray(String algo, boolean isSort, int[] arr) {
            try {
                String base = algo.replace(" ", "")
                        .replace("(", "")
                        .replace(")", "")
                        .toLowerCase();
                String suffix = isSort ? "_sorted.txt" : "_after_search.txt";
                java.io.File file = new java.io.File(base + suffix);
                saveArrayToFile(arr, file);
            } catch (Exception ex) {
                showError("Could not save output array: " + ex.getMessage());
            }
        }

        private void appendResultToCsv(ExperimentResult result, String algo) {
            try {
                String base = algo.replace(" ", "")
                        .replace("(", "")
                        .replace(")", "")
                        .toLowerCase();
                java.io.File csv = new java.io.File(base + "_results.csv");
                boolean newFile = !csv.exists();
                java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(csv, true));
                if (newFile) {
                    pw.println(ExperimentResult.csvHeader());
                }
                pw.println(result.toCsvRow());
                pw.close();
            } catch (Exception ex) {
                showError("Could not write CSV: " + ex.getMessage());
            }
        }
 }
 
    
    // Report panel (1 per algorithm)
    
    static class ReportPanel extends JPanel {

        private String csvFileName;
        private String algorithmName;
        
        private String descriptionText;
        private String analysisText;
        
        private JTable table;
        private SimpleChart chart;

          public ReportPanel(String csvFileName, String algorithmName, String descriptionText, String analysisText) {
              this.csvFileName = csvFileName;
              this.algorithmName = algorithmName;
              this.descriptionText = descriptionText;
              this.analysisText = analysisText;
      
              setLayout(new BorderLayout());
      
              //top bar
              JPanel top = new JPanel(new BorderLayout());
              top.add(new JLabel(algorithmName + " Report"), BorderLayout.WEST);
      
              JButton reloadButton = new JButton("Reload");
              JButton infoButton = new JButton("Algorithm Info");
              JButton analysisButton = new JButton("View Analysis");
      
              JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
              buttonPanel.add(reloadButton);
              buttonPanel.add(infoButton);
              buttonPanel.add(analysisButton);
      
              top.add(buttonPanel, BorderLayout.EAST);
      
              table = new JTable();
              chart = new SimpleChart();
      
              add(top, BorderLayout.NORTH);
              add(new JScrollPane(table), BorderLayout.CENTER);
              add(chart, BorderLayout.SOUTH);
      
              // Button listeners
              reloadButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent e) {
                      loadData();
                  }
              });
      
              infoButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent e) {
                      showAlgorithmDescription();
                  }
              });
      
              analysisButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent e) {
                      showAnalysis();
                  }
              });
      
              loadData();
          }
          
             private void showAlgorithmDescription() {
                 if (descriptionText == null || descriptionText.trim().isEmpty()) {
                     JOptionPane.showMessageDialog(
                             this,
                             "No description has been provided yet.",
                             algorithmName + " Description",
                             JOptionPane.INFORMATION_MESSAGE
                     );
                 } else {
                     JOptionPane.showMessageDialog(
                             this,
                             descriptionText,
                             algorithmName + " Description",
                             JOptionPane.INFORMATION_MESSAGE
                     );
                 }
             }
         
             private void showAnalysis() {
                 if (analysisText == null || analysisText.trim().isEmpty()) {
                     JOptionPane.showMessageDialog(
                             this,
                             "No analysis has been provided yet.",
                             algorithmName + " Analysis",
                             JOptionPane.INFORMATION_MESSAGE
                     );
                 } else {
                     JOptionPane.showMessageDialog(
                             this,
                             analysisText,
                             algorithmName + " Analysis",
                             JOptionPane.INFORMATION_MESSAGE
                     );
                 }
             }

        private void loadData() {
            java.util.List<String[]> rows = new java.util.ArrayList<String[]>();
            String[] header = null;

            java.io.File csv = new java.io.File(csvFileName);
            if (!csv.exists()) {
                table.setModel(new javax.swing.table.DefaultTableModel());
                chart.setData(new int[0], new long[0]);
                return;
            }

            try {
                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(csv));
                String line = br.readLine();
                if (line == null) {
                    br.close();
                    return;
                }
                header = line.split(",");
                while ((line = br.readLine()) != null) {
                    rows.add(line.split(","));
                }
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String[][] data = rows.toArray(new String[0][]);
            table.setModel(new javax.swing.table.DefaultTableModel(data, header));

            java.util.List<Integer> xList = new java.util.ArrayList<Integer>();
            java.util.List<Long> yList = new java.util.ArrayList<Long>();

             for (String[] r : rows) {
                 try {
                     int inputSize = Integer.parseInt(r[1]);
         
                     // comparisons may be larger than Integer.MAX_VALUE, so parse as long
                     long comparisonsLong = Long.parseLong(r[8]);
         
                     
                     if (comparisonsLong < 0) {
                        comparisonsLong = 0;
                     }
         
                     xList.add(inputSize);
                     yList.add(comparisonsLong);
                 } catch (Exception ex) {
                     // ignore parse errors
                 }
             }



            int[] xs = new int[xList.size()];
            long[] ys = new long[yList.size()];
            for (int i = 0; i < xList.size(); i++) {
                xs[i] = xList.get(i);
                ys[i] = yList.get(i);
            }
            chart.setData(xs, ys);
        }
    }

    static class SimpleChart extends JPanel {

    private int[] xs = new int[0]; // input sizes
    private long[] ys = new long[0]; // comparisons

    public void setData(int[] xs, long[] ys) {
        this.xs = xs;
        this.ys = ys;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        // Bigger chart area
        return new Dimension(1200, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (xs == null || ys == null || xs.length == 0 || ys.length == 0 || xs.length != ys.length) {
            g.drawString("No data yet. Run experiments from Main Menu.", 10, 20);
            return;
        }

        int n = xs.length;

        // Copy and sort data by x so we draw left -> right 
        int[] sx = new int[n];
        long[] sy = new long[n];
        for (int i = 0; i < n; i++) {
            sx[i] = xs[i];
            sy[i] = ys[i];
        }

        // Simple selection sort on sx, and keep sy in sync
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (sx[j] < sx[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmpX = sx[i];
                sx[i] = sx[minIndex];
                sx[minIndex] = tmpX;

                long tmpY = sy[i];
                sy[i] = sy[minIndex];
                sy[minIndex] = tmpY;
            }
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                            java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int padLeft = 70;
        int padRight = 40;
        int padTop = 30;
        int padBottom = 60;

        //  Find min/max for scale
        int minX = sx[0];
        int maxX = sx[0];
        long minY = sy[0];
        long maxY = sy[0];
        for (int i = 1; i < n; i++) {
            if (sx[i] < minX) minX = sx[i];
            if (sx[i] > maxX) maxX = sx[i];
            if (sy[i] < minY) minY = sy[i];
            if (sy[i] > maxY) maxY = sy[i];
        }

        if (maxX == minX) maxX = minX + 1; // avoid divide by zero
        if (maxY == minY) maxY = minY + 1;

        int plotWidth = w - padLeft - padRight;
        int plotHeight = h - padTop - padBottom;

        // Draw axes
        g2.setStroke(new BasicStroke(2));
        // X axis
        int xAxisY = h - padBottom;
        g2.drawLine(padLeft, xAxisY, w - padRight, xAxisY);
        // Y axis
        int yAxisX = padLeft;
        g2.drawLine(yAxisX, padTop, yAxisX, h - padBottom);

        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Draw ticks + labels on X axis (input size)
        int numXTicks = 5;
        for (int i = 0; i <= numXTicks; i++) {
            double t = i / (double) numXTicks;
            int xVal = (int) (minX + t * (maxX - minX));
            int xPos = padLeft + (int) (t * plotWidth);

            // tick
            g2.drawLine(xPos, xAxisY, xPos, xAxisY + 5);
            // label
            String label = String.valueOf(xVal);
            int labelWidth = g2.getFontMetrics().stringWidth(label);
            g2.drawString(label, xPos - labelWidth / 2, xAxisY + 20);
        }

        // -Draw ticks + labels on Y axis (comparisons)
        int numYTicks = 5;
        for (int i = 0; i <= numYTicks; i++) {
            double t = i / (double) numYTicks;
            long yVal = (long) (minY + (1.0 - t) * (maxY - minY)); // top = max
            int yPos = padTop + (int) (t * plotHeight);

            // tick
            g2.drawLine(yAxisX - 5, yPos, yAxisX, yPos);
            // label
            String label = String.valueOf(yVal);
            int labelWidth = g2.getFontMetrics().stringWidth(label);
            g2.drawString(label, yAxisX - 10 - labelWidth, yPos + 4);
        }

        // Draw data: line + points (sorted left-to-right)
        g2.setStroke(new BasicStroke(2));

        int prevPX = -1;
        int prevPY = -1;

        for (int i = 0; i < n; i++) {
            double xNorm = (sx[i] - minX) / (double) (maxX - minX);
            double yNorm = (sy[i] - minY) / (double) (maxY - minY);

            int px = padLeft + (int) (xNorm * plotWidth);
            int py = xAxisY - (int) (yNorm * plotHeight); // invert y for screen coordinates

            // line from previous point
            if (i > 0) {
                g2.drawLine(prevPX, prevPY, px, py);
            }

            // point
            g2.fillOval(px - 4, py - 4, 8, 8);

            prevPX = px;
            prevPY = py;
        }

        //7. Axis labels 
        g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        g2.drawString("Input size", padLeft + plotWidth / 2 - 40, h - 25);
        g2.drawString("Comparisons", 10, padTop - 10);
    }
}

        
         
         
        
        
   
}
