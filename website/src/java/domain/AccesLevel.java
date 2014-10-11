package domain;

public enum AccesLevel {
    
    ADMIN   (3),
    MOD     (2),
    USER    (1),
    BLOCKED (0);
    
    private final int level;
    
    AccesLevel(int level) {
        this.level = level;
    }
    
    public int getLevel() {
        return this.level;
    }
}