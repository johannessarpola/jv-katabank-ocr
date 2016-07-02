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

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 */
public class ApplicationDataTest {

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

    public ApplicationDataTest() {
    }

    @Test
    public void testDigits() {
        assertEquals(two, ApplicationData.Digits.Two());
        assertEquals(seven, ApplicationData.Digits.Seven());
        assertEquals(four, ApplicationData.Digits.Four());
        assertEquals(eight, ApplicationData.Digits.Eight());
        assertEquals(nine, ApplicationData.Digits.Nine());
        assertEquals(six, ApplicationData.Digits.getAsList().get(6));
        assertEquals(one, ApplicationData.Digits.getAsMap().get(1));
        assertEquals(zero, ApplicationData.Digits.getAsMap().get(0));
        assertEquals(three, ApplicationData.Digits.getAsMap().get(3));
        assertEquals(five, ApplicationData.Digits.getAsMap().get(5));
        assertEquals(10, ApplicationData.Digits.getAsList().size());
        assertEquals(10, ApplicationData.Digits.getAsMap().entrySet().size());

    }

}
