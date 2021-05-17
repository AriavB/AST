import java.nio.file.Path;
import java.util.List;

public class SensitiveDataExposure implements SecurityCheck {

    private static final String CHECKMARX_REGEX = "^.*Checkmarx.*Hellman & Friedman.*\\$1\\.15b.*$";

    @Override
    public void analyze(Path path, List<Vulnerability> vulnerabilities) {
        FileScanner.findVulnerabilties(this, path, CHECKMARX_REGEX, vulnerabilities);        
    }

}
