import java.util.*;

public class A_Star {

	class ElementPriority implements Comparable<ElementPriority> {
	    String element;
	    int path_cost;
	    int time ;

	    @Override public int compareTo(ElementPriority other) {
	      
	    	int res = Integer.compare(this.path_cost, other.path_cost);
	    	if (other.path_cost == this.path_cost)
	    	{
	    	       res = (time < other.time ? -1 : 1);
	    	    
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
	
	
		ElementPriority EP = new ElementPriority();
		EP.element = STARTNODE;
		EP.path_cost = 0;
		EP.time = 0;
		frontier.add(EP);
		
		parentLists.put(EP.element,new String(EP.element));
	
	
		while (!frontier.isEmpty())
		{
			
			
			ElementPriority Current = frontier.remove();
			
		
			explored.add(Current.element);
			pathcost.put(Current.element,(Current.path_cost - homework.hyuristic_map.get(Current.element)));
	
			if ( Current.element.equals(this.GOALNODE))
			{
			
			
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
							itr_child.path_cost = Current.path_cost + child_cost - homework.hyuristic_map.get(Current.element) + homework.hyuristic_map.get(itr)   ;
						
							
							itr_child.time = counter+1 ;
							frontier.add(itr_child);
						parentLists.put(itr_child.element,new String(Current.element));
					
						
						}
						
						if (!explored.contains(itr)&& flag == 1)
						{
						
						for (ElementPriority item : frontier)
						{
							if (item.element.equals(itr))
							{
								if (item.path_cost > homework.adjLists.get(Current.element).get(itr).get(0) +Current.path_cost - homework.hyuristic_map.get(Current.element) + homework.hyuristic_map.get(itr)){
									
								
									item.path_cost = homework.adjLists.get(Current.element).get(itr).get(0) +Current.path_cost - homework.hyuristic_map.get(Current.element) + homework.hyuristic_map.get(itr);
								
									modified_item = item;
									mod_flag = 1;
								
									parentLists.put(item.element, new String(Current.element));
								}
							}
						}
						if(mod_flag==1)
						 {
						
						  cur = frontier.remove();
					
						  temp_queue.add(cur);
						  if (cur.element.equals(modified_item.element))
						  {
							  frontier.add(temp_queue.remove());
							  mod_flag=0;
							  
						  }
						  while(!cur.element.equals(modified_item.element))
						   {
						
							cur = frontier.remove();
							temp_queue.add(cur);
						   }
						  if(cur.element.equals(modified_item.element) && mod_flag !=0 )
						  {
							
							  mod_flag=0;
						  }
						  while(!temp_queue.isEmpty())
							  frontier.add(temp_queue.remove());
						 } 
						
					
						
						}
						
					} 
				}
			
					
			}
			
		}
		
	}
	
}
