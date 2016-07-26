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
package fi.johannes.kata.ocr.cells;

import fi.johannes.kata.ocr.utils.files.ExistingFileConnection;
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
    ExistingFileConnection fc;

    public CellRowsTest() {
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("testrows");
        p = Paths.get(url.getPath().substring(1));
        fc = new ExistingFileConnection(url.getPath().substring(1));
    }

    /**
     * Test of iterator method, of class CellRows.
     */
    @Test
    public void testBuilder() throws IOException {
        List<String> lines = Files.readAllLines(p);
        int size = lines.size();
        pair = new IntegerPair(2, size / 2);
        crs = CellRows.Builder.build(pair, size, lines);
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
