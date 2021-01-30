import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class SymbolTable {
    private HashMap<String, Variable> variables;
    private HashMap<String, Function> functions;
    private ArrayList<Error> errors;

    public HashMap<String, Variable> getVariables() {
        return variables;
    }

    public ArrayList<Error> getErrors() {
        return errors;
    }

    public HashMap<String, Function> getFunctions() {
        return functions;
    }

    public SymbolTable() {
        variables = new HashMap<String,Variable>();
        functions = new HashMap<String, Function>();
        errors=new ArrayList<>();




    }
}
