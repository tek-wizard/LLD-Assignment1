public abstract class Exporter {

    // Final template method enforces the contract for all subclasses
    public final ExportResult export(ExportRequest req) {
        // Consistent null handling
        if (req == null) {
            return new ExportResult(getContentType(), new byte[0]);
        }
        
        // Enforce character limits at the base level to prevent subtype surprises
        if (req.body != null && req.body.length() > getMaxContentLength()) {
            throw new IllegalArgumentException(getFormatName() + " cannot handle content > " + getMaxContentLength() + " chars");
        }
        
        return generateExport(req);
    }

    // Hook for specific implementation
    protected abstract ExportResult generateExport(ExportRequest req);

    // Default limit (can be overridden by specific formats)
    public int getMaxContentLength() {
        return (int)1e9; 
    }

    public abstract String getFormatName();
    public abstract String getContentType();
}