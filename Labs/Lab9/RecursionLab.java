import java.io.*;

public class RecursionLab{
    
    public static void reversePrint (String inString){
    	
		if (inString.length() > 0)		// if string is not empty
		{
			int last = inString.length() - 1;
			System.out.print(inString.charAt(last));
			reversePrint(inString.substring(0, last));
		}
    }
    
    public static String reverseString(String inString) {
    	if (inString.length() > 0)		// if string is not empty
		{
			int last = inString.length() - 1;
			return (inString.charAt(last) + reverseString(inString.substring(0, last)));
		}
    	else return "";
    }

	    
    public static void main(String[] args){
        String inString = "abcde";

		// test reversePrint
        reversePrint(inString);
        System.out.println();
        
        // test reverseString
        String revString = reverseString(inString);
        System.out.println(revString);
    }
}
