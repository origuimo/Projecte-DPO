import Business.BusinessManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BusinessManager businessManager = new BusinessManager();
        businessManager.run();
    }
}
