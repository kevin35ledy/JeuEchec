/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jchess.display.windows;

import jchess.JChessApp;
import jchess.core.Chessboard;
import jchess.core.Game;
import jchess.core.Player;
import jchess.core.AI.GloutonAI;
import jchess.core.AI.MinMaxAI;
import jchess.core.AI.RandomAI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.TextListener;
import java.awt.event.TextEvent;
import java.awt.*;
import javax.swing.text.BadLocationException;
import jchess.utils.Settings;
import org.apache.log4j.Logger;

/**
 * @author: Mateusz Sławomir Lach ( matlak, msl ) Class responsible for drawing
 *          the fold with local game settings
 */
public class DrawLocalSettings extends JPanel implements ActionListener, TextListener {

	private static final Logger LOG = Logger.getLogger(DrawLocalSettings.class);

	JDialog parent;// needet to close newGame window
	JComboBox color;// to choose color of player
	JComboBox chooseAI1; // to choose which AI
	JComboBox chooseAI2; // to choose which AI
	JRadioButton oponentComp;// choose oponent
	JRadioButton oponentHuman;// choose oponent (human)
	JRadioButton CompVsComp;
	ButtonGroup oponentChoos;// group 4 radio buttons
	JFrame localPanel;
	JLabel compLevLab;
	JSlider computerLevel;// slider to choose jChess Engine level
	JTextField firstName;// editable field 4 nickname
	JTextField secondName;// editable field 4 nickname
	JLabel firstNameLab;
	JLabel secondNameLab;
	JCheckBox upsideDown;// if true draw chessboard upsideDown(white on top)
	GridBagLayout gbl;
	GridBagConstraints gbc;
	Container cont;
	JSeparator sep;
	JButton okButton;
	JCheckBox timeGame;
	JComboBox time4Game;
	String colors[] = { Settings.lang("white"), Settings.lang("black") };
	String AI[] = { "Random", "Glouton", "Min Max" };

	String times[] = { "1", "3", "5", "8", "10", "15", "20", "25", "30", "60", "120", "200" };

	/**
	 * Method witch is checking correction of edit tables
	 * 
	 * @param e
	 *            Object where is saving this what contents edit tables
	 */
	@Override
	public void textValueChanged(TextEvent e) {
		Object target = e.getSource();
		if (target == this.firstName || target == this.secondName) {
			JTextField temp = new JTextField();
			if (target == this.firstName) {
				temp = this.firstName;
			} else if (target == this.secondName) {
				temp = this.secondName;
			}

			int len = temp.getText().length();
			if (len > 8) {
				try {
					temp.setText(temp.getText(0, 7));
				} catch (BadLocationException exc) {
					LOG.error("BadLocationException: Something wrong in editables, msg: " + exc.getMessage()
							+ " object: " + exc);
				}
			}
		}
	}

	/**
	 * Method responsible for changing the options which can make a player when
	 * he want to start new local game
	 * 
	 * @param e
	 *            where is saving data of performed action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		if (target == this.oponentComp) // toggle enabled of controls depends of
										// oponent (if computer)
		{
			this.firstName.setEnabled(true);
			this.computerLevel.setEnabled(true);// enable level of computer
												// abilities
			this.secondName.setEnabled(false);// disable field with name of
												// player2
			this.chooseAI1.setEnabled(false);
			this.chooseAI2.setEnabled(false);
		} else if (target == this.oponentHuman) // else if oponent will be HUMAN
		{
			this.computerLevel.setEnabled(false);// disable level of computer
			this.firstName.setEnabled(true); // abilities
			this.secondName.setEnabled(true);// enable field with name of
												// player2
			this.chooseAI1.setEnabled(false);
			this.chooseAI2.setEnabled(false);
		} else if (target == this.CompVsComp) // else if oponent will be HUMAN
		{
			this.computerLevel.setEnabled(false);
			this.firstName.setEnabled(false);
			this.secondName.setEnabled(false);
			this.chooseAI1.setEnabled(true);
			this.chooseAI2.setEnabled(true);
			this.firstName.setText("computer1");
			this.secondName.setText("computer2");

		} else if (target == this.okButton) // if clicked OK button (on finish)
		{
			if (this.firstName.getText().length() > 9) // make names short to 10
														// digits
			{
				this.firstName.setText(this.trimString(firstName, 9));
			}
			if (this.secondName.getText().length() > 9) // make names short to
														// 10 digits
			{
				this.secondName.setText(this.trimString(secondName, 9));
			}
			if (!this.oponentComp.isSelected()
					&& (this.firstName.getText().length() == 0 || this.secondName.getText().length() == 0)) {
				JOptionPane.showMessageDialog(this, Settings.lang("fill_names"));
				return;
			}
			if ((this.oponentComp.isSelected() && this.firstName.getText().length() == 0)) {
				JOptionPane.showMessageDialog(this, Settings.lang("fill_name"));
				return;
			}
			Game newGUI = JChessApp.getJavaChessView()
					.addNewTab(this.firstName.getText() + " vs " + this.secondName.getText());

			Settings sett = newGUI.getSettings();// sett local settings variable
			Player pl1 = sett.getPlayerWhite();// set local player variable
			Player pl2 = sett.getPlayerBlack();// set local player variable
			sett.setGameMode(Settings.gameModes.newGame);
			// TODO: investigate and refactor
			if (this.color.getSelectedItem().equals("White")) // if first
																// player is
																// white
			{
				pl1.setName(this.firstName.getText());// set name of player
				pl2.setName(this.secondName.getText());// set name of player
			} else // else change names
			{
				pl2.setName(this.firstName.getText());// set name of player
				pl1.setName(this.secondName.getText());// set name of player
			}

			pl1.setType(Player.playerTypes.localUser);// set type of player
			pl2.setType(Player.playerTypes.localUser);// set type of player
			sett.setGameType(Settings.gameTypes.local);
			if (this.oponentComp.isSelected()) // if computer oponent is checked
			{
				pl2.setType(Player.playerTypes.computer);
				switch (this.computerLevel.getValue()) {
				case 1:
					System.out.println("Algo Random");
					pl2.setAI(new RandomAI());
					break;
				case 2:
					System.out.println("Algo Glouton");
					pl2.setAI(new GloutonAI());
					break;
				default:
					System.out.println("Algo Minmax");
					pl2.setAI(new MinMaxAI());
					;
				}
			}
			if (this.CompVsComp.isSelected()) { // if Computer vs Computer is
												// selected
				System.out.println("PLAYER = " + pl1.getName());
				pl2.setType(Player.playerTypes.computer);
				pl1.setType(Player.playerTypes.computer);
				switch (this.chooseAI1.getSelectedItem().toString()){
					case "Random":
						pl1.setAI(new RandomAI());
						break;
					case "Glouton":
						pl1.setAI(new GloutonAI());
						break;
					case "Min Max":
						pl1.setAI(new MinMaxAI());
						break;
				}
				switch (this.chooseAI2.getSelectedItem().toString()){
				case "Random":
					pl1.setAI(new RandomAI());
					break;
				case "Glouton":
					pl1.setAI(new GloutonAI());
					break;
				case "Min Max":
					pl1.setAI(new MinMaxAI());
					break;
			}
			
			}
			sett.setUpsideDown(this.upsideDown.isSelected());
			if (this.timeGame.isSelected()) // if timeGame is checked
			{
				String value = this.times[this.time4Game.getSelectedIndex()];// set
																				// time
																				// for
																				// game
				Integer val = new Integer(value);
				sett.setTimeForGame((int) val * 60);// set time for game and
													// mult it to seconds
				newGUI.getGameClock().setTimes(sett.getTimeForGame(), sett.getTimeForGame());
				newGUI.getLocalSettingsView().getTimeGame().setSelected(true);
				newGUI.getLocalSettingsView().getTime4Game().setSelectedIndex(this.time4Game.getSelectedIndex());
				newGUI.getLocalSettingsView().getTimeGame().setSelected(true);
			}
			LOG.debug("this.time4Game.getActionCommand(): " + this.time4Game.getActionCommand());
			LOG.debug("****************\nStarting new game: " + pl1.getName() + " vs. " + pl2.getName()
					+ "\ntime 4 game: " + sett.getTimeForGame() + "\ntime limit set: " + sett.isTimeLimitSet()
					+ "\nwhite on top?: " + sett.isUpsideDown() + "\n****************");// 4test

			this.parent.setVisible(false);// hide parent
			JChessApp.getJavaChessView().getActiveTabGame().repaint();
			JChessApp.getJavaChessView().setActiveTabGame(JChessApp.getJavaChessView().getNumberOfOpenedTabs() - 1);
			String value = this.times[this.time4Game.getSelectedIndex()];// set
			// time for game
			Integer val = new Integer(value);
			newGUI.newGame();// start new Game
			
		}
	}

	public DrawLocalSettings(JDialog parent) {
		super();
		// this.setA//choose oponent
		this.parent = parent;
		this.color = new JComboBox(colors);
		this.chooseAI1 = new JComboBox(AI);
		this.chooseAI2 = new JComboBox(AI);
		this.gbl = new GridBagLayout();
		this.gbc = new GridBagConstraints();
		this.sep = new JSeparator();
		this.okButton = new JButton(Settings.lang("ok"));
		this.compLevLab = new JLabel(Settings.lang("computer_level"));

		this.firstName = new JTextField("", 10);
		this.firstName.setSize(new Dimension(200, 50));
		this.secondName = new JTextField("", 10);
		this.secondName.setSize(new Dimension(200, 50));
		this.firstNameLab = new JLabel(Settings.lang("first_player_name") + ": ");
		this.secondNameLab = new JLabel(Settings.lang("second_player_name") + ": ");
		this.oponentChoos = new ButtonGroup();
		this.computerLevel = new JSlider();
		this.upsideDown = new JCheckBox(Settings.lang("upside_down"));
		this.timeGame = new JCheckBox(Settings.lang("time_game_min"));
		this.time4Game = new JComboBox(times);

		this.oponentComp = new JRadioButton(Settings.lang("against_computer"), false);
		this.oponentHuman = new JRadioButton(Settings.lang("against_other_human"), true);
		this.CompVsComp = new JRadioButton(Settings.lang("Computer VS Computer"), false);

		this.setLayout(gbl);
		this.oponentComp.addActionListener(this);
		this.oponentHuman.addActionListener(this);
		this.CompVsComp.addActionListener(this);
		this.okButton.addActionListener(this);

		this.secondName.addActionListener(this);

		this.oponentChoos.add(oponentComp);
		this.oponentChoos.add(oponentHuman);
		this.oponentChoos.add(CompVsComp);
		this.computerLevel.setEnabled(true);
		this.computerLevel.setMaximum(3);
		this.computerLevel.setMinimum(1);

		this.gbc.insets = new Insets(10, 10, 13, 13);

		this.gbl.setConstraints(oponentComp, gbc);
		this.add(oponentComp);

		this.gbl.setConstraints(oponentHuman, gbc);
		this.add(oponentHuman);

		this.gbl.setConstraints(CompVsComp, gbc);
		this.add(CompVsComp);

		this.gbc.gridy = 1;

		this.gbl.setConstraints(firstNameLab, gbc);
		this.add(firstNameLab);

		this.gbc.gridy = 2;

		this.gbl.setConstraints(firstName, gbc);
		this.add(firstName);

		this.gbl.setConstraints(color, gbc);
		this.add(color);

		this.gbl.setConstraints(chooseAI1, gbc);
		this.add(chooseAI1);

		this.gbc.gridy = 3;

		this.gbl.setConstraints(secondNameLab, gbc);
		this.add(secondNameLab);

		this.gbc.gridy = 4;

		this.gbl.setConstraints(secondName, gbc);
		this.add(secondName);

		this.gbl.setConstraints(chooseAI2, gbc);
		this.add(chooseAI2);

		this.gbc.gridy = 5;

		this.gbl.setConstraints(compLevLab, gbc);
		this.gbc.gridx = 0;
		this.add(compLevLab);

		this.gbc.gridy = 6;

		this.gbl.setConstraints(computerLevel, gbc);
		this.gbc.gridx = 0;
		this.gbc.weightx = 10;
		this.add(computerLevel);

		this.gbc.gridy = 7;

		this.gbl.setConstraints(upsideDown, gbc);

		this.gbc.gridx = 0;
		this.add(upsideDown);

		this.gbc.gridy = 8;

		this.gbc.gridy = 9;

		this.gbl.setConstraints(timeGame, gbc);
		this.gbc.gridx = 2;

		this.add(timeGame);

		this.gbl.setConstraints(time4Game, gbc);
		this.gbc.gridx = 0;
		this.add(time4Game);

		this.gbc.gridy = 10;

		this.gbl.setConstraints(okButton, gbc);
		this.add(okButton);

		this.oponentComp.setEnabled(true);// for now, becouse not implemented!
		this.CompVsComp.setEnabled(true);
	}

	/**
	 * Method responsible for triming white symbols from strings
	 * 
	 * @param txt
	 *            Where is capt value to equal
	 * @param length
	 *            How long is the string
	 * @return result trimmed String
	 */
	public String trimString(JTextField txt, int length) {
		String result = new String();
		try {
			result = txt.getText(0, length);
		} catch (BadLocationException exc) {
			LOG.error("BadLocationException: Something wrong in trimString: \n" + exc);
		}
		return result;
	}
}