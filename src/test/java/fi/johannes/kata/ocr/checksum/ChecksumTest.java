/*
 * The MIT License
 *
 * Copyright 2016 Johannes Sarpola <johannes.sarpola at gmail.com>.
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
package fi.johannes.kata.ocr.checksum;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 */
public class ChecksumTest {

    Checksum checksum;
    Checksum checksum2;
    Checksum checksum3;

    List<Integer> integers;
    List<Integer> integers2;
    List<Integer> integers3;

    public ChecksumTest() {
    }

    @Before
    public void setUp() {
        integers = Arrays.asList(1, 2, 3);
        integers2 = Arrays.asList(99, -98, 97);
        integers3 = Arrays.asList(3, 4, 5, 8, 8, 2, 8, 6, 5);
        checksum = new Checksum(integers);
        checksum2 = new Checksum(integers2);
        checksum3 = new Checksum(integers3);
    }

    @Test
    public void testChecksum() {
        // account number:  3  4  5  8  8  2  8  6  5
        // position names:  d9 d8 d7 d6 d5 d4 d3 d2 d1
        // (d1+2*d2+3*d3 +..+9*d9) mod 11 = 0
        
        System.out.println("test checksum");
        Double expect = 3 * 1 + 2 * 2 + 1 * 3.;
        Double expect2 = 97 * 1 + (-98) * 2 + 99 * 3.;

        assertEquals(expect, checksum.getChecksum(), 0);
        assertEquals(expect2, checksum2.getChecksum(), 0);

        assertEquals(false, checksum.isValid());
        assertEquals(true, checksum2.isValid());
        assertEquals(true, checksum3.isValid());

    }

}
