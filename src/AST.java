import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AST {

    private static Map<String, SecurityCheck> securityChecksMap;
    private static Map<String, OutputFormat> outputFormatsMap;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("AST [parameters] [path] [output]");
            return;
        } 

        Path sourceCodePath = Paths.get(args[args.length - 2]);
        if (!Files.exists(sourceCodePath)) {
            System.err.println("Please enter a valid path.");
            return;
        }
        
        initSecurityChecksMap();
        
        List<SecurityCheck> securityChecks = new ArrayList<>();
        for (int i = 0; i < args.length - 2; i++) {
            SecurityCheck securityCheck = securityChecksMap.get(args[i]);
            if (securityCheck == null) {
                System.out.println("Parameter " + args[i] + " is not a valid check.");
            } else {
                securityChecks.add(securityCheck);
            }
        }

        if (securityChecks.isEmpty()) {
            System.err.println("Please look at readme file for supported checks.");
            return;
        }

        initOutputFormatsMap();
        
        OutputFormat outputFormat = outputFormatsMap.get(args[args.length - 1]);
        if (outputFormat == null) {
            System.err.println("Please enter a supported output format.");
            return;
        }

        Engine securityCodeScanner = new Engine(sourceCodePath, securityChecks);
        Report report = securityCodeScanner.scan();

        if (report.hasVulnerabilities()) {
            report.printOutput(outputFormat);
        } else {
            System.out.println("No vulnerabilities found for source code path " + sourceCodePath);
        }
    }

    private static void initOutputFormatsMap() {
        outputFormatsMap = new HashMap<>();
        outputFormatsMap.put("text", new TextOutput());
        outputFormatsMap.put("json", new JsonOutput());
    }

    private static void initSecurityChecksMap() {
        securityChecksMap = new HashMap<>();
        securityChecksMap.put("-script", new CrossSiteScripting());
        securityChecksMap.put("-sensitive", new SensitiveDataExposure());
        securityChecksMap.put("-sql", new SqlInjection());
    }

}
