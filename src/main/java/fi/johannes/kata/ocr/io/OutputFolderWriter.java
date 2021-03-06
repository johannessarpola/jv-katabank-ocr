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
package fi.johannes.kata.ocr.io;

import fi.johannes.kata.ocr.utils.files.CFolderOperations;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Output to folder
 * 
 * @author Johannes Sarpola
 * @date Jul 1, 2016
 */
public class OutputFolderWriter extends FolderIO {

  public OutputFolderWriter(Path folder) {
    super(folder);
  }

  public void write(List<String> lines, String filename) throws IOException {
    Path outputfile = folder.resolve(filename);
    // TODO Fix if file exists
    Files.write(outputfile, lines, StandardOpenOption.CREATE);

  }

  public void create() throws IOException {
    CFolderOperations.createFolder(folder);
  }

  public void clear() {
    CFolderOperations.recursiveDelete(folder.toString());
  }



}
