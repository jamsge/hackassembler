public class HackAssembler {
    
    public static void main(String args[]){
        final String A_INSTRUCTION = "A_INSTRUCTION";
        final String C_INSTRUCTION = "C_INSTRUCTION";
        final String L_INSTRUCTION = "L_INSTRUCTION";
        if (args.length == 0){
            System.exit(1);
        }
        
        Parser symbolParser = new Parser(args[0]);
        SymbolTable symbols = new SymbolTable();
        int addr = 0;
        do {

            if (symbolParser.instructionType().equals(L_INSTRUCTION)){
                symbols.addEntry(symbolParser.symbol(), addr);
            } else if (symbolParser.instructionType().equals(A_INSTRUCTION) || symbolParser.instructionType().equals(C_INSTRUCTION)) {
                addr++;
            }
        } while (symbolParser.hasMoreLines());

        Parser codeParser = new Parser(args[0]);
        boolean first = true;
        do {
            if (!first){
                codeParser.advance();
            } else {
                first = false;
            }
            String binary = "";
            String instructionType = codeParser.instructionType();
            if (instructionType.equals(A_INSTRUCTION)){
                int new_addr = symbols.getAddress(codeParser.symbol());
                binary += String.format("%15s", Integer.toBinaryString(new_addr)).replace(' ', '0');
            } else if (instructionType.equals(C_INSTRUCTION)){
                binary += "111";
                String comp = Code.comp(codeParser.comp());
                String dest = Code.dest(codeParser.dest());
                String jump = Code.jump(codeParser.jump());
                binary += comp + dest + jump;
            } else if (instructionType.equals(L_INSTRUCTION)){

            }

            System.out.println(binary);

            
        } while (codeParser.hasMoreLines());
    }
}
