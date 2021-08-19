

import com.sun.source.tree.WhileLoopTree;

import java.lang.reflect.Parameter;
import java.util.*;



public class Algo {

    public static void main(String[] args) throws Exception{

        /*Algo algo = new Algo();
        List<Integer> arr = new LinkedList<>();

        arr.add(4);
        arr.add(5);
        arr.add(14);
        arr.add(65);
        arr.add(41);


        System.out.println(arr);
        arr.add(1,56);
        arr.remove(arr.size()-1);
        arr.remove(arr.size()-1);
        System.out.println(arr);*/

        Algo algo = new Algo();
    }

    public void sortObjectClass(){
        List<Word> words = new ArrayList<>();
        String[] strs = new String[]{"zx", "xza", "abk", "kba", "obj", "jbo", "object", "jectob", "bak"};

        for (String str : strs) words.add( new Word(str));

        Collections.sort(words, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                int lim= Math.min(o1.str1.length(), o2.str1.length());
                int k=0;
                while(k<lim) {
                    if(o1.str1.charAt(k)!= o2.str1.charAt(k)) {
                        return (int) o1.str1.charAt(k) - o2.str1.charAt(k);
                    }
                    k++;
                }
                return o1.str1.length() - o2.str1.length();
            }
        });

        for(Word word : words) System.out.print( word.toString() );



    }

    public String runLengthCoding(String input){
        ArrayList<Character> code = new ArrayList<>();
        char prev ='0';
        int len = input.length();
        for(int i=0; i<len; i++){
            if(i%2 == 0){
                prev = input.charAt(i);
            }
            else {
                int repeat = Integer.parseInt(String.valueOf(input.charAt(i)));
                for(int j=0; j<=repeat;j++){
                    code.add(prev);
                }
            }
        }

        char[] chars = new char[code.size()];
        for(int i=0; i<code.size(); i++){
            chars[i] = code.get(i);
        }

        return String.valueOf(chars);
    }

    public int maximumSwap(int num) {
        StringBuilder str = new StringBuilder(String.valueOf(num));

        int len = str.length();
        int first = -1;
        int second = -1;
        for(int i = 0; i<len-1; i++){
            outerloop:
            for(int j=i+1; j<len; j++){
                if(str.charAt(i) < str.charAt(j)){
                    first = i;
                    second = j;
                    break outerloop;
                }
            }
        }

        if(first != -1){
            char temp = str.charAt(first);
            str.setCharAt(first, str.charAt(second));
        }

        return Integer.parseInt(str.toString());

    }

    public List<List<Integer>> pascalTriangle(int n){
        List<List<Integer>> triangle = new ArrayList<>();
        if(n == 0) return triangle;
        List<Integer> row = new ArrayList<>();
        row.add(1);
        triangle.add(row);

        for(int i = 1; i<n; i++){
            int newLen = triangle.get(i-1).size();
            List<Integer> lastRow = triangle.get(i-1);
            List<Integer> newRow = new ArrayList<>();
            newRow.add(1);
            for (int j =1; j<=newLen -1; j++){
                newRow.add(lastRow.get(j) + lastRow.get(j-1));
            }
            newRow.add(1);
            triangle.add(newRow);
        }
        return triangle;
    }

    public void doorQuestion(){
        int n =100;
        int[] arr = new int[n];


        for(int i=1; i<n; i++){
            for(int j=i; j<n; j=j+i){
                arr[j] = arr[j] == 1 ? 0 : 1;
            }
        }
        for(int i=0; i<n; i++) System.out.print(  arr[i] + " ");

    }

}


class Printer{
    public static void main1(String[] args) {


    }
    synchronized public  void  printDoc(int copies, String doc){
        for(int i=1; i<=copies; i++){
            System.out.println("Printing "+doc+" "+i);
        }
    }
}

class Thread1 extends Thread{
    Printer p;
    int copies;
    String doc;

    Thread1(Printer p, int copies, String doc){
        this.p = p;
        this.copies = copies;
        this.doc = doc;
    }
    public void run() {
        p.printDoc(this.copies, this.doc);
    }
}

class Thread2 extends Thread{
    Printer p;
    String doc;

    Thread2(Printer p,  String doc){
        this.p = p;
        this.doc = doc;
    }
    public void run() {
        p.printDoc(10, this.doc);
    }
}
