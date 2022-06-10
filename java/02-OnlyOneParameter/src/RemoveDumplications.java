import java.util.ArrayList;
import java.util.List;

public class RemoveDumplications {

    private List<String> list = null;

    public RemoveDumplications(List<String> in) {
        list = in;
    }

	public int removeDumplications(){

        List<String> ret = new ArrayList<>();
		                
		System.out.println("The List is  = "+list.toString());

        List<Integer> flag = new ArrayList<>();

        list.forEach((_item) -> {
            flag.add(0);
        });

		//The loop finds parameters that appear more than three times
		for(int i = 0;i<list.size();i++){
            if (flag.get(i) != 0) continue;

            List<Integer> sames = new ArrayList<>();

			for(int j =i+1;j<list.size();j++){
				if(list.get(i).equals(list.get(j))){
                    flag.set(i, flag.get(i)+1);
                    sames.add(j);
				}
			}

            for (Integer s : sames) {
                flag.set(s, flag.get(i));
            }
		}

        int siz = list.size();
        for (int i = 0; i < list.size(); i++) {
            //System.out.println(flag.get(i));
            if (flag.get(i) == 2) {
                list.remove(i);
                flag.remove(i);
                i--;
            }
        }

		System.out.println("list = "+list.toString());
		return list.size();
	}
	
	public static void main(String[] args) {
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
}


