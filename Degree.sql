BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `Degree` (
	`Degree ID`	INTEGER,
	`Degree`	TEXT
);
INSERT INTO `Degree` VALUES (1,'Associates');
INSERT INTO `Degree` VALUES (2,'Bachelors');
INSERT INTO `Degree` VALUES (3,'Graduate');
COMMIT;
