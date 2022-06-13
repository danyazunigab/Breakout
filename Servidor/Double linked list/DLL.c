//
// Created by 2001i on 13/6/2022.
//

#include "DLL.h"
#include <stdio.h>
#include <stdlib.h>

void push(struct head *DLL, void *pointer) {
    struct node *old = NULL;
    old = DLL->start;
    struct node *new = malloc(sizeof(struct node));
    new->value = pointer;
    new->previous = NULL;
    DLL->quantity++;

    if (old == NULL) {
        //New linked list
        new->next = NULL;
        DLL->start = new;
        DLL->last = new;
    } else {
        new->next = DLL->start;
        DLL->start->previous = new;
        DLL->start = new;
    }

};


void *delete_by_value(struct head *DLL, void *pointer) {
    void *element = NULL;
    struct node *actualnode;

    ForEachDLL(actualnode, DLL) {
        element = actualnode->value;
        if (element == pointer) {
            delete_node(DLL, actualnode);
            return element;
        }
    }
    return NULL;
};

void *delete_by_node(struct head *DLL, struct node *pointer) {
    struct node *actualnode;

    ForEachDLL(actualnode, DLL) {
        if (actualnode == pointer) {
            return delete_node(DLL, actualnode);
        }
    }
    return NULL;
};

void *delete_node(struct head *DLL, struct node *pointer) {
    if (DLL->start == DLL->last) {
        //unico elemento
        DLL->start = NULL;
        DLL->last = NULL;
    }
    if (DLL->start == pointer && pointer->next != NULL) {
        // primer elemento
        DLL->start = pointer->next;
        pointer->next->previous = NULL;
    }
    if (DLL->last == pointer && pointer->next != NULL) {
        //ultimo elemento
        DLL->last = pointer->previous;
        pointer->previous->next = NULL;
    }
    if (pointer->next != NULL && pointer->previous != NULL) {
        //elemento enmedio
        pointer->next->previous = pointer->previous;
        pointer->previous->next = pointer->next;
    }
    void *tmp = pointer->value;
    free(pointer);
    DLL->quantity--;

    return tmp;
}


struct head *DLL_test() {
    struct head *DLLpointer = malloc(sizeof(struct head));
    DLLpointer->start = NULL;
    DLLpointer->last = NULL;
    DLLpointer->quantity = 0;
    int *pointer = malloc(sizeof(int) * 3);
    for (int i = 0; i < 3; ++i) {
        pointer[i] = i;
        push(DLLpointer, pointer + i);
    }
    struct node *actualnode = NULL;
    delete_by_value(DLLpointer, pointer + 1);

    ForEachDLL(actualnode, DLLpointer) {
        printf("%i,", *(int *) actualnode->value);
    }
    return DLLpointer;
};

/**init to zero a head
 * to reuse first destroy all the node al liberate they space.
 * @param head DLL head
 */
void DLL_zero(struct head * head){
    head->start=NULL;
    head->last=NULL;
    head->quantity=0;
};