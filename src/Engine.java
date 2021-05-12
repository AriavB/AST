import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Engine {

    private Path sourceCodePath;
    private List<SecurityCheck> securityChecks;

    public Engine(Path sourceCodePath, List<SecurityCheck> securityChecks) {
        this.sourceCodePath = sourceCodePath;
        this.securityChecks = securityChecks;
    }

    public Report scan() {
        List<Vulnerability> vulnerabilities = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executer = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try (Stream<Path> files = Files.walk(sourceCodePath)) {
            files
            .filter(Files::isReadable)
            .filter(Files::isRegularFile)
            .forEach(path -> executer.submit(() -> {
                for (SecurityCheck securityCheck : securityChecks) {
                    securityCheck.analyze(path, vulnerabilities);
                }
            }));
        } catch (IOException e) {
            System.err.println("Could not access path " + sourceCodePath);
        }

        executer.shutdown();
        try {
            executer.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("Not all vulnerabilities detected.");
        }
        
        return new Report(vulnerabilities);
    }

}
