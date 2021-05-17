import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileScanner {

    private static final Logger fileScannerLogger = System.getLogger(FileScanner.class.getSimpleName());

    public static void findVulnerabilties(SecurityCheck securityCheck, Path path, String regex, List<Vulnerability> vulnerabilities) {
        try {
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).matches(regex)) {
                    vulnerabilities.add(new Vulnerability(securityCheck, path, i + 1));
                }
            }
        } catch (IOException e) {
            fileScannerLogger.log(Level.DEBUG, "Could not read file " + path + e.getMessage());
        }
    }

}
