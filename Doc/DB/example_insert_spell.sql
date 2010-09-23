BEGIN TRANSACTION;

INSERT INTO spells (spell_name,time_id,range_id,target,duration_id,save_id,resistance_id,effect,text_id) 
VALUES ('Acid Arrow',1,1,'enemy',3,3,2,"One arrow of acid","A magical arrow of acid springs from your hand and speeds to its target. You must succeed on a ranged touch attack to hit your target. The arrow deals 2d4  points of acid damage with no splash damage. For every three caster levels (to a maximum of 18th), the acid, unless somehow neutralized, lasts for another round, dealing another 2d4 points of damage in that round.");


INSERT INTO spells (spell_name,time_id,range_id,target,duration_id,save_id,resistance_id,effect,text_id) 
VALUES ('Create Water',1,2,'area',2,3,2,'Up to 2 gallons of water/level','This spell generates wholesome, drinkable water, just like clean rain water. Water can be created in an area as small as will actually contain the liquid, or in an area three times as large—possibly creating a downpour or filling many small receptacles.');

commit;
