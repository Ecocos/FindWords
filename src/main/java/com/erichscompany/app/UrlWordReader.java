/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erichscompany.app;

/**
 *
 * @author Erich
 */
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * used to get words from online dictionary
 * @author Erich
 */
public class UrlWordReader {
    private static URL dictionary;
    
    /**
     * takes url for online dictionary. does not call until getWords() method is called
     * @param url
     * @throws MalformedURLException 
     */
    public UrlWordReader(String url) throws MalformedURLException
    {
        dictionary = new URL(url);
    }
    
 /**
  * calls web url and gets list of words
  * @return
  * @throws MalformedURLException
  * @throws IOException 
  */
    public List<String> getWords() throws MalformedURLException, IOException
    {
        List<String> wordList = new ArrayList<String>();
        BufferedReader in = new BufferedReader(
        new InputStreamReader(dictionary.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            wordList.add(inputLine);
        in.close();
 
        return wordList;
    }

    
}
