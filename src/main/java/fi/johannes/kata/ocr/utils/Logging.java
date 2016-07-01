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

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.log4j.Logger;

public class Logging {
        static Logger logger;
        
	public static String stringifyException(Exception e){
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
	/**
	 * Logs the from and stacktrace (error)
	 * @param from
	 * @param e
	 */
	public static void logStackTrace_Error(Object from, Exception e){
		logger = Logger.getLogger(from.getClass());
		logger.error(stringifyException(e));
	}
	public static void logStackTrace_Error(Class<? extends Object> from, Exception e) {
		logger = Logger.getLogger(from);
		logger.error(stringifyException(e));
	}
       /**
	 * Logs the from and stacktrace (fatal)
	 * @param from
	 * @param e
	 */
	public static void logStackTrace_Fatal(Object from, Exception e){
		logger = Logger.getLogger(from.getClass());
		logger.fatal(stringifyException(e));
	}
        public static void logStackTrace_Fatal(Class<? extends Object> from, Exception e) {
		logger = Logger.getLogger(from);
		logger.fatal(stringifyException(e));
	}
        public static void logMessage_Fatal(Class<? extends Object> from, String msg) {
		Logger logger = Logger.getLogger(from);
		logger.fatal(msg);
	}
        public static void logMessage_Error(Class<? extends Object> from, String msg) {
		logger = Logger.getLogger(from);
		logger.error(msg);
	}
        public static void logMessage_Info(Class<? extends Object> from, String msg) {
		logger = Logger.getLogger(from);
		logger.info(msg);
	}
}
