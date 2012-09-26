package juanma;
import java.awt.*;
import javax.swing.*;
import java.io.*;


public class ImagePanel extends JPanel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image image = null;

    public ImagePanel(Image image) {
        this.image = image;
    }
    public ImagePanel() {

    }
    public void setImage(Image filename){
        this.image = filename;
    }

    public Image getImage(Image image){
        return image;
    }

    public void paintComponent(Image i) {
    	
        }
    }

