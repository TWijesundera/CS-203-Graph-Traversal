/*
Graph Theory and Traversal
*/
/*
Thisara Wijesundera
10/26/2016
CS 203
*/

import java.io.*;
import java.util.*;

public class GraphMain
{   
   public static void main(String[] args)
   {
      int numberOfGraphs = 0; // Variable that holds the number of graphs that the input file contains
      try
      {
         File inputFile = new File(args[0]); // Create a file input which reads the first command line argument
         Scanner inputReader = new Scanner(inputFile); // Create a scanner to read file input
         
         while(inputReader.hasNextLine()) // Loop through input file
            // until end of input file has been reached
         {
            String graphLine = inputReader.nextLine(); // Single out the line
            numberOfGraphs += 1; // Increment the number of graphs by 1
            graphCreator(numberOfGraphs, graphLine); // Method to parse the single line
         }
      }
      catch(ArrayIndexOutOfBoundsException e)
      {
         System.out.println("No input file name, exitting...");
      }
      catch(FileNotFoundException e) 
      {
         System.out.println("Bad file name, exitting...");
      }      
   }
   
   /*
   Input: String (graphLine)
   Output: None
   
   Description: Further parsing of input string. Takes the string, reads the first number (the number of verticies)
   and assigns everything but that number into a new string. We then read that string and get the information of how the nodes are connected.
   Those strings are boxed and sent to a new method which creates the links. Next the adjacency list is traversed and the connected components
   are printed to the user. Finally, the program uses another method to print out the node cycle or if the cycle is ascylic. 
   */
   
   public static void graphCreator(int numberOfGraphs, String graphLine)
   {
      Scanner graphLineReader = new Scanner(graphLine); // Create new scanner to scan the graphline
      
      String verticies = graphLineReader.next(); // Create a vairable called verticies which holds the first number of graphLine
      graphLine = graphLine.substring(2, graphLine.length()); // Cut off the first number in graphLine
      
      Integer vertex = new Integer(verticies); // Boxes the String into an Integer which holds the number of verticies in the graph
      GraphModule module = new GraphModule(vertex); // Create a GraphModule object which is used to travese the adjaceny list
      
      module.createNodes(); // Create the node in the adjacency matrix
      
      Scanner edgeReader = new Scanner(graphLine); // Create new scanner to scan the new line
      String node1 = null; // Variable that holds the node connection
      String node2 = null; // Variable that holds the node connection
      
      while(edgeReader.hasNextLine())
      {
         String edge = edgeReader.next();
         Scanner vertexReader = new Scanner(edge).useDelimiter("\\("); // Use Delimiter to separate the edge into readable format
         String point = vertexReader.next(); // Get the next value
         node1 = point.substring(0, point.indexOf(",")); // Substring from the beginning of the node to the comma
         node2 = point.substring(point.indexOf(",") + 1, point.length()-1); // Substring from the comma+1 to the end of the string-1
         // Send firstNode and secondNode to a method in GraphModule //
         Integer firstNode = new Integer(node1); // Box the String into an Integer
         Integer secondNode = new Integer(node2); // Box the String into an Integer
         module.connection(firstNode-1, secondNode-1); // Subtract 1 from the numbers so they are base 0
      }
      
      System.out.println("Graph"+numberOfGraphs+":");
      module.dfs(); // Method to print the connected components
      if(!module.isCyclic()) // Method returns a boolean, if true do nothing, else print that the graph is Acyclic
         System.out.print("Acyclic");
      System.out.println("\n"); // Print new line for next graph output
   }
}