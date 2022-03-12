import java.util.Arrays;
import java.util.Collections;

public class StringOperations {
    final int ALLCHARS = 26;
    public static void main(String[] args) {
        /*StringOperations stringOperations = new StringOperations();
        String str = "HYDKSKSLSJSFK";
        System.out.println(stringOperations.ascOrderOfString(str));
        System.out.println(stringOperations.descOrderOfString(str));*/

        String str = "xabcaax";
        System.out.println(longestPalindromicSubsequence1(str));
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
    public static String longestPalindromicSubstring(String str) {
        int start =-1, end =-1;
        int len = 0;
        int n = str.length();
        for(int i=0; i<n; i++){
            int left = i-1, right = i+1;
            while(left>=0 && right<n && str.charAt(left) == str.charAt(right)){
                left -= 1;
                right += 1;
            }
            if(len < (right - left -1)){
                len = right - left -1;
                start = left+1;
                end = right-1;
            }

            left = i-1; right =i;
            while(left>=0 && right<n && str.charAt(left) == str.charAt(right)){
                left -= 1;
                right += 1;
            }
            if(len < (right - left -1)){
                len = right - left -1;
                start = left+1;
                end = right-1;
            }
        }
        return str.substring(start, end+1);
    }

    public static int longestPalindromicSubsequence(String str) {
        int n= str.length();
        int[][] dp = new int[n][n];

        for(int i=0; i<n; i++) dp[i][i] = 1;

        for(int i=1; i<n; i++){
            int left = 0, right=i;
            while (right < n){
                if(str.charAt(left) == str.charAt(right)) dp[left][right] = dp[left+1][right-1] + 2;
                else dp[left][right] = Math.max(dp[left+1][right], dp[left][right-1]);
                left += 1;
                right += 1;
            }
        }
        return dp[0][n-1];
    }

    public static int longestPalindromicSubsequence1(String str) {
        int n = str.length();
        int[][] cache = new int[n][n];
        int len = longestPalSub(str, 0, n-1, cache);
        return len;
    }
    public static int longestPalSub(String str, int left, int right, int[][] cache){
        if(left>right) return 0;
        if(cache[left][right] != 0) return cache[left][right];
        if(left==right){
            cache[left][right] = 1;
            return cache[left][right];
        }
        if(str.charAt(left) == str.charAt(right))
            return 2 + longestPalSub(str, left+1, right-1, cache);
        else
            return Math.max(longestPalSub(str, left, right-1, cache),longestPalSub(str, left+1, right, cache));

    }

}
