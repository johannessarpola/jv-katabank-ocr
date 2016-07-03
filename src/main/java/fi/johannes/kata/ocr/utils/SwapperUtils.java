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

import java.util.List;

/**
 * Performs swaps
 *
 * @author Johannes Sarpola
 * @date Jul 3, 2016
 */
public class SwapperUtils {

    /**
     * Swaps and returns original
     *
     * @param listOfObjs
     * @param pos
     * @param newObj
     * @return
     */
    public static <T> T swap(List<T> listOfObjs, int pos, T newObj) {
        T original = listOfObjs.get(pos);
        listOfObjs.set(pos, newObj);
        return original;
    }

    /**
     * Swaps and returns original, tries
     *
     * @param objArr
     * @param pos
     * @param newObj
     * @return either the old object if swap is successfull, otherwise null
     */
    public static <T> T swap(T[] objArr, int pos, T newObj) {
        try {
            T original = objArr[pos];
            objArr[pos] = newObj;
            return original;
        }
        catch (Exception e){
            return null;
        }

    }
}
