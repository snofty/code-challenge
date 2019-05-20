import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the migratoryBirds function below.
    static int migratoryBirds(List<Integer> arr) {
Map<Integer, Integer> countTypes = new HashMap<>(arr.size());
        arr.forEach(a -> {
            if (countTypes.containsKey(a)) {
                int count = countTypes.get(a);
                countTypes.put(a, ++count);
            } else {
                countTypes.put(a, 1);
            }
        });
       
        int counts =
                countTypes.values().stream().max(Comparator.naturalOrder()).get();
        return countTypes.entrySet().stream().filter(e -> e.getValue()==counts).map(Map.Entry::getKey).collect(Collectors.toList()).stream().min(Comparator.naturalOrder()).get();


    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = migratoryBirds(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}