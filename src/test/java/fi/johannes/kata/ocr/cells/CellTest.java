/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johannes.kata.ocr.cells;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 */
public class CellTest {

    public CellTest() {
    }

    /**
     * Test of append method, of class Cell.
     */
    @Test
    public void testCell() {
        System.out.println("test cell");
        String stuff = "stuff";
        Cell instance = new Cell();
        instance.append(stuff);
        assertEquals(stuff.length(), instance.toString().length());
        String stuffalso = "also";
        instance.append(stuffalso);
        assertEquals(stuff.length()+stuffalso.length(), instance.toString().length());

    }
}
