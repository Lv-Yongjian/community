ALTER TABLE `community`.`comment`
    MODIFY COLUMN `commentator` bigint(20) NOT NULL;
ALTER TABLE `community`.`question`
    MODIFY COLUMN `creator` bigint(20) NOT NULL;