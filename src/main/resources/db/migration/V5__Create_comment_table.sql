SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `parent_id` bigint(20) NOT NULL COMMENT '父类ID',
                            `type` int(11) NOT NULL COMMENT '父类类型',
                            `commentator` int(11) NULL DEFAULT NULL COMMENT '评论人id',
                            `gmt_create` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
                            `gmt_modified` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
                            `like_count` bigint(20) NULL DEFAULT 0 COMMENT '点赞数',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;