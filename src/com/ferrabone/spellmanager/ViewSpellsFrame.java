package com.ferrabone.spellmanager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/* This file is part of SpellManager
 *
 * SpellManager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpellManager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SpellManager. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * @author carlos
 *
 */
public class ViewSpellsFrame {
	
	private JFrame MainJFrame = null;
	private JPanel MainJPanel = null;
	private JTextArea JTAtext = null;
	private JScrollPane JSPscroller = null;
	
	public ViewSpellsFrame(Settings settings) {

		MainJFrame = new JFrame(settings.getString("ViewSpellsFrameTitle"));
		setMainJPanel(new JPanel());
		
		// TODO add a text area, and load the text in here
		JTAtext = new JTextArea(10,40);
		JTAtext.setMinimumSize(JTAtext.getPreferredScrollableViewportSize());
		JSPscroller = new JScrollPane(JTAtext);
		MainJPanel.add(JSPscroller);
		// TODO add buttons for prev, next, quit, and tied it to the reading
		
		// TODO make the default option to free memory but not end main app
		MainJFrame.getContentPane().add(MainJPanel);
		MainJFrame.setVisible(true);
		MainJFrame.setSize(MainJFrame.getPreferredSize());
		MainJFrame.setMinimumSize(MainJFrame.getPreferredSize());
	}




	public void dispose() {
		// TODO Auto-generated method stub
		
	}


	public void setMainJPanel(JPanel mainJPanel) {
		MainJPanel = mainJPanel;
	}

	public JPanel getMainJPanel() {
		return MainJPanel;
	}
	

}
