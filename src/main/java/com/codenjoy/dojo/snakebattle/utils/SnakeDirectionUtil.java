package com.codenjoy.dojo.snakebattle.utils;

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
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codenjoy.dojo.services.Direction.*;
import static com.codenjoy.dojo.snakebattle.model.Elements.*;

public class SnakeDirectionUtil {

    private static final Logger log = Logger.getLogger(SnakeDirectionUtil.class);

    public static List<Direction> getDirections(List<Direction> source, Direction... direction) {
        //log.info("get directions" + source + " - " + direction);
        List<Direction> result = Arrays.stream(direction)
                .filter(d -> source.contains(d))
                .collect(Collectors.toList());
        //log.info(result);
        if (!result.isEmpty()) {
            return result;
        } else {
            return source;
        }
    }

    public static List<Direction> checkFreeCellsAround(Board board) {
        log.info("Check free cells around");
        Point headPoint = board.getMe();
        List<Direction> directions = new ArrayList<>();

        List<Elements> elements = new ArrayList<>();
        elements.add(NONE);
        elements.add(TAIL_END_DOWN);
        elements.add(TAIL_END_UP);
        elements.add(TAIL_END_RIGHT);
        elements.add(TAIL_END_LEFT);
        elements.add(BODY_HORIZONTAL);
        elements.add(BODY_VERTICAL);
        elements.add(BODY_LEFT_DOWN);
        elements.add(BODY_LEFT_UP);
        elements.add(BODY_RIGHT_DOWN);
        elements.add(BODY_RIGHT_UP);

        if (elements.contains(board.getRightElement(headPoint))) {
            directions.add(RIGHT);
        }
        if (elements.contains(board.getLeftElement(headPoint))) {
            directions.add(LEFT);
        }
        if (elements.contains(board.getDownElement(headPoint))) {
            directions.add(DOWN);
        }
        if (elements.contains(board.getUpElement(headPoint))) {
            directions.add(UP);
        }

        log.info(directions);
        return directions;
    }

    public static String turnBack(Direction direction) {
        if (direction.equals(Direction.RIGHT)) {
            return "UP, LEFT";
        } else if (direction.equals(Direction.LEFT)) {
            return "DOWN, RIGHT";
        } else if (direction.equals(Direction.UP)) {
            return "LEFT, DOWN";
        } else if (direction.equals(Direction.DOWN)) {
            return "RIGHT, UP";
        } else {
            return null;
        }
    }
}
