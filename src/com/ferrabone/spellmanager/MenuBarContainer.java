package com.ferrabone.spellmanager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
	
	private JMenuBar menuBar=null;
	private JMenu JMFile=null;
	private JMenu JMTools=null;
	private JMenu JMHelp=null;
	private AddSpellFrame MainFrame=null;
	private Settings settings=null;
	
	// TODO check if its necesary to get the AddSpellFrame var
	public MenuBarContainer(AddSpellFrame parFrame,Settings settings){
		
		this.settings = settings;
		
		MainFrame=parFrame;
		menuBar = new JMenuBar();
		JMFile = new JMenu("File");
		JMTools = new JMenu("Tools");
		JMHelp = new JMenu("Help");
		
		// File menu
		// TODO make a open menu item
		JMenuItem tmpMenuItem = new JMenuItem("Open");
		
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
	
	
	public class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.dispose();
			
		}
		
	}
	
	public class MoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(MainFrame.getMainJFrame().getContentPane(), 
					"This options is not implemented yet,\n " +
					"but if you need to move the file, this is it\n"+
					settings.getSpellFile().getAbsolutePath());
			
		}
		
	}
	
	public class AboutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(MainFrame.getMainJFrame().getContentPane(), 
					"SpellManager by Carlos Ferrabone\n" +
					"Visit http://github.com/broli/SpellManager\n" +
					"To submit Bugs, requests, or to send me cookies");
			
		}
		
	}
	
}// Class MenuBarContainer
