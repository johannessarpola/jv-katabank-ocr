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
package fi.johannes.kata.ocr.digits;

import fi.johannes.kata.ocr.cells.Cell;
import fi.johannes.kata.ocr.core.data.Lexicons;
import java.util.Objects;

/**
 * Class to hold Cell and Number
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jul 1, 2016
 */
public final class Digit {

    private final Cell cell;
    private final Integer number;
    private final String representation;
    private final Boolean validDigit;

    public Digit(Cell cell) {
        this.cell = cell;
        this.number = Lexicons.DigitLexiconResolver.resolveNumber(cell);
        this.validDigit = DigitMethods.isValidNumber(number);
        this.representation = createRepresentation();
    }

    public Cell getCell() {
        return cell;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Digit)) {
            return false;
        } else {
            try {
                Digit otherDigit = (Digit) other;
                return this.getNumber().equals(otherDigit.getNumber());
                // if cast fails or equals fails (either is null (?) )
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.number);
        return hash;
    }

    private String createRepresentation() {
        if (validDigit) {
            return number.toString();
        } else {
            return DigitMethods.getInvalidRepresentation();
        }
    }

    public String getRepresentation() {
        return representation;
    }

    public Boolean isValid() {
        return validDigit;
    }

}
