DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `account_id` varchar(100) DEFAULT NULL,
  `token` char(36) DEFAULT NULL,
  `gmt_create` bigint(255) DEFAULT NULL,
  `gmt_modified` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;