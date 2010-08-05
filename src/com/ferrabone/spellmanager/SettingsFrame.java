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
		
		langPackages = pwd.listFiles(new LangPackFilter());
		for (File line:langPackages){
			StringTokenizer st = new StringTokenizer(line.getName(), "_.");

		}
		
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
