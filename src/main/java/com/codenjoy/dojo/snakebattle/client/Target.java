package com.codenjoy.dojo.snakebattle.client;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.snakebattle.model.Elements;
import org.apache.log4j.Logger;

import java.util.List;

import static com.codenjoy.dojo.services.Direction.*;

public class Target {

    private static final Logger log = Logger.getLogger(Target.class);

    private Snake snake;
    private Board board;

    private BlackPoints blackPoints;

    public Target(Snake snake) {
        this.snake = snake;
        this.board = snake.getBoard();
        this.blackPoints = new BlackPoints();
    }

    public void execute() {

        int targetX = 0;
        int targetY = 0;

        Point headPoint = snake.getHeadPoint();

        List<Point> goodsPoints;
        if (snake.getSize() >= 5 || snake.isEvil()) {
            log.info("Size : " + board.getSnakeSize() + " >= 5 --> get stones!");
            goodsPoints = board.get(Elements.STONE);
        } else {
            goodsPoints = board.get(Elements.GOLD);
        }
        if (goodsPoints.isEmpty()) {
            goodsPoints = board.get(Elements.APPLE);
        }
        if (!goodsPoints.isEmpty()) {
            goodsPoints = blackPoints.deleteBlackPoints(goodsPoints);
            log.info("Good points : " + goodsPoints);

            double pointDistance = 0;
            int cellPointDistance = 0;
            for (Point point : goodsPoints) {
                //pointDistance = headPoint.distance(point);
                cellPointDistance = Math.abs(headPoint.getX() - point.getX()) + Math.abs(headPoint.getY() - point.getY());
                // log.info(pointDistance + " = "+  Math.abs(myPoint.getX() - point.getX()) + " + " + Math.abs(myPoint.getY() - point.getY()));
                if (pointDistance == 0 || headPoint.distance(point) < pointDistance) {
                    pointDistance = headPoint.distance(point);
                    targetX = point.getX();
                    targetY = point.getY();
                }
            }
            log.info("Target position : x = " + targetX + " y = " + targetY + " Real distance : " + pointDistance + " Cell distance : " + cellPointDistance);
            if (targetX > headPoint.getX() && targetY > headPoint.getY()) {
                snake.choseDirectionIfExist(RIGHT, UP);
            }
            if (targetX > headPoint.getX() && targetY < headPoint.getY()) {
                snake.choseDirectionIfExist(RIGHT, DOWN);
            }
            if (targetX < headPoint.getX() && targetY > headPoint.getY()) {
                snake.choseDirectionIfExist(LEFT, UP);
            }
            if (targetX < headPoint.getX() && targetY < headPoint.getY()) {
                snake.choseDirectionIfExist(LEFT, DOWN);
            }
            if (targetX == headPoint.getX() && targetY < headPoint.getY()) {
                snake.choseDirectionIfExist(DOWN);
            }
            if (targetX == headPoint.getX() && targetY > headPoint.getY()) {
                snake.choseDirectionIfExist(UP);
            }
            if (targetX > headPoint.getX() && targetY == headPoint.getY()) {
                snake.choseDirectionIfExist(RIGHT);
            }
            if (targetX < headPoint.getX() && targetY == headPoint.getY()) {
                snake.choseDirectionIfExist(LEFT);
            }
        }
    }
}
