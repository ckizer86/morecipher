import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alphabet.substring(key) + alphabet.substring(0,key);
        for(int i = 0; i < input.length(); i++){
            Character ch = input.charAt(i);
            int where = alphabet.indexOf(ch);
            if(where == -1){
                String alphalower = alphabet.toLowerCase();
                where = alphalower.indexOf(ch);
                if(where == -1){
                    encrypted.append(ch);
                }
                else{
                    ch = shifted.charAt(where);
                    encrypted.append(Character.toLowerCase(ch));
                }
            }
            else{
                ch = shifted.charAt(where);
                encrypted.append(ch);
            }
        }
        return encrypted.toString();
    }
    
    public void testencrypted(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Please freeze more ice";
        int key = 15;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            Character letter = input.charAt(i);
            String currentLetter = letter.toString();
            if(i % 2 == 0){
                String first = encrypt(currentLetter, key1);
                encrypted.append(first);
            }
            else{
                String second = encrypt(currentLetter, key2);
                encrypted.append(second);
            }
        }
        return encrypted.toString();
    }
    
    public void testtwoKeys(){
        String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key1 = 8;
        int key2 = 21;
        String test = encryptTwoKeys(input, key1, key2);
        System.out.println(test);
    }
}
