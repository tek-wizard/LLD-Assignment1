import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    
    @Override
    public String getFormatName() { return "PDF"; }

    @Override
    public String getContentType() { return "application/pdf"; }

    // Honor the legacy requirement of a 20-char limit for PDF specifically
    @Override
    public int getMaxContentLength() { 
        return 20; 
    }

    @Override
    protected ExportResult generateExport(ExportRequest req) {
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult(getContentType(), fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}