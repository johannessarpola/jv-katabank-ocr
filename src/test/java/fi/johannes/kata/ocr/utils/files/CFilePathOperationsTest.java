/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johannes.kata.ocr.utils.files;

import TestUtils.TestFileUtils;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Johannes
 */
public class CFilePathOperationsTest {
    String prefix;
    String nm;
    Path p;
    public CFilePathOperationsTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        TestFileUtils.cleanTestFiles();
    }
    
    @Before
    public void setUp() throws IOException {
        nm = this.getClass().getName();
        nm = TestFileUtils.classDotsToSeparators(nm);
        p = TestFileUtils.createFile(nm);
        prefix = TestFileUtils.testFilesFolder;
    }
    
    /**
     * Test of stringToPath method, of class CFilePathOperations.
     * @throws java.lang.Exception
     */
    @Test
    public void testStringToPath() throws Exception {
        System.out.println("pathToString");
        CFilePathOperations instance = new CFilePathOperations();
        Path expResult = p;
        Path result = instance.stringToPath(prefix+nm);
        assertEquals(expResult.toString(), result.toString());
    }
    @Test
    public void testValidatePath(){
        String filename = "afile.md";
        String filename2 = "test";
        String filename3 = ":\\/";
        
        assertEquals(true, CFilePathOperations.validatePath(filename));
        assertEquals(true, CFilePathOperations.validatePath(filename2));
        assertEquals(false, CFilePathOperations.validatePath(filename3));
        
    }

    
}
