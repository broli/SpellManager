import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

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
	
	final String[] SDomains1 = { "Air", "Animal", "Chaos", "Death", "Destruction", "Earth", "Evil", "Fire"}; 
	final String[] SDomains2 = { "Good", "Healing", "Knowledge","Law", "Luck", "Magic", "Plant", "Protection"};
	final String[] SDomains3 = {"Strength", "Sun", "Travel", "Trickery", "War", "Water"};
	
	final String[] SCastingTime = { "Standard Action", "Round(s)", "Free Action" };
	final String[] SRanges = { "Personal","Touch","Close","Medium","Long","Unlimited"};
	
	final String[] SDurations = { "","Instantaneous", "Permanent","Concentration"};
	final String[] SSpellResistance = { "No","Yes", "Yes (Harmless)","See Text"};
	
	private JFrame MainJFrame=null;
	private JPanel MainJPanel=null;
	private HashMap<Integer, JPanel> Tiers = new HashMap<Integer, JPanel>();
	

	public void goMain() {
		JPanel tempJPanel=null;
		
		MainJFrame = new JFrame("SpellManager");
		MainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainJPanel = new JPanel();
		MainJPanel.setLayout(new BoxLayout(MainJPanel,BoxLayout.Y_AXIS));
		
		
		//Tier 1 (first line) ----------------------------------------------------------------------
		tempJPanel = new JPanel();
		Tiers.put(1, tempJPanel);
		
		
		//spell name -----------------------------------------------------------------------------
		JLabel JLSpellName = new JLabel("Spell Name");
		JTextField JTFSpellName = new JTextField(25);
		Tiers.get(1).add(JLSpellName);
		Tiers.get(1).add(JTFSpellName);
		Tiers.get(1).setMaximumSize(Tiers.get(1).getPreferredSize());
		
		//Create a new tier --------------------------------------------------------------------
		tempJPanel = new JPanel();
		tempJPanel.setLayout(new BoxLayout(tempJPanel,BoxLayout.X_AXIS));
		Tiers.put(2,tempJPanel);
		
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
		Tiers.get(2).add(JPClass_level);
		
		//This panel is to accommodate School and descriptor options
		JPanel JPTier2_Right = new JPanel();
		JPTier2_Right.setLayout(new BoxLayout(JPTier2_Right,BoxLayout.Y_AXIS));
		
		//School and sub school -----------------------------------------------------------------------------------
		SchoolWidget Schoolcombo = new SchoolWidget();
		JPTier2_Right.add(Schoolcombo.getJPanel());
		
		//Descriptor
		DescriptorWidget Descriptors = new DescriptorWidget();
		JPTier2_Right.add(Descriptors.getJPanel());
		Tiers.get(2).add(JPTier2_Right);
		
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
		
		// Tier 4
		JPanel JPTier4 = new JPanel();
		JPTier4.setLayout(new BoxLayout(JPTier4,BoxLayout.X_AXIS));
		
		// Casting time
		JComboBox JComboCastingtimeunit = new JComboBox(SCastingTime);
		JComboCastingtimeunit.setEditable(true);
		JLabel JLCastingtime = new JLabel(" Casting Time ");
		JSpinner JTACastingTimeNumber = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); 
		
	    //Range
		JLabel JLRange = new JLabel(" Range ");
		JComboBox JComboRange = new JComboBox(SRanges);
		JComboRange.setEditable(true);
		
		JPTier4.add(JLCastingtime);
		JPTier4.add(JTACastingTimeNumber);
		JPTier4.add(Box.createHorizontalStrut(5));
		JPTier4.add(JComboCastingtimeunit);
		JPTier4.add(Box.createHorizontalStrut(40));
		JPTier4.add(JLRange);
		JPTier4.add(JComboRange);
		JPTier4.setMaximumSize(JPTier4.getPreferredSize());
		
		
		// Tier 5
		JPanel JPTier5 = new JPanel();
		JPTier5.setLayout(new BoxLayout(JPTier5,BoxLayout.X_AXIS));
		
		//Targets
		JLabel JLTarget = new JLabel(" Target(s) ");
		JTextField JTFTarget = new JTextField(25);
		
		
		//duration
		JComboBox JComboDuration = new JComboBox(SDurations);
		JComboDuration.setEditable(true);
		JLabel JLDuration = new JLabel(" Duration ");
		JComboDuration.setMaximumSize(JComboDuration.getPreferredSize());
		
		JPTier5.add(JLTarget);
		JPTier5.add(JTFTarget);
		JPTier5.add(JLDuration);
		JPTier5.add(JComboDuration);
		JPTier5.setMaximumSize(JPTier5.getPreferredSize());

		//Tier 6
		JPanel JPTier6 = new JPanel();
		JPTier6.setLayout(new BoxLayout(JPTier6,BoxLayout.X_AXIS));
		
		//Saving Throw
		SavingThrowWidget SavingThrow = new SavingThrowWidget();
		
		JPTier6.add(SavingThrow.getJPanel());
		JPTier6.setMaximumSize(JPTier6.getPreferredSize());

		//tier 7
		JPanel JPTier7 = new JPanel();
		JPTier7.setLayout(new BoxLayout(JPTier7,BoxLayout.X_AXIS));
		
		//Spell resistance
		JLabel JLResistance = new JLabel(" Spell Resistance ");
		JComboBox JComboResistance = new JComboBox(SSpellResistance);
		JComboResistance.setMaximumSize(JComboResistance.getPreferredSize());
		
		//Efect (just a small description)
		JLabel JLEffect = new JLabel("Effect");
		JTextField JTFEffect = new JTextField(25);
		
		
		JPTier7.add(JLResistance);
		JPTier7.add(JComboResistance);
		JPTier7.add(JLEffect);
		JPTier7.add(JTFEffect);
		
		
		//Main description area, with scroller ---------------------------------------------------------------
		JTextArea JTADescription = new JTextArea(5,50);
		JScrollPane JSPTADescription = new JScrollPane(JTADescription);
		
		//Bottom Buttons
		JPanel JPControls = new JPanel();
		JPControls.setLayout(new BoxLayout(JPControls,BoxLayout.LINE_AXIS));
		
		JButton JBNext = new JButton("Add & Next");
		JButton JBAddQuit = new JButton("Add & Quit");
		JButton JBQuit = new JButton("Quit");
		JBQuit.addActionListener(new QuitListener());
		JButton JBClear = new JButton("Clear");
		
		JPControls.add(Box.createHorizontalGlue());
		JPControls.add(JBQuit);
		JPControls.add(Box.createRigidArea(new Dimension(30, 0)));
		JPControls.add(JBAddQuit);
		JPControls.add(Box.createRigidArea(new Dimension(10, 0)));
		JPControls.add(JBClear);
		JPControls.add(Box.createRigidArea(new Dimension(10, 0)));
		JPControls.add(JBNext);
		
		//Menues 
		MenuBarContainer menuBar = new MenuBarContainer(MainJFrame);
		
		
		//Adding everything to the main panel
		MainJPanel.add(Tiers.get(1));
		MainJPanel.add(Tiers.get(2));
		MainJPanel.add(JPTier3);
		MainJPanel.add(JPTier4);
		MainJPanel.add(JPTier5);
		MainJPanel.add(JPTier6);
		MainJPanel.add(JPTier7);
		MainJPanel.add(JSPTADescription);
		MainJPanel.add(JPControls);
		MainJFrame.getContentPane().add(MainJPanel);
		//And adding the menubar
		MainJFrame.setJMenuBar(menuBar.getJMenuBar());
		
		MainJFrame.setVisible(true);
		MainJFrame.setMinimumSize(MainJFrame.getPreferredSize());
		MainJFrame.setLocationRelativeTo(null);
		JTFSpellName.requestFocus();
	} //Public void goMain()
	
	
	
	public class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainJFrame.dispose();
			
		}
		
	}//Class ButtonListener
	
} //public class MFrame
