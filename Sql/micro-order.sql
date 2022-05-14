-- DATABASE
CREATE DATABASE IF NOT EXISTS `micro-order` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `micro-order`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for micro_order
-- ----------------------------
DROP TABLE IF EXISTS `micro_order`;
CREATE TABLE `micro_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) NOT NULL,
  `commodity_id` int(11) NOT NULL,
  `number` int(11) NOT NULL DEFAULT '0',
  `money` int(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
