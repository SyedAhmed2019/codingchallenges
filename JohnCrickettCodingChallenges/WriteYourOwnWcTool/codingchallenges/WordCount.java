
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {

    public static void main(String[] args) {
        WordCount wc = new WordCount();
        if (args.length < 2) {
            System.out.println("WordCount -<switch> <filename.txt>");
        } else {
            String arg1 = args[0];
            String fileName = args[1];
            switch (arg1.toLowerCase()) {
                case "-c":
                    System.out.println("character count");
                    System.out.println(wc.characterCount(fileName) + " " + fileName);
                    break;
                case "-w":
                    System.out.println("word count");
                    System.out.println(wc.wordCount(fileName) + " " + fileName);
                    break;
                case "-l":
                    System.out.println("line count");
                    System.out.println(wc.lineCount(fileName) + " " + fileName);
                    break;
                case "-m":
                    System.out.println("byte count");
                    System.out.println(wc.byteCount(fileName) + " " + fileName);
                    break;
                default:
                    System.out.println("usage: WordCount -c|-w|-l|-m <filename.txt>");
            }
        }
    }

    private long lineCount(String fileName) {
        int lineCount = -1;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineCount;
    }

    private long byteCount(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            return fis.readAllBytes().length;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private long wordCount(String fileName) {
        int wordCount = 0;
        String currentLine;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((currentLine = br.readLine()) != null) {
                String[] splitted = currentLine.trim().split("\\s+");
                if ((splitted.length >= 1 && !splitted[0].isEmpty())) {
                    wordCount += splitted.length;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return wordCount;
    }

    private long characterCount(String fileName) {
        int count = 0;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            while (fis.read() != -1) {
                ++count;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
