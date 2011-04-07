import java.util.*; 
import java.io.*; 
public class FileWordCount 
{ 
  public static void main(String args[])throws IOException 
  { 
	// ### You missed an extra ) at the end of this statement.
    // Scanner sf = new Scanner(new File("/Users/APCS/Desktop/FamousQuotes.txt")); 
    Scanner sf = new Scanner(new File("FamousQuotes.txt")); 
    
    int starter = -1; 
    String quotes[] = new String[10]; 
    while(sf.hasNext())  // ### You had an infinite loop here with the ; at the end of the line...
    { 
      starter++; 
      quotes[starter] = sf.nextLine(); 
    } 
    sf.close(); 
    
    for(int j = 0;j<=starter;j++) 
    {         
      StringTokenizer k = new StringTokenizer(quotes[j],"\t .,"); // this was to tokenize the sentences 
      //im not sure how to split them up
      System.out.print( "tokens = " + k.countTokens() + " " );
      int token_count = k.countTokens();
      String tokens[] = new String[token_count];
      for (int i = 0; i < token_count; i++ )
      {
    	tokens[i] = k.nextToken();
        System.out.print(" '" + tokens[i] + "', ");
      }  // for
      System.out.println();
    	
      System.out.println(quotes[j]); 
      System.out.println();
    } // for
  } // main
}  // FileWordCount
