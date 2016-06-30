/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johannes.kata.ocr.core;

import fi.johannes.kata.ocr.utils.ResourceGetter;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 */
public class ResourceGetterTest {
    
    public ResourceGetterTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getFile method, of class ResourceGetter.
     */
    @Test
    public void testGetFile() {
        System.out.println("getFile");
        String fileName = "LICENSE.txt";
        String result = ResourceGetter.getContents(fileName);
        assertTrue(2<result.length());

    }
    
}
