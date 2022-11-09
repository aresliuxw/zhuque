INSERT INTO demodb.p_menu (menu_code,menu_name,parent_id,menu_lvl,menu_type,menu_url,menu_order,enable) VALUES
	 ('10000','系统管理',0,'1','0','nnn','9','1'),
	 ('10001','用户管理',1,'2','0','/page/user','1','1'),
	 ('10002','角色管理',1,'2','0','/page/role','2','1'),
	 ('10003','菜单管理',1,'2','0',NULL,'3','1'),
	 ('20000','文件管理',0,'1','0',NULL,'1','1'),
	 ('20001','excel管理',5,'2','0',NULL,'1','1');