import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        String testString = "convert det her til binær";
        convertToBinary(testString);

        String testBinString = "1100011 1101111 1101110 1110110 1100101 1110010 1110100 100000 1100100 1100101 1110100 100000 1101000 1100101 1110010 100000 1110100 1101001 1101100 100000 1100010 1101001 1101110 11100110 1110010";

        convertFromBinary(testBinString);
        convertFromBinTxtFile("binary.txt");
    }

    static void convertToBinary(String textToBin) {
        System.out.println("CONVERTED TIL BINARY");
        StringBuilder builder = new StringBuilder();
        char[] chars = textToBin.toCharArray();

        for (char c : chars) {
            builder.append(Integer.toBinaryString(c)).append(" ");
        }
        System.out.println(builder);
    }

    static void convertFromBinary(String stringToBin){
        System.out.println("\nCONVERTED FRA BINARY STRING");
        Stream<String> stringStream = Arrays.stream(stringToBin.split(" "));
        stringStream.forEach(x -> convertEachChar(x));
    }

    static void convertFromBinTxtFile(String dir) {
        System.out.println("\n\nCONVERTED FRA TXT FIL");
        Stream<String> binaryFile = null;

        try {
            binaryFile = Files.lines(Paths.get(dir));
        } catch (IOException e) {
            e.printStackTrace();
        }

        binaryFile
                .flatMap(Pattern.compile(" ")::splitAsStream)
                .forEach(x -> convertEachChar(x));
    }

    static void convertEachChar(String chars) {
        int charCode = Integer.parseInt(chars, 2);
        String str = Character.toString((char) charCode);
        System.out.print(str);
    }

}
