import java.util.List;

public class TextOutput implements OutputFormat {

    @Override
    public void print(List<Vulnerability> vulnerabilities) {
        vulnerabilities.stream().forEach(System.out::println);
    }

}
