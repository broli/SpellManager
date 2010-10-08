BEGIN TRANSACTION;

INSERT INTO spells (spell_name,time_id,range_id,target,duration_id,save_id,resistance_id,effect,text_id) 
VALUES ('Acid Arrow',1,1,'enemy',3,3,2,'One arrow of acid','A magical arrow of acid springs from your hand and speeds to its target. You must succeed on a ranged touch attack to hit your target. The arrow deals 2d4  points of acid damage with no splash damage. For every three caster levels (to a maximum of 18th), the acid, unless somehow neutralized, lasts for another round, dealing another 2d4 points of damage in that round.');

INSERT INTO spells (spell_name,time_id,range_id,target,duration_id,save_id,resistance_id,effect,text_id) 
VALUES ('Create Water',1,2,'area',2,3,2,'Up to 2 gallons of water/level','This spell generates wholesome, drinkable water, just like clean rain water. Water can be created in an area as small as will actually contain the liquid, or in an area three times as large—possibly creating a downpour or filling many small receptacles.');

--- in here, we should get the ID of the spell, and insert the other tables.
--- this static sql script has the id hardcoded to show the insertion of the 
--- other tables, by guessing the id of the spell is 1 (that is the default
--- id asigned to the first spell entered in all the sqlite3 implementations i tested

--- For acid arrow
INSERT INTO class_info VALUES(1,1,2);
INSERT INTO class_info VALUES(1,2,2);

INSERT INTO descriptor_info VALUES(1,1);

INSERT INTO components_info VALUES(2,1);
INSERT INTO components_info VALUES(1,1);
INSERT INTO components_info VALUES(3,1);
INSERT INTO components_info VALUES(4,1);

INSERT INTO domain_info VALUES(6,1);

INSERT INTO school_info VALUES(1,1,1);

--- for Create water
INSERT INTO class_info VALUES(2,3,0);
INSERT INTO class_info VALUES(2,5,0);
INSERT INTO class_info VALUES(2,4,1);

INSERT INTO descriptor_info VALUES(2,7);

INSERT INTO components_info VALUES(2,2);
INSERT INTO components_info VALUES(1,2);

INSERT INTO domain_info VALUES(6,2);

INSERT INTO school_info VALUES(1,1,2);


COMMIT;
