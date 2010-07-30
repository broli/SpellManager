import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
 * @author cferrabo
 *
 */
public class MenuBarContainer {
	
	JMenuBar menuBar=null;
	JMenu JMFile=null;
	JMenu JMTools=null;
	JMenu JMHelp=null;
	JFrame MainJFrame=null;
	
	public MenuBarContainer(JFrame parFrame){
		MainJFrame=parFrame;
		menuBar = new JMenuBar();
		JMFile = new JMenu("File");
		JMTools = new JMenu("Tools");
		JMHelp = new JMenu("Help");
		
		// File menu
		JMenuItem tmpMenuItem = new JMenuItem("Open");
		tmpMenuItem.addActionListener(new OpenListener());
		JMFile.add(tmpMenuItem);
		
		JMFile.addSeparator();
		tmpMenuItem = new JMenuItem("Quit");
		tmpMenuItem.addActionListener(new QuitListener());
		JMFile.add(tmpMenuItem);
		
		//Tools menu
		tmpMenuItem = new JMenuItem("Move Spell File");
		tmpMenuItem.addActionListener(new MoveListener());
		JMTools.add(tmpMenuItem);
		
		
		//Help menu
		tmpMenuItem = new JMenuItem("About");
		tmpMenuItem.addActionListener(new AboutListener());
		JMHelp.add(tmpMenuItem);
		
		
		menuBar.add(JMFile);
		menuBar.add(JMTools);
		menuBar.add(JMHelp);
	}//Constructor
	
	public JMenuBar getJMenuBar(){
		return menuBar;
	}
	
	public class OpenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("tocaste " + e.getSource().toString());
			
		}
		
	}
	
	
	public class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.dispose();
			
		}
		
	}
	
	public class MoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("tocaste " + e.getSource().toString());
			
		}
		
	}
	
	public class AboutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("tocaste " + e.getSource().toString());
			
		}
		
	}
	
}// Class MenuBarContainer
