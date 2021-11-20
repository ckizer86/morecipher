import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        //read the words from the resource file
        
        for(String word : resource.words()){
            for(int i = 0; i < counts.length; i++){
                int wordlength = word.length();
                if(Character.isLetter(word.charAt(0)) != true && word.length() > 1){
                    int beginning = 1;
                    wordlength = wordlength - beginning;
                }
                
                if(Character.isLetter(word.charAt(word.length()-1)) != true && word.length() > 1){
                    int ending = 1;
                    wordlength = wordlength - ending;
                }
                if(wordlength == i){
                    counts[i] += 1;
                }
            }
            if (word.length() > counts.length){
                counts[counts.length] += 1;
            }
        }
        for(int i = 0; i < counts.length; i++){
            System.out.println("There are " + counts[i] + " words with " + i + " letters ");
        }
        
        
    }
    
    public int indexOfMax(int[] values){
        int maxSoFar = 0;
        int maxindex = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] > maxSoFar){
                maxSoFar = values[i];
                maxindex = i;
            }
        }
        return maxindex;
    }
    
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        int test = indexOfMax(counts);
        System.out.println(test);
    }
    
    public int[] countLetters(String encrypted){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i < encrypted.length(); i++){
            char ch = Character.toLowerCase(encrypted.charAt(i));
            int dex = alph.indexOf(ch);
            if(dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = indexOfMax(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted,26-dkey);
        
    }
    
    public void testdecrypt(){
        String message = "Eatpht ugttot bdgt xrt";
        String test = decrypt(message);
        System.out.println(test);
    }
}
