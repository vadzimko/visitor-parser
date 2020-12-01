package token;

import visitor.TokenVisitor;

public class NumberToken implements Token {

    int value;

    public NumberToken(int value) {
        this.value = value;
    }

    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public int getValue() {
        return value;
    }

    public void negateValue() {
        value = -value;
    }

}

