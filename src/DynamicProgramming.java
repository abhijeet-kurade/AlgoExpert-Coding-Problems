import java.util.*;

public class DynamicProgramming {

    static class Node {
        int val;
        Node next;
    }
    public Node mergerList(int[] listOne, int[] listTwo){



        return null;
    }


    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        // Waiting for the question.

        /*StringBuilder strr = new StringBuilder("AAAAA");
        strr.append('R');
        System.out.println(String.valueOf(strr));
        DynamicProgramming dp = new DynamicProgramming();*/

        //System.out.println(dp.maxSumIncreasingSubsequence(new int[]{-5, -2, 5, 3, 7, 6, -1}));
        //System.out.println(dp.wildcardPatternMatching("abfdggbbab", "*b"));

        /*int[][] arr = new int[][] {{0, 0, -1, -2}, {0, -1, 0, 0}, {0, 0, -2, 0}, {-2, 0, 0, 0}};
        int[][] output = dp.closestDoor(arr);
        int height = output.length;
        int width = output[0].length;

        for (int i = 0; i<height; i++){
            for(int j=0; j<width; j++){
                System.out.print(output[i][j] + " ");
            }
            System.out.println();
        }*/

        /*int[][] items = new int[][] {
                {1, 2},{5,6},{1,9},{4,3},{6,7},{8,5}
        };*/
        //System.out.println(dp.knapsackProblem(items, 10));

        //int[] arr = {-8, -9, -1, -7, -2};
        //System.out.println(dp.maxSubsetSumNoAdjacentAtLeastOne(arr));

        /*
        String str = "(()(()()()(((()())()))))))()";
        String str1 = "ABHIJEETTPS";
        String str2 = "ABCJEETFTP";
        */

        //System.out.println(dp.editingString(str1, str2));
        System.out.println(dp.regExMatching("googler", "g.*o*.g.*"));

        /*int[] arr = {2, 1, 5};
        numberOfWaysToMakeChange(arr, 15);*/

        //System.out.println(minNumberOfJumps(new int[]{1, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3}));


    }

    public boolean regExMatching(String str, String pattern){
        /*
         * Given a input string and pattern implement wildcard match for * where
         *   '*' - any number of precedence character
         *   '.' - any one character
         * */
        int n = str.length();
        int m = pattern.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for(int j=1; j<=m; j++) {
            if(pattern.charAt(j-1) == '*') dp[0][j] = dp[0][j-2];
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(pattern.charAt(j-1) == '.' || str.charAt(i-1) == pattern.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else if (pattern.charAt(j-1) == '*'){
                    /*
                     *  pattern.charAt(j-1) = current pattern character
                     * pattern.charAt(j-2) = last pattern character
                     *  str.charAt(i-1) = current string character                    *
                     * 
                     * dp[i][j] = false;
                     * boolean ans_without_curr_char = dp[i-1][j];
                     * if(dp[i][j-2] == true) dp[i][j] = true;
                     * else if(pattern.charAt(j-2) == '.') dp[i][j] = ans_without_curr_char;
                     * else if(str.charAt(i-1) == pattern.charAt(j-2))) dp[i][j] = ans_without_curr_char;
                     *
                     * */
                    dp[i][j] = dp[i][j-2] ?  true :
                            (pattern.charAt(j-2) == '.' || str.charAt(i-1) == pattern.charAt(j-2)) ? dp[i-1][j] : false;
                }
                else
                    dp[i][j] = false;
            }
        }
        System.out.print("    ");
        for(int j=0; j<m; j++) System.out.print(pattern.charAt(j) + " ");
        System.out.println();
        for(int i=0; i<=n; i++){
            if (i==0) System.out.print("  ");
            else System.out.print(str.charAt(i-1) + " ");
            for(int j=0; j<=m; j++){
                System.out.print(dp[i][j] ? "T " : "F" + " ");
            }
            System.out.println();
        }
        return dp[n][m];
    }

    public static void printArr(int[] arr){
        for(int i=0; i<arr.length; i++) System.out.print(arr[i]+"   ");
        System.out.println();
    }

    public boolean subsetSum(int[] arr, int sum){
        int len = arr.length;
        Arrays.sort(arr);

        boolean dp[][] = new boolean[len+1][sum+1];

        for(int i=0; i<=len; i++)
            dp[i][0] = true;

        for(int i=1; i<len+1; i++){
            for(int j=1; j< sum+1; j++){
                if(j < arr[i-1]) dp[i][j] = dp[i-1][j];
                else if(arr[i-1]==j) dp[i][j] = true;
                else dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]];
            }
        }
        System.out.print("  ");
        for(int i=0; i<sum+1; i++) System.out.print(i + "  ");
        System.out.println();

        for(int i=0; i<len+1; i++){
            if(i==0) System.out.print(0 + "  ");
            else System.out.print(arr[i-1] + "  ");
            for(int j=0; j<sum+1;j++){
                String s = j>9 ?"   ":"  ";
                System.out.print((dp[i][j] ? "T" : "F")+s );
            }
            System.out.println();
        }

        ArrayList<Integer> subset = new ArrayList<>();
        int col = sum;
        for(int row = len; row>0; row--){
            if(dp[row-1][col] == false){
                subset.add(arr[row-1]);
                col = col - arr[row-1];
            }
        }
        System.out.println(subset);
        return dp[len][sum];
    }
    public int maxSubArraySum(int[] arr){
        int len = arr.length;
        int curr_sum = arr[0];
        int max_sum = arr[0];

        for(int i =0; i<len; i++){
            int new_sum = curr_sum + arr[i];
            curr_sum = Math.max(arr[i], new_sum);
            max_sum = Math.max(max_sum, curr_sum);
        }
        return max_sum;
    }
    int longestValidParentheses(String str){
        int len = str.length();
        if(len < 2) return 0;
        int[] dp = new int[len];

        // O(n^2) time || O(n) space

        for(int i=0; i<len; i++){
            char c = str.charAt(i);
            if(c == '(') dp[i] = 0; // non-matching open parentheses = 0
            else{
                dp[i]=-1; // non-matching close parentheses = -1
                for(int j=i-1; j>=0; j--){
                    if(dp[j] == 0){
                        dp[i] = 1; // matching parentheses = 1
                        dp[j] = 1;
                        break;
                    }
                }
            }
        }
        for(int i=0; i<len; i++) System.out.print(str.charAt(i) + "  ");
        System.out.println();
        for(int i : dp) System.out.print(i + "  ");
        System.out.println();
        int longest = 0;
        int current = 0;
        for(int i=0; i<len; i++){
            if(dp[i] == 1) {
                current++;
                longest = Math.max(longest, current);
            }
            else
                current = 0;
        }

        return longest;
    }
    public int lengthOfLongestSequence(int[] arr){
        int len = arr.length;
        HashMap<Integer, Boolean> map = new HashMap<>();
        for(int i : arr)
            map.put(i, true);
        int max_length =0;
        for(int num : arr){
            if(map.get(num)){
                map.put(num, false); //num = 5 left = 4 right 7
                int left = num-1;
                while(map.get(left) != null && map.get(left) == true){
                    map.put(left, false);
                    left -= 1;
                }
                int right = num+1;
                while(map.get(right) != null && map.get(right)){
                    map.put(right, false);
                    right += 1;
                }
                int this_length = right - left - 1;
                max_length = Math.max(max_length, this_length);
            }
        }

        return max_length;
    }
    public int maxSubArrayProduct(int[] arr){

        int len = arr.length;
        int min_product = arr[0];
        int max_product = arr[0];
        int answer = max_product;

        for(int i=1; i<len; i++){

            int first = arr[i];
            int second = arr[i] * min_product;
            int third = arr[i] * max_product;

            min_product = Math.min(Math.min(first, second), third);
            max_product = Math.max(Math.max(first, second), third);

            answer = Math.max(answer, max_product);
        }

        return  answer;
    }
    public int maxSubsetSumNoAdjacent(int[] nums){
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int second_last = 0;
        int last_num = 0;

        for(int i=1; i<nums.length; i++){
            int sum = nums[i] + second_last;
            second_last = last_num;
            last_num = Math.max(second_last, sum);
        }
        return last_num;
    }
    public int maxSubsetSumNoAdjacentAtLeastOne(int[] nums){
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        int second_last = nums[0];
        int last_num = Math.max(nums[0], nums[1]);

        for(int i=2; i<nums.length; i++){
            System.out.println(second_last +" " + last_num);
            int sum = nums[i] + second_last;
            second_last = last_num;
            last_num = Math.max(second_last, Math.max(nums[i], sum));
        }
        System.out.println(second_last +" " + last_num);
        return last_num;
    }
    public int minCoinsChange(int[] coins, int amount){
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for(int coin : coins){
            for(int i=0; i<dp.length; i++){
                if(i>=coin){
                    int temp;
                    if(dp[i-coin] == Integer.MAX_VALUE) temp=dp[i-coin];
                    else temp = dp[i-coin] + 1;
                    dp[i] = Math.min(temp, dp[i]);
                }
                System.out.print(dp[i] + " ");
            }
            System.out.println();
        }
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }
    public static  int numberOfWaysToMakeChange(int[] coins, int amount){
        int[] ways = new int[amount+1];
        ways[0] = 1;
        for(int coin : coins){
            for(int i= coin; i<=amount; i++){
                ways[i] += ways[i-coin];
            }
            printArr(ways);
        }
        return ways[amount];
    }
    public int levenshteinDistance(String str1, String str2) {

        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1+1][len2+1];

        for(int i=0; i<=len2; i++){
            dp[0][i] = i;
        }
        for(int i=0; i<=len1; i++){
            dp[i][0] = i;
        }

        for(int i=1; i<=len1; i++){
            for(int j=1; j<=len2; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
    public ArrayList<String> editingString(String source, String target){
        /*
        * You can only add or remove character and that will consider as one operation
        * */

        ArrayList<String> edits = new ArrayList<>();
        int sourceLen = source.length();
        int targetLen = target.length();
        int[][] dp = new int[targetLen+1][sourceLen+1];


        for(int i=0; i<sourceLen+1; i++)
            dp[0][i] = i;
        for(int i=0; i<targetLen+1; i++)
            dp[i][0] = i;


        for (int i=1; i<=targetLen; i++){
            for(int j=1; j<=sourceLen; j++){
                if(source.charAt(j-1) == target.charAt(i-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
            }
        }


        for(int i=0; i<sourceLen+1; i++){
            if(i==0){
                System.out.print("    ");
            }
            else {
                System.out.print(source.charAt(i-1) + " ");
            }
        }
        System.out.println();

        for (int i=0; i<=targetLen; i++){
            if(i==0){
                System.out.print("  ");
            }
            else
                System.out.print(target.charAt(i-1) + " ");
            for(int j=0; j<=sourceLen; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        int i = targetLen;
        int j = sourceLen;

        while (i>=0 || j>=0){
            if(i==0){
                while (j!=0){
                    edits.add("-"+source.charAt(j-1));
                    j--;
                }
                break;
            }
            if(j==0){
                while (i!=0){
                    edits.add("-"+target.charAt(i-1));
                    i--;
                }
                break;
            }
            //System.out.println(source.charAt(j-1) + " " + target.charAt(i-1) );

            if(source.charAt(j-1) == target.charAt(i-1)){
                edits.add(String.valueOf(source.charAt(j-1)));
                i--;
                j--;
            }
            else {
                if(dp[i-1][j] < dp[i][j-1]){
                    edits.add("+"+target.charAt(i-1));
                    i--;
                }
                else {
                    edits.add("-"+source.charAt(j-1));
                    j--;
                }
            }
        }

        Collections.reverse(edits);
        /*int l = edits.size();
        for (int ii=0; ii<l/2; ii++){
            String temp = edits.get(ii);
            edits.set(ii, edits.get(l-1-ii));
            edits.set(l-1-ii, temp);
        }*/

        return edits;
    }
    public static int minNumberOfJumps(int[] array) {
        int len = array.length;
        if(len <= 1) return 0;
        int jumps=0;
        int nextMaxJump = array[0];
        int index=0;
        while(index < len){
            jumps++;
            int lastMaxJump = nextMaxJump;
            if(nextMaxJump >= len-1 ) break;
            int nextJumps = nextMaxJump - index;
            for(int i=0; i<nextJumps; i++){
                index++;
                nextMaxJump = Math.max(nextMaxJump, array[index]+index);
            }
            if(lastMaxJump == nextMaxJump) return -1;
        }
        return jumps;
    }
    public static int waterArea(int[] heights) {
        int n = heights.length;
        if(n<3) return 0;

        int leftMin= heights[0], rightMax = heights[n-1];
        int left = 1, right = n-2;
        int water = 0;

        while(left <= right){
            int min = Math.min(leftMin, rightMax);
            if(min == leftMin){
                water += leftMin > heights[left] ? leftMin - heights[left] : 0;
                leftMin = Math.max(leftMin, heights[left]);
                left += 1;
            }
            else{
                water += rightMax > heights[right] ? rightMax - heights[right] : 0;
                rightMax = Math.max(rightMax, heights[right]);
                right += -1;
            }
        }
        return water;

    }
    public boolean wildcardPatternMatching(String str, String pattern){
        /*
        * Given a input string and pattern implement wildcard match for * where
        *   '*' - any number of any characters
        *   '?' - any one character
        * */
        int m = str.length();
        int n = pattern.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for(int i =1; i<=n ; i++){
            if(pattern.charAt(i-1) == '*')
                dp[0][i] = dp[0][i-1];
            else
                dp[0][i] = false;
        }
        for(int i=1; i<=m; i++){
              dp[i][0] = false;
        }
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(str.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?')
                    dp[i][j] = dp[i-1][j-1];
                else if(pattern.charAt(j-1) == '*')
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                else
                    dp[i][j] = false;
            }
        }
        return dp[m][n];
    }
    public boolean closestDoorDFSInside(int[][] arr, int i, int j){
        int height = arr.length;
        int width = arr[0].length;
        return 0 <= i && i < height && 0 <= j && j < width;
    }
    public void closestDoorDFS(int[][] arr, int i, int j, int distance){

        if(!closestDoorDFSInside(arr,i,j) || arr[i][j] == -1 ||
                arr[i][j] == -2 || (arr[i][j] <= distance + 1 && arr[i][j] != 0))
            return;

        arr[i][j] = distance + 1;

        closestDoorDFS(arr, i-1, j, distance+1);
        closestDoorDFS(arr, i+1, j, distance+1);
        closestDoorDFS(arr, i, j-1, distance+1);
        closestDoorDFS(arr, i, j+1, distance+1);

    }
    public int[][] closestDoor(int[][] arr){
        int height = arr.length;
        int width = arr[0].length;
        for (int i = 0; i<height; i++){

            for(int j=0; j<width; j++){
                if(arr[i][j] == -2){
                    closestDoorDFS(arr, i-1, j, 0);
                    closestDoorDFS(arr, i+1, j, 0);
                    closestDoorDFS(arr, i, j-1, 0);
                    closestDoorDFS(arr, i, j+1, 0);
                }
            }

        }

        return arr;
    }
    public int getIndexGELIS(ArrayList<Integer> list, int num){
        int index = -1;
        int start = 0;
        int end = list.size()-1;
        while(start<=end){
            int mid = start + (end - start) / 2;
            if(num <= list.get(mid)){
                index = mid;
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        return index;
    }
    public List<Integer> generateLIS(int[] arr, ArrayList<Integer>  prev_indices, int index){
        ArrayList<Integer> longestCommonSubsequence = new ArrayList<>();
        while(index != -1){
            longestCommonSubsequence.add(arr[index]);
            index = prev_indices.get(index);
        }
        Collections.reverse(longestCommonSubsequence);
        return longestCommonSubsequence;
    }
    public List<Integer> longestIncreasingSubsequence(int[] arr){
        //System.out.println("Started");
        int len = arr.length;

        ArrayList<Integer> prev_indices = new ArrayList<>();
        ArrayList<Integer> best_sequence_values = new ArrayList<>();
        ArrayList<Integer> best_sequence_indices = new ArrayList<>();

        best_sequence_values.add(arr[0]);
        best_sequence_indices.add(0);
        prev_indices.add(-1);


        for(int i=1; i<len; i++){
            int index = getIndexGELIS(best_sequence_values, arr[i]);
            if(index == -1){
                int best_seq_last_index = best_sequence_indices.get(best_sequence_indices.size()-1);
                prev_indices.add(best_seq_last_index);
                best_sequence_values.add(arr[i]);
                best_sequence_indices.add(i);
            }
            else if(best_sequence_values.get(index) >= arr[i]){
                if(index == 0) prev_indices.add(-1);
                else prev_indices.add(best_sequence_indices.get(index-1));
                best_sequence_values.set(index, arr[i]);
                best_sequence_indices.set(index, i);
            }
            //System.out.println(arr[i] + " "+index + " " +best_sequence_values + " " +best_sequence_indices + " "+prev_indices);
        }
        return generateLIS(arr, prev_indices, best_sequence_indices.get(best_sequence_indices.size()-1));
    }
    public List<Character> longestCommonSubsequence(String str1, String str2){
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n+1][m+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        ArrayList<Character> common_sequence = new ArrayList<>();

        int row = n;
        int col = m;

        while(row != 0 || col != 0){
            if(str1.charAt(row-1) == str2.charAt(col-1)) {
                common_sequence.add(str1.charAt(row-1));
                row--;
                col--;
            }
            else{
                if(dp[row-1][col] > dp[row][col-1]){
                    row--;
                }
                else{
                    col--;
                }
            }
        }
        Collections.reverse(common_sequence);
        return common_sequence;
    }
    public List<List<Integer>> maxSumIncreasingSubsequence(int[] arr) {

        int len = arr.length;
        int[] prev_index = new int[len];
        int[] max_sum = new int[len];

        max_sum[0] = arr[0];
        prev_index[0] = -1;
        int maximum_sum = arr[0];
        int maximum_sum_index = 0;

        for(int i=1; i<len; i++){
            int num = arr[i];
            if(num <= 0){
                prev_index[i] = -1;
                max_sum[i] = arr[i];
                if(maximum_sum < max_sum[i]){
                    maximum_sum = max_sum[i];
                    maximum_sum_index = i;
                }
                continue;
            }

            int greatest_sum = Integer.MIN_VALUE;
            int greatest_sum_idx = -1 ;
            for(int j=0; j<i; j++){
                if(arr[j] < num && arr[j] > 0){
                    if(max_sum[j] > greatest_sum){
                        greatest_sum = max_sum[j];
                        greatest_sum_idx = j;
                    }
                }
            }

            if(greatest_sum_idx != -1){
                max_sum[i] = max_sum[greatest_sum_idx] + arr[i];
                prev_index[i] = greatest_sum_idx;
            }
            else{
                max_sum[i] = arr[i];
                prev_index[i] = -1;
            }
            if(maximum_sum < max_sum[i]){
                maximum_sum = Math.max(maximum_sum, max_sum[i]);
                maximum_sum_index = i;
            }
        }

        ArrayList<Integer> mx_sm = new ArrayList<>();
        mx_sm.add(maximum_sum);
        int index = maximum_sum_index;
        ArrayList<Integer> mx_sm_val = new ArrayList<>();
        while(index != -1){
            mx_sm_val.add(arr[index]);
            index = prev_index[index];
        }
        Collections.reverse(mx_sm_val);
        return new ArrayList<List<Integer>>() {
            {
                add((ArrayList)mx_sm.clone()); // Example max sum
                add((ArrayList)mx_sm_val.clone()); // Example max sequence
            }
        };
    }
    public List<List<Integer>> knapsackProblem(int[][] items, int capacity) {
        int len = items.length;
        int[][] dp = new int[len+1][capacity+1];

        for(int i=0; i<=len; i++) dp[i][0] = 0;
        for(int i=0; i<=capacity; i++) dp[0][i] = 0;

        for(int i=1; i<=len; i++){
            for(int j=1; j<=capacity; j++){
                if(j < items[i-1][1]) dp[i][j] = dp[i-1][j];
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - items[i-1][1]] + items[i-1][0]);
                }
            }
        }

        for(int i=0; i<=len; i++){
            for(int j=0; j<=capacity; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        int max_value = dp[len][capacity];
        ArrayList<Integer> max_val = new ArrayList<>();
        max_val.add(max_value);

        ArrayList<Integer> bagItems = new ArrayList<>();
        int item = len;
        int index = capacity;
        while(item >=0){
            if(index > 0 && item > 0 && dp[item][index] != dp[item-1][index]){
                bagItems.add(item-1);
                index = index - items[item-1][1];
            }
            item--;
        }
        Collections.reverse(bagItems);

        List<List<Integer>> bag = new ArrayList<>();
        bag.add(max_val);
        bag.add(bagItems);

        return bag;
    }


}
