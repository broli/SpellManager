--- first the main body with all the 1to1 tables
SELECT *
	FROM spells NATURAL JOIN castingTime NATURAL JOIN range NATURAL JOIN duration NATURAL JOIN savingThrow NATURAL JOIN resistance NATURAL JOIN (SELECT * FROM school_info NATURAL JOIN schools NATURAL JOIN subschools)
WHERE spells.spell_id = 1;

--- Now, one by one, to select the 1toMany tables

--- First, the descriptors
SELECT * FROM descriptor_info NATURAL JOIN descriptors WHERE spell_id = 1;

--- Caster's class and level
SELECT * FROM class_info NATURAL JOIN casters WHERE spell_id = 1;

--- Components
SELECT * FROM components_info NATURAL JOIN components WHERE spell_id = 1;

--- Components
SELECT * FROM components_info NATURAL JOIN components WHERE spell_id = 1;

--- Domains
SELECT * FROM domain_info NATURAL JOIN domains WHERE spell_id = 1;
