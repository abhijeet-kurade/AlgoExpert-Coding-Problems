public class Sorting {
    public static void main(String[] args) {
        int[] arr = new int[] {5,2,1,4,3,7,9};
        Sorting sort = new Sorting();
        //sort.quickSort(arr, 0, arr.length-1);
        sort.insertionSort(arr);
        for(int i : arr) System.out.print(i + " ");
    }

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void quickSort(int[] arr, int start, int end){
        if(start >= end) return;
        int pivot = start;
        int left = start +1;
        int right = end;

        while(left <= right){
            if(arr[left] > arr[pivot] && arr[right] < arr[pivot]){
                swap(arr, left, right);
                left += 1;
                right -= 1;
            }
            if(arr[left] <= arr[pivot]) left += 1;
            if(arr[right] >= arr[pivot]) right -= 1;
        }

        swap(arr, pivot, right);
        quickSort(arr, start, right -1);
        quickSort(arr, right+1, end);

    }

    public void selectionSort(int[] arr){
        int len = arr.length;

        for(int i =0; i<len; i++){
            int min = i;
            for(int j=i; j<len; j++){
                if(arr[min] >= arr[j]) min = j;
            }
            swap(arr, i, min);
        }
    }

    public void insertionSort(int[] arr){
        int len = arr.length;
        for(int i=1; i<len; i++){
            int ind = i;
            while(ind>0 && arr[ind] < arr[ind-1]) {
                swap(arr, ind, ind-1);
                ind -= 1;
            }
        }
    }

    public void threeNumberSort(int[] arr, int[] order){

        int len = arr.length;

        int num_one = order[0];
        int num_two = order[1];
        int num_three = order[2];

        int one = 0;
        int two = 0;
        int three = len;


        while(two < three){
            if(arr[two] == num_one)
                swap(arr, one++, two++);
            else if(arr[two] == num_two)
                two++;
            else if(arr[two] == num_three)
                swap(arr, two, --three);
        }

    }

}
