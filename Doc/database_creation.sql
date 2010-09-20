CREATE TABLE casters (
class_id NUMERIC NOT NULL  PRIMARY KEY AUTOINCREMENT,
class_name TEXT(50) NOT NULL 
);

CREATE TABLE schools  (
school_id NUMERIC NOT NULL  PRIMARY KEY AUTOINCREMENT,
school_name TEXT(50) NOT NULL 
);

CREATE TABLE subschools (
subschool_id NUMERIC NOT NULL  PRIMARY KEY AUTOINCREMENT,
subschool_name TEXT(50) NOT NULL 
);

CREATE TABLE school_info (
school_id NUMERIC NOT NULL  REFERENCES schools  (school_id),
subschool_id NUMERIC NOT NULL  REFERENCES subschools (subschool_id),
spell_id NUMERIC NOT NULL  REFERENCES spells (spell_id)
);

CREATE TABLE descriptors (
descriptor_id NUMERIC NOT NULL  PRIMARY KEY AUTOINCREMENT,
descriptor_name TEXT(50) NOT NULL 
);

CREATE TABLE descriptor_info (
descriptor_id NUMERIC NOT NULL  REFERENCES descriptors (descriptor_id),
spell_id NUMERIC NOT NULL  REFERENCES spells (spell_id)
);

CREATE TABLE components (
component_id NUMERIC NOT NULL  PRIMARY KEY AUTOINCREMENT,
component_name TEXT(50) NOT NULL 
);

CREATE TABLE domains (
domain_id NUMERIC NOT NULL  PRIMARY KEY AUTOINCREMENT,
domain_name TEXT(50) NOT NULL 
);

CREATE TABLE range (
range_id NUMERIC NOT NULL  PRIMARY KEY AUTOINCREMENT,
range_name TEXT(50) NOT NULL ,
rage_distance NUMERIC NOT NULL 
);

CREATE TABLE resistance (
resistance_id NUMERIC NOT NULL  PRIMARY KEY AUTOINCREMENT,
resistance_text TEXT(20) NOT NULL 
);

CREATE TABLE spells (
spell_id NUMERIC NOT NULL  PRIMARY KEY AUTOINCREMENT,
spell_name TEXT(50) NOT NULL ,
casting_time TEXT(100) DEFAULT NULL,
range_id NUMERIC NOT NULL  REFERENCES range (range_id),
targets TEXT(200) DEFAULT NULL,
duration TEXT(50) NOT NULL ,
saving_throw TEXT(200) NOT NULL ,
resistance_id NUMERIC NOT NULL  REFERENCES resistance (resistance_id),
effect TEXT(250) NOT NULL ,
text_id NUMERIC NOT NULL  REFERENCES textdesc (text_id)
);

CREATE TABLE class_info (
spell_id NUMERIC NOT NULL  REFERENCES spells (spell_id),
class_id NUMERIC NOT NULL  REFERENCES casters (class_id),
Level INTEGER NOT NULL  DEFAULT 0
);

CREATE TABLE components_info (
component_id NUMERIC NOT NULL  REFERENCES components (component_id),
spell_id NUMERIC NOT NULL  REFERENCES spells (spell_id)
);

CREATE TABLE domain_info (
domain_id NUMERIC NOT NULL  REFERENCES domains (domain_id),
spell_id NUMERIC NOT NULL  REFERENCES spells (spell_id)
);

CREATE TABLE textdesc (
text_id NUMERIC NOT NULL  PRIMARY KEY AUTOINCREMENT,
text TEXT NOT NULL 
);

