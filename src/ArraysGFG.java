import java.util.*;

public class ArraysGFG {

    public static void main(String[] args) {

        ArraysGFG arrays = new ArraysGFG();

        int[] arr = new int[]{4, 2, 17, 10, 9};
        int[] dept = new int[]{8, 5, 20, 21, 10};

        System.out.println(arrays.countTriplet1(arr, arr.length));

        ArraY arraY = new ArraY();

        System.out.println(arraY.minRequiredPlatform(arr, dept));



    }


    public void printArray(int[] nums){
        for (int i: nums)
            System.out.print(i + " ");
        System.out.println();
    }
    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public int quickSelect(int[] nums, int start, int end, int m){

        while(true){
            if(start > end) return -1;
            int pivot = start;
            int left = start + 1;
            int right = end;

            while(left <= right){
                if(nums[left] > nums[pivot] && nums[right] < nums[pivot]){
                    swap(nums, left, right);
                    left += 1;
                    right -= 1;
                }
                if(nums[left] <= nums[pivot]) left += 1;
                if(nums[right] >= nums[pivot]) right -= 1;
            }

            swap(nums, pivot, right);
            if(right == m) return nums[right];
            else if(right < m) start = right+1;
            else end = right -1;
        }


    }

    public int longestPeak(int[] array) {
        int len = array.length;
        int index=1;
        int maxPeak=0;

        while(index<len-1){
            boolean isThisPeak = (array[index-1] < array[index]) && (array[index] > array[index+1]);
            if(isThisPeak){
                System.out.println("Peak at " + index);
                int peak = index;
                int currentPeakStart= index-1;
                while(currentPeakStart >=0 && array[index]>array[currentPeakStart]){
                    currentPeakStart--;
                    index--;
                }
                currentPeakStart = index;
                index = peak;
                int currentPeakEnd = index+1;
                while(currentPeakEnd < len && array[index]>array[currentPeakEnd]){
                    currentPeakEnd++;
                    index++;
                }
                currentPeakEnd = index;
                System.out.println(currentPeakEnd + " "+ currentPeakStart);
                maxPeak = Math.max(maxPeak, (currentPeakEnd - currentPeakStart) + 1);
                index = currentPeakEnd;
            }
            index++;
        }

        return maxPeak;
    }


    public int[] sort0121(int[] nums){
        int zero = 0;
        int one = 0;
        int two = nums.length - 1;
        int count=0;
        while(one <= two){
            count++;
            if(nums[one] == 1){
                one++;
                continue;
            }
            if(nums[one] == 0){
                int temp = nums[zero];
                nums[zero] = nums[one];
                nums[one] = temp;
                    zero++;
                    one++;
            }
            if(nums[one] == 2){
                int temp = nums[one];
                nums[one] = nums[two];
                nums[two] = temp;
                two--;
            }
        }
        System.out.println(count);
        return nums;
    }

    public int[] sort012(int[] nums){
        int zero = 0;
        int one = 0;
        int two = nums.length - 1;
        int count=0;
        while(one < two && zero <= one){
            count++;
            if(nums[two] == 2){
                two--;
                continue;
            }
            if(nums[two] == 0){
                int temp = nums[zero];
                nums[zero] = nums[two];
                nums[two] = temp;
                if(zero == one){
                    zero++;
                    one++;
                }
                else{
                    zero++;
                }
            }
            if(nums[two] == 1){
                int temp = nums[one];
                nums[one] = nums[two];
                nums[two] = temp;
                one++;
            }
        }
        System.out.println(count);
        return nums;
    }

    public ArrayList<Integer> subarraySum(int[] arr, int n, int s){

        ArrayList<Integer> subarray = new ArrayList<>();
        subarray.add(-1);
        subarray.add(-1);

        int len = n;//arr.length;
        int left=0, right=0;
        int sum =arr[0];
        while (right<len && left<=right){
            System.out.println(left + " "+ right +" "+sum);
            if(sum > s && left == right && right<len-1){
                left++; right++;
                sum = arr[right];
                continue;
            }
            else if(sum > s){
                sum -= arr[left];
                left++;
                continue;
            }
            else if(sum < s && ++right<len ){
                //right++;
                sum += arr[right];
                continue;
            }
            else if(sum == s ){
                subarray.set(0, left+1);
                subarray.set(1,right+1);
                break;
            }
        }
        if(subarray.get(1) ==-1)
            subarray.remove(1);


        return subarray;
    }
    public ArrayList<Integer> subarraySum2(int[] arr, int n, int s){
        ArrayList<Integer> subarray = new ArrayList<>();
        int start=0, end=0;
        int sum =arr[start];
        while (end<n && start<=end){
            if(sum > s){
                if(start == end){
                    start++; end++;
                    if(end<n)
                        sum = arr[end];
                }
                else{
                    sum -= arr[start++];
                }
            }
            else if(sum<s){
                end++;
                if(end<n)
                    sum += arr[end];
            }
            else if(sum == s ){
                subarray.add(start+1);
                subarray.add(end+1);
                break;
            }
        }
        if(subarray.size() == 0)
            subarray.add(-1);


        return subarray;
    }
    public ArrayList<Integer> subarraySum1(int[] arr, int n, int s){
        ArrayList<Integer> subarray = new ArrayList<>();
        int start = 0;
        int sum = arr[0];
        for(int i=1; i<n; i++){
            while (sum>s && start<i){
                sum -= arr[start++];
            }
            if(sum == s){
                subarray.add(start+1);
                subarray.add(i+1);
                break;
            }
            if(i<n)
                sum += arr[i];
        }
        if(subarray.size() == 0)
            subarray.add(-1);
        return subarray;
    }

    int countTriplet(int[] arr, int n) {
        int count =0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                int sum = arr[i]+arr[j];
                if(map.get(sum)==null){
                    map.put(sum,1);
                }
                else{
                    map.put(sum, map.get(sum)+1);
                }
            }
        }
        for(int i=0; i<n; i++){
            if(map.get(arr[i])!=null){
                count += map.get(arr[i]);
            }
        }
        return count;
    }
    int countTriplet1(int arr[], int n) {
        int count =0;
        Arrays.sort(arr);
        for(int i=n-1; i>=2; i--){
            int target = arr[i];
            int start = 0;
            int end = i-1;
            while(start<end){
                int sum = arr[start] + arr[end];
                if(sum<target){
                    start++;
                }
                else if(sum>target){
                    end--;
                }
                else{
                    count++;
                    start++;end--;
                }
            }

        }

        return count;
    }

    public void mergeArraysWithoutExtraSpace(int[] arr1, int[]arr2){
        //O(n*logn + m*logm)
        for(int z : arr1)
            System.out.print(z +" ");
        for(int z : arr2)
            System.out.print(z +" ");
        System.out.println();

        int i=0, j=0, k= arr1.length-1;
        while(i<k && j< arr2.length-1){
            if(arr1[i] < arr2[j]){
                i++;
            }
            else{
                int temp = arr1[k];
                arr1[k] = arr2[j];
                arr2[j] = temp;
                j++;
                k--;
            }
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for(int z : arr1)
            System.out.print(z +" ");
        for(int z : arr2)
            System.out.print(z +" ");
    }

}

class ArraY{
    public static void main(String[] args) {

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return -1;
    }

    public int minRequiredPlatform(int[] arr, int[] dept){

        Arrays.sort(arr);
        Arrays.sort(dept);
        int len = arr.length;

        int platforms = 0;
        int maxPlatforms = 0;
        int i=0;
        int j=0;

        while(i<len){
            if(arr[i] < dept[j]){
                platforms += 1;
                maxPlatforms = Math.max(platforms, maxPlatforms);
                i += 1;
            }
            else{
                j += 1;
                platforms -= 1;
            }
        }

        return maxPlatforms;
    }
}
