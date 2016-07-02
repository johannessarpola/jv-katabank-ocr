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
package fi.johannes.kata.ocr.checksum;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * Holds the checksum and you can check if it's valid with isValid
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 25, 2016
 */
public class Checksum {

    private final boolean valid;
    private final Double checksum;
    final Integer modulo;
    
    
    public Checksum(List<Integer> numbers, Integer modulo) {
        this.modulo = modulo;
        this.checksum = calculateChecksum(numbers);
        this.valid = validate();
    }
    public Checksum(List<Integer> numbers) {
        this.modulo = 11; // default modulo
        this.checksum = calculateChecksum(numbers);
        this.valid = validate();
    }
    /**
     * account number: 3 4 5 8 8 2 8 6 5 
     * position names: d9 d8 d7 d6 d5 d4 d3 d2 d1
     *
     * checksum calculation: (d1+2*d2+3*d3 +..+9*d9) mod 11 = 0
     *
     */
    private Double calculateChecksum(List<Integer> numbers) {
        List<Integer> tNumbers = Lists.reverse(numbers);
        Double sum = 0.;
        Integer j = 1;
        for (Integer i = 0; i < tNumbers.size(); i++) {
            Integer val = tNumbers.get(i);
            val = val * j;
            sum += val;
            j++;
        }
        return sum;
    }
    private boolean validate(){
        return checksum%modulo==0;
    }

    public boolean isValid() {
        return valid;
    }

    public Double getChecksum() {
        return checksum;
    }
    
}
