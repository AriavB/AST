import java.nio.file.Path;
import java.util.List;

public interface SecurityCheck {

    void analyze(Path path, List<Vulnerability> vulnerabilities);

}
