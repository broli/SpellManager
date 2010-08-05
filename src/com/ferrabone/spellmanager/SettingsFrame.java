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

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author cferrabo
 *
 */
public class SettingsFrame {
	
	private Settings settings;
	private JFrame MainJFrame = null;
	private JPanel MainJPanel = null;
	private HashMap<Integer, Locale> HMapLocale = null;
	
	private HashMap<Integer, Locale> getAvailableLanguages(){
		HashMap<Integer, Locale> temporal= new HashMap<Integer, Locale>();
		File pwd = settings.getConfigfile();
		File[] langPackages=null;
		String language=null;
		String country=null;
		int loopcount=0;
		
		langPackages = pwd.listFiles(new LangPackFilter());
		for (File line:langPackages){  //for each file that matched the filter
			StringTokenizer st = new StringTokenizer(line.getName(), "_.");  //create the tokenizer
			if (st.countTokens() == 4){   //if it has 4 parts exactly ( LangPack, <language>,<country>, properties)
				while(st.hasMoreTokens()) { //just to be sure but should be needed 
                    String key = st.nextToken(); // read the next key
                    if (st.countTokens() == 3){ //if its the 2 token is the language (remember i made sure it has exactly 4)
                    	language = key;
                    }else if (st.countTokens() == 2){//if its the 3 token is the country (remember i made sure it has exactly 4) 
                    	country = key;
                    }// if its 2 or 3 token
                    
				} //tokenizer ran out of tokens
				temporal.put(loopcount, new Locale(language,country));
			}// if the name file was splited in 4 
			loopcount++;
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
		HMapLocale = getAvailableLanguages();
		
		
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
