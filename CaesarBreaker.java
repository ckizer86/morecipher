import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
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
    
    public String halfOfString(String message, int start){
        StringBuilder split = new StringBuilder();
        for(int i =start; i < message.length(); i+=2){
            
                split.append(message.charAt(i));
        }
        return split.toString();
    }
    
    public int getKey(String s){
        int[] counts = countLetters(s);
        for(int i = 0; i < counts.length; i++){
            System.out.println(counts[i]);
        }
        int key = indexOfMax(counts);
        System.out.println("The max index is " + key);
        
        int dkey = key - 4;
        //System.out.println("dkey = key - 4 is " + dkey);
        if(key < 4){
            dkey = 25 - (3-key);
            //System.out.println("dkey = 26 - (4-key)" + dkey);
        }
        
        //System.out.println("The returned dkey is " + dkey);
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted){
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        System.out.println("First key is " + firstKey);
        System.out.println("Second key is " + secondKey);
        CaesarCipher fHalf = new CaesarCipher();
        String d = fHalf.encryptTwoKeys(encrypted, 26-firstKey, 26-secondKey);
        return d;
        
    }
    
    public void testdecryptTwoKeys(){
        FileResource message = new FileResource();
        //String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        String test = decryptTwoKeys(message.asString());
        System.out.println(test);
    }
    
    
}
