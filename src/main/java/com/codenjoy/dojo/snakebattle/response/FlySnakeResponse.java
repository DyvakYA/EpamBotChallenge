package com.codenjoy.dojo.snakebattle.response;

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
import com.codenjoy.dojo.snakebattle.utils.SnakeDirectionUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.codenjoy.dojo.snakebattle.model.Elements.*;

public class FlySnakeResponse extends AbstractSnakeResponse {

    private static final Logger log = Logger.getLogger(FlySnakeResponse.class);

    public FlySnakeResponse(Board board) {
        super();
        this.board = board;
        this.myPoint = board.getMe();
    }

    @Override
    public String generate(List<Direction> directions, Point target) {
        this.directions = directions;
        this.target = target;

        // check is obsession present
        // check is obsession present
        if (!isObsession()) {
            moveToTarget();
        }
        checkBackDirection();
        // try move from closest enemy
        moveFromEnemy();
        // get random direction from direction list
        getRandomDirection();
        log.info("Target direction " + targetDirection);
        // record move to the history
        addMove(targetDirection);

        if (targetDirection == null) {
            this.directions = SnakeDirectionUtil.checkFreeCellsAround(board);
            checkBackDirection();
            if (this.directions.isEmpty()) {
                answer = "ACT(0)";
            } else {
                getRandomDirection();
                answer = targetDirection.toString();
            }
        } else {
            Point tail = board.getTail();
            if (tail != null && checkEnemiesAroundTail(tail)) {
                log.info("enemy near tail");
                answer = dropBomb(targetDirection);
            } else {
                answer = targetDirection.toString();
            }
        }

        return answer;
    }


    public boolean checkEnemiesAroundTail(Point point) {
        log.info("Check enemies around me");
        List<Elements> enemyHeadTypes = new ArrayList<>();
        enemyHeadTypes.add(ENEMY_HEAD_DOWN);
        enemyHeadTypes.add(ENEMY_HEAD_LEFT);
        enemyHeadTypes.add(ENEMY_HEAD_RIGHT);
        enemyHeadTypes.add(ENEMY_HEAD_UP);
        enemyHeadTypes.add(ENEMY_HEAD_EVIL);
        enemyHeadTypes.add(ENEMY_HEAD_FLY);

        if (!board.isOutOfField(point.getX() + 1, point.getY())) {
            if (enemyHeadTypes.contains(board.getAt(point.getX() + 1, point.getY()))) {
                return true;
            }
        }
        if (!board.isOutOfField(point.getX() + 1, point.getY() + 1)) {
            if (enemyHeadTypes.contains(board.getAt(point.getX() + 1, point.getY() + 1))) {
                return true;
            }
        }
        if (!board.isOutOfField(point.getX(), point.getY() + 1)) {
            if (enemyHeadTypes.contains(board.getAt(point.getX(), point.getY() + 1))) {
                return true;
            }
        }
        if (!board.isOutOfField(point.getX() + 1, point.getY() - 1)) {
            if (enemyHeadTypes.contains(board.getAt(point.getX() + 1, point.getY() - 1))) {
                return true;
            }
        }
        if (!board.isOutOfField(point.getX(), point.getY() - 1)) {
            if (enemyHeadTypes.contains(board.getAt(point.getX(), point.getY() - 1))) {
                return true;
            }
        }
        if (!board.isOutOfField(point.getX() - 1, point.getY() - 1)) {
            if (enemyHeadTypes.contains(board.getAt(point.getX() - 1, point.getY() - 1))) {
                return true;
            }
        }
        if (!board.isOutOfField(point.getX() - 1, point.getY())) {
            if (enemyHeadTypes.contains(board.getAt(point.getX() - 1, point.getY()))) {
                return true;
            }
        }
        if (!board.isOutOfField(point.getX() - 1, point.getY() + 1)) {
            if (enemyHeadTypes.contains(board.getAt(point.getX() - 1, point.getY() + 1))) {
                return true;
            }
        }
        return false;
    }
}
