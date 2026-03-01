public class EvaluationPipeline {
    private final PlagiarismCheckerInterface plagiarismChecker;
    private final GraderInterface grader;
    private final ReportWriterInterface reportWriter;

    public EvaluationPipeline(PlagiarismCheckerInterface pc, GraderInterface g, ReportWriterInterface rw) {
        this.plagiarismChecker = pc;
        this.grader = g;
        this.reportWriter = rw;
    }

    public void evaluate(Submission sub) {
        Rubric rubric = new Rubric(); 

        int plag = plagiarismChecker.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = grader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = reportWriter.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}