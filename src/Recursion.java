import java.util.*;

public class Recursion {

    public String[] permutation(String str){
        int len = str.length();
        int arraysize =2;
        while(len-- == 0){

        }
        String [] permutations = new String[10];

        return permutations;
    }
}


class Sets{

    public List<List<Integer>> subset(int[] nums){
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> subset = new  ArrayList<>();
        subsets.add(subset);
        for(int i : nums){
            int len = subsets.size();
            for(int j=0; j<len; j++){
                subset = new ArrayList<>(subsets.get(j));
                subset.add(i);
                subsets.add(subset);
            }
        }
        return subsets;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums){

        HashSet<List<Integer>> subsets = new HashSet<>();
        List<Integer> subset = new ArrayList<>();

        //subset.add(3);
        subsets.add(subset);
        //Arrays.sort(nums);

        for(int i : nums){
            HashSet<List<Integer>> subset1 = new HashSet<>(subsets);
            for(List<Integer> temp : subset1){
                List<Integer> tmp = new ArrayList<>(temp);
                tmp.add(i);
                subsets.add(tmp);
            }
        }

        List<List<Integer>> ans = new ArrayList<>(subsets);
        return ans;

    }

    public List<List<Integer>> subsetsWithDup1(int[] nums){

        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        subsets.add(subset);
        return subsets;

    }


}

