import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
	
	private String Name=null;
	// TODO Convert this app to the message bundle, and move these strings there
	private final String[] SClases = { "Bard", "Cleric", "Druid", "Paladin", "Ranger", "Sorcerer", "Wizard"};
	private final String[] SLevels = { "0", "1","2","3","4","5","6","7","8","9"};
	private final String[] SComponents = { "Verbal","Somatic","Material","Focus","Divine Focus","XP Cost"};
	private final String[] SDomains1 = { "Air", "Animal", "Chaos", "Death", "Destruction", "Earth", "Evil", "Fire"}; 
	private final String[] SDomains2 = { "Good", "Healing", "Knowledge","Law", "Luck", "Magic", "Plant", "Protection"};
	private final String[] SDomains3 = {"Strength", "Sun", "Travel", "Trickery", "War", "Water"};
	
	private final String[] SCastingTime = { "Standard Action", "Round(s)", "Free Action" };
	private final String[] SRanges = { "Personal","Touch","Close","Medium","Long","Unlimited"};
	
	private final String[] SDurations = { "","Instantaneous", "Permanent","Concentration"};
	private final String[] SSpellResistance = { "No","Yes", "Yes (Harmless)","See Text"};
	
	private JFrame MainJFrame=null;
	private JPanel MainJPanel=null;
	private HashMap<Integer, JPanel> HMapTiers = null;
	
	
	//Tier 1 vars
	private JLabel JLSpellName=null;
	private JTextField JTFSpellName=null;
	//tier 2 vars
	private JPanel JPClass_level = null;
	private JPanel JPClasesCheck = null;
	private JPanel JPLevelCombo = null;
	private ArrayList<SpellCaster> SpellCasterarr; //Array of all spellcasters
	//tier 2 right side
	private JPanel JPTier2_Right=null;//ugly hack to fit the 2 things side by side
	private SchoolWidget Schoolcombo=null;
	private DescriptorWidget Descriptors=null;
	//tier 3 vars
	private JPanel JPComponents=null;
	private HashMap<String,JCheckBox> HMapComponents = null;
	private JPanel JPDomains=null;
	private HashMap<Integer,JPanel> HMapDomains = null;
	private ArrayList<JCheckBox> ArrayJcheckDomains=null;
	//tier4
	private JComboBox JComboCastingtimeunit=null;
	private JLabel JLCastingtime=null;
	private JSpinner JTACastingTimeNumber=null;
	private JLabel JLRange=null;
	private JComboBox JComboRange=null;
	//tier5
	private JLabel JLTarget=null;
	private JTextField JTFTarget=null;
	private JComboBox JComboDuration=null;
	private JLabel JLDuration=null;
	//tier6
	private SavingThrowWidget SavingThrow=null;
	//tier7
	private JLabel JLResistance=null;
	private JComboBox JComboResistance=null;
	private JLabel JLEffect=null; 
	private JTextField JTFEffect=null;
	//Tier text 
	private JTextArea JTADescription=null;
	private JScrollPane JSPTADescription=null;
	//tier buttons
	BottomButtonsManager BottomButtons=null;
	
	private File SpellFile=null;
	

	private void Constructor(){
		Name="Add Spells Form";
		JPanel tempJPanel=null; //temp var to hold panels before adnig it to the map
		JCheckBox checkTemporal=null; //to temporaly hold the checkboxes before adding them to the array
		JComboBox comboTemporal=null; //to temporally hold the combos
		JPanel panelTemporal=null; //to hold temporal panels
		SpellCaster temporal=null; //to temporaly hold a SpellCaster
		
		HMapTiers = new HashMap<Integer, JPanel>(); //create the empty hashmap 
		HMapComponents = new HashMap<String,JCheckBox>(); //create the empty hashmap
		HMapDomains = new HashMap<Integer,JPanel>();//create the empty hashmap
		
		MainJFrame = new JFrame("SpellManager");
		MainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainJPanel = new JPanel();
		MainJPanel.setLayout(new BoxLayout(MainJPanel,BoxLayout.Y_AXIS));
		
		
		//Tier 1 (first line) ----------------------------------------------------------------------
		tempJPanel = new JPanel();
		HMapTiers.put(1, tempJPanel);
		
		
		//spell name -----------------------------------------------------------------------------
		JLSpellName = new JLabel("Spell Name");
		JTFSpellName = new JTextField(25);
		HMapTiers.get(1).add(JLSpellName);
		HMapTiers.get(1).add(JTFSpellName);
		HMapTiers.get(1).setMaximumSize(HMapTiers.get(1).getPreferredSize());
		
		//Create a new tier --------------------------------------------------------------------
		tempJPanel = new JPanel();
		tempJPanel.setLayout(new BoxLayout(tempJPanel,BoxLayout.X_AXIS));
		HMapTiers.put(2,tempJPanel);
		
		//Class and level selector ---------------------------------------------------------------------------
		JPClass_level = new JPanel();
		JPClasesCheck = new JPanel();
		JPLevelCombo = new JPanel();
		
		JPClass_level.setLayout(new BoxLayout(JPClass_level,BoxLayout.X_AXIS));
		JPClasesCheck.setLayout(new BoxLayout(JPClasesCheck,BoxLayout.Y_AXIS));
		JPLevelCombo.setLayout(new BoxLayout(JPLevelCombo,BoxLayout.Y_AXIS));
		
		SpellCasterarr = new ArrayList<SpellCaster>();
		
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
		HMapTiers.get(2).add(JPClass_level);
	
		//This panel is to accommodate School and descriptor options
		JPTier2_Right = new JPanel();
		JPTier2_Right.setLayout(new BoxLayout(JPTier2_Right,BoxLayout.Y_AXIS));
		
		//School and sub school -----------------------------------------------------------------------------------
		Schoolcombo = new SchoolWidget();
		JPTier2_Right.add(Schoolcombo.getJPanel());
		
		//Descriptor
		Descriptors = new DescriptorWidget();
		JPTier2_Right.add(Descriptors.getJPanel());
		HMapTiers.get(2).add(JPTier2_Right);
		
		//Tier 3
		tempJPanel = new JPanel();
		tempJPanel.setLayout(new BoxLayout(tempJPanel,BoxLayout.Y_AXIS));
		HMapTiers.put(3,tempJPanel);
		
		//Components of spells  -------------------------------------------------------------------------------------
		JPComponents = new JPanel();
		JPComponents.setLayout(new BoxLayout(JPComponents,BoxLayout.X_AXIS));
		
		for (String line:SComponents){
			checkTemporal = new JCheckBox(line);
			HMapComponents.put(line, checkTemporal);
			JPComponents.add(checkTemporal);
		}

		JPComponents.setBorder(BorderFactory.createTitledBorder("Components"));
		HMapTiers.get(3).add(JPComponents);
		
		//Domains
		JPDomains = new JPanel();
		panelTemporal = new JPanel();
	   	HMapDomains.put(1, panelTemporal);
	   	panelTemporal = new JPanel();
	   	HMapDomains.put(2, panelTemporal);
	   	panelTemporal = new JPanel();
	   	HMapDomains.put(3, panelTemporal);
	   	
		JPDomains.setLayout(new BoxLayout(JPDomains,BoxLayout.Y_AXIS));
		HMapDomains.get(1).setLayout(new BoxLayout(HMapDomains.get(1),BoxLayout.X_AXIS));
		HMapDomains.get(2).setLayout(new BoxLayout(HMapDomains.get(2),BoxLayout.X_AXIS));
		HMapDomains.get(3).setLayout(new BoxLayout(HMapDomains.get(3),BoxLayout.X_AXIS));
		
		
		ArrayJcheckDomains = new ArrayList<JCheckBox>(); //To hold all the checkboxes
		for (String line:SDomains1){
			checkTemporal = new JCheckBox(line);
			ArrayJcheckDomains.add(checkTemporal);
			HMapDomains.get(1).add(checkTemporal);
		}
		for (String line:SDomains2){
			checkTemporal = new JCheckBox(line);
			ArrayJcheckDomains.add(checkTemporal);
			HMapDomains.get(2).add(checkTemporal);
		}
		for (String line:SDomains3){
			checkTemporal = new JCheckBox(line);
			ArrayJcheckDomains.add(checkTemporal);
			HMapDomains.get(3).add(checkTemporal);
		}
		JPDomains.add(HMapDomains.get(1));
		JPDomains.add(HMapDomains.get(2));
		JPDomains.add(HMapDomains.get(3));
		JPDomains.setBorder(BorderFactory.createTitledBorder("Domains"));
		HMapTiers.get(3).add(JPDomains);
		
		// Tier 4
		tempJPanel = new JPanel();
		tempJPanel.setLayout(new BoxLayout(tempJPanel,BoxLayout.X_AXIS));
		HMapTiers.put(4,tempJPanel);
		
		// Casting time
		JComboCastingtimeunit = new JComboBox(SCastingTime);
		JComboCastingtimeunit.setEditable(true);
		JLCastingtime = new JLabel(" Casting Time ");
		JTACastingTimeNumber = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); 
		
	    //Range
		JLRange = new JLabel(" Range ");
		JComboRange = new JComboBox(SRanges);
		JComboRange.setEditable(true);
		
		HMapTiers.get(4).add(JLCastingtime);
		HMapTiers.get(4).add(JTACastingTimeNumber);
		HMapTiers.get(4).add(Box.createHorizontalStrut(5));
		HMapTiers.get(4).add(JComboCastingtimeunit);
		HMapTiers.get(4).add(Box.createHorizontalStrut(40));
		HMapTiers.get(4).add(JLRange);
		HMapTiers.get(4).add(JComboRange);
		HMapTiers.get(4).setMaximumSize(HMapTiers.get(4).getPreferredSize());
		
		
		// Tier 5
		tempJPanel = new JPanel();
		tempJPanel.setLayout(new BoxLayout(tempJPanel,BoxLayout.X_AXIS));
		HMapTiers.put(5,tempJPanel);
		
		//Targets
		JLTarget = new JLabel(" Target(s) ");
		JTFTarget = new JTextField(25);
		
		//duration
		JComboDuration= new JComboBox(SDurations);
		JComboDuration.setEditable(true);
		JLDuration = new JLabel(" Duration ");
		JComboDuration.setMaximumSize(JComboDuration.getPreferredSize());
		
		HMapTiers.get(5).add(JLTarget);
		HMapTiers.get(5).add(JTFTarget);
		HMapTiers.get(5).add(JLDuration);
		HMapTiers.get(5).add(JComboDuration);
		HMapTiers.get(5).setMaximumSize(HMapTiers.get(5).getPreferredSize());

		//Tier 6
		tempJPanel = new JPanel();
		tempJPanel.setLayout(new BoxLayout(tempJPanel,BoxLayout.X_AXIS));
		HMapTiers.put(6,tempJPanel);
		
		//Saving Throw
		SavingThrow = new SavingThrowWidget();
		
		HMapTiers.get(6).add(SavingThrow.getJPanel());
		HMapTiers.get(6).setMaximumSize(HMapTiers.get(6).getPreferredSize());

		//tier 7
		tempJPanel = new JPanel();
		tempJPanel.setLayout(new BoxLayout(tempJPanel,BoxLayout.X_AXIS));
		HMapTiers.put(7,tempJPanel);
		
		//Spell resistance
		JLResistance= new JLabel(" Spell Resistance ");
		JComboResistance= new JComboBox(SSpellResistance);
		JComboResistance.setMaximumSize(JComboResistance.getPreferredSize());
		
		//Efect (just a small description)
		JLEffect= new JLabel("Effect");
		JTFEffect = new JTextField(25);
		
		
		HMapTiers.get(7).add(JLResistance);
		HMapTiers.get(7).add(JComboResistance);
		HMapTiers.get(7).add(JLEffect);
		HMapTiers.get(7).add(JTFEffect);
		
		
		//Main description area, with scroller 
		JTADescription = new JTextArea(5,50);
		JTADescription.setLineWrap(true);
		JSPTADescription = new JScrollPane(JTADescription);
		
		//Bottom Buttons
		/**

		*/
		BottomButtons = new BottomButtonsManager(this);
		
		//Menues 
		MenuBarContainer menuBar = new MenuBarContainer(this);
		
		
		//Adding everything to the main panel
		MainJPanel.add(HMapTiers.get(1));
		MainJPanel.add(HMapTiers.get(2));
		MainJPanel.add(HMapTiers.get(3));
		MainJPanel.add(HMapTiers.get(4));
		MainJPanel.add(HMapTiers.get(5));
		MainJPanel.add(HMapTiers.get(6));
		MainJPanel.add(HMapTiers.get(7));
		MainJPanel.add(JSPTADescription);
		MainJPanel.add(BottomButtons.getPanel());
		MainJFrame.getContentPane().add(MainJPanel);
		//And adding the menubar
		MainJFrame.setJMenuBar(menuBar.getJMenuBar());
		
		MainJFrame.setVisible(true);
		MainJFrame.setMinimumSize(MainJFrame.getPreferredSize());
		MainJFrame.setLocationRelativeTo(null);
		JTFSpellName.requestFocus();
		
		SpellFile = new File("Spells.txt");
		
		
	} //private real constructor
	
	//Constructors --------------------------------------------------------
	public AddSpellFrame() {
		this.Constructor();
	}
	
	public AddSpellFrame(File parFile) {
		this.Constructor();
		this.setSpellFfile(parFile);
	}
	
	// Setters -------------------------------------------------------------
	public void setName(String parName){
		Name = parName;
	}
	
	public void setSpellFfile (File parFile){
		SpellFile = parFile;
		if (!SpellFile.exists()) {
			try {
				SpellFile.createNewFile();
			} catch (IOException e) {
				System.err.println("This was not suposed to happen, i checked if the file exists\n" +
						"in method setSpellFile(File), catch section");
				e.printStackTrace();
			}
		}
	}

	// getters -------------------------------------------------------------
	public JLabel getJLSpellName(){
		return JLSpellName;
	}
	public JFrame getMainJFrame(){
		return MainJFrame;
	}
	public File getSpellFile(){
		return SpellFile;
	}
	
	//Overrides 
	@Override
	public String toString(){
		return Name;
	}
	
	// Others ----------------------------------------------------------------
	public void AppendSpell (String parLine){
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(SpellFile,true));
			writer.write(parLine + "\n");
			writer.close();
			
		}catch (IOException ex) {
			System.err.println("Error Appending to Spellfile!!");
			ex.printStackTrace();
		}
		
	}
	
	public String getSpellasTextline (){
		/** we need to set a standard for this. Look on the docs folder for the current one
		 * SpellfileStandard.txt
		 */
		String options=null;
		String temp=null;
		boolean primero=true;
		boolean chainerror=false;
		
		//Spell name
		if (!chainerror){
			if (JTFSpellName.getText().isEmpty()) {
				JTFSpellName.requestFocus();
				chainerror=true;
			}else {
				options=JTFSpellName.getText()+";";
			}
		}
		
		//Spell casters
		primero=true;
		if (!chainerror) {
			for (SpellCaster line:SpellCasterarr) {
				if (line.getCheckBox().isSelected()) {
					if (primero){
						options = options + line.toString();
						primero=false;
					}else {
						options = options + ", "+line.toString();
					}
				}//if its enabled
			}
			if (primero){
				chainerror=true;
			}

			options = options + ";";
		}
		
		//Schools
		if (!chainerror) {
			options = options + Schoolcombo.toString()+";";
		}
		//Descriptors
		if (!chainerror){
			options = options + Descriptors.toString()+";";
		}
		//Components
		if (!chainerror) {
			primero=true;
			for (String line:SComponents){
				if (HMapComponents.get(line).isSelected()){
					if (primero){
						options = options + line;
						primero=false;
					}else{
						options = options +", "+ line;
					}
				}
			}
			options = options + ";";
		}
		
		//Domains
		if (!chainerror) {
			primero=true;
			for (JCheckBox line:ArrayJcheckDomains){
				if (line.isSelected()){
					if (primero){
						options = options + line.getText();
					}else {
						options = options + ", "+ line.getText();
					}
				}
			} //for each element in ArrayJcheckDomains
			options = options + ";";
		}//if chain errors
		
		//casting time
		if (!chainerror) {
			
			temp = (String)JComboCastingtimeunit.getSelectedItem();
			if (!temp.isEmpty()){
				options = options + JTACastingTimeNumber.getValue() +" "+temp;
			}else {
				chainerror=true;
			}
			options = options + ";";
		}
		//Range
		temp=null;
		if (!chainerror) {
			temp = (String)JComboRange.getSelectedItem();
			if (!temp.isEmpty()){
				options = options + temp+";";
			}else{
				chainerror=true;
			}
			
		}
		//Targets
		if (!chainerror) {
			if (!JTFTarget.getText().isEmpty()){
				options = options + JTFTarget.getText()+";";
			}else{
				chainerror=true;
			}
		}

		//Duration
		if (!chainerror) {
			options = options + JComboDuration.getSelectedItem()+";";
		}
		//Saving Throw
		if (!chainerror) {
			options = options + SavingThrow.toString()+";";
		}
		//spell resistance
		if (!chainerror) {
			options = options + JComboResistance.getSelectedItem()+";";
		}
		//effect
		if (!chainerror) {
			options = options + JTFEffect.getText()+";";
		}
		//Full description
		if (!chainerror) {
			options = options + JTADescription.getText()+";";
		}
		
		//And if nothing went wrong, we report the result
		if (chainerror) {
			return "";
		}else {
			return options;
		}
	}
	
	public void  resetForm (){
		JTFSpellName.setText("");
		
		for (SpellCaster line:SpellCasterarr) {
			line.clear();
		}
		
		Schoolcombo.reset();
		
		Descriptors.SetAllDisabled();
		
		for (String line:SComponents){
			HMapComponents.get(line).setSelected(false);
		}
		
		for (JCheckBox line:ArrayJcheckDomains){
			line.setSelected(false);
			}
		
		JTACastingTimeNumber.setValue(1);
		JComboCastingtimeunit.setSelectedIndex(0);
		
		JComboRange.setSelectedIndex(0);
		
		JTFTarget.setText("");
		
		JComboDuration.setSelectedIndex(0);
		
		SavingThrow.clear();
		
		JComboResistance.setSelectedIndex(0);
		
		JTFEffect.setText("");
		
		JTADescription.setText("");
		
	}

	public void dispose() {
		//nothing much for now, but i might need it in the future
		MainJFrame.dispose();
	}
	

} //public class MFrame
