package com.codenjoy.dojo.snakebattle.direction;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 - 2019 Codenjoy
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

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.snakebattle.client.Board;
import com.codenjoy.dojo.snakebattle.model.Elements;
import com.codenjoy.dojo.snakebattle.repository.SnakeRepository;
import com.codenjoy.dojo.snakebattle.utils.SnakeDirectionUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.codenjoy.dojo.services.Direction.*;
import static com.codenjoy.dojo.snakebattle.model.Elements.*;

public abstract class AbstractSnakeDirection implements SnakeDirection {

    private static final Logger log = Logger.getLogger(AbstractSnakeDirection.class);

    protected Board board;
    protected Point headPoint;

    protected Set<Elements> good;
    protected Set<Elements> bad;

    protected SnakeRepository repository;

    protected List<Direction> directions = new ArrayList<>(Direction.getValues());

    public AbstractSnakeDirection(Board board) {
        this.board = board;
        this.headPoint = board.getMe();
        this.repository = new SnakeRepository();
    }

    public abstract List<Direction> getDirections();

    public void checkBadAround() {

        log.info("Check bad around me");
        Elements[] elements = new Elements[bad.size()];
        elements = bad.toArray(elements);

        if (board.isBarrierAt(headPoint.getX(), headPoint.getY() + 1, elements)) {
            directions.remove(UP);
        }
        if (board.isBarrierAt(headPoint.getX(), headPoint.getY() - 1, elements)) {
            directions.remove(DOWN);
        }
        if (board.isBarrierAt(headPoint.getX() + 1, headPoint.getY(), elements)) {
            directions.remove(RIGHT);
        }
        if (board.isBarrierAt(headPoint.getX() - 1, headPoint.getY(), elements)) {
            directions.remove(LEFT);
        }
        log.info(directions);
    }

    public void checkGoodsAround() {
        log.info("Check goods around me");
        Elements element = null;
        List<Elements> elements = board.getNear(headPoint);
        Optional<Elements> optionalDirection = elements.stream()
                .filter(e -> good.contains(e))
                .sorted(Comparator.comparing(Elements::toString).reversed())
                .findFirst();

        if (optionalDirection.isPresent()) {
            element = optionalDirection.get();
            if (board.isAt(headPoint.getX(), headPoint.getY() + 1, element)) {
                directions = SnakeDirectionUtil.getDirections(directions, UP);
            }
            if (board.isAt(headPoint.getX(), headPoint.getY() - 1, element)) {
                directions = SnakeDirectionUtil.getDirections(directions, DOWN);
            }
            if (board.isAt(headPoint.getX() + 1, headPoint.getY(), element)) {
                directions = SnakeDirectionUtil.getDirections(directions, RIGHT);
            }
            if (board.isAt(headPoint.getX() - 1, headPoint.getY(), element)) {
                directions = SnakeDirectionUtil.getDirections(directions, LEFT);
            }
        }
        log.info(directions);
    }

    public void checkBlackPoints() {
        if (headPoint.getX() == 10 && headPoint.getY() == 20) {
            directions.remove(LEFT);
        }
        if (headPoint.getX() == 10 && headPoint.getY() == 22) {
            directions.remove(LEFT);
        }
        if ((headPoint.getX() == 19 && (headPoint.getY() + 1) == 10)) {
            directions.remove(UP);
        }
        if ((headPoint.getX() == 23 && (headPoint.getY() + 1) == 10)) {
            directions.remove(UP);
        }
        if ((headPoint.getX() == 21 && (headPoint.getY() - 1) == 10)) {
            directions.remove(DOWN);
        }
    }

    public void checkEnemiesAroundHeadDefence() {
        log.info("Check enemies around me");
        Point me = board.getMe();
        List<Elements> enemy = new ArrayList<>(Arrays.asList(ENEMY_HEAD_DOWN, ENEMY_HEAD_LEFT, ENEMY_HEAD_RIGHT, ENEMY_HEAD_UP, ENEMY_HEAD_EVIL, ENEMY_HEAD_FLY));

        // dangerous point #1
        if (!board.isOutOfField(me.getX(), me.getY() + 1)) {
            if (enemy.contains(board.getAt(me.getX(), me.getY() + 1))) {
                remove(UP);
            }
        }
        // dangerous point #2
        if (!board.isOutOfField(me.getX() + 1, me.getY() + 1)) {
            if (enemy.contains(board.getAt(me.getX() + 1, me.getY() + 1))) {
                remove(UP, RIGHT);
            }
        }
        // dangerous point #3
        if (!board.isOutOfField(me.getX() + 1, me.getY())) {
            if (enemy.contains(board.getAt(me.getX() + 1, me.getY()))) {
                remove(RIGHT);
            }
        }
        // dangerous point #4
        if (!board.isOutOfField(me.getX() + 1, me.getY() - 1)) {
            if (enemy.contains(board.getAt(me.getX() + 1, me.getY() - 1))) {
                remove(RIGHT, DOWN);
            }
        }
        // dangerous point #5
        if (!board.isOutOfField(me.getX(), me.getY() - 1)) {
            if (enemy.contains(board.getAt(me.getX(), me.getY() - 1))) {
                remove(DOWN);
            }
        }
        // dangerous point #6
        if (!board.isOutOfField(me.getX() - 1, me.getY() - 1)) {
            if (enemy.contains(board.getAt(me.getX() - 1, me.getY() - 1))) {
                remove(DOWN, LEFT);
            }
        }
        // dangerous point #7
        if (!board.isOutOfField(me.getX() - 1, me.getY())) {
            if (enemy.contains(board.getAt(me.getX() - 1, me.getY()))) {
                remove(LEFT);
            }
        }
        // dangerous point #8
        if (!board.isOutOfField(me.getX() - 1, me.getY() + 1)) {
            if (enemy.contains(board.getAt(me.getX() - 1, me.getY() + 1))) {
                remove(LEFT, UP);
            }
        }
        // dangerous point #9
        if (!board.isOutOfField(me.getX(), me.getY() + 2)) {
            if (enemy.contains(board.getAt(me.getX(), me.getY() + 2))) {
                remove(UP);
            }
        }
        // dangerous point #10
        if (!board.isOutOfField(me.getX() + 1, me.getY() + 2)) {
            if (enemy.contains(board.getAt(me.getX() + 1, me.getY() + 2))) {
                remove(UP, RIGHT);
            }
        }
        // dangerous point #11
        if (!board.isOutOfField(me.getX() + 2, me.getY() + 1)) {
            if (enemy.contains(board.getAt(me.getX() + 2, me.getY() + 1))) {
                remove(UP, RIGHT);
            }
        }
        // dangerous point #12
        if (!board.isOutOfField(me.getX() + 2, me.getY())) {
            if (enemy.contains(board.getAt(me.getX() + 2, me.getY()))) {
                remove(RIGHT);
            }
        }
        // dangerous point #13
        if (!board.isOutOfField(me.getX() + 2, me.getY() - 1)) {
            if (enemy.contains(board.getAt(me.getX() + 2, me.getY() - 1))) {
                remove(RIGHT, DOWN);
            }
        }
        // dangerous point #14
        if (!board.isOutOfField(me.getX() + 1, me.getY() - 2)) {
            if (enemy.contains(board.getAt(me.getX() + 1, me.getY() - 2))) {
                remove(RIGHT, DOWN);
            }
        }
        // dangerous point #15
        if (!board.isOutOfField(me.getX(), me.getY() - 2)) {
            if (enemy.contains(board.getAt(me.getX(), me.getY() - 2))) {
                remove(DOWN);
            }
        }
        // dangerous point #16
        if (!board.isOutOfField(me.getX() - 1, me.getY() - 2)) {
            if (enemy.contains(board.getAt(me.getX() - 1, me.getY() - 2))) {
                remove(LEFT, DOWN);
            }
        }
        // dangerous point #17
        if (!board.isOutOfField(me.getX() - 2, me.getY() - 1)) {
            if (enemy.contains(board.getAt(me.getX() - 2, me.getY() - 1))) {
                remove(LEFT, DOWN);
            }
        }
        // dangerous point #18
        if (!board.isOutOfField(me.getX() - 2, me.getY())) {
            if (enemy.contains(board.getAt(me.getX() - 2, me.getY()))) {
                remove(LEFT);
            }
        }
        // dangerous point #19
        if (!board.isOutOfField(me.getX() - 2, me.getY() + 1)) {
            if (enemy.contains(board.getAt(me.getX() - 2, me.getY() + 1))) {
                remove(LEFT, UP);
            }
        }
        // dangerous point #20
        if (!board.isOutOfField(me.getX() - 1, me.getY() + 2)) {
            if (enemy.contains(board.getAt(me.getX() - 1, me.getY() + 2))) {
                remove(LEFT, UP);
            }
        }
        // dangerous point #21
        if (!board.isOutOfField(me.getX(), me.getY() + 3)) {
            if (enemy.contains(board.getAt(me.getX(), me.getY() + 3))) {
                remove(UP);
            }
        }
        // dangerous point #22
        if (!board.isOutOfField(me.getX() + 3, me.getY())) {
            if (enemy.contains(board.getAt(me.getX() + 3, me.getY()))) {
                remove(RIGHT);
            }
        }
        // dangerous point #23
        if (!board.isOutOfField(me.getX(), me.getY() - 3)) {
            if (enemy.contains(board.getAt(me.getX(), me.getY() - 3))) {
                remove(DOWN);
            }
        }
        // dangerous point #24
        if (!board.isOutOfField(me.getX() - 3, me.getY())) {
            if (enemy.contains(board.getAt(me.getX() - 3, me.getY()))) {
                remove(LEFT);
            }
        }
        log.info(directions);
    }

    public void remove(Direction... dir) {
        for (Direction item : dir) {
            directions.remove(item);
        }
    }
}
