//
// Created by 2001i on 13/6/2022.
//

#ifndef BREAKOUT_DBL_H
#define BREAKOUT_DBL_H

#endif //BREAKOUT_DBL_H

/* Macro for iterating over an array or object */
/**
 * Original code for macro By Dave Gamble, Max Bruckner and Alan Wang, under MIT license in CJSON library.\n
 * element void* : pointer to storage value\n
 * LL struct head* :head of linked list\n
 * Node struct node* : actual node pointer\n
 */
#define ForEachDLL(node, LL) for(node = (LL != NULL) ? LL->start : NULL; node != NULL; node = node->next)

#define cJSON_ArrayForEach(element, array) for(element = (array != NULL) ? (array)->child : NULL; element != NULL; element = element->next)


struct node {
    struct node *next;
    struct node *previous;
    void *value;
};

struct head {
    struct node *start;
    struct node *last;
    int quantity;
};

/**
 * Insert a node at the start, dont reserved memory for the storage value
 * @param pointer void pointer to the value to storage
 */
void push(struct head*, void *pointer);

/** delete a node by searching it value
 *
 * @param pointer a void pointer, dont deallocate memory for the storage value
 * @return void pointer to the value contained in the node, Null if not founded
 */
void *delete_by_value(struct head*, void *pointer);

/**
 * Delete a node by searching the node, dont deallocate memory for the storage value
 * @param pointer a node pointer
 * @return void pointer to the value contained in the node, Null if not founded
 */
void *delete_by_node(struct head*, struct node *pointer);


void *delete_node(struct head*, struct node *pointer);

struct head *DLL_test();
