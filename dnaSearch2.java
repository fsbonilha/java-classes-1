
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String upperDna = dna.toUpperCase();
        String upperStartCodon = startCodon.toUpperCase();
        String upperStopCodon = stopCodon.toUpperCase();
        
        int startCodonIndex = upperDna.indexOf(upperStartCodon);
        int endCodonIndex = upperDna.indexOf(upperStopCodon, startCodonIndex+3);
        
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
        System.out.println("DNA1: " + findSimpleGene(dna1, "ATG", "taa"));
        
        String dna2 = "TGAGGTAAAATAAAA";
        System.out.println("DNA2: " + findSimpleGene(dna2, "atg", "taa"));
        
        String dna3 = "ATGGGAATTAGTAGTGGTAGT";
        System.out.println("DNA3: " + findSimpleGene(dna3, "AtG", "Taa"));
        
        String dna4 = "GGGGGAAAATTTTGGGTTAG";
        System.out.println("DNA4: " + findSimpleGene(dna4, "ATG", "TAA"));
        
        String dna5  = "ATGGTATTTAAATAA";
        System.out.println("DNA5: " + findSimpleGene(dna5, "atG", "taa"));
        
        String dna6  = "atggtagaagggtaa";
        System.out.println("DNA6: " + findSimpleGene(dna6, "Atg", "Taa"));
        
        String dna_test = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA Test: " + findSimpleGene(dna_test, "atG", "taA"));
    }
}
