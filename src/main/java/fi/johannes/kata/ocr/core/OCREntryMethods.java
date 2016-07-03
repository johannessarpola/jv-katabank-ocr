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
package fi.johannes.kata.ocr.core;

import fi.johannes.kata.ocr.digits.Digit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johannes Sarpola
 * @date Jul 3, 2016
 */
class OCREntryMethods {

    /**
     * Creates alternative String representations
     *
     * @param alternative
     * @return
     */
    static String createSecondaryRepresentation(String originalRepresentation, Integer alternative, Integer index) {
        StringBuilder alternativeOcrStr = new StringBuilder(originalRepresentation);
        // Gets the String representation of Digits number
        String repr = Digit.getRepresentationForInteger(alternative);
        // Remove the old representation
        alternativeOcrStr.delete(index, originalRepresentation.length() + index);
        // Insert the new one
        alternativeOcrStr.insert(index, repr);
        // Add it to alternative representations
        return alternativeOcrStr.toString();

    }

    /**
     * Creates alternative list of integers
     *
     * @param integers
     * @param index
     * @param alternative
     * @return
     */
    static List<Integer> createSecondaryIntegerList(List<Integer> integers, int index, Integer alternative) {
        List<Integer> ints = new ArrayList<>();
        for (int i : integers) {
            ints.add(i);
        }
        ints.set(index, alternative);
        return ints;
    }


}
