import java.util.*;

public class AlgoExpert {


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


