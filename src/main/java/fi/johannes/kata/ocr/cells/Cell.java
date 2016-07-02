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

import fi.johannes.kata.ocr.core.data.ApplicationStrings;
import fi.johannes.kata.ocr.utils.Logging;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Cell for the numbers, designed to be built by appending when read line by
 * line, basically a wrapper around StringBuilder.
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 25, 2016
 */
public class Cell {

    StringBuilder cellContent;
    StringBuilder copyOfContent = new StringBuilder();
    boolean altered = false;
    boolean keep = true;

    public Cell(String completeString) {
        cellContent = new StringBuilder(completeString);
    }

    public Cell(List<String> lines) {
        cellContent = new StringBuilder();
        for (String line : lines) {
            cellContent.append(line);
        }
    }

    public Cell() {
        cellContent = new StringBuilder();
    }

    public void append(String stuff) {
        cellContent.append(stuff);
    }

    @Override
    public String toString() {
        return cellContent.toString();
    }

    public Character[] toCharacterArray() {
        char[] c = cellContent.toString().toCharArray();
        return ArrayUtils.toObject(c);
    }

    public char swapChar(int pos, char c) throws ArrayIndexOutOfBoundsException {
        char original;
        altered = true;
        copyOfContent = new StringBuilder(cellContent.toString());
        try {
            original = cellContent.charAt(pos);
            cellContent.setCharAt(pos, c);
            return original;
        }
        catch(Exception e) {
            ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException();
            Logging.logMessageWithExpection_Error(this.getClass(), ApplicationStrings.LoggingMessages.Error.CELL_SWAP_AT_INVALID_INDEX, arrayIndexOutOfBoundsException);
            throw arrayIndexOutOfBoundsException;
        }
    }
    /**
     * Basically just clears the copy buffer, could save couple bytes
     */
    public void keep(){
        setKeep(true);
        copyOfContent = null;
    }
    /**
     * Resets the cell to original state
     */
    public void reset(){
        altered = false;
        if(copyOfContent != null && !copyOfContent.toString().equals(cellContent.toString())) {
            cellContent = new StringBuilder(copyOfContent.toString());
        }
        
    }
    /**
     * Checks if cell is altered
     * @return 
     */
    public boolean isAltered() {
        return altered;
    }
    /**
     * Performs deep copy of Cell
     * @return 
     */
    public Cell deepCopyOf(){
        return new Cell(cellContent.toString());
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    public boolean doKeep() {
        return keep;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.cellContent);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (!Objects.equals(this.cellContent.toString(), other.cellContent.toString())) {
            return false;
        }
        return true;
    }
    
}
