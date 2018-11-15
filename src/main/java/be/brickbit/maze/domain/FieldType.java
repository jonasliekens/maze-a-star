package be.brickbit.maze.domain;

public enum FieldType {
    VISITED("- "),
    SPACE("  "),
    WALL("[]"),
    MARKER("x "),
    START("S "),
    END(" E");

    private String value;

    FieldType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
