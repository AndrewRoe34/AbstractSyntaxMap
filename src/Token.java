public class Token {

    public enum TokenType {
        START,
        IDENTIFIER,
        OPERATOR,
        LITERAL,
        KEYWORD,
    }

    private String token;
    private TokenType tokenType;

    public Token(String token, TokenType tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    @Override
    public String toString() {
        return "[" +
                "token='" + token + '\'' +
                ", tokenType=" + tokenType +
                ']';
    }
}
