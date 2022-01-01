-- Menu SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES ('1', '${classInfo.classComment}', 'generator/${classInfo.className?uncap_first}', NULL, '1', 'config', '6');

-- Button parent menu ID
set @parentId = @@identity;

-- Menu corresponding button SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, 'Check', null, 'generator:${classInfo.className?uncap_first}:list,generator:${classInfo.className?uncap_first}:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, 'Add', null, 'generator:${classInfo.className?uncap_first}:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, 'Revise', null, 'generator:${classInfo.className?uncap_first}:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
SELECT @parentId, 'delete', null, 'generator:${classInfo.className?uncap_first}:delete', '2', null, '6';

