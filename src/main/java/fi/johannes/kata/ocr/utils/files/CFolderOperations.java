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
package fi.johannes.kata.ocr.utils.files;

import fi.johannes.kata.ocr.utils.Logging;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola@gmail.com>
 */
public class CFolderOperations {
    /**
     * files in folder
     *
     * @param path
     * @return
     */
    public static File[] getFilesInFolder(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }

    /**
     * filenames in folder
     *
     * @param path
     * @return
     */
    public static List<String> getFilenamesInFolder(String path) {
        File[] files = getFilesInFolder(path);
        List<String> names = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                names.add(file.getName());
            }
        }
        return names;
    }

    /**
     * counts files
     *
     * @param path
     * @return
     */
    public static int countFiles(String path) {
        return getFilesInFolder(path).length;
    }

    /**
     * Reads all files in folder Java 1.8
     *
     * @param folder
     * @return
     * @throws IOException
     */
    // TODO Maybe add chunk based reading (?)
    public static LinkedList<List<String>> readAllFilesInFolder(String folder) throws IOException {
        LinkedList<List<String>> l = new LinkedList<>();
        Files.walk(Paths.get(folder)).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                try {
                    List<String> lines = CFileOperations.getFileContentAsStrings(filePath);
                    l.add(lines);
                } catch (IOException ex) {
                    Logging.logStackTrace_Error(CFolderOperations.class, ex);
                }
            }
        });
        return l;
    }

    /**
     * Creates folder or returns existing
     *
     * @param folder
     * @return
     */
    public static File createFolder(String folder) {
        if (doesFolderExist(folder)) {
            return new File(folder);
        } else {
            File f = new File(folder);
            f.mkdir();
            return f;
        }
    }

    public static boolean doesFolderExist(String folder) {
        File f = new File(folder);
        return f.isDirectory();
    }
    /**
     * Removes folder and everything inside it
     * @param folder 
     */
    // TODO This doesn't work always, need to be tested
    public static void recursiveDelete(String folder) {
        File f = new File(folder);
        if (f.exists() && f.isDirectory()) {
            File[] fs = CFolderOperations.getFilesInFolder(folder);
            for (File fo : fs) {
                fo.delete();
                // Back up for deletion upon closing VM
                if(fo.exists()){
                    fo.deleteOnExit();
                }
            }
            f.delete();
            // Back up for deletion upon closing VM
            if(f.exists()){
                f.deleteOnExit();
            }
        }
    }

    /**
     * Creates dir or returns
     *
     * @param folder
     */
    private static File mkDir(String folder) throws NotDirectoryException {
        File f = new File(folder);
        if (!doesFolderExist(folder)) {
            f.mkdir();
        }
        if (f.isDirectory()) {
            return f;
        } else {
            throw new NotDirectoryException(folder);
        }
    }

    /**
     * Merges files from
     * http://www.programcreek.com/2012/09/merge-files-in-java/
     *
     * @param files
     * @param mergedFile
     * @throws IOException
     */
    public static void mergeFiles(File[] files, File mergedFile) throws IOException {

        FileWriter fstream;
        BufferedWriter out;
        fstream = new FileWriter(mergedFile, true);
        out = new BufferedWriter(fstream);
        for (File f : files) {
            FileInputStream fis;
            fis = new FileInputStream(f);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(fis))) {
                String aLine;
                while ((aLine = in.readLine()) != null) {
                    out.write(aLine);
                    out.newLine();
                }
            }

        }
        out.close();
    }
}
