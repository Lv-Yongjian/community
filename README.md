## 社区论坛  


## 资料  
[Spring 文档](https://spring.io/)  
[Spring Web](https://spring.io/guides/gs/serving-web-content/)  
[es](https://elasticsearch.cn/)  
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)
[Bootstrap](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)  
[Github OAuth](https://developer.github.com/apps/building-github-apps/creating-a-github-app/)

## 工具  
[Git](https://git-scm.com/)  
[Visual Paradigm](https://www.visual-paradigm.com)

## 脚本
```sql
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
```
