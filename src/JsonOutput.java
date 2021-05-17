import java.util.List;

public class JsonOutput implements OutputFormat {

    @Override
    public void print(List<Vulnerability> vulnerabilities) {
        System.out.println("{[");
        for (int i = 0; i < vulnerabilities.size(); i++) {
            if (i == vulnerabilities.size() - 1) {
                System.out.print("\t{\n\t\t\"securityCheck\": \"" + vulnerabilities.get(i).getSecurityCheck().getClass().getSimpleName() 
                    + "\",\n\t\t\"file\": \"" + vulnerabilities.get(i).getFile() + "\",\n\t\t\"line: \"" + vulnerabilities.get(i).getLine() + "\n\t}\n");
            } else {
                System.out.print("\t{\n\t\t\"securityCheck\": \"" + vulnerabilities.get(i).getSecurityCheck().getClass().getSimpleName() 
                + "\",\n\t\t\"file\": \"" + vulnerabilities.get(i).getFile() + "\",\n\t\t\"line: \"" + vulnerabilities.get(i).getLine() + "\n\t},\n");
            }
        }
        System.out.println("]}");
    }

}
