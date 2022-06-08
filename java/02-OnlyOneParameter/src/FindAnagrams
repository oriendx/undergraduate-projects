import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class FindAnagrams {
    private List<String> list = null;

    public FindAnagrams(List<String> in) {
        list = in;
    }

    public List<List<String>> findAnagrams (){
        Map<String, ArrayList<String>> anaMap = new HashMap<>() ;
        int flag[] = new int[list.size()];

        for (int i=0; i< list.size(); i++) {
            if (flag[i] == 1) continue;

            for (int j = i+1; j < list.size(); j++) {
                if (flag[j] == 1) continue;

                // Judge whether the length of parameters is equal 
                if (list.get(i).length() == list.get(j).length() && !list.get(i).equals(list.get(j))) {

                    // Convert parameters to character arrays
                    char[] listparam = list.get(i).toCharArray();
                    char[] isAnagram = list.get(j).toCharArray();
                    // Sort character arrays
                    Arrays.sort(listparam);
                    Arrays.sort(isAnagram);
                    // Convert sorted character arrays to String type
                    // comparisons
                    if (String.copyValueOf(listparam).equals(
                                String.copyValueOf(isAnagram))) {
                        if (anaMap.containsKey(list.get(i))) {
                            anaMap.get(list.get(i)).add(list.get(j));
                        } else {
                            ArrayList<String> value = new ArrayList<>();
                            value.add(list.get(j));
                            anaMap.put(list.get(i), value);
                        }
                        flag[i] = flag[j] = 1; // mark as selected
                    }
                }
            }
        }
        List<List<String>> ret = new ArrayList<>();

        anaMap.keySet().stream().map((String key) -> {
            List<String> items = new ArrayList<>();
            items.add(key);
            items.addAll(anaMap.get(key));
            return items;
        }).forEachOrdered((List<String> items) -> {
            ret.add(items);
        });

        return ret;
    }

    public static void main(String[] args) {
        List<String> t = new ArrayList<>();
        t.add("tea");
        t.add("eat");
        t.add("ate");
        t.add("acde");
        t.add("cdf");
        t.add("adec");
        t.add("cdf");
        FindAnagrams findanagrams = new FindAnagrams(t);
        List<List<String>> ret = findanagrams.findAnagrams();
        ret.forEach((lst) -> {
            System.out.println(lst.toString());
        });
    }
}
