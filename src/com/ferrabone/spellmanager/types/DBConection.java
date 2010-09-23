package com.ferrabone.spellmanager.types;
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


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author cferrabo
 *
 */
public class DBConection {
	private String dbfile=null;
	
	/**
	 * No Args constructor. 
	 * Remember to set the db file before using db related methods.
	 * It does loads the driver
	 * @throws ClassNotFoundException 
	 */
	public DBConection() throws ClassNotFoundException{
		loadDriver();
	}

	/**
	 * Instantiates an obj (and loads the sqlite driver class)
	 * @param dbFile the full path to the dbfile in OS dependent format
	 * @throws ClassNotFoundException 
	 * @see File.separator
	 * 
	 */
	public DBConection(String dbFile) throws ClassNotFoundException{
		loadDriver();
		setDbfile(dbFile);
	}
	/**
	 * Tries to load the SQLite driver
	 * @throws ClassNotFoundException
	 */
	private void loadDriver() throws ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
	}

	/**
	 * @param dbfile the dbfile to set
	 */
	public void setDbfile(String dbfile) {
		this.dbfile = dbfile;
	}

	/**
	 * @return the dbfile
	 */
	public String getDbfile() {
		return dbfile;
	}
	
	/**
	 * Search a spell by the name (exactly)
	 * @param spellName
	 * @return SpellClass
	 * @throws SQLException 
	 */
	public SpellClass getSpellbyName(String spellName) throws SQLException{
		SpellClass tmpSpell = null;
		Connection connection = null;
		int RScount=0;
		ResultSet rs=null;

		// TODO write this method getSpellbyName

		//open DB and start query
		connection = DriverManager.getConnection("jdbc:sqlite:"+getDbfile());
		//Statement statement = connection.createStatement();

		//SELECT COUNT(spell_id) FROM spells WHERE spell_name = ' spellName ' ;
		PreparedStatement prep = connection.prepareStatement("SELECT COUNT(spell_id) AS COUNT FROM spells WHERE spell_name = '?';");
		prep.setString(1, spellName);
		rs = prep.executeQuery();
		//A ResultSet cursor is initially positioned before the first row; 
		//the first call to the method next makes the first row the current row;
		rs.next(); 
		RScount = rs.getInt("COUNT");
		if (RScount == 0){
			// no results, nothing to do. 
			//this is just a place holder, i know my logic is flawed, so i will need this if
		}else {
			if (RScount != 1){
				//To many results, what do we do!!!
			}else{
				prep = connection.prepareStatement("SELECT spell_id FROM spells WHERE spell_name = '?';");
				prep.setString(1, spellName);
				rs = prep.executeQuery();
				rs.next(); //i can assume the result set has 1 element(i hope)
				tmpSpell = getSpellByID(rs.get);
			}
		}

		//tmpSpell = getSpellByID(int id)
		
		return tmpSpell;
	}
	/**
	 * Search a spell by ID
	 * @param id 
	 * @return SpellClass
	 */
	public SpellClass getSpellByID(int id){
		SpellClass tmpSpell = new SpellClass();
		// TODO write this method getSpellByID
		//open DB and start query
		
		// Full join and selects and stuff using spell id
		
		return tmpSpell;
	}
	
	/**
	 * Inserts the spell in the DB
	 * @param spell SpellClass object holding the spell.
	 * @return true only if the spell was fully and successfully written 
	 */
	public boolean writeSpell(SpellClass spell){
		boolean result=false;
		// TODO write this method writeSpell
		//open database and start transaction
		
		// insert main table data
		
		// search for spell id
		
		// insert double join table data
		
		//commit or rollback

		return result;
	}
	

}
