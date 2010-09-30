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
public class RangeType extends IDStringPairType {
	private int range=0;
	
	/**
	 * Creates an obj with empty members
	 */
	public RangeType(){
		// this automaticly calls super with no args
	}
	/**
	 * 
	 * @param Range to use (other members are created empty)
	 */
	public RangeType(int Range){
		super();
		this.setRange(Range);
	}
	/**
	 * Fully parameterized constructor
	 * @param iD
	 * @param name
	 * @param range
	 */
	public RangeType(int iD,String name,int range){
		super(iD,name);
		this.setRange(range);
	}
	/**
	 * @param range the range to set
	 */
	public void setRange(int range) {
		this.range = range;
	}
	/**
	 * @return the range
	 */
	public int getRange() {
		return range;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+this.getRange()+"]";
	}

}
