
-- 菜单配置表
CREATE TABLE IF NOT EXISTS menu_config (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    menuKey VARCHAR(60) COMMENT '菜单键名',
    menuName VARCHAR(40) COMMENT '按钮名称',
    eventType VARCHAR(10) COMMENT '触发事件类型 click,view',
    respType VARCHAR(10) COMMENT '回复信息的类型 1.word 2.image 3.method',
    respInfo VARCHAR(256) COMMENT '返回文字 直接文字或者是 调用方法类名',    
    nodeType TINYINT(1) COMMENT '1.父节点2.子节点',
    parentId INT COMMENT '父节点Id',
    createTime DATETIME COMMENT '创建时间'        
)ENGINE=MYISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- 关键字回复
CREATE TABLE IF NOT EXISTS `keyword_reply` (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    keyword VARCHAR(30) COMMENT '配置关键字',
    replyType VARCHAR(10) COMMENT '回复信息的类型 1.word 2.image 3.method',
    replyContent VARCHAR(256) COMMENT '返回内容 直接文字或者是 调用方法类名',
    materialId INT COMMENT '素材id',
    createTime DATETIME COMMENT '创建时间' 
)

DROP TABLE IF EXISTS `el_building_case`;

CREATE TABLE `el_building_case` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `storeId` INT(11) DEFAULT NULL COMMENT '商家店铺id',
  `title` VARCHAR(30) DEFAULT NULL COMMENT '标题',
  `description` VARCHAR(100) DEFAULT NULL COMMENT '描述',
  `pictureUrl` VARCHAR(1024) DEFAULT NULL COMMENT '图片json格式',
  `status` INT(1) DEFAULT NULL COMMENT '(1.使用 0.删除)',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `el_building_case` */

INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (1,1,'欣欣的书','欣欣的珍藏','[{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250157.jpg\",\"phonePicUrl\":\"upload/phone/20160705/146771543402398.jpg\"},{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250287.jpg\",\"phonePicUrl\":\"upload/original/20160705/146771543250287.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (2,3,'werwer','sdfsdf','[]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (3,3,'dfsdfs','werwer','[{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250157.jpg\",\"phonePicUrl\":\"upload/phone/20160705/146771543402398.jpg\"},{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250287.jpg\",\"phonePicUrl\":\"upload/original/20160705/146771543250287.jpg\"},{\"cutPicUrl\":\"upload/cut/20160706/146778501784785.jpg\",\"originalPicUrl\":\"upload/original/20160706/146778501181167.jpg\",\"phonePicUrl\":\"upload/phone/20160706/146778501776946.jpg\"},{\"cutPicUrl\":\"upload/cut/20160706/146778501787069.jpg\",\"originalPicUrl\":\"upload/original/20160706/146778501181253.jpg\",\"phonePicUrl\":\"upload/original/20160706/146778501181253.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (4,3,'werwer','sdfsdfsdf','[{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250157.jpg\",\"phonePicUrl\":\"upload/phone/20160705/146771543402398.jpg\"},{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250287.jpg\",\"phonePicUrl\":\"upload/original/20160705/146771543250287.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (9,3,'sdfsdf','werwer','[{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250157.jpg\",\"phonePicUrl\":\"upload/phone/20160705/146771543402398.jpg\"},{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250287.jpg\",\"phonePicUrl\":\"upload/original/20160705/146771543250287.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (10,3,'欣欣的火车头','欣欣的玩具火车头','[{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250157.jpg\",\"phonePicUrl\":\"upload/phone/20160705/146771543402398.jpg\"},{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250287.jpg\",\"phonePicUrl\":\"upload/original/20160705/146771543250287.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (11,3,'欣欣的铠甲','铁甲圣君','[{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250157.jpg\",\"phonePicUrl\":\"upload/phone/20160705/146771543402398.jpg\"},{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250287.jpg\",\"phonePicUrl\":\"upload/original/20160705/146771543250287.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (12,3,'xinxin de tu','fsdfsf','[{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250157.jpg\",\"phonePicUrl\":\"upload/phone/20160705/146771543402398.jpg\"},{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250287.jpg\",\"phonePicUrl\":\"upload/original/20160705/146771543250287.jpg\"},{\"cutPicUrl\":\"upload/cut/20160706/146778679353338.jpg\",\"originalPicUrl\":\"upload/original/20160706/146778678838579.jpg\",\"phonePicUrl\":\"upload/phone/20160706/146778679347425.jpg\"},{\"cutPicUrl\":\"upload/cut/20160706/146778679355366.jpg\",\"originalPicUrl\":\"upload/original/20160706/146778678838613.jpg\",\"phonePicUrl\":\"upload/original/20160706/146778678838613.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (13,3,'sdfsdfsdf',NULL,'[{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250157.jpg\",\"phonePicUrl\":\"upload/phone/20160705/146771543402398.jpg\"},{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250287.jpg\",\"phonePicUrl\":\"upload/original/20160705/146771543250287.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (14,3,NULL,'miaoshu','[{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250157.jpg\",\"phonePicUrl\":\"upload/phone/20160705/146771543402398.jpg\"},{\"cutPicUrl\":\"upload/phone/20160704/146761685667617.jpg\",\"originalPicUrl\":\"upload/original/20160705/146771543250287.jpg\",\"phonePicUrl\":\"upload/original/20160705/146771543250287.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (15,6,'anli','mioashiu','[{\"originalPicUrl\":\"upload/original/20160706/146778380884854.jpg\",\"phonePicUrl\":\"upload/phone/20160706/146778381075538.jpg\"},{\"originalPicUrl\":\"upload/original/20160706/14677838088493.jpg\",\"phonePicUrl\":\"upload/original/20160706/14677838088493.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (16,3,'xinxixn ixni a ','xinxi nix nisan ','[]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (17,3,'HEHE','jheje','[{\"cutPicUrl\":\"upload/cut/20160706/146778704873462.jpg\",\"originalPicUrl\":\"upload/original/20160706/146778703992862.jpg\",\"phonePicUrl\":\"upload/phone/20160706/146778704866354.jpg\"},{\"cutPicUrl\":\"upload/cut/20160706/146778704876938.jpg\",\"originalPicUrl\":\"upload/original/20160706/14677870399291.jpg\",\"phonePicUrl\":\"upload/original/20160706/14677870399291.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (18,4,'sdfsdf','swerwerwr','[{\"cutPicUrl\":\"upload/cut/20160706/146778852393997.jpg\",\"originalPicUrl\":\"upload/original/20160706/146778851249787.jpg\",\"phonePicUrl\":\"upload/phone/20160706/146778852390746.jpg\"}]',1);
INSERT  INTO `el_building_case`(`id`,`storeId`,`title`,`description`,`pictureUrl`,`status`) VALUES (19,3,NULL,'sdfsdf','[{\"cutPicUrl\":\"upload/cut/20160706/146779891979867.jpg\",\"originalPicUrl\":\"upload/original/20160706/146779889697657.jpg\",\"phonePicUrl\":\"upload/phone/20160706/146779891971464.jpg\"},{\"cutPicUrl\":\"upload/cut/20160706/146779891982017.jpg\",\"originalPicUrl\":\"upload/original/20160706/146779889697750.jpg\",\"phonePicUrl\":\"upload/original/20160706/146779889697750.jpg\"},{\"cutPicUrl\":\"upload/cut/20160706/146779891987512.jpg\",\"originalPicUrl\":\"upload/original/20160706/146779889954157.jpg\",\"phonePicUrl\":\"upload/phone/20160706/146779891984288.jpg\"}]',1);

/*Table structure for table `el_building_focus` */

DROP TABLE IF EXISTS `el_building_focus`;

CREATE TABLE `el_building_focus` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `memberId` INT(11) DEFAULT NULL COMMENT '用户id',
  `storeId` INT(11) DEFAULT NULL COMMENT '商家店铺id',
  `createTime` DATETIME DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `el_building_focus` */

INSERT  INTO `el_building_focus`(`id`,`memberId`,`storeId`,`createTime`) VALUES (1,1,3,'2016-07-02 19:55:53');
INSERT  INTO `el_building_focus`(`id`,`memberId`,`storeId`,`createTime`) VALUES (8,1,5,'2016-07-08 11:47:18');

/*Table structure for table `el_building_goods` */

DROP TABLE IF EXISTS `el_building_goods`;

CREATE TABLE `el_building_goods` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `storeId` INT(11) DEFAULT NULL COMMENT '商家id',
  `coverPictureUrl` VARCHAR(64) DEFAULT NULL COMMENT '封面图片',
  `goodsName` VARCHAR(64) DEFAULT NULL COMMENT '商品名称',
  `goodsPrice` DECIMAL(10,2) DEFAULT NULL COMMENT '商品价格',
  `unit` VARCHAR(10) DEFAULT NULL COMMENT '单位',
  `putAwayStatus` INT(1) DEFAULT NULL COMMENT '1:上架   0:下架',
  `originalPicUrl` VARCHAR(64) DEFAULT NULL COMMENT '上传原图片路径',
  `phonePicUrl` VARCHAR(300) DEFAULT NULL COMMENT '压缩后图片',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `el_building_goods` */

INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (1,1,'upload/original/20160627/146701327852095.jpg','欣欣的眼镜','200.00',NULL,NULL,NULL,NULL);
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (2,1,'upload/original/20160624/146675476969781.jpg','欣欣的贝雷帽','300.00',NULL,NULL,NULL,NULL);
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (3,1,'upload/original/20160624/146675491910695.jpg','欣欣的眼镜','200.00',NULL,NULL,NULL,NULL);
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (4,1,'upload/original/20160624/146675511262277.jpg','欣欣的眼镜','200.00',NULL,NULL,NULL,NULL);
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (5,1,'upload/original/20160624/146675519192447.jpg','欣欣的眼镜','200.00',NULL,NULL,NULL,NULL);
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (6,1,'upload/original/20160624/146675525603519.jpg','欣欣的眼镜','200.00',NULL,NULL,NULL,NULL);
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (7,2,'upload/original/20160624/146675557384290.jpg','欣欣的贝雷帽','300.00',NULL,NULL,NULL,NULL);
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (8,3,'upload/cut/20160705/146771378356994.jpg','shuangchuantu','23.00',NULL,1,'upload/original/20160705/146771378344910.jpg','upload/phone/20160705/146771378364669.jpg');
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (9,7,'upload/cut/20160705/146771992986240.jpg','fsdfsdfsdf','23.00',NULL,1,'upload/original/20160705/146771992942658.jpg','upload/phone/20160705/146771992990173.jpg');
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (10,8,'upload/cut/20160706/146779736415542.jpg','fsdfsdf','2343.00',NULL,1,'upload/original/20160706/146779736354093.jpg','upload/original/20160706/146779736354093.jpg');
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (11,3,'upload/cut/20160706/146779894540145.jpg','werwer','23.00',NULL,1,'upload/original/20160706/146779894536052.jpg','upload/original/20160706/146779894536052.jpg');
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (12,8,'upload/cut/20160706/146779922797062.jpg','dfgdfg','223.00',NULL,1,'upload/original/20160706/146779922733179.jpg','upload/original/20160706/146779922733179.jpg');
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (13,8,'upload/cut/20160706/146780061323058.jpg','sdsdf','23.00',NULL,1,'upload/original/20160706/146780061263542.jpg','upload/original/20160706/146780061263542.jpg');
INSERT  INTO `el_building_goods`(`id`,`storeId`,`coverPictureUrl`,`goodsName`,`goodsPrice`,`unit`,`putAwayStatus`,`originalPicUrl`,`phonePicUrl`) VALUES (14,4,'upload/cut/20160714/14684669372611.jpg','sdsdfsdf','300.00','m2',NULL,'upload/original/20160714/146846693605066.jpg','upload/original/20160714/146846693605066.jpg');

/*Table structure for table `el_building_store` */

DROP TABLE IF EXISTS `el_building_store`;

CREATE TABLE `el_building_store` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `storeNo` VARCHAR(11) DEFAULT NULL COMMENT '店铺号',
  `title` VARCHAR(45) DEFAULT NULL COMMENT '标题',
  `content` VARCHAR(100) DEFAULT NULL COMMENT '内容',
  `mainBusiness` VARCHAR(45) DEFAULT NULL COMMENT '主营业务',
  `province` VARCHAR(10) DEFAULT NULL COMMENT '所在省',
  `city` VARCHAR(10) DEFAULT NULL COMMENT '所在市',
  `district` VARCHAR(10) DEFAULT NULL COMMENT '所在区',
  `promotion` VARCHAR(60) DEFAULT NULL COMMENT '促销信息',
  `detailAddress` VARCHAR(100) DEFAULT NULL COMMENT '详细地址',
  `contactPhone` VARCHAR(60) DEFAULT NULL COMMENT '电话',
  `businessTime` VARCHAR(60) DEFAULT NULL COMMENT '营业时间',
  `description` VARCHAR(100) DEFAULT NULL COMMENT '描述',
  `buildingTypeId` INT(11) DEFAULT NULL COMMENT '建材类别id',
  `buildingTypeName` VARCHAR(30) DEFAULT NULL COMMENT '建材类别名称',
  `memberId` INT(11) DEFAULT NULL COMMENT '用户Id',
  `coverPictureUrl` VARCHAR(60) DEFAULT NULL COMMENT '店铺封面图片',
  `originalPicUrl` VARCHAR(60) DEFAULT NULL COMMENT '原始图片路径',
  `cityId` INT(11) DEFAULT NULL COMMENT '所在市',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `el_building_store` */

INSERT  INTO `el_building_store`(`id`,`storeNo`,`title`,`content`,`mainBusiness`,`province`,`city`,`district`,`promotion`,`detailAddress`,`contactPhone`,`businessTime`,`description`,`buildingTypeId`,`buildingTypeName`,`memberId`,`coverPictureUrl`,`originalPicUrl`,`cityId`) VALUES (1,'sdfsdfsdf','专业瓷砖',NULL,'欧式瓷砖','河北省','秦皇岛市',NULL,'满1000减100','九星市场九新市场九星市场九新市场九星市场九新市场','021-5555555','朝九晚五','专业瓷砖绝对专业',1,'瓷砖',12,'upload/phone/20160630/146729023049342.jpg','',5);
INSERT  INTO `el_building_store`(`id`,`storeNo`,`title`,`content`,`mainBusiness`,`province`,`city`,`district`,`promotion`,`detailAddress`,`contactPhone`,`businessTime`,`description`,`buildingTypeId`,`buildingTypeName`,`memberId`,`coverPictureUrl`,`originalPicUrl`,`cityId`) VALUES (2,NULL,'sdfsdf',NULL,'xcvxcvxcv',NULL,'上海市','徐汇区','sdfsdfsdf','石龙路','234234234',NULL,'werwer',1,NULL,5,'upload/original/20160624/146673653527553.jpg',NULL,NULL);
INSERT  INTO `el_building_store`(`id`,`storeNo`,`title`,`content`,`mainBusiness`,`province`,`city`,`district`,`promotion`,`detailAddress`,`contactPhone`,`businessTime`,`description`,`buildingTypeId`,`buildingTypeName`,`memberId`,`coverPictureUrl`,`originalPicUrl`,`cityId`) VALUES (3,'','欣欣的小屋',NULL,'各式地板砖','内蒙古自治区','赤峰市',NULL,'满1000减100','九星市场九新市场九星市场九新市场九星市场九新市场','021-132452234','996','欣欣的好无知',2,'地板',NULL,'upload/phone/20160630/146729023049342.jpg',NULL,28);
INSERT  INTO `el_building_store`(`id`,`storeNo`,`title`,`content`,`mainBusiness`,`province`,`city`,`district`,`promotion`,`detailAddress`,`contactPhone`,`businessTime`,`description`,`buildingTypeId`,`buildingTypeName`,`memberId`,`coverPictureUrl`,`originalPicUrl`,`cityId`) VALUES (4,'xjp77777xx','欣欣的店铺',NULL,'各种东西','山西省','晋城市',NULL,'满1000减100','陆家嘴','66666666688','24小时','有很多好东西',1,'瓷砖',NULL,'upload/original/20160629/146718131212036.jpg',NULL,18);
INSERT  INTO `el_building_store`(`id`,`storeNo`,`title`,`content`,`mainBusiness`,`province`,`city`,`district`,`promotion`,`detailAddress`,`contactPhone`,`businessTime`,`description`,`buildingTypeId`,`buildingTypeName`,`memberId`,`coverPictureUrl`,`originalPicUrl`,`cityId`) VALUES (5,'xjp98878hx','肖肖的店铺',NULL,'主要是寿司','山西省','长治市',NULL,'开业全场8.8折','上海南站','88888888','全天候','天南海北啥都有',1,'瓷砖',NULL,'upload/original/20160629/146718155179295.jpg','',17);
INSERT  INTO `el_building_store`(`id`,`storeNo`,`title`,`content`,`mainBusiness`,`province`,`city`,`district`,`promotion`,`detailAddress`,`contactPhone`,`businessTime`,`description`,`buildingTypeId`,`buildingTypeName`,`memberId`,`coverPictureUrl`,`originalPicUrl`,`cityId`) VALUES (6,NULL,'ethan',NULL,'摄影器材',NULL,'上海市','静安区','开业免费拍艺术照','静安寺','999999999999','双休日全天候','各种摄影器材',1,'瓷砖',4,'upload/original/20160629/146718162645457.jpg',NULL,NULL);
INSERT  INTO `el_building_store`(`id`,`storeNo`,`title`,`content`,`mainBusiness`,`province`,`city`,`district`,`promotion`,`detailAddress`,`contactPhone`,`businessTime`,`description`,`buildingTypeId`,`buildingTypeName`,`memberId`,`coverPictureUrl`,`originalPicUrl`,`cityId`) VALUES (7,'xjp97943qp',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8,NULL,NULL,NULL);
INSERT  INTO `el_building_store`(`id`,`storeNo`,`title`,`content`,`mainBusiness`,`province`,`city`,`district`,`promotion`,`detailAddress`,`contactPhone`,`businessTime`,`description`,`buildingTypeId`,`buildingTypeName`,`memberId`,`coverPictureUrl`,`originalPicUrl`,`cityId`) VALUES (8,'xjp91584el',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,202,NULL,NULL,NULL);
INSERT  INTO `el_building_store`(`id`,`storeNo`,`title`,`content`,`mainBusiness`,`province`,`city`,`district`,`promotion`,`detailAddress`,`contactPhone`,`businessTime`,`description`,`buildingTypeId`,`buildingTypeName`,`memberId`,`coverPictureUrl`,`originalPicUrl`,`cityId`) VALUES (9,'xjp92358sj',NULL,NULL,'sdfsdfsdf','河北省','秦皇岛市',NULL,NULL,'sdfsdf胜多负少地方',NULL,NULL,NULL,2,'地板',1,NULL,NULL,5);

/*Table structure for table `el_building_type` */

DROP TABLE IF EXISTS `el_building_type`;

CREATE TABLE `el_building_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `typeName` VARCHAR(20) DEFAULT NULL COMMENT '建材类别名称',
  `status` INT(1) DEFAULT NULL COMMENT '使用标志',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `el_building_type` */

INSERT  INTO `el_building_type`(`id`,`typeName`,`status`) VALUES (1,'瓷砖',1);
INSERT  INTO `el_building_type`(`id`,`typeName`,`status`) VALUES (2,'地板',1);
INSERT  INTO `el_building_type`(`id`,`typeName`,`status`) VALUES (3,'厨卫',1);
INSERT  INTO `el_building_type`(`id`,`typeName`,`status`) VALUES (4,'推荐',1);
INSERT  INTO `el_building_type`(`id`,`typeName`,`status`) VALUES (5,'涂料',1);
INSERT  INTO `el_building_type`(`id`,`typeName`,`status`) VALUES (6,'五金',1);
INSERT  INTO `el_building_type`(`id`,`typeName`,`status`) VALUES (7,'电器',1);
INSERT  INTO `el_building_type`(`id`,`typeName`,`status`) VALUES (8,'家居',1);
INSERT  INTO `el_building_type`(`id`,`typeName`,`status`) VALUES (9,'灯饰',1);


CREATE TABLE el_case_pic
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  caseId INT COMMENT '案例id',
  originalPicUrl VARCHAR(60) COMMENT '原图路径', 
  phonePicUrl VARCHAR(60) COMMENT '手机大图路径',  
  smallPicUrl VARCHAR(60) COMMENT '小图路径',
  ImageStatus TINYINT(4) COMMENT '图片上传状态 1.云 0.本地',
  STATUS TINYINT(4) COMMENT '是否使用 1.使用 0.停用',
  createTime DATETIME COMMENT '创建时间' 
)



/**省份**/

CREATE TABLE `provinces` (
  `id` BIGINT(20) NOT NULL,
  `provincename` VARCHAR(255) DEFAULT NULL,
  `province` VARCHAR(255) DEFAULT NULL,
  `flag` TINYINT(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `provinces` */

INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (1,'上海市','上海市',1);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (2,'北京市','北京市',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (3,'河北省','河北省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (4,'山西省','山西省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (5,'内蒙古自治区','内蒙古自治区',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (6,'辽宁省','辽宁省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (7,'吉林省','吉林省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (8,'黑龙江省','黑龙江省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (9,'天津市','天津市',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (10,'江苏省','江苏省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (11,'浙江省','浙江省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (12,'安徽省','安徽省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (13,'福建省','福建省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (14,'江西省','江西省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (15,'山东省','山东省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (16,'河南省','河南省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (17,'湖北省','湖北省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (18,'湖南省','湖南省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (19,'广东省','广东省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (20,'广西壮族自治区','广西壮族自治区',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (21,'海南省','海南省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (22,'重庆市','重庆市',1);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (23,'四川省','四川省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (24,'贵州省','贵州省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (25,'云南省','云南省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (26,'西藏自治区','西藏自治区',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (27,'陕西省','陕西省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (28,'甘肃省','甘肃省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (29,'青海省','青海省',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (30,'宁夏回族自治区','宁夏回族自治区',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (31,'新疆维吾尔自治区','新疆维吾尔自治区',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (32,'香港特别行政区','香港特别行政区',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (33,'澳门特别行政区','澳门特别行政区',0);
INSERT  INTO `provinces`(`id`,`provincename`,`province`,`flag`) VALUES (34,'台湾省','台湾省',0);



DROP TABLE IF EXISTS `el_cities`;

CREATE TABLE `el_cities` (
  `id` BIGINT(20) NOT NULL,
  `cityname` VARCHAR(255) DEFAULT NULL,
  `pid` BIGINT(20) DEFAULT NULL,
  `zipcode` INT(11) DEFAULT NULL,
  `city` VARCHAR(255) DEFAULT NULL,
  `flag` INT(10) DEFAULT NULL COMMENT '是否为服务城市；0：否；1：是',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `el_cities` */

INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (1,'北京市',2,100000,'北京市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (2,'天津市',9,100000,'天津市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (3,'石家庄市',3,50000,'石家庄市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (4,'唐山市',3,63000,'唐山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (5,'秦皇岛市',3,66000,'秦皇岛市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (6,'邯郸市',3,56000,'邯郸市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (7,'邢台市',3,54000,'邢台市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (8,'保定市',3,71000,'保定市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (9,'张家口市',3,75000,'张家口市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (10,'承德市',3,67000,'承德市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (11,'沧州市',3,61000,'沧州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (12,'廊坊市',3,65000,'廊坊市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (13,'衡水市',3,53000,'衡水市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (14,'太原市',4,30000,'太原市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (15,'大同市',4,37000,'大同市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (16,'阳泉市',4,45000,'阳泉市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (17,'长治市',4,46000,'长治市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (18,'晋城市',4,48000,'晋城市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (19,'朔州市',4,36000,'朔州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (20,'晋中市',4,30600,'晋中市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (21,'运城市',4,44000,'运城市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (22,'忻州市',4,34000,'忻州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (23,'临汾市',4,41000,'临汾市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (24,'吕梁市',4,30500,'吕梁市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (25,'呼和浩特市',5,10000,'呼和浩特市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (26,'包头市',5,14000,'包头市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (27,'乌海市',5,16000,'乌海市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (28,'赤峰市',5,24000,'赤峰市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (29,'通辽市',5,28000,'通辽市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (30,'鄂尔多斯市',5,10300,'鄂尔多斯市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (31,'呼伦贝尔市',5,21000,'呼伦贝尔市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (32,'巴彦淖尔市',5,14400,'巴彦淖尔市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (33,'乌兰察布市',5,11800,'乌兰察布市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (34,'兴安盟',5,137500,'兴安盟',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (35,'锡林郭勒盟',5,11100,'锡林郭勒盟',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (36,'阿拉善盟',5,16000,'阿拉善盟',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (37,'沈阳市',6,110000,'沈阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (38,'大连市',6,116000,'大连市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (39,'鞍山市',6,114000,'鞍山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (40,'抚顺市',6,113000,'抚顺市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (41,'本溪市',6,117000,'本溪市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (42,'丹东市',6,118000,'丹东市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (43,'锦州市',6,121000,'锦州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (44,'营口市',6,115000,'营口市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (45,'阜新市',6,123000,'阜新市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (46,'辽阳市',6,111000,'辽阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (47,'盘锦市',6,124000,'盘锦市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (48,'铁岭市',6,112000,'铁岭市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (49,'朝阳市',6,122000,'朝阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (50,'葫芦岛市',6,125000,'葫芦岛市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (51,'长春市',7,130000,'长春市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (52,'吉林市',7,132000,'吉林市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (53,'四平市',7,136000,'四平市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (54,'辽源市',7,136200,'辽源市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (55,'通化市',7,134000,'通化市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (56,'白山市',7,134300,'白山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (57,'松原市',7,131100,'松原市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (58,'白城市',7,137000,'白城市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (59,'延边朝鲜族自治州',7,133000,'延边朝鲜族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (60,'哈尔滨市',8,150000,'哈尔滨市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (61,'齐齐哈尔市',8,161000,'齐齐哈尔市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (62,'鸡西市',8,158100,'鸡西市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (63,'鹤岗市',8,154100,'鹤岗市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (64,'双鸭山市',8,155100,'双鸭山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (65,'大庆市',8,163000,'大庆市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (66,'伊春市',8,152300,'伊春市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (67,'佳木斯市',8,154000,'佳木斯市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (68,'七台河市',8,154600,'七台河市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (69,'牡丹江市',8,157000,'牡丹江市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (70,'黑河市',8,164300,'黑河市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (71,'绥化市',8,152000,'绥化市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (72,'大兴安岭地区',8,165000,'大兴安岭地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (73,'上海市',1,200000,'上海市',1);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (74,'南京市',10,210000,'南京市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (75,'无锡市',10,214000,'无锡市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (76,'徐州市',10,221000,'徐州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (77,'常州市',10,213000,'常州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (78,'苏州市',10,215000,'苏州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (79,'南通市',10,226000,'南通市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (80,'连云港市',10,222000,'连云港市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (81,'淮安市',10,223200,'淮安市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (82,'盐城市',10,224000,'盐城市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (83,'扬州市',10,225000,'扬州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (84,'镇江市',10,212000,'镇江市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (85,'泰州市',10,225300,'泰州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (86,'宿迁市',10,223800,'宿迁市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (87,'杭州市',11,310000,'杭州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (88,'宁波市',11,315000,'宁波市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (89,'温州市',11,325000,'温州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (90,'嘉兴市',11,314000,'嘉兴市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (91,'湖州市',11,313000,'湖州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (92,'绍兴市',11,312000,'绍兴市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (93,'金华市',11,321000,'金华市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (94,'衢州市',11,324000,'衢州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (95,'舟山市',11,316000,'舟山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (96,'台州市',11,318000,'台州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (97,'丽水市',11,323000,'丽水市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (98,'合肥市',12,230000,'合肥市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (99,'芜湖市',12,241000,'芜湖市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (100,'蚌埠市',12,233000,'蚌埠市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (101,'淮南市',12,232000,'淮南市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (102,'马鞍山市',12,243000,'马鞍山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (103,'淮北市',12,235000,'淮北市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (104,'铜陵市',12,244000,'铜陵市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (105,'安庆市',12,246000,'安庆市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (106,'黄山市',12,242700,'黄山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (107,'滁州市',12,239000,'滁州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (108,'阜阳市',12,236100,'阜阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (109,'宿州市',12,234100,'宿州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (110,'巢湖市',12,238000,'巢湖市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (111,'六安市',12,237000,'六安市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (112,'亳州市',12,236800,'亳州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (113,'池州市',12,247100,'池州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (114,'宣城市',12,366000,'宣城市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (115,'福州市',13,350000,'福州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (116,'厦门市',13,361000,'厦门市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (117,'莆田市',13,351100,'莆田市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (118,'三明市',13,365000,'三明市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (119,'泉州市',13,362000,'泉州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (120,'漳州市',13,363000,'漳州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (121,'南平市',13,353000,'南平市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (122,'龙岩市',13,364000,'龙岩市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (123,'宁德市',13,352100,'宁德市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (124,'南昌市',14,330000,'南昌市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (125,'景德镇市',14,333000,'景德镇市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (126,'萍乡市',14,337000,'萍乡市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (127,'九江市',14,332000,'九江市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (128,'新余市',14,338000,'新余市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (129,'鹰潭市',14,335000,'鹰潭市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (130,'赣州市',14,341000,'赣州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (131,'吉安市',14,343000,'吉安市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (132,'宜春市',14,336000,'宜春市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (133,'抚州市',14,332900,'抚州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (134,'上饶市',14,334000,'上饶市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (135,'济南市',15,250000,'济南市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (136,'青岛市',15,266000,'青岛市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (137,'淄博市',15,255000,'淄博市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (138,'枣庄市',15,277100,'枣庄市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (139,'东营市',15,257000,'东营市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (140,'烟台市',15,264000,'烟台市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (141,'潍坊市',15,261000,'潍坊市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (142,'济宁市',15,272100,'济宁市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (143,'泰安市',15,271000,'泰安市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (144,'威海市',15,265700,'威海市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (145,'日照市',15,276800,'日照市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (146,'莱芜市',15,271100,'莱芜市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (147,'临沂市',15,276000,'临沂市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (148,'德州市',15,253000,'德州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (149,'聊城市',15,252000,'聊城市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (150,'滨州市',15,256600,'滨州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (151,'荷泽市',15,255000,'荷泽市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (152,'郑州市',16,450000,'郑州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (153,'开封市',16,475000,'开封市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (154,'洛阳市',16,471000,'洛阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (155,'平顶山市',16,467000,'平顶山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (156,'安阳市',16,454900,'安阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (157,'鹤壁市',16,456600,'鹤壁市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (158,'新乡市',16,453000,'新乡市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (159,'焦作市',16,454100,'焦作市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (160,'濮阳市',16,457000,'濮阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (161,'许昌市',16,461000,'许昌市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (162,'漯河市',16,462000,'漯河市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (163,'三门峡市',16,472000,'三门峡市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (164,'南阳市',16,473000,'南阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (165,'商丘市',16,476000,'商丘市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (166,'信阳市',16,464000,'信阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (167,'周口市',16,466000,'周口市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (168,'驻马店市',16,463000,'驻马店市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (169,'武汉市',17,430000,'武汉市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (170,'黄石市',17,435000,'黄石市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (171,'十堰市',17,442000,'十堰市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (172,'宜昌市',17,443000,'宜昌市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (173,'襄阳市',17,441000,'襄阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (174,'鄂州市',17,436000,'鄂州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (175,'荆门市',17,448000,'荆门市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (176,'孝感市',17,432100,'孝感市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (177,'荆州市',17,434000,'荆州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (178,'黄冈市',17,438000,'黄冈市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (179,'咸宁市',17,437000,'咸宁市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (180,'随州市',17,441300,'随州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (181,'恩施土家族苗族自治州',17,445000,'恩施土家族苗族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (182,'神农架',17,442400,'神农架',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (183,'长沙市',18,410000,'长沙市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (184,'株洲市',18,412000,'株洲市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (185,'湘潭市',18,411100,'湘潭市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (186,'衡阳市',18,421000,'衡阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (187,'邵阳市',18,422000,'邵阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (188,'岳阳市',18,414000,'岳阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (189,'常德市',18,415000,'常德市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (190,'张家界市',18,427000,'张家界市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (191,'益阳市',18,413000,'益阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (192,'郴州市',18,423000,'郴州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (193,'永州市',18,425000,'永州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (194,'怀化市',18,418000,'怀化市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (195,'娄底市',18,417000,'娄底市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (196,'湘西土家族苗族自治州',18,416000,'湘西土家族苗族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (197,'广州市',19,510000,'广州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (198,'韶关市',19,521000,'韶关市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (199,'深圳市',19,518000,'深圳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (200,'珠海市',19,519000,'珠海市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (201,'汕头市',19,515000,'汕头市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (202,'佛山市',19,528000,'佛山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (203,'江门市',19,529000,'江门市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (204,'湛江市',19,524000,'湛江市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (205,'茂名市',19,525000,'茂名市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (206,'肇庆市',19,526000,'肇庆市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (207,'惠州市',19,516000,'惠州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (208,'梅州市',19,514000,'梅州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (209,'汕尾市',19,516600,'汕尾市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (210,'河源市',19,517000,'河源市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (211,'阳江市',19,529500,'阳江市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (212,'清远市',19,511500,'清远市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (213,'东莞市',19,511700,'东莞市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (214,'中山市',19,528400,'中山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (215,'潮州市',19,515600,'潮州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (216,'揭阳市',19,522000,'揭阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (217,'云浮市',19,527300,'云浮市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (218,'南宁市',20,530000,'南宁市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (219,'柳州市',20,545000,'柳州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (220,'桂林市',20,541000,'桂林市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (221,'梧州市',20,543000,'梧州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (222,'北海市',20,536000,'北海市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (223,'防城港市',20,538000,'防城港市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (224,'钦州市',20,535000,'钦州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (225,'贵港市',20,537100,'贵港市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (226,'玉林市',20,537000,'玉林市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (227,'百色市',20,533000,'百色市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (228,'贺州市',20,542800,'贺州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (229,'河池市',20,547000,'河池市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (230,'来宾市',20,546100,'来宾市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (231,'崇左市',20,532200,'崇左市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (232,'海口市',21,570000,'海口市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (233,'三亚市',21,572000,'三亚市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (234,'重庆市',22,400000,'重庆市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (235,'成都市',23,610000,'成都市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (236,'自贡市',23,643000,'自贡市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (237,'攀枝花市',23,617000,'攀枝花市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (238,'泸州市',23,646100,'泸州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (239,'德阳市',23,618000,'德阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (240,'绵阳市',23,621000,'绵阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (241,'广元市',23,628000,'广元市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (242,'遂宁市',23,629000,'遂宁市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (243,'内江市',23,641000,'内江市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (244,'乐山市',23,614000,'乐山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (245,'南充市',23,637000,'南充市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (246,'眉山市',23,612100,'眉山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (247,'宜宾市',23,644000,'宜宾市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (248,'广安市',23,638000,'广安市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (249,'达州市',23,635000,'达州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (250,'雅安市',23,625000,'雅安市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (251,'巴中市',23,635500,'巴中市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (252,'资阳市',23,641300,'资阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (253,'阿坝藏族羌族自治州',23,624600,'阿坝藏族羌族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (254,'甘孜藏族自治州',23,626000,'甘孜藏族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (255,'凉山彝族自治州',23,615000,'凉山彝族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (256,'贵阳市',24,55000,'贵阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (257,'六盘水市',24,553000,'六盘水市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (258,'遵义市',24,563000,'遵义市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (259,'安顺市',24,561000,'安顺市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (260,'铜仁地区',24,554300,'铜仁地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (261,'黔西南布依族苗族自治州',24,551500,'黔西南布依族苗族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (262,'毕节地区',24,551700,'毕节地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (263,'黔东南苗族侗族自治州',24,551500,'黔东南苗族侗族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (264,'黔南布依族苗族自治州',24,550100,'黔南布依族苗族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (265,'昆明市',25,650000,'昆明市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (266,'曲靖市',25,655000,'曲靖市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (267,'玉溪市',25,653100,'玉溪市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (268,'保山市',25,678000,'保山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (269,'昭通市',25,657000,'昭通市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (270,'丽江市',25,674100,'丽江市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (271,'思茅市',25,665000,'思茅市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (272,'临沧市',25,677000,'临沧市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (273,'楚雄彝族自治州',25,675000,'楚雄彝族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (274,'红河哈尼族彝族自治州',25,654400,'红河哈尼族彝族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (275,'文山壮族苗族自治州',25,663000,'文山壮族苗族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (276,'西双版纳傣族自治州',25,666200,'西双版纳傣族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (277,'大理白族自治州',25,671000,'大理白族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (278,'德宏傣族景颇族自治州',25,678400,'德宏傣族景颇族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (279,'怒江傈僳族自治州',25,671400,'怒江傈僳族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (280,'迪庆藏族自治州',25,674400,'迪庆藏族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (281,'拉萨市',26,850000,'拉萨市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (282,'昌都地区',26,854000,'昌都地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (283,'山南地区',26,856000,'山南地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (284,'日喀则地区',26,857000,'日喀则地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (285,'那曲地区',26,852000,'那曲地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (286,'阿里地区',26,859100,'阿里地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (287,'林芝地区',26,860100,'林芝地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (288,'西安市',27,710000,'西安市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (289,'铜川市',27,727000,'铜川市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (290,'宝鸡市',27,721000,'宝鸡市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (291,'咸阳市',27,712000,'咸阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (292,'渭南市',27,714000,'渭南市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (293,'延安市',27,716000,'延安市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (294,'汉中市',27,723000,'汉中市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (295,'榆林市',27,719000,'榆林市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (296,'安康市',27,725000,'安康市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (297,'商洛市',27,711500,'商洛市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (298,'兰州市',28,730000,'兰州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (299,'嘉峪关市',28,735100,'嘉峪关市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (300,'金昌市',28,737100,'金昌市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (301,'白银市',28,730900,'白银市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (302,'天水市',28,741000,'天水市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (303,'武威市',28,733000,'武威市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (304,'张掖市',28,734000,'张掖市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (305,'平凉市',28,744000,'平凉市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (306,'酒泉市',28,735000,'酒泉市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (307,'庆阳市',28,744500,'庆阳市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (308,'定西市',28,743000,'定西市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (309,'陇南市',28,742100,'陇南市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (310,'临夏回族自治州',28,731100,'临夏回族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (311,'甘南藏族自治州',28,747000,'甘南藏族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (312,'西宁市',29,810000,'西宁市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (313,'海东地区',29,810600,'海东地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (314,'海北藏族自治州',29,810300,'海北藏族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (315,'黄南藏族自治州',29,811300,'黄南藏族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (316,'海南藏族自治州',29,813000,'海南藏族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (317,'果洛藏族自治州',29,814000,'果洛藏族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (318,'玉树藏族自治州',29,815000,'玉树藏族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (319,'海西蒙古族藏族自治州',29,817000,'海西蒙古族藏族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (320,'银川市',30,750000,'银川市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (321,'石嘴山市',30,753000,'石嘴山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (322,'吴忠市',30,751100,'吴忠市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (323,'固原市',30,756000,'固原市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (324,'中卫市',30,751700,'中卫市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (325,'乌鲁木齐市',31,830000,'乌鲁木齐市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (326,'克拉玛依市',31,834000,'克拉玛依市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (327,'吐鲁番地区',31,838000,'吐鲁番地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (328,'哈密地区',31,839000,'哈密地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (329,'昌吉回族自治州',31,831100,'昌吉回族自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (330,'博尔塔拉蒙古自治州',31,833400,'博尔塔拉蒙古自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (331,'巴音郭楞蒙古自治州',31,841000,'巴音郭楞蒙古自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (332,'阿克苏地区',31,843000,'阿克苏地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (333,'克孜勒苏柯尔克孜自治州',31,835600,'克孜勒苏柯尔克孜自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (334,'喀什地区',31,844000,'喀什地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (335,'和田地区',31,848000,'和田地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (336,'伊犁哈萨克自治州',31,833200,'伊犁哈萨克自治州',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (337,'塔城地区',31,834700,'塔城地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (338,'阿勒泰地区',31,836500,'阿勒泰地区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (339,'石河子市',31,832000,'石河子市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (340,'阿拉尔市',31,843300,'阿拉尔市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (341,'图木舒克市',31,843900,'图木舒克市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (342,'五家渠市',31,831300,'五家渠市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (343,'香港特别行政区',32,0,'香港特别行政区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (344,'澳门特别行政区',33,0,'澳门特别行政区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (345,'台湾省',34,0,'台湾省',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (346,'天门市',17,0,'天门市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (347,'仙桃市',17,0,'仙桃市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (348,'开原市',6,0,'开原市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (349,'调兵山市',6,0,'调兵山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (350,'辛集市',3,0,'辛集市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (351,'燕郊市',3,0,'燕郊市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (352,'霸州市',3,0,'霸州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (353,'富拉尔基市',8,0,'富拉尔基市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (354,'延吉市',7,0,'延吉市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (355,'敦化市',7,0,'敦化市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (356,'珲春市',7,0,'珲春市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (357,'锡林浩特市',5,0,'锡林浩特市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (358,'衮州市',15,0,'衮州市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (359,'义乌市',11,0,'义乌市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (360,'舟山市',11,0,'舟山市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (361,'常熟市',10,0,'常熟市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (362,'张家港市',10,0,'张家港市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (363,'璧山区',22,0,'璧山区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (364,'永川区',22,0,'永川区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (365,'万州区',22,0,'万州区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (366,'沙坪坝区',22,0,'沙坪坝区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (367,'南岸区',22,0,'南岸区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (368,'渝北区',22,0,'渝北区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (369,'合川区',22,0,'合川区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (370,'涪陵区',22,0,'涪陵区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (371,'长寿区',22,0,'长寿区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (372,'北碚区',22,0,'北碚区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (373,'渝中区',22,0,'渝中区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (374,'江北区',22,0,'江北区',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (375,'晋江市',13,0,'晋江市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (376,'库尔勒市',31,0,'库尔勒市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (377,'奎屯市',31,0,'奎屯市',0);
INSERT  INTO `el_cities`(`id`,`cityname`,`pid`,`zipcode`,`city`,`flag`) VALUES (378,'黄浦区',1,0,'黄浦区',0);


CREATE TABLE `el_weixin_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(64) DEFAULT NULL COMMENT '呢称',
  `openid` VARCHAR(32) DEFAULT NULL COMMENT '用户的唯一标识 ',
  `phone` VARCHAR(32) DEFAULT NULL COMMENT '电话',
  `headimgurl` VARCHAR(256) DEFAULT NULL COMMENT '头像',
  `sex` INT(11) DEFAULT NULL COMMENT '性别:1男,2女,0未知',
  `language` VARCHAR(64) DEFAULT NULL COMMENT '语言',
  `country` VARCHAR(32) DEFAULT NULL COMMENT '国家',
  `province` VARCHAR(128) DEFAULT NULL COMMENT '省份',
  `city` VARCHAR(128) DEFAULT NULL COMMENT '城市',
  `subscribe` VARCHAR(32) DEFAULT NULL COMMENT '关注状态 1关注，2取消关注，3重新关注',
  `subscription` VARCHAR(128) DEFAULT NULL COMMENT '关注描述',
  `subscribe_time` VARCHAR(64) DEFAULT NULL COMMENT '关注时间',
  `createTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `lat` VARCHAR(32) DEFAULT NULL COMMENT '经度',
  `lng` VARCHAR(32) DEFAULT NULL COMMENT '纬度',
  `status` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8;

/*Data for the table `el_weixin_user` */

INSERT  INTO `el_weixin_user`(`id`,`nickname`,`openid`,`phone`,`headimgurl`,`sex`,`language`,`country`,`province`,`city`,`subscribe`,`subscription`,`subscribe_time`,`createTime`,`updateTime`,`lat`,`lng`,`status`) VALUES (1,'星仔',NULL,NULL,'http://wx.qlogo.cn/mmopen/PL5y7QQHZgJicBb9y5ibeyHIydlrccylPOqYK08icarePso7zyyedGumdHTvv5MjZsLVjibdunBqNhiaMYRO0J5KNmBBicJv9Ol3Fh/0',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT  INTO `el_weixin_user`(`id`,`nickname`,`openid`,`phone`,`headimgurl`,`sex`,`language`,`country`,`province`,`city`,`subscribe`,`subscription`,`subscribe_time`,`createTime`,`updateTime`,`lat`,`lng`,`status`) VALUES (2,'Dk','oqfGLs4vNdEWsqrtnYBNB5joLl6g','13485054422','http://wx.qlogo.cn/mmopen/Q3auHgzwzM5GNDQvS3Y7ODDrxAwtX0WuDx3xYic6xy5ib3mTIyGiaAlibcdwFXDxL9CEh5VTAebfOnV6u5WFC39Niaw/0',1,'zh_CN','中国','江苏','无锡','3',NULL,'1432623571','2015-09-07 13:39:21','2015-09-08 14:50:41',NULL,NULL,1);
INSERT  INTO `el_weixin_user`(`id`,`nickname`,`openid`,`phone`,`headimgurl`,`sex`,`language`,`country`,`province`,`city`,`subscribe`,`subscription`,`subscribe_time`,`createTime`,`updateTime`,`lat`,`lng`,`status`) VALUES (3,'康志煌','oqfGLs80hmLAcSukhuB0F3DwR53Y',NULL,'http://wx.qlogo.cn/mmopen/X71c3MsZXbwmVYLp7ZU9s5Kt99nbopQibrQn63TvXksyzsHVc6oqtwolK58KgXLnLQ5ozVdSfSU88DKucVn8s2Fib2Fp6nUljP/0',1,'zh_CN','中国','上海','','1',NULL,'1441604618','2015-09-07 13:43:39','2015-09-07 13:43:49',NULL,NULL,1);
INSERT  INTO `el_weixin_user`(`id`,`nickname`,`openid`,`phone`,`headimgurl`,`sex`,`language`,`country`,`province`,`city`,`subscribe`,`subscription`,`subscribe_time`,`createTime`,`updateTime`,`lat`,`lng`,`status`) VALUES (4,'大老虎亚士沈刚','oqfGLs1dJGI12dSPTDs2-95PR_-Q',NULL,'http://wx.qlogo.cn/mmopen/Pqu5f5EnEJTdtaF2BEia7ffbDcibzDfY9mC73Qhd24Ric3c41IFBsGLMNeHKy7s2g3vUv14CQF1Qzr21SELibicn7O4KfobWovs2B/0',1,'zh_CN','中国','浙江','杭州','1',NULL,'1441605691','2015-09-07 14:01:31','2015-09-07 14:01:38',NULL,NULL,1);
INSERT  INTO `el_weixin_user`(`id`,`nickname`,`openid`,`phone`,`headimgurl`,`sex`,`language`,`country`,`province`,`city`,`subscribe`,`subscription`,`subscribe_time`,`createTime`,`updateTime`,`lat`,`lng`,`status`) VALUES (5,'','oqfGLsxJiChnqa7N7WB1Rdtr-r7M',NULL,'http://wx.qlogo.cn/mmopen/X71c3MsZXbw7ibHXicKfDMIicnrmm3AHrBdgficyrXLLehkrL42TjZD95eUyeOke28rUVEEVkRTzt2p7dL4CGIONVA/0',1,'zh_CN','中国','江苏','无锡','3',NULL,'1432198105','2015-09-07 14:29:57','2015-11-12 14:15:15',NULL,NULL,1);
INSERT  INTO `el_weixin_user`(`id`,`nickname`,`openid`,`phone`,`headimgurl`,`sex`,`language`,`country`,`province`,`city`,`subscribe`,`subscription`,`subscribe_time`,`createTime`,`updateTime`,`lat`,`lng`,`status`) VALUES (6,'辉辉','oqfGLs8DQquSM-zyCLL_A4xoIaZc',NULL,'http://wx.qlogo.cn/mmopen/X71c3MsZXbwmVYLp7ZU9sxh4nOprAq9U79BibBwlNMCee3lv7WbiaYN0j4KDfmRniaO9oyhEybSMWGh0Tsu65em7mWHDlHoIalh/0',1,'zh_CN','中国','江苏','无锡','1',NULL,'1432602970','2015-09-07 14:53:45','2015-11-17 15:54:33',NULL,NULL,1);
INSERT  INTO `el_weixin_user`(`id`,`nickname`,`openid`,`phone`,`headimgurl`,`sex`,`language`,`country`,`province`,`city`,`subscribe`,`subscription`,`subscribe_time`,`createTime`,`updateTime`,`lat`,`lng`,`status`) VALUES (7,'月半哥瘦了','oqfGLsyzCRCcygdkiNWPNouZHuX4',NULL,'http://wx.qlogo.cn/mmopen/PiajxSqBRaEIGsQbQRGOMpXzpZjFCep89N8yhssoLSiaXvCLayVI0gkCR0yicibh0ZCzaYmptnpjiaDgIHHTeJhCC3w/0',1,'zh_CN','中国','四川','成都','1',NULL,'1441639797','2015-09-07 23:29:57','2015-09-07 23:31:37',NULL,NULL,1);
INSERT  INTO `el_weixin_user`(`id`,`nickname`,`openid`,`phone`,`headimgurl`,`sex`,`language`,`country`,`province`,`city`,`subscribe`,`subscription`,`subscribe_time`,`createTime`,`updateTime`,`lat`,`lng`,`status`) VALUES (8,'徐华春','oqfGLszv3Q7ehuDexpB_Dl5cXo2U',NULL,'http://wx.qlogo.cn/mmopen/GhtcrZPPluFEzebuEMr8onJIaC98gK0ryb1I1iaiabn9ac6yzcBKfam3QEn8YBOmKHsbNuOjeBTpmWVxM5XM36iasfeVsvmeCa8/0',2,'zh_CN','美国','加利福尼亚','洛杉矶','1',NULL,'1432869501','2015-09-08 12:59:17','2015-11-10 15:15:58',NULL,NULL,1);
INSERT  INTO `el_weixin_user`(`id`,`nickname`,`openid`,`phone`,`headimgurl`,`sex`,`language`,`country`,`province`,`city`,`subscribe`,`subscription`,`subscribe_time`,`createTime`,`updateTime`,`lat`,`lng`,`status`) VALUES (9,'祁永康','oqfGLsz34-96r9j_Yf1GdHuPt_Y4',NULL,'http://wx.qlogo.cn/mmopen/X71c3MsZXbwmVYLp7ZU9s6PO736YnQdItfV86QuGbvQxCTYQXtGCLGeqIZLyBklic8CM5KCrPf4HtUx2LvrWHFz1BT9ZwJcQx/0',1,'zh_CN','中国','上海','杨浦','1',NULL,'1441954942','2015-09-11 15:02:22','2015-09-11 15:02:35',NULL,NULL,1);
INSERT  INTO `el_weixin_user`(`id`,`nickname`,`openid`,`phone`,`headimgurl`,`sex`,`language`,`country`,`province`,`city`,`subscribe`,`subscription`,`subscribe_time`,`createTime`,`updateTime`,`lat`,`lng`,`status`) VALUES (10,'崔桂芳','oqfGLs2OIDYaJUsuca8HEwehkBQ0',NULL,'http://wx.qlogo.cn/mmopen/GhtcrZPPluECDwULc9nzosSscshGDDBSXicCTowQKrtPzIPQ2h2EdpcbEPGwrImjJMa7iaIqFQPhFBL0eJoaFBX7roRBWs1nQJ/0',2,'zh_CN','阿尔及利亚','','','3',NULL,'1432777642','2015-09-11 15:18:13','2015-09-14 16:37:27',NULL,NULL,1);


/***建材标签****/
CREATE TABLE el_building_tag(
   id INTEGER NOT NULL AUTO_INCREMENT,   
   tagNo INTEGER COMMENT '标签编号',
   tagName VARCHAR(30) COMMENT '标签名',   
   createTime DATETIME COMMENT '创建时间',
   STATUS TINYINT COMMENT '使用状态'
)

/*****评分表******/
CREATE TABLE el_building_score{
   id INTEGER NOT NULL AUTO_INCREMENT,
   score DECIMAL(10,2) COMMENT '评分',
   userId BIGINT COMMENT '用户id',
   storeId BIGINT COMMENT '店铺id'
   createTime DATETIME COMMENT '创建时间'	
} 

/*******店铺标签关系表********/
CREATE TABLE store_tag(
   id INTEGER NOT NULL AUTO_INCREMENT,
   storeId BIGINT COMMENT '店铺id', 
   tagId BIGINT COMMENT '标签id',
   tagName BIGINT COMMENT '标签名',
   createTime DATETIME COMMENT '创建时间'
)


 


