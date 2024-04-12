
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int currentIndex = 0;
        int count = 0;
        
        currentIndex = stringb.indexOf(stringa, currentIndex);
        while (currentIndex != -1){
            count = count + 1;
            currentIndex = stringb.indexOf(stringa, 
                                           currentIndex + stringa.length());
        }
        return count;
    }
    
    public void testHowMany(){
        System.out.println(howMany("AA", "ATAAAA")); //2
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC")); // 3
        System.out.println(howMany("C", "CCCCCCCCCC")); // 10
        System.out.println(howMany("PTA", "PTAAPTAAPTA")); // 3
    }
}
