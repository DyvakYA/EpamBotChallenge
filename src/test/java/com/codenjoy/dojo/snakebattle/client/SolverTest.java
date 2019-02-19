package com.codenjoy.dojo.snakebattle.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
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
import com.codenjoy.dojo.services.Direction;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static com.codenjoy.dojo.services.Direction.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author K.ilya
 * Это пример для реализации unit-тестов твоего бота
 * Необходимо раскомментировать существующие тесты, добиться их выполнения ботом.
 * Затем создавай свои тесты, улучшай бота и проверяй что не испортил предыдущие достижения.
 */

public class SolverTest {

    private static final Logger log = Logger.getLogger(SolverTest.class);

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
    public void should() {
        assertAI("☼☼☼☼☼☼☼☼" +
                "☼☼     ☼" +
                "╘►     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼☼☼☼☼☼☼", RIGHT);
    }

    @Test
    public void should1() {


        assertAI("☼☼☼☼☼☼☼☼" +
                "☼☼     ☼" +
                "☼╘►    ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼☼☼☼☼☼☼", RIGHT);
    }

    @Test
    public void should2() {
        assertAI("☼☼☼☼☼☼☼☼" +
                "☼☼     ☼" +
                "☼#╘►   ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼☼☼☼☼☼☼", RIGHT);
    }

    @Test
    public void should4() {
        assertAI("☼☼☼☼☼☼☼☼" +
                "☼☼  æ  ☼" +
                "☼☼  ˅  ☼" +
                "☼☼     ☼" +
                "☼☼ ╘►  ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼☼☼☼☼☼☼", RIGHT);
    }

    @Test
    public void should5() {
        assertAI("☼☼☼☼☼☼☼☼" +
                "☼☼     ☼" +
                "☼☼ ╘♥☼ ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼☼☼☼☼☼☼", RIGHT);
    }

    @Test
    public void should6() {
        assertAI("☼☼☼☼☼☼☼☼" +
                "☼☼     ☼" +
                "☼☼ ╘♠☼ ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼☼☼☼☼☼☼", RIGHT);
    }

    @Test
    public void should7() {

        assertAI("☼☼☼☼☼☼☼☼" +
                "☼☼  ●   ☼" +
                "☼☼ ╘♥ ●☼" +
                "☼☼     ☼" +
                "☼☼  ●  ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼☼☼☼☼☼☼", DOWN);

    }

    @Test
    public void should8() {
        assertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼☼         ○                 ☼" +
                "*ø           ×>              ☼" +
                "☼☼  ○    ●         ○         ☼" +
                "☼☼                      ○    ☼" +
                "☼☼ ○         ●    ○          ☼" +
                "☼☼     ☼☼☼☼☼     ○           ☼" +
                "☼☼     ☼                     ☼" +
                "*ø     ☼☼☼     ○  ☼☼☼☼#      ☼" +
                "☼☼     ☼          ☼   ☼  ●   ☼" +
                "☼☼     ☼☼☼☼☼      ☼☼☼☼#      ☼" +
                "☼☼                ☼         ○☼" +
                "☼☼○               ☼         ☼" +
                "☼☼    ●  ○      $  ●         ☼" +
                "☼#                           ☼" +
                "☼☼ ●         ○               ☼" +
                "☼☼        ☼☼☼               ○ ☼" +
                "☼☼   ○   ☼  ☼        ○       ☼" +
                "☼☼      ☼☼☼☼#     ☼☼   ☼#    ☼" +
                "☼☼     ©☼   ☼   ● ☼ ☼ ☼ ☼ ○  ☼" +
                "☼#      ☼   ☼     ☼  ☼  ☼    ☼" +
                "☼☼                ☼   © ☼    ☼" +
                "☼☼     ●          ☼     ☼    ☼" +
                "☼☼    ®○                     ☼" +
                "☼☼               ○  ○        ☼" +
                "☼☼ ○○   ○      ●○        ○   ☼" +
                "☼#           ×>         ©    ☼" +
                "☼☼                ○           ☼" +
                "☼☼╘♥   ○                     ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", UP);
    }

    @Test
    public void targetChoseMethodTest() {


        String board = "☼☼☼☼☼☼☼☼" +
                "☼☼  $   ☼" +
                "☼☼ ╘♥ $☼" +
                "☼☼     ☼" +
                "☼☼  $  ☼" +
                "☼☼     ☼" +
                "☼☼     ☼" +
                "☼☼☼☼☼☼☼☼";

        Board myBoard = board(board);

        //Point point = target.getTarget(myBoard.get(Elements.GOLD));
        //log.info(point);
        //assertTrue(point.getX() == 1);
    }

    @Test
    public void directionTest() {

        log.info(Direction.valueOf(0));
        log.info(Direction.valueOf(1));
        log.info(Direction.valueOf(2));
        log.info(Direction.valueOf(3));
        log.info(Direction.valueOf(4));
        log.info(Direction.valueOf(5));
        log.info(Direction.valueOf(6));


    }

    private void assertAI(String board, Direction expected) {
        String actual = ai.get(board(board));
        assertEquals(expected.toString(), actual);
    }

    private void dice(Direction direction) {
        when(dice.next(anyInt())).thenReturn(direction.value());
    }
}
