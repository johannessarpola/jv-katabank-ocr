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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 30, 2016
 */
public class CellRows implements Iterable<CellRow> {

    private List<CellRow> cellRows;
    private Integer height;
    private Integer width;
    private Integer numbersOnRow;

    public CellRows(IntegerPair pair, Integer numbersOnRow, List<String> lines) throws IOException {
        init(pair, numbersOnRow);
        buildRows(lines);
    }

    private void init(IntegerPair pair, Integer numbersOnRow) {
        this.width = pair.getX();
        this.height = pair.getY();
        this.numbersOnRow = numbersOnRow;
        cellRows = new ArrayList<>();

    }

    private void buildRows(List<String> lines) throws IOException {
        RowBundles<String> bundles = new RowBundles<>(lines, height);
        Iterator<RowBundle<String>> iterator = bundles.iterator();
        while (iterator.hasNext()) {
            RowBundle<String> rb = iterator.next();
            CellRow cr = new CellRow(rb.getRows(), width, numbersOnRow);
            cellRows.add(cr);
        }
    }

    @Override
    public Iterator<CellRow> iterator() {
        CellRows instance = this;
        Iterator<CellRow> it = new Iterator<CellRow>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < instance.cellRows.size() && instance.cellRows.get(currentIndex) != null;
            }

            @Override
            public CellRow next() {
                return instance.cellRows.get(currentIndex++);
            }

            @Override
            public void remove() {
                instance.cellRows.remove(currentIndex);
            }
        };
        return it;
    }

}
