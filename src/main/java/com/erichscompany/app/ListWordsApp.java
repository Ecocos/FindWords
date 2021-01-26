package com.erichscompany.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Portions of this code are authored by robau ( https://codereview.stackexchange.com/users/21279/robau )
 * Code at https://codereview.stackexchange.com/questions/166281/find-all-words-in-a-dictionary-that-can-be-made-with-a-string-of-characters-rec
 *
 */
public class ListWordsApp 
{
    private static List<String> _wordList;
    Dictionary dict;
    
    /**
     * Finds all combinations of words from the letters in a string
     * @param dictionary Dictionary object for verifying words that are generated
     * @param wordGeneratorWordList list of words used for generating words.
     * @throws IOException 
     */
    public ListWordsApp(Dictionary dictionary, List<String> wordGeneratorWordList) throws IOException{
        _wordList = wordGeneratorWordList;
        dict = dictionary;
    }
     
    /**
     * returns all word combinations from the characters in the passed string
     * @param word
     * @return returns string[] of found words
     * @throws IOException 
     */
    public String[] GetWords(String word) throws IOException{
        List<String> foundWords = new ArrayList<String>();
        List<String> lowercase = _wordList.stream().map( s -> s.toLowerCase() ).filter( s->s.chars().allMatch(Character::isLetter)).collect( Collectors.toList() );
        foundWords = findOptions( word, lowercase );
        
        List<String>finalWordList = new ArrayList<String>();
        for(String s :foundWords){
            if(dict.isEnglishWord(s)){
                finalWordList.add(s);
            }
        }

        String[] tmpArray = new String[finalWordList.size()];// foundWords.toArray(foundWords);
        tmpArray = finalWordList.toArray(tmpArray);
        return tmpArray;
    }
    
     /**
     * 
     * @param string string to test
     * @param lowercase list of words
     */
    private static List<String> findOptions( String string, List<String> lowercase )
    {
        List<String> foundWords = new ArrayList<String>();
        int[] freq = toFreq( string.toLowerCase().trim());
        for ( String l : lowercase )
        {
            int[] freqIn = toFreq( l );
            
            if ( matches( freq, freqIn ) )
            {
                foundWords.add(l);
            }
        }
        return foundWords;
    }

    /**
     * Returns true if all the frequencies of the letters match.
     * 
     * @param freq
     * @param freqIn
     * @return
     */
    private static boolean matches( int[] freq, int[] freqIn )
    {
        for ( int i = 0; i < 26; i++ )
        {
            if ( freq[i] == 0 && freqIn[i]>0) //didn't have letter we needed
            {
                return false;
            }
            else if (freq[i] < freqIn[i])
            {
                return false; //not enough letters
            }

        }
        return true;
    }

    /**
     * Encode a word in to a frequency array. int[0] = #a's, int[1] = #b's etc.
     * 
     * @param string
     * @return
     */
    private static int[] toFreq( String string )
    {
        int[] freq = new int[26];
        for ( char c : string.toCharArray() )
        {
            if ( ( c - 'a' ) >= 0 && ( c - 'a' ) < 26 )
            {
                freq[c - 'a']++;
            }
        }
        return freq;
    }
}
