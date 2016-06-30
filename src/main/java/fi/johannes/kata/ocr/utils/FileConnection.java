/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class FileConnection {

    Path p;

    public FileConnection(String path) throws FileNotFoundException {
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
