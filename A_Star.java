//import java.io.*;
import java.util.*;

// Please check the parent of start node

public class A_Star {

	class ElementPriority implements Comparable<ElementPriority> {
	    String element;
	    int path_cost;
	    int time ;

	    @Override public int compareTo(ElementPriority other) {
	      //  return Integer.compare(this.path_cost, other.path_cost);
	    	int res = Integer.compare(this.path_cost, other.path_cost);
	    	if (other.path_cost == this.path_cost)
	    	{
	    	       res = (time < other.time ? -1 : 1);
	    	    //   System.out.println("Comparing time");
	    	} 
	    	return res;
	    }
	}
	
	
	
	
	
	String STARTNODE ;
	String GOALNODE;
	
	
	public A_Star(String STARTNODE , String GOALNODE)
	{
		this.STARTNODE = STARTNODE;
		this.GOALNODE = GOALNODE;
		
	}

	public void compute_Astar()
	{
		PriorityQueue<ElementPriority> frontier = new PriorityQueue<>();
		PriorityQueue<ElementPriority> temp_queue = new PriorityQueue<>();

		int mod_flag = 0;
		ElementPriority cur = new ElementPriority();
		LinkedHashMap<String, Integer> pathcost = new LinkedHashMap<>();
		
		ArrayList<String> explored = new ArrayList<> ();
		
		
		HashMap<String, String> parentLists = new HashMap<String,String >();
		ElementPriority modified_item = new ElementPriority();
		int counter =0;
		
		
		pathcost.put(STARTNODE,0);
	
		homework.hyuristic_map.put(STARTNODE,0);
	//	System.out.println(homework.hyuristic_map);
	
		ElementPriority EP = new ElementPriority();
		EP.element = STARTNODE;
		EP.path_cost = 0;
		EP.time = 0;
		frontier.add(EP);
		//explored.add(EP.element);
		parentLists.put(EP.element,new String(EP.element));
	//	String temp = new String();
	//	temp = "";
		while (!frontier.isEmpty())
		{
			
			/*	System.out.println("dddddd");
				for (ElementPriority item : frontier){
					System.out.println(item.element + "  " + item.path_cost);
				
			} */
			ElementPriority Current = frontier.remove();
			
		//	temp = Current.element;
			explored.add(Current.element);
			pathcost.put(Current.element,(Current.path_cost - homework.hyuristic_map.get(Current.element)));
	
			if ( Current.element.equals(this.GOALNODE))
			{
			//	explored.add(Current.element);
			
				String child = Current.element;
				List<String> path = new ArrayList<String>();
				while (!parentLists.get(child).equals(child))
				{
					path.add(child);
					
					child = parentLists.get(child);
				}
				path.add(child);
				Collections.reverse(path);
				
				for (String route : path)
				{
					System.out.println(route +" "+pathcost.get(route));
				}
				
			
				return;
				
			}
			
			else
			{
			
				if ( homework.adjLists.get(Current.element)== null)
					continue;
				else 
				{
				
				/*	if(Current.element.equals("3")){
						System.out.println("children :" + homework.adjLists.get(Current.element).keySet() + "\n" + "frontier:\n");
						
						for (ElementPriority item : frontier){
							System.out.println(item.element + "  " + item.path_cost);
						}
						
					} */
					for ( String itr :homework.adjLists.get(Current.element).keySet() )
					{
					/*	if(Current.element.equals("3")){
						System.out.println("parent: "+ Current.element + "  child :" + itr);
						} */
						int flag = 0;
						for (ElementPriority item : frontier){
							if (item.element.equals(itr))
								flag = 1;
						}
						
						
						if (!explored.contains(itr) && flag == 0)
						{
							ElementPriority itr_child = new ElementPriority();
							itr_child.element = itr;
							int child_cost = homework.adjLists.get(Current.element).get(itr).get(0);
							itr_child.path_cost = Current.path_cost + child_cost - homework.hyuristic_map.get(Current.element) + homework.hyuristic_map.get(itr)   ;
						//	System.out.println("par " + parentLists.get(Current.element));
						/*	if( parentLists.get(Current.element).equals("3")){
								System.out.println("sss" + itr_child.element +" "+itr_child.path_cost);
							} */
							
							itr_child.time = counter+1 ;
							frontier.add(itr_child);
						parentLists.put(itr_child.element,new String(Current.element));
					
						
						}
						
						if (!explored.contains(itr)&& flag == 1)
						{
						//	int childcost = homework.adjLists.get(Current.element).get(itr).get(0) +Current.path_cost - homework.hyuristic_map.get(parentLists.get(Current.element)) + homework.hyuristic_map.get(itr);
						/*	if(Current.element.equals("3") && (itr.equals("28") || itr.equals("9"))){
								System.out.println(" cost :" + childcost);
							} */
							// testing below line
							
							
							//delete above lines
						for (ElementPriority item : frontier)
						{
							if (item.element.equals(itr))
							{
								if (item.path_cost > homework.adjLists.get(Current.element).get(itr).get(0) +Current.path_cost - homework.hyuristic_map.get(Current.element) + homework.hyuristic_map.get(itr)){
									
								//	frontier.remove(item);
									item.path_cost = homework.adjLists.get(Current.element).get(itr).get(0) +Current.path_cost - homework.hyuristic_map.get(Current.element) + homework.hyuristic_map.get(itr);
								//	frontier.add(item);
									modified_item = item;
									mod_flag = 1;
								//	System.out.println("Modifing "+item.element);
									parentLists.put(item.element, new String(Current.element));
								}
							}
						}
						if(mod_flag==1)
						 {
						/*	System.out.println("Contents of queue before modification");
							for(ElementPriority ele : frontier)
							{
								System.out.println(ele.element +" "+ele.path_cost);
							} */
						  cur = frontier.remove();
					/*	  System.out.println("Contents of queue after modification and removing first element");
							for(ElementPriority ele : frontier)
							{
								System.out.println(ele.element +" "+ele.path_cost);
							} */
						  temp_queue.add(cur);
						  if (cur.element.equals(modified_item.element))
						  {
							  frontier.add(temp_queue.remove());
							  mod_flag=0;
							  
						  }
						  while(!cur.element.equals(modified_item.element))
						   {
						//	  System.out.println("Length "+frontier.size());
							cur = frontier.remove();
							temp_queue.add(cur);
						   }
						  if(cur.element.equals(modified_item.element) && mod_flag !=0 )
						  {
							//  temp_queue.add();
							  mod_flag=0;
						  }
						  while(!temp_queue.isEmpty())
							  frontier.add(temp_queue.remove());
						 } 
						
					/*	System.out.println("Final Content");
						for(ElementPriority ele : frontier)
						{
							System.out.println(ele.element +" "+ele.path_cost);
						} */
						
						}
						
					} 
				}
			
					
			}
			//if(!explored.contains(Current.element))
			//	explored.add(Current.element);
	/*		System.out.println("Explored Queue");
			for(String itr : explored)
			{
				System.out.print(itr +" ");
			}
			System.out.println(" "); */
		}
		
	}
	
}
