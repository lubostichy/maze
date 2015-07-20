/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package maze;
import maze.tape.*;
import java.io.*;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
 /**
 *Trieda obsahuje spracovanie prikazov 
 * zadanych z prikazoveho riadka a nacitanie bludiska zo suboru
 * @author xtichy23,xvlkov01
 */
public class Main 
{
        
    /**
     * Metoda main spracova vstup, na zaklade vstupu rozhoduje o dalsich akciach
     * @param args argumenty
     */
    public static void main(String[] args) {
       
        Scanner sc = new Scanner(System.in, "Windows-1250");
        
  


 
        Tape t1 = null;
        TapeHead h1 = null;
                
        for(;;)
        {
        System.out.println("prikaz:");
        /**premmenna pre ulozenie vstupu*/
        String vstup;
        vstup = sc.nextLine();
        if(vstup.startsWith("game")) {
            if(vstup.length()<=5) {
                System.out.println("bad expression please try again"); 
                continue;
            }
            t1 = file(vstup.substring(5));
            if (t1==null) continue;
            h1 = t1.createHead(0);
        }
        else if("show".equals(vstup)){
            if (t1 != null) {
                t1.show(h1);
            }     
        }
        else if("close".equals(vstup)) {
            break;
        }
        else if("step".equals(vstup)){
            if (h1 != null) {
                if (h1.step()) {
                    System.out.println("True");
                }
                else {
                    System.out.println("False");
                }
            }
        }
        else if("left".equals(vstup)){
            if (h1 != null) {
                h1.left();                
                System.out.println("True");
            }
        }
        else if("right".equals(vstup)){
            if (h1 != null) {
                h1.right();
                System.out.println("True");
            }
        }
        else if("take".equals(vstup)) {
            if (h1 != null) {
                if (h1.take()) {
                    System.out.println("True");
                }
                else {
                    System.out.println("False");
                }
            }
        }
        else if("open".equals(vstup)){
            if (h1 != null) {
                if (h1.open()) {
                    System.out.println("True");
                }
                else {
                    System.out.println("False");
                }
            }
        }
        else if("keys".equals(vstup)){
            if (h1 != null) {
                
                System.out.println("Keys: "+h1.keys());
            }
        }
        else { 
            System.out.println("bad expression please try again"); 
            continue;
        }
       

//System.out.println(row);
        }
    }
    /**
     * Metoda nacita bludisko z textoveho suboru a
     * vracia vytvorenu pasku
     * @param name nazov bludiska
     * @return vracia vytvorenu pasku(bludisko)
     */
    
    public static Tape file(String name) {
        Tape tmp = null;
        int row = 0;
        int col = 0;
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        try{
            FileInputStream fstream = new FileInputStream(s+"/examples/"+name);            
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String whole="";
            int r=0;
            int c=0;
            while ((strLine = br.readLine()) != null) {
                //if(strLine.contains(name)) {System.out.println ("Found");  r=1; continue;}
                //else if(r!=1) continue;
                if(strLine.matches(".*\\d.*")){
                String[] parts=strLine.split(" ");

                row=Integer.parseInt(parts[0]);
                col=Integer.parseInt(parts[1]);
                //System.out.printf ("%d ddd\n",row);
                //System.out.println (col);
                continue;

                }
                // Print the content on the console
                //if(strLine.contains("@@")) break;
                whole=whole.concat(strLine);
                whole=whole.replaceAll("\\s+","");
                //System.out.println (strLine);

            }
            
            tmp = new Tape(row,col,0,whole);

            //Close the input stream
            in.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());

        }
        return tmp;
    }
   
}
