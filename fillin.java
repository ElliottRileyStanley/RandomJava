import java.util.ArrayList;
import java.util.Arrays;

public class fillin {
    public static void main(String[] args) {
        
        ArrayList<Integer> first = new ArrayList<Integer>(Arrays.asList(0, 4));
        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(4);
        second.add(5);
        ArrayList<Integer> combined = new ArrayList<Integer>();
        for(Integer number : first ) {
            if (second.contains(number)) {
                combined.add(number);
            }
        }
        System.out.println(combined);
    }
}