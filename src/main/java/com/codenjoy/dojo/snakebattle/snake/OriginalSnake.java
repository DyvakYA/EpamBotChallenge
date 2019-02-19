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
import com.codenjoy.dojo.snakebattle.direction.OriginalSnakeDirection;
import com.codenjoy.dojo.snakebattle.direction.SnakeDirection;
import com.codenjoy.dojo.snakebattle.response.OriginalSnakeResponse;
import com.codenjoy.dojo.snakebattle.response.SnakeResponse;
import com.codenjoy.dojo.snakebattle.strategy.OriginalSnakeStrategy;
import com.codenjoy.dojo.snakebattle.strategy.SnakeStrategy;

public class OriginalSnake implements Snake {

    private Board board;

    public OriginalSnake(Board board) {
        this.board = board;
    }

    @Override
    public SnakeStrategy getStrategy() {
        return new OriginalSnakeStrategy(board);
    }

    @Override
    public SnakeDirection getDirection() {
        return new OriginalSnakeDirection(board);
    }

    @Override
    public SnakeResponse getSnakeResponse() {
        return new OriginalSnakeResponse(board);
    }
}
