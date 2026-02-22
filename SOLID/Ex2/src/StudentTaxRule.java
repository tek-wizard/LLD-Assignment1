public class StudentTaxRule implements TaxRule {
    public double getTaxPercent() { return 5.0; }
    public double calculateTax(double subtotal) { return subtotal * 0.05; }
}