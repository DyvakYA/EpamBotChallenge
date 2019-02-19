package com.codenjoy.dojo.snakebattle.direction;

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
import com.codenjoy.dojo.snakebattle.client.Board;
import org.apache.log4j.Logger;

import java.util.List;

public class EvilSnakeDirection extends AbstractSnakeDirection {

    public static final Logger log = Logger.getLogger(EvilSnakeDirection.class);

    public EvilSnakeDirection(Board board) {
        super(board);
    }

    @Override
    public List<Direction> getDirections() {
        good = repository.getGoodForEvil();
        bad = repository.getBadForEvil();
        log.info("Good - " + good);
        log.info("Bad - " + bad);
        checkBlackPoints();
        checkBadAround();
        //checkGoodsAround();
        return directions;
    }
}
