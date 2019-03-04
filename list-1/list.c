#include <stdio.h>
#include <stdlib.h>
#include <string.h>

long long int comp = 0;
int optMethod = 0;
int optDelete = 0;

typedef struct Node{
  struct Node* next;      // Pointer to next node
  int key;                // Key of given node
} Node;

// Inserts new node at the beggining of list
void insert(Node* root, int key){
  Node* newNode = malloc(sizeof(Node));

  // Change first element with new node
  newNode->next = root->next;
  newNode->key = key;
  root->next = newNode;
}

// Deletes all the elements that have given key
void delete(Node* root, int key){
  Node* iterator = root;

  while(iterator->next != NULL){
    // Delete element if maches
    comp++;
    if(iterator->next->key == key){
      iterator->next = iterator->next->next;
      continue;
    }

    iterator = iterator->next;
  }
}

// Deletes first element of given key it encounters
void deleteSingle(Node* root, int key){
  Node* iterator = root;

  while(iterator->next != NULL){
    // Delete element if maches
    comp++;
    if(iterator->next->key == key){
      iterator->next = iterator->next->next;
      break;
    }

    iterator = iterator->next;
  }
}

// Prints list in order
void printList(Node* root){
  Node* temp = root;
  printf("ROOT ");

  while(temp->next != NULL){
    temp = temp->next;
    printf("-> %d ", temp->key);
  }

  printf("\n");
}

int isEmpty(Node* root){

  if(root->next == NULL){
    return 1;
  }else{
    return 0;
  }
}

// Moves all elements with key to the beggining
void findMTF(Node* root, int key){
  Node* iterator = root->next;

  while(iterator->next != NULL){
    // Move to beggining
    comp++;
    if(iterator->next->key == key){
      Node* nextNode = iterator->next->next;

      iterator->next->next = root->next;
      root->next = iterator->next;
      iterator->next = nextNode;
      continue;
    }

    iterator = iterator->next;
  }
}

// Moves all elements with key one place forward
void findTRANS(Node* root, int key){
  Node* iterator = root;

  while(iterator->next->next != NULL){
    // Move forward
    comp++;
    if(iterator->next->next->key == key){
      Node* swapNode = iterator->next;

      iterator->next = iterator->next->next;
      swapNode->next = swapNode->next->next;
      iterator->next->next = swapNode;
    }

    iterator = iterator->next;
  }
}

// Moves all elements with key to the beggining
void findMtfSingle(Node* root, int key){
  Node* iterator = root->next;

  while(iterator->next != NULL){
    // Move to beggining
    comp++;
    if(iterator->next->key == key){
      Node* nextNode = iterator->next->next;

      iterator->next->next = root->next;
      root->next = iterator->next;
      iterator->next = nextNode;
      break;
    }

    iterator = iterator->next;
  }
}

// Moves all elements with key one place forward
void findTransSingle(Node* root, int key){
  Node* iterator = root;

  while(iterator->next->next != NULL){
    // Move forward
    comp++;
    if(iterator->next->next->key == key){
      Node* swapNode = iterator->next;

      iterator->next = iterator->next->next;
      swapNode->next = swapNode->next->next;
      iterator->next->next = swapNode;
      break;
    }

    iterator = iterator->next;
  }
}

// Test for given functions; 0 -> mtf, 1 -> trans
void test(Node* root, int variant){
  comp = 0;

  // Initialize tab
  int* tab = malloc(sizeof(int)*100);
  for(int i=0; i<100; i++){
    tab[i] = 1;
  }

  // Insert elements in random order
  int count = 0;
  int r;
  while(count < 100){
    r = rand() % 100;
    if(tab[r] == 1){
      tab[r] = 0;
      count++;
      insert(root, r+1);
    }
  }


  // Delete elements till list is empty
  if(variant == 0){
    // Using findMTF
    for(int max=100; max>0; max--){
      for(int i=0; i<100; i++){
        // Decide how to search
        if(optMethod){
          findMtfSingle(root, i);
        }else{
          findMTF(root, i);
        }
      }

      // Decide how to delete
      if(optDelete){
        deleteSingle(root, max);
      }else{
        delete(root, max);
      }
    }
  }else if(variant == 1){
    // Using findTRANS
    for(int max=100; max>0; max--){
      for(int i=0; i<100; i++){
        // Decide how to search
        if(optMethod){
          findTransSingle(root, i);
        }else{
          findTRANS(root, i);
        }
      }

      // Decide how to delete
      if(optDelete){
        deleteSingle(root, max);
      }else{
        delete(root, max);
      }
    }
  }
}

int main(){
  Node* root = malloc(sizeof(Node));
  root->next = NULL;

  test(root, 0);
  int mtf = comp;

  test(root, 1);
  int trans = comp;

  printf("Number of comparisons for:\nMTF =\t%d\nTRANS =\t%d\n", mtf, trans);
  return 0;
}
