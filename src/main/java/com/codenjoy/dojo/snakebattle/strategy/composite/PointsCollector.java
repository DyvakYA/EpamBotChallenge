package com.codenjoy.dojo.snakebattle.strategy.composite;

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
import com.codenjoy.dojo.snakebattle.client.Board;
import com.codenjoy.dojo.snakebattle.model.Elements;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PointsCollector {

    private static final Logger log = Logger.getLogger(PointsCollector.class);

    public static ElementComposite collect(Board board, List<Point> treePoints, Point point, Elements... elements) {

        ElementComposite component = new ElementComposite();
        Elements element = board.getAt(point);
        component.setElement(element);
        component.setPoint(point);
        List<Point> points = board.get(elements);
        //log.info(points);
        points.removeAll(treePoints);
        for (Point componentPoint : points) {
            List<Point> newList = new ArrayList<>(treePoints);
            newList.add(componentPoint);
            ElementComposite newComponent = PointsCollector.collect(board, newList, componentPoint, elements);
            double distance = point.distance(componentPoint);
            newComponent.setDistance(distance);
            component.addComponent(newComponent);
        }
        return component;
    }

    public static ElementComposite collect(Board board, Point point, Elements... elements) {
        return collect(board, new ArrayList<>(), point, elements);
    }
}
