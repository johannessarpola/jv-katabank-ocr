/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johannes.kata.ocr.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import org.apache.commons.io.IOUtils;

/**
  Gets files in the Resoures folder
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 30, 2016
 */
public class ResourceGetter {

    public static String getContents(String fileName) {
        String result = "";
        Reader r = getReader(fileName);
        try {
            result = IOUtils.toString(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Reader getReader(String fileName) {
        ClassLoader classLoader = ResourceGetter.class.getClassLoader();
        Reader r = new InputStreamReader(classLoader.getResourceAsStream(fileName));
        return r;
    }

    public static URL getUrl(String fileName) {
        ClassLoader classLoader = ResourceGetter.class.getClassLoader();
        URL url =  classLoader.getResource(fileName);
        return url;
    }
    public static String getPath(String fileName) {
        URL url =  getUrl(fileName);
        if(url.getPath().startsWith("/")) {
            return url.getPath().substring(1);
        }
        return url.getPath();
    }
}
