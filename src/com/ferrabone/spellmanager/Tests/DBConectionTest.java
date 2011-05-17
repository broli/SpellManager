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

import java.sql.SQLException;

import com.ferrabone.spellmanager.types.DBConection;
import com.ferrabone.spellmanager.types.SpellClass;

import junit.framework.TestCase;

/**
 * @author cferrabo
 *
 */
public class DBConectionTest extends TestCase {

	/**
	 * @param name
	 */
	public DBConectionTest(String name) {
		super(name);
	}

	/**
	 * Test method for {@link com.ferrabone.spellmanager.types.DBConection#DBConection(java.lang.String)}.
	 */
	public void testDBConectionString() {
		try {
			DBConection test = new DBConection("example.db");
			test.getClass();
		} catch (ClassNotFoundException e) {
			// 
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.ferrabone.spellmanager.types.DBConection#getSpellID(java.lang.String)}.
	 */
	public void testGetSpellID() {
		try {
			DBConection test = new DBConection("example.db");
			System.out.println(test.getSpellID("Acid Arrow",null));
			
		} catch (ClassNotFoundException e) {
			// 
			e.printStackTrace();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.ferrabone.spellmanager.types.DBConection#getSpellbyName(java.lang.String)}.
	 */
	public void testGetSpellbyName() {
		try {
			SpellClass spell = null;
			DBConection test = new DBConection("example.db");
			spell = test.getSpellbyName("Acid Arrow");
			System.out.println(spell.toString());
			
		} catch (ClassNotFoundException e) {
			// 
			e.printStackTrace();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.ferrabone.spellmanager.types.DBConection#getSpellByID(int)}.
	 */
	public void testGetSpellByID() {
		try {
			SpellClass spell = null;
			DBConection test = new DBConection("example.db");
			spell = test.getSpellByID(2);
			System.out.println(spell.toString());
			
		} catch (ClassNotFoundException e) {
			// 
			e.printStackTrace();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
	}
	

	/**
	 * Test method for {@link com.ferrabone.spellmanager.types.DBConection#writeSpell(SpellClass)}.
	 */
	public void testwriteSpell() {
		try {
			SpellClass spell = null;
			int result=1;
			
			DBConection test = new DBConection("example.db");
			spell = test.getSpellByID(1);
			
			// modifico el spell para grabar como nuevo
			String name = spell.getName();
			int numero = spell.getID();
			
			spell.setName(name+" "+numero);
			
			result = test.writeSpell(spell);
			
			System.out.println("did it write? "+result);
			
			
		} catch (ClassNotFoundException e) {
			// 
			e.printStackTrace();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
	}
	
	
	/*
	public static Test suite(){
		return new TestSuite(DBConectionTest.class);
	}*/

}
