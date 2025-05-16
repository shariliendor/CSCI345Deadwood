import java.util.Arrays;
import java.util.List;

public class Card {
    private final Role[] roles;
    private final String name;
    private final String image;
    private final int budget;
    private final String sceneDescription;


    public Card(String name, String image, int budget, String sceneDescription, Role[] roles) {
        this.name = name;
        this.image = image;
        this.budget = budget;
        this.sceneDescription = sceneDescription;
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return Arrays.asList(this.roles);
    }

    
    public int getBudget() {
        return budget;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getSceneDescription() {
        return sceneDescription;
    }
}
