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
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.snakebattle.model.Elements;
import org.apache.log4j.Logger;

import java.util.List;

import static com.codenjoy.dojo.services.Direction.*;
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
        return !getMyHead().isEmpty() ? getMyHead().get(0) : null;
    }

    public boolean isGameOver() {
        return getMyHead().isEmpty();
    }

    private List<Point> getMyHead() {
        List<Point> positions = get(HEAD_DOWN, HEAD_LEFT, HEAD_RIGHT, HEAD_UP, HEAD_DEAD, HEAD_SLEEP, HEAD_EVIL, HEAD_FLY);
        return positions;
    }

    public int getSnakeSize() {
        List<Point> snake = get(HEAD_LEFT, HEAD_RIGHT, HEAD_UP, HEAD_DOWN, HEAD_EVIL, HEAD_FLY,
                TAIL_END_DOWN, TAIL_END_LEFT, TAIL_END_UP, TAIL_END_RIGHT, TAIL_INACTIVE,
                BODY_HORIZONTAL, BODY_VERTICAL, BODY_LEFT_DOWN, BODY_LEFT_UP, BODY_RIGHT_DOWN, BODY_RIGHT_UP);
        return snake.size();
    }

    public boolean isSmallEnemy(Point point) {
        List<Elements> elements = getNear(point);
        if (elements.contains(ENEMY_TAIL_END_DOWN) || elements.contains(ENEMY_TAIL_END_LEFT) || elements.contains(ENEMY_TAIL_END_RIGHT) || elements.contains(ENEMY_TAIL_END_UP)) {
            return true;
        } else {
            return false;
        }
    }


//        List<Point> snake = get(HEAD_LEFT, HEAD_RIGHT, HEAD_UP, HEAD_DOWN, HEAD_EVIL, HEAD_FLY,
//                TAIL_END_DOWN, TAIL_END_LEFT, TAIL_END_UP, TAIL_END_RIGHT, TAIL_INACTIVE,
//                BODY_HORIZONTAL, BODY_VERTICAL, BODY_LEFT_DOWN, BODY_LEFT_UP, BODY_RIGHT_DOWN, BODY_RIGHT_UP);
//        return snake.size();
//    }

//    public Elements getSnakeDirection() {
//        List<Point> headPoints = get(HEAD_DOWN, HEAD_LEFT, HEAD_RIGHT, HEAD_UP);
//        return !headPoints.isEmpty() ? getAt(headPoints.get(0)) : null;
//    }

    public Direction getSnakeDirection() {
        Elements left = getLeftElement(getMe());
        Elements right = getRightElement(getMe());
        Elements up = getUpElement(getMe());
        Elements down = getDownElement(getMe());
        if (getSnakeSize() == 2) {
            if (left != null) {
                if (left.equals(TAIL_END_LEFT)) {
                    return RIGHT;
                }
            }
            if (right != null) {
                if (right.equals(TAIL_END_RIGHT)) {
                    return LEFT;
                }
            }
            if (down != null) {
                if (down.equals(TAIL_END_DOWN)) {
                    return UP;
                }
            }
            if (up != null) {
                if (up.equals(TAIL_END_UP)) {
                    return DOWN;
                }
            }
        } else {
            if (left != null) {
                log.info("Elements around : " + left + " " + right + " " + down + " " + up);
                if (left.equals(BODY_HORIZONTAL) ||
                        left.equals(BODY_RIGHT_DOWN) ||
                        left.equals(BODY_RIGHT_UP)) {
                    return RIGHT;
                }
            }
            if (right != null) {
                if (right.equals(BODY_HORIZONTAL) ||
                        right.equals(BODY_LEFT_DOWN) ||
                        right.equals(BODY_LEFT_UP)) {
                    return LEFT;
                }
            }
            if (down != null) {
                if (down.equals(Elements.BODY_VERTICAL) ||
                        down.equals(BODY_LEFT_UP) ||
                        down.equals(BODY_RIGHT_UP)) {
                    return UP;
                }
            }
            if (up != null) {
                if (up.equals(Elements.BODY_VERTICAL) ||
                        up.equals(BODY_LEFT_DOWN) ||
                        up.equals(BODY_RIGHT_DOWN)) {
                    return DOWN;
                }
            }
        }
        return null;
    }

    public Direction getEnemyDirection(Point point) {
        log.info("Check enemy direction");
        Elements left = getLeftElement(point);
        Elements right = getRightElement(point);
        Elements up = getUpElement(point);
        Elements down = getDownElement(point);
        if (isSmallEnemy(point)) {
            if (left != null) {
                if (left.equals(ENEMY_TAIL_END_LEFT)) {
                    return RIGHT;
                }
            }
            if (right != null) {
                if (right.equals(ENEMY_TAIL_END_RIGHT)) {
                    return LEFT;
                }
            }
            if (down != null) {
                if (down.equals(ENEMY_TAIL_END_DOWN)) {
                    return UP;
                }
            }
            if (up != null) {
                if (up.equals(ENEMY_TAIL_END_UP)) {
                    return DOWN;
                }
            }
        } else {
            if (left != null) {
                log.info("Elements around : " + left + " " + right + " " + down + " " + up);
                if (left.equals(ENEMY_BODY_HORIZONTAL) ||
                        left.equals(ENEMY_BODY_RIGHT_DOWN) ||
                        left.equals(ENEMY_BODY_RIGHT_UP)) {
                    return RIGHT;
                }
            }
            if (right != null) {
                if (right.equals(ENEMY_BODY_HORIZONTAL) ||
                        right.equals(ENEMY_BODY_LEFT_DOWN) ||
                        right.equals(ENEMY_BODY_LEFT_UP)) {
                    return LEFT;
                }
            }
            if (down != null) {
                if (down.equals(ENEMY_BODY_VERTICAL) ||
                        down.equals(ENEMY_BODY_LEFT_UP) ||
                        down.equals(ENEMY_BODY_RIGHT_UP)) {
                    return UP;
                }
            }
            if (up != null) {
                if (up.equals(ENEMY_BODY_VERTICAL) ||
                        up.equals(ENEMY_BODY_LEFT_DOWN) ||
                        up.equals(ENEMY_BODY_RIGHT_DOWN)) {
                    return DOWN;
                }
            }
        }
        return null;
    }

    public List<Point> getEnemies() {
        return get(ENEMY_HEAD_DOWN, ENEMY_HEAD_LEFT, ENEMY_HEAD_RIGHT, ENEMY_HEAD_UP, ENEMY_HEAD_EVIL, ENEMY_HEAD_FLY, ENEMY_HEAD_SLEEP);
    }

    public Elements getLeftElement(Point point) {
        return (point.getX() - 1 > 0) ? getAt(point.getX() - 1, point.getY()) : null;
    }

    public Elements getRightElement(Point point) {
        return (point.getX() + 1 < 30) ? getAt(point.getX() + 1, point.getY()) : null;
    }

    public Elements getUpElement(Point point) {
        return (point.getY() + 1 < 30) ? getAt(point.getX(), point.getY() + 1) : null;
    }

    public Elements getDownElement(Point point) {
        return (point.getY() - 1 > 0) ? getAt(point.getX(), point.getY() - 1) : null;
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


    public Point getTail() {
        List<Point> tails = get(TAIL_END_DOWN, TAIL_END_LEFT, TAIL_END_RIGHT, TAIL_END_UP, TAIL_INACTIVE);
        return !tails.isEmpty() ? tails.get(0) : null;
    }

    public int getEnemySize() {
        List<Point> enemyPoints = get(ENEMY_HEAD_DOWN, ENEMY_HEAD_LEFT, ENEMY_HEAD_RIGHT, ENEMY_HEAD_UP, ENEMY_HEAD_FLY, ENEMY_HEAD_EVIL,
                ENEMY_BODY_RIGHT_DOWN, ENEMY_BODY_LEFT_DOWN, ENEMY_BODY_RIGHT_UP, ENEMY_BODY_RIGHT_UP, ENEMY_BODY_HORIZONTAL, ENEMY_BODY_VERTICAL,
                ENEMY_TAIL_END_DOWN, ENEMY_TAIL_END_LEFT, ENEMY_TAIL_END_RIGHT, ENEMY_TAIL_END_UP);
        return enemyPoints.size();

    }
}
