package com.ferrabone.spellmanager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private ViewSpellsFrame ViewSpellsApp=null;
	private Settings settings;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpellManager MainApp = new SpellManager();
		MainApp.showframe();
		
		
		
	}

	private void showframe() {
		LoadSettings();
		JFrame MainFrame = new JFrame("Add Spell");
		JPanel MainPanel = new JPanel();
		JButton JBaddSpells = new JButton("Add more spells");
		JButton JBquit = new JButton("Quit");
		JButton JBViewSpells = new JButton("View Spells");
		
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
		//MainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JBaddSpells.addActionListener(new butonsListeners());
		JBaddSpells.setAlignmentX(Component.CENTER_ALIGNMENT);
		JBquit.addActionListener(new butonsListeners());
		JBquit.setAlignmentX(Component.CENTER_ALIGNMENT);
		JBViewSpells.addActionListener(new butonsListeners());
		JBViewSpells.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		MainPanel.add(JBaddSpells);
		MainPanel.add(JBViewSpells);
		MainPanel.add(JBquit);
		
		MainFrame.getContentPane().add(MainPanel,BorderLayout.CENTER);
		
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.setVisible(true);
		MainFrame.setSize(MainFrame.getPreferredSize());
		MainFrame.setMinimumSize(MainFrame.getPreferredSize());
	}
	
	public void disposeClients() {
		if (AddSpellApp!=null){
			AddSpellApp.dispose();
		}
		if (ViewSpellsApp!=null){
			ViewSpellsApp.dispose();
		}
		
	}
	
	private void ShowErr(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	private void LoadSettings(){
		File ConfDir = new File(System.getProperty("user.home")+File.pathSeparator+".SpellManager");
		
		//test if it exist
		if (!ConfDir.exists()) {
			//if it dosnt, change to the current dir
			ConfDir = new File(System.getProperty("user.dir"));
		}
		File Configfile = new File(ConfDir.getAbsolutePath()+File.pathSeparator+"config.cfg");
		
		ObjectInputStream is;
		try {
			is = new ObjectInputStream(new FileInputStream(Configfile));
			settings =(Settings) is.readObject() ;
		} catch (FileNotFoundException e) {
			ShowErr("Can't read configuration file\n"+Configfile.getAbsolutePath()+"\nCorrupted or wrong file, Reverting to English defaults");
			try {
				settings = new Settings();
			} catch (IOException e1) {
				//we are screwed. no config file, and cant create a new config object.
				//tell the user and die
				ShowErr("Cant find a Language pack. Place one in the main folder");
				e1.printStackTrace();
				System.exit(1);
			}
		} catch (IOException e) {
			ShowErr("Can't read configuration file\n"+Configfile.getAbsolutePath()+"\nCorrupted or wrong file, Reverting to English defaults");
			try {
				settings = new Settings();
			} catch (IOException e1) {
				//we are screwed. no config file, and cant create a new config object.
				//tell the user and die
				e1.printStackTrace();
				ShowErr("Cant find a Language pack. Place one in the main folder");
				System.exit(1);
			}
		} catch (ClassNotFoundException e) {
			ShowErr("Can't read configuration file\n"+Configfile.getAbsolutePath()+"\nCorrupted or wrong file, Reverting to English defaults");
			try {
				settings = new Settings();
			} catch (IOException e1) {
				//we are screwed. no config file, and cant create a new config object.
				//tell the user and die
				e1.printStackTrace();
				ShowErr("Cant find a Language pack. Place one in the main folder");
				System.exit(1);
			}
			
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
			} else if (SSource.equalsIgnoreCase("View Spells")){
				 ViewSpellsApp = new ViewSpellsFrame(settings);
			}
			
		}
		
	}

	

}