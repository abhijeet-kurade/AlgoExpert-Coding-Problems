import java.util.*;

public class Shikha {
    public static void main(String[] args) {
        int n = (int) -1e8;
        System.out.println(Integer.MIN_VALUE - 1);
    }

    public static List<Long> gbs(List<String> s, int d, List<Integer> k, List<Integer> m){
        s.sort(String::compareToIgnoreCase);
        List<Long> output = new ArrayList<>();
        for(int i=0; i<k.size(); i++){
            int kk = k.get(i);
            int mm= m.get(i);
            output.add(Long.parseLong(s.get(kk-1).substring(0, mm)));
        }
        return output;
    }

    public static int gss(List<List<Character>> c, int k){
        int row = c.size();
        int col = c.get(0).size();
        int maxTotal =0;
        for(int i=0; i<col; i++){
            int total = 0;
            for(int j=i; j<col; j++){
                if(j-i > k) continue;
                for(int f=0; f<row; f++){
                    if(c.get(f).get(i) =='.') continue;
                    for(int s=0; s<row; s++){
                        if(c.get(s).get(j)=='.') continue;
                        int strength = Math.abs(i-j)+ Math.abs(f-s);
                        total += strength;
                    }
                }
            }
            maxTotal = Math.max(maxTotal, total);
        }
        return maxTotal;
    }

    public static int gd(List<Integer> e, List<Integer> r){
        int n= e.size();
        for(int i=0; i<n; i++){
            if(e.get(i) <= r.get(i)) continue;
            int temp = e.get(i);
            e.set(i, r.get(i));
            r.set(i, temp);
        }
        Collections.sort(e);
        Collections.sort(r);
        int i =0, j=0;
        int maxCount =0, count =0;

        while (i<n && j<n){
            if(e.get(i)<=r.get(j)){
                count += 1;
                maxCount = Math.max(maxCount, count);
                i += 1;
            }
            else {
                count -= 1;
                j += 1;
            }
        }
        return maxCount;
    }

    public static int numOfOperations(String s){
        int n = s.length();
        int last1Idx = -1;
        for(int i=n-1; i>=0; i--){
            if(s.charAt(i) =='1') last1Idx = i;
        }
        int ones = 0, zeros =0;
        for(int i=n-1; i>=last1Idx; i--){
            if(s.charAt(i) =='1') ones+=1;
            else zeros += 1;
        }
        return zeros + (2*ones) - 1;
    }

    public static int setBitsAfterProduct(int A, int B){
        long answer = (long)A * (long)B;
        /*long answer = 0;
        int num1 = A, num2=B;
        int count =0;
        while(num2 != 0){
            if((num2 & 1) == 1){
                long temp = num1;
                temp <<= count;
                answer += temp;
            }
            count += 1;
            num2 >>= 1;
        }*/
        int setBits = 0;
        while (answer != 0) {
            answer &= (answer - 1);
            setBits++;
        }
        return setBits;
    }
}
