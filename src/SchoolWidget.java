import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

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
 * This class is to manage school and subschool data and widgets
 *
 */
public class SchoolWidget {
	
	//Variables
	private final String[] SSchools = { "Universal","Abjuration","Conjuration","Divination","Enchantment","Evocation","Illusion","Necromancy","Transmutation"};
	private final String[] SInitialSubSchool = { "None" };
	private final String[] SConjSubschool = {"None","Calling", "Creation","Healing","Healing","Teleportation"};
	private final String[] SDivSubSchool = { "None", "Scrying"};
	private final String[] SEnchSubSchool = { "None", "Charm", "Compulsion"};
	private final String[] SIluSubSchool = { "None","Figment", "Glamer", "Pattern", "Phantasm", "Shadow"};
	
	private JComboBox JComboSchool=null;
	private JComboBox JComboSubSchool=null;
	private String Name=null;
	private JPanel JPSchool=null;
	
	//Constructors
	public SchoolWidget() {
		JPSchool = new JPanel();
		JComboSchool = new JComboBox(SSchools);
		JComboSubSchool = new JComboBox(SInitialSubSchool);
		JComboSchool.addActionListener(new SchoolListener());
		this.setName("Schools Widget");
		
		JPSchool.setLayout(new BoxLayout(JPSchool,BoxLayout.X_AXIS));
		JPSchool.add(JComboSchool);
		JPSchool.add(Box.createGlue());
		JPSchool.add(JComboSubSchool);
		JPSchool.setBorder(BorderFactory.createTitledBorder("School and SubSchool"));
		JPSchool.setMaximumSize(JPSchool.getPreferredSize());
	}
	

	//Setters
	public void setName(String parName){
		Name = parName;
	}
	public void setSchool(JComboBox parSchoolCombo){
		JComboSchool = parSchoolCombo;
	}
	public void setSubSchool(JComboBox parSubSchoolCombo){
		JComboSubSchool = parSubSchoolCombo;
	}
	public void setJPanel(JPanel parPanel){
		JPSchool = parPanel;
	}
	
	//Getters
	public String getName(){
		return Name;
	}
	public JComboBox getSchool( ){
		return JComboSchool;
	}
	public JComboBox getSubSchool(){
		return JComboSubSchool;
	}
	public JPanel getJPanel(){
		return JPSchool;
	}
	//Override
	@Override
	public String toString() {
		String temp = (String)JComboSchool.getSelectedItem()+","+ (String)JComboSubSchool.getSelectedItem();
		return temp;
	}
	
	public void reset(){
		JComboSchool.setSelectedIndex(0);
		JComboSubSchool.setSelectedIndex(0);
	}
	
	public class SchoolListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox argument = (JComboBox) arg0.getSource();
			String Selected = (String)argument.getSelectedItem();
			String[] temporal= {"None"};
			if (Selected.equals("Conjuration")){
				temporal = SConjSubschool;
			} else if (Selected.equals("Divination")) {
				temporal = SDivSubSchool;
			} else if (Selected.equals("Enchantment")) {
				temporal = SEnchSubSchool;
			} else if (Selected.equals("Illusion")) {
				temporal = SIluSubSchool;
			} 
			
			JComboSubSchool.removeAllItems();
			for (String line: temporal){
				JComboSubSchool.addItem(line);
			}
			//JPSchool.setSize(JPSchool.getPreferredSize());
			JPSchool.setMaximumSize(JPSchool.getPreferredSize());
				
		}

	}
}
