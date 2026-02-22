import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepository repository;
    private final TaxRule taxRule;
    private final DiscountRule discountRule;
    private final InvoiceFormatter formatter;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(TaxRule tax, DiscountRule discount, 
                           InvoiceRepository repo, InvoiceFormatter formatter) {
        this.taxRule = tax;
        this.discountRule = discount;
        this.repository = repo;
        this.formatter = formatter;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            subtotal += item.price * l.qty;
        }

        double tax = taxRule.calculateTax(subtotal);
        double taxPct = taxRule.getTaxPercent();
        double discount = discountRule.calculateDiscount(subtotal, lines.size());
        double total = subtotal + tax - discount;

        String printable = formatter.format(invId, lines, menu, subtotal, tax, taxPct, discount, total);
        
        System.out.print(printable);
        repository.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + repository.countLines(invId) + ")");
    }
}