package factory.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import factory.ArenaFactory;
import game.arenas.Arena;
import game.racers.Racer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class BuildArenaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblChooseArena;
	private JComboBox<String> cmbArena;
	private JTextField txtArenaLength;
	private JTextField txtMaxRacers;
	private RacePanel racePan;
	private Arena arena;
	private Mainframe FRAME;
	private String arenaChoose;
	private int length;
	private int maxRacers;
	private String arenaFullName;
	private BufferedImage image;


	/**
	 * Create the dialog.
	 */
	public BuildArenaDialog(Mainframe mainframe, RacePanel race_Panel) {
		racePan=race_Panel;
		FRAME=mainframe;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblChooseArena = new JLabel("Choose Arena:");
		}
		
		cmbArena = new JComboBox<String>();
		for (String string : RacingClassesFinder.getInstance().getArenasNamesList()) {
			cmbArena.addItem(string);
		}
		JLabel lblArenaLength = new JLabel("Arena Length:");
		txtArenaLength = new JTextField();
		txtArenaLength.setColumns(10);
		JLabel lblMaxRacers = new JLabel("Max Racers:");
		txtMaxRacers = new JTextField();
		txtMaxRacers.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(132, Short.MAX_VALUE)
					.addComponent(lblChooseArena)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cmbArena, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(169))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblArenaLength)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtArenaLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMaxRacers)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtMaxRacers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChooseArena)
						.addComponent(cmbArena, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblArenaLength)
						.addComponent(txtArenaLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaxRacers)
						.addComponent(txtMaxRacers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(138, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							arenaButtonActionPreformed(e);
						} catch (SecurityException | IllegalArgumentException | IOException e1) {
							JOptionPane.showMessageDialog(null,"Please enter Values");
						}
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			}
		}
	}
	/**
	 * the button action function that adds the arena and throws exceptions if needed
	 * @param e,the action the button got
	 * @throws IOException 
	 */
	public void arenaButtonActionPreformed(ActionEvent e) throws IOException {

		String action = e.getActionCommand();
		if (action.equals("OK")) {
				intiArenaFromGui();
				Mainframe.setArena(new ArenaFactory().getArena(arenaChoose));
				Mainframe.getArena().setLength(length);
				Mainframe.getArena().setMAX_RACERS(maxRacers);
				image = ImageIO.read(Mainframe.class.getResource("icons/arena/"+arenaChoose+".jpg"));
				int newRacePanelWidth =length;
				int newRacePanelHight = (int) (87.5*maxRacers);
				int newFrameWidth= newRacePanelWidth;
				int newFrameHeight = newRacePanelHight+90;

				if((maxRacers)>=8) {
					racePan.setSize(newRacePanelWidth,newRacePanelHight);
					FRAME.setSize(newFrameWidth, newFrameHeight);
				}
				racePan.changeImage(image);
		}
	}


	/**
	 * function for initializing the parameters for the arena build
	 */
	public void intiArenaFromGui(){
		racePan.removeAll();
		if(arena !=null) {
			arena.setActiveRacers(new ArrayList<Racer>());
			Mainframe.setRacers(new ArrayList<Racer>());
			FRAME.setyPlacement(0);
			FRAME.setOnGoingRaceFlag(false);
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
}
