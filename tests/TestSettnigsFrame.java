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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;

/**
 * @author cferrabo
 *
 */
public class TestSettnigsFrame {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		Settings settings=null;
		try {
			Locale aLocale = new Locale("en","US");
			Locale.setDefault(aLocale);
			settings = new Settings(aLocale);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (MissingResourceException e){
			e.printStackTrace();
		}
		
		SettingsFrame test = new SettingsFrame(settings);

	}

}
