package factory.Gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import factory.RaceBuilder;
import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer.Color;


public class Mainframe extends JFrame {
	private static final long serialVersionUID = 1L;
	private RacePanel Race_Panel;
	private JPanel toolbar;
	private final JLabel lblArenaChoose=new JLabel("Choose Arena:");
	private final JLabel lblArenaLength=new JLabel("Arena Length:");
	private final JLabel lblMaxRacer=new JLabel("Max Racers Number:");
	private final JLabel lblChooseRacer=new JLabel("Choose Racer:");
	private final JLabel lblChooseColor=new JLabel("Choose Color:");
	private final JLabel lblRacerName=new JLabel("Racer Name:");
	private final JLabel lblMaxSpeed=new JLabel("Max Speed:");
	private final JLabel lblAcceleration=new JLabel("Acceleration:");
	private JTextField txtArenaLength=new JTextField();
	private JTextField txtMaxRacers=new JTextField();
	private JTextField txtRacerName=new JTextField();
	private JTextField txtMaxSpeed=new JTextField();
	private JTextField txtAcceleration=new JTextField();
	private JComboBox<String> cmbArena ;
	private JComboBox<String> cmbRacers ;
	private JComboBox<Color> cmbColor ;
	private static Arena arena;
	private static RaceBuilder builder = RaceBuilder.getInstance();
	private static ArrayList<Racer> racers=new ArrayList<Racer>();
	private BufferedImage image;
	private JPanel arenaToolbar;
	private JPanel racerToolbar;
	private JPanel Start_InfoToolbar;
	private int yPlacement=0;
	private static ArrayList<JLabel> racersPics =new ArrayList<JLabel>();

	public Mainframe() {
		super("Race");
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		Race_Panel=new RacePanel();
		Race_Panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		Race_Panel.setBorder(null);
		Race_Panel.setSize(new Dimension(1000, 700));
		Race_Panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		toolbar=new JPanel();
		toolbar.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (java.awt.Color) new java.awt.Color(0, 0, 0)), new MatteBorder(0, 1, 1, 0, (java.awt.Color) new java.awt.Color(0, 0, 0))));
		arenaToolbar = new JPanel();
		arenaToolbar.setBorder(new MatteBorder(0, 0, 1, 0, (java.awt.Color) new java.awt.Color(0, 0, 0)));
		racerToolbar = new JPanel();
		racerToolbar.setBorder(new MatteBorder(0, 0, 1, 0, (java.awt.Color) new java.awt.Color(0, 0, 0)));
		Start_InfoToolbar = new JPanel();
		Start_InfoToolbar.setBorder(null);

		JButton btnBuildArena=new JButton("Build Arena");
		btnBuildArena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arenaButtonActionPreformed(arg0);
			}
		});
		btnBuildArena.setActionCommand("click");
		JButton btnAddRacer=new JButton("Add Racer");
		btnAddRacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				racerButtonActionPreformed(event);
			}
		});
		btnAddRacer.setActionCommand("click");
		JButton btnStart=new JButton("Start Race");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButtonActionPerformed(e);
			}

		
		});
		btnStart.setActionCommand("click");
		JButton btnShowInfo=new JButton("show info");

		BorderLayout border=new BorderLayout();
		border.setHgap(10);
		border.setVgap(20);
		getContentPane().setLayout(border);

		cmbArena = new JComboBox<String>();
		for (String string : RacingClassesFinder.getInstance().getArenasNamesList()) {
			cmbArena.addItem(string);
		}


		cmbRacers = new JComboBox<String>();
		for (String string : RacingClassesFinder.getInstance().getRacersNamesList()) {
			cmbRacers.addItem(string);
		}

		cmbColor = new JComboBox<Color>();
		for (Color string : Arrays.asList(Color.values())) {
			cmbColor.addItem(string);
		}

		getContentPane().add(Race_Panel,BorderLayout.CENTER);
		Race_Panel.setLayout(null);

		

		getContentPane().add(toolbar,BorderLayout.EAST);

		arenaToolbar.setLayout(new BoxLayout(arenaToolbar, BoxLayout.Y_AXIS));
		arenaToolbar.add(Box.createVerticalStrut(5));
		lblArenaChoose.setAlignmentX(Component.CENTER_ALIGNMENT);
		arenaToolbar.add(lblArenaChoose);
		cmbArena.setAlignmentX(Component.CENTER_ALIGNMENT);
		cmbArena.setMaximumSize(new Dimension(100, 25));
		arenaToolbar.add(cmbArena);
		arenaToolbar.add(Box.createVerticalStrut(5));
		lblArenaLength.setAlignmentX(Component.CENTER_ALIGNMENT);
		arenaToolbar.add(lblArenaLength);
		txtArenaLength.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtArenaLength.setMaximumSize(new Dimension(100, 25));
		arenaToolbar.add(txtArenaLength);
		arenaToolbar.add(Box.createVerticalStrut(5));
		lblMaxRacer.setAlignmentX(Component.CENTER_ALIGNMENT);
		arenaToolbar.add(lblMaxRacer);
		txtMaxRacers.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtMaxRacers.setMaximumSize(new Dimension(100, 25));
		arenaToolbar.add(txtMaxRacers);
		arenaToolbar.add(Box.createVerticalStrut(5));
		btnBuildArena.setAlignmentX(Component.CENTER_ALIGNMENT);
		arenaToolbar.add(Box.createVerticalStrut(5));
		arenaToolbar.add(btnBuildArena);
		arenaToolbar.add(Box.createVerticalStrut(5));


		racerToolbar.setLayout(new BoxLayout(racerToolbar, BoxLayout.Y_AXIS));
		racerToolbar.add(Box.createVerticalStrut(5));
		lblChooseRacer.setAlignmentX(Component.CENTER_ALIGNMENT);
		racerToolbar.add(lblChooseRacer);
		cmbRacers.setAlignmentX(Component.CENTER_ALIGNMENT);
		cmbRacers.setMaximumSize(new Dimension(100, 25));
		racerToolbar.add(cmbRacers);
		racerToolbar.add(Box.createVerticalStrut(5));
		lblChooseColor.setAlignmentX(Component.CENTER_ALIGNMENT);
		racerToolbar.add(lblChooseColor);
		cmbColor.setAlignmentX(Component.CENTER_ALIGNMENT);
		cmbColor.setMaximumSize(new Dimension(100, 25));
		racerToolbar.add(cmbColor);
		racerToolbar.add(Box.createVerticalStrut(5));
		lblRacerName.setAlignmentX(Component.CENTER_ALIGNMENT);
		racerToolbar.add(lblRacerName);
		txtRacerName.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtRacerName.setMaximumSize(new Dimension(100, 25));
		racerToolbar.add(txtRacerName);
		racerToolbar.add(Box.createVerticalStrut(5));
		lblMaxSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
		racerToolbar.add(lblMaxSpeed);
		txtMaxSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtMaxSpeed.setMaximumSize(new Dimension(100, 25));
		racerToolbar.add(txtMaxSpeed);
		racerToolbar.add(Box.createVerticalStrut(5));
		lblAcceleration.setAlignmentX(Component.CENTER_ALIGNMENT);
		racerToolbar.add(lblAcceleration);
		txtAcceleration.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtAcceleration.setMaximumSize(new Dimension(100, 25));
		racerToolbar.add(txtAcceleration);
		racerToolbar.add(Box.createVerticalStrut(5));
		btnAddRacer.setAlignmentX(Component.CENTER_ALIGNMENT);
		racerToolbar.add(btnAddRacer);
		racerToolbar.add(Box.createVerticalStrut(5));

		Start_InfoToolbar.setLayout(new BoxLayout(Start_InfoToolbar, BoxLayout.Y_AXIS));
		Start_InfoToolbar.add(Box.createVerticalStrut(5));
		btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		Start_InfoToolbar.add(btnStart);
		Start_InfoToolbar.add(Box.createVerticalStrut(5));
		btnShowInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		Start_InfoToolbar.add(btnShowInfo);
		Start_InfoToolbar.add(Box.createVerticalStrut(5));
		GroupLayout gl_toolbar = new GroupLayout(toolbar);
		gl_toolbar.setHorizontalGroup(
				gl_toolbar.createParallelGroup(Alignment.LEADING)
				.addComponent(Start_InfoToolbar, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
				.addComponent(racerToolbar, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
				.addComponent(arenaToolbar, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
				);
		gl_toolbar.setVerticalGroup(
				gl_toolbar.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_toolbar.createSequentialGroup()
						.addComponent(arenaToolbar, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(racerToolbar, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(Start_InfoToolbar, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
						.addGap(4))
				);
		toolbar.setLayout(gl_toolbar);

		setSize(1024,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);


	}
	public void arenaButtonActionPreformed(ActionEvent e) {

		String action = e.getActionCommand();
		if (action.equals("click")) {
			String arenaChoose=(String)cmbArena.getSelectedItem();
			String length=txtArenaLength.getText();
			String maxRacers=txtMaxRacers.getText();
			String arenaFullName = null;
			for(String string:RacingClassesFinder.getInstance().getArenasList()) {
				if(string.contains(arenaChoose)) {
					arenaFullName=string;
				}
			}
			try {
				arena = builder.buildArena(arenaFullName,Double.parseDouble(length), Integer.parseInt(maxRacers));
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				System.out.println("Unable to build arena!");
			}
			System.out.println(arena.toString());

			try {
				image = ImageIO.read(Mainframe.class.getResource("icons/arena/"+arenaChoose+".jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int newRacePanelWidth = Integer.parseInt(length);
			int newRacePanelHight = (int) (87.5*Integer.parseInt(maxRacers));
			int newFrameWidth= newRacePanelWidth+racerToolbar.getWidth();
			int newFrameHeight = newRacePanelHight;
			
			if(Integer.parseInt(maxRacers)>=8) {
				Race_Panel.setSize(newRacePanelWidth,newRacePanelHight);
				setSize(newFrameWidth, newFrameHeight);
			}
			Race_Panel.changeImage(image);




		}

	}
	public void racerButtonActionPreformed(ActionEvent e) {

		String action = e.getActionCommand();
		if (action.equals("click")) {
			String racerChoose=(String)cmbRacers.getSelectedItem();
			String racerName=txtRacerName.getText();
			double maxSpeed=Double.parseDouble(txtMaxSpeed.getText());
			double acceleration=Double.parseDouble(txtAcceleration.getText());
			String racerFullName = null;
			Racer buildRacer=null;
			Color color =(Color)cmbColor.getSelectedItem();
			for(String string:RacingClassesFinder.getInstance().getRacersList()) {
				if(string.contains(racerChoose)) {
					racerFullName=string;
				}
			}
			try {
				buildRacer = builder.buildRacer(racerFullName, racerName, maxSpeed, acceleration, color);
				racers.add(buildRacer);
				
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				System.out.println("Unable to build racer!");
			}



			JLabel label =new JLabel("");
			ImageIcon imageIcon = new ImageIcon(Mainframe.class.getResource("/factory/Gui/icons/"+racerChoose+color.toString()+".png"));
			imageIcon=new ImageIcon(getScaledImage(imageIcon.getImage(),70,70));
			label.setIcon(imageIcon);
			label.setBounds(0, yPlacement*(arena.getMinYGap()+70), 70, 70);
			racersPics.add(label);
			Race_Panel.add(racersPics.get(yPlacement));
			yPlacement++;
			revalidate();
			repaint();




		}

	}
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	private void startButtonActionPerformed(ActionEvent event) {
		
		
	}
}
