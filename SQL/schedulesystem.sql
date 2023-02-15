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

 Date: 15/02/2023 12:17:08
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
INSERT INTO `emp` VALUES (111, '7f6ffaa6bb0b408017b62254211691b5', '员工2', '222333112@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (112, '73278a4a86960eeb576a8fd4c9ec6997', '员工3', '222333113@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (113, '5fd0b37cd7dbbb00f97ba6ce92bf5add', '员工4', '222333114@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (114, '2b44928ae11fb9384c4cf38708677c48', '员工5', '222333115@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (115, 'c45147dee729311ef5b5c3003946c48f', '员工6', '222333116@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (116, 'eb160de1de89d9058fcb0b968dbbbd68', '员工7', '222333117@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (117, '5ef059938ba799aaa845e1c2e8a762bd', '员工8', '222333118@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (118, '07e1cd7dca89a1678042477183b7ac3f', '员工9', '222333119@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (120, '5f93f983524def3dca464469d2cf9f3e', '员工0', '22222@qq.com', '普通员工', 1);
INSERT INTO `emp` VALUES (128, '698d51a19d8a121ce581499d7b701668', '员工1', '222333129@qq.com', '普通员工', 3);
INSERT INTO `emp` VALUES (129, '7f6ffaa6bb0b408017b62254211691b5', '员工2', '222333130@qq.com', '普通员工', 3);
INSERT INTO `emp` VALUES (130, '73278a4a86960eeb576a8fd4c9ec6997', '员工3', '222333131@qq.com', '普通员工', 3);
INSERT INTO `emp` VALUES (131, '5fd0b37cd7dbbb00f97ba6ce92bf5add', '员工4', '222333132@qq.com', '普通员工', 3);
INSERT INTO `emp` VALUES (132, '2b44928ae11fb9384c4cf38708677c48', '员工5', '222333133@qq.com', '普通员工', 3);
INSERT INTO `emp` VALUES (133, 'c45147dee729311ef5b5c3003946c48f', '员工6', '222333134@qq.com', '普通员工', 3);
INSERT INTO `emp` VALUES (134, 'eb160de1de89d9058fcb0b968dbbbd68', '员工7', '222333135@qq.com', '普通员工', 3);
INSERT INTO `emp` VALUES (135, '5ef059938ba799aaa845e1c2e8a762bd', '员工8', '222333136@qq.com', '普通员工', 3);
INSERT INTO `emp` VALUES (136, '07e1cd7dca89a1678042477183b7ac3f', '员工9', '222333137@qq.com', '普通员工', 3);
INSERT INTO `emp` VALUES (137, '698d51a19d8a121ce581499d7b701668', '员工1', '222333138@qq.com', '普通员工', 4);
INSERT INTO `emp` VALUES (138, '7f6ffaa6bb0b408017b62254211691b5', '员工2', '222333139@qq.com', '普通员工', 4);
INSERT INTO `emp` VALUES (139, '73278a4a86960eeb576a8fd4c9ec6997', '员工3', '222333140@qq.com', '普通员工', 4);
INSERT INTO `emp` VALUES (140, '5fd0b37cd7dbbb00f97ba6ce92bf5add', '员工4', '222333141@qq.com', '普通员工', 4);
INSERT INTO `emp` VALUES (141, '2b44928ae11fb9384c4cf38708677c48', '员工5', '222333142@qq.com', '普通员工', 4);
INSERT INTO `emp` VALUES (142, 'c45147dee729311ef5b5c3003946c48f', '员工6', '222333143@qq.com', '普通员工', 4);
INSERT INTO `emp` VALUES (143, 'eb160de1de89d9058fcb0b968dbbbd68', '员工7', '222333144@qq.com', '普通员工', 4);
INSERT INTO `emp` VALUES (144, '5ef059938ba799aaa845e1c2e8a762bd', '员工8', '222333145@qq.com', '普通员工', 4);
INSERT INTO `emp` VALUES (145, '07e1cd7dca89a1678042477183b7ac3f', '员工9', '222333146@qq.com', '普通员工', 4);
INSERT INTO `emp` VALUES (146, '698d51a19d8a121ce581499d7b701668', '员工1', '222333147@qq.com', '普通员工', 5);
INSERT INTO `emp` VALUES (147, '7f6ffaa6bb0b408017b62254211691b5', '员工2', '222333148@qq.com', '普通员工', 5);
INSERT INTO `emp` VALUES (148, '73278a4a86960eeb576a8fd4c9ec6997', '员工3', '222333149@qq.com', '普通员工', 5);
INSERT INTO `emp` VALUES (149, '5fd0b37cd7dbbb00f97ba6ce92bf5add', '员工4', '222333150@qq.com', '普通员工', 5);
INSERT INTO `emp` VALUES (150, '2b44928ae11fb9384c4cf38708677c48', '员工5', '222333151@qq.com', '普通员工', 5);
INSERT INTO `emp` VALUES (151, 'c45147dee729311ef5b5c3003946c48f', '员工6', '222333152@qq.com', '普通员工', 5);
INSERT INTO `emp` VALUES (152, 'eb160de1de89d9058fcb0b968dbbbd68', '员工7', '222333153@qq.com', '普通员工', 5);
INSERT INTO `emp` VALUES (153, '5ef059938ba799aaa845e1c2e8a762bd', '员工8', '222333154@qq.com', '普通员工', 5);
INSERT INTO `emp` VALUES (154, '07e1cd7dca89a1678042477183b7ac3f', '员工9', '222333155@qq.com', '普通员工', 5);
INSERT INTO `emp` VALUES (155, '698d51a19d8a121ce581499d7b701668', '员工1', '222333156@qq.com', '普通员工', 6);
INSERT INTO `emp` VALUES (156, '7f6ffaa6bb0b408017b62254211691b5', '员工2', '222333157@qq.com', '普通员工', 6);
INSERT INTO `emp` VALUES (157, '73278a4a86960eeb576a8fd4c9ec6997', '员工3', '222333158@qq.com', '普通员工', 6);
INSERT INTO `emp` VALUES (158, '5fd0b37cd7dbbb00f97ba6ce92bf5add', '员工4', '222333159@qq.com', '普通员工', 6);
INSERT INTO `emp` VALUES (159, '2b44928ae11fb9384c4cf38708677c48', '员工5', '222333160@qq.com', '普通员工', 6);
INSERT INTO `emp` VALUES (160, 'c45147dee729311ef5b5c3003946c48f', '员工6', '222333161@qq.com', '普通员工', 6);
INSERT INTO `emp` VALUES (161, 'eb160de1de89d9058fcb0b968dbbbd68', '员工7', '222333162@qq.com', '普通员工', 6);
INSERT INTO `emp` VALUES (162, '5ef059938ba799aaa845e1c2e8a762bd', '员工8', '222333163@qq.com', '普通员工', 6);
INSERT INTO `emp` VALUES (163, '07e1cd7dca89a1678042477183b7ac3f', '员工9', '222333164@qq.com', '普通员工', 6);
INSERT INTO `emp` VALUES (164, '698d51a19d8a121ce581499d7b701668', '员工1', '222333165@qq.com', '普通员工', 7);
INSERT INTO `emp` VALUES (165, '7f6ffaa6bb0b408017b62254211691b5', '员工2', '222333166@qq.com', '普通员工', 7);
INSERT INTO `emp` VALUES (166, '73278a4a86960eeb576a8fd4c9ec6997', '员工3', '222333167@qq.com', '普通员工', 7);
INSERT INTO `emp` VALUES (167, '5fd0b37cd7dbbb00f97ba6ce92bf5add', '员工4', '222333168@qq.com', '普通员工', 7);
INSERT INTO `emp` VALUES (168, '2b44928ae11fb9384c4cf38708677c48', '员工5', '222333169@qq.com', '普通员工', 7);
INSERT INTO `emp` VALUES (169, 'c45147dee729311ef5b5c3003946c48f', '员工6', '222333170@qq.com', '普通员工', 7);
INSERT INTO `emp` VALUES (170, 'eb160de1de89d9058fcb0b968dbbbd68', '员工7', '222333171@qq.com', '普通员工', 7);
INSERT INTO `emp` VALUES (171, '5ef059938ba799aaa845e1c2e8a762bd', '员工8', '222333172@qq.com', '普通员工', 7);
INSERT INTO `emp` VALUES (172, '07e1cd7dca89a1678042477183b7ac3f', '员工9', '222333173@qq.com', '普通员工', 7);
INSERT INTO `emp` VALUES (173, '698d51a19d8a121ce581499d7b701668', '员工1', '222333174@qq.com', '普通员工', 8);
INSERT INTO `emp` VALUES (174, '7f6ffaa6bb0b408017b62254211691b5', '员工2', '222333175@qq.com', '普通员工', 8);
INSERT INTO `emp` VALUES (175, '73278a4a86960eeb576a8fd4c9ec6997', '员工3', '222333176@qq.com', '普通员工', 8);
INSERT INTO `emp` VALUES (176, '5fd0b37cd7dbbb00f97ba6ce92bf5add', '员工4', '222333177@qq.com', '普通员工', 8);
INSERT INTO `emp` VALUES (177, '2b44928ae11fb9384c4cf38708677c48', '员工5', '222333178@qq.com', '普通员工', 8);
INSERT INTO `emp` VALUES (178, 'c45147dee729311ef5b5c3003946c48f', '员工6', '222333179@qq.com', '普通员工', 8);
INSERT INTO `emp` VALUES (179, 'eb160de1de89d9058fcb0b968dbbbd68', '员工7', '222333180@qq.com', '普通员工', 8);
INSERT INTO `emp` VALUES (180, '5ef059938ba799aaa845e1c2e8a762bd', '员工8', '222333181@qq.com', '普通员工', 8);
INSERT INTO `emp` VALUES (181, '07e1cd7dca89a1678042477183b7ac3f', '员工9', '222333182@qq.com', '普通员工', 8);

-- ----------------------------
-- Table structure for emp_prefer
-- ----------------------------
DROP TABLE IF EXISTS `emp_prefer`;
CREATE TABLE `emp_prefer`  (
  `emp_Id` int NULL DEFAULT NULL,
  `prefer_type` int NULL DEFAULT NULL,
  `prefer_value` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `emp_Id`(`emp_Id`) USING BTREE,
  CONSTRAINT `emp_prefer_ibfk_1` FOREIGN KEY (`emp_Id`) REFERENCES `emp` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp_prefer
-- ----------------------------

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule`  (
  `rule_type` int NULL DEFAULT NULL,
  `shop_Id` int NULL DEFAULT NULL,
  `rule_value` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `shop_Id`(`shop_Id`) USING BTREE,
  CONSTRAINT `rule_ibfk_1` FOREIGN KEY (`shop_Id`) REFERENCES `shop` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule
-- ----------------------------

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `ID` int NOT NULL,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `size` double NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (1, '南京公司测试111', '南京', 33);
INSERT INTO `shop` VALUES (3, '测试数据3', '测试数据3', 50);
INSERT INTO `shop` VALUES (4, '测试数据4', '测试数据4', 50);
INSERT INTO `shop` VALUES (5, '测试数据5', '测试数据5', 50);
INSERT INTO `shop` VALUES (6, '测试数据6', '测试数据6', 50);
INSERT INTO `shop` VALUES (7, '测试数据7', '测试数据7', 50);
INSERT INTO `shop` VALUES (8, '测试数据8', '测试数据8', 50);
INSERT INTO `shop` VALUES (9, '测试数据9', '测试数据9', 50);
INSERT INTO `shop` VALUES (10, '测试数据10', '南京', 33);

SET FOREIGN_KEY_CHECKS = 1;
