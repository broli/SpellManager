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
	
	private AddSpellFrame MainJFrame=null;
	
	//Constructors ---------------------------------------
	public BottomButtonsManager(AddSpellFrame parFrame){
		this.setParentFrame(parFrame);
	}
	
	
	//Setters 
	public void setParentFrame(AddSpellFrame parFrame){
		MainJFrame=parFrame;
	}

}