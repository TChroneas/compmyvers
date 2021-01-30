import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FirstVisitor extends DepthFirstAdapter {
    private SymbolTable symtable;

    public HashMap<String, Variable> getVars() {
        return vars;
    }

    private HashMap<String,Variable> vars=new HashMap<>();
    public SymbolTable getSymtable() {
        return symtable;
    }
    boolean invalidDef=false;
    int errors=0;
    public FirstVisitor(SymbolTable symtable) {
        this.symtable = symtable;

    }


    public boolean checkInvalidFunc(String funName1,String funName2){
        String name1=funName1.substring(0,funName1.length()-2);
        String name2=funName2.substring(0,funName2.length()-2);
        int fc1=Character.getNumericValue(funName1.charAt(funName1.length()-2));
        int fc2=Character.getNumericValue(funName1.charAt(funName1.length()-1));
        int sc1=Character.getNumericValue(funName2.charAt(funName2.length()-2));
        int sc2=Character.getNumericValue(funName2.charAt(funName2.length()-1));
        if(fc1==sc1 && name1.equals(name2)){
            return true;
        }
         if(name1.equals(name2)&&(fc1+fc2)==sc1+sc2){
             return true;
         }
        return false;
    }



    @Override
    public void caseAFunction(AFunction node)
    {
        int nonDefArgs=0;
        int defArgs=0;
        int line;

        Variable first=new Variable();
        int pos;
        String name=null;
        inAFunction(node);
        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        if(node.getArgument() != null)
        {
            node.getArgument().apply(this);
            AArgument arg= (AArgument) node.getArgument();
            ASetValue set= (ASetValue) arg.getSetValue();
         if(arg.getSetValue()!=null){
             defArgs++;

             if(set.getValue() instanceof ANumberValue){
                 first.setType("int");
             }else if(set.getValue() instanceof AStringValue){
                 first.setType("string");
             }else if(set.getValue() instanceof ANoneValue){
                 first.setType("none");
             }
             vars.put(arg.getId().toString(),first);
             symtable.getVariables().put(arg.getId().toString(),first);

         }else{
             nonDefArgs++;
             first.setType("random");
             vars.put(arg.getId().toString(),first);
             symtable.getVariables().put(arg.getId().toString(),first);
         }
            LinkedList<ANextArg> args=arg.getNextArg();
            for(ANextArg arg1:args){
                if(arg1.getSetValue()!=null){
                    defArgs++;
                 Variable next=new Variable();
                 set=(ASetValue) arg1.getSetValue();
                    if(set.getValue() instanceof ANumberValue){
                        next.setType("int");
                    }else if(set.getValue() instanceof AStringValue){
                        next.setType("string");
                    }else if(set.getValue() instanceof ANoneValue){
                        next.setType("none");
                    }
                    vars.put(arg1.getId().toString(),next);
                    symtable.getVariables().put(arg1.getId().toString(),first);
                }else{
                    nonDefArgs++;
                    Variable next=new Variable();
                    next.setType("random");
                    vars.put(arg.getId().toString(),next);
                    symtable.getVariables().put(arg1.getId().toString(),first);

                    System.out.println();

                }
            }
            name=node.getId().toString();
            name=name+String.valueOf(nonDefArgs);
            name=name+String.valueOf(defArgs);
            name=name.replaceAll(" ","");
                for (String key : symtable.getFunctions().keySet()) {
                    if (checkInvalidFunc(name, key)) {
                        invalidDef = true;
                        break;
                    }
                }
            if(!(node.getStatement() instanceof AReturnStatement)){
                invalidDef=true;
            }
            if(invalidDef){
                errors++;
                Error error=new Error(node.getId().getLine(),node.getId().getPos());
                error.setMsg("Invalid function declaration");
                symtable.getErrors().add(error);
            }else{
                line=node.getId().getLine();
                pos=node.getId().getPos();
                Function f=new Function();
                f.setColumn(pos);
                f.setLine(line);
                f.setFuncVars(vars);
                f.setNode(node);





                symtable.getFunctions().put(name,f);
                System.out.println();
            }

            invalidDef=false;



        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAFunction(node);
    }


    @Override
    public void outAFunction(AFunction node){
        defaultOut(node);
    }
}
