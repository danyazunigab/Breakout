//
// Created by 2001i on 7/6/2022.
//

#include <stdlib.h>
#include <math.h>
#include <stdio.h>
#include <string.h>
#include "Game_data.h"


struct gamedata Init_gamedata(void) {
    ball_vel=1;
    struct paddle paddle = {300, 450};
    struct head *balls = malloc(sizeof(struct head));
    DLL_zero(balls);
    struct ball *ball = create_ball();
    push(balls, ball);

    struct gamedata data = {
            create_blocks(),//Keep in mind that this assign memory space, to avoid memory leak reuse the struct with reset_block
            paddle, balls, 1, 0, ""
    };
    return data;
}


void reset_blocks(struct gamedata *data) {
    for (int i = 0; i < ROWS; ++i) {
        for (int j = 0; j < COL; ++j) {
            data->blocks[i][j].state = 0;
        }
    }
};

struct bricks **create_blocks() {
    struct bricks **blocks = malloc(ROWS * sizeof(struct bricks *));
    struct bricks *block = calloc(COL * ROWS, sizeof(struct bricks));
    for (int i = 0; i < ROWS; ++i) {
        blocks[i] = block + i * COL;
    }

    for (int i = 0; i < ROWS; ++i) {
        for (int j = 0; j < COL; ++j) {
            blocks[i][j].state = 0;
            blocks[i][j].left = 70 * j + 16;
            blocks[i][j].rigth = 70 * j + 16 + 68;
            blocks[i][j].top = 45 * i + 16;
            blocks[i][j].down = 45 * i + 16 + 43;
            blocks[i][j].value = 150 * floor(i / 2) + 200;
        }
    }
    return blocks;

};

struct ball *create_ball() {
    struct ball *ball = malloc(sizeof(struct ball));
    reset_ball(ball);
    return ball;
}

void reset_ball(struct ball *ball) {
    ball->y = 650;
    ball->x = 450;
    ball->vx = 1.2;
    ball->vy = -1.2;
    ball->factor = 2;
};
