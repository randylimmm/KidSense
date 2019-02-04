package main.java.com.kidsense.nlptest;

/**
 *
 * @author Fernando 
 */

public class Main {
    
    public static void main(String[] args) {
        
        String[] examples = {"Fernando Perez lives in Irvine.", "Jamie Smith is a clerk and works in Los Angeles",
            "Randy Lim worked at Amazon.", "Josh Hart works in Los Angeles." };
        
        Filter filter = Filter.getFilter();
        
        for(String s : examples) {
            System.out.println(filter.filterText(s));
        }        
    }
}
