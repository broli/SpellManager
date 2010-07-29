import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
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
 * @author cferrabo
 *
 */
public class SavingThrowWidget {
	
	private final String[] SSavingThrow = { "None","Negates","Partial","Half","Disbelief","harmless"};
	
	private JComboBox JComboSavingThrow=null;
	private JLabel JLSavingThrow=null;
	private JTextField JTASavingThrow=null;
	private String Name=null;
	private JPanel myPanel=null;
	
	public SavingThrowWidget(){
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel,BoxLayout.X_AXIS));
		JComboSavingThrow = new JComboBox(SSavingThrow);
		JComboSavingThrow.setEditable(true);
		JComboSavingThrow.setMaximumSize(JComboSavingThrow.getPreferredSize());
		JComboSavingThrow.addActionListener(new ComboListener());
		
		JLSavingThrow = new JLabel(" Saving Throw ");
		JTASavingThrow = new JTextField();
		Name="Saving Throw";
		
		myPanel.add(JLSavingThrow);
		myPanel.add(JComboSavingThrow);
		myPanel.add(JTASavingThrow);
		
	}
	
	public String getName(){
		return Name;
	}
	public JComboBox getCombo(){
		return JComboSavingThrow;
	}
	public JLabel getLabel(){
		return JLSavingThrow;
	}
	public JTextField getTextField(){
		return JTASavingThrow;
	}
	public JPanel getJPanel(){
		return myPanel;
	}
	
	public class ComboListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
		}
		
	}
}
