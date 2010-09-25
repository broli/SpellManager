
SELECT a.spell_id, spell_name, b.time,c.range_name,c.rage_distance, target, d.duration, e.save, f.resistance_text, effect, text_id
	FROM spells AS a,castingTime AS b, range AS c, duration AS d, savingThrow AS e, resistance AS f
WHERE a.spell_id = 1;