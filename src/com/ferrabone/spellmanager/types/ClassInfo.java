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
 * @author carlos
 *
 */
public class ClassInfo {
	
	private int SpellID=0;
	private int ClassID=0;
	private int Level=0;
	private String ClassName=null;
	
	/**
	 * creates an empty instance (defaulting to 0 or null)
	 */
	public ClassInfo(){
		//does nothing for now
	}
	
	/**
	 * Constructor that takes all the state as parameters
	 * @param SpellID Database ID asigned to the Spell this caster belongs
	 * @param ClassID Database ID assigned to the Class
	 * @param Level Number representing the caster level
	 * @param ClassName The name of the caster class
	 */
	public ClassInfo(int SpellID,int ClassID,int Level,String ClassName){
		
	}

	/**
	 * @param spellID the spellID to set. if parameter is invalid, 0 is used
	 */
	public void setSpellID(int spellID) {
		if (spellID >= 0){
			this.SpellID = spellID;
		}else {
			this.SpellID=0;
		}
		
	}

	/**
	 * @return the spellID
	 */
	public int getSpellID() {
		return SpellID;
	}

	/**
	 * @param classID the classID to set, if parameter is inviladid, 0 is used
	 */
	public void setClassID(int classID) {
		if (classID >= 0){
			this.ClassID = classID;
		}else{
			this.ClassID = 0;
		}
	}

	/**
	 * @return the classID
	 */
	public int getClassID() {
		return ClassID;
	}

	/**
	 * @param level the level to set, if parameter is invalid, 0 is used
	 */
	public void setLevel(int level) {
		if ( level >= 0 ){
			this.Level = level;
		}else{
			this.Level= 0;
		}
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return Level;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		if (className != null && className.length() > 0){
			this.ClassName = className;
		}else{
			this.ClassName="Wrong!!!";
		}
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return ClassName;
	}

}
