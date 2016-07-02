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
package fi.johannes.kata.ocr.core.data;

/**
 *
 * @author Johannes Sarpola
 * @date Jul 1, 2016
 */
public class ApplicationStrings {

    /**
     * How fields are delimeted e.g 1234567890 ERR
     */
    public final static String FIELD_DELIMETER = " ";
    public final static String MALFORMED_DIGIT_REPRESENTATION = "?";

    /**
     * Has messages related to anomalies in OCR
     */
    public static class OutputBuilder {

        public final static String OK = "OK";
        public final static String INVALID_CHECKSUM_FIELD = "ERR";
        public final static String MALFORMED_DIGIT_FIELD = "ILL";
        public final static String AMBIGUOUS_ENTRY_FIELD = "AMB";
        public final static String INVALID_STATUS = "???";

    }

    /**
     * Has messages related to logging
     */
    public static class LoggingMessages {

        public static class Info {

            public static final String FOLDER_CONNECTION_SUCCESSFUL = "Connection to Folder done";
        }

        public static class Error {

            public static final String FOLDER_CONNECTION_UNSUCCESSFUL = "There was problem with establishing connection to folder";
            public static final String COULD_NOT_CONNECT_TO_INPUT = "There was problem with establishing connection to input folder";
            public static final String CELL_SWAP_AT_INVALID_INDEX = "Tried to swap char which was over the cells content length";
        }
    }

    /**
     * Strings from input-output
     */
    public static class InputOutput {

        public static final String CONNECTING_CHARACTER = "_";

        public static class I {

            public static final String SOURCE_DESCRIPTION_PREFIX = "Source";
        }

        public static class O {

            public static final String OUTPUT_DESCRIPTION = "OCR_Entry";
            public static final String OUTPUT_EXTENSION = ".txt";

        }
    }
}
