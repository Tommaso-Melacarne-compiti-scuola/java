package online.polp;

/**
 * Configuration constants for the restaurant simulation
 */
public class RestaurantConfig {
    // Core configuration parameters
    public static final int TRAY_CAPACITY = 5;
    public static final int NUM_COOKS = 3;
    public static final int NUM_WAITERS = 2;
    public static final int DISHES_PER_COOK = 10;
    
    // Derived configuration parameters
    public static final int TOTAL_DISHES = NUM_COOKS * DISHES_PER_COOK;
    public static final int DISHES_PER_WAITER = TOTAL_DISHES / NUM_WAITERS;
}
