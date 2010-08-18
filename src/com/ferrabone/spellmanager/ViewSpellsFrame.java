package com.ferrabone.spellmanager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
public class ViewSpellsFrame {
	
	private JFrame MainJFrame = null;
	private JPanel MainJPanel = null;
	private JTextArea JTAtext = null;
	private JScrollPane JSPscroller = null;
	private JButton JBSave=null;
	private Settings settings;
	
	public ViewSpellsFrame(Settings parSettings) {
		settings = parSettings;
		
		MainJFrame = new JFrame(settings.getString("ViewSpellsFrameTitle"));
		MainJPanel = new JPanel();
		
		JTAtext = new JTextArea(10,40);
		
		JSPscroller = new JScrollPane(JTAtext);
		MainJPanel.add(JSPscroller);
		// TODO add buttons for prev, next, quit, and tied it to the reading
		JBSave= new JButton(settings.getString("Save"));
		JBSave.addActionListener(new saveListener());
		
		MainJFrame.getContentPane().add(MainJPanel);
		MainJFrame.getContentPane().add(JBSave,BorderLayout.SOUTH);
		MainJFrame.setVisible(true);
		
		loadAllSpells();
		JTAtext.setMinimumSize(JTAtext.getPreferredScrollableViewportSize());
		MainJFrame.setMinimumSize(MainJFrame.getPreferredSize());
		MainJFrame.setSize(MainJFrame.getPreferredSize());
		
	}

	private void loadAllSpells () {
		String line=null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(settings.getSpellFile()));
			while ((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line,";");
				while (st.hasMoreTokens()) {
					if (st.countTokens()==14){
						JTAtext.append(st.nextToken()+"\n");
					}else if (st.countTokens()==13){
						JTAtext.append(st.nextToken()+"\n");
					}else if (st.countTokens()==12){
						JTAtext.append(settings.getString("School")+": "+st.nextToken()+"\n");
					}else if (st.countTokens()==11){
						JTAtext.append(settings.getString("Descriptor")+": "+st.nextToken()+"\n");
					}else if (st.countTokens()==10){
						JTAtext.append(settings.getString("Components")+": "+st.nextToken()+"\n");
					}else if (st.countTokens()==9){
						JTAtext.append(settings.getString("Domains")+": "+st.nextToken()+"\n");
					}else if (st.countTokens()==8){
						JTAtext.append(settings.getString("Casting_Time")+": "+st.nextToken()+"\n");
					}else if (st.countTokens()==7){
						JTAtext.append(settings.getString("Range")+": "+st.nextToken()+"\n");
					}else if (st.countTokens()==6){
						JTAtext.append(settings.getString("Target(s)")+": "+st.nextToken()+"\n");
					}else if (st.countTokens()==5){
						JTAtext.append(settings.getString("Duration")+": "+st.nextToken()+"\n");
					}else if (st.countTokens()==4){
						JTAtext.append(settings.getString("SavingThrow")+": "+st.nextToken()+"\n");
					}else if (st.countTokens()==3){
						JTAtext.append(settings.getString("Spell Resistance")+": "+st.nextToken()+"\n");
					}else if (st.countTokens()==2){
						JTAtext.append(settings.getString("Effect")+": "+st.nextToken()+"\n");
					}else if (st.countTokens()==1){
						JTAtext.append(settings.getString("Description")+": "+st.nextToken()+"\n");
					}
					
					
				}
				for (int i=0;i<50;i++)
				{
					JTAtext.append("*");
				}
				JTAtext.append("\n");

			}
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	private class saveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			File outfile=null;
			JFileChooser JFC = new JFileChooser(outfile);
			int returnval = JFC.showOpenDialog(MainJFrame);
			if(returnval == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedWriter salida = new BufferedWriter(new FileWriter(JFC.getSelectedFile()));
					salida.write(JTAtext.getText());
					salida.close();
				} catch (IOException ioe) {
					// TODO Auto-generated catch block
					ioe.printStackTrace();
				}
			 }
			
		}
		
	}
	

}
