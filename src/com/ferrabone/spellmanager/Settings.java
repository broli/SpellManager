package com.ferrabone.spellmanager;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
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
	
    private String language="";
    private String country="";
    private Locale currentLocale;
    private ResourceBundle strings;
    private File ConfDir;
    private File LangPackagedir;
    private File Configfile;
    private File SpellFile;

    
    
	/**
	 * @param language the language to set
	 * if the String is empty, it sets the default
	 */
	public void setLanguage(String language) {
		if (!language.isEmpty()){
			this.language = language;
		}else {
			setDefaultLanguage();
		}
	}
	/**
	 * Sets the default language to "en"
	 */
	public void setDefaultLanguage() {
		this.language = "en";
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param country the country to set
	 * if the string is empty, the country is set to US
	 */
	public void setCountry(String country) {
		if (!country.isEmpty()){
			this.country = country;
		}else {
			setDefaultCountry();
		}
	}
	
	/**
	 * Sets the current country to "US"
	 */
	public void setDefaultCountry() {
		this.country = "US";
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param currentLocale the currentLocale to set
	 */
	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}
	
	/**
	 * Sets the current locale member using the currently
	 * selected language and country
	 */
	public void setCurrentLocale() {
		this.currentLocale = new Locale(this.language, this.country);
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
		this.currentLocale = new Locale(language, country);
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
	public void setDefaultStrings() {
		this.strings = ResourceBundle.getBundle("LangPack",this.currentLocale);
	}
	
	/**
	 * 
	 * @param LangPack Prefix of the Language file
	 */
	public void setStrings(String LangPack) {
		this.strings = ResourceBundle.getBundle(LangPack,this.currentLocale);
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
	public String getString(String Key){
		return this.getStrings().getString(Key);
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
	
	private void myconstructor () throws IOException{

		//a nice default
		ConfDir = new File(System.getProperty("user.home")+File.pathSeparator+".SpellManager");
		//test if it exist
		if (!ConfDir.exists()) {
			//if it dosnt, change to the current dir
			ConfDir = new File(System.getProperty("user.dir"));
		}
		
		Configfile = new File(ConfDir.getAbsolutePath()+File.pathSeparator+"config.cfg");
		LangPackagedir = new File(ConfDir.getAbsolutePath()+File.pathSeparator+"Languages");
		if (!LangPackagedir.exists()){
			//we dont have a language file
			throw new IOException("Cant find Languages folder");
		}
		
		setDefaultLanguage();
		setDefaultCountry();
		setCurrentLocale();
		setDefaultStrings();
	}
	
	public Settings() throws IOException{
		this.myconstructor();
	}
}
