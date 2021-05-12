import java.util.List;

public class JsonOutput implements OutputFormat {

    @Override
    public void print(List<Vulnerability> vulnerabilities) {
        for (Vulnerability vulnerability : vulnerabilities) {
            System.out.print("{\n\tsecurityCheck: " + vulnerability.getSecurityCheck() + "\n\tfile: " + 
                vulnerability.getFile() + "\n\tline: " + vulnerability.getLine() + "\n}\n");
        }
    }

}
