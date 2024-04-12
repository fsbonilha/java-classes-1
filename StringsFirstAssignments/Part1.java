
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    static String STARTCODON = "ATG";
    static String ENDCODON = "TAA";
    
    public String findSimpleGene(String dna){
        int startCodonIndex = dna.indexOf(STARTCODON);
        int endCodonIndex = dna.indexOf(ENDCODON, startCodonIndex+3);
        
        if (startCodonIndex == -1 | endCodonIndex == -1){
            return "";
        }
        
        if (((endCodonIndex) - (startCodonIndex))%3 != 0){
            return "";
        }
        
        return dna.substring(startCodonIndex, endCodonIndex+3);
    }
    
    public void testSimplesGene(){
        String dna1 = "ATGTGAAGTTTAGTATGTTAA";
        System.out.println("DNA1: " + findSimpleGene(dna1));
        
        String dna2 = "TGAGGTAAAATAAAA";
        System.out.println("DNA2: " + findSimpleGene(dna2));
        
        String dna3 = "ATGGGAATTAGTAGTGGTAGT";
        System.out.println("DNA3: " + findSimpleGene(dna3));
        
        String dna4 = "GGGGGAAAATTTTGGGTTAG";
        System.out.println("DNA4: " + findSimpleGene(dna4));
        
        String dna5  = "ATGGTATTTAAATAA";
        System.out.println("DNA5: " + findSimpleGene(dna5));
        
        String dna6  = "ATGGTATAAATAA";
        System.out.println("DNA6: " + findSimpleGene(dna6));
        
        String dna_test = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA Test: " + findSimpleGene(dna_test));
    }
}
