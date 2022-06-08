import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RemoveList {

    private List<String> list = null;
    public RemoveList(List<String> in) {
        list = in;
    }
	
	public int removeTarget(String parameter){
        if (list == null) return 0;
		
        //If they are equal, remove them
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            if(parameter.equals(it.next())){
                it.remove();
            }
        }

		System.out.println("list = "+list.toString());

		return list.size();
	}

	public static void main(String[] args) {
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
}        
    

