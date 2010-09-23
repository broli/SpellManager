statement.execute("CREATE TABLE casters ( class_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, class_name TEXT(50) NOT NULL );");

statement.execute("CREATE TABLE schools  ( school_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, school_name TEXT(50) NOT NULL );");

statement.execute("CREATE TABLE subschools ( subschool_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, subschool_name TEXT(50) NOT NULL );");

statement.execute("CREATE TABLE school_info ( school_id INTEGER NOT NULL  REFERENCES schools  (school_id), subschool_id INTEGER NOT NULL  REFERENCES subschools (subschool_id), spell_id INTEGER NOT NULL  REFERENCES spells (spell_id));");

statement.execute("CREATE TABLE descriptors ( descriptor_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, descriptor_name TEXT(50) NOT NULL );");

statement.execute("CREATE TABLE descriptor_info ( descriptor_id INTEGER NOT NULL  REFERENCES descriptors (descriptor_id), spell_id INTEGER NOT NULL  REFERENCES spells (spell_id));");

statement.execute("CREATE TABLE components ( component_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, component_name TEXT(50) NOT NULL );");

statement.execute("CREATE TABLE domains ( domain_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, domain_name TEXT(50) NOT NULL );");

statement.execute("CREATE TABLE range ( range_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, range_name TEXT(50) NOT NULL , rage_distance INTEGER NOT NULL );");

statement.execute("CREATE TABLE resistance ( resistance_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, resistance_text TEXT(20) NOT NULL );");

statement.execute("CREATE TABLE spells ( spell_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, spell_name TEXT(50) NOT NULL , casting_time TEXT(100) DEFAULT NULL, range_id INTEGER NOT NULL  REFERENCES range (range_id), targets TEXT(200) DEFAULT NULL, duration TEXT(50) NOT NULL , saving_throw TEXT(200) NOT NULL , resistance_id INTEGER NOT NULL  REFERENCES resistance (resistance_id), effect TEXT(250) NOT NULL , text_id INTEGER NOT NULL  REFERENCES textdesc (text_id));");

statement.execute("CREATE TABLE class_info ( spell_id INTEGER NOT NULL  REFERENCES spells (spell_id), class_id INTEGER NOT NULL  REFERENCES casters (class_id)), Level INTEGER NOT NULL  DEFAULT 0;");

statement.execute("CREATE TABLE components_info ( component_id INTEGER NOT NULL  REFERENCES components (component_id), spell_id INTEGER NOT NULL  REFERENCES spells (spell_id));");

statement.execute("CREATE TABLE domain_info ( domain_id INTEGER NOT NULL  REFERENCES domains (domain_id), spell_id INTEGER NOT NULL  REFERENCES spells (spell_id));");

statement.execute("CREATE TABLE textdesc ( text_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, text TEXT NOT NULL );");

