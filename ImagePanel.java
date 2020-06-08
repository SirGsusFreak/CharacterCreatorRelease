package CharacterCreator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/** Decompiled from TheFlyingMinotaur's.jar by cyaggi.
 *  A JPanel that displays an image read from a file. */
public class ImagePanel extends JPanel
{
    private static final long serialVersionUID = 1690117328263205325L;
    private BufferedImage image;

    public ImagePanel(String filename) {
        try {
            this.image = ImageIO.read(new File(filename));
        } catch (IOException iOException) {
            /* Do nothing. */
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this);
    }

    protected void setImage(BufferedImage newImage) { this.image = newImage; }
}
