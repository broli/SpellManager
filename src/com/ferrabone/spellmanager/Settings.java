package com.ferrabone.spellmanager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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
public class Settings implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7503086199401682499L;
	
	
    private Locale currentLocale;
    transient private ResourceBundle strings;
    private File ConfDir;
    private File LangPackagedir;
    private File Configfile;
    private File SpellFile;
    private String LangPack = "LangPack";

    
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return currentLocale.getLanguage();
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return currentLocale.getCountry();
	}
	
	/**
	 * @param currentLocale the currentLocale to set
	 */
	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}
	
	
	/**
	 * Sets the current locale member using the language and country passed as parameters
	 * 
	 * @param language String with the language 
	 * @param country String with the country
	 * 
	 * both parameters in the format required by the Locale class
	 * @see Locale
	 */
	public void setCurrentLocale(String language,String country) {
		this.setCurrentLocale(new Locale(language,country));
	}
	
	/**
	 * @return the currentLocale
	 */
	public Locale getCurrentLocale() {
		return currentLocale;
	}
	
	/**
	 * @param strings the strings to set
	 */
	public void setStrings(ResourceBundle strings) {
		this.strings = strings;
	}
	
	/**
	 * sets the strings variable to point to the
	 * resource bundle in file with prefix "LangPack"
	 * and the current Locale
	 */
	public void updateStrings() throws MissingResourceException{
		try {
			setStrings(this.LangPack);
		} catch (MissingResourceException e)  {
			throw e;
		}
	}
	
	/**
	 * 
	 * @param LangPack Prefix of the Language file
	 */
	public void setStrings(String LangPack) throws MissingResourceException {
		try {
			this.strings = ResourceBundle.getBundle("Languages."+LangPack,this.currentLocale);
		} catch (MissingResourceException e)  {
			System.err.println("Cant find Language files in \n"+System.getProperty( "java.class.path" ));
			throw e;
		}
	}
	/**
	 * @return the strings
	 */
	public ResourceBundle getStrings() {
		return strings;
	}
	
	/**
	 * 
	 * @param Key the key to retrieve
	 * @return The string associated with the key
	 */
	public String getString(String Key) throws MissingResourceException {
		try {
			return this.getStrings().getString(Key);
		}catch (MissingResourceException e)  {
			throw e;
		}
	}

	/**
	 * @param configfile the configfile to set
	 */
	public void setConfigfile(File configfile) {
		Configfile = configfile;
	}
	/**
	 * @return the configfile
	 */
	public File getConfigfile() {
		return Configfile;
	}
	
	/**
	 * @param spellFile the spellFile to set
	 */
	public void setSpellFile(File spellFile) {
		SpellFile = spellFile;
	}
	/**
	 * @return the spellFile
	 */
	public File getSpellFile() {
		return SpellFile;
	}
	
	
	/**
	 * @param confDir the confDir to set
	 */
	public void setConfDir(File confDir) {
		ConfDir = confDir;
	}
	/**
	 * @return the confDir
	 */
	public File getConfDir() {
		return ConfDir;
	}
	
	/**
	 * @param langPackagedir the langPackagedir to set
	 */
	public void setLangPackagedir(File langPackagedir) {
		LangPackagedir = langPackagedir;
	}
	/**
	 * @return the langPackagedir
	 */
	public File getLangPackagedir() {
		return LangPackagedir;
	}
	/**
	 *
	 * This function updates the settings Locale, 
	 * reopens the message bunddle with the current language,
	 * and sets the default global Locale
	 * @param parLocale the new Locale.
	 */
	public void changeLanguage (Locale parLocale){
		setCurrentLocale(parLocale);
		updateStrings();
		Locale.setDefault(parLocale);
	}
	
	private void setDirs() throws FileNotFoundException{

		//a nice default
		ConfDir = new File(System.getProperty("user.home")+File.separator+".SpellManager");
		//test if it exist
		if (!ConfDir.exists()) {
			//if it dosnt, change to the current dir
			ConfDir = new File(System.getProperty("user.dir"));
		}
		
		Configfile = new File(ConfDir.getAbsolutePath()+File.separator+"config.cfg");
		LangPackagedir = new File(ConfDir.getAbsolutePath()+File.separator+"Languages");
		if (!LangPackagedir.exists()){
			//we dont have a language file
			System.err.println(System.getProperty("user.dir"));
			System.err.println(LangPackagedir.getAbsolutePath());
			throw new FileNotFoundException("Cant find Languages folder");
		}
		
		
	}
	
	public Settings() throws FileNotFoundException,MissingResourceException{
		this.setDirs();
		setCurrentLocale("en","US");
		myconstructor();
	}
	/**
	 * @param locale The locale to load the messageBundle
	 * @throws FileNotFoundException
	 */
	public Settings(Locale locale) throws FileNotFoundException,MissingResourceException{
		this.setDirs();
		setCurrentLocale(locale);
		myconstructor();
	}
	
	private void myconstructor() {
		this.SpellFile = new File(this.ConfDir.getAbsoluteFile()+File.separator+"SpellFile.txt");
		changeLanguage(this.currentLocale);
	}
	
}
