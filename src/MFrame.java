import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
public class MFrame {
	
	String[] SClases = { "Bard", "Cleric", "Druid", "Paladin", "Ranger", "Sorcerer", "Wizard"};
	String[] SLevels = { "0", "1","2","3","4","5","6","7","8","9"};

	public void goMain() {
		JFrame MainJFrame = new JFrame("SpellManager");
		MainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel MainJPanel = new JPanel();
		MainJPanel.setLayout(new BoxLayout(MainJPanel,BoxLayout.Y_AXIS));
		
		//Tier 1 (first line)
		JPanel JPTier1 = new JPanel();
		JPTier1.setMaximumSize(new Dimension(500,50));
		
		
		//spell name
		JLabel JLSpellName = new JLabel("Spell Name");
		JTextField JTFSpellName = new JTextField(25);
		JPTier1.add(JLSpellName);
		JPTier1.add(JTFSpellName);
		
		//Class and level selector
		JPanel JPTier2 = new JPanel();
		JPTier2.setLayout(new BoxLayout(JPTier2,BoxLayout.X_AXIS));
		JPTier2.setMaximumSize(new Dimension(500,50));
		JComboBox JComboClass = new JComboBox(SClases);
		JComboBox JComboLevel = new JComboBox(SLevels);
		JPTier2.add(JComboClass);
		JPTier2.add(JComboLevel);
		
		//Components of spells
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
		
		//Main description area, with scroller
		JTextArea JTADescription = new JTextArea(10,100);
		JScrollPane JSPTADescription = new JScrollPane(JTADescription);
		
		
		
		//Adding everyting to the main panel
		MainJPanel.add(JPTier1);
		MainJPanel.add(JPTier2);
		MainJPanel.add(JPComponents);
		MainJPanel.add(JSPTADescription);
		
		
		
		MainJFrame.getContentPane().add(MainJPanel);
		MainJFrame.setSize(500,500);
		MainJFrame.setVisible(true);
	} //Public void goMain()

} //public class MFrame
