import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AlgoExpert {

    public static void main(String[] args) {

    }

}

class AlgoExpertArrays{

    public int[] twoNumberSum(int[] array, int target) {
        Set<Integer> set = new HashSet<>();
        for(int num : array){
            if(set.contains(target - num)) return new int[]{num, target-num};
            else set.add(num);
        }
        return new int[]{};
    }

    public boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        int len = sequence.size();
        int index =0;
        for(int num : array){
            if(num == sequence.get(index)){
                index += 1;
                if(index == len) return true;
            }
        }
        return false;
    }

    public int[] sortedSquaredArray(int[] array) {
        int len = array.length;
        int start = 0;
        int end = len-1;
        int[] squaredArray = new int[len];
        int index = len-1;

        while (start <= end){
            if(Math.abs(array[start]) > Math.abs(array[end])){
                squaredArray[index--] =  array[start] * array[start];
                start += 1;
            }
            else{
                squaredArray[index--] =  array[end] * array[end];
                end -= 1;
            }
        }
        return squaredArray;
    }

    public String tournamentWinner(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
        HashMap<String, Integer> score = new HashMap<>();
        int maxScore = 0;
        String winner = "";
        int index=0;
        for(ArrayList<String> game : competitions){
            int win = results.get(index) == 0 ? 1 : 0;
            if(score.get(game.get(win)) != null){
                int scr = score.get(game.get(win))+1;

                System.out.println(game.get(win) +" "+ scr);
                score.put(game.get(win), scr);
                if(maxScore < scr){
                    maxScore = scr;
                    winner = game.get(win);
                }
            }
            else{
                score.put(game.get(win), 1);
                if(maxScore <= 1){
                    maxScore = 1;
                    winner = game.get(win);
                }
            }
            index++;
        }
        return winner;
    }

    public int nonConstructableChange(int[] coins) {
        Arrays.sort(coins);
        int change = 0;
        for(int coin : coins){
            if(coin <= change + 1) change += coin;
            else return change+1;
        }
        return change+1;
    }

    public List<Integer[]> threeNumberSum(int[] array, int target) {
        Arrays.sort(array);
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer[]> numbers = new ArrayList<>();
        int len = array.length;
        for(int i=0; i<len; i++) map.put(array[i], i);

        for(int i=0; i<len-1; i++){
            for(int j=i+1; j<len; j++){
                int req = target - array[i] - array[j];
                if(map.get(req) != null && map.get(req) > j)
                    numbers.add(new Integer[]{array[i], array[j], req});
            }
        }
        return numbers;
    }

    public int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        int lenOne = arrayOne.length;
        int lenTwo = arrayTwo.length;
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);

        int indexOne = 0;
        int indexTwo = 0;

        int smallestDiff = Integer.MAX_VALUE;
        int[] smallestDiffPair = new int[2];

        while(indexOne < lenOne || indexTwo < lenTwo){
            int diff = Math.abs(arrayOne[indexOne] - arrayTwo[indexTwo]);
            if(diff < smallestDiff){
                smallestDiff = diff;
                smallestDiffPair[0] = arrayOne[indexOne];
                smallestDiffPair[1] = arrayTwo[indexTwo];
            }
            if(arrayOne[indexOne] < arrayTwo[indexTwo]){
                indexOne += 1;
                if(indexOne == lenOne) break;
            }
            else {
                indexTwo += 1;
                if(indexTwo == lenTwo) break;
            }
        }
        return smallestDiffPair;
    }

    public List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        int len = array.size();

        int indToMove = -1;
        int indNonToMove = -1;

        for(int i=0; i<len; i++){
            if(array.get(i) == toMove) {
                indToMove = i;
                break;
            }
        }

        if(indToMove == -1 || indToMove == len-1) return array;

        for(int i=indToMove+1; i<len; i++){
            if(array.get(i) != toMove) {
                indNonToMove = i;
                break;
            }
        }

        if(indNonToMove == -1 ) return array;

        while (indNonToMove < len && indToMove < len){
            Collections.swap(array, indToMove, indNonToMove);
            indToMove = indToMove + 1;
            for(int i=indToMove; i<len; i++){
                if(array.get(i) != toMove){
                    indNonToMove = i;
                    break;
                }
            }
        }

        return array;
    }

    public static boolean isMonotonic(int[] array) {
        char o = 'N';
        int len = array.length;

        for(int i=0; i<len-1; i++){
            if(array[i]<array[i+1]){
                o = 'I';
                break;
            }
            else if(array[i]>array[i+1]){
                o='D';
                break;
            }
        }
        if(o == 'N'){
            return true;
        }

        for(int i=0; i<len-1; i++){
            if(o=='I'){
                if(array[i]>array[i+1])
                    return false;
                continue;
            }
            else{
                if(array[i]<array[i+1])
                    return false;
                continue;
            }
        }
        return true;
    }

    public static List<Integer> spiralTraverse(int[][] array) {
        int height = array.length;
        int width = array[0].length;

        List<Integer> spiral = new ArrayList<>();

        int rowStart = 0;
        int rowEnd = height-1;
        int colStart = 0;
        int colEnd = width-1;

        while(rowStart<=rowEnd && colStart <= colEnd){
            // top row
            for(int i=colStart; i<=colEnd; i++) spiral.add(array[rowStart][i]);
            //last col
            for(int i=rowStart+1; i<=rowEnd; i++) spiral.add(array[i][colEnd]);
            //last row
            for(int i=colEnd-1; i>=colStart; i--){
                if(rowStart==rowEnd) break;
                spiral.add(array[rowEnd][i]);
            }
            //first col
            for(int i=rowEnd-1; i>=rowStart+1; i--) {
                if(colStart==colEnd) break;
                spiral.add(array[i][colStart]);
            }

            rowStart += 1;
            rowEnd -= 1;
            colStart += 1;
            colEnd -= 1;
        }

        return spiral;
    }

    public int longestPeak(int[] array) {
        int len = array.length;
        int index=0;
        int maxPeak=0;

        while(index<len){
            boolean isPeak = false;
            if(index == 0 || index == array.length -1)
                isPeak = false;
            else
                isPeak = (array[index-1] < array[index]) && (array[index] > array[index+1]);
            if(isPeak){
                int peak = index;
                int currentPeakStart= index-1;
                while(currentPeakStart >=0 && array[index]>array[currentPeakStart]){
                    currentPeakStart--;
                    index--;
                }
                index = peak;
                int currentPeakEnd = index+1;
                while(currentPeakEnd < len && array[index]>array[currentPeakEnd]){
                    currentPeakEnd++;
                    index++;
                }
                maxPeak = Math.max(maxPeak, index - currentPeakStart);
            }
            index++;
        }
        return maxPeak;
    }

    public int[] arrayOfProducts(int[] array) {
        int len = array.length;

        int[] post = new int[len];
        post[len-1] = 1;
        for(int i=len-2; i>=0; i--) post[i] = post[i+1] * array[i+1];

        int pre = 1;
        for(int i=0; i<len; i++) {
            post[i] = pre * post[i];
            pre *= array[i];
        }
        return post;
    }

    public int firstDuplicateValue(int[] array) {
        int len = array.length;

        for(int i=0; i<len; i++ ){
            int ind = Math.abs(array[i]) - 1;
            if(array[ind] > 0)
                array[ind] *= -1;
            else
                return ind+1;
        }
        return -1;
    }

    public int[][] mergeOverlappingIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int len = intervals.length;
        int curr_start = intervals[0][0];
        int curr_end = intervals[0][1];
        List<int[]> merged = new ArrayList<>();
        for(int i=1; i<len; i++){
            int[] interval = intervals[i];
            if(curr_end - interval[0] >= 0 ){
                curr_end = Math.max(curr_end, interval[1]);
            }
            else{
                merged.add(new int[]{curr_start, curr_end});
                curr_start = interval[0];
                curr_end = interval[1];
            }
        }
        merged.add(new int[]{curr_start, curr_end});

        int s = merged.size();
        int[][] mrged = new int[s][2];
        for(int i=0; i<s; i++) mrged[i] = merged.get(i);
        return mrged;

    }

    public List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        Arrays.sort(array);
        Map<Integer, List<int[]>> map = new HashMap<>();
        List<Integer[]> fourNumbers = new ArrayList<>();

        int len = array.length;
        if(len <= 3 ) return fourNumbers;

        for(int i=0; i<len-1; i++){
            for(int j=i+1; j<len;j++){
                int sum = array[i] + array[j];
                if (map.get(sum) == null) {
                    map.put(sum, new ArrayList<>());
                }
                map.get(sum).add(new int[]{array[i], array[j]});
            }
        }

        for(int i=0; i<len-1; i++){
            for(int j=i+1; j<len;j++){
                int tgt = targetSum - array[i] - array[j];
                if(map.get(tgt) != null){
                    for(int[] pair : map.get(tgt)){
                        if(pair[0] > array[j])
                            fourNumbers.add(new Integer[]{array[i], array[j], pair[0], pair[1]});
                    }
                }
            }
        }
        return fourNumbers;
    }

    public int[] subArraySort(int[] array) {

        int len = array.length;
        int unordered_min = Integer.MIN_VALUE;
        int unordered_max = Integer.MAX_VALUE;

        boolean num_found = false;
        for(int i=1; i<len; i++){
            if(!num_found && array[i-1] > array[i]){
                unordered_min = array[i];
                num_found = true;
                continue;
            }
            unordered_min = Math.min(unordered_min, array[i]);

        }
        if(unordered_min == Integer.MIN_VALUE) return new int[] {-1, -1};

        num_found = false;
        for(int i=len-2; i>=0; i--){
            if(!num_found && array[i] > array[i+1]){
                unordered_max = array[i];
                num_found = true;
                continue;
            }
            unordered_max = Math.max(unordered_max, array[i]);
        }

        for(int i=0; i<len; i++){
            if(unordered_min < array[i]){
                unordered_min = i;
                break;
            }
        }

        for(int i=len-1; i>=0; i--){
            if(unordered_max > array[i]){
                unordered_max = i;
                break;
            }
        }
        return new int[] {unordered_min, unordered_max};
    }

    public int[] largestRange(int[] array) {
        int len = array.length;
        Map<Integer, Boolean> map = new HashMap<>();
        for(int i=0; i<len; i++) map.put(array[i], false);
        int max_length = Integer.MIN_VALUE;
        int[] max_range = new int[2];
        for(int i=0; i<len; i++){
            int num = array[i];
            if(!map.get(num)){
                map.put(num, true);
                int start = num-1;
                while(map.get(start) != null && !map.get(start)){
                    map.put(start, true);
                    start -= 1;
                }
                start += 1;
                int end = num+1;
                while(map.get(end) != null && !map.get(end)){
                    map.put(end, true);
                    end += 1;
                }
                end -= 1;
                int curr_length = end - start + 1;
                if(max_length <= curr_length){
                    max_length = curr_length;
                    max_range[0] = start;
                    max_range[1] = end;
                }
            }
        }
        return max_range;
    }

    public int minRewards(int[] scores) {
        int len = scores.length;
        int[] rewards = new int[len];

        for(int i=0; i<len; i++) rewards[i] = 1;

        for(int i=1; i<len; i++)
            if(scores[i-1] < scores [i])
                rewards[i] =  rewards[i-1] + 1;

        for(int i=len-2; i>=0; i--){
            if(scores[i] > scores [i+1]){
                int new_reward = rewards[i+1] + 1;
                rewards[i] = Math.max(rewards[i], new_reward);
            }
        }

        int total_reward = 0;
        for(int reward : rewards)
            total_reward += reward;

        return total_reward;
    }

    public List<Integer> zigzagTraverse(List<List<Integer>> array) {

        int height = array.size()-1;
        int width = array.get(0).size()-1;

        int row = 0;
        int col = 0;

        List<Integer> traverse = new  ArrayList<>();
        char direction = 'D';
        while(0<=row && row<=height && 0<=col && col<=width){
            traverse.add(array.get(row).get(col));
            if(direction == 'D'){
                if(col != 0 && row != height) {
                    row += 1;
                    col -= 1;
                }
                else{
                    direction = 'U';
                    if(row == height)
                        col += 1;
                    else
                        row += 1;
                }
            }
            else {
                if(row != 0 && col != width){
                    row -= 1;
                    col += 1;
                }
                else{
                    direction = 'D';
                    if(col == width)
                        row += 1;
                    else
                        col += 1;
                }
            }
        }
        return traverse;
    }

    public int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
        int reqs_len = reqs.length;
        int blocks_len = blocks.size();
        int[][] distances = new int[blocks_len][reqs_len];
        for(int i=0; i<blocks_len; i++)
            for(int j=0; j<reqs_len; j++)
                distances[i][j] = blocks.get(i).get(reqs[j]) ? 0 : Integer.MAX_VALUE - 1;

        for(int i=1;i<blocks_len; i++)
            for(int j=0; j<reqs_len; j++)
                distances[i][j] = Math.min(distances[i][j], distances[i-1][j] + 1);

        for(int i =blocks_len -2; i >= 0; i--)
            for(int j=0; j<reqs_len; j++)
                distances[i][j] = Math.min(distances[i][j], distances[i+1][j] + 1);

        int[] max_distance = new int[blocks_len];
        for(int i=0;i<blocks_len; i++) max_distance[i] = Integer.MIN_VALUE;
        for(int i=0;i<blocks_len; i++)
            for(int j=0; j<reqs_len; j++)
                max_distance[i] = Math.max(max_distance[i], distances[i][j]);

        int req_block = 0;
        for(int i=1;i<blocks_len; i++)
            if(max_distance[req_block] > max_distance[i])
                req_block = i;
        return req_block;
    }


    class CalendarMatching {

        public static void main(String[] args) {

            System.out.println("Matching Calendars for meetings!");
            List<CalendarMatching.StringMeeting> calendar1 = new ArrayList<CalendarMatching.StringMeeting>();
            calendar1.add(new CalendarMatching.StringMeeting("9:00", "10:30"));
            calendar1.add(new CalendarMatching.StringMeeting("12:00", "13:00"));
            calendar1.add(new CalendarMatching.StringMeeting("16:00", "18:00"));

            CalendarMatching.StringMeeting dailyBounds1 = new CalendarMatching.StringMeeting("6:00", "20:00");

            List<CalendarMatching.StringMeeting> calendar2 = new ArrayList<CalendarMatching.StringMeeting>();
            calendar2.add(new CalendarMatching.StringMeeting("7:00", "8:30"));
            calendar2.add(new CalendarMatching.StringMeeting("10:00", "11:30"));
            calendar2.add(new CalendarMatching.StringMeeting("12:30", "14:30"));
            calendar2.add(new CalendarMatching.StringMeeting("14:30", "15:00"));
            calendar2.add(new CalendarMatching.StringMeeting("16:00", "17:00"));

            CalendarMatching.StringMeeting dailyBounds2 = new CalendarMatching.StringMeeting("6:00", "18:30");

            int meetingDuration = 30;

            List<CalendarMatching.StringMeeting> expected = new ArrayList<CalendarMatching.StringMeeting>();
            expected.add(new CalendarMatching.StringMeeting("11:30", "12:00"));
            expected.add(new CalendarMatching.StringMeeting("15:00", "16:00"));
            expected.add(new CalendarMatching.StringMeeting("18:00", "18:30"));

            List<CalendarMatching.StringMeeting> actual =
                    CalendarMatching.calendarMatching(calendar1, dailyBounds1, calendar2, dailyBounds2, meetingDuration);
            System.out.println(actual);

        }

        static class StringMeeting {
            public String start;
            public String end;

            public StringMeeting(String start, String end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "[" +
                        "'" + start + '\'' +
                        " '" + end + '\'' + ']';
            }
        }

        static class IntMeeting {
            public int start;
            public int end;

            public IntMeeting(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "[" +
                        "'" + start + '\'' +
                        " '" + end + '\'' + ']';
            }
        }

        public static List<IntMeeting> mergeCalendars(List<IntMeeting> calendar1, List<IntMeeting> calendar2){

            int len1 = calendar1.size();
            int len2 = calendar2.size();

            int index1 = 0;
            int index2 = 0;

            List<IntMeeting> merged = new ArrayList<>();

            while(index1<len1 || index2<len2){
                if(calendar1.get(index1).start <= calendar2.get(index2).start){
                    merged.add(calendar1.get(index1));
                    index1 += 1;
                    if(index1 == len1){
                        for(; index2<len2; index2++)
                            merged.add(calendar2.get(index2));
                        break;
                    }
                }
                else{
                    merged.add(calendar2.get(index2));
                    index2 += 1;
                    if(index2 == len2){
                        for(; index1<len1; index1++)
                            merged.add(calendar1.get(index1));
                        break;
                    }
                }
            }

            return merged;
        }

        public static int timeToMinutes(String time){
            int i = time.indexOf(':');
            int hours = Integer.parseInt(time.substring(0,i));
            int minutes = Integer.parseInt(time.substring(i+1));
            return hours * 60 + minutes;
        }

        public static String minutesToTime(int minutes){
            int hours = minutes / 60;
            String hour = hours >= 10 ? hours+"" :"0"+hours;
            int minute = minutes % 60;
            String mnts = minute >= 10 ? minute+"" :"0"+minute;
            return hour + ":" + mnts;
        }

        public static List<IntMeeting> mergeMeetings(List<IntMeeting> calendar){
            int len = calendar.size();
            int start = calendar.get(0).start;
            int end = calendar.get(0).end;
            List<IntMeeting> merged = new ArrayList<>();
            for(int i=1; i<len; i++){
                if(end >= calendar.get(i).start){
                    end = Math.max(end, calendar.get(i).end);
                }
                else {
                    merged.add(new IntMeeting(start, end));
                    start = calendar.get(i).start;
                    end = Math.max(end, calendar.get(i).end);
                }
            }
            merged.add(new IntMeeting(start, end));
            return merged;
        }

        public static List<StringMeeting> calendarMatching(
                List<StringMeeting> calendar1,
                StringMeeting dailyBounds1,
                List<StringMeeting> calendar2,
                StringMeeting dailyBounds2,
                int meetingDuration) {




            int cal1_len = calendar1.size();
            List<IntMeeting> cal1 = new ArrayList<>();
            IntMeeting daily_bounds1 = new IntMeeting(timeToMinutes(dailyBounds1.start), timeToMinutes(dailyBounds1.end));
            int cal2_len = calendar2.size();
            List<IntMeeting> cal2 = new ArrayList<>();
            IntMeeting daily_bounds2 = new IntMeeting(timeToMinutes(dailyBounds2.start), timeToMinutes(dailyBounds2.end));



            cal1.add(new IntMeeting(0, daily_bounds1.start));
            for(StringMeeting meeting : calendar1){
                cal1.add(new IntMeeting(timeToMinutes(meeting.start), timeToMinutes(meeting.end)));
            }
            cal1.add(new IntMeeting(daily_bounds1.end,timeToMinutes("23:59")));



            cal2.add(new IntMeeting(0, daily_bounds2.start));
            for(StringMeeting meeting : calendar2){
                cal2.add(new IntMeeting(timeToMinutes(meeting.start), timeToMinutes(meeting.end)));
            }
            cal2.add(new IntMeeting(daily_bounds2.end,timeToMinutes("23:59")));



            List<IntMeeting> mergeCal = mergeCalendars(cal1, cal2);
            List<IntMeeting> mergedMeetings = mergeMeetings(mergeCal);




            List<IntMeeting> availableSlots = new ArrayList<>();
            for(int i=1; i<mergedMeetings.size(); i++){
                availableSlots.add(new IntMeeting(mergedMeetings.get(i-1).end, mergedMeetings.get(i).start));
            }



            List<IntMeeting> matchedSlots = new ArrayList<>();
            for(int i=0; i<availableSlots.size(); i++){
                if(availableSlots.get(i).end - availableSlots.get(i).start >= meetingDuration)
                    matchedSlots.add(availableSlots.get(i));
            }

        /*System.out.println("calendar1 - " + calendar1);
        System.out.println("cal1 - " + cal1);
        System.out.println("dailyBounds1 - " + dailyBounds1);
        System.out.println("daily_bounds1 - "+daily_bounds1);
        System.out.println("calendar2 - " + calendar2);
        System.out.println("cal2 - " + cal2);
        System.out.println("dailyBounds2 - " + dailyBounds2);
        System.out.println("daily_bounds2 - "+daily_bounds2);
        System.out.println("mergeCal - " + mergeCal);
        System.out.println("mergedMeetings - " + mergedMeetings);
        System.out.println("availableSlots - "+ availableSlots);
        System.out.println("matchedSlots - " + matchedSlots);*/

            List<StringMeeting> calendarMatch = new ArrayList<>();
            for(IntMeeting meeting : matchedSlots ){
                calendarMatch.add(new StringMeeting(minutesToTime(meeting.start), minutesToTime(meeting.end)));
            }

            return calendarMatch;
        }


    }


}

class AlgoExpertStack{

    public static void main(String[] args) {

    }
    static class MinMaxStack {

        Stack<int[]> stack ;
        int size;

        public MinMaxStack(){
            this.stack = new Stack<>();
            this.size = 0;
        }
        public int peek() {
            return stack.peek()[0];
        }

        public int pop() {
            this.size -= 1;
            return stack.pop()[0];
        }

        public void push(Integer number) {
            int curr_min = this.size != 0 ? getMin() : Integer.MAX_VALUE;
            int curr_max = this.size != 0 ? getMax() : Integer.MIN_VALUE;
            this.size += 1;
            stack.push(new int[]{ number, Math.min(curr_min, number), Math.max(curr_max, number) });
        }

        public int getMin() {
            return stack.peek()[1];
        }

        public int getMax() {
            return stack.peek()[2];
        }
    }
    public static boolean balancedBrackets(String str) {
        Map<Character, Character> bracks =  new HashMap<>();
        bracks.put('(', ')');
        bracks.put('[', ']');
        bracks.put('{', '}');

        Set<Character> chars = new HashSet<>(bracks.keySet());
        for(char c : bracks.keySet())  chars.add(bracks.get(c));

        Stack<Character> stack = new Stack<>();
        for(char c : str.toCharArray()){
            if(!chars.contains(c)) continue;
            if(bracks.get(c) != null){
                stack.push(c);
            }
            else {
                if(stack.isEmpty()) return false;
                if(bracks.get(stack.pop()) != c) return false;
            }
        }
        return stack.size() == 0;
    }
    public ArrayList<Integer> sunsetViews(int[] buildings, String direction) {

        int len = buildings.length;
        if(direction.equals("EAST")) {
            for(int i=0; i<(len / 2); i++) {
                int temp = buildings[i];
                buildings[i] = buildings[len-1-i];
                buildings[len-1-i] = temp;
            }
        }
        ArrayList<Integer> views = new ArrayList<>();
        int min_bld = Integer.MIN_VALUE;

        for(int i=0; i<len; i++){
            if(min_bld < buildings[i]){
                views.add(i);
                min_bld = buildings[i];
            }
        }
        if(direction.equals("EAST")) {
            int len1 = views.size();
            Collections.reverse(views);
            for(int i=0; i<len1; i++){
                views.set(i, len-1-i);
            }
        }
        return views;
    }
    class SortStack{
        public void insertIntoStack(ArrayList<Integer> stack, int val){
            int len = stack.size();
            if(len ==  0 || stack.get(len-1) <= val){
                stack.add(val);
                return;
            }
            int value = stack.remove(len-1);
            insertIntoStack(stack, val);
            stack.add(value);
            return;
        }
        public ArrayList<Integer> sortStack(ArrayList<Integer> stack) {
        int len = stack.size();
        if(len == 0) return stack;
        int val = stack.remove(len-1);
        sortStack(stack);
        insertIntoStack(stack, val);
        return stack;
    }
    }
    public int[] nextGreaterElement(int[] array) {
        int len = array.length;
        int[] nextGrtEle = new int[len];
        for(int i=0; i<len; i++) nextGrtEle[i] = -1;
        Stack<Integer> stack = new Stack<>();
        for(int idx = 2 * len -1; idx>=0; idx++){
            int ind = idx % len;
            while(!stack.isEmpty()){
                if(array[ind] >= stack.peek()){
                    stack.pop();
                }
                else {
                    nextGrtEle[ind] = stack.peek();
                    break;
                }
            }
            stack.push(array[ind]);
        }
        return nextGrtEle;
    }

    public static String shortenPath(String path) {
        boolean startWithPath = path.charAt(0) == '/';
        String[] tokensArr = path.split("/");
        List<String> tokensList = Arrays.asList(tokensArr);
        List<String> filteredTokens = tokensList.stream().filter(token -> (token.length() > 0 && !token.equals(".")))
                .collect(Collectors.toList());

        Stack<String> stack = new Stack<>();
        if(startWithPath) stack.push("");

        for(String token : filteredTokens){
            if(token.equals("..")){
                if( stack.isEmpty() || stack.peek().equals(".."))  stack.push(token);
                else if( !stack.peek().equals("") )  stack.pop();
            }
            else stack.push(token);
        }
        if(stack.size() == 1 && stack.peek().equals("")) return "/";
        return String.join("/", stack);
    }

    public int largestRectangleUnderSkyline(ArrayList<Integer> bldgs) {
        int len = bldgs.size();
        int[] buildings = new int[len];
        int index = 0;
        for(int num : bldgs) buildings[index++] = num;

        Stack<Integer> stack = new Stack<>();
        int[] left = new int[len];
        for(int i=0; i<len; i++){
            while(!stack.isEmpty()){
                if(buildings[stack.peek()] >= buildings[i])
                    stack.pop();
                else break;
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        int[] right = new int[len];
        for(int i=len-1; i>=0; i--){
            while(!stack.isEmpty()){
                if(buildings[stack.peek()] >= buildings[i])
                    stack.pop();
                else break;
            }
            right[i] = stack.isEmpty() ? len : stack.peek();
            stack.push(i);
        }

        int max_area = 0;
        for(int i=0; i<len; i++){
            int curr_area = buildings[i] * (right[i] - left[i] -1);
            max_area = Math.max(max_area, curr_area);
        }
        return max_area;
    }

}

class AlgoExpertLinkedList{
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }


    public LinkedList removeDuplicatesFromLinkedList(LinkedList head) {
        if(head == null) return head;
        LinkedList node = head;
        while (node.next != null){
            if(node.value == node.next.value)
                node.next = node.next.next;
            else
                node = node.next;
        }
        return head;
    }
    public static LinkedList findLoop(LinkedList head) {
        LinkedList slow = head;
        LinkedList fast = head.next;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = head;
        fast = fast.next;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    public static void removeKthNodeFromEnd(LinkedList head, int k) {
        LinkedList start = head;
        LinkedList end = head.next;

        for(int i=1; i<k; i++) end = end.next;

        if (end == null){
            head.value = head.next.value;
            head.next = head.next.next;
            return;
        }

        while (end.next != null){
            start = start.next;
            end = end.next;
        }

        start.next = start.next.next;
    }
    public LinkedList sumOfLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
        LinkedList one = linkedListOne;
        LinkedList two = linkedListTwo;
        int carry = 0;
        LinkedList head = new LinkedList(0);
        LinkedList node = head;
        while (one != null || two != null || carry != 0){
            int num_one = one != null ? one.value : 0;
            int num_two = two != null ? two.value : 0;

            int sum = num_one + num_two + carry;
            int unit = sum % 10;
            carry = sum / 10;

            LinkedList curr = new LinkedList(unit);
            node.next = curr;
            node = curr;

            one = one != null ? one.next : null;
            two = two != null ? two.next : null;

        }
        return  head.next;
    }
    public static LinkedList reverseLinkedList(LinkedList head) {
        LinkedList node = head;
        LinkedList prev = null;
        while (node.next != null){
            LinkedList temp = node.next;
            node.next = prev;
            prev = node;
            node = temp;
        }
        node.next = prev;
        return node;
    }
    public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) {
        LinkedList head = new LinkedList(0);
        LinkedList curr = head;
        while (headOne != null || headTwo != null){
            int one = headOne != null ? headOne.value : Integer.MAX_VALUE;
            int two = headTwo != null ? headTwo.value : Integer.MAX_VALUE;
            if(one <= two){
                curr.next = headOne;
                headOne = headOne.next;
            }
            else {
                curr.next = headTwo;
                headTwo = headTwo.next;
            }
            curr = curr.next;
        }
        return head.next;
    }
    public static LinkedList mergeLinkedLists1(LinkedList headOne, LinkedList headTwo) {
        LinkedList previous = null;
        LinkedList one = headOne;
        LinkedList two = headTwo;

        while (one != null && two != null){
            if(one.value < two.value){
                previous = one;
                one = one.next;
            }
            else{
                if (previous != null) previous.next = two;
                previous = two;
                two = two.next;
                previous.next = one;
            }
        }

        if(one == null) previous.next = two;
        return headOne.value < headTwo.value ? headOne : headTwo;
    }
    public static LinkedList shiftLinkedList(LinkedList head, int k) {
        LinkedList node = head;
        int nodes = 1;

        while (node.next != null){
            node = node.next;
            nodes += 1;
        }

        LinkedList tail = node;
        tail.next = head;

        int shift = k % nodes;
        shift = shift < 0 ? shift : nodes - shift;
        shift = Math.abs(shift);
        System.out.println(shift);

        node = tail;
        for(int i =0; i<shift; i++){
            node = node.next;
        }

        LinkedList new_head = node.next;
        node.next = null;

        return new_head;
    }
    public static LinkedList rearrangeLinkedList(LinkedList head, int k) {
        LinkedList smallListStart = new LinkedList(0);
        LinkedList smallListEnd = smallListStart;
        LinkedList equalListStart = new LinkedList(0);
        LinkedList equalListEnd = equalListStart;
        LinkedList largeListStart = new LinkedList(0);
        LinkedList largeListEnd = largeListStart;

        LinkedList currentNode = head;
        while(currentNode != null){
            if(currentNode.value < k){
                smallListEnd.next = currentNode;
                smallListEnd = currentNode;
            }
            else if(currentNode.value > k){
                largeListEnd.next = currentNode;
                largeListEnd = currentNode;
            }
            else{
                equalListEnd.next = currentNode;
                equalListEnd = currentNode;
            }
            currentNode = currentNode.next;
        }
        if (smallListStart.next != null && equalListStart.next != null &&  largeListStart.next != null){
            smallListEnd.next = equalListStart.next;
            equalListEnd.next = largeListStart.next;
        }
        else if (smallListStart.next != null && equalListStart.next != null &&  largeListStart.next == null){
            smallListEnd.next = equalListStart.next;
        }
        else if (smallListStart.next == null && equalListStart.next != null &&  largeListStart.next != null){
            smallListStart=equalListStart;
            equalListEnd.next = largeListStart.next;
        }
        else if (smallListStart.next == null && equalListStart.next == null &&  largeListStart.next != null){
            smallListStart = largeListStart;
        }
        else if (smallListStart.next != null && equalListStart.next == null &&  largeListStart.next != null){
            smallListEnd.next = largeListStart.next;
        }
        return smallListStart.next;
    }
    public static LinkedList rearrangeLinkedList1(LinkedList head, int k) {
        LinkedList new_start = new LinkedList(0);
        LinkedList return_start = new_start;

        LinkedList dummy_start = new LinkedList(0);
        dummy_start.next = head;

        LinkedList node;

        for(int i=0; i<2; i++){
            node = dummy_start;
            while (node.next != null){
                boolean condition = i==0 ? node.next.value < k : node.next.value == k;
                if(condition){
                    new_start.next = node.next;
                    node.next = node.next.next;
                    new_start = new_start.next;
                    new_start.next = null;
                }
                else node = node.next;
            }
        }

        new_start.next = dummy_start.next;
        return return_start.next;
    }
    public boolean linkedListPalindrome(LinkedList head) {
        LinkedList slow = head;
        LinkedList fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        LinkedList listOne = head;
        LinkedList listTwo;

        if(slow == null) listTwo = null;
        else{
            LinkedList node = slow;
            LinkedList prev = null;
            while (node.next != null){
                LinkedList temp = node.next;
                node.next = prev;
                prev = node;
                node = temp;
            }
            node.next = prev;
            listTwo = node;
        }

        while(listTwo != null){
            if(listOne.value != listTwo.value) return false;
            listOne = listOne.next;
            listTwo = listTwo.next;
        }
        return true;
    }
    public LinkedList zipLinkedList(LinkedList linkedList) {
        if(linkedList.next == null || linkedList.next.next == null) return linkedList;

        LinkedList slow = linkedList;
        LinkedList fast = linkedList;
        LinkedList prev = null;
        while (fast != null && fast.next != null){
            prev =slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        LinkedList listOne = linkedList;
        LinkedList listTwo;
        if(fast == null){
            listTwo = slow;
            prev.next = null;
        }
        else{
            listTwo = slow.next;
            slow.next = null;
        }

        LinkedList current = listTwo;
        prev = null;
        while (current != null){
            LinkedList temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        listTwo = prev;

        LinkedList head = new LinkedList(0);
        current = head;
        int turn = 0;
        while (listOne != null || listTwo != null){
            boolean trn = turn++ % 2 == 0 ;
            if(trn){
                current.next = listOne;
                listOne = listOne.next;
            }
            else{
                current.next = listTwo;
                listTwo = listTwo.next;
            }
            current = current.next;
        }
        return head.next;
    }
    public void printLL(LinkedList node){
        while (node != null){
            System.out.print(node.value+" ");
            node = node.next;
        }
        System.out.println();
    }
    LinkedList reverseKGroup(LinkedList head, int k) {
        LinkedList node = head;
        for(int i=0; i<k; i++){
            if(node == head) return head;
            node = node.next;
        }

        LinkedList current = reverseKGroup(node, k);


        while (node != head){

        }
        /*
        LinkedList node = head;

        for(int i=0; i<k; i++){
            if(node == null) return head;
            node = node.next;
        }
        LinkedList current = reverseKGroup(node, k);

        while(head != node){
            LinkedList next = head.next;
            head.next = current;
            current = head;
            head= next;
        }
        return current;
        */

        return null;
    }





    static class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    static class DoublyLinkedList {
        public Node head;
        public Node tail;

        public DoublyLinkedList(){
            this.head = null;
            this.tail = null;
        }

        public void setHead(Node node) {
            if(this.head == null){
                this.head = node;
                this.tail = node;
            }
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }

        public void setTail(Node node) {
            if(this.tail == null){
                this.head = node;
                this.tail = node;
            }
            node.prev = this.tail;
            this.tail.next = node;
            this.tail = node;
        }

        public void insertBefore(Node node, Node nodeToInsert) {
            if(node == this.head) setHead(nodeToInsert);
            insertAfter(node.prev, nodeToInsert);
        }

        public void insertAfter(Node node, Node nodeToInsert) {
            if(node == this.tail) setTail(nodeToInsert);
            Node curr = this.head;

            while(curr != node) curr = curr.next;

            nodeToInsert.prev = curr;
            nodeToInsert.next = curr.next;

            curr.next.prev = nodeToInsert;
            curr.next = nodeToInsert;

        }

        public void insertAtPosition(int position, Node nodeToInsert) {
            if(position == 1) setHead(nodeToInsert);
            int index = 2;
            Node node = this.head;
            while (index != position){
                node = node.next;
                index += 1;
            }
            insertAfter(node, nodeToInsert);
        }

        public void removeNodesWithValue(int value) {
            Node node = this.head;
            while (node != null){
                Node next = node.next;
                if(node.value == value) {
                    remove(node);
                }
                node = next;
            }
        }

        public void remove(Node node) {
            if(this.head == this.tail && node == this.head){
                this.head = null;
                this.tail = null;
            }
            else if(node == this.head){
                node.next.prev = null;
                this.head = node.next;
                node.next = null;
            }
            else if(node == this.tail){
                node.prev.next = null;
                this.tail = node.prev;
                node.prev = null;
            }
            else{
                node.next.prev = node.prev;
                node.prev.next = node.next;
                node.next = null;
                node.prev = null;
            }
        }

        public boolean containsNodeWithValue(int value) {
            Node node = this.head;
            while (node != null) {
                if(node.value == value) return true;
                node = node.next;
            }
            return false;
        }
    }


}

class AlgoExpertSort{

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int[] bubbleSort(int[] array) {
        int len = array.length;
        for(int i=len-1; i>0; i--){
            for(int j=0;j<i; j++){
                if(array[j]>array[j+1]){
                    swap(array, j, j+1);
                }
            }
        }
        return array;
    }

    public static int[] insertionSort(int[] array) {
        int len = array.length;
        for(int i=1; i<len; i++){
            for(int j=i+1; j>=1; j--){
                if(array[j] > array[j-1]) break;
                int temp = array[j];
                array[j] = array[j-1];
                array[j-1] = temp;
            }
        }
        return array;
    }





}

class AlgoExpertSearching{
    public static int binarySearch(int[] array, int target) {
        int start = 0;
        int end = array.length-1;
        while (start<=end){
            int mid = start + (end - start) / 2;
            if(array[mid] == target) return mid;
            else if(array[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return -1;
    }
    public static int[] findThreeLargestNumbers(int[] array) {
        int[] numbers = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        int len = array.length;

        for(int i=0; i<len; i++){
            if(array[i] >= numbers[2]){
                numbers[0] = numbers[1];
                numbers[1] = numbers[2];
                numbers[2] = array[i];
            }
            else if(array[i] >= numbers[1]){
                numbers[0] = numbers[1];
                numbers[1] = array[i];

            }
            else if(array[i] >= numbers[0]){
                numbers[0] = array[i];
            }
        }
        return numbers;
    }
    public static int[] searchInSortedMatrix(int[][] matrix, int target) {
        int height = matrix.length;
        int width = matrix[0].length;

        int row =  0;
        int col = width-1;

        while(0<=row && row<height && 0<=col && col<width ){
            if(matrix[row][col] == target) return new int[]{row, col};
            else if(matrix[row][col] < target) row += 1;
            else col -= 1;
        }

        return new int[] {-1, -1};
    }

    // [45, 61, 71, 72, 73, 0, 1, 21, 33, 37] 30 -> -1
    public static int shiftedBinarySearch(int[] array, int target) {
        int len = array.length;

        int start = 0;
        int end = len-1;

        while (start <= end){
            int mid = start + (end - start) / 2;
            if(array[mid] == target) return mid;
            char sorted = array[start] <= array[mid] ? 'F' : 'S';
            if(sorted == 'F'){
                if(array[start] <= target && target < array[mid]) end = mid - 1;
                else start = mid + 1;
            }
            else{
                if(array[mid] < target && target <= array[end]) start = mid + 1;
                else end = mid - 1;
            }
        }
        return -1;
    }

    public static int[] searchForRange(int[] array, int target) {
        int ind1 = equalToGreaterThan(array, target);
        if(ind1 == -1) return new int[] {-1, -1};
        int ind2 = equalToGreaterThan(array, target+1);
        if(ind2 == -1) return new int[] {ind1, array.length-1};
        return new int[] {ind1, ind2-1};
    }
    public static int equalToGreaterThan(int[] array, int target){
        int len = array.length;
        int start = 0;
        int end = len-1;
        int index = -1;
        while (start <= end){
            int mid = start + (end - start) / 2;
            if(array[mid] >= target){
                index = mid;
                end = mid - 1;
            }
            else start = mid + 1;
        }
        return index;
    }


}

class AlgoExpertHeapQue{
    public int laptopRentals(ArrayList<ArrayList<Integer>> times) {
        int len = times.size();

        int[] start = new int[len];
        int[] end = new int[len];

        int index = 0;
        for(ArrayList<Integer> time : times){
            start[index] = time.get(0);
            end[index] = time.get(1);
            index += 1;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int current = 0;
        int max = 0;
        int i = 0;
        int j = 0;

        while (i<len){
            if(start[i] < end[j]){
                current += 1;
                max = Math.max(max, current);
                i += 1;
            }
            else {
                j -= 1;
                current -= 1;
            }
        }

        return max;
    }

    static class ContinuousMedianHandler {
        double median = 0;

        PriorityQueue<Integer> low;
        PriorityQueue<Integer> high;

        public ContinuousMedianHandler(){
            this.low = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 -o1;
                }
            });
            this.high = new PriorityQueue<>();
        }

        public void insert(int number) {
            int lenOne = this.low.size();
            int lenTwo = this.high.size();

            int low_top = this.low.peek();
            if(lenOne == lenTwo){
                if(lenOne == 0){
                    this.low.add(number);
                    this.median = this.low.peek();
                    return;
                }
                if(lenOne == 0)
                if(low_top >= number){
                    this.low.add(number);
                    this.median = this.low.peek();
                }
                else{
                    this.high.add(number);
                    this.median = this.high.peek();
                    this.low.add(this.high.poll());
                }
            }
            else{
                if(lenTwo == 0){
                    this.high.add(number);
                    this.median = (this.low.peek() + this.high.peek()) / 2;
                    return;
                }
                if(low_top >= number){
                    this.low.add(number);
                    this.high.add(this.low.poll());
                }
                else{
                    this.high.add(number);
                }
                this.median = (this.low.peek() + this.high.peek()) / 2;
            }
        }

        public double getMedian() {
            return median;
        }
    }
}

class AlgoExpertHeap{

    static class MinHeap {
        List<Integer> heap = new ArrayList<Integer>();

        public MinHeap(List<Integer> array) {
            heap = buildHeap(array);
        }

        public List<Integer> buildHeap(List<Integer> array) {
            int len = array.size()-2;
            for(int i=len/2; i>=0; i++){
                this.siftDown(i, array.size()-1, array);
            }
            return array;
        }

        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            while( 2*currentIdx+1 <= endIdx){
                int leftChildIdx = 2*currentIdx+1;
                if(2*currentIdx+2 <= endIdx){
                    int rightChildIdx = 2*currentIdx+2;
                    int maxElement = Math.max(heap.get(leftChildIdx), heap.get(rightChildIdx));
                    int swapIdx = maxElement == heap.get(leftChildIdx) ? leftChildIdx : rightChildIdx;
                    Collections.swap(heap, currentIdx, swapIdx);
                    currentIdx = swapIdx;
                }
                else{
                    if(heap.get(leftChildIdx) > heap.get(currentIdx))
                        Collections.swap(heap, currentIdx, leftChildIdx);
                    break;
                }
            }
        }

        public void siftUp(int currentIdx, List<Integer> heap) {
            while(currentIdx > 0){
                int parentIdx = (currentIdx-1) / 2;
                if(heap.get(parentIdx) <= heap.get(currentIdx)) break;
                Collections.swap(this.heap, currentIdx, parentIdx);
                currentIdx = parentIdx;
            }
        }

        public int peek() {
            return this.heap.get(0);
        }

        public int remove() {
            Collections.swap(this.heap, 0, this.heap.size()-1);
            int minValue = this.heap.remove(this.heap.size()-1);
            this.siftDown(0, this.heap.size()-1, this.heap);
            return minValue;
        }

        public void insert(int value) {
            this.heap.add(value);
            this.siftUp(this.heap.size()-1, this.heap);
        }
    }
}

class MyHeap{
    public static void main(String[] args) {
        int[] arr = new int[]{48, 12, 24, 7, 8, -5, 24, 391};
        List<Integer> array = new ArrayList<>();
        for(int num : arr) array.add(num);
        System.out.println(array);
        MyHeap heap = new MyHeap(array);
        System.out.println(array);
    }
    List<Integer> heap = new ArrayList<>();
    public MyHeap(List<Integer> array){
        buildHeap(array);
    }
    public List<Integer> buildHeap(List<Integer> array) {
        int len = array.size()-2;
        for(int i=len/2; i>=0; i--){
            this.siftDown(i, array.size()-1, array);
        }
        return array;
    }
    public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
        int leftChildIdx = 2*currentIdx+1;
        while( leftChildIdx <= endIdx){
            int rightChildIdx = 2*currentIdx+2;
            if(rightChildIdx <= endIdx){
                int minElement = Math.min(heap.get(leftChildIdx), heap.get(rightChildIdx));
                int swapIdx = minElement == heap.get(leftChildIdx) ? leftChildIdx : rightChildIdx;
                if(heap.get(swapIdx) >= heap.get(currentIdx)) return;
                Collections.swap(heap, currentIdx, swapIdx);
                currentIdx = swapIdx;
                leftChildIdx = 2*currentIdx+1;
            }
            else{
                if(heap.get(leftChildIdx) < heap.get(currentIdx))
                    Collections.swap(heap, currentIdx, leftChildIdx);
                break;
            }
        }
    }

}

class AlgoExpertTree{

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;
        public BinaryTree parent;

        public BinaryTree(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "BinaryTree{" +
                    "value=" + value +
                    '}';
        }
    }

    public static List<Integer> inOrderTraverse(BinaryTree tree, List<Integer> array) {

        Stack<BinaryTree> stack = new Stack<>();
        BinaryTree prev = null;
        BinaryTree curr = tree;


        while (curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            array.add(curr.value);
            System.out.println(curr.value);
            curr = curr.right;
        }


        return new ArrayList<Integer>();
    }

    public static List<Integer> preOrderTraverse(BinaryTree tree, List<Integer> array) {
        // Write your code here.
        return new ArrayList<Integer>();
    }

    public static List<Integer> postOrderTraverse(BinaryTree tree, List<Integer> array) {
        // Write your code here.
        return new ArrayList<Integer>();
    }

    public static List<Integer>  brnchSums(BinaryTree node, List<Integer> sums, int sum){
        int new_sum = sum + node.value;
        if(node.left == null && node.right == null) sums.add(new_sum);


        if(node.left != null) brnchSums(node.left, sums, new_sum);
        if(node.right != null) brnchSums(node.right, sums, new_sum);
        return sums;
    }
    public static List<Integer> branchSums(BinaryTree root) {
        List<Integer> sums = new ArrayList<Integer>();
        return brnchSums(root, sums, 0);
    }


    public static int nodeDepthSum(BinaryTree node, int depth){
        if(node == null) return 0;
        int left = nodeDepthSum(node.left, depth+1) ;
        int right =  nodeDepthSum(node.right, depth+1) ;
        return depth + left + right;

    }
    public static int nodeDepths(BinaryTree root) {
        return nodeDepthSum(root, 0);
    }


    public static void invertBinaryTree(BinaryTree node) {
        if(node == null) return;

        BinaryTree temp = node.left;
        node.left = node.right;
        node.right = temp;

        invertBinaryTree(node.left);
        invertBinaryTree(node.right);

    }

    public int[] getDiameter(BinaryTree node){
        if(node == null)
            return new int[]{0, 0}; // 1. dia 2. depth

        int left[] = 	getDiameter(node.left);
        int right[] = getDiameter(node.right);

        int depth = Math.max(left[1], right[1]) + 1;
        int diameter = Math.max(Math.max(left[0], right[0]), left[1] + right[1]);

        return new int[]{diameter, depth};
    }
    public int binaryTreeDiameter(BinaryTree tree) {
        return getDiameter(tree)[0];
    }

    public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
        if(node.right != null){
            BinaryTree successor = node.right;
            while(successor.left != null)
                successor = successor.left;
            return successor;
        }
        else{
            BinaryTree node_parent = node.parent;
            while(node_parent != null && node_parent.left != node){
                node = node_parent;
                node_parent = node_parent.parent;
            }
            return node_parent;
        }
    }

    public int[] getHeight(BinaryTree node){

        if(node == null) return new int[]{0, 0};

        int[] left = getHeight(node.left) ;
        int[] right = getHeight(node.right);

        if(left[0] == 1 || right[0] == 1) return new int[]{1, 0};

        if(Math.abs(left[1] - right[1]) > 1)  return new int[]{1, 0};

        return new int[]{ 0, Math.max(left[1], right[1]) +1 };

    }
    public boolean heightBalancedBinaryTree(BinaryTree tree) {
        return getHeight(tree)[0] == 0;
    }



    public static int[] maxPathSums(BinaryTree node){
        if(node == null) return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};

        int[] left = maxPathSums(node.left);
        int[] right = maxPathSums(node.right);

        int leftPath = left[0];
        int leftBranch = left[1];
        int rightPath = right[0];
        int rightBranch = right[1];

        int currLeftBranch = leftBranch != Integer.MIN_VALUE ? node.value + leftBranch : Integer.MIN_VALUE;
        int currRightBranch = rightBranch != Integer.MIN_VALUE ? node.value + rightBranch : Integer.MIN_VALUE;

        int maxBranchSum = Math.max(Math.max(currLeftBranch, currRightBranch), node.value);

        int currLeftRightPath ;

        if(leftBranch != Integer.MIN_VALUE || rightBranch != Integer.MIN_VALUE){
            currLeftRightPath = Integer.MIN_VALUE;
        }
        else{
            currLeftRightPath = leftBranch + node.value + rightBranch;
        }

        int[] vals = new int[]{leftPath, rightPath, currLeftBranch, currRightBranch, currLeftRightPath, node.value};
        List<Integer> lst = new ArrayList<>();
        for(int num : vals) lst.add(num);
        int maxPathSum = Collections.max(lst);

        return new int[]{maxPathSum, maxBranchSum};
    }
    public static int maxPathSum(BinaryTree tree) {
        int[] vals = maxPathSums(tree);
        return Math.max(vals[0], vals[1]);
    }


    public boolean buildMap(BinaryTree node, int target, HashMap<BinaryTree, Boolean> map){
        if(node == null) return false;

        boolean left = buildMap(node.left, target, map);
        boolean right = buildMap(node.right, target, map);

        boolean thisVal = ( node.value == target ) || left || right ;

        map.put(node, thisVal);

        return thisVal;
    }
    public ArrayList<Integer> getKDistanceNodes(BinaryTree node, HashMap<BinaryTree, Boolean> map, int k, int dist,  ArrayList<Integer> nodes){
        if(node == null) return  nodes;

        int curr_dist = map.get(node) ? dist - 1 : dist + 1;

        if(curr_dist == k) nodes.add(node.value);

        getKDistanceNodes(node.left, map, k, curr_dist, nodes);
        getKDistanceNodes(node.right, map, k, curr_dist, nodes);

        return nodes;
    }
    public ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k) {
        HashMap<BinaryTree, Boolean> map = new HashMap<>();
        buildMap(tree, target, map);
        System.out.println(map);
        int dist = 0;
        for(BinaryTree nd : map.keySet()) if(map.get(nd)) dist += 1;
        ArrayList<Integer> nodes = new ArrayList<>();
        return getKDistanceNodes(tree, map, k, dist, nodes);
    }

    public static void iterativeInOrderTraversal(BinaryTree tree, Function<BinaryTree, Void> callback) {

        BinaryTree prev = null;
        BinaryTree curr = tree;

        while (curr != null){
            BinaryTree next = null;

            if(curr.right == prev && prev != null) next = curr.parent;
            else if(curr.left == null || prev == curr.left){
                System.out.println(curr.value);
                callback.apply(curr);
                next = curr.right != null ? curr.right : curr.parent;
            }
            else if(curr.left != null) next = curr.left;

            prev = curr;
            curr = next;
        }

    }


    public  static int allNodes(BinaryTree node, int depth){
        if(node == null) return 0;
        int contribution = ( depth * (depth + 1) ) / 2;
        depth += 1;
        int left = allNodes(node.left, depth);
        int right = allNodes(node.right, depth);

        return  left + contribution + right;
    }
    public static int allKindsOfNodeDepths(BinaryTree root) {
        return allNodes(root, 0);
    }
    public static int allKindsOfNodeDepths1(BinaryTree root) {
        if(root == null) return 0;
        Queue<BinaryTree> queue = new LinkedList<>();
        int sum = 0;
        int depth = -1;
        queue.add(root);
        while (!queue.isEmpty()){
            depth += 1;
            int size = queue.size();
            for(int i=0; i<size; i++) {
                int contribution = (depth * (depth + 1)) / 2;
                sum += contribution;
                BinaryTree node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right !=null) queue.add(node.right);
            }
        }
        return sum;
    }


    public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) {

        Stack<BinaryTree> stack1 = new Stack<>();
        Stack<BinaryTree> stack2 = new Stack<>();

        BinaryTree node1 = getNextLeaf(stack1,tree1);
        BinaryTree node2 = getNextLeaf(stack2,tree2);
        while (true){
            if(node1 == null && node2 == null) break;
            else if(node1 == null || node2 == null || node1.value != node2.value) return  false;
            node1 = getNextLeaf(stack1,null);
            node2 = getNextLeaf(stack2,null);
        }
        return true;
    }

    public BinaryTree getNextLeaf(Stack<BinaryTree> stack, BinaryTree current ){

        while (current != null || !stack.isEmpty()){
            while (current != null){
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if(current.left == null && current.right == null) return current;
            current = current.right;
        }
        return null;
    }


}

class AlgoExpertBST{
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }

        public void insert(int value) {
            if (value < this.value) {
                if (left == null) {
                    left = new BST(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new BST(value);
                } else {
                    right.insert(value);
                }
            }
        }
    }

    public static int findClosestValueInBst(BST root, int target) {
        if(root == null) return -1;
        BST node = root;
        int closest = root.value;

        while (node != null){
            closest = Math.abs(target - node.value) < Math.abs(target - closest) ? node.value : closest;
            if(target < node.value ) node = node.left;
            else node = node.right;
        }
        return closest;
    }


    public static BST minHeightBst(List<Integer> array) {
        int len = array.size();
        int[] arr = new int[len];
        int ind = 0;
        for(int num : array) arr[ind++] = num;

        int mid = len / 2;
        BST root = new BST(arr[mid]);
        minHeightBST(root, arr, 0, mid-1);
        minHeightBST(root, arr, mid+1, len-1);
        return root;
    }
    public static void minHeightBST(BST node, int[] arr, int min, int max){
        if(min > max) return;
        int mid = min + (max - min) / 2;
        node.insert(arr[mid]);
        minHeightBST(node, arr, min, mid-1);
        minHeightBST(node, arr, mid+1, max);
    }
    public static BST minHeightBST1(int[] arr, int min, int max){
        if(min > max) return null;
        int mid = min + (max - min) / 2;
        BST node = new BST(arr[mid]);
        node.left = minHeightBST1(arr, min, mid-1);
        node.right = minHeightBST1(arr, mid+1, max);
        return node;
    }


    public int findKthLargestValueInBst(BST tree, int k) {
        BST curr = tree;
        Stack<BST> stack = new Stack<>();
        int count = 0;
        while(curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.right;
            }
            BST node = stack.pop();
            count += 1;
            if(count == k) return node.value;
            curr = node.left;
        }
        return -1;
    }


    public static boolean validateBST(BST node, int min, int max){
        if(node == null) return true;
        if(node.value > min || max < node.value) return false;
        boolean left = validateBST(node.left, min, node.value);
        boolean right = validateBST(node.right, node.value, max);
        return left && right;
    }
    public static boolean validateBst(BST tree) {
        return validateBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


}



