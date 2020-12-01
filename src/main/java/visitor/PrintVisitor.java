package visitor;

import token.Brace;
import token.NumberToken;
import token.Operation;

public class PrintVisitor implements TokenVisitor {
    public void visit(Brace token) {
        if (token.isLeft()) {
            System.out.print("Left ");
        } else {
            System.out.print("Right ");
        }
    }

    public void visit(NumberToken token) {
        System.out.print("Number(" + token.getValue() + ") ");
    }

    public void visit(Operation token) {
        System.out.print(token.toString() + " ");
    }
}
