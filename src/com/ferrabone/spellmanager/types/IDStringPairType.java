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

/**
 * @author cferrabo
 *
 */
public class IDStringPairType {
	private int ID=0;
	private String text=null;
	
	/**
	 * Creates obj with empty members
	 */
	public IDStringPairType() {
		//Generic constructor
	}
	/**
	 * Creates an obj using the suplied parameters
	 * @param id = Database asigned ID, if an invalid parameter is passed, revert to 0
	 * @param name = Human readable text, if an invalid parameter is passed, revert to ""
	 */
	public IDStringPairType(int id,String text){
		if (id >= 0){
			this.setID(id);
		}else{
			this.setID(0);
		}
		
		if (text != null && text.length() >= 0){
			this.setText(text);
		}else{
			this.setText("");
		}
			
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the name
	 */
	public String getText() {
		return text;
	}
	

}
