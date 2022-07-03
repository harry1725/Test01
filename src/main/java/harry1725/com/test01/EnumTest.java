package harry1725.com.test01;

public enum EnumTest {
    APPLE("apple", true),
    CHOCOLATE("chocolate", false),
    RAMYEON("ramyeon", false),
    RICE("rice", true),
    CORN("corn", true),
    SUGAR_CUBE("sugar_cube", false),
    SALAD("salad", true);

    public final String foodName;
    public final boolean healthy;

    EnumTest(String foodName, boolean healthy) {
        this.foodName = foodName;
        this.healthy = healthy;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public static EnumTest[] getFoods() {
        return new EnumTest[] {APPLE, CHOCOLATE, RAMYEON, RICE, CORN, SUGAR_CUBE, SALAD};
    }

    public String toString() {
        return foodName;
    }
}
