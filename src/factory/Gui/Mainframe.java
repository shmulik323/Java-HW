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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import factory.RaceBuilder;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.EnumContainer.Color;
import utilities.Point;

/**
 * 
 * @author Alex Weizman 314342064, 
 * @author Shmuel moha 204568323
 *
 */

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
	private String arenaChoose;
	private int length;
	private int maxRacers;
	private String arenaFullName;
	private String racerChoose;
	private String racerName;
	private double maxSpeed;
	private double acceleration;
	private String racerFullName;
	private Racer buildRacer;
	private Color color;
	private boolean onGoingRaceFlag = false;
	private JTable infoTable;
	private String tableColumns[]= {"Racer Name","Current Speed","Max Speed","Current X Location","Finished"};
	private Object tableData[][];
	private ArrayList<JLabel> racersPics=new ArrayList<JLabel>();

	private String finish="No";
	public Mainframe() {
		super("Race");
		Race_Panel=new RacePanel();
		Race_Panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		//Race_Panel.setBorder(null);
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
		/**
		 * 	build arena button with action listener that builds the arena.
		 */
		JButton btnBuildArena=new JButton("Build Arena");
		btnBuildArena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arenaButtonActionPreformed(arg0);
			}
		});
		btnBuildArena.setActionCommand("click");
		/**
		 * AddRacer button adds the racer after the info is filled.
		 */
		JButton btnAddRacer=new JButton("Add Racer");
		btnAddRacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				racerButtonActionPreformed(event);
			}
		});
		btnAddRacer.setActionCommand("click");
		/**
		 * start button for the race
		 */
		JButton btnStart=new JButton("Start Race");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					startButtonActionPerformed(e);
				}
				catch(StringIndexOutOfBoundsException e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}


		});
		btnStart.setActionCommand("click");
		JButton btnShowInfo=new JButton("Show Info");
		/**
		 * show info button that shows the racers info in the start middle or end of the race
		 */
		btnShowInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				finish="No";
				infoTable=new JTable(tableData,tableColumns);
				DefaultTableModel dtm=new DefaultTableModel(0,0);	
				dtm.setColumnIdentifiers(tableColumns);
				infoTable.setModel(dtm);
				dtm.addRow(tableColumns);
				for(Racer racer: racers) {
					if(arena.getCompletedRacers().contains(racer))
						finish="Yes";
					else if(arena.getDisabledRacers().contains(racer))
						finish="Disabled";
					dtm.addRow(new Object[] {racer.getName(),new Double(racer.getCurrentSpeed()), 
							racer.getMaxSpeed(),racer.getCurrentLocation().getX(),finish } );
					finish="No";
				}
				dtm.addTableModelListener(new TableModelListener() {

					@Override
					public void tableChanged(TableModelEvent e) {
						dtm.addRow(tableColumns);
						for(Racer racer: racers) {
							if(arena.getCompletedRacers().contains(racer))
								finish="Yes";
							else if(arena.getDisabledRacers().contains(racer))
								finish="Disabled";
							dtm.addRow(new Object[] {racer.getName(),new Double(racer.getCurrentSpeed()), 
									racer.getMaxSpeed(),racer.getCurrentLocation().getX(),finish } );
							finish="No";
						}

					}
				});
				JOptionPane.showMessageDialog(null,infoTable);
			}
		});
		btnShowInfo.setActionCommand("click");
		/**
		 * setting the layout for the frame
		 */
		BorderLayout border=new BorderLayout();
		border.setHgap(10);
		border.setVgap(20);
		getContentPane().setLayout(border);

		/**
		 * arena combo box build
		 */
		cmbArena = new JComboBox<String>();
		for (String string : RacingClassesFinder.getInstance().getArenasNamesList()) {
			cmbArena.addItem(string);
		}

		/**
		 * racer combo box build
		 */
		cmbRacers = new JComboBox<String>();
		for (String string : RacingClassesFinder.getInstance().getRacersNamesList()) {
			cmbRacers.addItem(string);
		}

		/**
		 * racer color combo box build
		 */
		cmbColor = new JComboBox<Color>();
		for (Color string : Arrays.asList(Color.values())) {
			cmbColor.addItem(string);
		}


		getContentPane().add(Race_Panel,BorderLayout.CENTER);
		Race_Panel.setLayout(null);




		/**
		 * setting the toolbar location in the layout
		 */
		getContentPane().add(toolbar,BorderLayout.EAST);
		/**
		 * setting all the components in the arena panel in the toolbar
		 */
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
		txtArenaLength.setText("1000");
		txtArenaLength.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtArenaLength.setMaximumSize(new Dimension(100, 25));
		arenaToolbar.add(txtArenaLength);
		arenaToolbar.add(Box.createVerticalStrut(5));
		lblMaxRacer.setAlignmentX(Component.CENTER_ALIGNMENT);
		arenaToolbar.add(lblMaxRacer);
		txtMaxRacers.setText("8");
		txtMaxRacers.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtMaxRacers.setMaximumSize(new Dimension(100, 25));
		arenaToolbar.add(txtMaxRacers);
		arenaToolbar.add(Box.createVerticalStrut(5));
		btnBuildArena.setAlignmentX(Component.CENTER_ALIGNMENT);
		arenaToolbar.add(Box.createVerticalStrut(5));
		arenaToolbar.add(btnBuildArena);
		arenaToolbar.add(Box.createVerticalStrut(5));

		/**
		 * setting all the components in the racer toolbar panel
		 */
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

		/**
		 * setting the buttons in the start race toolbar panel
		 */
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

		/**
		 * setting the frame size and visibility
		 */
		setSize(1024,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);


	}
	/**
	 * the button action function that adds the arena and throws exceptions if needed
	 * @param e,the action the button got
	 */
	public void arenaButtonActionPreformed(ActionEvent e) {

		String action = e.getActionCommand();
		if (action.equals("click")) {
			try {
				intiArenaFromGui();
				arena = builder.buildArena(arenaFullName,length, maxRacers);
				image = ImageIO.read(Mainframe.class.getResource("icons/arena/"+arenaChoose+".jpg"));
				int newRacePanelWidth =length;
				int newRacePanelHight = (int) (87.5*maxRacers);
				int newFrameWidth= newRacePanelWidth+racerToolbar.getWidth();
				int newFrameHeight = newRacePanelHight;

				if((maxRacers)>=8) {
					Race_Panel.setSize(newRacePanelWidth,newRacePanelHight);
					setSize(newFrameWidth, newFrameHeight);
				}
				Race_Panel.changeImage(image);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				JOptionPane.showMessageDialog(null,"Please enter Values");
			}


		}
	}


	/**
	 * function for initializing the parameters for the arena build
	 */
	public void intiArenaFromGui() throws NumberFormatException{
		Race_Panel.removeAll();
		if(arena !=null) {
			arena.setActiveRacers(new ArrayList<Racer>());
			racers=new ArrayList<Racer>();
			yPlacement=0;
			onGoingRaceFlag=false;
		}
		arenaChoose = (String)cmbArena.getSelectedItem();
		length = Integer.parseInt(txtArenaLength.getText());
		maxRacers = Integer.parseInt(txtMaxRacers.getText());
		arenaFullName = "";
		for(String string:RacingClassesFinder.getInstance().getArenasList()) {
			if(string.contains(arenaChoose)) {
				arenaFullName=string;
			}
		}
	}

	/**
	 * action listener for adding a racer to the race
	 * @param e, the action performed
	 */
	public void racerButtonActionPreformed(ActionEvent e) {

		String action = e.getActionCommand();
		if (action.equals("click")) {
			try {
				initRacerFromGui();

				addRacerAndPaint();


			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException | RacerLimitException | RacerTypeException |StringIndexOutOfBoundsException  e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}

			revalidate();
			repaint();




		}

	}
	/**
	 * function that builds the racer objects and adds them to the neede array lists, if a problem occurs throws exception
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws RacerLimitException
	 * @throws RacerTypeException
	 */
	public void addRacerAndPaint() throws ClassNotFoundException, NoSuchMethodException, InstantiationException,
	IllegalAccessException, InvocationTargetException, RacerLimitException, RacerTypeException {
		buildRacer = builder.buildRacer(racerFullName, racerName, maxSpeed, acceleration, color);
		arena.addRacer(buildRacer);
		racers.add(buildRacer);
		racersPics.add(new JLabel(""));
		ImageIcon imageIcon = new ImageIcon(Mainframe.class.getResource("/factory/Gui/icons/"+racerChoose+color.toString()+".png"));
		imageIcon=new ImageIcon(getScaledImage(imageIcon.getImage(),70,70));
		racersPics.get(racers.indexOf(buildRacer)).setIcon(imageIcon);
		buildRacer.setCurrentLocation(new Point(0,yPlacement*(Arena.getMinYGap())));
		racersPics.get(racers.indexOf(buildRacer)).setBounds(0, yPlacement*(Arena.getMinYGap()), 70, 70);
		Race_Panel.add(racersPics.get(racers.indexOf(buildRacer)));
		yPlacement++;
	}
	/**
	 * gets the racer data from the gui and handles it, throws exception if a problem occurs
	 * @throws StringIndexOutOfBoundsException
	 * @throws RacerLimitException
	 */
	public void initRacerFromGui() throws StringIndexOutOfBoundsException,RacerLimitException {
		if(arena ==null) {
			throw new StringIndexOutOfBoundsException("There is no arena!!!!");
		}
		if(onGoingRaceFlag==true) {
			throw new StringIndexOutOfBoundsException("There is an ongoing race");
		}
		if(txtRacerName.getText().isEmpty() || txtMaxSpeed.getText().isEmpty() || txtAcceleration.getText().isEmpty()
				|| Double.parseDouble(txtMaxSpeed.getText())<0|| Double.parseDouble(txtAcceleration.getText())<0 ) {
			throw  new StringIndexOutOfBoundsException("Invalid Data Input! Please try again.");
		}
		if(arena.getActiveRacers().size()>=arena.getMAX_RACERS()) {
			throw new RacerLimitException("Racer Limit Exceeded.");
		}
		racerChoose = (String)cmbRacers.getSelectedItem();
		racerName = txtRacerName.getText();
		maxSpeed = Double.parseDouble(txtMaxSpeed.getText());
		acceleration = Double.parseDouble(txtAcceleration.getText());
		racerFullName = null;
		buildRacer = null;
		color = (Color)cmbColor.getSelectedItem();
		for(String string:RacingClassesFinder.getInstance().getRacersList()) {
			if(string.contains(racerChoose)) {
				racerFullName=string;
			}
		}


	}
	/**
	 * building the racer icon for the gui
	 * @param srcImg
	 * @param w
	 * @param h
	 * @return
	 */
	private Image getScaledImage(Image srcImg, int w, int h){
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
	/**
	 * action performed for the start button
	 * @param event
	 * @throws StringIndexOutOfBoundsException
	 */
	private void startButtonActionPerformed(ActionEvent event) throws StringIndexOutOfBoundsException {
		if(arena ==null) {
			throw new StringIndexOutOfBoundsException("There is no arena");
		}
		if(onGoingRaceFlag==true) {
			throw new StringIndexOutOfBoundsException("There is an ongoing race");
		}
		if(arena.getActiveRacers().isEmpty()) {
			throw new StringIndexOutOfBoundsException("There is no racers in the arena");
		}
		ExecutorService single = Executors.newSingleThreadExecutor();
		arena.initRace();
		onGoingRaceFlag=true;
		single.submit(new Runnable() {
			@Override
			public synchronized void run() {
				arena.startRace();
				while(!single.isShutdown()) 	{
					racerMove();

					try {
						Thread.sleep(30);
					} catch(InterruptedException e) {
						Thread.currentThread().notify();
					}
				}
				single.shutdown();
			}
		});


	}
	/**
	 * function that moves the racer icon in the gui
	 */
	public synchronized void racerMove() {
		for(Racer racer : racers) {
			if(racersPics.get(racers.indexOf(racer)).getLocation().getX()<(arena.getLength()-100)) {
				racersPics.get(racers.indexOf(racer)).setLocation((int)racer.getCurrentLocation().getX(),(int)racer.getCurrentLocation().getY());
			}
			else {
				racersPics.get(racers.indexOf(racer)).setLocation((int)arena.getLength()-100,(int)racer.getCurrentLocation().getY());
			}
			revalidate();
			repaint();


		}
	}
}
