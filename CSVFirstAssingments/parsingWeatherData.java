import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

/**
 * Write a description of parsingWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class parsingWeatherData {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord lowestTempRow = null;
        
        for (CSVRecord currentRow : parser){
            if (lowestTempRow == null){
                lowestTempRow = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowestTempRow.get("TemperatureF"));
                
                if(currentTemp < lowestTemp && currentTemp != -9999){
                   lowestTempRow = currentRow; 
                }
            }
        }
        return lowestTempRow;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Temperature: " + coldestHourInFile(parser).get("TemperatureF"));
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestTempRow = null;
        File coldestRowFile = null;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (coldestTempRow == null){
                coldestTempRow = currentRow;
                coldestRowFile = f;
            }
            
            Double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            Double coldestTemp = Double.parseDouble(coldestTempRow.get("TemperatureF"));
            if (currentTemp < coldestTemp){
                coldestTempRow = currentRow;
                coldestRowFile = f;
            }
        }
        System.out.println("Coldest day was in file " + coldestRowFile.getName());
        System.out.println("Coldest temperature on that day was " + coldestTempRow.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were:");
        
        FileResource fr = new FileResource(coldestRowFile);
        for (CSVRecord currentRow : fr.getCSVParser()){
            System.out.println(currentRow.get("DateUTC") + " " + currentRow.get("TemperatureF"));
        }
        
        return coldestRowFile.getName();
    }
    
    public CSVRecord fileWithLowestHumidity(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestTempRow = null;
        File coldestRowFile = null;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (coldestTempRow == null){
                coldestTempRow = currentRow;
                coldestRowFile = f;
            }
            
            Double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
            Double coldestTemp = Double.parseDouble(coldestTempRow.get("Humidity"));
            if (currentTemp < coldestTemp){
                coldestTempRow = currentRow;
                coldestRowFile = f;
            }
        }
        System.out.println("Lowest Humididy day was in file " + coldestRowFile.getName());
        System.out.println("Lowest Humididy on that day was " + coldestTempRow.get("Humidity"));
        // System.out.println("All the Humidities on the coldest day were:");
        return coldestTempRow;
    }
    
    public void testFileWithLowestHumidity(){
        fileWithLowestHumidity();
        
    }
    
    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidityRow = null;
        
        for (CSVRecord currentRow : parser){
            if (lowestHumidityRow == null){
                lowestHumidityRow = currentRow;
            }
            else {
                double currentHum = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHum = Double.parseDouble(lowestHumidityRow.get("Humidity"));
                
                if(currentHum < lowestHum){
                   lowestHumidityRow = currentRow; 
                }
            }
        }
        return lowestHumidityRow;  
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord row = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + row.get("Humidity") + " at " + row.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sumTemperature = 0.0;
        int count = 0;
        for (CSVRecord currentRow : parser){
            double temperature = Double.parseDouble(currentRow.get("TemperatureF"));
            sumTemperature += temperature;
            count += 1;
        }
        return (sumTemperature / count);
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sumTemperature = 0.0;
        int count = 0;
        for (CSVRecord currentRow : parser){
            double temperature = Double.parseDouble(currentRow.get("TemperatureF"));
            int humidity = Integer.parseInt(currentRow.get("Humidity"));
            
            if (humidity >= value){
                sumTemperature += temperature;
                count += 1;
            }
        }
        if (count == 0){
            return -1.0;
        }
        
        return (sumTemperature / count);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        
        if (average == -1.0){
            System.out.println("No temperatures with that Humidity");
            return;
        }
        System.out.println("Average Temp when high Humidity is " + average);
    }
}
