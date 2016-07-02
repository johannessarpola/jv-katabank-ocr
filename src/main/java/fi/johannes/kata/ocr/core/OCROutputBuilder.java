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

import fi.johannes.kata.ocr.core.data.ApplicationStrings;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johannes Sarpola
 * @date Jul 1, 2016
 */
public class OCROutputBuilder {

    /**
     * Builds output from a list of entries
     * @param entries
     * @return 
     */
    public static List<String> buildOutput(List<OCREntry> entries) {
        List<String> output = new ArrayList<>();
        for (OCREntry entry : entries) {
            String status = statusString(entry.getStatus());
            String line = entry.getDigitRepresentation() + ApplicationStrings.FIELD_DELIMETER + status;
            output.add(line);
        }
        return output;
    }

    private static String statusString(OCREntry.Status status) {
        if (null != status) {
            switch (status) {
                case OK:
                    return ApplicationStrings.OutputBuilder.OK;
                case Malformed:
                    return ApplicationStrings.OutputBuilder.MALFORMED_DIGIT_FIELD;
                case Invalid_Checksum:
                    return ApplicationStrings.OutputBuilder.INVALID_CHECKSUM_FIELD;
                case Ambiguous:
                    return ApplicationStrings.OutputBuilder.AMBIGUOUS_ENTRY_FIELD;
            }
        }
        return ApplicationStrings.OutputBuilder.INVALID_STATUS;

    }
}
