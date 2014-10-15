package domain;

public enum AccesLevel {
    
    ADMIN           (3),
    USER_ACTIVATED  (1),
    USER_CREATED    (0),
    BLOCKED         (-1);
    
    private final int level;
    
    AccesLevel(int level) {
        this.level = level;
    }
    
    public int getLevel() {
        return this.level;
    }
}