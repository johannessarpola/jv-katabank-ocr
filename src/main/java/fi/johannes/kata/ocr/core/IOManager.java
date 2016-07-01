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
import fi.johannes.kata.ocr.io.InputFolderReader;
import fi.johannes.kata.ocr.io.OutputFolderWriter;
import fi.johannes.kata.ocr.utils.TimeUtils;
import fi.johannes.kata.ocr.utils.structs.Filename;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Johannes Sarpola
 * @date Jul 1, 2016
 */
public class IOManager implements Iterable<Path> {

    InputFolderReader input;
    OutputFolderWriter output;

    List<Path> inputFiles;

    public IOManager(Path inputPath, Path outputPath) {
        input = new InputFolderReader(inputPath);
        output = new OutputFolderWriter(outputPath);
    }

    public void connectToInputFiles() throws IOException {
        inputFiles = input.getFiles();
    }

    public void createOutputFolder() throws IOException {
        output.create();
    }
    public void clearOutputfolder(){
        output.clear();
    }
    public void writeToFile(Filename source, List<String> lines) throws IOException{
        String file = createNameForOutput(source);
        output.write(lines, file);
    }
    
    @Override
    public Iterator<Path> iterator() {
        IOManager instance = this;
        Iterator<Path> it = new Iterator<Path>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < instance.inputFiles.size() && instance.inputFiles.get(currentIndex) != null;
            }

            @Override
            public Path next() {
                return instance.inputFiles.get(currentIndex++);
            }

            @Override
            public void remove() {
                instance.inputFiles.remove(currentIndex);
            }
        };
        return it;
    }

    /**
     * Creates a name with source name, date and filler with given extension
     *
     * @param source Filename
     * @return
     */
    private String createNameForOutput(Filename source) {
        StringBuilder sb = new StringBuilder();
        sb.append(ApplicationStrings.InputOutput.I.SOURCE_DESCRIPTION_PREFIX);
        sb.append(ApplicationStrings.InputOutput.CONNECTING_CHARACTER);
        sb.append(source.getName());
        sb.append(ApplicationStrings.InputOutput.CONNECTING_CHARACTER);
        sb.append(TimeUtils.getCurrentDate());
        sb.append(ApplicationStrings.InputOutput.CONNECTING_CHARACTER);
        sb.append(ApplicationStrings.InputOutput.O.OUTPUT_DESCRIPTION);
        sb.append(ApplicationStrings.InputOutput.O.OUTPUT_EXTENSION);
        return sb.toString();
    }

    public InputFolderReader getInput() {
        return input;
    }

    public List<Path> getInputFiles() {
        return inputFiles;
    }

    public OutputFolderWriter getOutput() {
        return output;
    }

}
