import java.util.Stack;

public class Stacks {

    public static void main(String[] args) {
        /*Stacks stacks = new Stacks();
        int[] array = {4,5,9,6,8,12,13,10,1};
        int[] result = stacks.getNextSmallerLeftToRight(array);
        System.out.println();
        for (int i : array){
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i : result){
            System.out.print(i + "  ");
        }
        result = stacks.getNextSmallerRightToLeft(array);
        System.out.println();
        for (int i : result){
            System.out.print(i + "  ");
        }*/

        Stacks stacks = new Stacks();

        int[] nums = new int[] {5, 3, 6, 1, 2, 4, 3, 11};
        System.out.println(stacks.sumSubArrayMins(nums));

    }
    public void printArray(int[] arr){
        int len = arr.length;
        System.out.print("{");
        for(int i=0; i<len; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.print("}");
        System.out.println();
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
    public int[] getNextSmallerRightToLeft(int[] array){
        int len = array.length;
        int[] nextMin = new int[len];
        Stack<Integer> stack = new Stack<>();
        for(int i=len-1; i>=0; i--){
            while(stack.size() != 0 && stack.peek() > array[i])
                stack.pop();
            if(stack.size() == 0){
                nextMin[i] = -1;
                stack.push(array[i]);
                continue;
            }
            nextMin[i] = stack.peek();
            stack.push(array[i]);
        }
        return nextMin;
    }
    public int[] getNextSmallerLeftToRight(int[] array){
        int len = array.length;
        int[] nextMin = new int[len];
        Stack<Integer> stack = new Stack<>();
        int index =0;
        while(index < len-1){
            System.out.print(index + "  ");
            if(array[index] < array[index+1]){
                stack.push(index);
                index++;
                continue;
            }
            nextMin[index] = array[index+1];
            while (stack.size() != 0 && array[stack.peek()] > array[index+1]){
                nextMin[stack.pop()] = array[index+1];
            }
            stack.push(++index);
        }
        while (stack.size() != 0){
            nextMin[stack.pop()] = -1;
        }
        return nextMin;
    }
    public int sumSubArrayMins(int[] nums){
        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];

        Stack<Integer[]> leftStack = new Stack<>();
        Stack<Integer[]> rightStack = new Stack<>();

        for(int i=0; i<len; i++){
            int cnt = 1;
            while(leftStack.size() != 0 && leftStack.peek()[0] > nums[i]){
                cnt += leftStack.peek()[1];
                leftStack.pop();
            }
            leftStack.push(new Integer[]{nums[i], cnt});
            left[i] = cnt;
        }
        for(int i=len-1; i>=0; i--){
            int cnt = 1;

            while(rightStack.size() != 0 && rightStack.peek()[0] >= nums[i]){
                cnt += rightStack.peek()[1];
                rightStack.pop();
            }

            rightStack.push(new Integer[]{nums[i], cnt});
            right[i] = cnt;

        }

        printArray(left);

        printArray(right);

        int result = 0;

        for(int i=0; i<len; i++)  result += (nums[i] * left[i] * right[i]);

        return result;
    }
    public int catchThieves(char[] arr, int k){

        Stack<Integer> policemen = new Stack<>();
        Stack<Integer> thieves = new Stack<>();

        int len = arr.length;

        for(int i=0; i<len; i++){
            if(arr[i] == 'P') policemen.add(i);
            else thieves.add(i);
        }
        int count = 0;
        while (policemen.size() != 0 && thieves.size() != 0){
            int police = policemen.peek();
            int theft = thieves.peek();

            if(Math.abs(police - theft ) <= k){
                count += 1;
                policemen.pop();
                thieves.pop();
            }
            else if(police > theft)  policemen.pop();
            else   thieves.pop();
        }
        return  count;
    }
}

class MinStack {
    // Feel free to add new properties and methods to the class.
    static class Item{
        int val;
        int min;
        int max;
        Item(int val, int min, int max){
            this.val = val;
            this.min = min;
            this.max = max;
        }
    }
    static class MinMaxStack {
        static Stack<Item> stack;
        int size;
        public MinMaxStack(){
            stack = new Stack<>();
            this.size = 0;
        }
        public int peek() {
            Item top = stack.peek();
            return top.val;
        }

        public int pop() {
            if (this.size == 0){
                System.out.println("Stack underflow");
                return -1;
            }
            Item top = stack.pop();
            this.size--;
            return top.val;
        }

        public void push(Integer number) {
            if(this.size == 0){
                stack.push(new Item(number,number,number));
                this.size++;
                return;
            }
            Item top = stack.peek();
            stack.push(new Item(number,Math.min(number, top.min),Math.max(number, top.max)));
            this.size++;
            return;
        }

        public int getMin() {
            if (this.size == 0){
                System.out.println("Stack is empty");
                return -1;
            }
            Item top = stack.peek();
            return top.min;
        }

        public int getMax() {
            if (this.size == 0){
                System.out.println("Stack is empty");
                return -1;
            }
            Item top = stack.peek();
            return top.max;
        }
    }
}

