import minipython.node.AFunction;
import minipython.node.PFunction;

import java.util.HashMap;

public class Function {
    private int line;
    private int column;
    private String statementType;
    private PFunction node;
    private String returns;
    public PFunction getNode() {
        return node;
    }

    public void setNode(PFunction node) {
        this.node = node;
    }

    public String isReturns() {
        return returns;
    }

    public void setReturns(String returns) {
        this.returns = returns;
    }


    private HashMap<String,Variable> funcVars;
    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setFuncVars(HashMap<String, Variable> funcVars) {
        this.funcVars = funcVars;
    }

    public HashMap<String, Variable> getFuncVars() {
        return funcVars;
    }

    public Function() {
        funcVars=new HashMap<>();
    }
}
