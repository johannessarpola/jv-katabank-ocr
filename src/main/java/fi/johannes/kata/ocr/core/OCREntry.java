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

import fi.johannes.kata.ocr.cells.Cell;
import fi.johannes.kata.ocr.cells.CellRow;
import fi.johannes.kata.ocr.checksum.Checksum;
import fi.johannes.kata.ocr.core.data.ApplicationProperties;
import fi.johannes.kata.ocr.core.data.Lexicons;
import fi.johannes.kata.ocr.digits.Digit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 25, 2016
 */
public class OCREntry {

    
    private Checksum checksum;
    private List<Digit> digits;

    public OCREntry(CellRow cr) {
        init();
        build(cr);

    }

    private void build(CellRow cr) {
        List<Cell> cells = cr.getAsList();
        Digit d;
        Integer number;
        List<Integer> integers = new ArrayList<>();
        for (Cell cell : cells) {
            number = Lexicons.DigitLexiconResolver.resolveNumber(cell);
            integers.add(number);
            d = new Digit(cell, number);
            digits.add(d);
        }
        buildChecksum(integers);
    }

    private void buildChecksum(List<Integer> integers) {
        checksum = new Checksum(integers, ApplicationProperties.Entries.CHECKSUM_MODULO);
    }

    private void init() {
        digits = new ArrayList<>();
    }

    public List<Digit> getDigits() {
        return digits;
    }

    public Checksum getChecksum() {
        return checksum;
    }

}