import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

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
public class TestDescriptorWidget {

	/**
	 * @param args
	 */
	
	public DescriptorWidget test;
	
	public static void main(String[] args) {
		TestDescriptorWidget maingui = new TestDescriptorWidget();
		maingui.go();

	}

	private void go() {
		JFrame mainframe = new JFrame();
		JButton boton = new JButton("press me");
		test = new DescriptorWidget();
		
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainframe.getContentPane().add(test.getJPanel(),BorderLayout.CENTER);
		mainframe.getContentPane().add(boton,BorderLayout.SOUTH);
		
		boton.addActionListener(new botonlistener());
		
		mainframe.setSize(400, 400);
		mainframe.setVisible(true);
		
		
	}
	
	public class botonlistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(test.getListEnabled());
			
		}
		
	}

}
