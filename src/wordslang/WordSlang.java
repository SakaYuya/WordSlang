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
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

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
        wordSlang.handle();
    }

    public WordSlang() throws IOException {        
        history = new ArrayList<>();
    };
    
    public void readFile() throws FileNotFoundException, IOException {
        wordSlang = new HashMap<>();
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
        if(wordSlang.containsKey(word)) {            
            return wordSlang.get(word);
        }
        return null;
    }
    
    public void inCase1() {
        System.out.print("Input word slang: ");
        String word = ip.nextLine();
        String ans = searchBySlangWord(word);
        if(ans!=null) {
            System.out.println(ans);
            history.add(word);
        }
        else {
            System.out.println("There is no information of " + word);
        }
    }
    
    public void inCase3() {
        if (history.isEmpty()) {
            System.out.println("There is no history information");
        }
        else {
            System.out.println("Search history:");
            for(String it:history) {
                System.out.println(it);
            }
        }
    }
    
    public void inCase4() {        
        //Input and check slang word
        System.out.print("Input new slang word: ");
        String slang = ip.nextLine();
        if(wordSlang.containsKey(slang)) {
            System.out.println("This slang word is already defined");
            return;
        }
        
        //Input slang word's definition
        System.out.print("Definition of slang word: ");
        String defi = ip.nextLine();
        
        wordSlang.put(slang, defi);
    }
    
    public void inCase5() {
        //Get slang word to edit
        System.out.println("Input slang word: ");
        String slang = ip.nextLine();
        if(!wordSlang.containsKey(slang)) {
            System.out.println("This slang word is not exist!");
            return;
        }
        
        System.out.println(slang + ": " + wordSlang.get(slang));
        System.out.print("Input new definition of " + slang + ": ");        
        String defi = ip.nextLine();
        
        wordSlang.replace(slang, defi);
    }
    
    
    public void inCase6() {
        //Get slang word to delete
        System.out.print("Input slang word: ");
        String slang = ip.nextLine();
        if(!wordSlang.containsKey(slang)) {
            System.out.println("This slang word is not exist!");
            return;
        }
        
        System.out.println("Are you sure you want to delete: (y/n)");
        System.out.println(slang + ": " + wordSlang.get(slang));
        
        String ans;
        ans = ip.nextLine();
        if(ans.toLowerCase().equals("y")) {
            wordSlang.remove(slang);
        }
    }
    

    
    public void menu() {
        System.out.println("---MENU---");
	System.out.println("0. Exit");
	System.out.println("1. Search by slang word");
	System.out.println("2. ");
	System.out.println("3. Show history");
	System.out.println("4. Add new slang word");
	System.out.println("5. Edit slang word");
	System.out.println("6. Delete slang word");
	System.out.println("7. Reset slang word dictionary");
        System.out.println("8. On this day slang word");
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
        int choice;
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
                    inCase3();
                    break;
                case 4:
                    inCase4();
                    break;
                case 5:
                    inCase5();
                    break;
                case 6:
                    inCase6();
                    break;
                case 7:
                    inCase7();
                    break;
                case 8:
                    inCase8();
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

