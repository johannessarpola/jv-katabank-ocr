/*
 * The MIT License
 *
 * Copyright 2016 Johannes Sarpola.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package fi.johannes.kata.ocr.digits;

import fi.johannes.kata.ocr.cells.Cell;
import fi.johannes.kata.ocr.core.OcrDigitResolver;
import fi.johannes.kata.ocr.core.data.ApplicationProperties;
import fi.johannes.kata.ocr.core.data.ApplicationStrings;
import java.util.List;

/**
 * Digit specific methods, works as interface between the static classes and Digit
 * 
 * @author Johannes Sarpola
 * @date Jul 1, 2016
 */
class DigitMethods {

  public static List<Integer> getPossibleNumbers(Cell cell) {
    // First is the original number
    List<Integer> possibleNumbers =
        OcrDigitResolver.CellNumber.resolveNumberWithPossibilities(cell);
    return possibleNumbers.subList(1, possibleNumbers.size());
  }

  public static Integer getNumber(Cell cell) {
    return OcrDigitResolver.CellNumber.resolveNumber(cell);
  }

  public static boolean isValidNumber(Integer numb) {
    return !OcrDigitResolver.CellNumber.isInvalidNumber(numb);
  }

  public static String getInvalidRepresentation() {
    return ApplicationStrings.MALFORMED_DIGIT_REPRESENTATION;
  }

  public static String getStringRepresentation(Integer number) {
    if (number.equals(ApplicationProperties.Cells.INVALID_NUMBER)) {
      return getInvalidRepresentation();
    } else
      return number.toString();

  }
}
