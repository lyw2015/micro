/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : micro-portal

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2022-04-29 17:59:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for micro_user
-- ----------------------------
DROP TABLE IF EXISTS `micro_user`;
CREATE TABLE `micro_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `money` int(11) DEFAULT '0',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of micro_user
-- ----------------------------
INSERT INTO `micro_user` VALUES ('1', 'laiyw', '0', 'admin', '2022-04-26 11:56:47', 'admin', '2022-04-26 12:52:13');
INSERT INTO `micro_user` VALUES ('2', 'test', '10000', 'admin', '2022-04-26 11:57:06', null, null);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AT transaction mode undo table';

-- ----------------------------
-- Records of undo_log
-- ----------------------------
INSERT INTO `undo_log` VALUES ('7043886611364522985', '192.168.47.1:8091:7043886611364519645', 'serializer=kryo&compressorType=NONE', 0x2D01000000, '1', '2022-04-28 15:10:19.005992', '2022-04-28 15:10:19.005992');
