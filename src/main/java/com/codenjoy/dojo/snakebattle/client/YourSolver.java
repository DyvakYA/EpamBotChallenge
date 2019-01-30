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


import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snakebattle.model.Elements;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;

import static com.codenjoy.dojo.services.Direction.*;

/**
 * User: your name
 * Это твой алгоритм AI для игры. Реализуй его на свое усмотрение.
 * Обрати внимание на {@see YourSolverTest} - там приготовлен тестовый
 * фреймворк для тебя.
 */
public class YourSolver implements Solver<Board> {

    private static final Logger log = Logger.getLogger(YourSolver.class);

    private Dice dice;
    private Board board;

    private BlackPoints blackPoints;
    private Snake snake;
    private Target target;

    private List<Direction> directions;

    YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;

        blackPoints = new BlackPoints();
        snake = new Snake(board);
        target = new Target(snake);

        snake.checkSnakeCondition();
        log.info("Good - " + snake.getGood());
        log.info("Bad - " + snake.getBad());

        if (snake.getHeadPoint() != null) {

            Direction direction = blackPoints.check(snake.getHeadPoint());
            if(direction != null){
                snake.remove(direction);
            }

            snake.checkBadAround();
            snake.checkGoodsAround();
            snake.checkDirection();

            // chose target and move there
            target.execute();
        }

        directions = snake.getDirections();

        log.info("Available directions : " + directions);

        Random rand = new Random();
        int randomIndex = rand.nextInt(directions.size());
        Direction randomDirection = directions.get(randomIndex);

        if (board.isGameOver()) return "";

        return randomDirection.toString();
    }

    public static void main(String... args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "https://game2.epam-bot-challenge.com.ua/codenjoy-contest/board/player/dyvakyurii@gmail.com?code=10427676249616874",
                new YourSolver(new RandomDice()),
                new Board());
    }
}
