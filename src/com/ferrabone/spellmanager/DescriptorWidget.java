package com.ferrabone.spellmanager;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
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
 * Class to hold all the descriptor jwidgets and changes
 */

public class DescriptorWidget {
	
	private  String[] SDescriptor1; 
	private  String[] SDescriptor2;
	private  String[] SDescriptor3;
	private  String[] SDescriptor4;
	
	private JPanel JPDescriptor = null;
	private JPanel JPDescriptor1 = null;
	private JPanel JPDescriptor2 = null;
	private JPanel JPDescriptor3 = null;
	private JPanel JPDescriptor4 = null;
	
	private ArrayList<JCheckBox> ArrayJcheckDescriptors = null;//To hold all the checkboxes
	private Settings settings;
	
	public DescriptorWidget(Settings parSettings) {
		this.settings = parSettings;
		populateStrings();
		
		JPDescriptor = new JPanel();
		JPDescriptor1 = new JPanel();
		JPDescriptor2 = new JPanel();
		JPDescriptor3 = new JPanel();
		JPDescriptor4 = new JPanel();
		JPDescriptor.setLayout(new BoxLayout(JPDescriptor,BoxLayout.X_AXIS));
		JPDescriptor1.setLayout(new BoxLayout(JPDescriptor1,BoxLayout.Y_AXIS));
		JPDescriptor2.setLayout(new BoxLayout(JPDescriptor2,BoxLayout.Y_AXIS));
		JPDescriptor3.setLayout(new BoxLayout(JPDescriptor3,BoxLayout.Y_AXIS));
		JPDescriptor4.setLayout(new BoxLayout(JPDescriptor4,BoxLayout.Y_AXIS));
		
		ArrayJcheckDescriptors = new ArrayList<JCheckBox>(); 
		
		JCheckBox checkTemporal; //to temporaly hold the checkboxes before adding them to the array
		
		for (String line:SDescriptor1){
			checkTemporal = new JCheckBox(line);
			ArrayJcheckDescriptors.add(checkTemporal);
			JPDescriptor1.add(checkTemporal);
		}
		JPDescriptor.add(JPDescriptor1);
		
		for (String line:SDescriptor2){
			checkTemporal = new JCheckBox(line);
			ArrayJcheckDescriptors.add(checkTemporal);
			JPDescriptor2.add(checkTemporal);
		}
		
		for (String line:SDescriptor3){
			checkTemporal = new JCheckBox(line);
			ArrayJcheckDescriptors.add(checkTemporal);
			JPDescriptor3.add(checkTemporal);
		}
		
		for (String line:SDescriptor4){
			checkTemporal = new JCheckBox(line);
			ArrayJcheckDescriptors.add(checkTemporal);
			JPDescriptor4.add(checkTemporal);
		}
		
		JPDescriptor.add(JPDescriptor1);
		JPDescriptor.add(JPDescriptor2);
		JPDescriptor.add(JPDescriptor3);
		JPDescriptor.add(JPDescriptor4);
		
		JPDescriptor.setBorder(BorderFactory.createTitledBorder(settings.getString("Descriptor")));
		
	}
	
	private void populateStrings() {
		SDescriptor1 = new String[]{settings.getString("Descriptor.acid"), settings.getString("Descriptor.air"), settings.getString("Descriptor.chaotic"), settings.getString("Descriptor.cold"), settings.getString("Descriptor.darkness")}; 
		SDescriptor2 = new String[]{settings.getString("Descriptor.death"), settings.getString("Descriptor.earth"), settings.getString("Descriptor.electricity"), settings.getString("Descriptor.evil"), settings.getString("Descriptor.fear")};
		SDescriptor3 = new String[]{settings.getString("Descriptor.fire"), settings.getString("Descriptor.force"), settings.getString("Descriptor.good"), settings.getString("Descriptor.language-dependent"), settings.getString("Descriptor.lawful")};
		SDescriptor4 = new String[]{settings.getString("Descriptor.light"), settings.getString("Descriptor.mind-affecting"), settings.getString("Descriptor.sonic"), settings.getString("Descriptor.water")};
	}
	
	public JPanel getJPanel(){
		return JPDescriptor;
	}
	
	public String getListEnabled(){
		String temporal="";
		boolean first=true;
		
		for (JCheckBox line: ArrayJcheckDescriptors){
			if (line.isSelected()){
				if (first) {
					temporal = temporal + line.getText();
					first=false;
				}else {
					temporal = temporal + ", " + line.getText() ;
				}
				
			}
		}
		return temporal;
			
	}
	
	public void SetAllDisabled(){
		for (JCheckBox line: ArrayJcheckDescriptors){
			line.setSelected(false);
		}
	}
	
	public String[] getListEnabledArr(){
		ArrayList<String> temporal = new ArrayList<String>();
		for (JCheckBox line: ArrayJcheckDescriptors){
			if (line.isSelected()){
				temporal.add(line.getText());
			}
		}
		return (String[]) temporal.toArray();
			
	}
	
	@Override
	public String toString(){
		return this.getListEnabled();
	}
	
	
}
