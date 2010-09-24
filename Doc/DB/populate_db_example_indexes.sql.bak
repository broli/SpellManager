BEGIN TRANSACTION;

-- populate index tables
INSERT INTO casters (class_name) VALUES('Sorcerer');
INSERT INTO casters (class_name) VALUES('Wizard');
INSERT INTO casters (class_name) VALUES('Cleric');
INSERT INTO casters (class_name) VALUES('Paladin');
INSERT INTO casters (class_name) VALUES('Druid');

INSERT INTO castingTime (time) VALUES('1 estandard action');
INSERT INTO castingTime (time) VALUES('1 minute');
INSERT INTO castingTime (time) VALUES('1 round');

INSERT INTO components (component_name) VALUES('Somatic');
INSERT INTO components (component_name) VALUES('Verbal');
INSERT INTO components (component_name) VALUES('Focus');
INSERT INTO components (component_name) VALUES('Material');
INSERT INTO components (component_name) VALUES('Divine Focus');
INSERT INTO components (component_name) VALUES('Experience');

INSERT INTO descriptors (descriptor_name) VALUES('acid');
INSERT INTO descriptors (descriptor_name) VALUES('fire');
INSERT INTO descriptors (descriptor_name) VALUES('chaotic');
INSERT INTO descriptors (descriptor_name) VALUES('force');
INSERT INTO descriptors (descriptor_name) VALUES('ligth');
INSERT INTO descriptors (descriptor_name) VALUES('lawfull');

INSERT INTO domains (domain_name) VALUES('Air');
INSERT INTO domains (domain_name) VALUES('Animal');
INSERT INTO domains (domain_name) VALUES('Evil');
INSERT INTO domains (domain_name) VALUES('Fire');
INSERT INTO domains (domain_name) VALUES('Good');

INSERT INTO duration (duration) VALUES('1 round');
INSERT INTO duration (duration) VALUES('Instantaneous');
INSERT INTO duration (duration) VALUES('1 round + 1 round/3 levels');

INSERT INTO range (range_name,rage_distance) VALUES('Long','400 + 40/level');
INSERT INTO range (range_name,rage_distance) VALUES('Short','25 + 5/2 levels');
INSERT INTO range (range_name,rage_distance) VALUES('Medium','100 + 10/level');

INSERT INTO resistance (resistance_text) VALUES('Yes');
INSERT INTO resistance (resistance_text) VALUES('No');
INSERT INTO resistance (resistance_text) VALUES('Yes(See Text)');
INSERT INTO resistance (resistance_text) VALUES('No (See Text)');
INSERT INTO resistance (resistance_text) VALUES('See Text');

INSERT INTO savingThrow (save) VALUES('Reflex half');
INSERT INTO savingThrow (save) VALUES('Fortitude negates');
INSERT INTO savingThrow (save) VALUES('None');
INSERT INTO savingThrow (save) VALUES('See Text');

INSERT INTO schools (school_name) VALUES('Conjuration');
INSERT INTO schools (school_name) VALUES('Abjuration');
INSERT INTO schools (school_name) VALUES('Divination');
INSERT INTO schools (school_name) VALUES('Evocation');
INSERT INTO schools (school_name) VALUES('Necromancy');

INSERT INTO subschools (subschool_name) VALUES('Creation');
INSERT INTO subschools (subschool_name) VALUES('Calling');
INSERT INTO subschools (subschool_name) VALUES('Healing');
INSERT INTO subschools (subschool_name) VALUES('Charm');

commit;
