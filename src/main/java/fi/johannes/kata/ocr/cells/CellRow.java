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

/**
 * Builds list of three by three cells from input
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 25, 2016
 */
// Wrap this around with multi three line stuff (height)
public class CellRow {

    private List<Cell> cells;
    private Integer celllength;
    private Integer cellsOnRow;

    private CellRow() {
        cells = new ArrayList<>();
    }

    public CellRow(List<String> lines, Integer cellLength) {
        init(cellLength, -1);
        cells = readFromList(lines);
    }

    public CellRow(List<String> lines, Integer cellLength, Integer cellsOnRow) {
        init(cellLength, cellsOnRow);
        cells = readFromList(lines);
    }

    private void init(int cellLength, Integer cellsOnRow) {
        this.celllength = cellLength;
        this.cellsOnRow = cellsOnRow;

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

        Splitter rowSplitter;
        if (cellsOnRow != -1) {
            rowSplitter = Splitter.fixedLength(cellsOnRow * celllength);
        } else {
            rowSplitter = Splitter.fixedLength(ls.get(0).length());
        }
        Splitter cellSplitter = Splitter.fixedLength(celllength);;
        for (String line : ls) {
            String fixedWidthLine = rowSplitter.splitToList(line).get(0);

            List<String> parts = cellSplitter.splitToList(fixedWidthLine);
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

}
