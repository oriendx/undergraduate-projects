import FindAnagrams.*;
import RemoveDumplications.*;
import RemoveList.*;

import java.util.*;

public class Test {
    public static void testFindAnagrams() {
		System.out.println("Find Anagrams: ");
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

    public static void testRemoveDumpli() {
        List<String> list = new ArrayList<>();
		//Set parameters
		list.add("accordion");
		list.add("banjo");
		list.add("clarinet");
		list.add("banjo");
		list.add("clarinet");
		list.add("clarinet");
		list.add("BanJo");

		RemoveDumplications remove = new RemoveDumplications(list);
		int RemoveDumplicationsSize = remove.removeDumplications();
		System.out.println("RemoveDumplications = "+ RemoveDumplicationsSize);
    }

    public static void testRemoveTarget() {
        List<String> list = new ArrayList<>();
		
		//Set parameters
		list.add("abc");
		list.add("Abc");
		list.add("aBc");
		list.add("abC");
		list.add("ABC");
		
		RemoveList remove = new RemoveList(list);
		int ReMovetargetSize = remove.removeTarget("abc");
		System.out.println("RemoveList = "+ ReMovetargetSize);	
    }

    public static void main(String[] args) {
        testFindAnagrams();
        testRemoveDumpli();
        testRemoveTarget();
    }
}
