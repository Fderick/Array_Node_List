package problem6_37;

public class TestDrive {
	public static void main(String[] args) {
		ANList<String> anl = new ANList<String>();
		ANList<String> anl2 = new ANList<String>();
		
		//Empty Add 
		System.out.println(anl.add("Scotty")); //Add on Empty List
		System.out.println(anl.add("Gabey"));
		System.out.println(anl);
		anl.add(0, "Danny");
		System.out.println(anl.size());
		
		anl.add(2,"Bobby");
		System.out.println(anl.toString() +" " + anl.size()); //Indexed add on 0th index. Also test size()
		
		anl2.add(0,"Scary");
		System.out.println(anl2); //Indexed add on 0th place in empty list
		System.out.println(anl2.remove(0)); //Indexed remove on list with one element
		System.out.println(anl2);
		
		anl.find("Stevey");
		System.out.println(anl.location + " " + anl.found); //Item not on list
		anl.find("Bobby");
		System.out.println(anl.location + " " + anl.found); //Item on list
		System.out.println(anl.get(2)); //Indexed get
		System.out.println(anl.get("Danny")); // Target get on first
		System.out.println(anl.get("Scotty")); //Target get other case
		System.out.println(anl.get("HippoScottamus")); //Target case inexistent
		
		System.out.println(anl.contains("Danny")); //Contains is basically found so I will omit test cases.
		
		anl.add("Faithy");
		anl.add("Miggy");
		System.out.println(anl);
		System.out.println(anl.remove("angel")); //non existent remove
		System.out.println(anl.remove("Faithy")); //Remove
		System.out.println(anl);
		anl.remove("Danny");
		anl.remove("Miggy");
		System.out.println(anl);
		
		//Indexed remove test
		anl.add("Faithy");
		anl.add("Miggy");
		anl2.add("Ape");
		System.out.println(anl);
		System.out.println(anl.remove(0));
		System.out.println(anl2.remove(0));
		System.out.println(anl.remove(2));
		System.out.println(anl + " " +anl2);

	
		System.out.println(anl2.isEmpty()); //Is empty test
		
		
		System.out.println(anl.set(0, "Scotty"));
		System.out.println(anl.set(2, "Amy"));
		System.out.println(anl);
		
		//indexOf test
		System.out.println(anl.indexOf("Gabey"));
		System.out.println(anl.indexOf("Scotty"));
		System.out.println(anl.indexOf("Scooty"));
		
		for (String item: anl) { //Iterator test
			System.out.println(item);
		}
		
	}
}
