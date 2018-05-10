package factory.Gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JPanel;

public class RacePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;

	/**
	 * Create the panel.
	 */
	public RacePanel() {

	}
	public RacePanel(Image img) {
		super(new GridLayout());
		this.image = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

    @Override
    protected void paintComponent(Graphics g){ 
        super.paintComponent(g);    
        g.drawImage(image, 0, 0, null);
    } 

    public void changeImage(Image img) {
        this.image = img;
        image = img.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
        repaint();

    }

}