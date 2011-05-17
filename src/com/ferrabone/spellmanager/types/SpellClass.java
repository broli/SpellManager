package com.ferrabone.spellmanager.types;

import java.util.ArrayList;

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
public class SpellClass {
	private int ID=-1;
	private String Name=null;
	private ArrayList<ClassInfo> casters=null;
	private SchoolInfo school=null;
	private ArrayList<IDStringPairType> descriptors=null;
	private ArrayList<IDStringPairType> components=null;
	private ArrayList<IDStringPairType> domains=null;
	private RangeType range=null;
	private IDStringPairType savingThrow=null;
	private IDStringPairType resistance=null;
	private IDStringPairType castingTime=null;
	private String targets=null;
	private IDStringPairType duration=null;
	private String effect=null;
	private String text=null; 
	
	/**
	 * No args constructor 
	 */
	public SpellClass(){
		// nothing for now
	}
	
	public SpellClass(String name){
		setName(name);
	}
	 
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the casters
	 */
	public ArrayList<ClassInfo> getCasters() {
		return casters;
	}
	/**
	 * @param casters the casters to set
	 */
	public void setCasters(ArrayList<ClassInfo> casters) {
		this.casters = casters;
	}
	/**
	 * @return the school
	 */
	public SchoolInfo getSchool() {
		return school;
	}
	/**
	 * @param school the school to set
	 */
	public void setSchool(SchoolInfo school) {
		this.school = school;
	}
	/**
	 * @return the descriptors
	 */
	public ArrayList<IDStringPairType> getDescriptors() {
		return descriptors;
	}
	/**
	 * @param descriptors the descriptors to set
	 */
	public void setDescriptors(ArrayList<IDStringPairType> descriptors) {
		this.descriptors = descriptors;
	}
	/**
	 * @return the components
	 */
	public ArrayList<IDStringPairType> getComponents() {
		return components;
	}
	/**
	 * @param components the components to set
	 */
	public void setComponents(ArrayList<IDStringPairType> components) {
		this.components = components;
	}
	/**
	 * @return the domains
	 */
	public ArrayList<IDStringPairType> getDomains() {
		return domains;
	}
	/**
	 * @param domains the domains to set
	 */
	public void setDomains(ArrayList<IDStringPairType> domains) {
		this.domains = domains;
	}
	/**
	 * @return the range
	 */
	public RangeType getRange() {
		return range;
	}
	/**
	 * @param range the range to set
	 */
	public void setRange(RangeType range) {
		this.range = range;
	}
	/**
	 * @return the savingThrow
	 */
	public IDStringPairType getSavingThrow() {
		return savingThrow;
	}
	/**
	 * @param savingThrow the savingThrow to set
	 */
	public void setSavingThrow(IDStringPairType savingThrow) {
		this.savingThrow = savingThrow;
	}
	/**
	 * @return the resistance
	 */
	public IDStringPairType getResistance() {
		return resistance;
	}
	/**
	 * @param resistance the resistance to set
	 */
	public void setResistance(IDStringPairType resistance) {
		this.resistance = resistance;
	}
	/**
	 * @return the castingTime
	 */
	public IDStringPairType getCastingTime() {
		return castingTime;
	}
	/**
	 * @param castingTime the castingTime to set
	 */
	public void setCastingTime(IDStringPairType castingTime) {
		this.castingTime = castingTime;
	}
	/**
	 * @return the targets
	 */
	public String getTargets() {
		return targets;
	}
	/**
	 * @param targets the targets to set
	 */
	public void setTargets(String targets) {
		this.targets = targets;
	}
	/**
	 * @return the duration
	 */
	public IDStringPairType getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(IDStringPairType duration) {
		this.duration = duration;
	}

	/**
	 * @param effect the effect to set
	 */
	public void setEffect(String effect) {
		this.effect = effect;
	}

	/**
	 * @return the effect
	 */
	public String getEffect() {
		return effect;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result=false;
		SpellClass parameter = (SpellClass) obj;
		
		if (parameter.getID() == this.getID()){
			result = true;
		}else{
			result = false;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String line="";
		
		
		
		line = "Spell id: "+ this.getID() +";"+ this.getName() +";Casters -";
		for (ClassInfo caster:this.getCasters()){
			line = line + caster.toString()+";";
		}
		line = line +"School/subSchool -"+ this.getSchool().toString() +";"+ this.getDescriptors().toString() +";"+
				this.getComponents().toString() +";"+ this.getDomains() +";"+ this.getRange().toString()+";"+
				this.getSavingThrow().toString() +";"+ this.getResistance().toString() +";"+ 
				this.getCastingTime().toString()+";"+ this.getTargets() +";"+ this.getDuration().toString()+";"+
				this.getEffect() +";"+ this.getText();
				
		return line;
	}
	
}
