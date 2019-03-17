[List 1 (One way lists)](README.md#list-1)\
[List 2 (Sorting algorithms)](README.md#list-2)

# List 1 
**Implementation of one way list in C along with comparisons count**

# List 2 
**Implementation of sorting algorithms (insertion, select, heap, quick) in Java.**\
Additional hybrid sorting algorythm that sorts as quick sort for array size down to 16 and insertion sort for smaller arrays *(dynamically)*.\
Program should be run with additional parameters:
* **--type** *<insert/select/quick/heap/mquick>* - asks for elements and sorts them using chosen algorithm
* **--asc** - sorts elements in ascending order
* **--desc** - sorts elements in descending order
* **--stat** *<file_path> <n>* - runs test: 
    1. Creates arrays of *size = {100, 200, 300 ... 9900, 10000}*
    2. Sorts them with every algorithm gathering data about number of comparisons and swaps between elements as well as time of execution. 
    3. Repeats steps 1-2 *n* times.
    4. Prints results to a given file.
