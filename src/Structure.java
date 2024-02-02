import java.util.ArrayList;
import java.util.List;

public class Structure {
    private List<Token> root;
    private List<Structure> children;

    public Structure(List<Token> root) {
        this.root = root;
        this.children = new ArrayList<>();
    }

    public boolean addChild(Structure structure) {
        return children.add(structure);
    }

    public List<Token> getRoot() {
        return root;
    }

    public List<Structure> getChildren() {
        return children;
    }
}
