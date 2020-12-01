package token;

import visitor.TokenVisitor;

public class Operation implements Token {

    Operator op;

    public Operation(char op) {
        switch (op) {
            case '+':
                this.op = Operator.Plus;
                break;
            case '-':
                this.op = Operator.Minus;
                break;
            case '*':
                this.op = Operator.Mul;
                break;
            case '/':
                this.op = Operator.Div;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        return op.toString();
    }

    public boolean isNegation() {
        return op == Operator.Minus;
    }

    public int execute(int left, int right) {
        switch (op) {
            case Minus:
                return left - right;
            case Plus:
                return left + right;
            case Mul:
                return left * right;
            default:
                return left / right;
        }
    }

    public int getPriority() {
        switch (op) {
            case Minus:
            case Plus:
                return 1;
            default:
                return 2;
        }
    }

    private enum Operator {
        Plus,
        Minus,
        Div,
        Mul
    }
}
