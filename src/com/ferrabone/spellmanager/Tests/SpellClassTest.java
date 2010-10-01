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
package com.ferrabone.spellmanager.Tests;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.ferrabone.spellmanager.types.ClassInfo;
import com.ferrabone.spellmanager.types.IDStringPairType;
import com.ferrabone.spellmanager.types.RangeType;
import com.ferrabone.spellmanager.types.SchoolInfo;
import com.ferrabone.spellmanager.types.SpellClass;

/**
 * @author cferrabo
 *
 */
public class SpellClassTest extends TestCase {

	/**
	 * @param name
	 */
	public SpellClassTest(String name) {
		super(name);
	}
	
	private SpellClass createSpell(){
		SpellClass tmpSpell = new SpellClass("Test");
		
		IDStringPairType tmpPair=null;
		ClassInfo tmpCaster=null;
		SchoolInfo tmpSchoolInfo=null;
		RangeType tmpRange=null;
		ArrayList<IDStringPairType> tmpArray= new ArrayList<IDStringPairType>();
		ArrayList<ClassInfo> tmpArrCaster = new ArrayList<ClassInfo>();
			
		
		tmpSpell.setID(3);
		
		//school
		tmpSchoolInfo = new SchoolInfo();
		
		tmpPair = new IDStringPairType(1, "casa");
		tmpSchoolInfo.setSchool(tmpPair);
		
		tmpPair = new IDStringPairType(1, "banio");
		tmpSchoolInfo.setSubSchool(tmpPair);
		
		tmpSpell.setSchool(tmpSchoolInfo);
		
		//spellcasting time
		tmpPair = new IDStringPairType(1, "1 round");
		tmpSpell.setCastingTime(tmpPair);
		
		//range
		tmpRange = new RangeType(1, "short short", "5 foots");
		tmpSpell.setRange(tmpRange);
		
		//target
		tmpSpell.setTargets("algun papanatas");
		
		//duration
		tmpPair = new IDStringPairType(1, "all day long");
		tmpSpell.setDuration(tmpPair);
		
		//save
		tmpPair = new IDStringPairType(1, "smartsypants save");
		tmpSpell.setSavingThrow(tmpPair);
		
		//Resistance
		tmpPair = new IDStringPairType(1, "nopes");
		tmpSpell.setResistance(tmpPair);
		
		//effect 
		tmpSpell.setEffect("summons a dude");
		
		//Text
		tmpSpell.setText("bill or ted dudes");
		
		tmpPair = new IDStringPairType(1, "awsome");
		tmpArray.add(tmpPair);
		tmpPair = new IDStringPairType(1, "dude");
		tmpArray.add(tmpPair);

		tmpSpell.setDescriptors(tmpArray);
		
		//Casters

		tmpCaster = new ClassInfo(1, "white mage", 10);
		tmpArrCaster.add(tmpCaster);
		
		tmpCaster = new ClassInfo(2, "Dog", 1);
		tmpArrCaster.add(tmpCaster);

		tmpSpell.setCasters(tmpArrCaster);
	
		//components

		tmpArray= new ArrayList<IDStringPairType>();

		tmpPair = new IDStringPairType(1, "verbical");
		tmpArray.add(tmpPair);
		tmpPair = new IDStringPairType(1, "mimic");
		tmpArray.add(tmpPair);
		tmpPair = new IDStringPairType(1, "money");
		tmpArray.add(tmpPair);

		tmpSpell.setComponents(tmpArray);
		
		//domains		
		tmpArray= new ArrayList<IDStringPairType>();
		tmpPair = new IDStringPairType(1, "Cool");
		tmpArray.add(tmpPair);

		tmpSpell.setDomains(tmpArray);
		

		return tmpSpell;
	}

	/**
	 * Test method for {@link com.ferrabone.spellmanager.types.SpellClass#toString()}.
	 */
	public void testToString() {
		SpellClass tmpSpell = this.createSpell();
		
		System.out.println(tmpSpell.toString());
		
	}

}
