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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import factory.RaceBuilder;
import game.arenas.Arena;
import game.racers.Racer;
import utilities.Point;

/**
 * 
 * @author Alex Weizman 314342064, 
 * @author Shmuel moha 204568323
 *
 */

public class Mainframe extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private RacePanel Race_Panel;
	private static Arena arena;
	private static RaceBuilder builder = RaceBuilder.getInstance();
	private static ArrayList<Racer> racers=new ArrayList<Racer>();
	private int yPlacement=0;
	private boolean onGoingRaceFlag = false;
	private JTable infoTable;
	private String tableColumns[]= {"Place","Racer Name","Current Speed","Max Speed","Current X Location","Finished"};
	private Object tableData[][];
	private ArrayList<JLabel> racersPics=new ArrayList<JLabel>();

	private String finish="No";
	public Mainframe() {
		super("Race");
		Race_Panel=new RacePanel();
		Race_Panel.setAlignmentY(Component.TOP_ALIGNMENT);
		Race_Panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		Race_Panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		//Race_Panel.setBorder(null);
		Race_Panel.setSize(new Dimension(1000, 700));
		Race_Panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(Race_Panel, BorderLayout.CENTER);
		Race_Panel.setLayout(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		JButton btnBuildArena=new JButton("Build Arena");
		panel.add(btnBuildArena);
		btnBuildArena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arenaButtonActionPreformed(arg0);
			}
		});
		btnBuildArena.setActionCommand("click");
		btnBuildArena.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton btnAddRacer=new JButton("Add Racer");
		panel.add(btnAddRacer);
		btnAddRacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				racerButtonActionPreformed(event);
			}
		});
		btnAddRacer.setActionCommand("click");
		btnAddRacer.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton btnStart=new JButton("Start Race");
		panel.add(btnStart);
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
		btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton btnShowInfo=new JButton("Show Info");
		panel.add(btnShowInfo);
		btnShowInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Collections.sort(getRacers());
				finish="No";
				infoTable=new JTable(tableData,tableColumns);
				DefaultTableModel dtm=new DefaultTableModel(0,0);	
				dtm.setColumnIdentifiers(tableColumns);
				infoTable.setModel(dtm);
				dtm.addRow(tableColumns);
				for(Racer racer: getRacers()) {
					if(getArena().getCompletedRacers().contains(racer))
						finish="Yes";
					else if(getArena().getDisabledRacers().contains(racer))
						finish="Disabled";
					dtm.addRow(new Object[] {getRacers().indexOf(racer),racer.getName(),new Double(racer.getCurrentSpeed()), 
							racer.getMaxSpeed(),racer.getCurrentLocation().getX(),finish } );
					finish="No";
				}
				dtm.addTableModelListener(new TableModelListener() {

					@Override
					public void tableChanged(TableModelEvent e) {
						dtm.addRow(tableColumns);
						Collections.sort(getRacers());
						for(Racer racer: getRacers()) {
							if(getArena().getCompletedRacers().contains(racer))
								finish="Yes";
							else if(getArena().getDisabledRacers().contains(racer))
								finish="Disabled";
							dtm.addRow(new Object[] {getRacers().indexOf(racer),racer.getName(),new Double(racer.getCurrentSpeed()), 
									racer.getMaxSpeed(),racer.getCurrentLocation().getX(),finish } );
							finish="No";
						}

					}
				});
				JOptionPane.showMessageDialog(null,infoTable);
			}
		});
		btnShowInfo.setActionCommand("click");
		btnShowInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

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
			Race_Panel.removeAll();
			Race_Panel.repaint();
			if(arena !=null) {
				arena.setActiveRacers(new ArrayList<Racer>());
				Mainframe.setRacers(new ArrayList<Racer>());
				setyPlacement(0);
				setOnGoingRaceFlag(false);
			}
			BuildArenaDialog dialog = new BuildArenaDialog(this,this.Race_Panel);
			dialog.setVisible(true);

		}
	}
	/**
	 * action listener for adding a racer to the race
	 * @param e, the action performed
	 */
	public void racerButtonActionPreformed(ActionEvent e) {

		String action = e.getActionCommand();
		if (action.equals("click")) {
			if(onGoingRaceFlag==false) {
				AddRacerDialog dialog = new AddRacerDialog(this,this.Race_Panel,this.getArena());
				dialog.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null,"there is a race in action!!");
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
	public Image getScaledImage(Image srcImg, int w, int h){
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
		if(getArena() ==null) {
			throw new StringIndexOutOfBoundsException("There is no arena");
		}
		if(isOnGoingRaceFlag()==true) {
			throw new StringIndexOutOfBoundsException("There is an ongoing race");
		}
		if(getArena().getActiveRacers().isEmpty()) {
			throw new StringIndexOutOfBoundsException("There is no racers in the arena");
		}
		getArena().initRace();
		setOnGoingRaceFlag(true);
		getArena().startRace();

	}
	public void addPicToRace(Racer racer,JLabel label,String racerChoose,String color) {
		int position;
		if(!getRacersPics().contains(label)) {
			position = getyPlacement()*(Arena.getMinYGap());
			getRacersPics().add(label);
		}
		else
			position=label.getVerticalAlignment();
		ImageIcon imageIcon = new ImageIcon(Mainframe.class.getResource("/factory/Gui/icons/"+racerChoose+returnColor(color)+".png"));
		imageIcon=new ImageIcon(getScaledImage(imageIcon.getImage(),70,70));
		getRacersPics().get(arena.getActiveRacers().indexOf(racer)).setIcon(imageIcon);
		racer.setCurrentLocation(new Point(0,yPlacement*(Arena.getMinYGap())));
		getRacersPics().get(arena.getActiveRacers().indexOf(racer)).setBounds(0,position, 70, 70);
		Race_Panel.add(getRacersPics().get(arena.getActiveRacers().indexOf(racer)));
		setyPlacement(getyPlacement() + 1);
		getRacersPics().get(arena.getActiveRacers().indexOf(racer)).repaint();
	}
	/**
	 * @param color
	 * @return
	 */
	public String returnColor(String color) {
		if(color=="RED")
			return "Red";
		else if(color=="BLUE")
			return "Blue";
		else if(color=="YELLOW")
			return "Yellow";
		else if(color=="BLACK")
			return "Black";
		else
			return "Green";
	}
	/**
	 * function that moves the racer icon in the gui
	 */
	public synchronized void racerMove() {
		for(Racer racer : arena.getActiveRacers()) {
			if(getRacersPics().get(getRacers().indexOf(racer)).getLocation().getX()<(getArena().getLength()-100)) {
				getRacersPics().get(getRacers().indexOf(racer)).setLocation((int)racer.getCurrentLocation().getX(),(int)racer.getCurrentLocation().getY());
			}
			else {
				getRacersPics().get(getRacers().indexOf(racer)).setLocation((int)getArena().getLength()-80,(int)racer.getCurrentLocation().getY());
			}
			getRacersPics().get(getRacers().indexOf(racer)).revalidate();
			getRacersPics().get(getRacers().indexOf(racer)).repaint();
			System.out.println(racer.describeRacer());
		}
	}
	@Override
	public synchronized void update(Observable o, Object arg){
		String string = arg.toString();
		switch (string) {
		case "FINISHED":
			arena.getCompletedRacers().add((Racer) o);
			arena.getActiveRacers().remove((Racer)o);
			break;
		case "BROKENDOWN":
			arena.getBrokenRacers().add((Racer) o);
			break;
		case "DISABLED":
			arena.getDisabledRacers().add((Racer)o);
			arena.getActiveRacers().remove((Racer)o);	
			break;
		case "REPAIRED":
			arena.getBrokenRacers().remove((Racer) o);
			break;
		case "Moved":
				racerMove();
		
		default:
			break;
		}

	}
	/**
	 * @return the racers
	 */
	public static ArrayList<Racer> getRacers() {
		return racers;
	}
	/**
	 * @param racers the racers to set
	 */
	public static void setRacers(ArrayList<Racer> racers) {
		Mainframe.racers = racers;
	}
	/**
	 * @return the racersPics
	 */
	public ArrayList<JLabel> getRacersPics() {
		return racersPics;
	}
	/**
	 * @param racersPics the racersPics to set
	 */
	public void setRacersPics(ArrayList<JLabel> racersPics) {
		this.racersPics = racersPics;
	}
	/**
	 * @return the yPlacement
	 */
	public int getyPlacement() {
		return yPlacement;
	}
	/**
	 * @param yPlacement the yPlacement to set
	 */
	public void setyPlacement(int yPlacement) {
		this.yPlacement = yPlacement;
	}
	/**
	 * @return the onGoingRaceFlag
	 */
	public boolean isOnGoingRaceFlag() {
		return onGoingRaceFlag;
	}
	/**
	 * @param onGoingRaceFlag the onGoingRaceFlag to set
	 */
	public void setOnGoingRaceFlag(boolean onGoingRaceFlag) {
		this.onGoingRaceFlag = onGoingRaceFlag;
	}
	/**
	 * @return the arena
	 */
	public static Arena getArena() {
		return arena;
	}
	/**
	 * @param arena the arena to set
	 */
	public static void setArena(Arena arena) {
		Mainframe.arena = arena;
	}
}
