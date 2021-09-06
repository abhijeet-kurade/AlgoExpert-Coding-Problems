import java.util.*;
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
    
}


