package com.ferrabone.spellmanager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

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
 * This class will hold a "spell caster", with a reference
 * to the ComboBox and CheckBox that enables it and the level
 * required
 *
 */
public class SpellCaster {
	private JCheckBox check;
	private JComboBox combo;
	private String name;
	
	public SpellCaster(String ParName,JCheckBox ParCheck,JComboBox ParCombo) {
		this.setName(ParName);
		this.setCheckBox(ParCheck);
		this.setComboBox(ParCombo);
		check.setName(getName());
		combo.setName(getName());
		combo.addActionListener(new ClassLevelListener());
	}
	
	public void setName(String ParName){
		name = ParName;	
	}
	
	public void setCheckBox(JCheckBox ParCheck){
		check = ParCheck;
	}
	public void setComboBox(JComboBox ParCombo){
		combo = ParCombo;
	}
	
	public String getName(){
		return name;	
	}
	
	public JCheckBox getCheckBox(){
		return check;
	}
	public JComboBox getComboBox(){
		return combo;
	}
	
	@Override
	public String toString(){
		String temp = name +" "+(String)combo.getSelectedItem();
		
		return temp;
	}
	
	public void clear (){
		combo.setSelectedIndex(0);
		check.setSelected(false);
	}
	
	public class ClassLevelListener implements  ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			check.setSelected(true);
		}
	}
	
	
}
