public class Card {
    private final Role[] roles;

    public Card(Role[] roles) { // add xml attributes
        this.roles = roles;
    }

    public Role[] getRoles() {
        return this.roles;
    }
}
