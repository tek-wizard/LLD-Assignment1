public class StudentDiscountRule implements DiscountRule {
    public double calculateDiscount(double subtotal, int distinctLines) {
        return (subtotal >= 180.0) ? 10.0 : 0.0;
    }
}