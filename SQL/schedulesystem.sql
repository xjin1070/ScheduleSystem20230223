/*
 Navicat MySQL Data Transfer

 Source Server         : song
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : schedulesystem

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 05/02/2023 02:52:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `ID` int NOT NULL,
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_Id` int NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE,
  INDEX `shop_Id`(`shop_Id`) USING BTREE,
  CONSTRAINT `emp_ibfk_1` FOREIGN KEY (`shop_Id`) REFERENCES `shop` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (110, '5f93f983524def3dca464469d2cf9f3e', '员工0', '2220@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (111, '698d51a19d8a121ce581499d7b701668', '员工1', '2221@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (112, '7f6ffaa6bb0b408017b62254211691b5', '员工2', '2222@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (113, '73278a4a86960eeb576a8fd4c9ec6997', '员工3', '2223@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (114, '5fd0b37cd7dbbb00f97ba6ce92bf5add', '员工4', '2224@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (115, '2b44928ae11fb9384c4cf38708677c48', '员工5', '2225@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (116, 'c45147dee729311ef5b5c3003946c48f', '员工6', '2226@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (117, 'eb160de1de89d9058fcb0b968dbbbd68', '员工7', '2227@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (118, '5ef059938ba799aaa845e1c2e8a762bd', '员工8', '2228@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (119, '07e1cd7dca89a1678042477183b7ac3f', '员工9', '2229@qq.com', '普通员工', 1);

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `ID` int NOT NULL,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `addresss` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  ` Size` double NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (1, '南京公司', '南京', 33);

SET FOREIGN_KEY_CHECKS = 1;
