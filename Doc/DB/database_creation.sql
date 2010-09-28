CREATE TABLE casters (
class_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
class_name TEXT(50) NOT NULL 
);

CREATE TABLE schools  (
school_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
school_name TEXT(50) NOT NULL 
);

CREATE TABLE subschools (
subschool_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
subschool_name TEXT(50) NOT NULL 
);

CREATE TABLE school_info (
school_id INTEGER NOT NULL  REFERENCES schools  (school_id),
subschool_id INTEGER NOT NULL  REFERENCES subschools (subschool_id),
spell_id INTEGER NOT NULL  REFERENCES spells (spell_id)
);

CREATE TABLE descriptors (
descriptor_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
descriptor_name TEXT(50) NOT NULL 
);

CREATE TABLE descriptor_info (
descriptor_id INTEGER NOT NULL  REFERENCES descriptors (descriptor_id),
spell_id INTEGER NOT NULL  REFERENCES spells (spell_id)
);

CREATE TABLE components (
component_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
component_name TEXT(50) NOT NULL 
);

CREATE TABLE domains (
domain_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
domain_name TEXT(50) NOT NULL 
);

CREATE TABLE range (
range_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
range_name TEXT(50) NOT NULL ,
rage_distance TEXT NOT NULL 
);

CREATE TABLE resistance (
resistance_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
resistance_text TEXT(20) NOT NULL 
);

CREATE TABLE spells (
spell_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
spell_name TEXT(50) NOT NULL ,
time_id INTEGER NOT NULL  DEFAULT NULL REFERENCES castingTime (time_id),
range_id INTEGER NOT NULL  REFERENCES range (range_id),
target TEXT NOT NULL ,
duration_id INTEGER NOT NULL  REFERENCES duration (duration_id),
save_id INTEGER NOT NULL  REFERENCES savingThrow (save_id),
resistance_id INTEGER NOT NULL  REFERENCES resistance (resistance_id),
effect TEXT(250) NOT NULL ,
text_id TEXT NOT NULL 
);

CREATE TABLE class_info (
spell_id INTEGER NOT NULL  REFERENCES spells (spell_id),
class_id INTEGER NOT NULL  REFERENCES casters (class_id),
Level INTEGER NOT NULL  DEFAULT 0
);

CREATE TABLE components_info (
component_id INTEGER NOT NULL  REFERENCES components (component_id),
spell_id INTEGER NOT NULL  REFERENCES spells (spell_id)
);

CREATE TABLE domain_info (
domain_id INTEGER NOT NULL  REFERENCES domains (domain_id),
spell_id INTEGER NOT NULL  REFERENCES spells (spell_id)
);

CREATE TABLE castingTime (
time_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
time TEXT NOT NULL 
);

CREATE TABLE duration (
duration_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
duration TEXT NOT NULL 
);

CREATE TABLE savingThrow (
save_id INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
save TEXT NOT NULL 
);

CREATE VIEW Vschool AS 
SELECT * FROM school_info AS si 
JOIN schools AS sc ON si.school_id = sc.school_id
JOIN subschools AS ssc ON si.subschool_id = ssc.subschool_id

CREATE VIEW Vspells AS 
SELECT * FROM spells AS s
   JOIN range AS ra ON s.range_id = ra.range_id 
   JOIN castingTime AS ct ON s.time_id = ct.time_id 
   JOIN duration AS d ON s.duration_id = d.duration_id
   JOIN savingThrow AS st ON s.save_id = st.save_id
   JOIN resistance AS re ON s.resistance_id = re.resistance_id
   JOIN Vschool as vs ON s.spell_id = vs.spell_id;