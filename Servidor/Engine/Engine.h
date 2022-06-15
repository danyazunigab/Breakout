//
// Created by 2001i on 12/6/2022.
//

#ifndef BREAKOUT_ENGINE_H
#define BREAKOUT_ENGINE_H

#endif //BREAKOUT_ENGINE_H

void move_paddle(struct serversocket *socket, struct gamedata *data);

void Interpretate(struct gamedata *data);

void next_frame(struct gamedata *data);

void move_ball(struct ball *ball);

struct bricks* check_collision(struct ball*, struct bricks  **);

void collision(struct ball*, struct bricks*,struct gamedata*);

void check_limits(struct ball*,struct node*, struct gamedata*);

void paddle_collision(struct ball*, struct paddle*);

void check_win(struct gamedata*);
