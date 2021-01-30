

import java.io.*;
import minipython.lexer.Lexer;
import minipython.parser.Parser;
import minipython.node.*;
import java.util.*;

public class ParserTest {
    public static void main(String[] args) {
        try {
            Parser parser = new Parser(new Lexer(new PushbackReader(new FileReader(args[0].toString()), 1024)));

            SymbolTable symtable = new SymbolTable();
            Start ast = parser.parse();
            FirstVisitor fv = new FirstVisitor(symtable);
            ast.apply(fv);
             System.out.println();
            ast.apply(new SecondVisitor(fv.getSymtable(),fv.getVars()));
            System.out.println();
            for(Error e:symtable.getErrors()){
                System.out.println("error at:"+e.getLine()+","+e.getCol());
                System.out.println(e.getMsg());

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }
}
