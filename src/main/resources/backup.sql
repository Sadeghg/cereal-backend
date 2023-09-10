SET @TS = DATE_FORMAT(NOW(),'_%Y_%m_%d_%H_%i_%s');

SET @FOLDER = 'D:/Projects/Java/backend-cereal/src/main/resources';
SET @PREFIX = 'category';
SET @EXT    = '.csv';

SET @CMD = CONCAT("SELECT * FROM category INTO OUTFILE '",@FOLDER,@PREFIX,@TS,@EXT,
                  "' FIELDS ENCLOSED BY '\"' TERMINATED BY ';' ESCAPED BY '\"'",
                  "  LINES TERMINATED BY '\r\n';");

PREPARE statement FROM @CMD;

EXECUTE statement;
