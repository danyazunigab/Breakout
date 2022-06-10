//
// Created by 2001i on 7/6/2022.
//

#include "Game_data.h"
#include "../Consts.h"
struct paddle{
    int left;
    int rigth;
};
struct ball{
    int x;
    int y;
};

struct gamedata{
    int blocks[LINES][ROWS];
    struct paddle paddle;

//Hay que rescribir este como una linkedlist
    struct ball *ball;
    int ball_number;

    char *msg;
};