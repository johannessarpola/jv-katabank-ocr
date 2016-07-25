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

import fi.johannes.kata.ocr.cells.CellRows;
import fi.johannes.kata.ocr.core.data.ApplicationProperties;
import fi.johannes.kata.ocr.core.data.ApplicationStrings;
import fi.johannes.kata.ocr.utils.AppLogging;
import fi.johannes.kata.ocr.utils.ResourceGetter;
import fi.johannes.kata.ocr.utils.files.ExistingFileConnection;
import fi.johannes.kata.ocr.utils.structs.Filename;
import fi.johannes.kata.ocr.utils.structs.IntegerPair;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 25, 2016
 */
public class OCR {

    IOManager ioManager;
    Path input;
    Path output;

    public OCR() throws IOException {
        defaultPaths();
        init();

    }

    public OCR(Path input, Path output) throws IOException {
        AppLogging.logMessage_Info(this.getClass(), ApplicationStrings.LoggingMessages.Info.INPUT_PATH_IS + input.toAbsolutePath().toString());
        AppLogging.logMessage_Info(this.getClass(), ApplicationStrings.LoggingMessages.Info.OUTPUT_PATH_IS + output.toAbsolutePath().toString());

        this.input = input;
        this.output = output;
        init();
    }

    private void init() throws IOException {
        ioManager = new IOManager(input, output);
        ioManager.connectToInputFiles();

    }

    private void defaultPaths() {
        try {
            input = ResourceGetter.getPath("ocr/");
            output = Paths.get("./result");
        } catch (URISyntaxException ex) {
            AppLogging.logStackTrace_Error(this.getClass(), ex);
        }
    }

    public void run() throws FileNotFoundException, IOException {

        Iterator<Path> pathIter = ioManager.iterator();
        ioManager.createOutputFolder();

        IntegerPair cellSize = new IntegerPair(ApplicationProperties.Cells.CELL_WIDTH, ApplicationProperties.Cells.CELL_HEIGHT);
        Integer cellsOnRow = ApplicationProperties.Cells.CELLS_ON_ROW;
        CellRows crs;
        List<OCREntry> entries;
        List<String> entriesStr;
        Filename source;

        while (pathIter.hasNext()) {
            Path p = pathIter.next();
            AppLogging.logMessage_Info(this.getClass(), ApplicationStrings.LoggingMessages.Info.CURRENT_PATH_IS+p.toString());
            source = new Filename(p);
            crs = createCellRows(p, cellSize, cellsOnRow);
            entries = OCREntryBuilder.buildEntries(crs);
            entriesStr = OCROutputBuilder.buildOutput(entries);
            ioManager.writeToFile(source, entriesStr);
        }

    }

    public void clearPreviousOutputs() {
        ioManager.clearOutputfolder();

    }

    private CellRows createCellRows(Path p, IntegerPair cellSize, Integer cellsOnRow) throws FileNotFoundException, IOException {
        ExistingFileConnection efc = new ExistingFileConnection(p);
        return new CellRows(cellSize, cellsOnRow, efc);
    }
}
