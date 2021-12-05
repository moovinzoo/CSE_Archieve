import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

//sort by key
    //메인메소드에서 구현
public class Test2 {
    public static void main(String[] args) {
        Map<Double,Integer> hashMap = new HashMap<Double,Integer>();
        hashMap.put(1.1,99);
        hashMap.put(2.2,70);
        hashMap.put(13.3,89);
        hashMap.put(7.7,79);
        hashMap.put(10.1,99);
        TreeMap<Double,Integer> tm = new TreeMap<Double,Integer>(hashMap);

        //키값 오름차순 정렬(기본)
        Iterator<Double> iteratorKey = tm.keySet( ).iterator( );

        while(iteratorKey.hasNext()) {
            Double key = iteratorKey.next();
            System.out.println(key+","+tm.get(key));
        }
    }
}



