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
public class SchoolInfo {
	private IDStringPairType school=null;
	private IDStringPairType subSchool=null;
	
	/**
	 * no arg constructor, leaves members empty
	 */
	public SchoolInfo(){
		setSchool(new IDStringPairType());
		setSubSchool(new IDStringPairType());
	}
	/**
	 * 
	 * @param school object
	 * @param subSchool object
	 */
	public SchoolInfo(IDStringPairType school,IDStringPairType subSchool){
		setSchool(school);
		setSubSchool(subSchool);
	}
	

	/**
	 * @param school the school to set
	 */
	public void setSchool(IDStringPairType school) {
		this.school = school;
	}

	/**
	 * @return the school
	 */
	public IDStringPairType getSchool() {
		return school;
	}

	/**
	 * @param subSchool the subSchool to set
	 */
	public void setSubSchool(IDStringPairType subSchool) {
		this.subSchool = subSchool;
	}

	/**
	 * @return the subSchool
	 */
	public IDStringPairType getSubSchool() {
		return subSchool;
	}
	
	

}
