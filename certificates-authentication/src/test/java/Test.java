import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder code = new BCryptPasswordEncoder();
        String encoded = code.encode("admin");
        System.out.println(encoded);
    }
}
