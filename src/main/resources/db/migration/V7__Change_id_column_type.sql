ALTER TABLE `community`.`user`
    MODIFY COLUMN `id` bigint(20) NOT NULL AUTO_INCREMENT FIRST;
ALTER TABLE `community`.`question`
    MODIFY COLUMN `id` bigint(20) NOT NULL AUTO_INCREMENT FIRST;