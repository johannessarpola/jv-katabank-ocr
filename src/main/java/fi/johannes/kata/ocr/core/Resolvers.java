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

import com.google.common.collect.ImmutableMap;
import fi.johannes.kata.ocr.cells.Cell;
import fi.johannes.kata.ocr.core.data.ApplicationData;
import fi.johannes.kata.ocr.core.data.ApplicationProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Johannes Sarpola
 * @date Jul 2, 2016
 */
public class Resolvers {

    public static class CellNumber {

        private static final Map<String, Integer> MAPPED_DIGITS = ImmutableMap.<String, Integer>builder()
                .put(ApplicationData.Digits.One(), 1)
                .put(ApplicationData.Digits.Two(), 2)
                .put(ApplicationData.Digits.Three(), 3)
                .put(ApplicationData.Digits.Four(), 4)
                .put(ApplicationData.Digits.Five(), 5)
                .put(ApplicationData.Digits.Six(), 6)
                .put(ApplicationData.Digits.Seven(), 7)
                .put(ApplicationData.Digits.Eight(), 8)
                .put(ApplicationData.Digits.Nine(), 9)
                .put(ApplicationData.Digits.Zero(), 0).build();

        /**
         *
         * Uses the static map of Digits
         *
         * @param cell Digit cell
         * @return Integer what's represented in the Cell, or -1 if it couldn't
         * be resolved
         */
        public static Integer resolveNumber(Cell cell) {
            String cStr = cell.toString();
            if (MAPPED_DIGITS.containsKey(cStr)) {
                return MAPPED_DIGITS.get(cStr);
            } else {
                // Preferred as opposed to null as we know what are possible digits
                return ApplicationProperties.Cells.INVALID_NUMBER;
            }

        }

        /**
         * Decision tree to figure out numbers from the list of cells
         *
         * @param cells Digit cells
         * @return List of Integers what's represented in the Cells, or -1 for a
         * cell if it couldn't be resolved
         */
        public static List<Integer> resolveNumbers(List<Cell> cells) {
            List<Integer> integers = new ArrayList<>();
            for (Cell cell : cells) {
                integers.add(resolveNumber(cell));
            }
            return integers;
        }

        public static List<Integer> resolveNumberWithPossibilities(Cell cell) {
            List<Cell> permutations = resolvePermutations(cell);
            List<Integer> possibleNumbers = new ArrayList<>();

            // first gets added which can be invalid as well
            possibleNumbers.add(resolveNumber(cell));
            for (Cell c : permutations) {
                Integer n = resolveNumber(c);
                // No need for extra invalid numbers
                if (!n.equals(ApplicationProperties.Cells.INVALID_NUMBER)) {
                    possibleNumbers.add(n);
                }
            }
            return possibleNumbers;
        }

        private static List<Cell> resolvePermutations(Cell cell) {
            // arr 0-8
            List<Cell> permutations = resolveValidPermutations(cell);
            return permutations;
        }

        private static List<Cell> resolveValidPermutations(Cell cellParam) {
            int length = cellParam.toString().length();
            List<Cell> validPermutations = new ArrayList<>();
            char originalChar;
            for (int i = 0; i < length; i++) {
                try {
                    // Deep copy of original to permutate
                    for (char c : ApplicationData.CHAR_ALTERATIONS) {
                        permutate(cellParam, validPermutations, c, i);
                    }

                } catch (Exception e) {

                }

            }
            return validPermutations;
        }

        private static void permutate(Cell cellParam, List<Cell> validPermutations, char toBeSwapped, int position) {
            char originalChar;
            // Deep copy of original to permutate
            Cell copyOfCell = cellParam.deepCopyOf();

            // Try adding vertical base
            originalChar = copyOfCell.swapChar(position, toBeSwapped);
            copyOfCell = vaidatePermutation(copyOfCell, originalChar, position);
            if (copyOfCell.doKeep() && !cellParam.equals(copyOfCell)) {
                validPermutations.add(copyOfCell);
            }
        }

        /**
         * Checks if the cell is resolvable
         *
         * @param cell
         * @param original
         * @param position
         * @return original if it was not, altered if it was
         */
        private static Cell vaidatePermutation(Cell cell, char original, int position) {
            // Check if it's valid
            if (!resolveNumber(cell).equals(ApplicationProperties.Cells.INVALID_NUMBER)) {
                cell.keep();
                return cell;
            } else {
                // Swap to original
                cell.setKeep(false);
                cell.swapChar(position, original);
            }
            return cell;
        }

        public static boolean isInvalidNumber(Integer integer) {
            if (integer.equals(ApplicationProperties.Cells.INVALID_NUMBER)) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * Gets the numerical value of invalid Number
         *
         * @return
         */
        public static Integer InvalidNumber() {
            return ApplicationProperties.Cells.INVALID_NUMBER;
        }

    }
}
