import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the getTotalX function below.
     */
    static int getTotalX(int[] a, int[] b) {
        /*
         * Write your code here.
         */
        long lowerLimit = 0;
        int count = 0;
        for(int ai:a){
            if(lowerLimit<ai){
                lowerLimit = ai;
            }
        }
        //System.out.println(lowerLimit);
        long upperLimit = Long.MAX_VALUE;
        for(int bi:b){
            if(upperLimit> bi){
               upperLimit = bi;
            }
        }
        //System.out.println(upperLimit);
        List<Long> multiples = getMultiples(lowerLimit,upperLimit);
        Set<Long> sets = new HashSet<Long>();
        for(Long mul:multiples){
            System.out.println(mul);
            for(int ai:a){
                if(mul%ai==0){
                    sets.add(mul);
                }else{
                    sets.remove(mul);
                    break;
                }
            }
        }
        Set<Long> finalSets = new HashSet<Long>();
        for(long set:sets){
            System.out.println(set);
            for(int bi:b){
                if(bi%set==0){
                    finalSets.add(set);
                }else{
                    finalSets.remove(set);
                    break;
                }
            }
        }
        return finalSets.size();
    }

    private static List<Long> getMultiples(Long num, Long limit){
        List<Long> multiples = new ArrayList<Long>();
        for(int i=1;;i++){
            long mul = num*i;
            if(mul >limit){
                break;
            }
            multiples.add(mul);
        }
        return multiples;
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scan.nextLine().split(" ");

        int n = Integer.parseInt(nm[0].trim());

        int m = Integer.parseInt(nm[1].trim());

        int[] a = new int[n];

        String[] aItems = scan.nextLine().split(" ");

        for (int aItr = 0; aItr < n; aItr++) {
            int aItem = Integer.parseInt(aItems[aItr].trim());
            a[aItr] = aItem;
        }

        int[] b = new int[m];

        String[] bItems = scan.nextLine().split(" ");

        for (int bItr = 0; bItr < m; bItr++) {
            int bItem = Integer.parseInt(bItems[bItr].trim());
            b[bItr] = bItem;
        }

        int total = getTotalX(a, b);

        bw.write(String.valueOf(total));
        bw.newLine();

        bw.close();
    }
}
