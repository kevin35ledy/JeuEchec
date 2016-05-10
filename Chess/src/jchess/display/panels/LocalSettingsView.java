/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jchess.display.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import jchess.core.Game;
import jchess.display.windows.DrawLocalSettings;
import jchess.utils.Settings;

/**
 *
 * @author Mateusz Lach ( matlak, msl )
 */
public class LocalSettingsView extends JPanel implements ActionListener {
	private JCheckBox isUpsideDown;

	private JCheckBox isDisplayLegalMovesEnabled;

	private JCheckBox isRenderLabelsEnabled;

	private GridBagConstraints gbc;

	private GridBagLayout gbl;

	private Game game;

	private static final Logger LOG = Logger.getLogger(DrawLocalSettings.class);

	private JCheckBox timeGame;
	private JComboBox time4Game;
	private String times[] = { "1", "3", "5", "8", "10", "15", "20", "25", "30", "60", "120", "250" };

	public LocalSettingsView(Game game) {
		this.game = game;

		this.gbc = new GridBagConstraints();
		this.gbl = new GridBagLayout();

		this.setLayout(gbl);

		initUpsideDownControl();
		initDisplayLegalMovesControl();
		initRenderLabelsControl();
		initTime();
		refreshCheckBoxesState();
	}

	private void initUpsideDownControl() {
		this.isUpsideDown = new JCheckBox();
		this.isUpsideDown.setText(Settings.lang("upside_down"));
		this.isUpsideDown.setSize(this.isUpsideDown.getHeight(), this.getWidth());
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(3, 3, 3, 3);
		this.gbl.setConstraints(isUpsideDown, gbc);
		this.add(isUpsideDown);

		isUpsideDown.addActionListener(this);
	}

	private void initDisplayLegalMovesControl() {
		this.isDisplayLegalMovesEnabled = new JCheckBox();
		this.isDisplayLegalMovesEnabled.setText(Settings.lang("display_legal_moves"));

		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbl.setConstraints(isDisplayLegalMovesEnabled, gbc);
		this.add(isDisplayLegalMovesEnabled);

		isDisplayLegalMovesEnabled.addActionListener(this);
	}

	private void initRenderLabelsControl() {
		this.isRenderLabelsEnabled = new JCheckBox();
		this.isRenderLabelsEnabled.setText(Settings.lang("display_labels"));

		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.gbl.setConstraints(isRenderLabelsEnabled, gbc);
		this.add(isRenderLabelsEnabled);

		isRenderLabelsEnabled.addActionListener(this);
	}

	private void initTime() {
		this.timeGame = new JCheckBox(Settings.lang("time_game_min"));
		this.time4Game = new JComboBox(times);

		this.gbc.gridy = 8;
		this.gbc.gridwidth = 1;
		this.gbl.setConstraints(timeGame, gbc);
		this.add(timeGame);

		this.gbc.gridx = 1;
		this.gbc.gridy = 8;
		this.gbc.gridwidth = 1;
		this.gbl.setConstraints(time4Game, gbc);
		this.add(time4Game);

		timeGame.addActionListener(this);
	}

	private void refreshCheckBoxesState() {
		if (isInitiatedCorrectly()) {
			isUpsideDown.setSelected(game.getSettings().isUpsideDown());
			isDisplayLegalMovesEnabled.setSelected(game.getSettings().isDisplayLegalMovesEnabled());
			isRenderLabelsEnabled.setSelected(game.getSettings().isRenderLabels());
		}
	}

	public void update() {
		// After the first round for each player, we started the clock
		if (game.getTurnCounter() == 1 && timeGame.isSelected()) {
				game.getGameClock().start();
			this.timeGame.setEnabled(false);
			this.time4Game.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox clickedComponent = (JCheckBox) e.getSource();

		if (clickedComponent == isUpsideDown) {
			game.getSettings().setUpsideDown(isUpsideDown.isSelected());
		} else if (clickedComponent == isDisplayLegalMovesEnabled) {
			game.getSettings().setDisplayLegalMovesEnabled(isDisplayLegalMovesEnabled.isSelected());
		} else if (clickedComponent == isRenderLabelsEnabled) {
			game.getSettings().setRenderLabels(isRenderLabelsEnabled.isSelected());
			game.resizeGame();
		} else if (clickedComponent == timeGame) // if timeGame is checked
		{
			String value = this.times[this.time4Game.getSelectedIndex()];// set
			// time
			// for
			// game
			Integer val = new Integer(value);
			game.getSettings().setTimeForGame((int) val * 60);// set time for
			// game and mult
			// it to seconds
			game.getGameClock().setTimes(game.getSettings().getTimeForGame(), game.getSettings().getTimeForGame());
			LOG.debug("this.time4Game.getActionCommand(): " + this.time4Game.getActionCommand());
		}
		game.repaint();
	}

	@Override
	public void repaint() {
		refreshCheckBoxesState();
		super.repaint();
	}

	private boolean isInitiatedCorrectly() {
		return null != isUpsideDown && null != isDisplayLegalMovesEnabled && null != isRenderLabelsEnabled;
	}
}