import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.ArrayList;
import java.util.HashMap;

public class SecondVisitor extends DepthFirstAdapter {
    private SymbolTable symtable;
    private HashMap<String,Variable> vars;
    int errors=0;
    String fncreturn=null;
    public SecondVisitor(SymbolTable symtable,HashMap<String,Variable> vars) {
        this.symtable = symtable;
        this.vars=vars;
    }

    @Override
    public void inAStringValue(AStringValue node)
    {
        defaultIn(node);
        if(node.parent() instanceof AValueExpression){
            if(node.parent().parent() instanceof AMinusExpression){
                Error e=new Error(node.getString().getLine(),node.getString().getPos());
                e.setMsg("Use of string in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if(node.parent().parent() instanceof AMaxExpression){
                Error e=new Error(node.getString().getLine(),node.getString().getPos());
                e.setMsg("Use of string in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if(node.parent().parent() instanceof AMinExpression){
                Error e=new Error(node.getString().getLine(),node.getString().getPos());
                e.setMsg("Use of string in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if(node.parent().parent() instanceof ADmultExpression){
                Error e=new Error(node.getString().getLine(),node.getString().getPos());
                e.setMsg("Use of string in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if(node.parent().parent() instanceof AModExpression){
                Error e=new Error(node.getString().getLine(),node.getString().getPos());
                e.setMsg("Use of string in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if(node.parent().parent() instanceof ADivExpression){
                Error e=new Error(node.getString().getLine(),node.getString().getPos());
                e.setMsg("Use of string in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if(node.parent().parent() instanceof AArrayIdExpression){
                Error e=new Error(node.getString().getLine(),node.getString().getPos());
                e.setMsg("Use of string in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if(node.parent().parent() instanceof ADivEqualStatement){
                Error e=new Error(node.getString().getLine(),node.getString().getPos());
                e.setMsg("Use of string in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if(node.parent().parent() instanceof AMinEqualStatement){
                Error e=new Error(node.getString().getLine(),node.getString().getPos());
                e.setMsg("Use of string in a arithmetic expression");
                symtable.getErrors().add(e);
            }

        }




    }
    @Override
    public void inANumberValue(ANumberValue node)
    {
        defaultIn(node);
        if(node.parent() instanceof AValueExpression){
            if(node.parent().parent() instanceof AOpenExpression){
                Error e=new Error(node.getNumber().getLine(),node.getNumber().getPos());
                e.setMsg("Use of integer in a open expression");
                symtable.getErrors().add(e);
            }
        }

    }


    @Override
    public void inAOpenExpression(AOpenExpression node)
    {
        defaultIn(node);

        if(node.parent()instanceof AMinEqualStatement){
            Error e=new Error(((AMinEqualStatement) node.parent()).getId().getLine(),((AMinEqualStatement) node.parent()).getId().getPos());
            e.setMsg("Use of open in a arithmetic statement");
            symtable.getErrors().add(e);
        }
        if(node.parent()instanceof ADivEqualStatement){
            Error e=new Error(((ADivEqualStatement) node.parent()).getId().getLine(),((ADivEqualStatement) node.parent()).getId().getPos());
            e.setMsg("Use of open in a arithmetic statement");
            symtable.getErrors().add(e);
        }
        if(node.parent().parent() instanceof AEqualStatement)
        {
            if (node.parent() instanceof APlusExpression) {
                System.out.println();
                Error e=new Error(((AEqualStatement) node.parent().parent()).getId().getLine(),((AEqualStatement) node.parent().parent()).getId().getPos());
                e.setMsg("Use of open in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if (node.parent() instanceof AMinusExpression) {
                System.out.println();
                Error e = new Error(((AEqualStatement) node.parent().parent()).getId().getLine(), ((AEqualStatement) node.parent().parent()).getId().getPos());
                e.setMsg("Use of open in a arithmetic expression");
                symtable.getErrors().add(e);
            }

            if (node.parent() instanceof ADivExpression) {
                System.out.println();
                Error e = new Error(((AEqualStatement) node.parent().parent()).getId().getLine(), ((AEqualStatement) node.parent().parent()).getId().getPos());
                e.setMsg("Use of open in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if (node.parent() instanceof AMultExpression) {
                System.out.println();
                Error e = new Error(((AEqualStatement) node.parent().parent()).getId().getLine(), ((AEqualStatement) node.parent().parent()).getId().getPos());
                e.setMsg("Use of open in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if (node.parent() instanceof ADmultExpression) {
                System.out.println();
                Error e = new Error(((AEqualStatement) node.parent().parent()).getId().getLine(), ((AEqualStatement) node.parent().parent()).getId().getPos());
                e.setMsg("Use of open in a arithmetic expression");
                symtable.getErrors().add(e);
            }
            if (node.parent() instanceof AModExpression) {
                System.out.println();
                Error e = new Error(((AEqualStatement) node.parent().parent()).getId().getLine(), ((AEqualStatement) node.parent().parent()).getId().getPos());
                e.setMsg("Use of open in a arithmetic expression");
                symtable.getErrors().add(e);
            }

        }

    }


    @Override
    public void inAEqualStatement(AEqualStatement node)
    {
        defaultIn(node);

        Variable a=new Variable();
        if(node.getExpression() instanceof AValueExpression){
            AValueExpression expr= (AValueExpression) node.getExpression();
            if(expr.getValue() instanceof ANumberValue){
                a.setType("int");
                a.setLine(node.getId().getLine());
                a.setColumn(node.getId().getPos());
                symtable.getVariables().put(node.getId().toString(),a);
            }
            if(expr.getValue() instanceof AStringValue){
                a.setType("string");
                a.setLine(node.getId().getLine());
                a.setColumn(node.getId().getPos());
                symtable.getVariables().put(node.getId().toString(),a);
            }
            if(expr.getValue() instanceof ANoneValue){
                a.setType("none");
                a.setLine(node.getId().getLine());
                a.setColumn(node.getId().getPos());
                symtable.getVariables().put(node.getId().toString(),a);
            }

        }
        if(node.getExpression() instanceof AOpenExpression){
            a.setType("open");
            a.setLine(node.getId().getLine());
            a.setColumn(node.getId().getPos());
            symtable.getVariables().put(node.getId().toString(),a);

        }

        if(node.getExpression() instanceof APlusExpression){
            String name=node.getId().toString();
            String right=expressionType(node.getExpression());
            if(right!="error"){
                Variable var=new Variable();
                var.setType(right);
                symtable.getVariables().put(name,var);
            }else{
                Error e =new Error(node.getId().getLine(),node.getId().getPos());
                e.setMsg("Invalid expression");
                symtable.getErrors().add(e);
            }
        }
        if(node.getExpression() instanceof AMinusExpression){
            String name=node.getId().toString();
            String right=expressionType(node.getExpression());
            if(right!="error"){
                Variable var=new Variable();
                var.setType(right);
                symtable.getVariables().put(name,var);
            }else{
                Error e =new Error(node.getId().getLine(),node.getId().getPos());
                e.setMsg("Invalid expression");
                symtable.getErrors().add(e);
            }
        }
        if(node.getExpression() instanceof ADivExpression){
            String name=node.getId().toString();
            String right=expressionType(node.getExpression());
            if(right!="error"){
                Variable var=new Variable();
                var.setType(right);
                symtable.getVariables().put(name,var);
            }else{
                Error e =new Error(node.getId().getLine(),node.getId().getPos());
                e.setMsg("Invalid expression");
                symtable.getErrors().add(e);
            }
        }
        if(node.getExpression() instanceof AMultExpression){
            String name=node.getId().toString();
            String right=expressionType(node.getExpression());
            if(right!="error"){
                Variable var=new Variable();
                var.setType(right);
                symtable.getVariables().put(name,var);
            }else{
                Error e =new Error(node.getId().getLine(),node.getId().getPos());
                e.setMsg("Invalid expression");
                symtable.getErrors().add(e);
            }
        }
        if(node.getExpression() instanceof ADmultExpression){
            String name=node.getId().toString();
            String right=expressionType(node.getExpression());
            if(right!="error"){
                Variable var=new Variable();
                var.setType(right);
                symtable.getVariables().put(name,var);
            }else{
                Error e =new Error(node.getId().getLine(),node.getId().getPos());
                e.setMsg("Invalid expression");
                symtable.getErrors().add(e);
            }
        }
        if(node.getExpression() instanceof AModExpression){
            String name=node.getId().toString();
            String right=expressionType(node.getExpression());
            if(right!="error"){
                Variable var=new Variable();
                var.setType(right);
                symtable.getVariables().put(name,var);
            }else{
                Error e =new Error(node.getId().getLine(),node.getId().getPos());
                e.setMsg("Invalid expression");
                symtable.getErrors().add(e);
            }
        }
        if(node.getExpression() instanceof AArrayExpExpression){
            a.setType("array");
            a.setLine(node.getId().getLine());
            a.setColumn(node.getId().getPos());
            symtable.getVariables().put(node.getId().toString(),a);
        }





// TODO: function call value
//TODO:None ?




    }



    @Override
    public void inAIdentExpression(AIdentExpression node) {
        defaultIn(node);

        boolean exists=false;

        if(symtable.getVariables().containsKey(node.getId().toString())&&!exists){
            exists=true;
        }
            if (exists) {

                if (symtable.getVariables().get(node.getId().toString()).getType() == "string") {
                    if (node.parent() instanceof AMinusExpression || node.parent() instanceof AMultExpression ||
                            node.parent() instanceof ADivExpression || node.parent() instanceof ADmultExpression || node.parent() instanceof AModExpression) {
                        Error e = new Error(node.getId().getLine(), node.getId().getPos());
                        e.setMsg("Use of a string variable in arithmetic expression");
                        symtable.getErrors().add(e);


                    }
                    if (node.parent() instanceof ADivEqualStatement || node.parent() instanceof AMinEqualStatement) {

                        Error e = new Error(node.getId().getLine(), node.getId().getPos());
                        e.setMsg("Use of a string variable in arithmetic statement");
                        symtable.getErrors().add(e);

                    }


                }


            } else {



                Error e = new Error(node.getId().getLine(), node.getId().getPos());
                e.setMsg("undeclared variable");
                symtable.getErrors().add(e);
            }



    }

    @Override
    public void inAPrintStatement(APrintStatement node) {
        defaultIn(node);
        PExpression exp=node.getExpression();

        if(exp instanceof APlusExpression){

            String right=expressionType(exp);
            if(right!="int"){

                Error e = new Error(1,1);
                e.setMsg("invalid print statement");
                symtable.getErrors().add(e);

            }



        }


    }

    @Override
    public void inAFunctionCallStatement( AFunctionCallStatement node){
        defaultIn(node);
        AFunctionCallFunctionCall fnc= (AFunctionCallFunctionCall) node.getFunctionCall();
        String name=fnc.getId().toString();
        int arg1=0;
        int arg2=0;
        int startarg;
        int i=0;
        if(fnc.getArglist()!=null){
            AArglist arglist= (AArglist) fnc.getArglist();
            PExpression exp =arglist.getExpression();
            arg1++;
            arg1=arg1+arglist.getNextExpression().size();
        }
        name=name+String.valueOf(arg1);
        name=name+String.valueOf(arg2);
        name=name.replaceAll(" ","");
        startarg=arg1;
        boolean found=false;
        while(!found&&i<=startarg){
            if(symtable.getFunctions().containsKey(name)){
                found=true;
            }else{
                if(arg1>0) {
                    arg1--;
                }
                arg2++;
                i++;
                name=fnc.getId().toString();
                name=name+String.valueOf(arg1);
                name=name+String.valueOf(arg2);
                name=name.replaceAll(" ","");

            }

        }

        if(!found){
            arg1=startarg;
            arg2=0;
            for(int j=0;j<60;j++){

                name=fnc.getId().toString();
                name=name+String.valueOf(arg1);
                name=name+String.valueOf(arg2);
                name=name.replaceAll(" ","");
                if(symtable.getFunctions().containsKey(name)){
                    found=true;
                    break;
                }else{
                    arg2++;
                }

            }



        }



        if(!found){
            Error e=new Error(fnc.getId().getLine(),fnc.getId().getPos());
            e.setMsg("Use of undeclared function");
            symtable.getErrors().add(e);
        }

        if(found){
            ArrayList<AValueExpression> args=new ArrayList<>();
            AFunction fn= (AFunction) symtable.getFunctions().get(name).getNode();
            AReturnStatement st= (AReturnStatement) fn.getStatement();
            System.out.println();
            AArglist as= (AArglist) fnc.getArglist();
            if(as!=null){
                args.add((AValueExpression) as.getExpression());
                for(int o=0;o<as.getNextExpression().size();o++){
                    ANextNextExpression a= (ANextNextExpression) as.getNextExpression().get(o);
                    AValueExpression vl= (AValueExpression) a.getExpression();
                    args.add(vl);
                    System.out.println();
                }


            }

            PExpression exp=st.getExpression();
            System.out.println();

            if(found) {
                boolean stringconflict = false;
                boolean intconflict = false;
                boolean ints = false;
                boolean strings = false;
                PValue p = null;
                if (!args.isEmpty()) {
                    p = args.get(0).getValue();
                }
                if (p instanceof AStringValue) {
                    strings = true;
                    ints = false;
                    for (int l = 1; l < args.size(); l++) {
                        if (!(args.get(l).getValue() instanceof AStringValue)) {
                            stringconflict = true;
                        }
                    }
                }
                if (p instanceof ANumberValue) {
                    strings = false;
                    ints = true;
                    for (int l = 1; l < args.size(); l++) {
                        if (!(args.get(l).getValue() instanceof ANumberValue)) {
                            intconflict = true;
                        }
                    }
                }

                if (!stringconflict && !intconflict) {

                    if (ints) {
                        if (st.getExpression() instanceof AOpenExpression) {
                            Error e = new Error(fnc.getId().getLine(), fnc.getId().getPos());
                            e.setMsg("Invalid function operations");
                            symtable.getErrors().add(e);
                        }

                    }

                    if (strings) {
                        if (!(st.getExpression() instanceof APlusExpression || st.getExpression() instanceof AOpenExpression)) {
                            Error e = new Error(fnc.getId().getLine(), fnc.getId().getPos());
                            e.setMsg("Invalid function operations");
                            symtable.getErrors().add(e);
                        }

                    }


                }else{
                    Error e = new Error(fnc.getId().getLine(), fnc.getId().getPos());
                    e.setMsg("Invalid function operations");
                    symtable.getErrors().add(e);


                }

            }








        }


    }

    @Override
    public void inAFunctionCallExpression( AFunctionCallExpression node){
        defaultIn(node);
        AFunctionCallFunctionCall fnc= (AFunctionCallFunctionCall) node.getFunctionCall();
        String name=fnc.getId().toString();
        int arg1=0;
        int arg2=0;
        int startarg;
        boolean ints = false;
        boolean strings = false;
        String returns=null;
        boolean apply=false;
        int i=0;
        if(fnc.getArglist()!=null){
            AArglist arglist= (AArglist) fnc.getArglist();
            PExpression exp =arglist.getExpression();
            arg1++;
            arg1=arg1+arglist.getNextExpression().size();
        }
        name=name+String.valueOf(arg1);
        name=name+String.valueOf(arg2);
        name=name.replaceAll(" ","");
        startarg=arg1;
        boolean found=false;
        while(!found&&i<=startarg){
            if(symtable.getFunctions().containsKey(name)){
                found=true;
            }else{
                if(arg1>0) {
                    arg1--;
                }
                arg2++;
                i++;
                name=fnc.getId().toString();
                name=name+String.valueOf(arg1);
                name=name+String.valueOf(arg2);
                name=name.replaceAll(" ","");

            }

        }

        if(!found){
            arg1=startarg;
            arg2=0;
            for(int j=0;j<60;j++){

                name=fnc.getId().toString();
                name=name+String.valueOf(arg1);
                name=name+String.valueOf(arg2);
                name=name.replaceAll(" ","");
                if(symtable.getFunctions().containsKey(name)){
                    found=true;
                    break;
                }else{
                    arg2++;
                }

            }



        }



        if(!found){
            Error e=new Error(fnc.getId().getLine(),fnc.getId().getPos());
            e.setMsg("Use of undeclared function");
            symtable.getErrors().add(e);
        }
        
        if(found){
            ArrayList<AValueExpression> args=new ArrayList<>();
            AFunction fn= (AFunction) symtable.getFunctions().get(name).getNode();
            AReturnStatement st= (AReturnStatement) fn.getStatement();
            System.out.println();
            AArglist as= (AArglist) fnc.getArglist();
            if(as!=null){
                args.add((AValueExpression) as.getExpression());
                for(int o=0;o<as.getNextExpression().size();o++){
                     ANextNextExpression a= (ANextNextExpression) as.getNextExpression().get(o);
                     AValueExpression vl= (AValueExpression) a.getExpression();
                     args.add(vl);
                    System.out.println();
                }


            }

            PExpression exp=st.getExpression();
            System.out.println();

            if(found) {
                boolean stringconflict = false;
                boolean intconflict = false;

                PValue p = null;
                if (!args.isEmpty()) {
                    p = args.get(0).getValue();
                }
                if (p instanceof AStringValue) {
                    strings = true;
                    returns="string";
                    fncreturn="string";
                    ints = false;
                    for (int l = 1; l < args.size(); l++) {
                        if (!(args.get(l).getValue() instanceof AStringValue)) {
                            stringconflict = true;
                        }
                    }
                }
                if (p instanceof ANumberValue) {
                    strings = false;
                    ints = true;
                    returns="int";
                    fncreturn="int";
                    for (int l = 1; l < args.size(); l++) {
                        if (!(args.get(l).getValue() instanceof ANumberValue)) {
                            intconflict = true;
                        }
                    }
                }

                if (!stringconflict && !intconflict) {

                    if (ints) {
                        if (st.getExpression() instanceof AOpenExpression) {
                            Error e = new Error(fnc.getId().getLine(), fnc.getId().getPos());
                            e.setMsg("Invalid function operations");
                            symtable.getErrors().add(e);
                        }else{
                            apply=true;
                        }

                    }

                    if (strings) {
                        if (!(st.getExpression() instanceof APlusExpression || st.getExpression() instanceof AOpenExpression)) {
                            Error e = new Error(fnc.getId().getLine(), fnc.getId().getPos());
                            e.setMsg("Invalid function operations");
                            symtable.getErrors().add(e);
                        }else{
                            apply=true;
                        }

                    }


                }else{
                    Error e = new Error(fnc.getId().getLine(), fnc.getId().getPos());
                    e.setMsg("Invalid function operations");
                    symtable.getErrors().add(e);


                }

            }

            if(apply){

                if(node.parent() instanceof AEqualStatement){
                          Variable a=new Variable();
                        a.setType(returns);
                       String temp=((AEqualStatement) node.parent()).getId().toString();
                       symtable.getVariables().put(temp,a);


                }








            }








        }

    }






































    public String expressionType(PExpression exp){
        if(exp instanceof AFunctionCallExpression){
            inAFunctionCallExpression((AFunctionCallExpression) exp);
            return fncreturn;



        }
        if (exp instanceof AValueExpression) {
            if (((AValueExpression) exp).getValue() instanceof ANumberValue) {
                return "int";
            }
            if (((AValueExpression) exp).getValue() instanceof AStringValue) {
                return "string";
            }
            if (((AValueExpression) exp).getValue() instanceof AIdValue) {
                if (symtable.getVariables().containsKey(((AIdValue) ((AValueExpression) exp).getValue()).getId().toString())) {
                    if (symtable.getVariables().get(((AIdValue) ((AValueExpression) exp).getValue()).getId().toString()).getType() == "int") {
                        return "int";
                    }
                    if (symtable.getVariables().get(((AIdValue) ((AValueExpression) exp).getValue()).getId().toString()).getType() == "string") {
                        return "string";
                    }
                    if (symtable.getVariables().get(((AIdValue) ((AValueExpression) exp).getValue()).getId().toString()).getType() == "none") {
                        return "none";
                    }


                } else {
                    return "error";
                }
            }

        }


        if (exp instanceof APlusExpression) {
            String right = expressionType(((APlusExpression) exp).getExpr1());
            String left = expressionType(((APlusExpression) exp).getExpr2());
            if (left == right) {
                return left;
            } else if (left.equals("random") || right.equals("random")) {
            } else {
                return "error";
            }
        }
        if (exp instanceof AMinusExpression) {
            String right = expressionType(((AMinusExpression) exp).getExpr1());
            String left = expressionType(((AMinusExpression) exp).getExpr2());
            if (left == right) {
                if (left != "string" && left != "none") {
                    return left;
                }
            } else if (left.equals("random") || right.equals("random")) {
            } else {
                return "error";
            }
        }
        if (exp instanceof AMultExpression) {
            String right = expressionType(((AMultExpression) exp).getExpr1());
            String left = expressionType(((AMultExpression) exp).getExpr2());
            if (left == right) {
                if (left != "string" && left != "none") {
                    return left;
                } else if (left.equals("random") || right.equals("random")) {
                } else {
                    return "error";
                }
            }
        }
        if (exp instanceof ADivExpression) {
            String right = expressionType(((ADivExpression) exp).getExpr1());
            String left = expressionType(((ADivExpression) exp).getExpr2());
            if (left == right) {
                if (left != "string" && left != "none") {
                    return left;
                } else if (left.equals("random") || right.equals("random")) {
                } else {
                    return "error";
                }
            }
        }
        if (exp instanceof ADmultExpression) {
            String right = expressionType(((ADmultExpression) exp).getExpr1());
            String left = expressionType(((ADmultExpression) exp).getExpr2());
            if (left == right) {
                if (left != "string" && left != "none") {
                    return left;
                }else if(left.equals("random")||right.equals("random")){
            } else {
                return "error";
            }
        }
    }

        if(exp instanceof AModExpression) {
            String right = expressionType(((AModExpression) exp).getExpr1());
            String left = expressionType(((AModExpression) exp).getExpr2());
            if (left == right) {
                if (left != "string" && left != "none") {
                    return left;
                } else if (left.equals("random") || right.equals("random")) {
                } else {
                    return "error";
                }
            }
        }

        return "random";

    }



}
