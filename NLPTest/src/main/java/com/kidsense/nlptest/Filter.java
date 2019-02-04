package main.java.com.kidsense.nlptest;

/**
 *
 * @author fernando
 */

import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.namefind.NameFinderME;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Filter {

    private static final String PERSON_MODEL_PATH = "C:\\OpenNLP_models\\en-ner-person.bin";
    
    private static final String LOCATION_MODEL_PATH = "C:\\OpenNLP_models\\en-ner-location.bin";
    
    private static Filter filter;
    
    private static NameFinderME nameModel;
    
    private static NameFinderME locationModel;
    
    private Filter() {}
    
    public static Filter getFilter() {
        if(filter == null) {
            filter = new Filter();
            generateModels();
        }
        
        return filter;
    }
    
    public String filterText(String text) {
        String[] tokens = createTokens(text);
        
        String censored;
        
        censored = genericEntityFilter(text, tokens, nameModel);
        censored = genericEntityFilter(censored, tokens, locationModel);
        
        return censored;
    }
    
    //Generate streams on construction so not continuously re-generated when calling filtertext
    private static void generateModels() {
        nameModel = genericFinderModel(PERSON_MODEL_PATH);
        locationModel = genericFinderModel(LOCATION_MODEL_PATH);
    }
    
    //Create file input stream for given model path
    private static InputStream createStream(String path) {
        InputStream stream = null;
        try {
            stream = new FileInputStream(path);
            
            return stream;
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return stream;
    }
    
    //Create token array using token model
    private static String[] createTokens(String text) {
        String[] tokens;
        
        WhitespaceTokenizer model = WhitespaceTokenizer.INSTANCE;

        tokens = model.tokenize(text);

        return tokens;
    }    
    
    //Creates a NameFinder model for a given modelpath
    private static NameFinderME genericFinderModel(String modelPath) {
        NameFinderME finder = null;
        try {
            TokenNameFinderModel model = new TokenNameFinderModel(createStream(modelPath));
            
            return new NameFinderME(model);
            
        }catch(IOException e) {
            e.printStackTrace();
        }
        return finder;
    }
   
    //Filters entities based on a supplied finder model
    private static String genericEntityFilter(String text, String[] tokens, NameFinderME finder) { 
        
        Span[] nameSpans = finder.find(tokens); 
       
        String[] spans = Span.spansToStrings(nameSpans, tokens);
        
        String censored = new String(text);
        for(int i = 0; i < spans.length; i++) {
            
            censored = censored.replace(spans[i], "*********");
        } 
        
        return censored;
    }
    
    
}
