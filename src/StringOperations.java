import java.util.Arrays;
import java.util.Collections;

public class StringOperations {
    final int ALLCHARS = 26;
    public static void main(String[] args) {
        StringOperations stringOperations = new StringOperations();
        String str = "HYDKSKSLSJSFK";
        System.out.println(stringOperations.ascOrderOfString(str));
        System.out.println(stringOperations.descOrderOfString(str));
    }

    public String ascOrderOfString(String str){
        int len = str.length();
        char[] charCount = new char[ALLCHARS];
        for(int i=0; i< len; i++)
            charCount[str.charAt(i) - 'A']++;
        char[] sorted = new char[len];
        int index = 0;
        for(int i=0; i<ALLCHARS; i++){
            for(int j=0; j < charCount[i]; j++)
                sorted[index++] = (char)('A' + i);
        }
        return String.valueOf(sorted);
    }

    public String descOrderOfString(String str){
        int len = str.length();
        char[] charCount = new char[ALLCHARS];
        for(int i=0; i< len; i++)
            charCount[str.charAt(i) - 'A']++;
        char[] sorted = new char[len];
        int index = 0;
        for(int i=ALLCHARS-1; i>=0; i--){
            for(int j=0; j < charCount[i]; j++)
                sorted[index++] = (char)('A' + i);
        }
        return String.valueOf(sorted);
    }
}
