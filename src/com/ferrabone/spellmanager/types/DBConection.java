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
import java.util.ArrayList;


/**
 * @author cferrabo
 *
 */
public class DBConection {
	private String dbfile=null;
	
	/**
	 * No Args constructor. 
	 * Remember to set the db file before using db related methods.
	 * It does load the driver
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
	 * Sets the DBFile to the specified one 
	 * 
	 * @param dbfile the dbfile to set
	 */
	public void setDbfile(String dbfile) {
		this.dbfile = dbfile;
	}

	/**
	 * @return the dbfile (String)
	 */
	public String getDbfile() {
		return dbfile;
	}
	
	/**
	 * Search the database for the ID of a spell, given the name (exactly)
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
		//Create Statement for later execution
		PreparedStatement prep = connection.prepareStatement("SELECT COUNT(spell_id) AS COUNT FROM spells WHERE spell_name = ? ;");
		// Set the variable value of statement
		prep.setString(1, spellName);
		//Execute the statement
		rs = prep.executeQuery();
		//A ResultSet cursor is initially positioned before the first row; 
		//the first call to the method "next" makes the first row the current row;
		rs.next(); 
		// Get the "COUNT" of results
		RScount = rs.getInt("COUNT");

		CloseConections(rs,null,null);
		if (RScount == 0){
			// no results, nothing to do. 
			//this is just a place holder, i know my logic is flawed, so i will need this
		}else {
			if (RScount != 1){
				//To many results, what do we do!!!
			}else{
				//Create Statement for later execution (reusing existing var)
				prep = connection.prepareStatement("SELECT spell_id FROM spells WHERE spell_name = ?;");
				// Set the variable value of statement
				prep.setString(1, spellName);
				//Execute the statement
				rs = prep.executeQuery();
				//A ResultSet cursor is initially positioned before the first row; 
				//the first call to the method "next" makes the first row the current row;
				rs.next(); //i can assume the result set has 1 element
				spellid = rs.getInt("spell_id");
				CloseConections(rs,null,null);
			}
		}
		CloseConections(null,prep,connection);

		/* Return the spell ID recovered from the DB.
		 * if the search had no results, the spellid var has the value -1 (set during creation)
		 */
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
		ClassInfo tmpCaster=null;
		SchoolInfo tmpSchoolInfo=null;
		RangeType tmpRange=null;
		ArrayList<IDStringPairType> tmpArray= new ArrayList<IDStringPairType>();
		ArrayList<ClassInfo> tmpArrCaster = new ArrayList<ClassInfo>();
			
		//open DB and start query
		connection = DriverManager.getConnection("jdbc:sqlite:"+getDbfile());
		
		//was going to use one natural join SELECT to get one spell at a time
		//but a few tries with the example database and test java code made me
		//decide to separate this into a few SELECTS
		
		/*
		 * --- first the main body with all the 1to1 tables 
		 */
		prep = connection.prepareStatement("SELECT * FROM Vspells WHERE spell_id = ? ;");  
		//pass the id to the sql connection thingy and execute it
		prep.setInt(1, id);
		rs = prep.executeQuery();
		/*
		 * it should be imposible to get more than 1 spell with the same id, 
		 * unless SQLite or the SQLite3 jdbc driver im using frack things up big time
		 */
		rs.next();
		//and now we finaly get ths tuff out and into a spell obj
		tmpSpell = new SpellClass(rs.getString("spell_name"));
		tmpSpell.setID(rs.getInt("spell_id"));
		
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
		tmpRange = new RangeType(rs.getInt("range_id"), rs.getString("range_name"), rs.getString("range_distance"));
		tmpSpell.setRange(tmpRange);
		
		//target
		tmpSpell.setTargets(rs.getString("target"));
		
		//duration
		tmpPair = new IDStringPairType(rs.getInt("duration_id"), rs.getString("duration"));
		tmpSpell.setDuration(tmpPair);
		
		//save
		tmpPair = new IDStringPairType(rs.getInt("save_id"), rs.getString("save"));
		tmpSpell.setSavingThrow(tmpPair);
		
		//Resistance
		tmpPair = new IDStringPairType(rs.getInt("resistance_id"), rs.getString("resistance_text"));
		tmpSpell.setResistance(tmpPair);
		
		//effect 
		tmpSpell.setEffect(rs.getString("effect"));
		
		//Text
		tmpSpell.setText(rs.getString("text_id"));
		
		//cleanup the JDBC
		CloseConections(rs,prep,null);
		// --- Now, one by one, to select the 1toMany tables
		
		// Descriptors
		prep = connection.prepareStatement("SELECT * FROM descriptor_info NATURAL JOIN descriptors WHERE spell_id = ?;");
		prep.setInt(1, id);
		rs = prep.executeQuery();
		
		while (rs.next()){
			tmpPair = new IDStringPairType(rs.getInt("descriptor_id"), rs.getString("descriptor_name"));
			tmpArray.add(tmpPair);
		}
		tmpSpell.setDescriptors(tmpArray);
		
		//cleanup the JDBC
		CloseConections(rs,prep,null);
		
		//Casters
		prep = connection.prepareStatement("SELECT * FROM class_info NATURAL JOIN casters WHERE spell_id = ?;");
		prep.setInt(1, id);
		rs = prep.executeQuery();
		
		while (rs.next()){
			tmpCaster = new ClassInfo(rs.getInt("class_id"), rs.getString("class_name"), rs.getInt("Level"));
			tmpArrCaster.add(tmpCaster);
		}
		tmpSpell.setCasters(tmpArrCaster);
		
		//cleanup the JDBC
		CloseConections(rs,prep,null);
		
		//components
		prep = connection.prepareStatement("SELECT * FROM components_info NATURAL JOIN components WHERE spell_id = ?;");
		prep.setInt(1, id);
		rs = prep.executeQuery();
		
		tmpArray= new ArrayList<IDStringPairType>();
		while (rs.next()){
			tmpPair = new IDStringPairType(rs.getInt("component_id"), rs.getString("component_name"));
			tmpArray.add(tmpPair);
		}
		tmpSpell.setComponents(tmpArray);
		
		//cleanup the JDBC
		CloseConections(rs,prep,null);
		
		//domains
		prep = connection.prepareStatement("SELECT * FROM domain_info NATURAL JOIN domains WHERE spell_id = ?;");
		prep.setInt(1, id);
		rs = prep.executeQuery();
		
		tmpArray= new ArrayList<IDStringPairType>();
		while (rs.next()){
			tmpPair = new IDStringPairType(rs.getInt("domain_id"), rs.getString("domain_name"));
			tmpArray.add(tmpPair);
		}
		tmpSpell.setDomains(tmpArray);
		
		//And finaly close everything
		CloseConections(rs,prep,connection);
		
		return tmpSpell;
	}
	
	/**
	 * Inserts the spell in the DB
	 * @param spell SpellClass object holding the spell.
	 * @return true only if the spell was fully and successfully written 
	 * @throws SQLException 
	 */
	public boolean writeSpell(SpellClass spell) throws SQLException{
		boolean result=false;
		Connection connection = null;
		PreparedStatement prep = null;

		
		// TODO write this method writeSpell
		//open database and start transaction
		connection = DriverManager.getConnection("jdbc:sqlite:"+getDbfile());
		
		// insert main table data
		prep = connection.prepareStatement("INSERT INTO spells (spell_name,time_id,range_id,"+
				"target,duration_id,save_id,resistance_id,effect,text_id) "+
				"VALUES (?,?,?,?,?,?,?,?,?);");
		
		prep.setString(1, spell.getName());
		prep.setInt(2, spell.getCastingTime().getID());
		prep.setInt(3, spell.getRange().getID());
		prep.setString(4, spell.getTargets());
		prep.setInt(5, spell.getDuration().getID());
		prep.setInt(6, spell.getSavingThrow().getID());
		prep.setInt(7, spell.getResistance().getID());
		prep.setString(8, spell.getEffect());
		prep.setString(9, spell.getText());
		
		prep.executeUpdate();
		CloseConections(null,prep,null);
		
		// search for spell id
		spell.setID(this.getSpellbyName(spell.getName()).getID());
		
		//******
		// insert double join table data
		//******
		
		// Enter batch mode
		connection.setAutoCommit(false);
		
		// Class info
		// Prepare the statement 
		prep = connection.prepareStatement("INSERT INTO class_info VALUES(?,?,?);");
		


		//for each caster in the array
		for ( ClassInfo caster : spell.getCasters()){

			// set the 3 values to insert
			prep.setInt(1, spell.getID());  // Spell ID
			prep.setInt(2, caster.getID()); // Caster ID
			prep.setInt(3, caster.getLevel()); // Level
			//and add to the transaction
			prep.executeUpdate();
		}
		//commit the transaction, and close the prepared statement
		//The commit is only needed because the auto commit setting is of
		connection.commit();
		CloseConections(null,prep,null);
		
		
		
		// Descriptor Info
		prep = connection.prepareStatement("INSERT INTO descriptor_info VALUES(?,?);");
		
		//for each Descriptor in the array
		for ( IDStringPairType descriptors : spell.getDescriptors()){

			// set the 2 values to insert
			prep.setInt(1, descriptors.getID()); // Descriptor ID
			prep.setInt(2, spell.getID());  // Spell ID
			//and add to the transaction
			prep.executeUpdate();
		}
		//commit the transaction, and close the prepared statement
		//The commit is only needed because the auto commit setting is of
		connection.commit();
		CloseConections(null,prep,null);
		
		
		
		// School Info
		prep = connection.prepareStatement("INSERT INTO school_info VALUES(?,?,?);");
		
		prep.setInt(1, spell.getSchool().getSchool().getID()); // School ID
		prep.setInt(2, spell.getSchool().getSubSchool().getID()); // Subschool ID
		prep.setInt(3, spell.getID());  // Spell ID
		
		prep.executeUpdate();
		//commit the transaction, and close the prepared statement
		//The commit is only needed because the auto commit setting is of
		connection.commit();
		CloseConections(null,prep,null);
		
		
		
		// Components Info
		prep = connection.prepareStatement("INSERT INTO components_info VALUES(?,?);");
		
		//for each Component in the array
		for ( IDStringPairType Components : spell.getComponents()){

			// set the 2 values to insert
			prep.setInt(1, Components.getID()); // component ID
			prep.setInt(2, spell.getID());  // Spell ID
			//and add to the transaction
			prep.executeUpdate();
		}
		//commit the transaction, and close the prepared statement
		//The commit is only needed because the auto commit setting is of
		connection.commit();
		CloseConections(null,prep,null);
		
		
		
		// Domain Info
		prep = connection.prepareStatement("INSERT INTO domain_info VALUES(?,?);");
		
		//for each Component in the array
		for ( IDStringPairType domain : spell.getDomains()){

			// set the 2 values to insert
			prep.setInt(1, domain.getID()); // domain ID
			prep.setInt(2, spell.getID());  // Spell ID
			//and add to the transaction
			prep.executeUpdate();
		}
		//commit the transaction, and close the prepared statement
		//The commit is only needed because the auto commit setting is of
		connection.commit();
		
		CloseConections(null,prep,connection);
		return result;
	}
	
	
	/**
	 * Closes the Database related objs passed as parameters.
	 * Parameters can be null, in which case they will simple be ignored
	 * @param ResultSet 
	 * @param PrepStmt
	 * @param connection
	 * @throws SQLException 
	 */
	private void CloseConections(ResultSet resultSet, PreparedStatement PrepStmt,Connection connection) throws SQLException{
		if (resultSet != null){
			resultSet.close();
		}
		if (PrepStmt != null){
			PrepStmt.close();
		}
		if (connection != null){
			connection.close();
		}
		
	}
	

}
