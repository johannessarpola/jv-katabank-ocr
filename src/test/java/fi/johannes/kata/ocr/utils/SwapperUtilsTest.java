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

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Johannes Sarpola
 */
public class SwapperUtilsTest {

    public SwapperUtilsTest() {
    }

    @Test
    public void testResilientSwaps() {
        String obj1 = "ab";
        String obj2 = "cd";

        String obj3 = "ef";

        List<String> list = Arrays.asList(obj1, obj2);
        String[] arr = {obj1, obj2};

        String orig = SwapperUtils.resilientSwap(list, 0, obj3);
        assertEquals(obj1, orig);
        assertEquals(obj3, list.get(0));

        String orig2 = SwapperUtils.resilientSwap(arr, 1, obj3);
        assertEquals(obj2, orig2);
        assertEquals(obj3, arr[1]);

        String nullStr = SwapperUtils.resilientSwap(arr, 4, obj3);
        assertEquals(null, nullStr);

    }
}
