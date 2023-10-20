import java.util.HashMap;
import java.util.Map;

public class Code {
    private static final String ZERO = "0";
    private static final String ONE = "1";

    public static String dest(String registers){
        String bits = "";
        if (registers.contains("A")){
            bits += ONE;
        } else {
            bits += ZERO;
        }
        if (registers.contains("D")){
            bits += ONE;
        } else {
            bits += ZERO;
        }
        if (registers.contains("M")){
            bits += ONE;
        } else {
            bits += ZERO;
        }
        return bits;
    }

    public static String jump(String comparison){
        String bits = "000";
        switch (comparison){
            case "JMP":
            bits = "111";
            break;

            case "JLE":
            bits = "110";
            break;

            case "JNE":
            bits = "101";
            break;

            case "JLT":
            bits = "100";
            break;

            case "JGE":
            bits = "011";
            break;

            case "JEQ":
            bits = "010";
            break;

            case "JGT":
            bits = "001";
            break;

            default:
            System.out.println("invalid jump comparison made");
        }
        return bits;
    }

    public static String comp(String operation){
        Map<String, String> compCodes = new HashMap<>();

        compCodes.put("0", "0101010");
        compCodes.put("1", "0111111");
        compCodes.put("-1", "0111010");
        compCodes.put("D", "0001100");
        compCodes.put("A", "0110000");
        compCodes.put("!D", "0001101");
        compCodes.put("!A", "0110001");
        compCodes.put("-D", "0001111");
        compCodes.put("-A", "0110011");
        compCodes.put("D+1", "0011111");
        compCodes.put("A+1", "0110111");
        compCodes.put("D-1", "0001110");
        compCodes.put("A-1", "0110010");
        compCodes.put("D+A", "0000010");
        compCodes.put("D-A", "0010011");
        compCodes.put("A-D", "0000111");
        compCodes.put("D&A", "0000000");
        compCodes.put("D|A", "0010101");
        compCodes.put("", "xxxxxxx");
        compCodes.put("", "xxxxxxx");
        compCodes.put("", "xxxxxxx");
        compCodes.put("", "xxxxxxx");
        compCodes.put("M", "1110000");
        compCodes.put("", "xxxxxxx");
        compCodes.put("!M", "1110001");
        compCodes.put("", "xxxxxxx");
        compCodes.put("-M", "1110011");
        compCodes.put("", "xxxxxxx");
        compCodes.put("M+1", "1110111");
        compCodes.put("", "xxxxxxx");
        compCodes.put("M-1", "1110010");
        compCodes.put("D+M", "1000010");
        compCodes.put("D-M", "1010011");
        compCodes.put("M-D", "1000111");
        compCodes.put("D&M", "1000000");
        compCodes.put("D|M", "1010101");
        return compCodes.get(operation);    
    }
}
