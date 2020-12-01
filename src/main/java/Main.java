import exceptions.CalcException;
import exceptions.ParseException;
import token.Token;
import token.Tokenizer;
import visitor.CalcVisitor;
import visitor.ParseVisitor;
import visitor.PrintVisitor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException, CalcException {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        InputStream is = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

        Tokenizer tokenizer = new Tokenizer(is);
        List<Token> tokens = tokenizer.getTokens();

        System.out.print("Input tokens: ");
        print(tokens);
        System.out.println("");

        tokens = parse(tokens);

        System.out.print("RPN tokens: ");
        print(tokens);
        System.out.println("");

        System.out.println("Expression result: " + calc(tokens));
    }

    public static List<Token> parse(List<Token> tokens) throws ParseException {
        ParseVisitor parseVisitor = new ParseVisitor();
        for (Token token : tokens) {
            token.accept(parseVisitor);
        }
        return parseVisitor.toRPN();
    }

    public static void print(List<Token> tokens) {
        PrintVisitor printVisitor = new PrintVisitor();
        for (Token token : tokens) {
            token.accept(printVisitor);
        }
    }

    public static int calc(List<Token> tokens) throws CalcException {
        CalcVisitor calcVisitor = new CalcVisitor();
        for (Token token : tokens) {
            token.accept(calcVisitor);
        }
        return calcVisitor.getResult();
    }

}
