package View;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Play;
import Control.Utility;
import Model.AllyShip;
import Model.BattleCruiser;
import Model.BattleShooter;
import Model.BattleStar;
import Model.EnemyShip;
import Model.Scores;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.nio.file.attribute.AclEntryType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.SwingConstants;

import Model.MasterShip;
/*
 * this class visualises the game
 */
public class GraficalInterface extends JFrame {

	Play theGame = new Play();
	private Utility utility = new Utility();
	private Scores theScores = new Scores();
	private JPanel contentPane;
	private ArrayList<JLabel> theJlabelList = new ArrayList<JLabel>();
	private JLabel lbl_1;
	private JLabel lbl_2;
	private JLabel lbl_3;
	private JLabel lbl_4;
	private JLabel lbl_5;
	private JLabel lbl_6;
	private JLabel lbl_7;
	private JLabel lbl_8;
	private JLabel lbl_9;
	private JLabel lbl_10;
	private JLabel lbl_11;
	private JLabel lbl_12;
	private JLabel lbl_13;
	private JLabel lbl_14;
	private JLabel lbl_15;
	private JLabel lbl_16;
	private JLabel lbl_scores;
	private String initialLabelState = "galaxy far far away";

	/*
	 * serialisation
	 */
	Play afterThegame = null;
	Play beforGame = null;
	String file = "play.ser";
	FileOutputStream fos;
	ObjectOutputStream oos;

	FileInputStream fis;
	ObjectInputStream ois;

	/*
	 * styles and colours for labels
	 */
	Border blackline = BorderFactory.createLineBorder(Color.decode("#66aad1"), 2, true);
	String bumm = "<img src=\"https://icons.iconarchive.com/icons/aha-soft/free-game/64/Explosion-icon.png\" width=\"64\" height=\"64\">";

	/*
	 * too update the labels on the main panel. Loops through the arrayList of
	 * labels and updates their text using html tags inside the jLabel.setTetx()
	 * constructor.
	 */
	public void updatelabels() {
		int[] masterShipPosition = this.theGame.getMasterShipPosition();

		String jLabelText = "<html>";
		String masterShipMode = theGame.getMastersShipmModeAsString(masterShipPosition[0], masterShipPosition[1]);

		for (int i = 0; i < this.theJlabelList.size(); i++) {
			JLabel label = this.theJlabelList.get(i);
			int[] coordinates = utility.getListPossibleCoordinates().get(i);
			ArrayList<AllyShip> allyShipList = theGame.getListOfAllyShips(coordinates[0], coordinates[1]);
			ArrayList<EnemyShip> enemyShipList = theGame.getListOfEnemyShips(coordinates[0], coordinates[1]);

			String additionalText = "";
			label.setText(jLabelText);
			label.setText(label.getText());

			List<AllyShip> allyShipListCopy = new ArrayList<>(allyShipList);
			List<EnemyShip> enemyShipListCopy = new ArrayList<>(enemyShipList);

			for (AllyShip allyShip : allyShipListCopy) {

				String allyShipName = allyShip.getType();
				String allyShipImage = ((MasterShip) allyShip).getImageURL();
				String allyShipMode = theGame.getMastersShipmModeAsString(coordinates[0], coordinates[1]);
				additionalText = additionalText + "<br/>" + allyShipName + allyShipImage + "<br/>" + allyShipMode;

			}

			for (EnemyShip enemyShip : enemyShipListCopy) {
				String enemyShipName = "";
				String enemyShipImage = "";

				if (enemyShip instanceof BattleCruiser) {
					enemyShipName = enemyShip.getType();
					enemyShipImage = ((BattleCruiser) enemyShip).getImageURL();
				}
				if (enemyShip instanceof BattleStar) {
					enemyShipName = enemyShip.getType();
					enemyShipImage = ((BattleStar) enemyShip).getImageURL();
				}
				if (enemyShip instanceof BattleShooter) {
					enemyShipName = enemyShip.getType();
					enemyShipImage = ((BattleShooter) enemyShip).getImageURL();
				}

				additionalText = additionalText + "<br/>" + enemyShipName + enemyShipImage;
			}

			if (theGame.isThereShipFight(coordinates[0], coordinates[1])) {
				additionalText = additionalText + bumm;
			}
			label.setText(label.getText() + additionalText);

			// changing the text of lbl_scores using the OBSERVER pattern
			theGame.registerObserver(theScores);
			theGame.notifyObserver();
			int scores = theScores.getScores();
			this.lbl_scores.setText("Current score: " + scores + "");
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraficalInterface frame = new GraficalInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public GraficalInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFont(new Font("Sans Serif Collection", Font.BOLD, 18));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(GraficalInterface.class.getResource("/resources/StarWars148x148.png")));
		setTitle("Star Wars");
		setResizable(false);
		setBounds(100, 100, 1473, 880);

		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#15202b"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(getlbl_1());
		contentPane.add(getlbl_2());
		contentPane.add(getlbl_3());
		contentPane.add(getlbl_4());
		contentPane.add(getlbl_5());
		contentPane.add(getlbl_6());
		contentPane.add(getlbl_7());
		contentPane.add(getlbl_8());
		contentPane.add(getlbl_9());
		contentPane.add(getlbl_10());
		contentPane.add(getlbl_11());
		contentPane.add(getlbl_12());
		contentPane.add(getlbl_13());
		contentPane.add(getlbl_14());
		contentPane.add(getlbl_15());
		contentPane.add(getlbl_16());

		theJlabelList.add(this.lbl_1);
		theJlabelList.add(this.lbl_2);
		theJlabelList.add(this.lbl_3);
		theJlabelList.add(this.lbl_4);
		theJlabelList.add(this.lbl_5);
		theJlabelList.add(this.lbl_6);
		theJlabelList.add(this.lbl_7);
		theJlabelList.add(this.lbl_8);
		theJlabelList.add(this.lbl_9);
		theJlabelList.add(this.lbl_10);
		theJlabelList.add(this.lbl_11);
		theJlabelList.add(this.lbl_12);
		theJlabelList.add(this.lbl_13);
		theJlabelList.add(this.lbl_14);
		theJlabelList.add(this.lbl_15);
		theJlabelList.add(this.lbl_16);

		// button to start the game. Initialising the mastership, update labels
		JButton btnStart = new JButton("Start Game");
		btnStart.setBounds(36, 697, 305, 119);
		contentPane.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theGame.initializeMasterShip();
				updatelabels();
				btnStart.setEnabled(false);
			}
		});

		// to make the next turn. Moving the ships and updating them
		JButton btnTurn = new JButton("Next turn");
		btnTurn.setBounds(377, 697, 305, 119);
		contentPane.add(btnTurn);

		btnTurn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					theGame.masterShipMoving();
					theGame.initializeEnemyShip();
					updatelabels();
					theGame.enemyShipMoving();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btnTurn, "You did not start the game yet! ");
				}
			}
		});

		// button to change the MasterShip mode
		JButton btnChangeShipMode = new JButton("Change Ship Mode");
		btnChangeShipMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					theGame.changeMastershipMode();
					updatelabels();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(btnTurn, "You did not start the game yet! ");
				}
			}
		});
		btnChangeShipMode.setBounds(715, 697, 305, 119);
		contentPane.add(btnChangeShipMode);

		JLabel lblScores = new JLabel();
		lblScores.setHorizontalAlignment(SwingConstants.CENTER);
		lblScores.setForeground(new Color(255, 255, 255));
		lblScores.setBackground(new Color(255, 255, 255));
		lblScores.setBounds(1202, 725, 175, 63);

		this.lbl_scores = lblScores;
		contentPane.add(lblScores);

		JButton btnSaveGame = new JButton("Save Game");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);

					oos.writeObject(theGame);
					oos.flush();
					oos.close();

				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(btnSaveGame, "No saved game!");
				}catch (IOException e3) {
					e3.printStackTrace();					
				}

			}
		});
		btnSaveGame.setBounds(1030, 697, 129, 50);
		contentPane.add(btnSaveGame);

		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);	
					
					theGame = (Play)ois.readObject();
					ois.close();
					updatelabels();
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(btnLoadGame, "No saved game found!");
				}catch (IOException e3) {
					e3.printStackTrace();
				}catch (ClassNotFoundException e4) {
					e4.printStackTrace();
				}
			}
		});
		btnLoadGame.setBounds(1030, 758, 129, 50);
		contentPane.add(btnLoadGame);

	}

	private JLabel getlbl_1() {
		if (lbl_1 == null) {
			lbl_1 = new JLabel("1");
			lbl_1.setBounds(5, 6, 362, 170);
			lbl_1.setBorder(blackline);
			lbl_1.setForeground(Color.decode("#F5F5F5"));
			lbl_1.setText(initialLabelState);
			lbl_1.setIcon(utility.getGalaxyPictures().get(0));
		}
		return lbl_1;
	}

	private JLabel getlbl_2() {
		if (lbl_2 == null) {
			lbl_2 = new JLabel("2");
			lbl_2.setBounds(367, 6, 362, 170);
			lbl_2.setBorder(blackline);
			lbl_2.setForeground(Color.decode("#F5F5F5"));
			lbl_2.setText(initialLabelState);
			lbl_2.setIcon(utility.getGalaxyPictures().get(1));
		}
		return lbl_2;
	}

	private JLabel getlbl_3() {
		if (lbl_3 == null) {
			lbl_3 = new JLabel("3");
			lbl_3.setBounds(729, 6, 362, 170);
			lbl_3.setBorder(blackline);
			lbl_3.setForeground(Color.decode("#F5F5F5"));
			lbl_3.setText(initialLabelState);
			lbl_3.setIcon(utility.getGalaxyPictures().get(2));
		}
		return lbl_3;
	}

	private JLabel getlbl_4() {
		if (lbl_4 == null) {
			lbl_4 = new JLabel("4");
			lbl_4.setBounds(1091, 6, 362, 170);
			lbl_4.setBorder(blackline);
			lbl_4.setForeground(Color.decode("#F5F5F5"));
			lbl_4.setText(initialLabelState);
			lbl_4.setIcon(utility.getGalaxyPictures().get(3));
		}
		return lbl_4;
	}

	private JLabel getlbl_5() {
		if (lbl_5 == null) {
			lbl_5 = new JLabel("5");
			lbl_5.setBounds(5, 176, 362, 170);
			lbl_5.setBorder(blackline);
			lbl_5.setForeground(Color.decode("#F5F5F5"));
			lbl_5.setText(initialLabelState);
			lbl_5.setIcon(utility.getGalaxyPictures().get(4));
		}
		return lbl_5;
	}

	private JLabel getlbl_6() {
		if (lbl_6 == null) {
			lbl_6 = new JLabel("6");
			lbl_6.setBounds(367, 176, 362, 170);
			lbl_6.setBorder(blackline);
			lbl_6.setForeground(Color.decode("#F5F5F5"));
			lbl_6.setText(initialLabelState);
			lbl_6.setIcon(utility.getGalaxyPictures().get(5));

		}
		return lbl_6;
	}

	private JLabel getlbl_7() {
		if (lbl_7 == null) {
			lbl_7 = new JLabel("7");
			lbl_7.setBounds(729, 176, 362, 170);
			lbl_7.setBorder(blackline);
			lbl_7.setForeground(Color.decode("#F5F5F5"));
			lbl_7.setText(initialLabelState);
			lbl_7.setIcon(utility.getGalaxyPictures().get(6));
		}
		return lbl_7;
	}

	private JLabel getlbl_8() {
		if (lbl_8 == null) {
			lbl_8 = new JLabel("8");
			lbl_8.setBounds(1091, 176, 362, 170);
			lbl_8.setBorder(blackline);
			lbl_8.setForeground(Color.decode("#F5F5F5"));
			lbl_8.setText(initialLabelState);
			lbl_8.setIcon(utility.getGalaxyPictures().get(7));
		}
		return lbl_8;
	}

	private JLabel getlbl_9() {
		if (lbl_9 == null) {
			lbl_9 = new JLabel("9");
			lbl_9.setBounds(5, 346, 362, 170);
			lbl_9.setBorder(blackline);
			lbl_9.setForeground(Color.decode("#F5F5F5"));
			lbl_9.setText(initialLabelState);
			lbl_9.setIcon(utility.getGalaxyPictures().get(8));
		}
		return lbl_9;
	}

	private JLabel getlbl_10() {
		if (lbl_10 == null) {
			lbl_10 = new JLabel("10");
			lbl_10.setBounds(367, 346, 362, 170);
			lbl_10.setBorder(blackline);
			lbl_10.setForeground(Color.decode("#F5F5F5"));
			lbl_10.setText(initialLabelState);
			lbl_10.setIcon(utility.getGalaxyPictures().get(9));
		}
		return lbl_10;
	}

	private JLabel getlbl_11() {
		if (lbl_11 == null) {
			lbl_11 = new JLabel("11");
			lbl_11.setBounds(729, 346, 362, 170);
			lbl_11.setBorder(blackline);
			lbl_11.setForeground(Color.decode("#F5F5F5"));
			lbl_11.setText(initialLabelState);
			lbl_11.setIcon(utility.getGalaxyPictures().get(10));
		}
		return lbl_11;
	}

	private JLabel getlbl_12() {
		if (lbl_12 == null) {
			lbl_12 = new JLabel("12");
			lbl_12.setBounds(1091, 346, 362, 170);
			lbl_12.setBorder(blackline);
			lbl_12.setForeground(Color.decode("#F5F5F5"));
			lbl_12.setText(initialLabelState);
			lbl_12.setIcon(utility.getGalaxyPictures().get(11));
		}
		return lbl_12;
	}

	private JLabel getlbl_13() {
		if (lbl_13 == null) {
			lbl_13 = new JLabel("13");
			lbl_13.setBounds(5, 516, 362, 170);
			lbl_13.setBorder(blackline);
			lbl_13.setForeground(Color.decode("#F5F5F5"));
			lbl_13.setText(initialLabelState);
			lbl_13.setIcon(utility.getGalaxyPictures().get(12));
		}
		return lbl_13;
	}

	private JLabel getlbl_14() {
		if (lbl_14 == null) {
			lbl_14 = new JLabel("14");
			lbl_14.setBounds(367, 516, 362, 170);
			lbl_14.setBorder(blackline);
			lbl_14.setForeground(Color.decode("#F5F5F5"));
			lbl_14.setText(initialLabelState);
			lbl_14.setIcon(utility.getGalaxyPictures().get(13));
		}
		return lbl_14;
	}

	private JLabel getlbl_15() {
		if (lbl_15 == null) {
			lbl_15 = new JLabel("15");
			lbl_15.setBounds(729, 516, 362, 170);
			lbl_15.setBorder(blackline);
			lbl_15.setForeground(Color.decode("#F5F5F5"));
			lbl_15.setText(initialLabelState);
			lbl_15.setIcon(utility.getGalaxyPictures().get(14));
		}
		return lbl_15;
	}

	private JLabel getlbl_16() {
		if (lbl_16 == null) {
			lbl_16 = new JLabel("16");
			lbl_16.setBounds(1091, 516, 362, 170);
			lbl_16.setBorder(blackline);
			lbl_16.setForeground(Color.decode("#F5F5F5"));
			lbl_16.setText(initialLabelState);
			lbl_16.setIcon(utility.getGalaxyPictures().get(15));
		}
		return lbl_16;
	}
}
