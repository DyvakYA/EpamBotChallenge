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

import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.snakebattle.model.Elements;
import com.codenjoy.dojo.snakebattle.strategy.composite.ElementComposite;
import com.codenjoy.dojo.snakebattle.strategy.composite.PointsCollector;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.codenjoy.dojo.snakebattle.model.Elements.APPLE;
import static com.codenjoy.dojo.snakebattle.model.Elements.GOLD;
import static org.mockito.Mockito.mock;

public class CompositeTest {

    private static final Logger log = Logger.getLogger(CompositeTest.class);

    private Dice dice;
    private YourSolver ai;

    @Before
    public void setup() {
        dice = mock(Dice.class);
        ai = new YourSolver(dice);
    }

    private Board board(String board) {
        return (Board) new Board().forString(board);
    }

    @Test
    public void should8() {
        String field = "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼☼                           ☼" +
                "☼☼                           ☼" +
                "☼☼                           ☼" +
                "☼☼              $            ☼" +
                "☼☼                           ☼" +
                "☼☼                           ☼" +
                "☼☼                           ☼" +
                "☼☼        $            $     ☼" +
                "☼☼                           ☼" +
                "☼☼                           ☼" +
                "☼☼              $            ☼" +
                "☼☼                           ☼" +
                "☼☼                   $       ☼" +
                "☼☼                           ☼" +
                "☼☼      $                    ☼" +
                "☼☼                           ☼" +
                "☼☼                   $       ☼" +
                "☼☼                           ☼" +
                "☼☼         $                 ☼" +
                "☼☼                           ☼" +
                "☼☼                           ☼" +
                "☼☼                           ☼" +
                "☼☼                           ☼" +
                "☼☼                  $        ☼" +
                "☼☼                           ☼" +
                "☼☼                           ☼" +
                "☼☼      $                    ☼" +
                "☼☼╘♥                         ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼";
        Board board = board(field);
        long before = System.currentTimeMillis();
        ElementComposite component = PointsCollector.collect(board, board.getMe(), GOLD);
        log.info(component);
        //log.info(component.getTotalDistance(0));
        log.info(component.getShorterRoute());
        log.info(component.getShorterDistance());
        long after = System.currentTimeMillis();
        log.info( "Time : " + (after - before) + "ms");
        log.info(new PointImpl(6, 5).distance(new PointImpl(2, 17)));
        log.info(new PointImpl(6, 5).distance(new PointImpl(19, 3)));
        log.info(new PointImpl(6, 5).distance(new PointImpl(22, 6)));
        //log.info(new PointImpl(22, 6).distance(new PointImpl(26, 10)));
        log.info("SCORE: " + component.getShorterRouteScore());


    }
}
