/*
Graph Theory and Traversal
*/
/*
Thisara Wijesundera
10/26/2016
CS 203
*/

import java.util.*;

public class GraphModule
{
   private LinkedList<Integer> adjList[]; // LinkedList to hold the connected components and adjacency list
   
   /*
   Input: int (verticies)
   Description: GraphModule constructor. Instantiates the LinkedList for use around the whole class 
   */
   
   public GraphModule(int verticies)
   {
      adjList = new LinkedList[verticies];
   }
   
   /*
   Input: None
   Output: None
   
   Description: Creates a new Integer LinkedList at each spot in the adjacency list
   */
   
   public void createNodes()
   {
      for(int i=0; i<adjList.length; i++)
      {
         adjList[i] = new LinkedList();
      }
   }
   
   /*
   Input: int (firstNode), int (secondNode)
   Output: None
   
   Description: Creates the link between two nodes in an adjacency list
   */
   
   public void connection(int firstNode, int secondNode)
   {
      adjList[firstNode].add(secondNode); // Add the secondNode to the firstNode location in the adjacency list
      adjList[secondNode].add(firstNode); // Add the firstNode to the secondNode location in the adjacency list
   }
   
   /*
   Input: None
   Output: None
   
   Description: Traverses the adjacency list recursively checking if spots in the list have been visited
   */
   public void dfs()
   {
      Stack<Integer> dfsStack = new Stack(); // Create stack to hold popped vertex after reaching dead end
      boolean[] visited = new boolean[adjList.length]; // Create a boolean array to mark if nodes have been visited in dfs
      for(int i=0; i<visited.length; i++)
      {
         if (!visited[i]) // If the location in the adjacency list hasn't been visited yet then visit it
         {
            System.out.print("{ ");
            dfs(dfsStack, i, visited); // Method used to print the connected components
            System.out.print("} ");
         }
      }
   }
   
   /*
   Input:Stack<Integer> (dfsStack), int (i), boolean[] (visited)
   Output: None
   
   Description: Used to travese the adjacency list and print the connected components in the graph
   */
   public void dfs(Stack<Integer> dfsStack, int i, boolean[] visited)
   {
      visited[i] = true; // Mark that this node was visited
      dfsStack.push(i); // Push the first node onto the stack
      Iterator<Integer> iterator = adjList[i].iterator(); // Create an iterator to traverse the linkedlist of nodes
      Integer vertex; // Used to hold the next node
      
      while(iterator.hasNext())
      {
         vertex = iterator.next(); // Get the next node in the linkedlist (adjList)
                  
         if(!visited[vertex]) // Check if the vertex hasn't been visited, if it hasn't then take the new node and visit it
         {
            dfs(dfsStack, vertex, visited); // Recursive call
         }
      }
      // Finish recursive calls
      while(!dfsStack.isEmpty()) // Stack starts as full
      {
         System.out.print((dfsStack.pop()+1) + " "); // Pop off and print elements in stack until the stack is empty
      }      
   }
   
   /*
   Input: None
   Output: boolean
   
   Description: Method returns true if graph has a cycle, otherwise it returns false which makes the program print "Acyclic"
   */   
   public boolean isCyclic()
   {
      System.out.print("\n");
      Boolean[] visited = new Boolean[adjList.length]; // Create a Boolean array (same length as the adjList)
      for(int j=0; j<adjList.length; j++)
         visited[j] = false; // Set the list to false to keep track of nodes visited
      //Stack<Integer> cyclicStack = new Stack();
      for(int i=0; i<adjList.length; i++)
      {
         if(!visited[i]) // If the adjList at 'i' hasn't been visited then expore it
            if(isCyclic(visited, i, -1)) // Start with parent at 1
            {
               System.out.print((i+1)); // Print the starting node in the cycle if true
               return true;
            }
      }
      return false; // Print "Acyclic"
   }
   
   /*
   Input: Boolean[] (visited), int (currentVertex), int (parent)
   Output: boolean
   
   Description: Traverses the list until a node is found where the parent node is not visited again. If this is true then
   we know there is a cycle in the graph. The nodes of the cycle are then printed
   */
   private boolean isCyclic(Boolean[] visited, int currentVertex, int parent)
   {
      visited[currentVertex] = true; // Mark that this node was visited
      Integer i; // variable use to hold the next value given by the iterator
      
      Iterator<Integer> iterator = adjList[currentVertex].iterator(); // iterator used to traverse the LinkedList
      while(iterator.hasNext())
      {
         i = iterator.next(); // Get the next node in the LinkedList
         
         if(!visited[i]) // If the node hasn't been visited yet
         {
            if(isCyclic(visited, i, currentVertex))
            {
               System.out.print((i+1)+ "-"); // Print the node in the cycle
               return true; // Prints the first node in the cycle again
            }   
         }
         else if(i != parent) // Base case
         {
            System.out.print((i+1)+ "-"); // Print the node in the cycle
            return true; // Prints the first node in the cycle again
         }
      }
      return false; // Prints "Acyclic"
   }
}
