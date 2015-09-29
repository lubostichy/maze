package maze;

import maze.tape.*;
import java.io.*;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;

 /**
 * Trieda obsahuje spracovanie príkazov zadaných z príkazového riadka a načítanie bludiska zo suboru.
 * @author Ľuboš Tichý
 */
public class Main 
{        
    /**
     * Spracováva vstup, na základe vstupu rozhoduje o ďalších akciách.
     * @param args argumenty
     */
    public static void main(String[] args) 
    {       
        Scanner sc = new Scanner(System.in); 
        Tape t1 = null;
        TapeHead h1 = null;
                
        while(true)
        {
	        System.out.println("command:");
	        
	        String input;
	        while(true)
	        {
                    if (sc.hasNextLine())
                    {
	        	input = sc.nextLine();
	        	break;
                    }
	        }
	        
	        
	        if(input.startsWith("game")) 
	        {
	            if(input.length() <= 5) 
	            {
	                System.out.println("bad expression please try again"); 
	                continue;
	            }
	            t1 = file(input.substring(5));
                    
	            if (t1 == null) continue;
                    
	            h1 = t1.createHead(0);
	        }
	        else
	        {
	        	switch(input) 
	        	{
                            case "show":
                                    if (t1 != null) 
                                    {
                                        t1.show(h1);
                                    }    
                                    break;
                            case "close":
                                    break;
                            case "step":
                                    if (h1 != null)
                                    {
                                        if (h1.step()) 
                                        {
                                                    System.out.println("True");
                                        }
                                        else 
                                        {
                                            System.out.println("False");
                                        }
                                    }
                                    break;
                            case "left":			        
                                    if (h1 != null) 
                                    {
                                        h1.left();                
                                        System.out.println("True");
                                    }
                                    break;
                            case "right": 
                                    if (h1 != null) 
                                    {
                                        h1.right();
                                        System.out.println("True");
                                    }	
                                    break;
                            case "take":
                                    if (h1 != null) 
                                    {
                                        if (h1.take()) 
                                        {
                                            System.out.println("True");
                                        }
                                        else 
                                        {
                                            System.out.println("False");
                                        }
                                    }
                                    break;
                            case "open":			        
                                    if (h1 != null)
                                    {
                                        if (h1.open()) 
                                        {
                                            System.out.println("True");
                                        }
                                        else 
                                        {
                                            System.out.println("False");
                                        }
                                     }
                                     break;			         
                            case "keys":
                                    if (h1 != null) 
                                    {			                
                                            System.out.println("Keys: "+h1.keys());
                                    }
                                    break;
                            default:			    	 		    	 
                                    System.out.println("bad expression please try again"); 
                                    continue;
	        	} // switch
	        } // else
	        
	        if (input.equals("close")) 
	        {
	        	sc.close();
	        	break;
	        }	        
         } // while        
    }

        
    /**
     * Metóda načíta bludisko z textového súboru a vracia vytvorenú pásku.
     * @param name názov bludiska
     * @return vracia vytvorenú pásku(bludisko)
     */    
    public static Tape file(String name) 
    {
        Tape tmp = null;
        int row = 0;
        int col = 0;
        
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        
        try
        {
            FileInputStream fstream = new FileInputStream(s+"/examples/"+name);            
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String whole="";

            while ((strLine = br.readLine()) != null) 
            {
                if(strLine.matches(".*\\d.*"))
                {
                    String[] parts=strLine.split(" ");

                    row = Integer.parseInt(parts[0]);
                    col  =Integer.parseInt(parts[1]);

                    continue;
                }
                
                whole = whole.concat(strLine);
                whole = whole.replaceAll("\\s+","");
            }
            
            tmp = new Tape(row,col,0,whole);

            in.close();
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());

        }
        return tmp;
    }
   
}
