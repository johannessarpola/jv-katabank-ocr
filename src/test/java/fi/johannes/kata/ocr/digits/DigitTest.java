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
package fi.johannes.kata.ocr.digits;

import fi.johannes.kata.ocr.cells.Cell;
import fi.johannes.kata.ocr.core.data.Lexicons;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Johannes Sarpola
 */
public class DigitTest {

    Cell cell1;
    Cell cell2;
    Cell cell3;

    public DigitTest() {
    }

    @Before
    public void setUp() {
        cell1 = new Cell(Lexicons.Digits.Four());
        cell2 = new Cell(Lexicons.Digits.Three());
        cell3 = new Cell("Something");

    }

    /**
     * Test of getCell method, of class Digit.
     */
    @Test
    public void testDigit() {
        Digit d1 = new Digit(cell1);
        Digit d2 = new Digit(cell2);
        Digit d3 = new Digit(cell3);

        assertEquals(new Integer(4), d1.getNumber());
        assertEquals(new Integer(3), d2.getNumber());
        assertEquals(new Integer(-1), d3.getNumber());
        
        assertEquals(true, d2.isValid());
        assertEquals(false, d3.isValid());
        
        assertEquals("3", d2.getRepresentation());
        assertEquals("?", d3.getRepresentation());

    }

}
