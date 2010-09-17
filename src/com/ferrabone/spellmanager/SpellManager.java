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
	@SuppressWarnings("unused")
	private SettingsFrame SettingsFrameApp=null;
	private Settings settings=null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpellManager MainApp = new SpellManager();
		MainApp.showframe();
		
		
		
	}

	private void showframe() {
		LoadSettings();
		// After this point, you can use the settings
		JFrame MainFrame = new JFrame(settings.getString("SpellManager"));
		JPanel MainPanel = new JPanel();
		JButton JBaddSpells = new JButton(settings.getString("AddSpells"));
		JButton JBquit = new JButton(settings.getString("Quit"));
		JButton JBViewSpells = new JButton(settings.getString("ViewSpells"));
		JButton JBSettings = new JButton(settings.getString("Settings"));
		
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
		//MainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JBaddSpells.addActionListener(new butonsListeners());
		JBaddSpells.setAlignmentX(Component.CENTER_ALIGNMENT);
		JBquit.addActionListener(new butonsListeners());
		JBquit.setAlignmentX(Component.CENTER_ALIGNMENT);
		JBquit.setName("Quit");
		JBViewSpells.addActionListener(new butonsListeners());
		JBViewSpells.setAlignmentX(Component.CENTER_ALIGNMENT);
		JBSettings.addActionListener(new butonsListeners());
		JBSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		MainPanel.add(JBaddSpells);
		MainPanel.add(JBViewSpells);
		MainPanel.add(JBSettings);
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
	
	/**
	 * Atempts to load the config from disk.
	 * if it fails, it reverts to a default config
	 */
	private void LoadSettings(){
		File ConfDir = new File(System.getProperty("user.home")+File.separator+".SpellManager");
		
		//test if it exist
		if (!ConfDir.exists()) {
			//if it dosnt, change to the current dir
			ConfDir = new File(System.getProperty("user.dir"));
		}
		File Configfile = new File(ConfDir.getAbsolutePath()+File.separator+"config.cfg");

		// TODO read Locale.getDefault() and try that before reverting to en_US
		ObjectInputStream is;
		try {
			is = new ObjectInputStream(new FileInputStream(Configfile));
			settings =(Settings) is.readObject() ;
			is.close();
			settings.updateStrings();
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
			// TODO use the name instead of the text (so i18n wont make this fail)
			String SSource = source.getText();
			if (SSource.equalsIgnoreCase(settings.getString("AddSpells"))){
				AddSpellApp = new AddSpellFrame(settings);
			}else if (SSource.equalsIgnoreCase(settings.getString("Quit"))){
				disposeClients();
				System.exit(0);
			}else if (SSource.equalsIgnoreCase(settings.getString("ViewSpells"))){
				ViewSpellsApp = new ViewSpellsFrame(settings);
			}else if (SSource.equalsIgnoreCase(settings.getString("Settings"))){
				SettingsFrameApp = new SettingsFrame(settings);
			}
			
		}
		
	}

	

}
