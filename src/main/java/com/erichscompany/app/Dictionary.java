/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erichscompany.app;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class is used for verifying strings are words
 * @author Erich
 */
public class Dictionary {
    private List<String> lowercase;
    
    /**
     * constructor uses urlWordReader to get dictionary
     * @param urlWordReader
     * @throws IOException 
     */
    public Dictionary(UrlWordReader urlWordReader) throws IOException{
        lowercase = urlWordReader.getWords();
    }
    
    /**
     * Checks if a word is in the dictionary (assumes english word list)
     * @param word
     * @return 
     */
    public Boolean isEnglishWord(String word)
    {
        return lowercase.contains(word.toLowerCase().trim() );
    }
}