package visitor;

import exceptions.CalcException;
import token.Brace;
import token.NumberToken;
import token.Operation;

import java.util.Stack;

public class CalcVisitor implements TokenVisitor {

    Stack<Integer> stack = new Stack<>();

    public void visit(Brace token) {

    }

    public void visit(NumberToken token) {
        stack.push(token.getValue());
    }

    public void visit(Operation token) {
        int right = stack.pop();
        int left = stack.pop();
        stack.push(token.execute(left, right));
    }

    public int getResult() throws CalcException {
        if (stack.size() != 1) {
            throw new CalcException();
        }
        return stack.pop();
    }
}
