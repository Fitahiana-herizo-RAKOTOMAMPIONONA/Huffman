
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
    
    public static String readTXT(String chemin){
        StringBuilder content = new StringBuilder();
        try (BufferedReader buff = new BufferedReader(new FileReader(chemin)))
        {
            String ligne;
            while ((ligne = buff.readLine()) != null) {
                content.append(ligne);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return content.toString();
    }

    public static String[] prepareData(String data){
        return data.split(" ");
    }
    public static void addToSymbol(){
        HashMap<Character,String> donne = new HashMap<>();
        for (String x : prepareData(readTXT("montexte.txt"))){
            char[] character;
            character = x.toCharArray();
            for (char ch : character){
                if (donne.containsKey(ch)){
                    // donne.put(ch, donne.get()));
                }
            }
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
        // System.out.println(symbList.get(4).getSymbol());
        
        var codes = huffmanMain(symbList);
        exportCSV(codes);
        System.out.println(codes);

        // readTXT("montexte.txt");
    }
        
    
}
