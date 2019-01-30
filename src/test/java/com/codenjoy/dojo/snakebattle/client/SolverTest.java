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

import java.util.ArrayList;
import java.util.List;

import static com.codenjoy.dojo.services.Direction.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
                "☼☼     ☼" +
                "☼☼ ╘►  ☼" +
                "☼☼     ☼" +
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
        assertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼☼         ○                 ☼" +
                "*ø           ×>              ☼" +
                "☼☼  ○    ●         ○         ☼" +
                "☼☼                      ○    ☼" +
                "☼☼ ○         ●    ○$         ☼" +
                "☼☼     ☼☼☼☼☼     ○           ☼" +
                "☼☼     ☼ $                   ☼" +
                "*ø     ☼☼☼     ○  ☼☼☼☼#      ☼" +
                "☼☼     ☼          ☼   ☼  ●   ☼" +
                "☼☼     ☼☼☼☼☼      ☼☼☼☼#      ☼" +
                "☼☼                ☼         ○☼" +
                "☼☼○               ☼         ☼" +
                "☼☼    ●  ○         ●         ☼" +
                "☼#                       ►   ☼" +
                "☼☼ ●         ○               ☼" +
                "☼☼        ☼☼☼           $   ○ ☼" +
                "☼☼   ○   ☼  ☼        ○       ☼" +
                "☼☼      ☼☼☼☼#     ☼☼   ☼#    ☼" +
                "☼☼     ©☼   ☼   ● ☼ ☼ ☼ ☼ ○  ☼" +
                "☼#      ☼   ☼     ☼  ☼  ☼    ☼" +
                "☼☼              $ ☼   © ☼    ☼" +
                "☼☼     ●          ☼     ☼    ☼" +
                "☼☼    ®○                     ☼" +
                "☼☼               ○  ○        ☼" +
                "☼☼ ○○   ○      ●○        ○   ☼" +
                "☼#           ×>         ©    ☼" +
                "☼☼                ○           ☼" +
                "☼☼     ○                     ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", DOWN);
    }

//    @Test
//    public void directionExtractorTest_checkSize() {
//        ai.setDirections(Direction.getValues());
//        ai.choseDirectionIfExist(RIGHT);
//        assertTrue(ai.getDirections().size() == 1);
//    }
//
//    @Test
//    public void directionExtractorTest_checkWhenRight() {
//        ai.setDirections(Direction.getValues());
//        ai.choseDirectionIfExist(RIGHT);
//        assertTrue(ai.getDirections().get(0).equals(RIGHT));
//    }
//
//    @Test
//    public void directionExtractorTest_checkWhenLeft() {
//        ai.setDirections(Direction.getValues());
//        ai.choseDirectionIfExist(LEFT);
//        assertTrue(ai.getDirections().get(0).equals(LEFT));
//    }
//
//    @Test
//    public void directionExtractorTest_checkWhenLeftAndRight() {
//        ai.setDirections(Direction.getValues());
//        ai.choseDirectionIfExist(LEFT, RIGHT);
//        assertTrue(ai.getDirections().get(1).equals(RIGHT));
//    }
//
//    @Test
//    public void directionExtractorTest_checkWhenLeftAndRightSizeEquals2() {
//        ai.setDirections(Direction.getValues());
//        ai.choseDirectionIfExist(LEFT, RIGHT);
//        assertTrue(ai.getDirections().size() == 2);
//    }
//
//    @Test
//    public void directionExtractorTest_checkWhenListNotFull() {
//        List<Direction> directions = new ArrayList<>();
//        directions.add(RIGHT);
//        directions.add(LEFT);
//        ai.setDirections(directions);
//        ai.choseDirectionIfExist(UP, DOWN);
//        log.info(ai.getDirections());
//        assertTrue(ai.getDirections().size() == 0);
//    }

    private void assertAI(String board, Direction expected) {
        String actual = ai.get(board(board));
        assertEquals(expected.toString(), actual);
    }

    private void dice(Direction direction) {
        when(dice.next(anyInt())).thenReturn(direction.value());
    }
}
