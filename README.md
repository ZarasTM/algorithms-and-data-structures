# Algorithms and data structures

[List 1 (One way lists)](README.md#list-1)\
[List 2 (Sorting algorithms)](README.md#list-2)\
[List 3 (Priority Queues and Graph algorithms)](README.md#list-3)\
[List 4 (Binary tree data structures)](README.md#list-4)

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
   
# List 3
**Implementation of priority queue as well as 4 graph algorithms**\
For help with running program add **-h** or **--help** option\
\
Pass one of the arguments to run program:
* **-prior** - to run priority queue program
* **-dijkstra** - to run dijkstra algorithm program
* **-mst** - to run min spanning tree program
    1. **-p** - to use Prim's algorithm
    2. **-k** - to use Kruskal's algorithm
* **-scc** - to run program finding strongly connected components in graph\
\
If you want to run test programs add **--test** or **-t** option

# List 4
**Implementation of tree data structures (Binary tree, Red-Black tree, Splay tree)**\
Program should be run with **./a.out --type \<t>** command\
\
Run with **t** defined as one of the options:
* **bst** - to run for binary search tree
* **rbt** - to run for red-black tree
* **splay** - to run for splay tree
\
*(Default value for t is rbt)*
\
\
As the input you can pass one of the arguments:
* **insert s** - to insert string **s** to the structure
* **delete s** - to delete string **s** from the structure
* **search s** - to search for the string **s** in the structure *(returns true or false depending on results)*
* **load f** - to load every string divided by white sign from a file **f** to a structure
* **inorder** - to print contents of the structure in order
\
\
*Times for the structure can be incorrect. To be fixed*
