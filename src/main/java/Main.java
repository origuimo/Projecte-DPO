import Business.BusinessManager;
import Presentation.PresentationController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        PresentationController presentationController = new PresentationController();
        presentationController.run();
    }
}
