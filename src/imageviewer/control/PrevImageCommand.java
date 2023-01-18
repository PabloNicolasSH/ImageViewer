package imageviewer.control;

import imageviewer.ui.ImageDisplay;

public class PrevImageCommand implements Command{

    private final ImageDisplay imageDisplay;

    public PrevImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public String name() {
        return "prev";
    }

    @Override
    public void execute() {
        imageDisplay.display(imageDisplay.currentImage().prev());
    }
}
