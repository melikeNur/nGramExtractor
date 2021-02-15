/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ngramextractor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;
import java.util.Comparator;

/**
 *
 * @author melikenur
 * @since 3.01.2021
 */
public class NgramExtractor {
public static void main(String[] args) throws FileNotFoundException, IOException {
        String M =" ";
       ArrayList<String> t = new ArrayList<>();      
       Map<String,Integer> wordMap = new HashMap<>( ); 
        File file = new File(args[0]);
        FileReader fileReader = new FileReader(file);
        String st;
        BufferedReader br = new BufferedReader(fileReader); 
        st = br.readLine(); 
        String s = " ";
        while (st != null) {
            s += st + " ";
            st = br.readLine();
        }
        String[] list = s.split(" ");
        int n = list.length;
         String[] array = new String[n];
        int gram = Integer.parseInt(args[2]); 
        for(int i =0;i<n;i++){  
           array[i] = M;
            //System.out.println(Arrays.toString(array));
            M="";
            t.removeAll(t);
          for(int m=i;m<n;m++){
              t.add(list[m]);
              M = M +" "+ list[m];
            // System.out.println(M);
              if(t.size()==gram){
                  m=n;
                   
              }
            }
          
         String word = array[i].toLowerCase( );
         Integer count = wordMap.get(word); 
         if (count == null)
         count = 0; 
        wordMap.put(word, 1 + count); 
           
        
        }  
        br.close();
        File output = new File(args[1]);
        FileOutputStream os = new FileOutputStream(output);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

        double size=wordMap.size() ;
        bw.write("\n" + "Number of tokens is "  + (int)size+" "+"\n");
        bw.write("\n" + " Ngram  " + "\t"+ "Count  " +"\t" +"Frequency  ");
        int Count = 0;
        String Word = "no word";
        List<Entry<String ,Integer>> wordsByCount = new ArrayList<>(wordMap.entrySet());
        wordsByCount.sort(Entry.comparingByValue((Comparator.reverseOrder())));
        Map<String,Integer> reverseSortedMap = new LinkedHashMap<>( );
          for(Entry<String, Integer> x : wordsByCount ) {
            reverseSortedMap.put(x.getKey(), x.getValue());
        }
     
        //System.out.println(reverseSortedMap);
         for (Entry<String,Integer> ent : reverseSortedMap.entrySet( )){
           Word = ent.getKey( );
           Count = ent.getValue( );
          
         bw.write("\n" +Word+"      "+"\t"+Count+"      " + 100*Count/size);
   }
  bw.close();
   }
 
       
}



