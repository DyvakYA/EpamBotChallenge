package com.codenjoy.dojo.snakebattle.client;

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

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.snakebattle.model.Pair;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.codenjoy.dojo.services.Direction.*;
import static com.codenjoy.dojo.services.Direction.DOWN;
import static com.codenjoy.dojo.services.Direction.UP;

public class BlackPoints {

    private static final Logger log = Logger.getLogger(BlackPoints.class);

    private List<Pair> blackPointsForGoods = new ArrayList<>();
    private List<Pair> blackPointsForSnake = new ArrayList<>();

    private BlackPoints() {
        blackPointsForGoods.add(new Pair(10, 12));
        blackPointsForGoods.add(new Pair(11, 12));

        blackPointsForGoods.add(new Pair(8, 20));
        blackPointsForGoods.add(new Pair(9, 20));
        blackPointsForGoods.add(new Pair(8, 22));
        blackPointsForGoods.add(new Pair(9, 22));

        blackPointsForGoods.add(new Pair(19, 10));
        blackPointsForGoods.add(new Pair(23, 10));
        blackPointsForGoods.add(new Pair(21, 10));

        blackPointsForGoods.add(new Pair(19, 20));
        blackPointsForGoods.add(new Pair(20, 20));
        blackPointsForGoods.add(new Pair(21, 20));
    }

    private static class Holder {
        static final BlackPoints INSTANCE = new BlackPoints();
    }

    public static BlackPoints getInstance() {
        return Holder.INSTANCE;
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
