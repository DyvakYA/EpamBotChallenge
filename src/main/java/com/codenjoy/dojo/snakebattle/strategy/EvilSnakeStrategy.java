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

public class EvilSnakeStrategy implements SnakeStrategy {

    private static final Logger log = Logger.getLogger(EvilSnakeStrategy.class);

    private Board board;
    private BlackPoints blackPoints = BlackPoints.getInstance();

    public EvilSnakeStrategy(Board board) {
        this.board = board;
    }

    @Override
    public Point getTarget() {
        List<Point> targets = Collections.emptyList();

        if (targets.isEmpty()) {
            List<Point> enemies = board.get(
                    ENEMY_HEAD_FLY,
                    ENEMY_HEAD_DOWN,
                    ENEMY_HEAD_LEFT,
                    ENEMY_HEAD_RIGHT,
                    ENEMY_HEAD_UP,
                    ENEMY_BODY_HORIZONTAL,
                    ENEMY_BODY_VERTICAL,
                    ENEMY_BODY_LEFT_DOWN,
                    ENEMY_BODY_RIGHT_DOWN,
                    ENEMY_BODY_LEFT_UP,
                    ENEMY_BODY_RIGHT_UP,
                    ENEMY_TAIL_END_LEFT,
                    ENEMY_TAIL_END_RIGHT,
                    ENEMY_TAIL_END_UP,
                    ENEMY_TAIL_END_DOWN);
            enemies = blackPoints.deleteBlackPoints(enemies);
            if (!enemies.isEmpty()) {
                Point enemy = SnakeTargetUtil.getNearestTarget(board.getMe(), enemies);
                if (enemy != null && board.getMe().distance(enemy) < 8) {
                    targets = enemies;
                }
            }
        }

        if (targets.isEmpty()) {
            List<Point> stones = board.get(STONE);
            stones = blackPoints.deleteBlackPoints(stones);
            if (!stones.isEmpty()) {
                Point stone = SnakeTargetUtil.getNearestTarget(board.getMe(), stones);
                if (stone != null && board.getMe().distance(stone) < 8) {
                    targets = stones;
                }
            }
        }

        if (targets.isEmpty()) {
            List<Point> golds = board.get(GOLD);
            golds = blackPoints.deleteBlackPoints(golds);
            if (!golds.isEmpty()) {
                Point goldPoint = SnakeTargetUtil.getNearestTarget(board.getMe(), golds);
                if (goldPoint != null && board.getMe().distance(goldPoint) < 10) {
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
