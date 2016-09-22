//import java.io.*;
import java.util.*;

public class BreadthFirstSearch {
	String STARTNODE ;
	String GOALNODE;
	
	public BreadthFirstSearch(String STARTNODE , String GOALNODE)
	{
		this.STARTNODE = STARTNODE;
		this.GOALNODE = GOALNODE;
		
	}

	public void compute_bfs()
	{
		HashMap<String, String> parentLists = new HashMap<String,String >();

		
		
		if (this.STARTNODE.equals(this.GOALNODE))
		{
			System.out.println(this.STARTNODE +" " + 0);
			return;
			
		}
		Queue<String> queue = new LinkedList<> ();
		ArrayList<String> explored = new ArrayList<> ();
		queue.add(this.STARTNODE);
		explored.add(this.STARTNODE);
		parentLists.put(this.STARTNODE,new String(""));
		int cost = 0;
		while ( !queue.isEmpty())
		{
		
			String Current = queue.remove();
		
			if ( Current.equals(this.GOALNODE))
			{
				explored.add(Current);
			
				String child = Current;
				ArrayList<String> path = new ArrayList<String>();
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
			
				if ( homework.adjLists.get(Current)== null)
					continue;
				else 
				{
				
					
					for ( String itr :homework.adjLists.get(Current).keySet() )
					{
					
						if (!explored.contains(itr) && !queue.contains(itr))
						{
						queue.add(itr);
						parentLists.put(itr,new String(Current));
					
						
						}
					} 
				}
			
					
			}
			if(!explored.contains(Current))
			explored.add(Current);
			
		}
		
	}
}
