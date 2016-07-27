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
package fi.johannes.kata.ocr.utils.files;

import fi.johannes.kata.ocr.utils.OsUtils;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Johannes
 */
public class CFilePathOperations {

  public static Path stringToPath(String str) throws FileNotFoundException {
    Path p = Paths.get(str);
    if (Files.exists(p)) {
      return p;
    } else {
      throw new FileNotFoundException();
    }

  }

  /**
   * Validates the filename
   * 
   * @param path
   * @return
   */
  public static boolean validatePath(String path) {
    try {
      Paths.get(path);

    } catch (InvalidPathException | NullPointerException ex) {
      return false;
    }
    if (OsUtils.isWindows()) {
      return validatePathWindows(path);
    }
    return true;
  }

  /**
   * http://stackoverflow.com/questions/6730009/validate-a-file-name-on-windows
   * 
   * @author http://stackoverflow.com/users/433790/ridgerunner
   * @param text
   * @return
   */
  private static boolean validatePathWindows(String text) {
    Pattern pattern = Pattern.compile(
        "# Match a valid Windows filename (unspecified file system).          \n"
            + "^                                # Anchor to start of string.        \n"
            + "(?!                              # Assert filename is not: CON, PRN, \n"
            + "  (?:                            # AUX, NUL, COM1, COM2, COM3, COM4, \n"
            + "    CON|PRN|AUX|NUL|             # COM5, COM6, COM7, COM8, COM9,     \n"
            + "    COM[1-9]|LPT[1-9]            # LPT1, LPT2, LPT3, LPT4, LPT5,     \n"
            + "  )                              # LPT6, LPT7, LPT8, and LPT9...     \n"
            + "  (?:\\.[^.]*)?                  # followed by optional extension    \n"
            + "  $                              # and end of string                 \n"
            + ")                                # End negative lookahead assertion. \n"
            + "[^<>:\"/\\\\|?*\\x00-\\x1F]*     # Zero or more valid filename chars.\n"
            + "[^<>:\"/\\\\|?*\\x00-\\x1F\\ .]  # Last char is not a space or dot.  \n"
            + "$                                # Anchor to end of string.            ",
        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.COMMENTS);
    Matcher matcher = pattern.matcher(text);
    boolean isMatch = matcher.matches();
    return isMatch;
  }

}
