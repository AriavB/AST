import java.util.List;

public class TextOutput implements OutputFormat {

    @Override
    public void print(List<Vulnerability> vulnerabilities) {
        if (vulnerabilities.size() == 0) {
        } else {
            vulnerabilities.stream().forEach(System.out::println);
        }
    }

}
