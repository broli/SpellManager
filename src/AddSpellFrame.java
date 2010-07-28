import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
 * @author carlos
 *
 */
public class AddSpellFrame {
	
	final String[] SClases = { "Bard", "Cleric", "Druid", "Paladin", "Ranger", "Sorcerer", "Wizard"};
	final String[] SLevels = { "0", "1","2","3","4","5","6","7","8","9"};
	
	final String[] SSchools = { "Abjuration","Conjuration","Divination","Enchantment","Evocation","Illusion","Necromancy","Transmutation"};
	final String[] SFirstchooseschool = { "Not applicable"};
	final String[] SConjSubschool = {"None","Calling", "Creation","Healing","Healing","Teleportation"};
	final String[] SDivSubSchool = { "None", "Scrying"};
	final String[] SEnchSubSchool = { "None", "Charm", "Compulsion"};
	final String[] SIluSubSchool = { "Figment", "Glamer", "Pattern", "Phantasm", "Shadow"};
	
	final String[] SDescriptor1 = {"acid", "air", "chaotic", "cold", "darkness"}; 
	final String[] SDescriptor2 = {"death", "earth", "electricity", "evil", "fear"};
	final String[] SDescriptor3 = {"fire", "force", "good", "language-dependent", "lawful"};
	final String[] SDescriptor4 = {"light", "mind-affecting", "sonic", "water"};
	
	final String[] SDomains1 = { "Air", "Animal", "Chaos", "Death", "Destruction", "Earth", "Evil", "Fire"}; 
	final String[] SDomains2 = { "Good", "Healing", "Knowledge","Law", "Luck", "Magic", "Plant", "Protection"};
	final String[] SDomains3 = {"Strength", "Sun", "Travel", "Trickery", "War", "Water"};

	private JComboBox JComboSubSchool;

	public void goMain() {
		JFrame MainJFrame = new JFrame("SpellManager");
		MainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel MainJPanel = new JPanel();
		MainJPanel.setLayout(new BoxLayout(MainJPanel,BoxLayout.Y_AXIS));
		
		
		//Tier 1 (first line) ----------------------------------------------------------------------
		JPanel JPTier1 = new JPanel();
		
		
		//spell name -----------------------------------------------------------------------------
		JLabel JLSpellName = new JLabel("Spell Name");
		JTextField JTFSpellName = new JTextField(25);
		JPTier1.add(JLSpellName);
		JPTier1.add(JTFSpellName);
		JPTier1.setMaximumSize(JPTier1.getPreferredSize());
		
		//Create a new tier --------------------------------------------------------------------
		JPanel JPTier2 = new JPanel();
		JPTier2.setLayout(new BoxLayout(JPTier2,BoxLayout.X_AXIS));
		
		//Class and level selector ---------------------------------------------------------------------------
		JPanel JPClass_level = new JPanel();
		JPanel JPClasesCheck = new JPanel();
		JPanel JPLevelCombo = new JPanel();
		
		JPClass_level.setLayout(new BoxLayout(JPClass_level,BoxLayout.X_AXIS));
		JPClasesCheck.setLayout(new BoxLayout(JPClasesCheck,BoxLayout.Y_AXIS));
		JPLevelCombo.setLayout(new BoxLayout(JPLevelCombo,BoxLayout.Y_AXIS));
		
		JCheckBox checkTemporal; //to temporaly hold the checkboxes before adding them to the array
		JComboBox comboTemporal; //to temporally hold the combos

		SpellCaster temporal;
		ArrayList<SpellCaster> SpellCasterarr = new ArrayList<SpellCaster>();
		
		for (String line:SClases){
			checkTemporal = new JCheckBox(line);
			comboTemporal = new JComboBox(SLevels);
			comboTemporal.setMaximumRowCount(10);
			comboTemporal.setMaximumSize(comboTemporal.getPreferredSize());
			temporal = new SpellCaster(line,checkTemporal,comboTemporal);
			SpellCasterarr.add(temporal);
			
			JPClasesCheck.add(checkTemporal);
			JPLevelCombo.add(comboTemporal);
		}
		
		JPClass_level.add(JPClasesCheck);
		JPClass_level.add(JPLevelCombo);
		JPClass_level.setBorder(BorderFactory.createTitledBorder("Class and Level"));
		JPTier2.add(JPClass_level);
		
		//This panel is to accommodate School and descriptor options
		JPanel JPTier2_Right = new JPanel();
		JPTier2_Right.setLayout(new BoxLayout(JPTier2_Right,BoxLayout.Y_AXIS));
		
		//School and sub school -----------------------------------------------------------------------------------
		JPanel JPSchool = new JPanel();
		JPSchool.setLayout(new BoxLayout(JPSchool,BoxLayout.X_AXIS));
		JComboBox JComboSchool = new JComboBox(SSchools);
		JComboSubSchool = new JComboBox(SFirstchooseschool);
		JComboSubSchool.setEnabled(false);
		JComboSchool.addActionListener(new SchoolListener());
		
		JPSchool.add(JComboSchool);
		JPSchool.add(JComboSubSchool);
		JPSchool.setBorder(BorderFactory.createTitledBorder("School and SubSchool"));
		JPSchool.setMaximumSize(JPSchool.getPreferredSize());
		JPTier2_Right.add(JPSchool);
		
		//Descriptor
		JPanel JPDescriptor = new JPanel();
		JPanel JPDescriptor1 = new JPanel();
		JPanel JPDescriptor2 = new JPanel();
		JPanel JPDescriptor3 = new JPanel();
		JPanel JPDescriptor4 = new JPanel();
		JPDescriptor.setLayout(new BoxLayout(JPDescriptor,BoxLayout.X_AXIS));
		JPDescriptor1.setLayout(new BoxLayout(JPDescriptor1,BoxLayout.Y_AXIS));
		JPDescriptor2.setLayout(new BoxLayout(JPDescriptor2,BoxLayout.Y_AXIS));
		JPDescriptor3.setLayout(new BoxLayout(JPDescriptor3,BoxLayout.Y_AXIS));
		JPDescriptor4.setLayout(new BoxLayout(JPDescriptor4,BoxLayout.Y_AXIS));
		
		ArrayList<JCheckBox> ArrayJcheckDescriptors = new ArrayList<JCheckBox>(); //To hold all the checkboxes
		
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
		JPDescriptor.setBorder(BorderFactory.createTitledBorder("Descriptor(s)"));
		
		JPTier2_Right.add(JPDescriptor);
		JPTier2.add(JPTier2_Right);
		
		//Tier 3
		JPanel JPTier3 = new JPanel();
		JPTier3.setLayout(new BoxLayout(JPTier3,BoxLayout.Y_AXIS));
		
		
		//Components of spells  -------------------------------------------------------------------------------------
		JPanel JPComponents = new JPanel();
		JPComponents.setLayout(new BoxLayout(JPComponents,BoxLayout.X_AXIS));
		JCheckBox JCBVerbal = new JCheckBox("Verbal");
		JCheckBox JCBSomatic = new JCheckBox("Somatic");
		JCheckBox JCBMaterial = new JCheckBox("Material");
		JCheckBox JCBFocus = new JCheckBox("Focus");
		JCheckBox JCBDivine_Focus = new JCheckBox("Divine Focus");
		JCheckBox JCBXP_Cost = new JCheckBox("XP Cost");
		JPComponents.add(JCBVerbal);
		JPComponents.add(JCBSomatic);
		JPComponents.add(JCBMaterial);
		JPComponents.add(JCBFocus);
		JPComponents.add(JCBDivine_Focus);
		JPComponents.add(JCBXP_Cost);
		JPComponents.setBorder(BorderFactory.createTitledBorder("Components"));
		JPTier3.add(JPComponents);
		
		//Domains
		JPanel JPDomains = new JPanel();
		JPanel JPDomains1 = new JPanel();
		JPanel JPDomains2 = new JPanel();
		JPanel JPDomains3 = new JPanel();
		JPDomains.setLayout(new BoxLayout(JPDomains,BoxLayout.Y_AXIS));
		JPDomains1.setLayout(new BoxLayout(JPDomains1,BoxLayout.X_AXIS));
		JPDomains2.setLayout(new BoxLayout(JPDomains2,BoxLayout.X_AXIS));
		JPDomains3.setLayout(new BoxLayout(JPDomains3,BoxLayout.X_AXIS));
		
		ArrayList<JCheckBox> ArrayJcheckDomains = new ArrayList<JCheckBox>(); //To hold all the checkboxes
		for (String line:SDomains1){
			checkTemporal = new JCheckBox(line);
			ArrayJcheckDomains.add(checkTemporal);
			JPDomains1.add(checkTemporal);
		}
		for (String line:SDomains2){
			checkTemporal = new JCheckBox(line);
			ArrayJcheckDomains.add(checkTemporal);
			JPDomains2.add(checkTemporal);
		}
		for (String line:SDomains3){
			checkTemporal = new JCheckBox(line);
			ArrayJcheckDomains.add(checkTemporal);
			JPDomains3.add(checkTemporal);
		}
		JPDomains.add(JPDomains1);
		JPDomains.add(JPDomains2);
		JPDomains.add(JPDomains3);
		JPDomains.setBorder(BorderFactory.createTitledBorder("Domains"));
		JPTier3.add(JPDomains);
		
		
		//Main description area, with scroller ---------------------------------------------------------------
		//JTextArea JTADescription = new JTextArea(10,100);
		//JScrollPane JSPTADescription = new JScrollPane(JTADescription);
		
		
		
		
		//Adding everything to the main panel
		MainJPanel.add(JPTier1);
		MainJPanel.add(JPTier2);
		MainJPanel.add(JPTier3);
		MainJFrame.getContentPane().add(MainJPanel);
		
		MainJFrame.setVisible(true);
		MainJFrame.setMinimumSize(MainJFrame.getPreferredSize());
		MainJFrame.setLocationRelativeTo(null);
		JTFSpellName.requestFocus();
	} //Public void goMain()
	
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
				JComboSubSchool.setEnabled(true);
			}

				
		}
		
	}
	
	
} //public class MFrame
