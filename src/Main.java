public class Main {
    public static void main(String[] args) {
//        String line = "x : \"Hello\"";
        String line = "t1 : foo ( arga . get_attr ( x ) )";
//        String line = "elif ( x . get_bool() )";
//        String line = "return x + 3 * 2";
//        String line = "func foo ( x , y )";
        Interpreter i = new Interpreter(line);
        i.interpreter();
    }
}
