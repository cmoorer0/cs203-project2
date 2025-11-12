# CS203 - Group Activity #2: Sorting and Searching Algorithms

**Course:** Computing and Algorithms III (CS203)  
**Instructor:** Dr. Jamal Alhiyafi  
**Term:** Fall 2025  
**Due Date:** Monday, December 8, 2025 @ 10:20 AM  
**Weight:** 15% of Final Grade  
**Group Size:** 1–2 Students  

---

## 📘 Overview

This project involves implementing, analyzing, and comparing multiple **sorting and searching algorithms**.  
Will gather all relevant algorithms into a single program with a **menu-driven interface**, allowing users to select algorithms, generate random input, and analyze performance under different conditions.

In addition to functional correctness, the project emphasizes **code readability**, **analysis**, and **documentation**.

---

## 🧩 Algorithms Covered

### Sorting Algorithms
- Selection Sort  
- Bubble Sort  
- Insertion Sort  
- Merge Sort  
- Quick Sort  
- Heap Sort (to be covered soon)

### Searching Algorithms
- Sequential (Linear) Search  
- Binary Search  

---

## 🧭 Program Requirements

The program should:

1. Display a **menu** for selecting between sorting and searching operations.  
2. Allow the **user to specify input size** and generate random arrays accordingly.  
3. Save the input array to a file (so it can be reused in multiple tests).  
4. Let users choose:
   - Which **algorithm** to run  
   - Whether to **sort** or **search**  
   - The **value** to search for (if applicable)  
5. Run the selected algorithm, displaying and storing the output results.
6. Measure and compare algorithm **performance** (best, average, and worst cases).
7. Record:
   - Number of basic operations  
   - Time elapsed for each algorithm  
   - Case type (random, ascending, descending)  
8. Save both **input and output values** to files.  
9. Generate **tables and graphs** showing algorithm comparisons.

---

## 📊 Required Report

Create a detailed report titled: YourLastNames-CS203-F25-ProjectReport.docx

The report must include:

- Tables showing results for different input sizes (`n`)
- Data for best, average, and worst cases  
- Graphs comparing performance  
- Analysis of:
  - Theoretical vs. experimental results  
  - Differences between algorithms  
  - Observed trends and efficiency  
- Screenshots or excerpts of program outputs  
- Discussion of findings  

---

## 💾 File & Folder Structure

The final submission should include:
YourLastNames-CS203-F25-Project/
│
├── README.txt # How to compile and run the code
├── YourLastNames-CS203-F25-ProjectReport.docx
├── *.java / *.py / *.cpp # Source code files
├── input/ # Input data files (for tests)
├── output/ # Output data files (results)
└── graphs/ # Charts and visuals for the report

Compress the folder into a `.zip` or `.rar` file:

Submit the archive via **Blackboard**.

---

## 🧠 Extra Credit Opportunities

For Extra Credit, I will Include:

- Input Customization:
    - Array Balance Scale
    - For Search: Sorted or Unsorted
    - Allow Duplicates?
- Algorithm Customization:
    - For QuickSort: Choose Partitioning
    - Optimize Bubblesort Option
- Interface
    - GUI.exe/Webpage
    - Add Report w/ Graphs to GUI
- Data Handling
    - Store metrics in an excel sheet that continues to be updated ( Generating and saving performance graphs dynamically  )
- Adaptive Sort Option: detect if data is nearly sorted and automatically pick the best sort method based on user question (do you care about space or time?). 



Contact **Dr. Alhiyafi** for approval and details before proceeding with extra-credit features.

---

## 🧩 Grading Criteria

| Category | Description |
|-----------|-------------|
| **Correctness** | Code compiles and runs without errors |
| **Readability** | Proper indentation, naming, spacing, and comments |
| **Documentation** | Includes detailed README and report |
| **Performance Analysis** | Contains tables, graphs, and analysis |
| **Presentation/Demo** | Short demo of the running project |
| **Extra Credit (Optional)** | Approved enhancements and analysis |

---

## 🧰 Additional Notes

- All code must include:
  - Your **name**, **course**, **term**, and **project title** at the top of each file.  
- The `README.txt` file must clearly explain:
  - How to compile and execute the program  
  - Any file dependencies or setup instructions  
- The project will be worked on across **four lab sessions**, with ongoing clarifications provided.

---

## ✍️ Example Header for Source Files

```java
/*
 * Author: John Doe
 * Course: CS203 - Computing and Algorithms III
 * Term: Fall 2025
 * Project: Group Activity #2
 */

