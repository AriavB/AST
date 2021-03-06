import java.nio.file.Path;
import java.util.List;

public class CrossSiteScripting implements SecurityCheck {

    private static final String ALERT_REGEX = "^.*alert\\(.*\\).*$";

    @Override
    public void analyze(Path path, List<Vulnerability> vulnerabilities) {
        if (path.toString().endsWith(".html") || path.toString().endsWith(".js")) {
            FileScanner.findVulnerabilties(this, path, ALERT_REGEX, vulnerabilities);
        }
    }

}