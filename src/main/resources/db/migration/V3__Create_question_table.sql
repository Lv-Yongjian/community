DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `gmt_create` bigint(20) NULL DEFAULT NULL,
  `gmt_modified` bigint(20) NULL DEFAULT NULL,
  `creator` int(11) NULL DEFAULT NULL,
  `comment_count` int(11) NULL DEFAULT 0,
  `view_count` int(11) NULL DEFAULT 0,
  `like_count` int(11) NULL DEFAULT 0,
  `tag` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
