DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification`  (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `notifier` bigint(20) NOT NULL,
                                 `receiver` bigint(20) NOT NULL,
                                 `outerId` bigint(20) NULL DEFAULT NULL,
                                 `type` int(11) NOT NULL,
                                 `gmt_create` bigint(20) NOT NULL,
                                 `status` int(11) NULL DEFAULT 0,
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;