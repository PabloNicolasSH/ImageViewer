package imageviewer.ui;

import imageviewer.model.Image;

import java.util.Iterator;

public interface ImageDisplay {
    void display(Image image);

    Image currentImage();
}
