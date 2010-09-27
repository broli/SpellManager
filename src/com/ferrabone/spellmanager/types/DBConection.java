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
	 * Search the database for the ID of a spell, given the name
	 * @param spellName to search for
	 * @return Id of the spell, or -1 if its not found
	 * @throws SQLException 
	 */
	public int getSpellID(String spellName) throws SQLException{
		int spellid=-1;
		Connection connection = null;
		int RScount=0;
		ResultSet rs=null;

		//open DB and start query
		connection = DriverManager.getConnection("jdbc:sqlite:"+getDbfile());

		//SELECT COUNT(spell_id) FROM spells WHERE spell_name = ' spellName ' ;
		PreparedStatement prep = connection.prepareStatement("SELECT COUNT(spell_id) AS COUNT FROM spells WHERE spell_name = '?';");
		prep.setString(1, spellName);
		rs = prep.executeQuery();
		//A ResultSet cursor is initially positioned before the first row; 
		//the first call to the method next makes the first row the current row;
		rs.next(); 
		RScount = rs.getInt("COUNT");
		rs.close();
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
				spellid = rs.getInt("spell_id");
				rs.close();
			}
		}
		connection.close();

		
		return spellid;
	}
	
	/**
	 * Search a spell by the name (exactly)
	 * @param spellName
	 * @return SpellClass object or null if no spell matches
	 * @throws SQLException 
	 */
	public SpellClass getSpellbyName(String spellName) throws SQLException{
		SpellClass tmpSpell = null;
		
		tmpSpell = getSpellByID(getSpellID(spellName));

		return tmpSpell;
	}
	/**
	 * Search a spell by ID
	 * @param id 
	 * @return SpellClass
	 * @throws SQLException 
	 */
	public SpellClass getSpellByID(int id) throws SQLException{
		SpellClass tmpSpell = null;
		Connection connection = null;
		PreparedStatement prep = null;
		ResultSet rs=null;
		IDStringPairType tmpPair=null;
		SchoolInfo tmpSchoolInfo=null;
		RangeType tmpRange=null;
		
		// TODO write this method getSpellByID
		//open DB and start query
		connection = DriverManager.getConnection("jdbc:sqlite:"+getDbfile());
		
		//was going to use one natural join SELECT to get one spell at a time
		//but a few tries with the example database and test java code made me
		//decide to separate this into a few SELECTS
		
		/*
		 * --- first the main body with all the 1to1 tables 
		 * SELECT a.spell_id, a.spell_name, g.school_name, g.subschool_name, b.time,c.range_name,c.rage_distance, a.target, d.duration, e.save, f.resistance_text, a.effect, a.text_id
		 * 	FROM spells AS a NATURAL JOIN castingTime AS b NATURAL JOIN range AS c NATURAL JOIN duration AS d NATURAL JOIN savingThrow AS e NATURAL JOIN resistance AS f NATURAL JOIN (SELECT * FROM school_info NATURAL JOIN schools NATURAL JOIN subschools) as g
		 * WHERE a.spell_id = 1;
		 */
		prep = connection.prepareStatement("SELECT a.spell_id, a.spell_name, g.school_name, g.subschool_name, b.time,c.range_name,c.rage_distance, a.target, d.duration, e.save, f.resistance_text, a.effect, a.text_id FROM spells AS a NATURAL JOIN castingTime AS b NATURAL JOIN range AS c NATURAL JOIN duration AS d NATURAL JOIN savingThrow AS e NATURAL JOIN resistance AS f NATURAL JOIN (SELECT * FROM school_info NATURAL JOIN schools NATURAL JOIN subschools) as g WHERE a.spell_id = '?';");  
		//pass the id to the sql connection thingy and execute it
		prep.setInt(1, id);
		rs = prep.executeQuery();
		/*
		 * it should be imposible to get more than 1 spell with the same id, 
		 * unless SQLite or the SQLite3 jdbc driver im using frack thing up big time
		 */
		rs.next();
		//and now we finaly get ths tuff out and into a spell obj
		tmpSpell = new SpellClass(rs.getString("a.spell_name"));
		
		//school
		tmpSchoolInfo = new SchoolInfo();
		
		tmpPair = new IDStringPairType(rs.getInt("school_id"), rs.getString("school_name"));
		tmpSchoolInfo.setSchool(tmpPair);
		
		tmpPair = new IDStringPairType(rs.getInt("subschool_id"), rs.getString("subschool_name"));
		tmpSchoolInfo.setSubSchool(tmpPair);
		
		tmpSpell.setSchool(tmpSchoolInfo);
		
		//spellcasting time
		tmpPair = new IDStringPairType(rs.getInt("time_id"), rs.getString("time"));
		tmpSpell.setCastingTime(tmpPair);
		
		//range
		tmpRange = new RangeType(rs.getInt("range_id"), rs.getString("range_name"), rs.getShort("range_distance"));
		tmpSpell.setRange(tmpRange);
		
		
		
		
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
