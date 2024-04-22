import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class whichCountriesExport {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        bigExporters(parser, "$999,999,999,999");
        
        //System.out.println(numberOfExporters(parser, "sugar"));
        
        //parser = fr.getCSVParser();
        //System.out.println(numberOfExporters(parser, "cocoa"));
        //listExportersTwoProducts(parser, "fish", "nuts");
        
        //parser = fr.getCSVParser();
        // listExportersTwoProducts(parser, "cotton", "flowers");
        
        //System.out.println(countryInfo(parser, "Germany"));
        
        //parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Peru"));
        
        //parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
    }
    
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord rec : parser){
            //System.out.println(rec.get("Country"));
            if (rec.get("Country").contains(country)){
                return (rec.get("Country") + ": " +
                        rec.get("Exports") + ": " +  
                        rec.get("Value (dollars)"));
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord rec : parser){
            if (rec.get("Exports").contains(exportItem1) && rec.get("Exports").contains(exportItem2)){
                System.out.println(rec.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord rec : parser){
            if (rec.get("Exports").contains(exportItem)){
                count += 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord rec : parser){
            if (rec.get("Value (dollars)").length() > amount.length()){
                System.out.println(rec.get("Country") + " " + rec.get("Value (dollars)"));
            }
        }
    }
}
