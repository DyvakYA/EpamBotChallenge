package com.codenjoy.dojo.snakebattle.client;

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.snakebattle.model.Elements;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codenjoy.dojo.services.Direction.*;
import static com.codenjoy.dojo.snakebattle.model.Elements.*;

public class Snake {

    private static final Logger log = Logger.getLogger(Snake.class);

    private Board board;
    private Target target;

    private int size;
    private Elements snakeDirection;
    private Point headPoint;

    private Set<Elements> good;
    private Set<Elements> bad;

    private List<Direction> directions;

    private boolean isEvil;
    private boolean isFly;


    public Snake(Board board) {
        this.board = board;
        this.size = board.getSnakeSize();
        this.snakeDirection = board.getSnakeDirection();
        this.headPoint = board.getMe();

        good = new HashSet<>();
        good.add(GOLD);
        good.add(APPLE);
        good.add(FLYING_PILL);
        good.add(FURY_PILL);

        becomeGood();

        directions = new ArrayList<>(Direction.getValues());

        isEvil = board.isHeadEvil();
        isFly = board.isHeadFly();

    }

    public void becomeEvil() {
        good = good.stream()
                .filter(e -> e.equals(STONE))
                .collect(Collectors.toSet());

        bad.remove(STONE);
        bad.remove(ENEMY_TAIL_END_RIGHT);
        bad.remove(ENEMY_TAIL_END_LEFT);
        bad.remove(ENEMY_TAIL_END_UP);
        bad.remove(ENEMY_TAIL_END_DOWN);
        bad.remove(ENEMY_TAIL_INACTIVE);

        bad.remove(ENEMY_BODY_HORIZONTAL);
        bad.remove(ENEMY_BODY_VERTICAL);
        bad.remove(ENEMY_BODY_LEFT_DOWN);
        bad.remove(ENEMY_BODY_LEFT_UP);
        bad.remove(ENEMY_BODY_RIGHT_DOWN);
        bad.remove(ENEMY_BODY_RIGHT_UP);
    }

    public void checkBadAround() {
        log.info("Check bad around me");
        Elements[] elements = new Elements[bad.size()];
        elements = bad.toArray(elements);

        if (board.isBarrierAt(headPoint.getX(), headPoint.getY() + 1, elements)) {
            directions.remove(UP);
            log.info("Remove up");
        }
        if (board.isBarrierAt(headPoint.getX(), headPoint.getY() - 1, elements)) {
            directions.remove(DOWN);
            log.info("Remove down");
        }
        if (board.isBarrierAt(headPoint.getX() + 1, headPoint.getY(), elements)) {
            directions.remove(RIGHT);
            log.info("Remove right");
        }
        if (board.isBarrierAt(headPoint.getX() - 1, headPoint.getY(), elements)) {
            directions.remove(LEFT);
            log.info("Remove left");
        }
        log.info(directions);
    }

    public void checkGoodsAround() {
        log.info("Check goods around me");
        Elements element = null;
        List<Elements> elements = board.getNear(headPoint);
        Optional<Elements> optionalDirection = elements.stream()
                .filter(e -> good.contains(e))
                .findAny();
        if (optionalDirection.isPresent()) {

            log.info("Good detected");
            element = optionalDirection.get();
            if (board.isAt(headPoint.getX(), headPoint.getY() + 1, element)) {
                choseDirectionIfExist(UP);
            }
            if (board.isAt(headPoint.getX(), headPoint.getY() - 1, element)) {
                choseDirectionIfExist(DOWN);
            }
            if (board.isAt(headPoint.getX() + 1, headPoint.getY(), element)) {
                choseDirectionIfExist(RIGHT);
            }
            if (board.isAt(headPoint.getX() - 1, headPoint.getY(), element)) {
                choseDirectionIfExist(LEFT);
            }
        }
        log.info(directions);
    }

    public void checkDirection() {
        log.info("Check back direction");
        Elements myDirection = board.getSnakeDirection();
        if (myDirection != null) {
            if (myDirection.equals(Elements.HEAD_LEFT)) {
                directions.remove(RIGHT);
            } else if (myDirection.equals(Elements.HEAD_RIGHT)) {
                directions.remove(LEFT);
            } else if (myDirection.equals(Elements.HEAD_UP)) {
                directions.remove(DOWN);
            } else if (myDirection.equals(Elements.HEAD_DOWN)) {
                directions.remove(UP);
            }
        }
        log.info(directions);
    }

    public void choseDirectionIfExist(Direction... direction) {
        List<Direction> result = Arrays.stream(direction)
                .filter(d -> directions.contains(d))
                .collect(Collectors.toList());

        if (!result.isEmpty()) {
            directions = result;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Point getHeadPoint() {
        return headPoint;
    }

    public void setHeadPoint(Point headPoint) {
        this.headPoint = headPoint;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isEvil() {
        return isEvil;
    }

    public void setEvil(boolean evil) {
        isEvil = evil;
    }

    public boolean isFly() {
        return isFly;
    }

    public void setFly(boolean fly) {
        isFly = fly;
    }

    public void remove(Direction... dir) {
        for (Direction item : dir) {
            directions.remove(item);
        }
    }

    public void checkSnakeCondition() {
        if (isEvil()) {
            becomeEvil();
        }
        if (!isEvil()) {
            becomeGood();
        }
        if (size >= 5) {
            good.add(STONE);
            bad.remove(STONE);
        }
        if (size < 5) {
            good.remove(STONE);
            bad.add(STONE);
        }
    }

    private void becomeGood() {
        bad = new HashSet<>();
        bad.add(WALL);
        bad.add(STONE);
        bad.add(START_FLOOR);
        bad.add(ENEMY_HEAD_RIGHT);
        bad.add(ENEMY_HEAD_LEFT);
        bad.add(ENEMY_HEAD_UP);
        bad.add(ENEMY_HEAD_DOWN);
        bad.add(ENEMY_HEAD_EVIL);
        bad.add(ENEMY_HEAD_FLY);

        bad.add(ENEMY_HEAD_SLEEP);

        bad.add(ENEMY_TAIL_END_RIGHT);
        bad.add(ENEMY_TAIL_END_LEFT);
        bad.add(ENEMY_TAIL_END_UP);
        bad.add(ENEMY_TAIL_END_DOWN);
        bad.add(ENEMY_TAIL_INACTIVE);

        bad.add(ENEMY_BODY_HORIZONTAL);
        bad.add(ENEMY_BODY_VERTICAL);
        bad.add(ENEMY_BODY_LEFT_DOWN);
        bad.add(ENEMY_BODY_LEFT_UP);
        bad.add(ENEMY_BODY_RIGHT_DOWN);
        bad.add(ENEMY_BODY_RIGHT_UP);
    }

    public void setGood(Set<Elements> good) {
        this.good = good;
    }

    public void setBad(Set<Elements> bad) {
        this.bad = bad;
    }

    public Set<Elements> getGood() {
        return good;
    }

    public Set<Elements> getBad() {
        return bad;
    }
}
