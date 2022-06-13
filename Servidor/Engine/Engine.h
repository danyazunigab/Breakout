//
// Created by 2001i on 12/6/2022.
//

#ifndef BREAKOUT_ENGINE_H
#define BREAKOUT_ENGINE_H

#endif //BREAKOUT_ENGINE_H

void move_paddle(struct serversocket *socket, struct gamedata *data);

struct gamedata test_adapter(void);

int main(void);

void reset_blocks(struct gamedata*);
struct bricks** create_blocks();