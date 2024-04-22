import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

/**
 * Write a description of babyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyNames {
    public void totalBirths(CSVParser parser){
        int birthCount = 0;
        int boysNamesCount = 0;
        int girlsNamesCount = 0;
        for (CSVRecord currentRow : parser){
            int currentBirths = Integer.parseInt(currentRow.get(2));
            String currentGender = currentRow.get(1);
            
            birthCount += currentBirths;
            
            if (currentGender.contains("F")){
                girlsNamesCount += 1;
            }
            if(currentGender.contains("M")){
                boysNamesCount += 1;
            }
        }
        System.out.println("Total Births: " +  birthCount);
        System.out.println("Girls Names: " + girlsNamesCount);
        System.out.println("Boys Names: " +  boysNamesCount);
    }
    
    public int getRank(int year, String name, String gender){
        CSVParser parser = getParser(year);
        int rank = 0;
        
        for (CSVRecord currentRow : parser){
            String currentGender = currentRow.get(1);
            if (currentGender.equals(gender)){
                rank += 1; 
                String currentName = currentRow.get(0);
                if (currentName.equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender){
        CSVParser parser = getParser(year);
        
        int currentRank = 0;
        for (CSVRecord currentRow : parser){
            String currentGender = currentRow.get(1);
                if (currentGender.equals(gender)){
                    currentRank += 1;
                }
                if (currentRank == rank){
                    return currentRow.get(0);
                }
        }
        return "NAME NOT FOUND";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        String pronoun = null;
        String newName = null;
        int nameRank = 0;
        
        if (gender.equals("F"))
            pronoun = "she";
        if (gender.equals("M"))
            pronoun = "he";
        
        nameRank = getRank(year, name, gender);
        if (nameRank == -1){
            System.out.println("Sorry, name not found in database");
            return;
        }
        newName = getName(newYear, nameRank, gender);
        
        System.out.println(name + " born in " + Integer.toString(year) + 
                           " would be " + newName + " if " + pronoun +
                           " was born in " + Integer.toString(newYear) + ".");
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        int highestRankYear = 0;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            String currentYearS = f.getName().substring(3,7);
            int currentYear = Integer.parseInt(currentYearS);
            
            int currentRank = getRankOfFile(parser, name, gender);
            if (highestRank == -1){
                highestRank = currentRank;
                highestRankYear = currentYear;
            }
            else if (currentRank < highestRank && currentRank != -1){
                highestRank = currentRank;
                highestRankYear = currentYear;                
            }
        }
        return highestRankYear;
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int rankSum = 0;
        int rankCount = 0;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            
            int currentRank = getRankOfFile(parser, name, gender);
            if (currentRank != -1){
                rankSum += currentRank;
                rankCount += 1;
            }
        }
        return ((double)rankSum)/rankCount;
    }
    
    public int getTotalBirthRankedHigher(int year, String name, String gender){
        int rank = getRank(year, name, gender);
        int currentRank = 0;
        int birthsSum = 0;
        
        CSVParser parser = getParser(year);
        
        for (CSVRecord currentRow : parser){
            String currentGender = currentRow.get(1);
            
            if (currentGender.equals(gender)){
                currentRank += 1;
                int births = Integer.parseInt(currentRow.get(2));
                if (currentRank < rank){
                    birthsSum += births;
                }
            }
        }
        return birthsSum;
    }
    
    public int getRankOfFile(CSVParser parser, String name, String gender){
        int rank = 0;
        
        for (CSVRecord currentRow : parser){
            String currentGender = currentRow.get(1);
            if (currentGender.equals(gender)){
                rank += 1; 
                String currentName = currentRow.get(0);
                if (currentName.equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public CSVParser getParser(int year){
        String path = "us_babynames_by_year/yob" + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(path);
        CSVParser parser = fr.getCSVParser(false);
        return parser;
    }
    
    public void testGetTotalBirthRankedHigher(){
        System.out.println(getTotalBirthRankedHigher(1990, "Drew", "M"));
    }
    
    public void testGetAverageRank(){
        System.out.println(getAverageRank("Susan", "F"));
    }
    
    public void testYearOfHighestRank(){
        //System.out.println(yearOfHighestRank("Felipe", "M"));
        //System.out.println(yearOfHighestRank("Noah", "M"));
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    public void testGetRank(){
        System.out.println(getRank(1971, "Frank", "M"));
    }
    
    public void testgetName(){
        System.out.println(getName(1982, 450, "M"));
    }
    
    public void testWhatIsNameInYear(){
        //whatIsNameInYear("Felipe", 2000, 1900, "M");
        //whatIsNameInYear("Victoria", 1999, 1899, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        totalBirths(parser);
    }
}
