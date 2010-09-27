--- first the main body with all the 1to1 tables
SELECT a.spell_id, a.spell_name, g.school_name, g.subschool_name, b.time,c.range_name,c.rage_distance, a.target, d.duration, e.save, f.resistance_text, a.effect, a.text_id
	FROM spells AS a NATURAL JOIN castingTime AS b NATURAL JOIN range AS c NATURAL JOIN duration AS d NATURAL JOIN savingThrow AS e NATURAL JOIN resistance AS f NATURAL JOIN (SELECT * FROM school_info NATURAL JOIN schools NATURAL JOIN subschools) as g
WHERE a.spell_id = 1;

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