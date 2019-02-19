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
import com.codenjoy.dojo.snakebattle.direction.SnakeDirection;
import com.codenjoy.dojo.snakebattle.response.SnakeResponse;
import com.codenjoy.dojo.snakebattle.snake.Snake;
import com.codenjoy.dojo.snakebattle.snake.SnakeFactory;
import com.codenjoy.dojo.snakebattle.strategy.SnakeStrategy;
import org.apache.log4j.Logger;

import java.net.URI;
import java.util.List;

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

    YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        log.info("*************************************");
        // Snake factory create snake
        Snake snake = SnakeFactory.createSnake(board);
        // Get snake strategy
        SnakeStrategy strategy = snake.getStrategy();
        Point target = strategy.getTarget();
        if (target != null) {
            log.info("TARGET: " + "Type - " + board.getAt(target) + " Point - " + target);
        }
        log.info("*************************************");
        //  Choose directions for snake
        SnakeDirection snakeDirection = snake.getDirection();
        List<Direction> directions = snakeDirection.getDirections();
        log.info("*************************************");
        // Produce answer for server
        SnakeResponse response = snake.getSnakeResponse();
        String answer = response.generate(directions, target);
        // if game is over
        if (board.isGameOver()) return "";
        //return random direction from list of available directions
        return answer;
    }

    public static void main(String... args) {
//        URI uri = URI.create("wss://game3.epam-bot-challenge.com.ua/codenjoy-contest/ws?user=dyvakyurii@gmail.com?code=6938453951410310450");
//        WebSocketRunner.run(uri, new YourSolver(new RandomDice()), new Board(), 1000);

        WebSocketRunner.runClient("https://game3.epam-bot-challenge.com.ua/codenjoy-contest/board/player/kfeskc9on9uncjs7hbs3?code=6938453951410310450", new YourSolver(new RandomDice()), new Board());
        //"wss://game2.epam-bot-challenge.com.ua/codenjoy-contest/ws?user=dyvakyurii@gmail.com&code=10427676249616874"
        // Reset
        // https://game2.epam-bot-challenge.com.ua/codenjoy-contest/rest/player/dyvakyurii@gmail.com/10427676249616874/reset

    }
}
