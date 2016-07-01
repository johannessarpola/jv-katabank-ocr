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
package fi.johannes.kata.ocr.io;

import fi.johannes.kata.ocr.core.data.ApplicationStrings;
import fi.johannes.kata.ocr.utils.Logging;
import fi.johannes.kata.ocr.utils.files.CFolderOperations;
import fi.johannes.kata.ocr.utils.structs.Filename;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 *
 * @author Johannes Sarpola
 * @date Jul 1, 2016
 */
public class Output {

    Path folder;

    public Output() {

    }

    public void write(List<String> lines, Filename filename) throws IOException {
        // TODO Fix if file exists
        Files.write(filename.getAbsolutePath(), lines, StandardOpenOption.CREATE_NEW);

    }

    public void connectToFolder(Path folder) throws NotDirectoryException {
        this.folder = folder;
        if (!Files.isDirectory(folder)) {
            Logging.logMessageWithExpection_Fatal(this.getClass(), ApplicationStrings.LoggingMessages.Error.FOLDER_CONNECTION_UNSUCCESSFUL, new NotDirectoryException(folder.toString()));
            throw new NotDirectoryException(folder.toString());
        } else if (!Files.exists(folder)) {
            CFolderOperations.createFolder(folder.toString());
        } else {
            Logging.logMessage_Info(this.getClass(), ApplicationStrings.LoggingMessages.Info.FOLDER_CONNECTION_SUCCESSFUL);
        }
    }

    public Path getFolder() {
        return folder;
    }
    
}