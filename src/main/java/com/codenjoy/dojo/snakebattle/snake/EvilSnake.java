package com.codenjoy.dojo.snakebattle.snake;

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

import com.codenjoy.dojo.snakebattle.client.Board;
import com.codenjoy.dojo.snakebattle.direction.EvilSnakeDirection;
import com.codenjoy.dojo.snakebattle.direction.SnakeDirection;
import com.codenjoy.dojo.snakebattle.response.EvilSnakeResponse;
import com.codenjoy.dojo.snakebattle.response.SnakeResponse;
import com.codenjoy.dojo.snakebattle.strategy.EvilSnakeStrategy;
import com.codenjoy.dojo.snakebattle.strategy.SnakeStrategy;

public class EvilSnake implements Snake {

    private Board board;

    public EvilSnake(Board board) {
        this.board = board;
    }


    @Override
    public SnakeStrategy getStrategy() {
        return new EvilSnakeStrategy(board);
    }

    @Override
    public SnakeDirection getDirection() {
        return new EvilSnakeDirection(board);
    }

    @Override
    public SnakeResponse getSnakeResponse() {
        return new EvilSnakeResponse(board);
    }
}
