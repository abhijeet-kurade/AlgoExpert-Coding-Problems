public class BinarySearchAlgoExpert {
    public static void main(String[] args) {
        BinarySearchAlgoExpert bsearch = new BinarySearchAlgoExpert();
        int[] nums = {1,3,4,6,8,12,16,23,56};
        System.out.println(bsearch.getGreaterOrEqual(nums, 8));

    }
    public int shiftedBSearch(int[] nums, int target){
        int len = nums.length;
        int start=0;
        int end=len-1;
        while(start<=end){
            int mid = start + ((end - start) / 2);
            System.out.print(start + " "+mid + " "+ end +" ");
            if(nums[mid] == target){
                return mid;
            }
            int sorted = nums[start] <= nums[mid] ? 0 : 1;
            System.out.println(sorted);
            if(sorted == 0){
                if(nums[start] <= target && target < nums[mid]){
                    end = mid - 1;
                }
                else {
                    start = mid + 1;
                }
            }
            if(sorted == 1){
                if(nums[mid] < target && target <= nums[end]){
                    start = mid + 1;
                }
                else{
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public int[] findRange(int[] nums, int target){
        int first = getGreaterOrEqual(nums, target);
        if(first == -1)
            return new int[] {-1, -1};
        int second = getGreaterOrEqual(nums, target+1);
        if(second == -1)
            return new int[] {first, nums.length -1};
        return new int[] {first, second-1};
    }
    public int getGreaterOrEqual(int[] nums, int target){
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        int temp = -1;
        while(start <= end){
            int mid = start + (end - start) / 2;
            if(target <= nums[mid]){
                temp = mid;
                end = mid -1;
            }
            else{
                start = mid + 1;
            }
        }
        return temp;
    }


    public int kthSmallest(int[] nums, int k){
        int len = nums.length;

        int start = 0;
        int end = len-1;

        int pivot = -1;

        while(pivot != k-1 && start<=end ){
            pivot = start;
            int left = start + 1;
            int right = end;
            while(left<right) {
                while (left < right && nums[left] <= nums[pivot]) {
                    left++;
                }
                while (right > left && nums[pivot] < nums[right]) {
                    right--;
                }
                if (left != right) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }
            }
            int temp = nums[left-1];
            nums[left-1] = nums[pivot];
            nums[pivot] = temp;
            pivot = left-1;
        }
        return -1;

    }
}

class BinarySearch{

    public int binarySearch(int[] nums, int target){
        int index = -1;
        int start = 0;
        int end = nums.length -1;
        int mid = start + (end - start) / 2;
        while(start < end){
            mid = start + (end - start) / 2;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] > target){
                end = mid;
            }
            else{
                start = mid+1;
            }
        }
        return -1;
    }

    public int binarySearchRecursive(int[] nums, int start, int end, int target){
        if(start > end) return -1;
        int indx = -1;
        int mid = start + (end - start) / 2;
        if(nums[mid] == target) return mid;
        else if(nums[mid] < target) start = mid+1;
        else end = mid-1;
        indx = binarySearchRecursive(nums, start, end, target);
        return indx;
    }

    public int equalMax(int[] nums, int target){
        int index = -1;
        int start = 0;
        int end = nums.length -1;
        int mid ;
        while(start <= end){
            mid = start + (end - start) / 2;
            if(nums[mid] >= target){
                index = mid;
                end = mid-1;
            }
            else{
                start = mid+1;
            }
        }
        return index;
    }

}
