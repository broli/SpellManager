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
package com.ferrabone.spellmanager;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author cferrabo
 *
 */
public class SettingsFrame {
	// TODO Add a button to change spell file. update error message about reverting to default options
	
	private Settings settings;
	private JFrame MainJFrame = null;
	private JPanel MainJPanel = null;
	private JPanel JPLanguage = null;
	private JPanel JPSpellFile = null;
	private JPanel JPButtons = null;
	private HashMap<String, Locale> HMapLocale = null;
	private JComboBox JComboLanguages=null;
	private JLabel JLSpellFile=null;
	private JButton JBCancel=null;
	private JButton JBSave=null;
	private Locale newLocale=null;
	
	private HashMap<String,Locale> getAvailableLanguages(){
		HashMap<String, Locale> temporal= new HashMap<String, Locale>();
		File pwd = settings.getLangPackagedir();
		File[] langPackages=null;
		String language=null;
		String country=null;
		
		langPackages = pwd.listFiles(new LangPackFilter());
		for (File line:langPackages){  //for each file that matched the filter
			StringTokenizer st = new StringTokenizer(line.getName(), "_.");  //create the tokenizer
			if (st.countTokens() == 4){   //if it has 4 parts exactly ( LangPack, <language>,<country>, properties)
				while(st.hasMoreTokens()) { //just to be sure but should be needed 
                    String key = st.nextToken(); // read the next key
                    if (st.countTokens() == 2){ //if its the 2 token is the language (remember i made sure it has exactly 4)
                    	language = key;
                    }else if (st.countTokens() == 1){//if its the 3 token is the country (remember i made sure it has exactly 4) 
                    	country = key;
                    }// if its 2 or 3 token
                    
				} //tokenizer ran out of tokens
				Locale tempLocale = new Locale(language,country);
				temporal.put(tempLocale.getDisplayName(), tempLocale);
			}// if the name file was splited in 4 
		}// for each file that matched the filter
		
		return temporal;
	}
	/**
	 * 
	 * @param settings
	 */
	public SettingsFrame(Settings settings){
		this.settings = settings;
		
		MainJFrame = new JFrame(settings.getString("SettingsFrameTitle"));
		
		MainJPanel = new JPanel();
		JPLanguage = new JPanel();
		JPSpellFile = new JPanel();
		JPButtons = new JPanel();
		JLSpellFile = new JLabel(settings.getSpellFile().getAbsolutePath());
		
		JBCancel = new JButton(settings.getString("Cancel"));
		JBSave = new JButton(settings.getString("Save"));
		
		
		MainJPanel.setBorder(BorderFactory.createTitledBorder(settings.getString("Settings")));
		MainJPanel.setLayout(new BoxLayout(MainJPanel, BoxLayout.Y_AXIS));
		JPLanguage.setBorder(BorderFactory.createTitledBorder(settings.getString("Language")));
		JPSpellFile.setBorder(BorderFactory.createTitledBorder(settings.getString("SpellFile")));
		JPButtons.setLayout(new BoxLayout(JPButtons,BoxLayout.X_AXIS));
		
		
		
		HMapLocale = getAvailableLanguages();
		JComboLanguages = new JComboBox(HMapLocale.keySet().toArray());
		JComboLanguages.addActionListener(new LanguageListener());
		JComboLanguages.setSelectedItem(settings.getCurrentLocale().getDisplayName());
		JPLanguage.add(JComboLanguages);
		
		JPSpellFile.add(JLSpellFile);
		
		JBCancel.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		JBSave.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		JBCancel.addActionListener(new BotonListener());
		JBSave.addActionListener(new BotonListener());
		
		JPButtons.add(JBCancel);
		JPButtons.add(Box.createRigidArea(new Dimension(30, 0)));
		JPButtons.add(JBSave);
		
		MainJPanel.add(JPLanguage);
		MainJPanel.add(JPSpellFile);
		MainJPanel.add(JPButtons);
		
		
		MainJFrame.getContentPane().add(MainJPanel);
		MainJFrame.setVisible(true);
		MainJFrame.setSize(MainJFrame.getPreferredSize());
		//MainJFrame.setMinimumSize(MainJFrame.getPreferredSize());
	}
	
	public class BotonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			String SSource = source.getText();
			if (SSource.equalsIgnoreCase(settings.getString("Cancel"))){
				// nothing happens but the close
				MainJFrame.dispose();
			}else if (SSource.equalsIgnoreCase(settings.getString("Save"))){
				settings.changeLanguage(newLocale);
				ObjectOutputStream os;
					try {
						os = new ObjectOutputStream(new FileOutputStream(settings.getConfigfile()));
						os.writeObject(settings);
						os.close();
					} catch (FileNotFoundException e1) {
						// 
						e1.printStackTrace();
					} catch (IOException e1) {
						// 
						e1.printStackTrace();
					}
				
				
				MainJFrame.dispose();
			}
			
		}
		
	}
	
	public class LanguageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox combo = (JComboBox) e.getSource();
			String Language = (String) combo.getSelectedItem();
			newLocale = HMapLocale.get(Language);
			// 
		}
		
	}
	
	public class LangPackFilter implements FilenameFilter {

		@Override
		public boolean accept(File dir, String name) {
			if ((name.startsWith("LangPack")) &&(name.endsWith("properties")) ){
				return true;
			}
			return false;
		}
		
	}
}
