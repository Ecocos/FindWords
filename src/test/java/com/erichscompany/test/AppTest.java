package com.erichscompany.test;
import static org.mockito.Mockito.*;
import com.erichscompany.app.Dictionary;
import com.erichscompany.app.ListWordsApp;
import com.erichscompany.app.UrlWordReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 * NOTE: The word list came from: "https://raw.githubusercontent.com/dwyl/english-words/master/words.txt"
 */
public class AppTest 
{
     /**
     * happy path
     */
    @Test
    public void happyPath() throws IOException
    {
        UrlWordReader mockReader = mock(UrlWordReader.class);
        List<String> wordList ;
        wordList = new ArrayList<String>();
        wordList.add("dork");
        wordList.add("work");  
        when(mockReader.getWords()).thenReturn(wordList);        

        String filePath = "./src/test/java/com/erichscompany/test/DictionaryWords.txt";
        File file = new File(filePath);
        List<String> list = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
        //uncomment these lines to use non-mocked test (changes results)
        //UrlWordReader urlWordReader = new UrlWordReader("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt");
        //list = urlWordReader.getWords();
        Dictionary dict = new Dictionary(mockReader);
        ListWordsApp wordApp = new ListWordsApp(dict,list);
        String[] words = wordApp.GetWords("Working");
        assertTrue(words.length == 1);
        assertTrue(words[0].equals("work") );
    }
 
     /**
     * tests a happy path
     * Note this test uses the DictionaryWords.txt file for both generating and verifying words
     */
    @Test
    public void happyPathFullDictionary() throws IOException
    {
        UrlWordReader mockReader = mock(UrlWordReader.class);
        String filePath = "./src/test/java/com/erichscompany/test/DictionaryWords.txt";
        File file = new File(filePath);
        List<String> wordList = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
        when(mockReader.getWords()).thenReturn(wordList);        
        
        Dictionary dict = new Dictionary(mockReader);
        ListWordsApp wordApp = new ListWordsApp(dict,wordList);
        String[] words = wordApp.GetWords("Working");
        assertTrue(words.length == 78);
        assertTrue(words[0].equals("gink") );
    }
    
     /**
     * tests that passing a blank string will not cause throw
     * Note this test uses the DictionaryWords.txt file for both generating and verifying words
     */
    @Test
    public void blankDoesNotThrow() throws IOException
    {
        UrlWordReader mockReader = mock(UrlWordReader.class);
        String filePath = "./src/test/java/com/erichscompany/test/DictionaryWords.txt";
        File file = new File(filePath);
        List<String> wordList = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
        when(mockReader.getWords()).thenReturn(wordList);        
        
        Dictionary dict = new Dictionary(mockReader);
        ListWordsApp wordApp = new ListWordsApp(dict,wordList);
        String[] words = wordApp.GetWords(" ");
        assertTrue(words.length == 0);
       
    }
    
         /**
     * tests that passing a blank string will not cause throw
     * Note this test uses the DictionaryWords.txt file for both generating and verifying words
     */
    @Test
    public void MultiByteDoesNotThrow() throws IOException
    {
        UrlWordReader mockReader = mock(UrlWordReader.class);
        String filePath = "./src/test/java/com/erichscompany/test/DictionaryWords.txt";
        File file = new File(filePath);
        List<String> wordList = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
        when(mockReader.getWords()).thenReturn(wordList);        
        
        Dictionary dict = new Dictionary(mockReader);
        ListWordsApp wordApp = new ListWordsApp(dict,wordList);
        String[] words = wordApp.GetWords("稲妻");
        assertTrue(words.length == 0);
    }
    
         /**
     * tests sending two words, still returns words
     * Note this test uses the DictionaryWords.txt file for both generating and verifying words
     */
    @Test
    public void WorksWithAnEmbeddedSpace() throws IOException
    {
        UrlWordReader mockReader = mock(UrlWordReader.class);
        String filePath = "./src/test/java/com/erichscompany/test/DictionaryWords.txt";
        File file = new File(filePath);
        List<String> wordList = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
        when(mockReader.getWords()).thenReturn(wordList);        
        
        Dictionary dict = new Dictionary(mockReader);
        ListWordsApp wordApp = new ListWordsApp(dict,wordList);
        String[] words = wordApp.GetWords("Working Hard");
        assertTrue(words.length == 473);
        assertTrue(words[0].equals("a") );
    }
    
            /**
     * tests sending garbage
     * Note this test uses the DictionaryWords.txt file for both generating and verifying words
     */
    @Test
    public void NonAlphabeticCharsDoNotReturnWords() throws IOException
    {
        UrlWordReader mockReader = mock(UrlWordReader.class);
        String filePath = "./src/test/java/com/erichscompany/test/DictionaryWords.txt";
        File file = new File(filePath);
        List<String> wordList = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
        when(mockReader.getWords()).thenReturn(wordList);        
        
        Dictionary dict = new Dictionary(mockReader);
        ListWordsApp wordApp = new ListWordsApp(dict,wordList);
        String[] words = wordApp.GetWords("*^(&^*&(");
        assertTrue(words.length == 0);
    }
    
               /**
     * tests with a beginning space
     * Note this test uses the DictionaryWords.txt file for both generating and verifying words
     */
    @Test
    public void PrefixSpaceIgnored() throws IOException
    {
        UrlWordReader mockReader = mock(UrlWordReader.class);
        String filePath = "./src/test/java/com/erichscompany/test/DictionaryWords.txt";
        File file = new File(filePath);
        List<String> wordList = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
        when(mockReader.getWords()).thenReturn(wordList);        
        
        Dictionary dict = new Dictionary(mockReader);
        ListWordsApp wordApp = new ListWordsApp(dict,wordList);
        String[] words = wordApp.GetWords(" Working");
        assertTrue(words.length == 78);
        assertTrue(words[0].equals("gink") );
    }
    
    /** tests with an ending space
     * Note this test uses the DictionaryWords.txt file for both generating and verifying words
     */
    @Test
    public void SuffixSpaceIgnored() throws IOException
    {
        UrlWordReader mockReader = mock(UrlWordReader.class);
        String filePath = "./src/test/java/com/erichscompany/test/DictionaryWords.txt";
        File file = new File(filePath);
        List<String> wordList = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
        when(mockReader.getWords()).thenReturn(wordList);        
        
        Dictionary dict = new Dictionary(mockReader);
        ListWordsApp wordApp = new ListWordsApp(dict,wordList);
        String[] words = wordApp.GetWords("Working ");
        assertTrue(words.length == 78);
        assertTrue(words[0].equals("gink") );
    }
    
     /**
     * tests a string with different capitalizations.
     * Note this test uses the DictionaryWords.txt file for both generating and verifying words
     */
    @Test
    public void CaseInsensitive() throws IOException
    {
        UrlWordReader mockReader = mock(UrlWordReader.class);
        String filePath = "./src/test/java/com/erichscompany/test/DictionaryWords.txt";
        File file = new File(filePath);
        List<String> wordList = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
        when(mockReader.getWords()).thenReturn(wordList);        
        
        Dictionary dict = new Dictionary(mockReader);
        ListWordsApp wordApp = new ListWordsApp(dict,wordList);
        String[] words = wordApp.GetWords("aBdeI");
        assertTrue(words.length == 24);
        assertTrue(words[3].equals("abide") );
    }
    
         /**
     * tests that passing a blank string will not cause throw
     * Note this test uses the DictionaryWords.txt file for both generating and verifying words
     */
    @Test
    public void EmptyDoesNotThrow() throws IOException
    {
        UrlWordReader mockReader = mock(UrlWordReader.class);
        String filePath = "./src/test/java/com/erichscompany/test/DictionaryWords.txt";
        File file = new File(filePath);
        List<String> wordList = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
        when(mockReader.getWords()).thenReturn(wordList);        
        
        Dictionary dict = new Dictionary(mockReader);
        ListWordsApp wordApp = new ListWordsApp(dict,wordList);
        String[] words = wordApp.GetWords("");
        assertTrue(words.length == 0);
       
    }
     /**
     * tests a string with a dash, dash should be ignored.
     * Note this test uses the DictionaryWords.txt file for both generating and verifying words
     */
    @Test
    public void nonLettersIgnored() throws IOException
    {
        UrlWordReader mockReader = mock(UrlWordReader.class);
        String filePath = "./src/test/java/com/erichscompany/test/DictionaryWords.txt";
        File file = new File(filePath);
        List<String> wordList = Files.readAllLines( file.toPath(), Charset.defaultCharset() );
        when(mockReader.getWords()).thenReturn(wordList);        
        
        Dictionary dict = new Dictionary(mockReader);
        ListWordsApp wordApp = new ListWordsApp(dict,wordList);
        String[] words = wordApp.GetWords("a-te");
        assertTrue(words.length == 6);
        assertTrue(words[0].equals("a") );
    }
    
}

//a-be