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
import com.codenjoy.dojo.snakebattle.repository.SnakeRepository;
import com.codenjoy.dojo.snakebattle.utils.SnakeDirectionUtil;
import com.codenjoy.dojo.snakebattle.utils.SnakeTargetUtil;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.codenjoy.dojo.services.Direction.*;
import static com.codenjoy.dojo.snakebattle.model.Elements.*;

public abstract class AbstractSnakeResponse implements SnakeResponse {

    private static final Logger log = Logger.getLogger(AbstractSnakeResponse.class);

    protected static int isObsession = 0;

    protected Board board;

    protected Point myPoint;

    protected Point target;

    protected Direction targetDirection;
    protected String answer;
    protected List<Direction> directions;

    private static LinkedList<Direction> moving = new LinkedList<>();

    private SnakeRepository repository;

    public AbstractSnakeResponse() {
        this.repository = new SnakeRepository();
    }

    public String dropBomb(Direction direction) {
        return new StringBuilder(direction.toString()).append(", ACT").toString();
    }

    public void getRandomDirection() {
        // Random direction from direction list
        if (!directions.isEmpty()) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(directions.size());
            targetDirection = directions.get(randomIndex);
        }
    }

    public void addMove(Direction direction) {
        moving.add(direction);
        if (moving.size() > 6) {
            moving.removeFirst();
        }
        log.info(moving);
    }

    public void moveToTarget() {
        log.info("Move to target - My point:" + myPoint + " Target point : " + target);
        if (target.getX() > myPoint.getX() && target.getY() > myPoint.getY()) {
            directions = SnakeDirectionUtil.getDirections(directions, RIGHT, UP);
        }
        if (target.getX() > myPoint.getX() && target.getY() < myPoint.getY()) {
            directions = SnakeDirectionUtil.getDirections(directions, RIGHT, DOWN);
        }
        if (target.getX() < myPoint.getX() && target.getY() > myPoint.getY()) {
            directions = SnakeDirectionUtil.getDirections(directions, LEFT, UP);
        }
        if (target.getX() < myPoint.getX() && target.getY() < myPoint.getY()) {
            directions = SnakeDirectionUtil.getDirections(directions, LEFT, DOWN);
        }
        if (target.getX() == myPoint.getX() && target.getY() < myPoint.getY()) {
            directions = SnakeDirectionUtil.getDirections(directions, DOWN);
        }
        if (target.getX() == myPoint.getX() && target.getY() > myPoint.getY()) {
            directions = SnakeDirectionUtil.getDirections(directions, UP);
        }
        if (target.getX() > myPoint.getX() && target.getY() == myPoint.getY()) {
            directions = SnakeDirectionUtil.getDirections(directions, RIGHT);
        }
        if (target.getX() < myPoint.getX() && target.getY() == myPoint.getY()) {
            directions = SnakeDirectionUtil.getDirections(directions, LEFT);
        }
        log.info(directions);
    }

    public void moveFromEnemy() {

        List<Point> enemies = board.getEnemies();
        Point enemy = SnakeTargetUtil.getNearestTarget(board.getMe(), enemies);
        if (enemy != null) {
            log.info("Move from point - My point:" + myPoint + " Enemy point : " + enemy);
            if (enemy.getX() > myPoint.getX() && enemy.getY() > myPoint.getY()) {
                directions = SnakeDirectionUtil.getDirections(directions, LEFT, DOWN);
            }
            if (enemy.getX() > myPoint.getX() && enemy.getY() < myPoint.getY()) {
                directions = SnakeDirectionUtil.getDirections(directions, LEFT, UP);
            }
            if (enemy.getX() < myPoint.getX() && enemy.getY() > myPoint.getY()) {
                directions = SnakeDirectionUtil.getDirections(directions, RIGHT, DOWN);
            }
            if (enemy.getX() < myPoint.getX() && enemy.getY() < myPoint.getY()) {
                directions = SnakeDirectionUtil.getDirections(directions, RIGHT, UP);
            }
            if (enemy.getX() == myPoint.getX() && enemy.getY() < myPoint.getY()) {
                directions = SnakeDirectionUtil.getDirections(directions, UP);
            }
            if (enemy.getX() == myPoint.getX() && enemy.getY() > myPoint.getY()) {
                directions = SnakeDirectionUtil.getDirections(directions, DOWN);
            }
            if (enemy.getX() > myPoint.getX() && enemy.getY() == myPoint.getY()) {
                directions = SnakeDirectionUtil.getDirections(directions, LEFT);
            }
            if (enemy.getX() < myPoint.getX() && enemy.getY() == myPoint.getY()) {
                directions = SnakeDirectionUtil.getDirections(directions, RIGHT);
            }
        }
        log.info(directions);
    }


    public boolean isObsession() {
        if (repository.getObsessionList().contains(moving) && isObsession == 0) {
            isObsession = 5;
            return true;
        } else if (isObsession > 0) {
            log.info("Obhsession: " + isObsession);
            isObsession--;
            return true;
        } else {
            return false;
        }
    }

    public void checkBackDirection() {
        log.info("Check direction");
        Elements left = board.getLeftElement(board.getMe());
        Elements right = board.getRightElement(board.getMe());
        Elements up = board.getUpElement(board.getMe());
        Elements down = board.getDownElement(board.getMe());
        if (board.getSnakeSize() == 2) {
            if (left != null) {
                log.info("" + left + right + down + up);
                if (left.equals(TAIL_END_LEFT)) {
                    directions.remove(LEFT);
                }
            }
            if (right != null) {
                if (right.equals(TAIL_END_RIGHT)) {
                    directions.remove(RIGHT);
                }
            }
            if (down != null) {
                if (down.equals(TAIL_END_DOWN)) {
                    directions.remove(DOWN);
                }
            }
            if (up != null) {
                if (up.equals(TAIL_END_UP)) {
                    directions.remove(UP);
                }
            }
        } else {
            if (left != null) {
                if (left.equals(BODY_HORIZONTAL) ||
                        left.equals(BODY_RIGHT_DOWN) ||
                        left.equals(BODY_RIGHT_UP)) {
                    directions.remove(LEFT);
                }
            }
            if (right != null) {
                if (right.equals(BODY_HORIZONTAL) ||
                        right.equals(BODY_LEFT_DOWN) ||
                        right.equals(BODY_LEFT_UP)) {
                    directions.remove(RIGHT);
                }
            }
            if (down != null) {
                if (down.equals(Elements.BODY_VERTICAL) ||
                        down.equals(BODY_LEFT_UP) ||
                        down.equals(BODY_RIGHT_UP)) {
                    directions.remove(DOWN);
                }
            }
            if (up != null) {
                if (up.equals(Elements.BODY_VERTICAL) ||
                        up.equals(BODY_LEFT_DOWN) ||
                        up.equals(BODY_RIGHT_DOWN)) {
                    directions.remove(UP);
                }
            }
        }
        log.info(directions);
    }

    public void getReverseDirection() {
        Direction snakeDirection = board.getSnakeDirection();
        if (snakeDirection != null) {
            if (snakeDirection.equals(RIGHT)) {
                answer = "UP, LEFT";
            } else if (snakeDirection.equals(LEFT)) {
                answer = "DOWN, RIGHT";
            } else if (snakeDirection.equals(UP)) {
                answer = "LEFT, DOWN";
            } else if (snakeDirection.equals(DOWN)) {
                answer = "RIGHT, UP";
            }
        }
    }

    public boolean isBackDirection(Direction direction) {
        Direction myDirection = board.getSnakeDirection();
        log.info(myDirection);
        if (myDirection != null) {
            return direction.equals(myDirection.inverted()) ? true : false;
        } else {
            return false;
        }
    }
}
