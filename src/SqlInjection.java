import java.nio.file.Path;
import java.util.List;

public class SqlInjection implements SecurityCheck {

    private static final String SQL_REGEX = "^.*SELECT.*WHERE.*%s.*$";

    @Override
    public void analyze(Path path, List<Vulnerability> vulnerabilities) {
        FileScanner.findVulnerabilties(this, path, SQL_REGEX, vulnerabilities);        
    }

}
