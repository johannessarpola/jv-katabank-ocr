/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
