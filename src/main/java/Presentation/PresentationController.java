package Presentation;

public class PresentationController {

    private final Controller controller;

    public PresentationController() {this.controller = new Controller();}

    public Controller getUiController() {
        return controller;
    }

}
