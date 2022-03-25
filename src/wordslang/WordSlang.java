/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wordslang;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.ArrayList;
import wordslang.queryData;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ITshop
 */
public class WordSlang {

    /**
     * @param args the command line arguments
     */
    
    HashMap<String, String> wordSlang;
    ArrayList<String> history;
    Scanner ip = new Scanner(System.in);
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
        // TODO code application logic here
        WordSlang wordSlang = new WordSlang();
    }

    public WordSlang() throws IOException {
        handle();
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
    
    public String searchBySlangWord(String word) {
        return wordSlang.get(word);
    }
    
    public void saveHistory(String word) {
        history.add(word);
    }
    
    public void inCase1() {
        System.out.print("Input word slang: ");
        String word = ip.nextLine();
        System.out.println(searchBySlangWord(word));
    }
    
    public void menu() {
        System.out.println("---MENU---");
	System.out.println("0. Exit");
	System.out.println("1. Search by slang word");
	System.out.println("2. ");
	System.out.println("3. ");
	System.out.println("4. ");
	System.out.println("5. ");
	System.out.println("6. ");
	System.out.println("7. ");
        System.out.println("8. ");
        System.out.println("9. ");
	System.out.println("----------");
    }
    
    public void handle(){
        //Print menu
        menu();
        
        //Get info from file
        try {
            readFile();
        } catch (IOException ex) {
            System.out.println("There is no information");
        }        
        
        
        //Choose option
        int choice = 0;
        System.out.print("Choose your option: ");
        Scanner ip = new Scanner(System.in);
        choice = parseInt(ip.nextLine());
        
        while(choice > 0 && choice <= 9) {
            switch(choice) {
                case 1:
                    inCase1();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
            }
            System.out.print("Choose your option: ");
            choice = parseInt(ip.nextLine());
        }
        //Free memory        
    }
}

