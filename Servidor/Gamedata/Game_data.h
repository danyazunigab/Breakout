//
// Created by 2001i on 7/6/2022.
//

#include "../Consts.h"
#include "../Double linked list/DLL.h"
#ifndef CLIENT_GAME_DATA_H
#define CLIENT_GAME_DATA_H

#endif //CLIENT_GAME_DATA_H

struct bricks {
    int state;/**< int that indicate the type of the brick */
    int value;/**< int that indicate the type of the brick */
    int left;/**< int that indicate the left position of the brick */
    int rigth;/**< int that indicate the rigth position of the brick */
    int top;/**< int that indicate the top position of the brick */
    int down;/**< int that indicate the down position of the brick */
};

struct paddle{
    int left;
    int rigth;
};
struct ball{
    int x;
    int y;
    float vx;
    float vy;
    float factor;
};

struct gamedata{
    struct bricks  **blocks;
    struct paddle paddle;

    struct head *balls;

    int lifes;
    double points;

    char *msg;
};

struct gamedata Init_gamedata(void);
void reset_blocks(struct gamedata*);
struct bricks** create_blocks();
struct ball * create_ball();
void reset_ball(struct ball*);