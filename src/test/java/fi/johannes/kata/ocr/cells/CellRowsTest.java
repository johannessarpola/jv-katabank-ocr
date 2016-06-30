/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johannes.kata.ocr.cells;

import fi.johannes.kata.ocr.utils.FileConnection;
import fi.johannes.kata.ocr.utils.structs.IntegerPair;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 */
public class CellRowsTest {

    CellRows crs;
    IntegerPair pair;
    Path p;
    FileConnection fc;

    public CellRowsTest() {
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("testrows");
        p = Paths.get(url.getPath().substring(1));
        fc = new FileConnection(url.getPath().substring(1));
    }

    /**
     * Test of iterator method, of class CellRows.
     */
    @Test
    public void testBuilder() throws IOException {
        List<String> lines = Files.readAllLines(p);
        int size = lines.size();
        pair = new IntegerPair(2, size / 2);
        crs = new CellRows(pair, fc);
        Iterator<CellRow> iterator = crs.iterator();
        while (iterator.hasNext()) {
            CellRow cellr = iterator.next();
            List<Cell> cells = cellr.getAsList();
            Integer iter = 1;
            for (Cell cell : cells) {
                String cStr = cell.toString();
                System.out.println(cStr);
                for (Character ch : cStr.toCharArray()) {
                    assertEquals(iter.toString(), ch.toString());
                }
                iter++;
            }

        }
    }

}
