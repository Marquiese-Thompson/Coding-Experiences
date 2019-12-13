import java.io.*;
import java.util.*;


public class WordFiles {
    public HashSet<String> wordFiles(File word) throws Exception
    {
        Scanner fileContent = new Scanner(word);

        HashSet<String> wordsList = new HashSet<String>();

        while (fileContent.hasNextLine()) {
            String line = fileContent.nextLine();
            if (line.startsWith(";"))
            {
                continue;
            }else if (line.startsWith(" "))
            {
                continue;
            }else
                wordsList.add(line);
        }

        fileContent.close();
        return wordsList;
    }
}


