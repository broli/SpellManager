BEGIN TRANSACTION;

UPDATE spells 
SET spell_name='update' , 
	time_id=2 ,
	range_id=2 ,
	target='update',
	duration_id=2 ,
	save_id=2,
	resistance_id=2,
	effect='update',
	text_id='update update'
WHERE spell_id = 3;

UPDATE school_info
SET school_id = 2,
	subschool_id = 2
WHERE spell_id = 3;

COMMIT;
