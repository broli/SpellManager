package com.ferrabone.spellmanager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
 * Class to be called when a button is pressed
 */
public class BottomButtonsManager {
	
	private AddSpellFrame MainFrame=null;
	private JPanel JPControls=null;
	private JButton JBNext=null;
	private JButton JBAddQuit = null;
	private JButton JBQuit = null;
	private JButton JBClear = null;
	
	//Constructors ---------------------------------------
	public BottomButtonsManager(AddSpellFrame parFrame){
		this.setParentFrame(parFrame);
		JPControls= new JPanel();
		JPControls.setLayout(new BoxLayout(JPControls,BoxLayout.LINE_AXIS));
		
		JBNext = new JButton("Add & Next");
		JBNext.addActionListener(new AddNextListener());
		JBAddQuit = new JButton("Add & Quit");
		JBQuit = new JButton("Quit");
		JBQuit.addActionListener(new QuitListener());
		JBClear = new JButton("Clear");
		JBClear.addActionListener(new ClearListener());
		
		JPControls.add(Box.createHorizontalGlue());
		JPControls.add(JBQuit);
		JPControls.add(Box.createRigidArea(new Dimension(30, 0)));
		JPControls.add(JBAddQuit);
		JPControls.add(Box.createRigidArea(new Dimension(10, 0)));
		JPControls.add(JBClear);
		JPControls.add(Box.createRigidArea(new Dimension(10, 0)));
		JPControls.add(JBNext);
	}
	
	
	//Setters 
	public void setParentFrame(AddSpellFrame parFrame){
		MainFrame=parFrame;
	}
	
	//Getters
	public JPanel getPanel(){
		return JPControls;
	}
	
	// Inner Clases *****************************************************************
	public class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainFrame.dispose();			
		}//Override actionPerformed
		
	}//Class quitlistener
	
	//Class Add&next listener
	public class AddNextListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String line=null;
			line = MainFrame.getSpellasTextline();
			if (!line.isEmpty()){
				MainFrame.AppendSpell(line);
				MainFrame.resetForm();
			}else {
				// TODO show a message saying there was an error generating the string
			}
			
		}//Override actionPerformed
		
	}//inner class AddNextListener
	
	//clas Add and Quit
	public class AddandQuit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String line=null;
			line = MainFrame.getSpellasTextline();
			if (!line.isEmpty()){
				MainFrame.AppendSpell(line);
				MainFrame.dispose();
			}else {
				// TODO show a message saying there was an error generating the string
			}
			
		}
		
	}
	
	//Class ClearListener
	public class ClearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.resetForm();
		}
		
	}

}
