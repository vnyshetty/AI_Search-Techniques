//import java.io.*;
import java.util.*;




public class UCS_Test {
	
	class ElementPriority implements Comparable<ElementPriority> {
	    String element;
	    int path_cost;
	    int time ;

	    @Override public int compareTo(ElementPriority other) {
	       // return Integer.compare(this.path_cost, other.path_cost);
	    //	System.out.println ("Comparing "+this.element +" and " + other.element);
	    //	return (this.path_cost < other.path_cost ? -1
	    //            : (this.path_cost == other.path_cost ? 0 : 1));
	    	//int res = Integer.compare(this.path_cost, other.path_cost);
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
	
	
	public UCS_Test(String STARTNODE , String GOALNODE)
	{
		this.STARTNODE = STARTNODE;
		this.GOALNODE = GOALNODE;
		
	}

	public void compute_ucs()
	{
		PriorityQueue<ElementPriority> frontier = new PriorityQueue<>();
		PriorityQueue<ElementPriority> temp_queue = new PriorityQueue<>();
		
		LinkedHashMap<String, Integer> pathcost = new LinkedHashMap<>();
		
		ArrayList<String> explored = new ArrayList<> ();
		
		
		HashMap<String, String> parentLists = new HashMap<String,String >();
		
		int mod_flag = 0;
		ElementPriority cur = new ElementPriority();
		ElementPriority modified_item = new ElementPriority();
		
		 int counter =0;
		
		
		pathcost.put(STARTNODE,0);
	
		ElementPriority EP = new ElementPriority();
		EP.element = STARTNODE;
		EP.path_cost = 0;
		EP.time = 0;
		frontier.add(EP);
	//	explored.add(EP.element);
		parentLists.put(EP.element,new String(""));
		
		while (!frontier.isEmpty())
		{
			
			ElementPriority Current = frontier.remove();
			explored.add(Current.element);
		//	System.out.println(Current.element);
			pathcost.put(Current.element,Current.path_cost);
		
			if ( Current.element.equals(this.GOALNODE))
			{
			//	explored.add(Current.element);
			
				String child = Current.element;
				List<String> path = new ArrayList<String>();
				while (!parentLists.get(child).isEmpty())
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
				
					
					for ( String itr :homework.adjLists.get(Current.element).keySet() )
					{
					
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
							itr_child.path_cost = Current.path_cost + child_cost;
							itr_child.time = counter+1 ;
							frontier.add(itr_child);
							//Added later
					//		System.out.println(frontier.iterator());
					/*		System.out.println("Contents of queue");
							for (ElementPriority i : frontier)
							{
								System.out.println(i.element);
							}
							*/
							//original
						//	System.out.println("Added : "+itr_child.element);
						//	System.out.println("Path Cost is : "+itr_child.path_cost);
						parentLists.put(itr_child.element,new String(Current.element));
					
						
						}
						 
						if (!explored.contains(itr)&& flag == 1)
						{
						for (ElementPriority item : frontier)
						{
							if (item.element.equals(itr))
							{
								if (item.path_cost > homework.adjLists.get(Current.element).get(itr).get(0) +Current.path_cost){
								
									item.path_cost = homework.adjLists.get(Current.element).get(itr).get(0) +Current.path_cost;
									modified_item = item;
									mod_flag = 1;
									parentLists.put(item.element, new String(Current.element));
								}
							}
						}
						//adding now
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
						
						
						}
						
					} 
				}
			
					
			}
		//	if(!explored.contains(Current.element))
		//		explored.add(Current.element);
		}
		
	}
	
}
