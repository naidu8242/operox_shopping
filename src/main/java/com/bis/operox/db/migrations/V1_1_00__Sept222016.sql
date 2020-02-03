DELIMITER $$

DROP PROCEDURE IF EXISTS operox.AlterTableAddColumn 
$$
create procedure operox.AlterTableAddColumn(
	    IN dbName text,
		IN tableName text,
		IN fieldName text,
		IN fieldDef text)
begin
	IF NOT EXISTS (
			SELECT * FROM information_schema.COLUMNS
			WHERE column_name=fieldName
			and table_name=tableName
			and table_schema=dbName
		)
	THEN
		set @ddl=CONCAT('ALTER TABLE ',dbName,'.',tableName,
			' ADD COLUMN ',fieldName,' ',fieldDef);
		prepare stmt from @ddl;
		execute stmt;
	END IF;
end;
$$


DROP PROCEDURE IF EXISTS operox.AlterTableRemoveColumn 
$$
create procedure operox.AlterTableRemoveColumn(
	    IN dbName text,
		IN tableName text,
		IN fieldName text)
begin
	IF EXISTS (
			SELECT * FROM information_schema.COLUMNS
			WHERE column_name=fieldName
			and table_name=tableName
			and table_schema=dbName
		)
	THEN
		set @ddl=CONCAT('ALTER TABLE ',dbName,'.',tableName,
			' DROP COLUMN ',fieldName);
		prepare stmt from @ddl;
		execute stmt;
	END IF;
end;
$$



DROP PROCEDURE IF EXISTS operox.DropColumnIndex $$
CREATE PROCEDURE operox.DropColumnIndex (IN  dbName varchar(64), IN  tableName varchar(64), IN colmnName varchar(64))
BEGIN

    DECLARE IndexColumnCount INT;
    DECLARE SQLStatement VARCHAR(256);

    SELECT COUNT(*) INTO IndexColumnCount
    FROM information_schema.statistics
    WHERE table_schema = dbName
    AND table_name = tableName
    AND index_name = colmnName;

    IF IndexColumnCount > 0 THEN
        SET SQLStatement = CONCAT('ALTER TABLE `',dbName,'`.`',tableName,'` DROP
INDEX `',colmnName,'`');
        SET @SQLStmt = SQLStatement;
        PREPARE s FROM @SQLStmt;
        EXECUTE s;
        DEALLOCATE PREPARE s;
    END IF;

end;
$$

DROP PROCEDURE IF EXISTS operox.AddColumnIndex$$
CREATE PROCEDURE operox.AddColumnIndex(IN  dbName varchar(64), IN  tableName varchar(64), IN colmnName varchar(64))
BEGIN

    SELECT @indexes := COUNT(1)
        FROM INFORMATION_SCHEMA.STATISTICS
        WHERE table_schema = dbName
        AND table_name = tableName
        AND COLUMN_NAME = colmnName;

    IF @indexes = 0 THEN
        SET @sql_cmd := CONCAT('CREATE INDEX `',colmnName,'` ON `',dbName,'`.`',tableName,'` ( `',colmnName,'`)');
        SELECT @sql_cmd;
        PREPARE stmt FROM @sql_cmd;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
    END IF;

end;
$$


DROP PROCEDURE IF EXISTS operox.AddColumnIndexWithUNIQUE$$
CREATE PROCEDURE operox.AddColumnIndexWithUNIQUE(IN  dbName varchar(64), IN  tableName varchar(64), IN colmnName varchar(64))
BEGIN

    SELECT @indexes := COUNT(1)
        FROM INFORMATION_SCHEMA.STATISTICS
        WHERE table_schema = dbName
        AND table_name = tableName
        AND COLUMN_NAME = colmnName;

    IF @indexes = 0 THEN
        SET @sql_cmd := CONCAT('ALTER TABLE `',dbName,'`.`',tableName,'` ADD UNIQUE INDEX `',colmnName,'_UNIQUE`',
            '( `', colmnName, '` ASC)');
        SELECT @sql_cmd;
        PREPARE stmt FROM @sql_cmd;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
    END IF;

end;
$$

DELIMITER ;




