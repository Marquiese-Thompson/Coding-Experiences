import java.io.*;
import java.util.*;

public class Classifier {


    /**
     * constructor
     * @param posRevPath path to file containing positive words
     * @param negRevPath path to file containing negative words
     * @param pWords Hash set of positive search words
     * @param nWords Hash set of negative search words
     */
    public void ReviewClassifier(File posRevPath, File negRevPath, HashSet<String> pWords, HashSet<String> nWords) throws FileNotFoundException {
        Precision precision = new Precision();
        int negRevCount = 0;
        int posRevCount = 0;



        String[] files = posRevPath.list();
        String fileSeparatorChar = System.getProperty("file.separator");

        for (String fName : files)
        {
            int negWordsCount = 0;
            int posWordsCount = 0;

            if (fName.endsWith(".txt"));
            String name = posRevPath + fileSeparatorChar + fName;
            System.out.println("File Name : "+name);
            Scanner inFile = new Scanner(new FileReader(name));
            String context = "";

            while (inFile.hasNextLine())
            {
                context += inFile.nextLine();
            }
            context = context.replaceAll("<br />","");
            context = context.replaceAll("\\p{Punct}"," ");
            context = context.toLowerCase();

            String[] words = context.split("\\s+");

            for(int j = 0; j<words.length; j++)
            {
                if(nWords.contains(words[j]))
                {
                    negWordsCount++;
                }
                else if(pWords.contains(words[j]))
                {

                    posWordsCount++;
                }
            }

            System.out.println("Real Class : Positive");
            if(negWordsCount >= posWordsCount)
            {
                System.out.println("Predicted Class : Negative\n");
                negRevCount++;
            }
            else
            {
                System.out.println("Predicted Class : Positive\n");
                posRevCount++;
            }
        }

        int negRevCount2 = 0;
        int posRevCount2 = 0;

        files = negRevPath.list();
        fileSeparatorChar = System.getProperty("file.separator");

        for (int i=0; i<files.length; i++)
        {

            int trueWords = 0;
            int falseWords = 0;

            if (files[i].endsWith(".txt"));

            String name = negRevPath + fileSeparatorChar + files[i];
            System.out.println("File Name : "+name);
            Scanner inFile = new Scanner(new FileReader(name));
            String context = "";

                while (inFile.hasNextLine())
                {
                    context += inFile.nextLine();
                }
            context = context.replaceAll("<br />","");
            context = context.replaceAll("\\p{Punct}"," ");
            context = context.toLowerCase();

            String[] words = context.split("\\s+");

            for(int j = 0; j< words.length; j++)
            {
                if(pWords.contains(words[j]))
                {
                    trueWords++;
                }
                else if(nWords.contains(words[j]))
                {
                    falseWords++;
                }
            }

            System.out.println("Real Class : Negative");
            if(trueWords > falseWords)
            {
                System.out.println("Predicted Class : Positive\n");
                negRevCount2++;
            }
            else if(trueWords <= falseWords)
            {
                System.out.println("Predicted Class : Negative\n");
                posRevCount2++;
            }
        }

        System.out.println("Final Result..\n");
        precision.accurate(posRevCount, negRevCount, "Positive");
        precision.accurate(posRevCount2,negRevCount2,"Negative");
        precision.accurate(((posRevCount2+posRevCount)),((negRevCount2+negRevCount)),"Total");


    }

}
