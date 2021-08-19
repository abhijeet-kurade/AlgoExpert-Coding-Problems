import java.util.*;

public class CollectionDemo {
    public static void main (String args[]){

        Model m1 = new Model(1, "ABK", 15);
        Model m2 = new Model(6, "Z", 53);
        Model m3 = new Model(8, "DU", 56);
        Model m4 = new Model(7, "H", 95);
        Model m5 = new Model(6, "D", 65);
        Model m6 = new Model(18, "ABKH", 543);
        Model m7 = new Model(19, "HR", 125);
        Model m8 = new Model(12, "ABK", 45);
        Model m9 = new Model(13, "B", 5);

        ArrayList<Model> arr = new ArrayList<>();
        arr.add(m1);
        arr.add(m2);
        arr.add(m3);
        arr.add(m4);
        arr.add(m5);
        arr.add(m6);
        arr.add(m7);
        arr.add(m8);
        arr.add(m9);

        for(Model m : arr) System.out.print( m.val +" ");
        System.out.println();
        Collections.sort(arr, new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return o1.val - o2.val;
            }
        });
        Model[] mdl = new Model[5];
        Arrays.sort(mdl);
        ArrayList<Model> arr1 = new ArrayList<>(arr);


        for(Model m : arr1) System.out.print( m.val +" ");

        Collections.sort(arr1, new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return o1.rate - o2.rate;
            }
        });
        System.out.println();
        for(Model m : arr1) System.out.print( m.rate +" ");
        
    }
}

class Model {
    int val;
    String descp ;
    int rate;

    public Model(int val, String descp, int rate) {
        this.val = val;
        this.descp = descp;
        this.rate = rate;
    }
}
class ArrayLists{




    public int[] arrayListToInt(ArrayList<Integer> arrayList){
        return arrayList.stream().mapToInt(i->i).toArray();
    }

    public ArrayList<Integer> intToArrayList(int[] arr){
        return new ArrayList<>() {{for(int i : arr) add(i);}};
    }

}
class HashMaps{

}