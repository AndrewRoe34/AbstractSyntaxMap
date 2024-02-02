import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    private Structure syntaxMap;
    private Lexer lexer;

    public Interpreter(String buff) {
        lexer = new Lexer(buff);
        List<Token> start = new ArrayList<>();
        start.add(new Token(null, Token.TokenType.START));
        syntaxMap = new Structure(start);
    }

    private Token eat(Token.TokenType expected, Token token) {
        if(expected == token.getTokenType()) {
            return lexer.getCurrToken();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void interpreter() {
        List<Token> root = new ArrayList<>();
        while(true) {
            Token t1 = lexer.getCurrToken();
            if(t1.getTokenType() == null) break;
            root.add(t1);
        }
        syntaxMap.addChild(new Structure(root));
        System.out.println(syntaxMap.getRoot());
        for(Structure s : syntaxMap.getChildren()) {
            System.out.println("\t" + s.getRoot());
        }
        System.out.println();
    }
}
