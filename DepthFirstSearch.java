import java.util.*;

public class DepthFirstSearch {
	String STARTNODE ;
	String GOALNODE;
	
	public DepthFirstSearch(String STARTNODE , String GOALNODE)
	{
		this.STARTNODE = STARTNODE;
		this.GOALNODE = GOALNODE;
		
	}

	public void compute_dfs()
	{
		
		HashMap<String, String> parentLists = new HashMap<String,String >();
		
		
		
		if (this.STARTNODE.equals(this.GOALNODE))
		{
			System.out.println(this.STARTNODE +" " + 0);
			return;
			
		}
		Stack<String> dfs_stack = new Stack<> ();
	
		ArrayList<String> explored = new ArrayList<> ();
		dfs_stack.add(this.STARTNODE);
		ArrayList<String> path = new ArrayList<String>();
	
		parentLists.put(this.STARTNODE,new String(""));
	
		int cost = 0;
		while ( !dfs_stack.isEmpty())
		{
	
			String Current = dfs_stack.pop();
	
			if (!explored.contains(Current))
			{
		
			if ( Current.equals(this.GOALNODE))
			{
				
				explored.add(Current);
			
				String child = Current;
				
				while (!parentLists.get(child).isEmpty())
				
				{
					path.add(child);
					
					child = parentLists.get(child);
				}
				path.add(child);
			
				Collections.reverse(path); 
			
				
				for (String i : path)
				{
					System.out.println(i +" "+cost);
					cost++;
				}
				return;
				
			}
			else
			{
				ArrayList<String> vertices = new ArrayList<String>();
				if ( homework.adjLists.get(Current)== null)
					continue;
				
				for ( String itr :homework.adjLists.get(Current).keySet())
				{
					
					vertices.add(itr);
				}
				Collections.reverse(vertices);
					
					
				    for ( String itr :vertices)
					{
					
				    	int index = dfs_stack.search(itr);
				    	
						if(index == -1 && !explored.contains(itr))
						{
						
						dfs_stack.add(itr);
						parentLists.put(itr,new String(Current));
						 
						
						}
					
					
						
					}
				
				    vertices = null;
				    
				
				
			
					
			}
			
			if(!explored.contains(Current))
			explored.add(Current);
			}
			
		}
		
	}

}
