import java.util.*;

public class Miscellaneous {


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
}

class Word {
    String str1;
    String str;

    public Word(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        this.str1 = String.valueOf(chars);
        this.str = str;
    }

    @Override
    public String toString() {
        return "{"  + this.str1 + ":" + this.str +"} " ;
    }
}
class Iterator {
    int[][] array;
    private int index1 =-1;
    private int index2 =-1;

    public Iterator(int[][] array){
        this.array = array;
        for(int i = 0; i < this.array.length ; i++){
            if(this.array[i].length > 0){
                index1 = i;
                index2 = 0;
                break;
            }
        }
    }

    public boolean hasNext(){
        if(index1 == -1)
            return false;
        else return true;
    }

    public int next(){

        if(index1 == -1) return -1;
        int val = this.array[index1][index2];
        boolean brkouter = false;

        int temp1 = index1;
        int temp2 = index2;

        for(int i = index1; i< this.array.length; i++){
            int indx2 =-1;
            if(i == index1) indx2 = index2;
            for (int j = indx2+1; j < this.array[i].length; j++){
                this.index1 = i;
                this.index2 = j;
                brkouter = true;
                break;
            }
            if(brkouter) break;
        }

        if(temp1 == index1 && temp2 == index2){
            this.index1 = -1;
            this.index2 = -1;
        }

        return val;
    }

    public boolean remove(){

        if(index1 == -1) return false;

        int temp_itr1 = index1;
        int temp_itr2 = index2;

        if(index2 == array[index1].length-1) this.next();

        int[] newArray = new int[this.array[temp_itr1].length - 1];
        int newIndex = 0;
        for(int i = 0; i<this.array[temp_itr1].length; i++){
            if(i == temp_itr2) continue;
            newArray[newIndex++] = this.array[index1][i];
        }
        System.out.print("{" + this.array[temp_itr1][temp_itr2] +" has removed.}");
        this.array[temp_itr1] = newArray;
        return true;
    }

}

