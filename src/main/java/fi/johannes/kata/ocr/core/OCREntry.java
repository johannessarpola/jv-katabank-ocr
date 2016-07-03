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
import fi.johannes.kata.ocr.core.data.ApplicationStrings;
import fi.johannes.kata.ocr.digits.Digit;
import fi.johannes.kata.ocr.utils.structs.Pair;
import java.util.ArrayList;
import java.util.List;

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
    private String entryRepresentation;
    private List<String> secondaryEntryRepresentations;

    private int statusCode = 0;
    private boolean malformed;
    private boolean error;
    private boolean ambiguous;

    public OCREntry(CellRow cr) {
        init();
        build(cr);
        resolveStatus();
    }

    private void init() {
        integers = new ArrayList<>();
        secondaryEntryRepresentations = new ArrayList<>();
        entryRepresentation = "";
        malformed = false;
        error = false;
        ambiguous = false;
    }

    private void build(CellRow cr) {
        List<Cell> cells = cr.getAsList();
        List<Digit> digits = new ArrayList<>();
        //Map<Integer, Digit> digitsWithAlternatives = new HashMap<>();
        List<DigitIndexPair> digitsWithAlternatives = new ArrayList<>();
        Digit d;
        Integer number;

        for (Cell cell : cells) {
            d = new Digit(cell);
            // reference to integer
            integers.add(d.getNumber());

            digits.add(d);
            // Store reference to digits with alternatives here
            if (d.hasAlternatives()) {
                digitsWithAlternatives.add(new DigitIndexPair(digits.size() - 1, d));
            }
            // each digit is tested here for validity
            entryRepresentation += d.getRepresentation();

        }
        checksum = OCREntryMethods.buildChecksum(integers);
        resolveStatus();
        if (digitsWithAlternatives.size() > 0) {
            createAlternativeRepresentations(digitsWithAlternatives);
        }
    }

    private boolean isChecksumInvalid(Checksum checksum) {
        // this is needed for the alternative digits
        return !checksum.isValid();
    }

    private void resolveStatus() {
        malformed = entryRepresentation.contains(ApplicationStrings.MALFORMED_DIGIT_REPRESENTATION);
        error = isChecksumInvalid(checksum);
        if (malformed) {
            statusCode = 1;
        }
        if (error) {
            statusCode = 2;
        }
        if (ambiguous) {
            statusCode = 3;
        }
        if (!malformed && !error && !ambiguous) {
            statusCode = 0;
        }
    }

    /**
     * Gets the alternative representations for possibilities
     */
    private void createAlternativeRepresentations(List<DigitIndexPair> digitsWithSecondaries) {

        // Store the first valids if the original is invalid
        String firstValidSecondaryRepr = "";
        List<Integer> firstValidSecondaryIntegers = new ArrayList<>();
        Checksum firstValidSecondarChecksum = null;

        // keeps track hwo many secondaries with valid checksums
        int secondariesValidChecksums = 0;

        for (DigitIndexPair pair : digitsWithSecondaries) {

            // Index pointer to a spot in list of integers 
            Integer index = pair.getIndex();
            // Digit has it's alternatives
            Digit d = pair.getDigit();

            for (Integer alternativeInt : d.getPossibleNumbers()) {

                // Do alternative list of integers 
                List<Integer> alternativeIntegers = OCREntryMethods.createSecondaryIntegerList(integers, index, alternativeInt);
                Checksum altCheck = OCREntryMethods.buildChecksum(alternativeIntegers);

                // Create alternative reprensentation if alt checksum is valid
                if (altCheck.isValid() && OCREntryMethods.isValidIntegerList(alternativeIntegers)) {

                    ambiguous = true;
                    String secondaryRepr = OCREntryMethods.createSecondaryRepresentation(entryRepresentation, d.getRepresentation(), alternativeInt, index);
                    secondaryEntryRepresentations.add(secondaryRepr);

                    // if original is malformed or erroneous
                    if (secondariesValidChecksums < 1) {
                        firstValidSecondarChecksum = altCheck;
                        firstValidSecondaryIntegers = alternativeIntegers;
                        firstValidSecondaryRepr = secondaryRepr;
                    }
                    // If there are multiple valids we need to increment as it'll stay ambiguous
                    secondariesValidChecksums++;

                }
            }

        }
        // If there is only one valid alternative -> make it the entry
        if (secondariesValidChecksums == 1 && this.statusCode > 0) {
            reinitializeAttributes(firstValidSecondaryIntegers, firstValidSecondarChecksum, firstValidSecondaryRepr);
        }

    }

    private void reinitializeAttributes(List<Integer> integers, Checksum checksum, String representation) {
        // It's not ambiguous anymore if there was only one alternative
        ambiguous = false;
        this.integers = integers;
        this.checksum = checksum;
        this.entryRepresentation = representation;
    }

    public Checksum getChecksum() {
        return checksum;
    }

    public String getEntryRepresentation() {
        return entryRepresentation;
    }

    /**
     * Just gets the status based on status int
     *
     * @return
     */
    public Status getStatus() {
        return Status.values()[statusCode];
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

    public List<String> getSecondaryEntryRepresentations() {
        return secondaryEntryRepresentations;
    }

    public boolean isMalformed() {
        return malformed;
    }

    public boolean isError() {
        return error;
    }

    public boolean isAmbiguous() {
        return ambiguous;
    }

    /**
     * Just specialized class for pair, mostly readability
     */
    public static class DigitIndexPair extends Pair<Integer, Digit> {

        public DigitIndexPair(Integer k, Digit v) {
            super(k, v);
        }

        Integer getIndex() {
            return getLeft();
        }

        Digit getDigit() {
            return getRight();
        }

    }
}
