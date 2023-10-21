import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Integer> symbolTable;

    private int symAddr = 0;
    public SymbolTable() {
        symbolTable = new HashMap<>();
    }

    public void addEntry(String symbol, int address) {
        symbolTable.put(symbol, address);
    }

    public boolean contains(String symbol) {
        return symbolTable.containsKey(symbol);
    }

    public int getAddress(String symbol) {
        if (contains(symbol)) {
            return symbolTable.get(symbol);
        } else {
            addEntry(symbol, symAddr);
            symAddr++;
        }
        return 0;
    }   
}
