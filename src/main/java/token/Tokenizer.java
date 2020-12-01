package token;

import exceptions.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private int currChar;
    private int currPos;
    private InputStream inputStream;
    private List<Token> tokens;

    public Tokenizer(InputStream inputStream) throws ParseException {
        this.inputStream = inputStream;
        this.currPos = 0;
        this.tokens = new ArrayList<>();
        nextChar();
    }

    private boolean isBlank(int c) {
        return Character.isWhitespace(c) || c == '\r' || c == '\n' || c == '\t';
    }

    private void nextChar() throws ParseException {
        currPos++;
        try {
            currChar = inputStream.read();
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), currPos);
        }
    }

    public List<Token> getTokens() throws ParseException {
        while (true) {
            while (isBlank(currChar)) {
                nextChar();
            }

            if (currChar == -1) {
                break;
            }

            if (Character.isDigit(currChar)) {
                StringBuilder buffer = new StringBuilder();
                while (Character.isDigit(currChar)) {
                    buffer.append((char) currChar);
                    nextChar();
                }
                tokens.add(new NumberToken(Integer.parseInt(buffer.toString())));
                continue;
            }

            switch (currChar) {
                case '+':
                case '-':
                case '/':
                case '*':
                    tokens.add(new Operation((char) currChar));
                    break;
                case '(':
                case ')':
                    tokens.add(new Brace((char) currChar));
                    break;
                default:
                    throw new ParseException("Invalid character: " + (char) currChar, currPos);
            }
            nextChar();
        }

        return tokens;
    }
}
