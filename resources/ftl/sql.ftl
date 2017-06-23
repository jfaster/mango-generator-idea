DROP TABLE IF EXISTS `${table}`;
CREATE TABLE `${table}` (
<#list mFields as item>
    ${item.sql},
</#list>
    PRIMARY KEY (`n_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;