/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : myshop

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2016-12-23 11:11:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `empNo` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `empName` varchar(50) NOT NULL COMMENT '员工姓名',
  `empPwd` varchar(50) DEFAULT NULL COMMENT '员工密码',
  `sex` varchar(5) NOT NULL COMMENT '性别',
  `age` int(11) NOT NULL COMMENT '年龄',
  `employee` varchar(50) NOT NULL COMMENT '职位',
  `empCard` varchar(50) NOT NULL COMMENT '员工身份证号',
  `telephone` varchar(50) NOT NULL COMMENT '联系电话',
  `address` varchar(200) NOT NULL COMMENT '地址',
  `toDate` datetime DEFAULT NULL COMMENT '入职日期',
  PRIMARY KEY (`empNo`)
) ENGINE=InnoDB AUTO_INCREMENT=22276131 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('22276128', 'admin123', 'admin123', '男', '25', '会计', '441721199878548956', '13849923151', '广州', '2016-12-21 17:10:24');
INSERT INTO `employee` VALUES ('22276130', '李大神', null, '女', '25', '主管', '441721199878548957', '145625145', '广州', '1990-12-24 00:00:00');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `goodNo` varchar(20) DEFAULT NULL COMMENT '产品编号',
  `goodName` varchar(20) DEFAULT NULL COMMENT '产品名称',
  `goodPrice` decimal(10,2) DEFAULT NULL COMMENT '产品价格',
  `goodIntegral` int(11) DEFAULT NULL COMMENT '产品积分',
  `typeId` int(11) DEFAULT NULL COMMENT '产品类型ID',
  `goodNum` int(11) DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', 'ACC', '空调泡沫清洁剂', '75.00', '64', '1', '1');
INSERT INTO `goods` VALUES ('2', 'AF131', '荟新芦荟滋养护色洗发露', '38.00', '32', '2', '2');
INSERT INTO `goods` VALUES ('3', 'AF132', '荟新去头屑洗发露', '34.00', '29', '2', '2');
INSERT INTO `goods` VALUES ('4', 'AF231', '荟新芦荟滋养护色润发乳', '38.00', '32', '1', '2');
INSERT INTO `goods` VALUES ('5', 'AF232', '荟新芦荟保湿修护润发乳', '27.00', '32', '1', '2');
INSERT INTO `goods` VALUES ('6', 'AF301', '荟新动感发胶', '38.00', '32', '1', null);

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `typeId` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES ('1', '日化用品');
INSERT INTO `goodstype` VALUES ('2', '食品');
INSERT INTO `goodstype` VALUES ('3', '服装');
INSERT INTO `goodstype` VALUES ('4', '家用电器');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userNo` varchar(20) DEFAULT NULL COMMENT '会员编号',
  `userName` varchar(20) DEFAULT NULL COMMENT '会员姓名',
  `sex` varchar(5) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `userID` varchar(50) DEFAULT NULL COMMENT '完美卡号',
  `telePhone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(20) DEFAULT '' COMMENT '地址',
  `joinDate` datetime DEFAULT NULL COMMENT '加入日期',
  `userCard` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('2', '2', '胡  歌', '男', '26', '441721199811305545', '145625145', '广州', '1990-12-24 00:00:00', '441721199811305545');
INSERT INTO `member` VALUES ('3', '2', '胡  歌', '男', '25', '441721199811305545', '145625145', '广州', '1990-12-24 00:00:00', '441721199811305545');

-- ----------------------------
-- Table structure for sellinfo
-- ----------------------------
DROP TABLE IF EXISTS `sellinfo`;
CREATE TABLE `sellinfo` (
  `SIid` int(11) NOT NULL AUTO_INCREMENT,
  `Pid` int(11) NOT NULL COMMENT '产品ID',
  `Num` int(11) NOT NULL COMMENT '销售数量',
  `OutDate` datetime NOT NULL COMMENT '销售日期',
  PRIMARY KEY (`SIid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sellinfo
-- ----------------------------
INSERT INTO `sellinfo` VALUES ('1', '1', '2', '2010-12-23 08:10:40');
INSERT INTO `sellinfo` VALUES ('2', '2', '3', '2016-12-14 08:10:50');
INSERT INTO `sellinfo` VALUES ('3', '3', '4', '2016-12-23 08:11:02');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsId` int(11) DEFAULT NULL COMMENT '商品编号',
  `storhead` varchar(20) DEFAULT NULL COMMENT '负责人',
  `storageDate` datetime DEFAULT NULL COMMENT '录入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES ('1', '2', '登记负责人', '2016-12-22 15:13:10');
SET FOREIGN_KEY_CHECKS=1;
