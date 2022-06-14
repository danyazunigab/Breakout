//
// Created by 2001i on 7/6/2022.
//

#include <stdlib.h>
#include <math.h>
#include "Game_data.h"


struct gamedata Init_gamedata(void) {
    struct paddle paddle = {300, 450,705};
    struct head *balls = malloc(sizeof(struct head));
    DLL_zero(balls);
    struct ball *ball = create_ball();
    push(balls,ball);

    struct gamedata data = {
            create_blocks(),//Keep in mind that this assign memory space, to avoid memory leak reuse the struct with reset_block
            paddle, balls, 1, 0, "pajuila"
    };
    return data;
}


void reset_blocks(struct gamedata *data) {
    for (int i = 0; i < LINES; ++i) {
        for (int j = 0; j < ROWS; ++j) {
            data->blocks[i][j].value = 0;
        }
    }
};

struct bricks **create_blocks() {
    struct bricks **blocks = malloc(ROWS * sizeof(struct bricks *));
    struct bricks *block = calloc(ROWS * LINES, sizeof(struct bricks));
    for (int i = 0; i < ROWS; ++i) {
        blocks[i] = block + i * LINES;
    }
    for (int i = 0; i < LINES; ++i) {
        for (int j = 0; j < ROWS; ++j) {
            blocks[i][j].state = 0;
            blocks[i][j].left = 70 * j + 16;
            blocks[i][j].rigth = 70 * j + 16 + 63;
            blocks[i][j].top = 70 * i + 16;
            blocks[i][j].down = 70 * i + 16 + 43;
            blocks[i][j].value = 150 * floor(i / 2) + 200;
        }
    }
    return blocks;

};

struct ball * create_ball(){
    struct ball *ball = malloc(sizeof(struct ball));
    ball->y = 600;
    ball->x = 400;
    ball->vx=1.2;
    ball->vy=-1.2;
    ball->factor=1;
    return ball;
}