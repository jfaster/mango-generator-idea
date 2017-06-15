DROP TABLE IF EXISTS `${table}`;
CREATE TABLE `${table}` (
<#assign keys=results?keys/>
<#list keys as key>
<#if results[key]?contains("n_id")>`n_id` bigint(20) NOT NULL AUTO_INCREMENT<#else>`${results[key]}`<#if results[key]?starts_with("n_")> int(11) DEFAULT NULL<#else> varchar(255) DEFAULT NULL</#if></#if>,
</#list>
PRIMARY KEY (`n_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;