import java.util.List;

public class Report {

    private List<Vulnerability> vulnerabilities;

    public Report(List<Vulnerability> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    public void printOutput(OutputFormat outputFormat) {
        outputFormat.print(vulnerabilities);
    }

    public boolean hasVulnerabilities() {
        return !vulnerabilities.isEmpty();
    }

}
