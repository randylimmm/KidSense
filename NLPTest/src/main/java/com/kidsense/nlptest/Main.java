package main.java.com.kidsense.nlptest;

/**
 *
 * @author Fernando 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    //Model Trainer command
    //opennlp TokenNameFinderTrainer -model TARGET_FILE.bin -lang en -data DATASET.train -encoding UTF-8
    
    public static void main(String[] args) {
        
        Filter filter = Filter.getFilter();
        /*
        //Generic tests  i made
        String[] examples = {"Fernando lives in Irvine and told Angel he sucks.", "Jamie Smith is a god damn bitch",
            "Randy Lim worked at Amazon.", "Josh Hart works in Los Angeles.", "Brandon Holmes is a writer.", "Michael lives in Amsterdam."
        , "John went to McDonalds.", "My name is Kyle", "Hello my name is John", "Morgan works at the bank.", "Hector went to the store to buy some milk"};
        
        
        
        for(String s : examples) {
            System.out.println(filter.filterText(s));
        }*/
        
        
        //Used for tagging text documents in order to help with creating data models
        try {
        ArrayList<String> results = new ArrayList();
        
        //Provide Dataset file path here
        File file = new File("C:\\Users\\ferna\\Desktop\\191A\\datasets\\names.txt");
 
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //Provide target write to file here
        File fileWriter = new File("C:\\Users\\ferna\\Desktop\\191A\\datasets\\en-ner-names.train");
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileWriter));

        String line;
        while((line = reader.readLine()) != null) {
            //Tag each line in the dataset and write to the target file
            writer.write("<START:name> " + line + " <END>\n");
            System.out.println("<START:names> " + line + " <END>");
            //results.add(filter.filterText(line)); //uncomment if you want to filter the line
        }
        
        /* Uncomment to view results of filtering if you uncomment line 58
        for(String s : results) {
            System.out.println(s);
        }*/
 
        writer.close();
        
        }catch(FileNotFoundException e) {
        }catch(IOException e) {       
        }
    }
}
