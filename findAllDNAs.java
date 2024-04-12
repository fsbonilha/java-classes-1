
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        while (currIndex != -1){
            if ((currIndex - startIndex) % 3 == 0){
                return currIndex;
            }
            
            else{
            currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int startIndex){
        int currIndex = dna.indexOf("ATG", startIndex);
        if (currIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, currIndex+3, "TAA");
        int tagIndex = findStopCodon(dna, currIndex+3, "TAG");
        int tgaIndex = findStopCodon(dna, currIndex+3, "TGA");
        
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        
        if (minIndex == dna.length()){
            return "";
        }
        return dna.substring(currIndex, minIndex+3);
        
    }
    
    public void testFindStopCodon(){
        String dna = "ATGXXXAAAXXXKTAAZZTAA";
        int test = findStopCodon(dna, 0, "TAA"); // 18
        System.out.println(test + "// len: " + dna.length());

        dna = "ATGXTAAZTAAXTAA";
        test = findStopCodon(dna, 0, "TAA"); // 12
        System.out.println(test + "// len: " + dna.length());
        
        //        v  v  v  v  v
        dna = "ATGFXXXAAAXXXTAA";
        test = findStopCodon(dna, 0, "TAA"); // len 
        System.out.println(test + "// len: " + dna.length());
        //        v  v  v  v  
        dna = "ATGXXXAAAXXXTAG";
        test = findStopCodon(dna, 0, "TAG"); // 12
        System.out.println(test + "// len: " + dna.length());
        //        v
        dna = "ATGTAG";
        test = findStopCodon(dna, 0, "TAG"); // 3
        System.out.println(test + "// len: " + dna.length());
        
        dna = "ATGXTAG";
        test = findStopCodon(dna, 0, "TAG"); // len
        System.out.println(test + "// len: " + dna.length());
        
        dna = "ATGXYZTAG";
        test = findStopCodon(dna, 0, "TAG"); // 6
        System.out.println(test + "// len: " + dna.length());
        //        v  v  v  v  v
        dna = "XXXXATGXXXYYYKKKTAG"; // len
        test = findStopCodon(dna, 0, "TAG");
        System.out.println(test + "// len: " + dna.length());
    }
    
    public void testFindGene(){
        String dna = "AAAAATGAAAGGGTAAAAAAA";
        String gene = findGene(dna,0);
        System.out.println(dna + " Gene encontrado: " + gene);
        //        v  v  v  v  v  v
        dna = "BBBBATGTTTTAATAATATAA"; 
        gene = findGene(dna,0);
        System.out.println(dna + " Gene encontrado: " + gene);
        
        dna = "BBBBATTAAAGGGTAATAA"; // sem gene
        gene = findGene(dna,0);
        System.out.println(dna + " Gene encontrado: " + gene);
        //             v  v  v  v  v  v  v  v
        dna = "TGTTTTGTATGAAAGGGATAATAATAATAATAA";
        gene = findGene(dna,0);
        System.out.println(dna + " Gene encontrado: " + gene);
        //        v  v  v  v  v  v
        dna = "TTTATGATGATGATGAAATAA";
        gene = findGene(dna,0);
        System.out.println(dna + " Gene encontrado: " + gene);
    }
    
    public void printAllGenes(String dna){
        int currIndex = 0;
        while (true){
            String gene = findGene(dna, currIndex);
            if (gene.isEmpty()){
                break;
            }
            System.out.println(gene);
            currIndex = dna.indexOf(gene, currIndex) + gene.length();
        }
    }
    
    public void main(){
        String dna = "ATGAAAGGGAAATAAGATTTTAAAGGGATGTTTTAAAAATTTTATGTAAGGGGGATGTTTXTTTTAA";
        System.out.println(dna);
        printAllGenes(dna);
    }
}
