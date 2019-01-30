package com.codenjoy.dojo.snakebattle.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.AbstractBoard;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.snakebattle.model.Elements;
import org.apache.log4j.Logger;

import java.util.List;

import static com.codenjoy.dojo.snakebattle.model.Elements.*;

/**
 * Класс, обрабатывающий строковое представление доски.
 * Содержит ряд унаследованных методов {@see AbstractBoard},
 * но ты можешь добавить сюда любые свои методы на их основе.
 */
public class Board extends AbstractBoard<Elements> {

    private static final Logger log = Logger.getLogger(Board.class);

    @Override
    public Elements valueOf(char ch) {
        return Elements.valueOf(ch);
    }

    public boolean isBarrierAt(int x, int y) {
        return isAt(x, y, WALL, STONE, START_FLOOR, ENEMY_HEAD_SLEEP, ENEMY_TAIL_INACTIVE, TAIL_INACTIVE,
                ENEMY_BODY_HORIZONTAL, ENEMY_BODY_VERTICAL, ENEMY_BODY_LEFT_DOWN, ENEMY_BODY_LEFT_UP, ENEMY_BODY_RIGHT_DOWN, ENEMY_BODY_RIGHT_UP);
    }

    public boolean isBarrierAt(int x, int y, Elements[] elements) {
        return isAt(x, y, elements);
    }

    @Override
    protected int inversionY(int y) {
        return size - 1 - y;
    }

    public Point getMe() {
        return getMyHead().get(0);
    }

    public boolean isGameOver() {
        return getMyHead().isEmpty();
    }

    private List<Point> getMyHead() {
        List<Point> positions = get(HEAD_DOWN, HEAD_LEFT, HEAD_RIGHT, HEAD_UP, HEAD_DEAD, HEAD_SLEEP, HEAD_EVIL, HEAD_FLY);
        log.info("My position: " + positions);
        return positions;
    }

    public int getSnakeSize() {
        List<Point> snake = get(HEAD_LEFT, HEAD_RIGHT, HEAD_UP, HEAD_DOWN, HEAD_EVIL, HEAD_FLY,
                TAIL_END_DOWN, TAIL_END_LEFT, TAIL_END_UP, TAIL_END_RIGHT, TAIL_INACTIVE,
                BODY_HORIZONTAL, BODY_VERTICAL, BODY_LEFT_DOWN, BODY_LEFT_UP, BODY_RIGHT_DOWN, BODY_RIGHT_UP);
        return snake.size();
    }

    public Elements getSnakeDirection() {
        List<Point> headPoints = get(HEAD_DOWN, HEAD_LEFT, HEAD_RIGHT, HEAD_UP);
        return !headPoints.isEmpty() ? getAt(headPoints.get(0)) : null;
    }

    public boolean isHeadEvil() {
        return (!get(HEAD_EVIL).isEmpty()) ? true : false;
    }

    public boolean isHeadFly() {
        return (!get(HEAD_FLY).isEmpty()) ? true : false;
    }

    public boolean isStoneAt(int x, int y) {
        return isAt(x, y, STONE);
    }

    public boolean isWallAt(int x, int y) {
        return isAt(x, y, WALL);
    }

}
