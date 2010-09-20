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
package com.ferrabone.spellmanager.types;

import java.util.HashMap;


/**
 * @author carlos
 * This class will hold 1 spell at a time.
 * its main use will be to transport a spell from
 *  the addSpellFrame to the database  
 * But will also be used to read a spell out of the database
 */
public class SpellStruct {
	
	private String SpellName=null;
	private HashMap<Integer, String> HMCasters = null;
	
	public SpellStruct(){
		
		
	}

}
