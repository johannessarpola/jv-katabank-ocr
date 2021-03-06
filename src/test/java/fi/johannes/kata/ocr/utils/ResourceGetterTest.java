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
package fi.johannes.kata.ocr.utils;

import fi.johannes.kata.ocr.utils.files.ExistingFileConnection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
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
        ExistingFileConnection connection = new ExistingFileConnection(result.getPath().substring(1));
        assertEquals(expResult, connection.getExtension());
    }

}
