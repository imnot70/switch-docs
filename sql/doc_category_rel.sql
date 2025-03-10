/*
 Navicat Premium Dump SQL

 Source Server         : Docker-MySQL
 Source Server Type    : MySQL
 Source Server Version : 90100 (9.1.0)
 Source Host           : localhost:3306
 Source Schema         : switch_docs

 Target Server Type    : MySQL
 Target Server Version : 90100 (9.1.0)
 File Encoding         : 65001

 Date: 10/03/2025 08:05:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for doc_category_rel
-- ----------------------------
DROP TABLE IF EXISTS `doc_category_rel`;
CREATE TABLE `doc_category_rel`  (
  `doc_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`doc_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
