-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2018-11-12 20:10:10
-- 服务器版本： 5.6.35
-- PHP 版本： 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `my_shiro02`
--

-- --------------------------------------------------------

--
-- 表的结构 `permission`
--

CREATE TABLE `permission` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `pid` int(11) NOT NULL COMMENT '父级',
  `name` varchar(50) NOT NULL COMMENT '资源名',
  `url` varchar(255) DEFAULT NULL,
  `ismenu` tinyint(1) UNSIGNED NOT NULL COMMENT '是否为菜单',
  `deep` int(11) DEFAULT NULL COMMENT '深度',
  `code` varchar(50) DEFAULT NULL COMMENT '权限代码',
  `status` tinyint(1) NOT NULL COMMENT '状态 1:开启 0:关闭'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

--
-- 转存表中的数据 `permission`
--

INSERT INTO `permission` (`id`, `pid`, `name`, `url`, `ismenu`, `deep`, `code`, `status`) VALUES
(1, 0, '系统管理', NULL, 1, 1, NULL, 1),
(2, 1, '用户管理', 'sys/user/list', 1, 2, 'user:*', 1),
(3, 2, '用户列表', NULL, 0, 3, 'user:list', 1),
(4, 2, '新增用户', NULL, 0, 3, 'user:create', 1),
(5, 2, '编辑用户', NULL, 0, 3, 'user:edit', 1),
(6, 2, '删除用户', NULL, 0, 3, 'user:delete', 1),
(7, 1, '角色管理', 'sys/role/list', 1, 2, 'role:*', 1),
(8, 7, '角色列表', NULL, 0, 3, 'role:list', 1),
(9, 7, '新增角色', NULL, 0, 3, 'role:create', 1),
(10, 7, '编辑角色', NULL, 0, 3, 'role:edit', 1),
(11, 7, '删除角色', NULL, 0, 3, 'role:delete', 1),
(12, 1, '权限管理', 'sys/permission/list', 1, 2, 'permission:*', 1),
(13, 12, '权限列表', NULL, 0, 3, 'permission:list', 1),
(14, 12, '新增权限', NULL, 0, 3, 'permission:create', 1),
(15, 12, '编辑权限', NULL, 0, 3, 'permission:edit', 1),
(16, 12, '删除权限', NULL, 0, 3, 'permission:delete', 1),
(17, 0, '商品管理', NULL, 1, 1, NULL, 1),
(18, 17, '栏目管理', 'sys/cate/list', 1, 2, 'cate:*', 1),
(19, 0, '分类管理', 'sys/category', 1, 1, NULL, 1),
(20, 18, '测试', '', 0, NULL, '', 1);

-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL COMMENT '角色ID',
  `name` varchar(50) NOT NULL COMMENT '角色名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, '超级管理员'),
(2, '管理员'),
(3, '普通'),
(5, '测试'),
(6, '技术人员');

-- --------------------------------------------------------

--
-- 表的结构 `role_permission`
--

CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

--
-- 转存表中的数据 `role_permission`
--

INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`) VALUES
(1, 1, 2),
(2, 1, 7),
(3, 1, 12),
(6, 2, 2),
(7, 3, 3),
(8, 3, 4),
(9, 5, 8),
(10, 6, 7);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `salt` varchar(200) NOT NULL COMMENT '加密密码的盐',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `status` tinyint(1) NOT NULL COMMENT '是否有效 1：有效  0：锁定',
  `reg_time` datetime NOT NULL COMMENT '注册时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `salt`, `nickname`, `status`, `reg_time`) VALUES
(1, 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '我爱天文', 1, '2018-10-28 12:08:04'),
(2, 'yuyi', '56080f0362756cf8d943b583432db47b', '1a5d3a6a21eea5e81626be68a50c08bc', '羽翼', 1, '2018-10-03 03:00:00'),
(3, 'xiaohong', 'd6c01ddc90d7e04441b67a9e197da39c', '744aba86c3126e0d0524eaf6316b6334', '小红', 1, '2018-10-31 08:00:00'),
(5, 'weitian00', '4633b7b3b88206f5a82b30f9dbf2f8e9', 'dbffd0d2ebc5fe68a283937f834e642a', '微甜', 0, '2018-11-12 08:20:54'),
(6, 'dede', '0bbb9f5efd8b342381c4a7e3df799935', 'a6e291b1831603b9da968ce10ce0796b', 'dede', 1, '2018-11-12 20:07:28');

-- --------------------------------------------------------

--
-- 表的结构 `user_role`
--

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

--
-- 转存表中的数据 `user_role`
--

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
(1, 1, 1),
(15, 2, 2);

--
-- 转储表的索引
--

--
-- 表的索引 `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `role_permission`
--
ALTER TABLE `role_permission`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `permission`
--
ALTER TABLE `permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=21;

--
-- 使用表AUTO_INCREMENT `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID', AUTO_INCREMENT=7;

--
-- 使用表AUTO_INCREMENT `role_permission`
--
ALTER TABLE `role_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=11;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID', AUTO_INCREMENT=7;

--
-- 使用表AUTO_INCREMENT `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
