import java.util.ArrayList;
import java.util.Arrays;

public class InterviewBit {

    public static void main(String[] args) {

        InterviewBit ib = new InterviewBit();
        ArrayList<String> list= new ArrayList<>(Arrays.asList("XOX", "OOO", "XOX"));
        System.out.println(ib.blackShapes(list));


    }
    public int blackShapes(ArrayList<String> list){
        ArrayList<StringBuilder> list1 = new ArrayList<>();
        for(String str : list){
            list1.add(new StringBuilder(str));
        }
        int shapes = 0;
        int array_len = list1.size();
        int str_len = list1.get(0).length();
        for(int i=0; i<array_len; i++){
            for(int j=0; j<str_len; j++){
                if(list1.get(i).charAt(j) == 'X'){
                    shapes++;
                    mark_shape(list1, i, j);
                }
            }
        }
        return shapes;
    }
    public void mark_shape(ArrayList<StringBuilder> list, int i, int j){
        if(list.get(i).charAt(j) == 'O') return;

        list.get(i).setCharAt(j,'O');

        if(i>0) mark_shape(list, i-1, j); //up
        if(i<list.size()-1)mark_shape(list, i+1, j); //down
        if(j>0) mark_shape(list, i, j-1); //left
        if(j<list.get(i).length()-1)mark_shape(list, i, j+1); //right
    }

}
