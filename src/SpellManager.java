import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
public class SpellManager {
	
	private AddSpellFrame AddSpellApp=null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpellManager MainApp = new SpellManager();
		MainApp.showframe();
		
		
		
	}

	private void showframe() {
		JFrame MainFrame = new JFrame("Add Spell");
		JPanel MainPanel = new JPanel();
		JButton JBaddSpells = new JButton("Add more spells");
		JButton JBquit = new JButton("Quit");
		
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
		//MainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JBaddSpells.addActionListener(new butonsListeners());
		JBaddSpells.setAlignmentX(Component.CENTER_ALIGNMENT);
		JBquit.addActionListener(new butonsListeners());
		JBquit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		MainPanel.add(JBaddSpells);
		
		MainPanel.add(JBquit);
		
		MainFrame.getContentPane().add(MainPanel,BorderLayout.CENTER);
		
		
		MainFrame.setVisible(true);
		MainFrame.setSize(MainFrame.getPreferredSize());
		MainFrame.setMinimumSize(MainFrame.getPreferredSize());
	}
	
	public void disposeClients() {
		if (AddSpellApp!=null){
			AddSpellApp.dispose();
		}
		
	}
	
	public class butonsListeners implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			String SSource = source.getText();
			if (SSource.equalsIgnoreCase("Add more Spells")){
				AddSpellApp = new AddSpellFrame();
			}else if (SSource.equalsIgnoreCase("Quit")){
				disposeClients();
				System.exit(0);
			}
			
		}
		
	}

	

}
