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
package fi.johannes.kata.ocr.utils.structs;

import fi.johannes.kata.ocr.utils.files.CFilePathOperations;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;

/**
 * Caches the relevant infos for a filename
 *
 * @author Johannes Sarpola
 * @date Jul 1, 2016
 */
public class Filename {

  Path absolutePath;
  String fullpath;
  String name;
  String extension;

  public Filename(String filename) throws IOException {
    if (CFilePathOperations.validatePath(filename)) {
      absolutePath = Paths.get(filename).toAbsolutePath();
      cacheStrings();
    } else {
      throw new IOException();
    }
  }

  public Filename(Path filename) throws IOException {
    // TODO Not sure is it even necessary to validate Path, check
    if (CFilePathOperations.validatePath(filename.getFileName().toString())) {
      absolutePath = filename.toAbsolutePath();
      cacheStrings();
    } else {
      throw new IOException();
    }
  }

  private void cacheStrings() {
    fullpath = FilenameUtils.getFullPath(absolutePath.toString());
    name = FilenameUtils.getBaseName(absolutePath.toString());
    extension = FilenameUtils.getExtension(absolutePath.toString());
  }

  public String getFilename() {
    return name + "." + extension;
  }

  public String getFullpath() {
    return fullpath;
  }

  public String getExtension() {
    return extension;
  }

  public String getName() {
    return name;
  }

  public Path getAbsolutePath() {
    return absolutePath;
  }

}
