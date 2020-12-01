package token;

import visitor.TokenVisitor;

public class Brace implements Token {

    char value;

    public Brace(char value) {
        if (value != '(' && value != ')') {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public boolean isLeft() {
        return value == '(';
    }

    public boolean isRight() {
        return value == ')';
    }
}
