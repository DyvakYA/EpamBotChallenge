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
import org.apache.log4j.Logger;


public class SnakeFactory {

    private static final Logger log = Logger.getLogger(SnakeFactory.class);

    private static int extraCapacity = 0;

    public static Snake createSnake(Board board) {

//        if (board.getEnemies().size() == 1) {
//            log.info("ATTACK --> " + extraCapacity);
//            return new AttackSnake(board);
//        } else
        if (board.isHeadEvil()) {
            log.info("EVIL --> " + extraCapacity);
            if (extraCapacity == 0) {
                extraCapacity = 9;
                return new EvilSnake(board);
            } else if (extraCapacity == 1) {
                log.info("return original snake");
                extraCapacity = 0;
                return new OriginalSnake(board);
            } else {
                extraCapacity--;
                return new EvilSnake(board);
            }
        } else if (board.isHeadFly()) {
            log.info("FLY --> " + extraCapacity);
            if (extraCapacity == 0) {
                extraCapacity = 9;
                return new FlySnake(board);
            } else if (extraCapacity == 1) {
                log.info("return original snake");
                extraCapacity = 0;
                return new OriginalSnake(board);
            } else {
                extraCapacity--;
                return new FlySnake(board);
            }

        } else if (board.getSnakeSize() >= 6) {
            extraCapacity = 0;
            log.info("LARGE --> " + extraCapacity);
            return new LargeSnake(board);
        } else {
            extraCapacity = 0;
            log.info("ORIGINAL --> " + extraCapacity);
            return new OriginalSnake(board);
        }
    }
}
