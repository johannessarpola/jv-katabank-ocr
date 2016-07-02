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
import fi.johannes.kata.ocr.core.data.ApplicationStrings;
import fi.johannes.kata.ocr.digits.Digit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 25, 2016
 */
public class OCREntry {

    private Checksum checksum;

    private List<Integer> integers;

    // Just store reference to digit here if there are other number possibilities
    // Needs the index as well
    private String digitsRepresentation;
    private List<String> alternativeRepresentations;

    private int status = 0;
    private boolean malformed;
    private boolean error;
    private boolean ambiguous;

    public OCREntry(CellRow cr) {
        init();
        build(cr);
    }

    private void init() {
        integers = new ArrayList<>();
        alternativeRepresentations = new ArrayList<>();
        digitsRepresentation = "";
        malformed = false;
        error = false;
        ambiguous = false;
    }

    private void build(CellRow cr) {
        List<Cell> cells = cr.getAsList();
        List<Digit> digits = new ArrayList<>();
        Map<Integer, Digit> digitsWithAlternatives = new HashMap<>();
        Digit d;
        Integer number;

        for (Cell cell : cells) {
            d = new Digit(cell);
            // reference to integer
            integers.add(d.getNumber());

            digits.add(d);
            // Store reference to digits with alternatives here
            if (d.hasAlternatives()) {
                digitsWithAlternatives.put(digits.size() - 1, d);
            }
            // each digit is tested here for validity
            digitsRepresentation += d.getRepresentation();

        }
        checksum = buildChecksum(integers);
        resolveStatus();
        if (digitsWithAlternatives.keySet().size() > 0) {
            createAlternativeRepresentations(digitsWithAlternatives);
        }
    }

    private boolean isChecksumInvalid(Checksum checksum) {
        // this is needed for the alternative digits
        return !checksum.isValid();
    }

    private Checksum buildChecksum(List<Integer> integers) {
        return new Checksum(integers, ApplicationProperties.Validation.CHECKSUM_MODULO);
    }

    private void resolveStatus() {
        malformed = digitsRepresentation.contains(ApplicationStrings.MALFORMED_DIGIT_REPRESENTATION);
        error = isChecksumInvalid(checksum);

        if (malformed) {
            status = 1;
        } else if (error) {
            status = 2;
        } else if (ambiguous) {
            status = 3;
        }
        else {
            status = 0;
        }
    }

    /**
     * Gets the alternative representations for possibilities
     */
    private void createAlternativeRepresentations(Map<Integer, Digit> digitsWithAlternatives) {
        String firstValidRepr = "";
        List<Integer> firstValidIntegers = new ArrayList<>();
        Checksum firstValidChecksum = null;

        int alternativeValidChecksums = 0;

        for (Entry<Integer, Digit> e : digitsWithAlternatives.entrySet()) {

            Integer index = e.getKey();
            Digit d = e.getValue();

            for (Integer alternative : d.getPossibleNumbers()) {

                List<Integer> alternativeIntegers = alternativeIntegerList(integers, index, alternative);
                Checksum altCheck = buildChecksum(alternativeIntegers);
                if (altCheck.isValid()) {

                    String altRepr = alternativeRepresentation(d.getRepresentation(), alternative, index);
                    alternativeRepresentations.add(altRepr);

                    if (altCheck.isValid() && this.status > 0) {
                        if (alternativeValidChecksums < 1) {
                            firstValidChecksum = altCheck;
                            firstValidIntegers = alternativeIntegers;
                            firstValidRepr = altRepr;
                        }
                        alternativeValidChecksums++;
                    }
                    if (alternativeValidChecksums == 1 && this.status > 0 && firstValidChecksum.isValid()) {
                        this.integers = firstValidIntegers;
                        this.checksum = firstValidChecksum;
                        this.digitsRepresentation = firstValidRepr;
                        resolveStatus();
                    }
                }
            }
        }

    }

    /**
     * Creates alternative String representations
     *
     * @param alternative
     * @return
     */
    private String alternativeRepresentation(String originalRepresentation, Integer alternative, Integer index) {
        StringBuilder alternativeOcrStr = new StringBuilder(digitsRepresentation);
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
    private List<Integer> alternativeIntegerList(List<Integer> integers, int index, Integer alternative) {
        List<Integer> ints = new ArrayList<>();
        for (int i : integers) {
            ints.add(i);
        }
        ints.set(index, alternative);
        return ints;
    }

    /**
     * Swapper for ints
     *
     * @param integers
     * @param pos
     * @param newInt
     * @return
     */
    public Integer swapInteger(List<Integer> integers, int pos, Integer newInt) {
        // TODO just make a swapper utility class
        Integer integer = integers.get(pos);
        integers.set(pos, newInt);
        return integer;
    }

    public Checksum getChecksum() {
        return checksum;
    }

    public String getDigitsRepresentation() {
        return digitsRepresentation;
    }

    /**
     * Just gets the status based on status int
     *
     * @return
     */
    public Status getStatus() {
        return Status.values()[status];
    }

    /**
     * Public enum to be make status more readable
     */
    public enum Status {
        OK,
        Malformed,
        Invalid_Checksum,
        Ambiguous
    }

    public List<Integer> getIntegers() {
        return integers;
    }

}
