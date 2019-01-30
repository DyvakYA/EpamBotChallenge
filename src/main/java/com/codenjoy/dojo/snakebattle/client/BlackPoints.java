package com.codenjoy.dojo.snakebattle.client;

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.codenjoy.dojo.services.Direction.*;

public class BlackPoints {

    private List<Pair> blackPointsForGoods = new ArrayList<>();
    private List<Pair> blackPointsForSnake = new ArrayList<>();

    public BlackPoints() {
        blackPointsForGoods.add(new Pair(9, 12));
        blackPointsForGoods.add(new Pair(10, 12));
        blackPointsForGoods.add(new Pair(8, 20));
        blackPointsForGoods.add(new Pair(9, 20));
        blackPointsForGoods.add(new Pair(8, 22));
        blackPointsForGoods.add(new Pair(9, 22));
        blackPointsForGoods.add(new Pair(19, 20));
        blackPointsForGoods.add(new Pair(20, 20));
        blackPointsForGoods.add(new Pair(21, 20));
        blackPointsForSnake.add(new Pair(8, 20));
        blackPointsForSnake.add(new Pair(9, 20));
        blackPointsForSnake.add(new Pair(8, 22));
        blackPointsForSnake.add(new Pair(9, 22));
        blackPointsForGoods.add(new Pair(19, 10));
        blackPointsForGoods.add(new Pair(23, 10));
        blackPointsForGoods.add(new Pair(21, 10));

        blackPointsForSnake.add(new Pair(8, 20));
        blackPointsForSnake.add(new Pair(9, 20));
        blackPointsForSnake.add(new Pair(8, 22));
        blackPointsForSnake.add(new Pair(9, 22));
        blackPointsForSnake.add(new Pair(19, 10));
        blackPointsForSnake.add(new Pair(23, 10));
        blackPointsForSnake.add(new Pair(21, 10));
    }


    private static final Logger log = Logger.getLogger(BlackPoints.class);

    public Direction check(Point headPoint) {
        log.info("Check black points around me");
        if (headPoint.getX() == 10 & headPoint.getY() == 20) {
            return LEFT;
        }
        if (headPoint.getX() == 10 & headPoint.getY() == 22) {
            return LEFT;
        }
        if ((headPoint.getX() == 19 & (headPoint.getY() + 1) == 10)) {
            return UP;
        }
        if ((headPoint.getX() == 23 & (headPoint.getY() + 1) == 10)) {
            return UP;
        }
        if ((headPoint.getX() == 21 & (headPoint.getY() - 1) == 10)) {
            return DOWN;
        }
        return null;
    }

    public List<Point> deleteBlackPoints(List<Point> points) {
        List copyList = new ArrayList(points);
        for (Point point : points) {
            for (Pair blackListPair : blackPointsForGoods) {
                if (point.getX() == blackListPair.getX() && point.getY() == blackListPair.getY()) {
                    copyList.remove(point);
                }
            }
        }
        return copyList;
    }
}
