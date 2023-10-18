import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.net.URL; 



public class Parser {
    
    final private String A_INSTRUCTION = "A_INSTRUCTION";
    final private String C_INSTRUCTION = "C_INSTRUCTION";
    final private String L_INSTRUCTION = "L_INSTRUCTION";
    final private String BLANK = "";

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
    }

    public String getCurrentLine(){
        return currentLine;
    }

    public boolean hasMoreLines(){
        return sc.hasNextLine();
    }

    
    // @TODO: make this function also trim whitespace from currentLine
    public String advance(){
        currentLine = sc.nextLine();
        return currentLine;
    }


    public String instructionType(){
        String instruction = currentLine;
        if (instruction.substring(0,1).equals("@")){
            return A_INSTRUCTION;
        } else if (instruction.contains("=")){
            return C_INSTRUCTION;
        } else if (instruction.contains("(") && instruction.contains(")")){
            return L_INSTRUCTION;
        } else if(instruction.equals("")){
            return BLANK;
        } else {
            System.out.println("ERROR: invalid instruction");
            System.out.println(instruction);
            System.exit(0);
        }
        System.out.println("something has gone terribly wrong");
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
        return currentLine.substring(0,1);
    }

    public String comp(){
        if (!instructionType().equals(C_INSTRUCTION)){
            return null;
        }
        String myComp = currentLine.substring(2);
        int scLoc = myComp.indexOf(";");
        if (scLoc > -1){
            myComp = myComp.substring(0, scLoc);
        }
        return myComp;
    }

    public String jump(){
        if (!instructionType().equals(C_INSTRUCTION)){
            return null;
        }
        int scLoc = currentLine.indexOf(";");
        if (scLoc == -1){
            return null;
        }
        return currentLine.substring(scLoc + 1);
    }
}
