import java.util.*;

public class OneSolution{

    public static void main(String[] args) {

    }

    private void dynamicStack() {
        DynamicStack stack = new DynamicStack(5);
        stack.push(3);
        stack.push(31);
        stack.push(32);
        stack.push(312);
        stack.push(13);
        stack.printStack();
        stack.push(223);
        stack.printStack();
        stack.push(3);
        stack.push(31);
        stack.push(32);
        stack.push(312);
        stack.printStack();
        stack.push(13);
        stack.printStack();
        stack.pop();
        stack.printStack();
    }

    private void imclass() {
        ArrayList<ArrayList<Integer>> list1 = new ArrayList<>();
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1,2,3,5));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(11,12,13,15));
        ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(21,22,23,25));
        ArrayList<Integer> l4 = new ArrayList<>(Arrays.asList(31,32,33,35));
        ArrayList<Integer> l5 = new ArrayList<>(Arrays.asList(41,42,43,45));

        list1.add(l1);
        list1.add(l2);
        list1.add(l3);
        list1.add(l4);
        list1.add(l5);

        ImmutableClass im = new ImmutableClass(1, "Abhijeet", list1) ;

        ArrayList<ArrayList<Integer>> list2 = im.getList1();
        list2.add(new ArrayList<>());
        list2.get(1).add(9);
        System.out.println(list2);
        System.out.println(im.getList1());
        l3.add(566);
        System.out.println(im.getList1());
    }

    private void maxSal() {
        List<Emp> org = new ArrayList<>();
        org.add(new Emp(100,"Army","Joy",120000));
        org.add(new Emp(200,"Army","Jenny",90000));
        org.add(new Emp(300,"Mobile","Abhijeet",100000));
        org.add(new Emp(400,"Java","Ganesh",95000));
        org.add(new Emp(500,"Army","James",150000));

        HashMap<String, Emp> map = new HashMap<>();

        for(Emp e : org){
            if(map.get(e.project) ==  null){
                map.put(e.project, e);
            }
            else{
                Emp e1 = map.get(e.project);
                if(e1.salary < e.salary){
                    map.put(e.project, e);
                }
            }

        }

        for (String p : map.keySet()){
            Emp e = map.get(p);
            System.out.println(e.project + "  "+ e.empName +" "+e.salary);
        }
    }

}

class Emp{

    int empId;
    String project;
    String empName;
    int salary;

    public Emp(int empId, String project, String empName, int salary){
        this.empId = empId;
        this.project = project;
        this.empName = empName;
        this.salary = salary;
    }

}

final class  ImmutableClass{
    final private int var1;
    final private String str1;
    final private ArrayList<ArrayList<Integer>> list;

    public ImmutableClass(int var1, String str1, ArrayList<ArrayList<Integer>> list){
        this.var1 = var1;
        this.str1 = str1;
        this.list = new ArrayList<>();
        for(ArrayList<Integer> i : list){
            this.list.add((ArrayList<Integer>) i.clone());
        }
    }

    public int getVar1() {
        return var1;
    }

    public String getStr1() {
        return str1;
    }

    public ArrayList<ArrayList<Integer>> getList1() {

        ArrayList<ArrayList<Integer>> tempList = new ArrayList<>();
        for(ArrayList<Integer> i : this.list){
            tempList.add((ArrayList<Integer>) i.clone());
        }

        return tempList;
    }
}

//push pop clear size
class  DynamicStack{
    int capacity;
    private int[] stackArray;
    private int top;

    public DynamicStack(int size){
        this.top = 0;
        this.capacity = size;
        this.stackArray = new int[size];
    }

    public int size(){
        return this.top;
    }

    public boolean clear(){
        this.top = 0;
        return true;
    }

    public boolean isFull(){
        if(this.top == this.capacity)
            return true;
        else
            return false;
    }

    public boolean isEmpty(){
        if(this.top == 0)
            return true;
        else
            return false;
    }

    private void resize(){
        int doubleSize = this.capacity * 2;
        int[] newArray = new int[doubleSize];
        int index =0;
        for(int i : this.stackArray){
            newArray[index++] = i;
        }
        this.capacity = doubleSize;
        this.stackArray = newArray;
    }

    public void shrink(){
        int halfSize = this.capacity/2;
        int[] newArray = new int[halfSize];
        for(int i=halfSize-1; i>=0; i--){
            newArray[i] = this.stackArray[i];
        }
        this.stackArray = newArray;
        this.capacity = halfSize;

    }

    public boolean push(int val){
        if(isFull())
            this.resize();
        stackArray[this.top++] = val;
        return true;
    }

    public int pop(){
        int val = -1;
        if(isEmpty()){
            System.out.println("Array underflow");
        }
        else{
            --this.top;
            val = stackArray[this.top];
            stackArray[this.top] = 0;
            if(this.top == ((this.capacity/2))){
                this.shrink();
            }
        }
        return val;
    }

    public void printStack(){
        for(int i= this.stackArray.length-1; i>=0; i--)
            System.out.print(this.stackArray[i]+" ");
        System.out.println();
    }


}
