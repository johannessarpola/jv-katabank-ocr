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
package fi.johannes.kata.ocr.cells;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 */
public class CellRowTest {

    CellRow cr;
    List<String> ls;
    final int width = 2;
    int dividedCount;

    public CellRowTest() {
    }

    @Before
    public void setUp() throws Exception {
        ls = Arrays.asList("item 1", "item 2", "item 3", "item 4");
        cr = new CellRow(ls, width);
        dividedCount = ls.get(0).length()/width;
    }

    /**
     * Test of getCell method, of class CellRow.
     */
    @Test
    public void testGetCell() {
        String subst = "";
        for (int i = 0; i < ls.size(); i++) {
            subst += ls.get(i).substring(0, width);
        }
        Cell cell = cr.getCell(0);
        assertEquals(subst, cell.toString());
    }

    /**
     * Test of getAsList method, of class CellRow.
     */
    @Test
    public void testGetAsList() {
        assertEquals(dividedCount, cr.getAsList().size());
        
    }

    /**
     * Test of fits method, of class CellRow.
     */
    @Test
    public void testFits() {
        assertEquals(true, cr.fits(dividedCount-1));
    }

    /**
     * Test of size method, of class CellRow.
     */
    @Test
    public void testSize() {
        assertEquals(dividedCount, cr.size());
    }

}
