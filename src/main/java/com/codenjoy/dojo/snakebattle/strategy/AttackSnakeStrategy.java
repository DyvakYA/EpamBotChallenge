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

public class AttackSnakeStrategy implements SnakeStrategy {

    private static final Logger log = Logger.getLogger(AttackSnakeStrategy.class);

    private Board board;
    private BlackPoints blackPoints = BlackPoints.getInstance();

    public AttackSnakeStrategy(Board board) {
        this.board = board;
    }

    @Override
    public Point getTarget() {

        List<Point> targets = Collections.emptyList();

        List<Point> enemies = board.get(
                ENEMY_HEAD_FLY,
                ENEMY_HEAD_DOWN,
                ENEMY_HEAD_LEFT,
                ENEMY_HEAD_RIGHT,
                ENEMY_HEAD_UP);
        enemies = blackPoints.deleteBlackPoints(enemies);
        log.info("ENEMIES - " + enemies);

        List<Point> stuff = board.get(FURY_PILL, FLYING_PILL);
        stuff = blackPoints.deleteBlackPoints(stuff);
        log.info("STUFF: " + stuff);

        List<Point> golds = board.get(GOLD);
        golds = blackPoints.deleteBlackPoints(golds);
        log.info("STUFF: " + stuff);

        List<Point> apples = board.get(APPLE);
        apples = blackPoints.deleteBlackPoints(apples);
        log.info("APPLES: " + apples);

        if (board.getEnemySize() < board.getSnakeSize()) {
            log.info("My size: " + board.getSnakeSize() + " Enemy size : " + board.getEnemySize());
            if (targets.isEmpty()) {
                if (!enemies.isEmpty()) {
                    Point enemy = SnakeTargetUtil.getNearestTarget(board.getMe(), enemies);
                    targets = enemies;

                }
            }
        }
        if (targets.isEmpty()) {
            if (!stuff.isEmpty()) {
                Point stuffPoint = SnakeTargetUtil.getNearestTarget(board.getMe(), stuff);
                if (stuffPoint != null && board.getMe().distance(stuffPoint) < 4) {
                    targets = stuff;
                }
            }
        }
        if (targets.isEmpty()) {
            if (!apples.isEmpty()) {
                Point applePoint = SnakeTargetUtil.getNearestTarget(board.getMe(), apples);
                if (applePoint != null) {
                    targets = apples;
                }
            }
        }
        if (targets.isEmpty()) {
            if (!golds.isEmpty()) {
                Point gold = SnakeTargetUtil.getNearestTarget(board.getMe(), golds);
                if (gold != null) {
                    targets = golds;
                }
            }
        }

        log.info("TARGET LIST: " + targets);
        return SnakeTargetUtil.getNearestTarget(board.getMe(), targets);
    }
}
