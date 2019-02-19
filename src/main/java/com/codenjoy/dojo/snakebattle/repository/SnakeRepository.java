package com.codenjoy.dojo.snakebattle.repository;

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
import com.codenjoy.dojo.snakebattle.model.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.codenjoy.dojo.services.Direction.*;
import static com.codenjoy.dojo.snakebattle.model.Elements.*;

public class SnakeRepository {


    public SnakeRepository() {
    }

    public Set<Elements> getGoodForOriginal() {
        Set<Elements> result = new HashSet<>(Arrays.asList(APPLE, GOLD, FURY_PILL, FLYING_PILL));
        return result;
    }

    public Set<Elements> getBadForOriginal() {
        Set<Elements> result = new HashSet<>(Arrays.asList(WALL, STONE, START_FLOOR,
                BODY_RIGHT_DOWN, BODY_LEFT_DOWN, BODY_RIGHT_UP, BODY_RIGHT_DOWN, BODY_HORIZONTAL, BODY_VERTICAL,
                TAIL_END_DOWN, TAIL_END_UP, TAIL_END_LEFT, TAIL_END_RIGHT,
                ENEMY_HEAD_DOWN, ENEMY_HEAD_LEFT, ENEMY_HEAD_RIGHT, ENEMY_HEAD_UP,
                ENEMY_BODY_RIGHT_DOWN, ENEMY_BODY_LEFT_DOWN, ENEMY_BODY_RIGHT_UP, ENEMY_BODY_LEFT_UP, ENEMY_BODY_HORIZONTAL, ENEMY_BODY_VERTICAL,
                ENEMY_TAIL_END_DOWN, ENEMY_TAIL_END_LEFT, ENEMY_TAIL_END_RIGHT, ENEMY_TAIL_END_UP));
        return result;
    }

    public Set<Elements> getBadForEvil() {
        Set<Elements> result = new HashSet<>(Arrays.asList(WALL, START_FLOOR));
        return result;
    }

    public Set<Elements> getGoodForEvil() {
        Set<Elements> result = new HashSet<>(Arrays.asList(STONE, APPLE, GOLD, FURY_PILL, FLYING_PILL,
                ENEMY_HEAD_DOWN, ENEMY_HEAD_LEFT, ENEMY_HEAD_RIGHT, ENEMY_HEAD_UP,
                ENEMY_BODY_RIGHT_DOWN, ENEMY_BODY_LEFT_DOWN, ENEMY_BODY_RIGHT_UP, ENEMY_BODY_LEFT_UP, ENEMY_BODY_HORIZONTAL, ENEMY_BODY_VERTICAL,
                ENEMY_TAIL_END_DOWN, ENEMY_TAIL_END_LEFT, ENEMY_TAIL_END_RIGHT, ENEMY_TAIL_END_UP));
        return result;
    }

    public Set<Elements> getGoodForLargeSnake() {
        Set<Elements> result = new HashSet<>(Arrays.asList(APPLE, GOLD, FURY_PILL, FLYING_PILL, STONE));
        return result;
    }

    public Set<Elements> getBadForLargeSnake() {
        Set<Elements> result = new HashSet<>(Arrays.asList(WALL, START_FLOOR,
                BODY_RIGHT_DOWN, BODY_LEFT_DOWN, BODY_RIGHT_UP, BODY_RIGHT_DOWN, BODY_HORIZONTAL, BODY_VERTICAL,
                TAIL_END_DOWN, TAIL_END_UP, TAIL_END_LEFT, TAIL_END_RIGHT,
                ENEMY_HEAD_DOWN, ENEMY_HEAD_LEFT, ENEMY_HEAD_RIGHT, ENEMY_HEAD_UP,
                ENEMY_BODY_RIGHT_DOWN, ENEMY_BODY_LEFT_DOWN, ENEMY_BODY_RIGHT_UP, ENEMY_BODY_LEFT_UP, ENEMY_BODY_HORIZONTAL, ENEMY_BODY_VERTICAL,
                ENEMY_TAIL_END_DOWN, ENEMY_TAIL_END_LEFT, ENEMY_TAIL_END_RIGHT, ENEMY_TAIL_END_UP));
        return result;
    }

    public Set<Elements> getGoodForFly() {
        Set<Elements> result = new HashSet<>(Arrays.asList(APPLE, GOLD, FURY_PILL, FLYING_PILL,
                ENEMY_HEAD_DOWN, ENEMY_HEAD_LEFT, ENEMY_HEAD_RIGHT, ENEMY_HEAD_UP,
                ENEMY_BODY_RIGHT_DOWN, ENEMY_BODY_LEFT_DOWN, ENEMY_BODY_RIGHT_UP, ENEMY_BODY_LEFT_UP, ENEMY_BODY_HORIZONTAL, ENEMY_BODY_VERTICAL,
                ENEMY_TAIL_END_DOWN, ENEMY_TAIL_END_LEFT, ENEMY_TAIL_END_RIGHT, ENEMY_TAIL_END_UP));
        return result;
    }

    public Set<Elements> getBadForFly() {
        Set<Elements> result = new HashSet<>(Arrays.asList(WALL, START_FLOOR));
        return result;
    }

    public List<LinkedList<Direction>> getObsessionList() {
        List<LinkedList<Direction>> obsessions = new ArrayList<>();
        obsessions.add(new LinkedList<>(Arrays.asList(UP, LEFT, DOWN, RIGHT, UP, LEFT)));
        obsessions.add(new LinkedList<>(Arrays.asList(UP, RIGHT, DOWN, LEFT, UP, RIGHT)));
        obsessions.add(new LinkedList<>(Arrays.asList(LEFT, LEFT, DOWN, RIGHT, RIGHT, UP)));
        obsessions.add(new LinkedList<>(Arrays.asList(RIGHT, RIGHT, DOWN, LEFT, LEFT, UP)));
        obsessions.add(new LinkedList<>(Arrays.asList(LEFT, LEFT, UP, LEFT, LEFT, DOWN)));
        obsessions.add(new LinkedList<>(Arrays.asList(RIGHT, RIGHT, UP, LEFT, LEFT, DOWN)));
        obsessions.add(new LinkedList<>(Arrays.asList(UP, UP, RIGHT, DOWN, DOWN, LEFT)));
        obsessions.add(new LinkedList<>(Arrays.asList(DOWN, DOWN, RIGHT, UP, UP, LEFT)));
        obsessions.add(new LinkedList<>(Arrays.asList(UP, UP, LEFT, DOWN, DOWN, RIGHT)));
        obsessions.add(new LinkedList<>(Arrays.asList(DOWN, DOWN, LEFT, UP, UP, RIGHT)));
        return obsessions;
    }

    public Set<Elements> getGoodForAttack() {
        Set<Elements> result = new HashSet<>(Arrays.asList(APPLE, GOLD, FURY_PILL, FLYING_PILL,
                ENEMY_HEAD_DOWN, ENEMY_HEAD_LEFT, ENEMY_HEAD_RIGHT, ENEMY_HEAD_UP));
        return result;
    }

    public Set<Elements> getBadForAttack() {
        Set<Elements> result = new HashSet<>(Arrays.asList(WALL, START_FLOOR, STONE,
                BODY_RIGHT_DOWN, BODY_LEFT_DOWN, BODY_RIGHT_UP, BODY_RIGHT_DOWN, BODY_HORIZONTAL, BODY_VERTICAL,
                TAIL_END_DOWN, TAIL_END_UP, TAIL_END_LEFT, TAIL_END_RIGHT,
                ENEMY_BODY_RIGHT_DOWN, ENEMY_BODY_LEFT_DOWN, ENEMY_BODY_RIGHT_UP, ENEMY_BODY_LEFT_UP, ENEMY_BODY_HORIZONTAL, ENEMY_BODY_VERTICAL,
                ENEMY_TAIL_END_DOWN, ENEMY_TAIL_END_LEFT, ENEMY_TAIL_END_RIGHT, ENEMY_TAIL_END_UP));
        return result;
    }
}
