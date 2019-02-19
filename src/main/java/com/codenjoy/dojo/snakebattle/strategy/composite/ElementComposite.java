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
import com.codenjoy.dojo.snakebattle.model.Elements;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.codenjoy.dojo.snakebattle.model.Elements.APPLE;
import static com.codenjoy.dojo.snakebattle.model.Elements.GOLD;


public class ElementComposite implements ElementComponent {

    private static final Logger log = Logger.getLogger(ElementComposite.class);

    private Elements element;

    private Point point;

    private double distance = 0;
    private double totalDistance;

    private int score;
    private int totalScore;

    private List<ElementComponent> components;

    public ElementComposite() {
        components = new ArrayList<>();
    }

    @Override
    public double getTotalDistance(double total) {
        double result = total + distance;
        for (ElementComponent component : components) {
            result = component.getTotalDistance(result);
        }
        return result;
    }

    @Override
    public double getShorterDistance() {
        double minDistance = 0;
        double distance = 0;
        Optional<ElementComponent> optional = components.stream()
                .min(Comparator.comparingDouble(e -> e.getDistance()));
        if (optional.isPresent()) {
            ElementComponent component = optional.get();
            distance = component.getDistance();
            minDistance = component.getShorterDistance();
        }
        return distance + minDistance;
    }

    @Override
    public List<Point> getShorterRoute(List<Point> points) {
        points.add(point);
        log.info(components);
        Optional<ElementComponent> optional = components.stream()
                .min(Comparator.comparingDouble(e -> e.getDistance()));
        if (optional.isPresent()) {
            ElementComponent component = optional.get();
            return component.getShorterRoute(points);
        } else {
            return points;
        }
    }

    public List<Point> getShorterRoute() {
        return getShorterRoute(new ArrayList<>());
    }

    @Override
    public int getShorterRouteScore() {
        log.info(score);
        int score = this.score;
        Optional<ElementComponent> optional = components.stream()
                .min(Comparator.comparingDouble(e -> e.getDistance()));
        if (optional.isPresent()) {
            ElementComponent component = optional.get();
            score = score + component.getShorterRouteScore();
        }
        return score;
    }

    public void addComponent(ElementComponent component) {
        components.add(component);
    }

    public void removeComponent(ElementComposite component) {
        components.remove(component);
    }

    public Elements getElement() {
        return element;
    }

    public void setElement(Elements element) {
        if (element.equals(GOLD)) {
            score = 5;
        } else if (element.equals(APPLE)) {
            score = 1;
        } else {
            score = 0;
        }
        this.element = element;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<ElementComponent> getComponents() {
        return components;
    }

    public void setComponents(List<ElementComponent> components) {
        this.components = components;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "ElementComposite{" +
                ", element=" + element +
                ", point=" + point +
                ", distance=" + distance +
                ", totalDistance=" + totalDistance +
                ", score=" + score +
                ", totalScore=" + totalScore +
                ", " + components +
                '}';
    }
}
