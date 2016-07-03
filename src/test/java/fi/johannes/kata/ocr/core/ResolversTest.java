/*
 * The MIT License
 *
 * Copyright 2016 Johannes Sarpola.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fi.johannes.kata.ocr.core;

import fi.johannes.kata.ocr.cells.Cell;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Johannes Sarpola
 */
public class ResolversTest {

    public ResolversTest() {
    }
    String zero = " _ " + "| |" + "|_|";
    String one = "   " + "  |" + "  |";
    String two = " _ " + " _|" + "|_ ";

    String three = " _ " + " _|" + " _|";
    String four = "   " + "|_|" + "  |";
    String five = " _ " + "|_ " + " _|";

    String six = " _ " + "|_ " + "|_|";
    String seven = " _ " + "  |" + "  |";
    String eight = " _ " + "|_|" + "|_|";

    String nine = " _ " + "|_|" + " _|";

    String possibleFive = " _ " + "|_ " + " _|";
    String possibleNine = " _ " + " _|" + " _|";
    String possibleSix = " _ " + "|_ " + "| |";
    String possibleSeven = "   " + "  |" + "  |";
    String possibleOne = "   " + "  |" + "   ";
    String noPossibilities = "   " + "   " + "   ";
    String possibleOne2 = "    _|  |";

    @Test
    public void testResolver() {
        // 100 % tests to make sure everything gets resolved correctly
        Cell cZero = new Cell(zero);
        Cell cOne = new Cell(one);
        Cell cTwo = new Cell(two);
        Cell cThree = new Cell(three);
        Cell cFour = new Cell(four);
        Cell cFive = new Cell(five);
        Cell cSix = new Cell(six);
        Cell cSeven = new Cell(seven);
        Cell cEight = new Cell(eight);
        Cell cNine = new Cell(nine);

        assertEquals(0, Resolvers.CellNumber.resolveNumber(cZero).intValue());
        assertEquals(1, Resolvers.CellNumber.resolveNumber(cOne).intValue());
        assertEquals(2, Resolvers.CellNumber.resolveNumber(cTwo).intValue());
        assertEquals(3, Resolvers.CellNumber.resolveNumber(cThree).intValue());
        assertEquals(4, Resolvers.CellNumber.resolveNumber(cFour).intValue());
        assertEquals(5, Resolvers.CellNumber.resolveNumber(cFive).intValue());
        assertEquals(6, Resolvers.CellNumber.resolveNumber(cSix).intValue());
        assertEquals(7, Resolvers.CellNumber.resolveNumber(cSeven).intValue());
        assertEquals(8, Resolvers.CellNumber.resolveNumber(cEight).intValue());
        assertEquals(9, Resolvers.CellNumber.resolveNumber(cNine).intValue());
    }

    @Test
    public void testPossibleNumberResolver() {
        Cell pFive = new Cell(possibleFive);
        Cell pNine = new Cell(possibleNine);
        Cell pSeven = new Cell(possibleSeven);
        Cell pSix = new Cell(possibleSix);
        Cell pOne = new Cell(possibleOne);
        Cell pOne2 = new Cell(possibleOne2);

        Cell nPos = new Cell(noPossibilities);

        List<Integer> pOnes = Resolvers.CellNumber.resolveNumberWithPossibilities(pOne);
        List<Integer> pOnes2 = Resolvers.CellNumber.resolveNumberWithPossibilities(pOne2);

        List<Integer> pFives = Resolvers.CellNumber.resolveNumberWithPossibilities(pFive);
        List<Integer> pNines = Resolvers.CellNumber.resolveNumberWithPossibilities(pNine);
        List<Integer> pSevens = Resolvers.CellNumber.resolveNumberWithPossibilities(pSeven);
        List<Integer> pSixes = Resolvers.CellNumber.resolveNumberWithPossibilities(pSix);
        List<Integer> nPoss = Resolvers.CellNumber.resolveNumberWithPossibilities(nPos);

        // should only have -1 and 1
        assertEquals(2, pOnes.size());
        assertEquals(new Integer(1), pOnes.get(1));

        // should be 1, 7 and -1
        assertEquals(3, pOnes2.size());
        assertTrue(pOnes2.contains(1));
        
        assertEquals(3, pFives.size());
        assertTrue(pFives.contains(6));
        assertTrue(pFives.contains(9));

        // Should only have 9 and 3
        assertEquals(2, pNines.size());
        // Should only have 1 and 7 
        assertEquals(2, pSevens.size());
        assertEquals(new Integer(7), pSevens.get(1));

        // Should only have -1 and 6
        assertEquals(2, pSixes.size());
        assertEquals(new Integer(6), pSixes.get(1));

        assertEquals(1, nPoss.size());
        assertEquals(new Integer(-1), nPoss.get(0));

    }

}
