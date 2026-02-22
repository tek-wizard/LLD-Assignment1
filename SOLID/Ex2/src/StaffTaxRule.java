public class StaffTaxRule implements TaxRule {
    public double getTaxPercent() { return 2.0; }
    public double calculateTax(double subtotal) { return subtotal * 0.02; }
}