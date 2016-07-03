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
import fi.johannes.kata.ocr.cells.CellRow;
import fi.johannes.kata.ocr.core.OCREntry.Status;
import fi.johannes.kata.ocr.core.data.ApplicationData;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Johannes Sarpola
 */
public class OCREntryTest {

    String malformedZero = "   | ||_|";
    List<Cell> cells;
    List<Cell> cells2;

    CellRow cr;
    CellRow cr2;

    public OCREntryTest() {
    }

    @Before
    public void setUp() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        cells = Arrays.asList(new Cell(ApplicationData.Digits.Three()),
                new Cell(ApplicationData.Digits.Four()),
                new Cell(ApplicationData.Digits.Five()),
                new Cell(ApplicationData.Digits.Eight()),
                new Cell(ApplicationData.Digits.Eight()),
                new Cell(ApplicationData.Digits.Two()),
                new Cell(ApplicationData.Digits.Eight()),
                new Cell(ApplicationData.Digits.Six()),
                new Cell(ApplicationData.Digits.Five())
        );
        cells2 = Arrays.asList(
                new Cell(ApplicationData.Digits.Zero()),
                new Cell(malformedZero),
                new Cell(ApplicationData.Digits.Zero()),
                new Cell(ApplicationData.Digits.Zero()),
                new Cell(ApplicationData.Digits.Zero()),
                new Cell(ApplicationData.Digits.Zero()),
                new Cell(ApplicationData.Digits.Zero()),
                new Cell(ApplicationData.Digits.Five()),
                new Cell(ApplicationData.Digits.One())
        );
        Constructor<CellRow> constructor = CellRow.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object[] obj = {"CellRow"};
        cr = (CellRow) constructor.newInstance(new Object[0]);
        Field field = CellRow.class.getDeclaredField("cells");
        field.setAccessible(true);
        field.set(cr, cells);

        cr2 = (CellRow) constructor.newInstance(new Object[0]);
        field.set(cr2, cells2);

    }

    /**
     * Test of getDigits method, of class OCREntry.
     */
    @Test
    public void testOCREntry() {

        OCREntry ocr = new OCREntry(cr);
        assertEquals("345882865", ocr.getEntryRepresentation());
        assertEquals(Status.OK, ocr.getStatus());
        assertEquals(cells.size(), ocr.getIntegers().size());

        OCREntry ocr2Malform = new OCREntry(cr2);
        assertEquals("000000051", ocr2Malform.getEntryRepresentation());
        assertEquals(Status.OK, ocr2Malform.getStatus());
        assertEquals(cells.size(), ocr2Malform.getIntegers().size());

    }
}
