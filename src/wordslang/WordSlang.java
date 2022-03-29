/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wordslang;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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
    
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException{
        // TODO code application logic here
        WordSlang wordSlang = new WordSlang();
        wordSlang.handle();
    }

    public WordSlang() throws IOException {        
        history = new ArrayList<>();
    };
    
    public void readFile(String filePath) throws FileNotFoundException, IOException {
        wordSlang = new HashMap<>();
        //Đọc file
        FileReader fr = new FileReader(filePath);
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
    
       public void saveFile(String path, HashMap<String, String> list) {        
        try {
            FileWriter fw = new FileWriter(path); 
            //Write first line
            fw.write("Slag`Meaning\n");
            //Write file
            list.forEach((key, value) -> {                
                try {
                    fw.write(key+ "`" + value + "\n");
                } catch (IOException ex) {}                
            });
            fw.close();
        } catch (IOException ex) {
            System.out.println("Cannot save file!");
        }
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
    
    public void inCase2() {
        System.out.print("Input definition: ");
        String defi = ip.nextLine();
        wordSlang.forEach((key, value) -> {
            if(value.contains(defi)) {
                System.out.println(key + ": " + value);
            }
        });
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
        System.out.println("Successfully!!!");

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
        System.out.println("Successfully!!!");
    }
    
    
    public void inCase6() {
        //Get slang word to delete
        System.out.print("Input slang word: ");
        String slang = ip.nextLine();
        if(!wordSlang.containsKey(slang)) {
            System.out.println("This slang word is not exist!");
            return;
        }
        
        System.out.println("Are you sure you want to delete:");
        System.out.println(slang + ": " + wordSlang.get(slang));
        System.out.println("(Y/N): ");
        
        String ans;
        ans = ip.nextLine();
        if(ans.toLowerCase().equals("y")) {
            wordSlang.remove(slang);
            System.out.println("Successfully!!!");
        }
        else {
            System.out.println("Cannot delete this slang word!");

        }
    }
    
    public void inCase7() {
        //Get info from file
        try {
            readFile("slangBack-up.txt");
            System.out.println("Succesfully!!!");
        } catch (IOException ex) {
            System.out.println("There is no information");
        } 
    }
    
    public void inCase8() {
        //References: https://stackoverflow.com/questions/929554/is-there-a-way-to-get-the-value-of-a-hashmap-randomly-in-java
        Random generator = new Random();
        Object[] keys = wordSlang.keySet().toArray();
        Object randomKey = keys[generator.nextInt(keys.length)];
        System.out.println(randomKey + ": " + wordSlang.get((String) randomKey));
    }
    
    public void inCase9() {
        //Get random slang word
        Random generator = new Random();
        Object[] keys = wordSlang.keySet().toArray();
        Object randomKey = keys[generator.nextInt(keys.length)];
        //Create array of choices
        int numberOfChoices = 4;
        String[] choices = new String[numberOfChoices];
        int correctChoice = generator.nextInt(numberOfChoices);
        choices[correctChoice] = wordSlang.get((String) randomKey);
        for (int i=0; i<numberOfChoices; i++) {
            String choice = (String) wordSlang.get(keys[generator.nextInt(keys.length)]);
            if(!choice.equals(randomKey) && i!=correctChoice) {
                choices[i] = choice;
            }
        }
        //Print quiz and choices
        System.out.println("What does '" + randomKey + "' mean?");
        System.out.println("A. " + choices[0]);
        System.out.println("B. " + choices[1]);
        System.out.println("C. " + choices[2]);
        System.out.println("D. " + choices[3]);
        
        //Your choice
        String ans = ip.nextLine();
        int yourChoice = -1;
        switch(ans) {
            case "a", "A":
                yourChoice = 0;
                break;
            case "b", "B":
                yourChoice = 1;
                break;
            case "c", "C":
                yourChoice = 2;
                break;
            case "d", "D":
                yourChoice = 3;
                break;
        }
        if(yourChoice == correctChoice) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect!");
        }               
    }   
    
    public void inCase10() {
        //Get random slang word
        Random generator = new Random();
        Object[] keys = wordSlang.keySet().toArray();
        Object randomKey = keys[generator.nextInt(keys.length)];
        //Create array of choices
        int numberOfChoices = 4;
        String[] choices = new String[numberOfChoices];
        int correctChoice = generator.nextInt(numberOfChoices);
        choices[correctChoice] = (String) randomKey;
        for (int i=0; i<numberOfChoices; i++) {
            String choice = (String) keys[generator.nextInt(keys.length)];
            if(!choice.equals(randomKey) && i!=correctChoice) {
                choices[i] = wordSlang.get(choice);
            }
        }
        //Print quiz and choices
        System.out.println("What is meaning '" + wordSlang.get(randomKey) + "'?");
        System.out.println("A. " + choices[0]);
        System.out.println("B. " + choices[1]);
        System.out.println("C. " + choices[2]);
        System.out.println("D. " + choices[3]);
        
        //Your choice
        String ans = ip.nextLine();
        int yourChoice = -1;
        switch(ans) {
            case "a", "A":
                yourChoice = 0;
                break;
            case "b", "B":
                yourChoice = 1;
                break;
            case "c", "C":
                yourChoice = 2;
                break;
            case "d", "D":
                yourChoice = 3;
                break;
        }
        if(yourChoice == correctChoice) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect!");
        }        
    }
    
    public void inCase11() {
        saveFile("slang.txt", wordSlang);
        System.out.println("Successfully!");
    }
        
    public void menu() {
        System.out.println("---MENU---");
	System.out.println("0. Exit");
	System.out.println("1. Search by slang word");
	System.out.println("2. Search by definition");
	System.out.println("3. Show history");
	System.out.println("4. Add new slang word");
	System.out.println("5. Edit slang word");
	System.out.println("6. Delete slang word");
	System.out.println("7. Reset slang word dictionary");
        System.out.println("8. On this day slang word");
        System.out.println("9. Quiz (choose definition of slang word)");       
        System.out.println("10. Quiz (choose slang word of definition)");        
        System.out.println("11. Save file");
	System.out.println("----------");
    }
    
    public void handle(){
        //Print menu
        menu();
        
        //Get info from file
        try {
            readFile("slang.txt");
        } catch (IOException ex) {
            System.out.println("There is no information");
        }
        
        //Choose option
        int choice;
        System.out.print("Choose your option: ");
        Scanner ip = new Scanner(System.in);
        choice = parseInt(ip.nextLine());
        
        while(choice > 0 && choice <= 11) {
            switch(choice) {
                case 1:
                    inCase1();
                    break;
                case 2:
                    inCase2();
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
                    inCase9();
                    break;
                case 10:
                    inCase10();
                    break;
                case 11:
                    inCase11();
            }
            System.out.print("Choose your option: ");
            choice = parseInt(ip.nextLine());
        }
        //SaveFile    
        saveFile("slang.txt", wordSlang);        
    }
}

