package com.codenjoy.dojo.snakebattle.strategy;

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
import com.codenjoy.dojo.snakebattle.client.BlackPoints;
import com.codenjoy.dojo.snakebattle.client.Board;
import com.codenjoy.dojo.snakebattle.utils.SnakeTargetUtil;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

import static com.codenjoy.dojo.snakebattle.model.Elements.*;

public class FlySnakeStrategy implements SnakeStrategy {

    private static final Logger log = Logger.getLogger(FlySnakeStrategy.class);

    private Board board;
    private BlackPoints blackPoints = BlackPoints.getInstance();

    public FlySnakeStrategy(Board board) {
        this.board = board;
    }

    @Override
    public Point getTarget() {

        List<Point> targets = Collections.emptyList();

        if (targets.isEmpty()) {
            List<Point> apples = board.get(APPLE);
            apples = blackPoints.deleteBlackPoints(apples);
            if (!apples.isEmpty()) {
                Point apple = SnakeTargetUtil.getNearestTarget(board.getMe(), apples);
                if (apple != null && board.getMe().distance(apple) < 2) {
                    targets = apples;
                }
            }
        }
        if (targets.isEmpty()) {
            List<Point> stuff = board.get(FURY_PILL);
            stuff = blackPoints.deleteBlackPoints(stuff);
            if (!stuff.isEmpty()) {
                Point stuffPoint = SnakeTargetUtil.getNearestTarget(board.getMe(), stuff);
                if (stuffPoint != null && board.getMe().distance(stuffPoint) < 5) {
                    targets = stuff;
                }
            }
        }
        if (targets.isEmpty()) {
            List<Point> golds = board.get(GOLD);
            golds = blackPoints.deleteBlackPoints(golds);
            if (!golds.isEmpty()) {
                Point gold = SnakeTargetUtil.getNearestTarget(board.getMe(), golds);
                if (gold != null && board.getMe().distance(gold) < 10) {
                    targets = golds;
                }
            }
        }
        if (targets.isEmpty()) {
            List<Point> goods = board.get(FURY_PILL, APPLE, GOLD);
            goods = blackPoints.deleteBlackPoints(goods);
            if (!goods.isEmpty()) {
                Point applePoint = SnakeTargetUtil.getNearestTarget(board.getMe(), goods);
                if (applePoint != null) {
                    targets = goods;
                }
            }
        }

        log.info("TARGET LIST: " + targets);
        return SnakeTargetUtil.getNearestTarget(board.getMe(), targets);
    }
}
