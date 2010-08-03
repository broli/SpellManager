import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
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
 * @author carlos
 *
 */
public class ViewSpellsFrame {
	
	private JFrame MainJFrame = null;
	private JPanel MainJPanel = null;
    private String language="";
    private String country="";
    private Locale currentLocale;
    private ResourceBundle strings;
    


	
	public ViewSpellsFrame() {
		language = new String("en");
        country = new String("US");
        currentLocale = new Locale(language, country);
        strings = ResourceBundle.getBundle("LangPack",currentLocale);


		MainJFrame = new JFrame(strings.getString("ViewSpellsFrameTitle"));
		MainJPanel = new JPanel();
		
		// TODO add a text area, and load the text in here
		// TODO add buttons for prev, next, quit, and tied it to the reading
		
		
		MainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainJFrame.setVisible(true);
		MainJFrame.setSize(MainJFrame.getPreferredSize());
		MainJFrame.setMinimumSize(MainJFrame.getPreferredSize());
	}
	

}
