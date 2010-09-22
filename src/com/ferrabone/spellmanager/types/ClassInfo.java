package com.ferrabone.spellmanager.types;
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
public class ClassInfo {
	private IDStringPairType casterClass=null;
	private int level=0;
	
	/**
	 * No args constructor, creates an obj with empty members
	 */
	public ClassInfo(){
		setCasterClass(new IDStringPairType());
	}
	/**
	 * Creates an obj, using another one, leaving level=0 
	 * @param obj to copy
	 */
	public ClassInfo(IDStringPairType obj){
		setCasterClass(new IDStringPairType(obj));
	}
	/**
	 * Creates an obj, using another one, and using arg as level
	 * @param obj to copy
	 * @param level to set
	 */
	public ClassInfo(IDStringPairType obj,int level){
		setCasterClass(new IDStringPairType(obj));
		setLevel(level);
	}
	
	public ClassInfo(int iD,String text,int level){
		setCasterClass(new IDStringPairType(iD, text));
		setLevel(level);
	}
	/**
	 * @param casterClass the casterClass to set
	 */
	public void setCasterClass(IDStringPairType casterClass) {
		this.casterClass = casterClass;
	}
	/**
	 * @return the casterClass
	 */
	public IDStringPairType getCasterClass() {
		return casterClass;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		if (level >= 0){
			this.level = level;
		}else{
			this.level = 0;
		}
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

}
