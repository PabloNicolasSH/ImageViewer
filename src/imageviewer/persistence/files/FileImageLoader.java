package imageviewer.persistence.files;

import imageviewer.model.Image;
import imageviewer.persistence.ImageLoader;

import java.io.*;

public class FileImageLoader implements ImageLoader {

    private final static String[] ImageExtensions = new String[]{"jpg", "png", "bmp"};
    private final File[] files;

    public FileImageLoader(String folder) {
        this.files = new File(folder).listFiles(withImageExtension());
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int index){
        return  new Image(){
            @Override
            public byte[] bitmap() {
                try {
                    FileInputStream file = new FileInputStream(files[index]);
                    return read(file);
                } catch (IOException e){return new byte[0];}
            }

            @Override
            public Image next() {
                return (index < files.length - 1) ? imageAt(index+1) : imageAt(0);
            }

            @Override
            public Image prev() {
                return (index > 0) ? imageAt(index-1) : imageAt(files.length-1);
            }

            private byte[] read(FileInputStream file) throws IOException {
                byte[] buffer = new byte[4096];
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                while(true){
                    int length = file.read(buffer);
                    if(length < 0) break;
                    outputStream.write(buffer, 0, length);
                }
                return outputStream.toByteArray();
            }

        };
    }

    private FilenameFilter withImageExtension() {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                for (String extension : ImageExtensions)
                    if (name.endsWith(extension)) return true;
                return false;
            }
        };
    }
}
