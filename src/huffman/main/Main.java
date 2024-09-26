
package huffman.main;

import huffman.tree.Leaf;
import huffman.tree.Node;
import huffman.tree.Tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author ISPM
 */
public class Main {
    public static HashMap<Character,String> huffmanMain(List<Symbol> symbolList){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        //partie 1 - chargement des symboles recensés avec leurs fréquences respectives
        for (Symbol symbol : symbolList) {
            pq.add(new Leaf(symbol.getSymbol(), symbol.getFrequency()));
        }
        
        //partie 2 - création de l'arbre
        while(pq.size()>1){
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Tree(left, right));
        }
        
        
        //partie 3 - création de la table de codes selon l'arbre
        Tree t = (Tree)pq.peek();
        return t.createTable();
    }

    public static void exportCSV(HashMap<Character,String> codes){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("huffman.csv")))
        {
            for(Map.Entry<Character,String> x: codes.entrySet()){
                writer.write(x.toString());
                writer.newLine();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void readTXT(String chemin){
        try (BufferedReader buff = new BufferedReader(new FileReader(chemin)))
        {
            String ligne;
            while ((ligne = buff.readLine()) != null) {
                // ArrayList<String> bibliotheque = new ArrayList<String>();
                String[] bibliotheque;
                bibliotheque =  ligne.split(" ");
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    public static void main(String[] args) {
        var symbList = new ArrayList<Symbol>();
        symbList.add(new Symbol('a',150));
        symbList.add(new Symbol('b',10));
        symbList.add(new Symbol('e',44));
        symbList.add(new Symbol('g',20));
        symbList.add(new Symbol('x',44));
        symbList.add(new Symbol('.',4));
        symbList.add(new Symbol('s',60));
                    
        // var codes = huffmanMain(symbList);
        // exportCSV(codes);
        // System.out.println(codes);

        readTXT("montexte.txt");
    }
        
    
}
