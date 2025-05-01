public interface View {
    default void display(String message) {}

    default String selectOption(String[] options) {
        return "";
    }
}
