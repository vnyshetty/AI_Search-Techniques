import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;



public class homework {
	
public static	LinkedHashMap<String, LinkedHashMap<String, ArrayList<Integer>>> adjLists = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Integer>>>();
public static LinkedHashMap<String,Integer> hyuristic_map = new LinkedHashMap<String,Integer>();
	public static void main(String[] args) {
	
		// TODO Auto-generated method stub
		
		try {
		
			File file = new File("input.txt");
			Scanner	input = new Scanner(file);
		
                String ALGO = input.nextLine();
             
               
                String START_STATE = input.nextLine();
          
               
                String GOAL_STATE = input.nextLine();
          
               
                int NUMBER_OF_LIVE_TRAFFIC_LINES = Integer.valueOf(input.nextLine());
                
        
                
                for (int i=0 ; i<NUMBER_OF_LIVE_TRAFFIC_LINES;i++)
                {
                	String token [] = input.nextLine().split(" ");
                	if ( !adjLists.containsKey(token[0]))
                	adjLists.put(token[0], new LinkedHashMap<String, ArrayList<Integer>>());
               
                	adjLists.get(token[0]).put(token[1], new ArrayList<Integer>());
               
                	adjLists.get(token[0]).get(token[1]).add(Integer.valueOf(token[2]));
                	
                }
        
                int NUMBER_OF_SUNDAY_TRAFFIC_LINES = Integer.valueOf(input.nextLine());
           
                
               
                for (int i=0 ; i<NUMBER_OF_SUNDAY_TRAFFIC_LINES;i++)
                {
                	String token [] = input.nextLine().split(" ");
                	
                	hyuristic_map.put(token[0],Integer.valueOf(token[1]));
               
                	
                }
          
         
            input.close();
            
             file = new File ("output.txt");
    		FileOutputStream fos;
    		try {
    			fos = new FileOutputStream(file);
    			PrintStream ps = new PrintStream(fos);
    			System.setOut(ps);
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
            
            switch (ALGO){
            case "BFS" :
            	BreadthFirstSearch bfs = new BreadthFirstSearch(START_STATE,GOAL_STATE);
                bfs.compute_bfs();
                break;
            case "DFS" :
            	DepthFirstSearch dfs = new DepthFirstSearch(START_STATE,GOAL_STATE);
                dfs.compute_dfs(); 
                break;
            case "UCS" :
            	UCS_Test ucf = new UCS_Test(START_STATE,GOAL_STATE);
                ucf.compute_ucs();
                break;
            case "A*" :
            	A_Star a = new A_Star(START_STATE,GOAL_STATE);
                a.compute_Astar();
                break;
            	
            }
                 
            
		}
		
		catch (Exception ex) {
            ex.printStackTrace();
        }
	
	}
	

}





