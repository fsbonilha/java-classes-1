import edu.duke.*;

/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public void readURL() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : ur.words()){
            int wordIndex = word.toLowerCase().indexOf("youtube.com");
            if (wordIndex != -1){
                int startOfURL = word.lastIndexOf("\"", wordIndex);
                int endOfURL = word.indexOf("\"", startOfURL+1);
                System.out.println(word.substring(startOfURL+1, endOfURL));
            }
        }
    }
    public void main(){
        readURL();
    }
}

