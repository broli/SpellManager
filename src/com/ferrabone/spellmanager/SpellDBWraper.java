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
package com.ferrabone.spellmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author carlos
 *
 */
public class SpellDBWraper {
	private String dataBase=null;
	private Connection connection = null;

	
	/**
	 * Defaults to file name Spell.db
	 * @throws SQLException if its no posible to create a conection to the DB
	 * @throws ClassNotFoundException if it cannot load the SQLite driver
	 */
	public SpellDBWraper() throws ClassNotFoundException, SQLException {
		dataBase="Spell.db";
		this.myconstructor();
	}
	/**
	 * 
	 * @param DataBase String with the SQLite3 database file
	 * @throws SQLException if its no posible to create a conection to the DB
	 * @throws ClassNotFoundException if it cannot load the SQLite driver
	 */
	public SpellDBWraper (String DataBase) throws ClassNotFoundException, SQLException{
		this.dataBase= DataBase;
		this.myconstructor();
	}
	/**
	 * 
	 * @throws ClassNotFoundException if it cannot load the SQLite driver
	 * @throws SQLException when problems occur while opening/closing the DB
	 */
	private void myconstructor() throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		//test opening and closing the databse
		connection = DriverManager.getConnection("jdbc:sqlite:"+dataBase);
		connection.close();
		
	}
	
	// TODO add function to insert spell
	// TODO add function to search spell
	// TODO add function to delete spell
	// TODO add function to alter? spell
}
