# Algorithms_project

Sorting algorithms benchmark project for university (Java, NetBeans).

This project compares the performance of several classic sorting algorithms on different input sizes and different types of arrays.  
We implemented all algorithms **by ourselves** based on the pseudocode from the course (no `Arrays.sort()` or other library sorting).

---

## 📌 Project Overview

We implemented and benchmarked the following sorting algorithms in Java:

- **Insertion Sort**
- **Merge Sort**
- **Heap Sort**
- **QuickSort** (pivot = last element)
- **Randomized QuickSort** (random pivot)

For each algorithm we measured the running time (in milliseconds) on different input sizes and dataset types, then analyzed the results in a short report and presentation.

---

## 📊 Dataset Types

Each algorithm is tested on **four** types of input arrays:

1. **Random Data**  
   - Random integers in the range **[1, 1,000,000]**.

2. **Sorted Data (Ascending)**  
   - Already sorted in ascending order: `1, 2, 3, ..., n`.

3. **Reverse-Sorted Data (Descending)**  
   - Sorted in descending order: `n, n-1, ..., 1`.

4. **Few Unique Values**  
   - Only **5 distinct values**: `{1, 2, 3, 4, 5}`, chosen randomly.

For every test we:

- Generate **one base array** for a given size and type.
- Make **copies** of this array so all algorithms sort **exactly the same data**.

---

## 📏 Input Sizes

We use the following array sizes:

- `n = 1,000`
- `n = 10,000`
- `n = 50,000`
- `n = 100,000`

These sizes allow us to see how the running time grows when `n` increases by a factor of 10.

---

## 🧠 Algorithms & Time Complexity

**Insertion Sort**
- Best case: Θ(n) (already sorted)
- Average case: Θ(n²)
- Worst case: Θ(n²)

**Merge Sort**
- Best / Average / Worst: Θ(n log n)
- Always splits and merges, independent of input order.

**Heap Sort**
- Best / Average / Worst: Θ(n log n)
- Uses a max-heap; in-place (O(1) extra memory).

**QuickSort (last-element pivot)**
- Best: Θ(n log n)
- Average: Θ(n log n)
- Worst: Θ(n²) on sorted / reverse-sorted input (very unbalanced partitions).

**Randomized QuickSort**
- Expected: Θ(n log n) with random pivots.
- Worst: Θ(n²) in theory, but probability is small.

---

## 📂 Project Structure

```text
Algorithms_project/
├─ nbproject/           # NetBeans project files
├─ src/
│  └─ sortingproject/
│     └─ SortingProject.java   # Main class with all algorithms & experiments
├─ build.xml
├─ manifest.mf
└─ .gitignore
