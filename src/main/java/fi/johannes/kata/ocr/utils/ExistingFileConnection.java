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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/**
 * Combines the necessary methods for a file and make sure that a file exists
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 30, 2016
 */
public class ExistingFileConnection {

    Path p;

    public ExistingFileConnection(String path) throws FileNotFoundException {
        p = Paths.get(path);
        testExistence();
    }

    public List<String> readLines() throws IOException {
        return Files.readAllLines(p);
    }

    public BufferedReader createReader() throws IOException {
        return Files.newBufferedReader(p);
    }

    public void appendLines(List<String> lines) throws IOException {
        Files.write(p, lines, StandardOpenOption.APPEND);
    }

    public void writeNew(List<String> lines) throws IOException {
        Files.delete(p);
        Files.write(p, lines, StandardOpenOption.CREATE);
    }

    public String getExtension() {
        String ext = FilenameUtils.getExtension(p.toString());
        return ext;
    }
    public void delete() throws IOException{
        Files.delete(p);
    }
    private void testExistence() throws FileNotFoundException {
        if (Files.exists(p)); else {
            throw new FileNotFoundException();
        }
    }
}
