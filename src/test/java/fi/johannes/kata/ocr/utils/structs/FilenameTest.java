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
package fi.johannes.kata.ocr.utils.structs;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Johannes Sarpola
 */
public class FilenameTest {

    Filename fn;
    Filename fn2;

    public FilenameTest() {
    }

    @Before
    public void setUp() throws IOException {
        fn = new Filename("./testfile.txt");
        fn2 = new Filename("afile.md");
    }

    /**
     * Test of getFilename method, of class Filename.
     */
    @Test
    public void testFilename() {
        String exp = "testfile";
        String exp2 = "txt";

        String exp3 = "afile";
        String exp4 = "md";
        String exp5 = "afile.md";
        
        System.out.println(fn.getFullpath());
        System.out.println(fn2.getFullpath());

        assertEquals(exp, fn.getName());
        assertEquals(exp2, fn.getExtension());
        assertEquals(exp3, fn2.getName());
        assertEquals(exp4, fn2.getExtension());
        assertEquals(exp5, fn2.getFilename());
    }

}
