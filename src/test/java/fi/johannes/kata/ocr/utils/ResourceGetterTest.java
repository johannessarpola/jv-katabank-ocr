/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johannes.kata.ocr.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

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
    public void testGetContents() {
        System.out.println("getFile");
        String fileName = "LICENSE.txt";
        String result = ResourceGetter.getContents(fileName);
        assertTrue(2 < result.length());

    }

    /**
     * Test of getReader method, of class ResourceGetter.
     */
    @Test
    public void testGetReader() throws IOException {
        System.out.println("getReader");
        String fileName = "LICENSE.txt";
        Reader result = ResourceGetter.getReader(fileName);
        assertTrue(result.read()>0);
    }

    /**
     * Test of getUrl method, of class ResourceGetter.
     */
    @Test
    public void testGetUrl() throws FileNotFoundException {
        System.out.println("getUrl");
        String fileName = "LICENSE.txt";
        String expResult = "txt";
        URL result = ResourceGetter.getUrl(fileName);
        // url has extra '/' in the beginning
        FileConnection connection = new FileConnection(result.getPath().substring(1));
        assertEquals(expResult, connection.getExtension());
    }

}
