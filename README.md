# Sort Search GUI

## Purpose

This Program is a Java GUI that allows the user to:
- Generate integer arrays of specified lengths and distributions
- Run sorting and search algorithms on the arrays
- Measure and save analytcal data

Algorithms Included:
- Sorting: Selection, Bubble (+Optimized), Insertion, Merge, Quick, Heap
- Search: Sequential, Binary

## How to Compile and Run (via jGrasp)

Requirements:
- File 1: SortSearchGUI.java
- File 2: SortSearchAnalysis.java
- JGrasp
- Files 1 and 2 located within the same folder.

**Compiling Steps:**
1. Open jGrasp
2. Select File --> Open SortSearchGUI.java and SortSearchAnalysis.java
3. With SortSearchGUI.java active, click 'Run'
4. The *Sort/Search Analysis* window will appear

## How to Use the Program

### 1. Main Menu

**Top Row - Input Setings**
- Input Size: enter desired length of the array
- Generate: creates a new array using the settings below
- Load Input File: Load a previously saved array from a text file
- Save Input File: Save the current array to a text file (will be formatted as space-separated integers)
- Allow Duplicates: (Checked = Values May Repeat)
- Balance (1-5): Controls skew of random values
  - 1 = skewed heavily left
  - 3 = balanced
  - 5 = skewed heavily right
- Initial Order:
  - Random
  - Ascending
  - Descending

**Second Row - Algorithm and Mode**
- Algorithm: pick one of the soring or searching algorithms
- Search Value: integer to look for (only for search algorithms)

**Third Row - Run and Results**
- Run
  - Clones the current array
  - Runs the selected algorithm in the selected mode
  - Measures time, comparions, swaps/copies
  - Shows the resulting array in the text area
  - Saves output array to a file in working directory
  - Appends results to '[algorithm]_results.csv' for that algorithm

## Report Tabs

Each algroithm has a Report tab.

Each report tab shows
- A table loaded from the corresponding CSV file
- A graph of input size v. number of comparisons
- Buttons
  - Reload (must be pressed after each run of selected algorithm)
  - Algorithm Info - a brief description of how the algorithm works
  - View Analysis - a high level explanation of what the graph should look like



