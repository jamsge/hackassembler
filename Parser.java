import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.net.URL; 



public class Parser {
    
    final public String A_INSTRUCTION = "A_INSTRUCTION";
    final public String C_INSTRUCTION = "C_INSTRUCTION";
    final public String L_INSTRUCTION = "L_INSTRUCTION";
    final public String BLANK = "BLANK";

    private File file;
    private Scanner sc;
    private String currentLine = "";

    public Parser(String path){
        URL url = getClass().getResource(path);
        file = new File(url.getPath());
        try{
            sc = new Scanner(file);
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
        
        do {
            currentLine = sc.nextLine().replaceAll("\\s+","").replaceAll("\\s+","");
            int commentIdx = currentLine.indexOf("//");
            if (commentIdx > -1){
                currentLine = currentLine.substring(0, commentIdx);
            }
        } while(currentLine.equals(""));
    }

    public String getCurrentLine(){
        return currentLine;
    }

    public boolean hasMoreLines(){
        return sc.hasNextLine();
    }

    
    // @TODO: skip blank lines
    public String advance(){
        do {
            currentLine = sc.nextLine().replaceAll("\\s+","").replaceAll("\\s+","");
            int commentIdx = currentLine.indexOf("//");
            if (commentIdx > -1){
                currentLine = currentLine.substring(commentIdx);
            }
        } while(currentLine.equals(""));
        return currentLine;
    }


    public String instructionType(){
        String instruction = currentLine;
        if (instruction.length()!=0 && instruction.substring(0,1).equals("@")){
            return A_INSTRUCTION;
        } else if (instruction.contains("=") || instruction.contains(";")){
            return C_INSTRUCTION;
        } else if (instruction.contains("(") && instruction.contains(")")){
            return L_INSTRUCTION;
        } else if(instruction.equals("")){
            return BLANK;
        } 
        System.out.println("ERROR: invalid instruction");
        System.out.println(instruction);
        System.exit(0);
        return "";
    }

    public String symbol(){
        if (instructionType().equals(A_INSTRUCTION)){
            return currentLine.substring(1);
        } else if(instructionType().equals(L_INSTRUCTION)){
            return currentLine.replace("(", "").replace(")", "");
        }
        return null;
    }

    public String dest(){
        if (!instructionType().equals(C_INSTRUCTION)){
            return null;
        }
        if (currentLine.contains("="))
            return currentLine.substring(0,currentLine.indexOf("="));
        
        return currentLine.substring(0,1);
    }

    public String comp(){
        if (!instructionType().equals(C_INSTRUCTION)){
            return null;
        }
        String myComp = currentLine;
        int scLoc = myComp.indexOf(";");
        if (scLoc > -1){
            myComp = myComp.substring(0, scLoc);
        }
        return myComp;
    }

    public String jump(){
        if (!instructionType().equals(C_INSTRUCTION)){
            System.out.println("this is not a C instruction");
            return null;
        }
        int scLoc = currentLine.indexOf(";");
        if (scLoc == -1){
            return "";
        }
        return currentLine.substring(scLoc + 1);
    }
}
