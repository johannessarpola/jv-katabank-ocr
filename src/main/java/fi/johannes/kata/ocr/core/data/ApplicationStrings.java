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

    public final static String FIELD_DELIMETER = " ";

    public static class Anomalies {

        public final static String MALFORMED_DIGIT_REPRESENTATION = "?";
        public final static String INVALID_CHECKSUM_FIELD = "ERR";
        public final static String MALFORMED_DIGIT_FIELD = "ILL";
        public final static String AMBIGUOUS_ENTRY_FIELD = "AMB";
    }

    public static class LoggingMessages {

        public static class Info {

            public static final String FOLDER_CONNECTION_SUCCESSFUL = "Connection to Folder done";
        }

        public static class Error {

            public static final String FOLDER_CONNECTION_UNSUCCESSFUL = "There was problem with establishing connection to folder";

        }
    }

}
