import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileScanner {

    public static void findVulnerabilties(SecurityCheck securityCheck, Path path, String regex, List<Vulnerability> vulnerabilities) {
        try {
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).matches(regex)) {
                    vulnerabilities.add(new Vulnerability(securityCheck, path, i));
                }
            }
        } catch (IOException e) {
            System.out.println(e + "Could not open file " + path);
        }
    }

}
