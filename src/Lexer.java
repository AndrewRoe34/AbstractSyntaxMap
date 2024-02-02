public class Lexer {

    private String buff;
    private int pos;
    private char currentCh;
    private Token currToken;

    public Lexer(String buff) {
        this.buff = buff;
        this.pos = 0;
        this.currentCh = buff.charAt(pos);
        getNextToken();
    }

    private void advance() {
        pos++;
        currentCh = pos < buff.length() ? buff.charAt(pos) : 0;
    }

    private void skipWhitespace() {
        while(currentCh != 0 && currentCh == ' ' || currentCh == '\t') {
            advance();
        }
    }

    public Token getCurrToken() {
        Token temp = currToken;
        getNextToken();
        return temp;
    }

    private void getNextToken() {
        skipWhitespace();
        StringBuilder token = new StringBuilder();
        Token.TokenType tokenType = null;
        while(this.pos < buff.length()) {
            // Whitespace
            if(currentCh == ' ' || currentCh == '\t') {
                break;
            }

            // Operators
            boolean flag = false;
            switch(currentCh) {
                case '.':
                case ':':
                case '(':
                case ')':
                case ',':
                case '*':
                case '/':
                case '+':
                case '-':
                    if(token.length() == 0) {
                        tokenType = Token.TokenType.OPERATOR;
                        token.append(currentCh);
                        advance();
                    }
                    flag = true;
                    break;
                default:
                    // do nothing
            }
            if(flag) {
                break;
            }

            // String literals
            if(token.length() == 0 && currentCh == '"') {
                token.append("\"");
                while(this.pos < buff.length()) {
                    advance();
                    if(currentCh == '"') {
                        tokenType = Token.TokenType.LITERAL;
                        advance();
                        break;
                    } else if(currentCh == 0) {
                        throw new IllegalArgumentException();
                    } else {
                        token.append(currentCh);
                    }
                }
                token.append("\"");
                break;
            }

            // Integer literals
            if(token.length() == 0 && currentCh >= '0' && currentCh <= '9') {
                while(currentCh != 0 && currentCh >= '0' && currentCh <= '9') {
                    token.append(currentCh);
                    advance();
                }
                tokenType = Token.TokenType.LITERAL;
                break;
            }
            tokenType = Token.TokenType.IDENTIFIER;
            token.append(currentCh);
            advance();
        }

        // Boolean literals
        if("true".equals(token.toString()) || "false".equals(token.toString())) {
            tokenType = Token.TokenType.LITERAL;
        }

        // Keywords
        switch (token.toString()) {
            case "func":
            case "if":
            case "return":
            case "elif":
            case "else":
            case "for":
            case "to":
                tokenType = Token.TokenType.KEYWORD;
                break;
            default:
                //do nothing
        }
        currToken = new Token(token.toString(), tokenType);
    }
}
