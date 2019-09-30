
     H DFTACTGRP(*NO) ACTGRP(*NEW)

      * Linked list node DS
     D  node           S             10I 0

      * Linked list DS
     D listDS          DS                  QUALIFIED
     D  memorySize                   10I 0 inz(4)                               Size of list
     D  length                       10I 0 inz(0)                               Number of elements
     D  pHead                          *

     D pCurrNode       S               *                                        Ptr to curr node
     D bCurrNode       S                   like(node) based(pCurrNode)          Curr node field

      * Returns node on given index (idx starts from 1 not 0)
     D getNode         PR                  like(node)
     D  list                               likeds(listDS)
     D  idx                          10I 0 VALUE

      * Deletes first element with given value
     D delNode         PR
     D  list                               likeds(listDS)
     D  val                          10I 0 VALUE

      * Deletes all elements with given value
     D delAllNodes     PR
     D  list                               likeds(listDS)
     D  val                          10I 0 VALUE

      * Adds element to list
     D addNode         PR
     D  list                               likeds(listDS)
     D  val                          10I 0 VALUE

      * Checks if list contain given value (if does returns it's idx if not returns 0)
     D contains        PR            10I 0
     D  list                               likeds(listDS)
     D  val                          10I 0 VALUE

      * Clears list
     D clearList       PR
     D  list                               likeds(listDS)

      * Reallocates memory accordingly
     D reallocate      PR
     D  list                               likeds(listDS)

     D count           S              5I 0 inz(1)

     D myList          DS                  likeds(listDS)

      /Free
       // Allocate memory for default array size and current node pointer
       pCurrNode = %ALLOC(%SIZE(node));


       // TEST RUN
       dsply 'Clearing';
       clearList(myList);

       dsply 'Inserting numbers from 1 to 10';
       FOR count = 1 by 1 to 10;
         addNode(myList:count);
       ENDFOR;

       dsply ('Size :'+%CHAR(myList.memorySize)+
                ', ElemNum :'+%CHAR(myList.length));

       dsply 'Delete:';
       dsply '4';
       delNode(myList:4);
       dsply '8';
       delNode(myList:8);
       dsply '1';
       delNode(myList:1);
       dsply '10';
       delNode(myList:10);

       dsply 'Inserting 5, 0 and 5';
       addNode(myList:5);
       addNode(myList:0);
       addNode(myList:5);

       dsply 'Delete all 5';
       delAllNodes(myList:5);

       dsply 'Reading All';
       FOR count = 1 by 1 to myList.length;
         dsply %CHAR(getNode(myList:count));
       ENDFOR;

       dsply ('Size: '+%CHAR(myList.memorySize)+
               ', ElemNum: '+%CHAR(myList.length));

       dsply 'Contains:';
       dsply ('2 - '+%CHAR(contains(myList:2)));
       dsply ('3 - '+%CHAR(contains(myList:3)));
       dsply ('9 - '+%CHAR(contains(myList:9)));
       dsply ('10 - '+%CHAR(contains(myList:10)));

       *inlr = *on;
      /End-Free

      * GET NODE PROCEDURE
     P getNode         B
     D getNode         PI                  like(node)
     D  list                               likeds(listDS)
     D  idx                          10I 0 VALUE
      /Free
       IF idx>list.length OR idx<1;
         dsply ('Idx '+%CHAR(idx)+' out of bounds. Size: '+%CHAR(list.length));
       ENDIF;
       pCurrNode = list.pHead + ((idx - 1) * %SIZE(node));
       return bCurrNode;
      /End-Free
     P getNode         E

      * DELETE ELEMENT PROCEDURE
     P delNode         B
     D delNode         PI
     D  list                               likeds(listDS)
     D  val                          10I 0 VALUE
     D movedNode       S                   like(node)
     D tmp             S                   like(node)
     D pDelNode        S               *

      /Free
        pCurrNode = list.pHead;

        // Find node to delete
        DOW NOT (bCurrNode = val);
          pCurrNode += %SIZE(node);
          IF ((pCurrNode - list.pHead)/%SIZE(node)) > list.length;
            dsply ('No node of value ' + %CHAR(val));
            RETURN;
          ENDIF;
        ENDDO;

        // Remove node and shift array left
        pDelNode = pCurrNode;
        pCurrNode = list.pHead + (%SIZE(node) * list.length);
        movedNode = bCurrNode;
        DOW pCurrNode > pDelNode;
          pCurrNode -= %SIZE(node);
          tmp = bCurrNode;
          bCurrNode = movedNode;
          movedNode = tmp;
        ENDDO;

        list.length -= 1;
        reallocate(list);
      /End-Free
     P delNode         E

      * DELETE ALL ELEMENTS PROCEDURE
     P delAllNodes     B
     D delAllNodes     PI
     D  list                               likeds(listDS)
     D  val                          10I 0 VALUE
     D movedNode       S                   like(node)
     D tmp             S                   like(node)
     D pDelNode        S               *

      /Free
       pCurrNode = list.pHead;
       DOW 1=1;
        // Find node to delete
        DOW NOT (bCurrNode = val);
          pCurrNode += %SIZE(node);
          IF ((pCurrNode - list.pHead)/%SIZE(node)) > list.length;
            reallocate(list);
            RETURN;
          ENDIF;
        ENDDO;

        // Remove node and shift array left
        pDelNode = pCurrNode;
        pCurrNode = list.pHead + (%SIZE(node) * list.length);
        movedNode = bCurrNode;
        DOW pCurrNode > pDelNode;
          pCurrNode -= %SIZE(node);
          tmp = bCurrNode;
          bCurrNode = movedNode;
          movedNode = tmp;
        ENDDO;

        list.length -= 1;
       ENDDO;
      /End-Free
     P delAllNodes     E

      * ADD PROCEDURE
     P addNode         B
     D addNode         PI
     D  list                               likeds(listDS)
     D  val                          10I 0 VALUE
      /Free
       reallocate(list);

       pCurrNode = list.pHead + (list.length * %SIZE(node));
       bCurrNode = val;

       list.length += 1;
      /End-Free
     P addNode         E

      * CONTAINS PROCEDURE
     P contains        B
     D contains        PI            10I 0
     D  list                               likeds(listDS)
     D  val                          10I 0 VALUE
       /Free
         pCurrNode = list.pHead;
         DOW ((pCurrNode - list.pHead) / %SIZE(node)) < list.length;
           IF bCurrNode = val;
             RETURN 1 + ((pCurrNode - list.pHead) / %SIZE(node));
           ENDIF;
           pCurrNode += %SIZE(node);
         ENDDO;

         RETURN 0;
       /End-Free
     P contains        E

      * REALLOCATE PROCEDURE
     P reallocate      B
     D reallocate      PI
     D  list                               likeds(listDS)
      /Free
        // Reallocate memory if needed
        IF list.length >= list.memorySize;
          // Reallocate up
          list.pHead = %REALLOC(list.pHead : (%SIZE(node) *
                                  list.memorySize * 2));
          list.memorySize = list.memorySize * 2;
        ELSEIF list.length < %DIV(list.memorySize : 2) AND list.memorySize > 4;
          // Reallocate down
          list.pHead = %REALLOC(list.pHead : %DIV((%SIZE(node) *
                                  list.memorySize):2));
          list.memorySize = list.memorySize / 2;
        ELSEIF list.length = 0;
          // Initial alloc
          list.pHead = %ALLOC(%SIZE(node) * list.memorySize);
        ENDIF;
      /End-Free
     P reallocate      E

      * CLEAR PROCEDURE
     P clearList       B
     D clearList       PI
     D  list                               likeds(listDS)
      /Free
        list.memorySize = 4;
        list.length = 0;
        reallocate(list);
      /End-Free
     P clearList       E
 