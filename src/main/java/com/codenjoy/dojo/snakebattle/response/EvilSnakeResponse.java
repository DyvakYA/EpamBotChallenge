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
import com.codenjoy.dojo.snakebattle.utils.SnakeDirectionUtil;
import org.apache.log4j.Logger;

import java.util.List;

public class EvilSnakeResponse extends AbstractSnakeResponse {

    private static final Logger log = Logger.getLogger(EvilSnakeResponse.class);

    public EvilSnakeResponse(Board board) {
        super();
        this.board = board;
        this.myPoint = board.getMe();
    }

    @Override
    public String generate(List<Direction> snakeDirections, Point target) {
        this.directions = snakeDirections;
        this.target = target;

        checkBackDirection();
        if (!isObsession()) {
            // try move to target
            moveToTarget();
        }

        getRandomDirection();

        addMove(targetDirection);

        if (targetDirection == null) {
            super.directions = SnakeDirectionUtil.checkFreeCellsAround(board);
            checkBackDirection();
            getRandomDirection();
            answer = targetDirection.toString();
        }

        return targetDirection.toString();
    }
}
