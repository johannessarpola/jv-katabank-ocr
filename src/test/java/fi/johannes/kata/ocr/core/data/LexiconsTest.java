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
package fi.johannes.kata.ocr.core.data;

import fi.johannes.kata.ocr.cells.Cell;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 */
public class LexiconsTest {

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

    public LexiconsTest() {
    }

    @Test
    public void testDigits() {
        assertEquals(two, Lexicons.Digits.Two());
        assertEquals(seven, Lexicons.Digits.Seven());
        assertEquals(four, Lexicons.Digits.Four());
        assertEquals(eight, Lexicons.Digits.Eight());
        assertEquals(nine, Lexicons.Digits.Nine());
        assertEquals(six, Lexicons.Digits.getAsList().get(6));
        assertEquals(one, Lexicons.Digits.getAsMap().get(1));
        assertEquals(zero, Lexicons.Digits.getAsMap().get(0));
        assertEquals(three, Lexicons.Digits.getAsMap().get(3));
        assertEquals(five, Lexicons.Digits.getAsMap().get(5));
        assertEquals(10, Lexicons.Digits.getAsList().size());
        assertEquals(10, Lexicons.Digits.getAsMap().entrySet().size());

    }

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
        
        assertEquals(0, Lexicons.DigitResolver.resolve(cZero).intValue());
        assertEquals(1, Lexicons.DigitResolver.resolve(cOne).intValue());
        assertEquals(2, Lexicons.DigitResolver.resolve(cTwo).intValue());
        assertEquals(3, Lexicons.DigitResolver.resolve(cThree).intValue());
        assertEquals(4, Lexicons.DigitResolver.resolve(cFour).intValue());
        assertEquals(5, Lexicons.DigitResolver.resolve(cFive).intValue());
        assertEquals(6, Lexicons.DigitResolver.resolve(cSix).intValue());
        assertEquals(7, Lexicons.DigitResolver.resolve(cSeven).intValue());
        assertEquals(8, Lexicons.DigitResolver.resolve(cEight).intValue());
        assertEquals(9, Lexicons.DigitResolver.resolve(cNine).intValue());
    }

}