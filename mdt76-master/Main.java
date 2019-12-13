import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception
    {
        Classifier classifier = new Classifier();
        WordFiles searchWords = new WordFiles();

        String posPath = args[0];
        String negPath = args[1];
        String posRevPath = args[2];
        String negRevPath = args[3];

        File posWord = new File(posPath);
        File negWord = new File(negPath);
        File posRevP = new File(posRevPath);
        File negRevP = new File(negRevPath);

        System.out.println("Path to positive words: "+posPath);


        System.out.println("Path to negative words: "+negPath);


        System.out.println("Path to positive reviews folder: "+posRevPath);

        System.out.println("Path to negative reviews folder: "+negRevPath);

        HashSet<String> pWords = searchWords.wordFiles(posWord);
        HashSet<String> nWords = searchWords.wordFiles(negWord);
        System.out.println(pWords.size() + " positive words  loaded!");
        System.out.println(nWords.size() + " negative words  loaded!\n");


        classifier.ReviewClassifier(posRevP,negRevP,pWords,nWords);
    }
}