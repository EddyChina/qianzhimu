use qianzhimu;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : dokit

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 24/02/2022 09:28:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for code_column_config
-- ----------------------------
DROP TABLE IF EXISTS `code_column_config`;
CREATE TABLE `code_column_config` (
                                      `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                      `table_name` varchar(255) DEFAULT NULL,
                                      `column_name` varchar(255) DEFAULT NULL,
                                      `column_type` varchar(255) DEFAULT NULL,
                                      `dict_name` varchar(255) DEFAULT NULL,
                                      `extra` varchar(255) DEFAULT NULL,
                                      `form_show` bit(1) DEFAULT NULL,
                                      `form_type` varchar(255) DEFAULT NULL,
                                      `key_type` varchar(255) DEFAULT NULL,
                                      `list_show` bit(1) DEFAULT NULL,
                                      `not_null` bit(1) DEFAULT NULL,
                                      `query_type` varchar(255) DEFAULT NULL,
                                      `remark` varchar(255) DEFAULT NULL,
                                      `date_annotation` varchar(255) DEFAULT NULL,
                                      PRIMARY KEY (`column_id`) USING BTREE,
                                      KEY `idx_table_name` (`table_name`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='代码生成字段信息存储';

-- ----------------------------
-- Table structure for code_gen_config
-- ----------------------------
DROP TABLE IF EXISTS `code_gen_config`;
CREATE TABLE `code_gen_config` (
                                   `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `table_name` varchar(255) DEFAULT NULL COMMENT '表名',
                                   `author` varchar(255) DEFAULT NULL COMMENT '作者',
                                   `cover` bit(1) DEFAULT NULL COMMENT '是否覆盖',
                                   `module_name` varchar(255) DEFAULT NULL COMMENT '模块名称',
                                   `pack` varchar(255) DEFAULT NULL COMMENT '至于哪个包下',
                                   `path` varchar(255) DEFAULT NULL COMMENT '前端代码生成的路径',
                                   `api_path` varchar(255) DEFAULT NULL COMMENT '前端Api文件路径',
                                   `prefix` varchar(255) DEFAULT NULL COMMENT '表前缀',
                                   `api_alias` varchar(255) DEFAULT NULL COMMENT '接口名称',
                                   PRIMARY KEY (`config_id`) USING BTREE,
                                   KEY `idx_table_name` (`table_name`(100))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='代码生成器配置';

-- ----------------------------
-- Table structure for mnt_app
-- ----------------------------
DROP TABLE IF EXISTS `mnt_app`;
CREATE TABLE `mnt_app` (
                           `app_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                           `name` varchar(255) DEFAULT NULL COMMENT '应用名称',
                           `upload_path` varchar(255) DEFAULT NULL COMMENT '上传目录',
                           `deploy_path` varchar(255) DEFAULT NULL COMMENT '部署路径',
                           `backup_path` varchar(255) DEFAULT NULL COMMENT '备份路径',
                           `port` int(255) DEFAULT NULL COMMENT '应用端口',
                           `start_script` varchar(4000) DEFAULT NULL COMMENT '启动脚本',
                           `deploy_script` varchar(4000) DEFAULT NULL COMMENT '部署脚本',
                           `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                           `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                           `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                           `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                           PRIMARY KEY (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='应用管理';

-- ----------------------------
-- Records of mnt_app
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mnt_database
-- ----------------------------
DROP TABLE IF EXISTS `mnt_database`;
CREATE TABLE `mnt_database` (
                                `db_id` varchar(50) NOT NULL COMMENT 'ID',
                                `name` varchar(255) NOT NULL COMMENT '名称',
                                `jdbc_url` varchar(255) NOT NULL COMMENT 'jdbc连接',
                                `user_name` varchar(255) NOT NULL COMMENT '账号',
                                `pwd` varchar(255) NOT NULL COMMENT '密码',
                                `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                                `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                PRIMARY KEY (`db_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据库管理';

-- ----------------------------
-- Records of mnt_database
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mnt_deploy
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy`;
CREATE TABLE `mnt_deploy` (
                              `deploy_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                              `app_id` bigint(20) DEFAULT NULL COMMENT '应用编号',
                              `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                              `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                              `create_time` datetime DEFAULT NULL,
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              PRIMARY KEY (`deploy_id`) USING BTREE,
                              KEY `FK6sy157pseoxx4fmcqr1vnvvhy` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='部署管理';

-- ----------------------------
-- Records of mnt_deploy
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mnt_deploy_history
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_history`;
CREATE TABLE `mnt_deploy_history` (
                                      `history_id` varchar(50) NOT NULL COMMENT 'ID',
                                      `app_name` varchar(255) NOT NULL COMMENT '应用名称',
                                      `deploy_date` datetime NOT NULL COMMENT '部署日期',
                                      `deploy_user` varchar(50) NOT NULL COMMENT '部署用户',
                                      `ip` varchar(20) NOT NULL COMMENT '服务器IP',
                                      `deploy_id` bigint(20) DEFAULT NULL COMMENT '部署编号',
                                      PRIMARY KEY (`history_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='部署历史管理';

-- ----------------------------
-- Records of mnt_deploy_history
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mnt_deploy_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_server`;
CREATE TABLE `mnt_deploy_server` (
                                     `deploy_id` bigint(20) NOT NULL COMMENT '部署ID',
                                     `server_id` bigint(20) NOT NULL COMMENT '服务ID',
                                     PRIMARY KEY (`deploy_id`,`server_id`) USING BTREE,
                                     KEY `FKeaaha7jew9a02b3bk9ghols53` (`server_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='应用与服务器关联';

-- ----------------------------
-- Records of mnt_deploy_server
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mnt_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_server`;
CREATE TABLE `mnt_server` (
                              `server_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                              `account` varchar(50) DEFAULT NULL COMMENT '账号',
                              `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
                              `name` varchar(100) DEFAULT NULL COMMENT '名称',
                              `password` varchar(100) DEFAULT NULL COMMENT '密码',
                              `port` int(11) DEFAULT NULL COMMENT '端口',
                              `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                              `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              PRIMARY KEY (`server_id`) USING BTREE,
                              KEY `idx_ip` (`ip`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='服务器管理';

-- ----------------------------
-- Records of mnt_server
-- ----------------------------
BEGIN;
INSERT INTO `mnt_server` VALUES (1, 'root', '132.232.129.20', '腾讯云', 'Dqjdda1996.', 8013, NULL, NULL, '2019-11-24 20:35:02', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
                            `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `pid` bigint(20) DEFAULT NULL COMMENT '上级部门',
                            `sub_count` int(5) DEFAULT '0' COMMENT '子部门数目',
                            `name` varchar(255) NOT NULL COMMENT '名称',
                            `dept_sort` int(5) DEFAULT '999' COMMENT '排序',
                            `enabled` bit(1) NOT NULL COMMENT '状态',
                            `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                            `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`dept_id`) USING BTREE,
                            KEY `inx_pid` (`pid`),
                            KEY `inx_enabled` (`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (2, 7, 0, '研发部', 3, b'1', NULL, 'admin', '2019-03-25 09:15:32', '2020-05-10 17:37:58');
INSERT INTO `sys_dept` VALUES (5, 7, 0, '运维部', 4, b'1', NULL, NULL, '2019-03-25 09:20:44', NULL);
INSERT INTO `sys_dept` VALUES (6, 8, 0, '测试部', 6, b'1', NULL, NULL, '2019-03-25 09:52:18', NULL);
INSERT INTO `sys_dept` VALUES (7, NULL, 2, '华南分部', 0, b'1', NULL, 'admin', '2019-03-25 11:04:50', '2020-05-10 19:59:12');
INSERT INTO `sys_dept` VALUES (8, NULL, 2, '华北分部', 1, b'1', NULL, 'admin', '2019-03-25 11:04:53', '2020-05-14 12:54:00');
INSERT INTO `sys_dept` VALUES (15, 8, 0, 'UI部门', 7, b'1', 'admin', 'admin', '2020-05-13 22:56:53', '2020-05-14 12:54:13');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
                            `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `name` varchar(255) NOT NULL COMMENT '字典名称',
                            `description` varchar(255) DEFAULT NULL COMMENT '描述',
                            `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                            `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (1, 'user_status', '用户状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (4, 'dept_status', '部门状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` VALUES (5, 'job_status', '岗位状态', NULL, NULL, '2019-10-27 20:31:36', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
                                   `detail_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                   `dict_id` bigint(11) DEFAULT NULL COMMENT '字典id',
                                   `label` varchar(255) NOT NULL COMMENT '字典标签',
                                   `value` varchar(255) NOT NULL COMMENT '字典值',
                                   `dict_sort` int(5) DEFAULT NULL COMMENT '排序',
                                   `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                                   `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                                   `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                   PRIMARY KEY (`detail_id`) USING BTREE,
                                   KEY `FK5tpkputc6d9nboxojdbgnpmyb` (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据字典详情';

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_detail` VALUES (1, 1, '激活', 'true', 1, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (2, 1, '禁用', 'false', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (3, 4, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (4, 4, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` VALUES (5, 5, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` VALUES (6, 5, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
                           `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                           `name` varchar(255) NOT NULL COMMENT '岗位名称',
                           `enabled` bit(1) NOT NULL COMMENT '岗位状态',
                           `job_sort` int(5) DEFAULT NULL COMMENT '排序',
                           `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                           `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                           `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                           `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                           PRIMARY KEY (`job_id`) USING BTREE,
                           UNIQUE KEY `uniq_name` (`name`),
                           KEY `inx_enabled` (`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='岗位';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_job` VALUES (8, '人事专员', b'1', 3, NULL, NULL, '2019-03-29 14:52:28', NULL);
INSERT INTO `sys_job` VALUES (10, '产品经理', b'1', 4, NULL, NULL, '2019-03-29 14:55:51', NULL);
INSERT INTO `sys_job` VALUES (11, '全栈开发', b'1', 2, NULL, 'admin', '2019-03-31 13:39:30', '2020-05-05 11:33:43');
INSERT INTO `sys_job` VALUES (12, '软件测试', b'1', 5, NULL, 'admin', '2019-03-31 13:39:43', '2020-05-10 19:56:26');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
                           `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                           `description` varchar(255) DEFAULT NULL,
                           `log_type` varchar(255) DEFAULT NULL,
                           `method` varchar(255) DEFAULT NULL,
                           `params` text,
                           `request_ip` varchar(255) DEFAULT NULL,
                           `time` bigint(20) DEFAULT NULL,
                           `username` varchar(255) DEFAULT NULL,
                           `address` varchar(255) DEFAULT NULL,
                           `browser` varchar(255) DEFAULT NULL,
                           `exception_detail` text,
                           `create_time` datetime DEFAULT NULL,
                           PRIMARY KEY (`log_id`) USING BTREE,
                           KEY `log_create_time_index` (`create_time`),
                           KEY `inx_log_type` (`log_type`)
) ENGINE=InnoDB AUTO_INCREMENT=2573 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
                            `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `pid` bigint(20) DEFAULT NULL COMMENT '上级菜单ID',
                            `sub_count` int(5) DEFAULT '0' COMMENT '子菜单数目',
                            `type` int(11) DEFAULT NULL COMMENT '菜单类型',
                            `title` varchar(255) DEFAULT NULL COMMENT '菜单标题',
                            `name` varchar(255) DEFAULT NULL COMMENT '组件名称',
                            `component` varchar(255) DEFAULT NULL COMMENT '组件',
                            `menu_sort` int(5) DEFAULT NULL COMMENT '排序',
                            `icon` varchar(255) DEFAULT NULL COMMENT '图标',
                            `path` varchar(255) DEFAULT NULL COMMENT '链接地址',
                            `i_frame` bit(1) DEFAULT NULL COMMENT '是否外链',
                            `cache` bit(1) DEFAULT b'0' COMMENT '缓存',
                            `hidden` bit(1) DEFAULT b'0' COMMENT '隐藏',
                            `permission` varchar(255) DEFAULT NULL COMMENT '权限',
                            `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                            `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`menu_id`) USING BTREE,
                            UNIQUE KEY `uniq_title` (`title`),
                            UNIQUE KEY `uniq_name` (`name`),
                            KEY `inx_pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, NULL, 7, 0, '系统管理', NULL, NULL, 1, 'system', 'system', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:11:29', NULL);
INSERT INTO `sys_menu` VALUES (2, 1, 3, 1, '用户管理', 'User', 'system/user/index', 2, 'peoples', 'user', b'0', b'0', b'0', 'user:list', NULL, NULL, '2018-12-18 15:14:44', NULL);
INSERT INTO `sys_menu` VALUES (3, 1, 3, 1, '角色管理', 'Role', 'system/role/index', 3, 'role', 'role', b'0', b'0', b'0', 'roles:list', NULL, NULL, '2018-12-18 15:16:07', NULL);
INSERT INTO `sys_menu` VALUES (5, 1, 3, 1, '菜单管理', 'Menu', 'system/menu/index', 5, 'menu', 'menu', b'0', b'0', b'0', 'menu:list', NULL, NULL, '2018-12-18 15:17:28', NULL);
INSERT INTO `sys_menu` VALUES (6, NULL, 5, 0, '系统监控', NULL, NULL, 10, 'monitor', 'monitor', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:17:48', NULL);
INSERT INTO `sys_menu` VALUES (7, 6, 0, 1, '操作日志', 'Log', 'monitor/log/index', 11, 'log', 'logs', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:18:26', NULL);
INSERT INTO `sys_menu` VALUES (9, 6, 0, 1, 'SQL监控', 'Sql', 'monitor/sql/index', 18, 'sqlMonitor', 'druid', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-18 15:19:34', NULL);
INSERT INTO `sys_menu` VALUES (10, NULL, 5, 0, '组件管理', NULL, NULL, 50, 'zujian', 'components', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-19 13:38:16', NULL);
INSERT INTO `sys_menu` VALUES (11, 10, 0, 1, '图标库', 'Icons', 'components/icons/index', 51, 'icon', 'icon', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-19 13:38:49', NULL);
INSERT INTO `sys_menu` VALUES (14, 36, 0, 1, '邮件工具', 'Email', 'tools/email/index', 35, 'email', 'email', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-27 10:13:09', NULL);
INSERT INTO `sys_menu` VALUES (15, 10, 0, 1, '富文本', 'Editor', 'components/Editor', 52, 'fwb', 'tinymce', b'0', b'0', b'0', NULL, NULL, NULL, '2018-12-27 11:58:25', NULL);
INSERT INTO `sys_menu` VALUES (16, 36, 2, 1, '图床管理', 'Pictures', 'tools/picture/index', 33, 'image', 'pictures', b'0', b'0', b'0', 'pictures:list', NULL, NULL, '2018-12-28 09:36:53', NULL);
INSERT INTO `sys_menu` VALUES (18, 36, 3, 1, '存储管理', 'Storage', 'tools/storage/index', 34, 'qiniu', 'storage', b'0', b'0', b'0', 'storage:list', NULL, NULL, '2018-12-31 11:12:15', NULL);
INSERT INTO `sys_menu` VALUES (21, NULL, 2, 0, '多级菜单', NULL, '', 900, 'menu', 'nested', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:22:03', NULL);
INSERT INTO `sys_menu` VALUES (22, 21, 2, 1, '二级菜单1', NULL, 'nested/menu1/index', 999, 'menu', 'menu1', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:23:29', NULL);
INSERT INTO `sys_menu` VALUES (23, 21, 0, 1, '二级菜单2', NULL, 'nested/menu2/index', 999, 'menu', 'menu2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:23:57', NULL);
INSERT INTO `sys_menu` VALUES (24, 22, 0, 1, '三级菜单1', NULL, 'nested/menu1/menu1-1', 999, 'menu', 'menu1-1', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-04 16:24:48', NULL);
INSERT INTO `sys_menu` VALUES (27, 22, 0, 1, '三级菜单2', NULL, 'nested/menu1/menu1-2', 999, 'menu', 'menu1-2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-07 17:27:32', NULL);
INSERT INTO `sys_menu` VALUES (28, 1, 3, 1, '任务调度', 'Timing', 'system/timing/index', 999, 'timing', 'timing', b'0', b'0', b'0', 'timing:list', NULL, NULL, '2019-01-07 20:34:40', NULL);
INSERT INTO `sys_menu` VALUES (30, 36, 0, 1, '代码生成', 'GeneratorIndex', 'generator/index', 32, 'dev', 'generator', b'0', b'1', b'0', NULL, NULL, NULL, '2019-01-11 15:45:55', NULL);
INSERT INTO `sys_menu` VALUES (32, 6, 0, 1, '异常日志', 'ErrorLog', 'monitor/log/errorLog', 12, 'error', 'errorLog', b'0', b'0', b'0', NULL, NULL, NULL, '2019-01-13 13:49:03', NULL);
INSERT INTO `sys_menu` VALUES (33, 10, 0, 1, 'Markdown', 'Markdown', 'components/MarkDown', 53, 'markdown', 'markdown', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-08 13:46:44', NULL);
INSERT INTO `sys_menu` VALUES (34, 10, 0, 1, 'Yaml编辑器', 'YamlEdit', 'components/YamlEdit', 54, 'dev', 'yaml', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-08 15:49:40', NULL);
INSERT INTO `sys_menu` VALUES (35, 1, 3, 1, '部门管理', 'Dept', 'system/dept/index', 6, 'dept', 'dept', b'0', b'0', b'0', 'dept:list', NULL, NULL, '2019-03-25 09:46:00', NULL);
INSERT INTO `sys_menu` VALUES (36, NULL, 8, 0, '系统工具', NULL, '', 30, 'sys-tools', 'sys-tools', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 10:57:35', NULL);
INSERT INTO `sys_menu` VALUES (37, 1, 3, 1, '岗位管理', 'Job', 'system/job/index', 7, 'Steve-Jobs', 'job', b'0', b'0', b'0', 'job:list', NULL, NULL, '2019-03-29 13:51:18', NULL);
INSERT INTO `sys_menu` VALUES (38, 36, 0, 1, '接口文档', 'Swagger', 'tools/swagger/index', 36, 'swagger', 'swagger2', b'0', b'0', b'0', NULL, NULL, NULL, '2019-03-29 19:57:53', NULL);
INSERT INTO `sys_menu` VALUES (39, 1, 3, 1, '字典管理', 'Dict', 'system/dict/index', 8, 'dictionary', 'dict', b'0', b'0', b'0', 'dict:list', NULL, NULL, '2019-04-10 11:49:04', NULL);
INSERT INTO `sys_menu` VALUES (41, 6, 0, 1, '在线用户', 'OnlineUser', 'monitor/online/index', 10, 'Steve-Jobs', 'online', b'0', b'0', b'0', NULL, NULL, NULL, '2019-10-26 22:08:43', NULL);
INSERT INTO `sys_menu` VALUES (44, 2, 0, 2, '用户新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'user:add', NULL, NULL, '2019-10-29 10:59:46', NULL);
INSERT INTO `sys_menu` VALUES (45, 2, 0, 2, '用户编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'user:edit', NULL, NULL, '2019-10-29 11:00:08', NULL);
INSERT INTO `sys_menu` VALUES (46, 2, 0, 2, '用户删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'user:del', NULL, NULL, '2019-10-29 11:00:23', NULL);
INSERT INTO `sys_menu` VALUES (48, 3, 0, 2, '角色创建', NULL, '', 2, '', '', b'0', b'0', b'0', 'roles:add', NULL, NULL, '2019-10-29 12:45:34', NULL);
INSERT INTO `sys_menu` VALUES (49, 3, 0, 2, '角色修改', NULL, '', 3, '', '', b'0', b'0', b'0', 'roles:edit', NULL, NULL, '2019-10-29 12:46:16', NULL);
INSERT INTO `sys_menu` VALUES (50, 3, 0, 2, '角色删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'roles:del', NULL, NULL, '2019-10-29 12:46:51', NULL);
INSERT INTO `sys_menu` VALUES (52, 5, 0, 2, '菜单新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'menu:add', NULL, NULL, '2019-10-29 12:55:07', NULL);
INSERT INTO `sys_menu` VALUES (53, 5, 0, 2, '菜单编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'menu:edit', NULL, NULL, '2019-10-29 12:55:40', NULL);
INSERT INTO `sys_menu` VALUES (54, 5, 0, 2, '菜单删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'menu:del', NULL, NULL, '2019-10-29 12:56:00', NULL);
INSERT INTO `sys_menu` VALUES (56, 35, 0, 2, '部门新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dept:add', NULL, NULL, '2019-10-29 12:57:09', NULL);
INSERT INTO `sys_menu` VALUES (57, 35, 0, 2, '部门编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dept:edit', NULL, NULL, '2019-10-29 12:57:27', NULL);
INSERT INTO `sys_menu` VALUES (58, 35, 0, 2, '部门删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dept:del', NULL, NULL, '2019-10-29 12:57:41', NULL);
INSERT INTO `sys_menu` VALUES (60, 37, 0, 2, '岗位新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'job:add', NULL, NULL, '2019-10-29 12:58:27', NULL);
INSERT INTO `sys_menu` VALUES (61, 37, 0, 2, '岗位编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'job:edit', NULL, NULL, '2019-10-29 12:58:45', NULL);
INSERT INTO `sys_menu` VALUES (62, 37, 0, 2, '岗位删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'job:del', NULL, NULL, '2019-10-29 12:59:04', NULL);
INSERT INTO `sys_menu` VALUES (64, 39, 0, 2, '字典新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'dict:add', NULL, NULL, '2019-10-29 13:00:17', NULL);
INSERT INTO `sys_menu` VALUES (65, 39, 0, 2, '字典编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'dict:edit', NULL, NULL, '2019-10-29 13:00:42', NULL);
INSERT INTO `sys_menu` VALUES (66, 39, 0, 2, '字典删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'dict:del', NULL, NULL, '2019-10-29 13:00:59', NULL);
INSERT INTO `sys_menu` VALUES (70, 16, 0, 2, '图片上传', NULL, '', 2, '', '', b'0', b'0', b'0', 'pictures:add', NULL, NULL, '2019-10-29 13:05:34', NULL);
INSERT INTO `sys_menu` VALUES (71, 16, 0, 2, '图片删除', NULL, '', 3, '', '', b'0', b'0', b'0', 'pictures:del', NULL, NULL, '2019-10-29 13:05:52', NULL);
INSERT INTO `sys_menu` VALUES (73, 28, 0, 2, '任务新增', NULL, '', 2, '', '', b'0', b'0', b'0', 'timing:add', NULL, NULL, '2019-10-29 13:07:28', NULL);
INSERT INTO `sys_menu` VALUES (74, 28, 0, 2, '任务编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'timing:edit', NULL, NULL, '2019-10-29 13:07:41', NULL);
INSERT INTO `sys_menu` VALUES (75, 28, 0, 2, '任务删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'timing:del', NULL, NULL, '2019-10-29 13:07:54', NULL);
INSERT INTO `sys_menu` VALUES (77, 18, 0, 2, '上传文件', NULL, '', 2, '', '', b'0', b'0', b'0', 'storage:add', NULL, NULL, '2019-10-29 13:09:09', NULL);
INSERT INTO `sys_menu` VALUES (78, 18, 0, 2, '文件编辑', NULL, '', 3, '', '', b'0', b'0', b'0', 'storage:edit', NULL, NULL, '2019-10-29 13:09:22', NULL);
INSERT INTO `sys_menu` VALUES (79, 18, 0, 2, '文件删除', NULL, '', 4, '', '', b'0', b'0', b'0', 'storage:del', NULL, NULL, '2019-10-29 13:09:34', NULL);
INSERT INTO `sys_menu` VALUES (80, 6, 0, 1, '服务监控', 'ServerMonitor', 'monitor/server/index', 14, 'codeConsole', 'server', b'0', b'0', b'0', 'monitor:list', NULL, 'admin', '2019-11-07 13:06:39', '2020-05-04 18:20:50');
INSERT INTO `sys_menu` VALUES (82, 36, 0, 1, '生成配置', 'GeneratorConfig', 'generator/config', 33, 'dev', 'generator/config/:tableName', b'0', b'1', b'1', '', NULL, NULL, '2019-11-17 20:08:56', NULL);
INSERT INTO `sys_menu` VALUES (83, 10, 0, 1, '图表库', 'Echarts', 'components/Echarts', 50, 'chart', 'echarts', b'0', b'1', b'0', '', NULL, NULL, '2019-11-21 09:04:32', NULL);
INSERT INTO `sys_menu` VALUES (90, NULL, 5, 1, '运维管理', 'Mnt', '', 20, 'mnt', 'mnt', b'0', b'0', b'0', NULL, NULL, NULL, '2019-11-09 10:31:08', NULL);
INSERT INTO `sys_menu` VALUES (92, 90, 3, 1, '服务器', 'ServerDeploy', 'mnt/server/index', 22, 'server', 'mnt/serverDeploy', b'0', b'0', b'0', 'serverDeploy:list', NULL, NULL, '2019-11-10 10:29:25', NULL);
INSERT INTO `sys_menu` VALUES (93, 90, 3, 1, '应用管理', 'App', 'mnt/app/index', 23, 'app', 'mnt/app', b'0', b'0', b'0', 'app:list', NULL, NULL, '2019-11-10 11:05:16', NULL);
INSERT INTO `sys_menu` VALUES (94, 90, 3, 1, '部署管理', 'Deploy', 'mnt/deploy/index', 24, 'deploy', 'mnt/deploy', b'0', b'0', b'0', 'deploy:list', NULL, NULL, '2019-11-10 15:56:55', NULL);
INSERT INTO `sys_menu` VALUES (97, 90, 1, 1, '部署备份', 'DeployHistory', 'mnt/deployHistory/index', 25, 'backup', 'mnt/deployHistory', b'0', b'0', b'0', 'deployHistory:list', NULL, NULL, '2019-11-10 16:49:44', NULL);
INSERT INTO `sys_menu` VALUES (98, 90, 3, 1, '数据库管理', 'Database', 'mnt/database/index', 26, 'database', 'mnt/database', b'0', b'0', b'0', 'database:list', NULL, NULL, '2019-11-10 20:40:04', NULL);
INSERT INTO `sys_menu` VALUES (102, 97, 0, 2, '删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deployHistory:del', NULL, NULL, '2019-11-17 09:32:48', NULL);
INSERT INTO `sys_menu` VALUES (103, 92, 0, 2, '服务器新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:add', NULL, NULL, '2019-11-17 11:08:33', NULL);
INSERT INTO `sys_menu` VALUES (104, 92, 0, 2, '服务器编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:edit', NULL, NULL, '2019-11-17 11:08:57', NULL);
INSERT INTO `sys_menu` VALUES (105, 92, 0, 2, '服务器删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'serverDeploy:del', NULL, NULL, '2019-11-17 11:09:15', NULL);
INSERT INTO `sys_menu` VALUES (106, 93, 0, 2, '应用新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:add', NULL, NULL, '2019-11-17 11:10:03', NULL);
INSERT INTO `sys_menu` VALUES (107, 93, 0, 2, '应用编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:edit', NULL, NULL, '2019-11-17 11:10:28', NULL);
INSERT INTO `sys_menu` VALUES (108, 93, 0, 2, '应用删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'app:del', NULL, NULL, '2019-11-17 11:10:55', NULL);
INSERT INTO `sys_menu` VALUES (109, 94, 0, 2, '部署新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:add', NULL, NULL, '2019-11-17 11:11:22', NULL);
INSERT INTO `sys_menu` VALUES (110, 94, 0, 2, '部署编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:edit', NULL, NULL, '2019-11-17 11:11:41', NULL);
INSERT INTO `sys_menu` VALUES (111, 94, 0, 2, '部署删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'deploy:del', NULL, NULL, '2019-11-17 11:12:01', NULL);
INSERT INTO `sys_menu` VALUES (112, 98, 0, 2, '数据库新增', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:add', NULL, NULL, '2019-11-17 11:12:43', NULL);
INSERT INTO `sys_menu` VALUES (113, 98, 0, 2, '数据库编辑', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:edit', NULL, NULL, '2019-11-17 11:12:58', NULL);
INSERT INTO `sys_menu` VALUES (114, 98, 0, 2, '数据库删除', NULL, '', 999, '', '', b'0', b'0', b'0', 'database:del', NULL, NULL, '2019-11-17 11:13:14', NULL);
INSERT INTO `sys_menu` VALUES (116, 36, 0, 1, '生成预览', 'Preview', 'generator/preview', 999, 'java', 'generator/preview/:tableName', b'0', b'1', b'1', NULL, NULL, NULL, '2019-11-26 14:54:36', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job` (
                                  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                  `bean_name` varchar(255) DEFAULT NULL COMMENT 'Spring Bean名称',
                                  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
                                  `is_pause` bit(1) DEFAULT NULL COMMENT '状态：1暂停、0启用',
                                  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
                                  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
                                  `params` varchar(255) DEFAULT NULL COMMENT '参数',
                                  `description` varchar(255) DEFAULT NULL COMMENT '备注',
                                  `person_in_charge` varchar(100) DEFAULT NULL COMMENT '负责人',
                                  `email` varchar(100) DEFAULT NULL COMMENT '报警邮箱',
                                  `sub_task` varchar(100) DEFAULT NULL COMMENT '子任务ID',
                                  `pause_after_failure` bit(1) DEFAULT NULL COMMENT '任务失败后是否暂停',
                                  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                                  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                                  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                                  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                  PRIMARY KEY (`job_id`) USING BTREE,
                                  KEY `inx_is_pause` (`is_pause`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务';

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_quartz_job` VALUES (2, 'testTask', '0/5 * * * * ?', b'1', '测试1', 'run1', 'test', '带参测试，多参使用json', '测试', NULL, NULL, NULL, NULL, 'admin', '2019-08-22 14:08:29', '2020-05-05 17:26:19');
INSERT INTO `sys_quartz_job` VALUES (3, 'testTask', '0/5 * * * * ?', b'1', '测试', 'run', '', '不带参测试', 'dokit', '', '2,6', b'1', NULL, 'admin', '2019-09-26 16:44:39', '2020-05-05 20:45:39');
INSERT INTO `sys_quartz_job` VALUES (5, 'Test', '0/5 * * * * ?', b'1', '任务告警测试', 'run', NULL, '测试', 'test', '', NULL, b'1', 'admin', 'admin', '2020-05-05 20:32:41', '2020-05-05 20:36:13');
INSERT INTO `sys_quartz_job` VALUES (6, 'testTask', '0/5 * * * * ?', b'1', '测试3', 'run2', NULL, '测试3', 'dokit', '', NULL, b'1', 'admin', 'admin', '2020-05-05 20:35:41', '2020-05-05 20:36:07');
COMMIT;

-- ----------------------------
-- Table structure for sys_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_log`;
CREATE TABLE `sys_quartz_log` (
                                  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                  `bean_name` varchar(255) DEFAULT NULL,
                                  `create_time` datetime DEFAULT NULL,
                                  `cron_expression` varchar(255) DEFAULT NULL,
                                  `exception_detail` text,
                                  `is_success` bit(1) DEFAULT NULL,
                                  `job_name` varchar(255) DEFAULT NULL,
                                  `method_name` varchar(255) DEFAULT NULL,
                                  `params` varchar(255) DEFAULT NULL,
                                  `time` bigint(20) DEFAULT NULL,
                                  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务日志';

-- ----------------------------
-- Records of sys_quartz_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                            `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `name` varchar(255) NOT NULL COMMENT '名称',
                            `level` int(255) DEFAULT NULL COMMENT '角色级别',
                            `description` varchar(255) DEFAULT NULL COMMENT '描述',
                            `data_scope` varchar(255) DEFAULT NULL COMMENT '数据权限',
                            `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                            `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`role_id`) USING BTREE,
                            UNIQUE KEY `uniq_name` (`name`),
                            KEY `role_name_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', 1, '-', '全部', NULL, 'admin', '2018-11-23 11:04:37', '2020-05-11 18:34:06');
INSERT INTO `sys_role` VALUES (2, '普通用户', 2, '-', '自定义', NULL, 'admin', '2018-11-23 13:09:06', '2020-05-11 18:28:45');
COMMIT;

-- ----------------------------
-- Table structure for sys_roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_depts`;
CREATE TABLE `sys_roles_depts` (
                                   `role_id` bigint(20) NOT NULL,
                                   `dept_id` bigint(20) NOT NULL,
                                   PRIMARY KEY (`role_id`,`dept_id`) USING BTREE,
                                   KEY `FK7qg6itn5ajdoa9h9o78v9ksur` (`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色部门关联';

-- ----------------------------
-- Records of sys_roles_depts
-- ----------------------------
BEGIN;
INSERT INTO `sys_roles_depts` VALUES (2, 7);
COMMIT;

-- ----------------------------
-- Table structure for sys_roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_menus`;
CREATE TABLE `sys_roles_menus` (
                                   `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
                                   `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                   PRIMARY KEY (`menu_id`,`role_id`) USING BTREE,
                                   KEY `FKcngg2qadojhi3a651a5adkvbq` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色菜单关联';

-- ----------------------------
-- Records of sys_roles_menus
-- ----------------------------
BEGIN;
INSERT INTO `sys_roles_menus` VALUES (1, 1);
INSERT INTO `sys_roles_menus` VALUES (2, 1);
INSERT INTO `sys_roles_menus` VALUES (3, 1);
INSERT INTO `sys_roles_menus` VALUES (5, 1);
INSERT INTO `sys_roles_menus` VALUES (6, 1);
INSERT INTO `sys_roles_menus` VALUES (7, 1);
INSERT INTO `sys_roles_menus` VALUES (9, 1);
INSERT INTO `sys_roles_menus` VALUES (10, 1);
INSERT INTO `sys_roles_menus` VALUES (11, 1);
INSERT INTO `sys_roles_menus` VALUES (14, 1);
INSERT INTO `sys_roles_menus` VALUES (15, 1);
INSERT INTO `sys_roles_menus` VALUES (16, 1);
INSERT INTO `sys_roles_menus` VALUES (18, 1);
INSERT INTO `sys_roles_menus` VALUES (19, 1);
INSERT INTO `sys_roles_menus` VALUES (21, 1);
INSERT INTO `sys_roles_menus` VALUES (22, 1);
INSERT INTO `sys_roles_menus` VALUES (23, 1);
INSERT INTO `sys_roles_menus` VALUES (24, 1);
INSERT INTO `sys_roles_menus` VALUES (27, 1);
INSERT INTO `sys_roles_menus` VALUES (28, 1);
INSERT INTO `sys_roles_menus` VALUES (30, 1);
INSERT INTO `sys_roles_menus` VALUES (32, 1);
INSERT INTO `sys_roles_menus` VALUES (33, 1);
INSERT INTO `sys_roles_menus` VALUES (34, 1);
INSERT INTO `sys_roles_menus` VALUES (35, 1);
INSERT INTO `sys_roles_menus` VALUES (36, 1);
INSERT INTO `sys_roles_menus` VALUES (37, 1);
INSERT INTO `sys_roles_menus` VALUES (38, 1);
INSERT INTO `sys_roles_menus` VALUES (39, 1);
INSERT INTO `sys_roles_menus` VALUES (41, 1);
INSERT INTO `sys_roles_menus` VALUES (80, 1);
INSERT INTO `sys_roles_menus` VALUES (82, 1);
INSERT INTO `sys_roles_menus` VALUES (83, 1);
INSERT INTO `sys_roles_menus` VALUES (90, 1);
INSERT INTO `sys_roles_menus` VALUES (92, 1);
INSERT INTO `sys_roles_menus` VALUES (93, 1);
INSERT INTO `sys_roles_menus` VALUES (94, 1);
INSERT INTO `sys_roles_menus` VALUES (97, 1);
INSERT INTO `sys_roles_menus` VALUES (98, 1);
INSERT INTO `sys_roles_menus` VALUES (116, 1);
INSERT INTO `sys_roles_menus` VALUES (1, 2);
INSERT INTO `sys_roles_menus` VALUES (2, 2);
INSERT INTO `sys_roles_menus` VALUES (3, 2);
INSERT INTO `sys_roles_menus` VALUES (5, 2);
INSERT INTO `sys_roles_menus` VALUES (10, 2);
INSERT INTO `sys_roles_menus` VALUES (21, 2);
INSERT INTO `sys_roles_menus` VALUES (36, 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `dept_id` bigint(20) DEFAULT NULL COMMENT '部门名称',
                            `username` varchar(255) DEFAULT NULL COMMENT '用户名',
                            `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
                            `gender` varchar(2) DEFAULT NULL COMMENT '性别',
                            `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
                            `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
                            `avatar_name` varchar(255) DEFAULT NULL COMMENT '头像地址',
                            `avatar_path` varchar(255) DEFAULT NULL COMMENT '头像真实路径',
                            `password` varchar(255) DEFAULT NULL COMMENT '密码',
                            `is_admin` bit(1) DEFAULT b'0' COMMENT '是否为admin账号',
                            `enabled` bigint(20) DEFAULT NULL COMMENT '状态：1启用、0禁用',
                            `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                            `update_by` varchar(255) DEFAULT NULL COMMENT '更新着',
                            `pwd_reset_time` datetime DEFAULT NULL COMMENT '修改密码的时间',
                            `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`user_id`) USING BTREE,
                            UNIQUE KEY `UK_kpubos9gc2cvtkb0thktkbkes` (`email`) USING BTREE,
                            UNIQUE KEY `username` (`username`) USING BTREE,
                            UNIQUE KEY `uniq_username` (`username`),
                            UNIQUE KEY `uniq_email` (`email`),
                            KEY `FK5rwmryny6jthaaxkogownknqp` (`dept_id`) USING BTREE,
                            KEY `FKpq2dhypk2qgt68nauh2by22jb` (`avatar_name`) USING BTREE,
                            KEY `inx_enabled` (`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 2, 'admin', '管理员', '男', '18888888888', '18888888888@qq.com', NULL, NULL, '$2a$10$Egp1/gvFlt7zhlXVfEFw4OfWQCGPw0ClmMcc6FjTnvXNRVf9zdMRa', b'1', 1, NULL, 'admin', '2020-05-03 16:38:31', '2018-08-23 09:11:56', '2020-05-05 10:12:21');
INSERT INTO `sys_user` VALUES (2, 2, 'test', '测试', '男', '18888888888', '18888888889@qq.com', NULL, NULL, '$2a$10$4XcyudOYTSz6fue6KFNMHeUQnCX5jbBQypLEnGk1PmekXt5c95JcK', b'0', 1, 'admin', 'admin', NULL, '2020-05-05 11:15:49', '2020-05-05 11:20:51');
COMMIT;

-- ----------------------------
-- Table structure for sys_users_jobs
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_jobs`;
CREATE TABLE `sys_users_jobs` (
                                  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                  `job_id` bigint(20) NOT NULL COMMENT '岗位ID',
                                  PRIMARY KEY (`user_id`,`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users_jobs
-- ----------------------------
BEGIN;
INSERT INTO `sys_users_jobs` VALUES (1, 11);
INSERT INTO `sys_users_jobs` VALUES (1, 12);
INSERT INTO `sys_users_jobs` VALUES (2, 12);
COMMIT;

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
                                   `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                   `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                   PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
                                   KEY `FKq4eq273l04bpu4efj0jd0jb98` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色关联';

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
BEGIN;
INSERT INTO `sys_users_roles` VALUES (1, 1);
INSERT INTO `sys_users_roles` VALUES (2, 2);
COMMIT;

-- ----------------------------
-- Table structure for tool_email_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_email_config`;
CREATE TABLE `tool_email_config` (
                                     `config_id` bigint(20) NOT NULL COMMENT 'ID',
                                     `from_user` varchar(255) DEFAULT NULL COMMENT '收件人',
                                     `host` varchar(255) DEFAULT NULL COMMENT '邮件服务器SMTP地址',
                                     `pass` varchar(255) DEFAULT NULL COMMENT '密码',
                                     `port` varchar(255) DEFAULT NULL COMMENT '端口',
                                     `user` varchar(255) DEFAULT NULL COMMENT '发件者用户名',
                                     PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='邮箱配置';

-- ----------------------------
-- Table structure for tool_local_storage
-- ----------------------------
DROP TABLE IF EXISTS `tool_local_storage`;
CREATE TABLE `tool_local_storage` (
                                      `storage_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                      `real_name` varchar(255) DEFAULT NULL COMMENT '文件真实的名称',
                                      `name` varchar(255) DEFAULT NULL COMMENT '文件名',
                                      `suffix` varchar(255) DEFAULT NULL COMMENT '后缀',
                                      `path` varchar(255) DEFAULT NULL COMMENT '路径',
                                      `type` varchar(255) DEFAULT NULL COMMENT '类型',
                                      `size` varchar(100) DEFAULT NULL COMMENT '大小',
                                      `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                                      `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                                      `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                                      `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                      PRIMARY KEY (`storage_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='本地存储';

-- ----------------------------
-- Records of tool_local_storage
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tool_picture
-- ----------------------------
DROP TABLE IF EXISTS `tool_picture`;
CREATE TABLE `tool_picture` (
                                `picture_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `filename` varchar(255) DEFAULT NULL COMMENT '图片名称',
                                `md5code` varchar(255) DEFAULT NULL COMMENT '文件的MD5值',
                                `size` varchar(255) DEFAULT NULL COMMENT '图片大小',
                                `url` varchar(255) DEFAULT NULL COMMENT '图片地址',
                                `delete_url` varchar(255) DEFAULT NULL COMMENT '删除的URL',
                                `height` varchar(255) DEFAULT NULL COMMENT '图片高度',
                                `width` varchar(255) DEFAULT NULL COMMENT '图片宽度',
                                `username` varchar(255) DEFAULT NULL COMMENT '用户名称',
                                `create_time` datetime DEFAULT NULL COMMENT '上传日期',
                                PRIMARY KEY (`picture_id`) USING BTREE,
                                UNIQUE KEY `uniq_md5_code` (`md5code`),
                                KEY `inx_url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='Sm.Ms图床';

-- ----------------------------
-- Table structure for tool_qiniu_config
-- ----------------------------
DROP TABLE IF EXISTS `tool_qiniu_config`;
CREATE TABLE `tool_qiniu_config` (
                                     `config_id` bigint(20) NOT NULL COMMENT 'ID',
                                     `access_key` text COMMENT 'accessKey',
                                     `bucket` varchar(255) DEFAULT NULL COMMENT 'Bucket 识别符',
                                     `host` varchar(255) NOT NULL COMMENT '外链域名',
                                     `secret_key` text COMMENT 'secretKey',
                                     `type` varchar(255) DEFAULT NULL COMMENT '空间类型',
                                     `zone` varchar(255) DEFAULT NULL COMMENT '机房',
                                     PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='七牛云配置';

-- ----------------------------
-- Table structure for tool_qiniu_content
-- ----------------------------
DROP TABLE IF EXISTS `tool_qiniu_content`;
CREATE TABLE `tool_qiniu_content` (
                                      `content_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                      `bucket` varchar(255) DEFAULT NULL COMMENT 'Bucket 识别符',
                                      `name` varchar(255) DEFAULT NULL COMMENT '文件名称',
                                      `size` varchar(255) DEFAULT NULL COMMENT '文件大小',
                                      `type` varchar(255) DEFAULT NULL COMMENT '文件类型：私有或公开',
                                      `url` varchar(255) DEFAULT NULL COMMENT '文件url',
                                      `suffix` varchar(255) DEFAULT NULL COMMENT '文件后缀',
                                      `update_time` datetime DEFAULT NULL COMMENT '上传或同步的时间',
                                      PRIMARY KEY (`content_id`) USING BTREE,
                                      UNIQUE KEY `uniq_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='七牛云文件存储';

SET FOREIGN_KEY_CHECKS = 1;