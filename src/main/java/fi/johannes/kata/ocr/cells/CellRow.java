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

import com.google.common.base.Splitter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Builds list of three by three cells from input
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 25, 2016
 */
// Wrap this around with multi three line stuff (height)
public class CellRow {

    private List<Cell> cells;
    private int celllength;
    private Splitter fixedLengthSplitter;

    public CellRow(List<String> lines, int cellLength) {
        init(cellLength);
        cells = readFromList(lines);
    }

    public CellRow(Stream stream, int cellLength) {
        init(cellLength);
        cells = readCellsFromStream(stream);
    }

    private void init(int cellLength) {
        this.celllength = cellLength;
        this.fixedLengthSplitter = Splitter.fixedLength(celllength);

    }

    /**
     * Gets cell
     *
     * @param index
     * @return
     * @throws IndexOutOfBoundsException implicintly throw exception as index
     * might be too big
     */
    public Cell getCell(int index) throws IndexOutOfBoundsException {
        if (index < cells.size()) {
            return cells.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * gets cells as list
     *
     * @return
     */
    public List<Cell> getAsList() {
        return cells;
    }

    /**
     * checks if index fits
     *
     * @param index
     * @return
     */
    public boolean fits(int index) {
        return index < cells.size();
    }

    /**
     * returns size
     *
     * @return
     */
    public int size() {
        return cells.size();
    }

    /**
     * Reads from list of lines
     *
     * @param ls
     * @return
     */
    private List<Cell> readFromList(List<String> ls) {
        List<Cell> tCell = new ArrayList<>();
        for (String line : ls) {
            List<String> parts = fixedLengthSplitter.splitToList(line);
            for (int i = 0; i < parts.size(); i++) {
                // if cell doesn't exist yet create
                try {
                    tCell.get(i).append(parts.get(i));
                } catch (IndexOutOfBoundsException e) {
                    Cell newCell = new Cell();
                    newCell.append(parts.get(i));
                    tCell.add(i, newCell);
                }
            }
            // otherwise append
        }

        return tCell;
    }

    /**
     * Reads from stream
     *
     * @param st
     * @return
     */
    private List<Cell> readCellsFromStream(Stream<String> st) {
        List<String> lines = new ArrayList<>();
        st.forEach((String t) -> {
            lines.add(t);
        });
        return readFromList(lines);
    }
}
