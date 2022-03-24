/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wordslang;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import wordslang.queryData;
import java.util.HashMap;

/**
 *
 * @author ITshop
 */
public class WordSlang {

    /**
     * @param args the command line arguments
     */
    
    HashMap<String, String> wordSlang;
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException{
        // TODO code application logic here
    }

    public WordSlang() {
        
    };
    
    public void readFile() throws FileNotFoundException, IOException {
        wordSlang = new HashMap<String, String>();
        //Đọc file
        FileReader fr = new FileReader("slang.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
            
        //Readfile
        //Skip first line
        line = br.readLine();
        while (line!=null)
        {
            String[] obj = line.split("`");
            String word = obj[0];
            String meaning = obj[1];
            
            wordSlang.put(word, meaning);
            
            //Đọc dòng kế tiếp
            line = br.readLine();
        }
        fr.close();          
    }
}
