package visitor;

import exceptions.ParseException;
import token.Brace;
import token.NumberToken;
import token.Operation;
import token.Token;

import java.util.ArrayList;
import java.util.List;

public class ParseVisitor implements TokenVisitor {

    List<Token> tokens = new ArrayList<>();
    int pos;

    public void visit(Brace token) {
        tokens.add(token);
    }

    public void visit(NumberToken token) {
        tokens.add(token);
    }

    public void visit(Operation token) {
        tokens.add(token);
    }

    public List<Token> toRPN() throws ParseException {
        pos = 0;
        return parseExpr();
    }

    private Token nextToken() {
        return tokens.get(pos++);
    }

    public List<Token> parseExpr() throws ParseException {
        List<Token> newTokens = parseMulDiv();
        while (pos < tokens.size()) {
            Token token = tokens.get(pos);
            if (token instanceof Operation && ((Operation) token).getPriority() == 1) {
                pos++;
                newTokens.addAll(parseMulDiv());
                newTokens.add(token);
            } else {
                return newTokens;
            }
        }

        return newTokens;
    }

    private List<Token> parseMulDiv() throws ParseException {
        List<Token> newTokens = parseTerm();
        while (pos < tokens.size()) {
            Token token = tokens.get(pos);
            if (token instanceof Operation && ((Operation) token).getPriority() == 2) {
                pos++;
                newTokens.addAll(parseTerm());
                newTokens.add(token);
            } else {
                return newTokens;
            }
        }

        return newTokens;
    }

    private List<Token> parseTerm() throws ParseException {
        List<Token> newTokens = new ArrayList<>();
        Token token = nextToken();

        if (token instanceof Brace && ((Brace) token).isLeft()) {
            newTokens = parseExpr();
            if (!((Brace) nextToken()).isRight()) {
                throw new ParseException("Expected right bracket at pos: " + pos);
            }
            return newTokens;
        } else if (token instanceof NumberToken) {
            newTokens.add(token);
            return newTokens;
        }

        throw new ParseException("Unexpected: " + token.toString() + " at pos " + pos);
    }
}
