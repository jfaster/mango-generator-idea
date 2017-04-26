package ${basePackage}.dao;

import ${basePackage}.bean.${bean};
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.SQL;

import java.util.List;

@DB(table="${table}")
@Results({
    <#assign keys=results?keys/>
    <#list keys as key>
        @Result(column = "${results[key]}", property = "${key}")<#if key_has_next>,</#if>
    </#list>
})
public interface ${bean}Dao {
    String COLUMNS = "<#list keys as key>${key}<#if key_has_next>,</#if></#list>";

    @SQL("INSERT INTO #table(" + COLUMNS + ") VALUES (<#list keys as key>:1.${key}<#if key_has_next>,</#if></#list>)")
    void save(${bean} object);

    @SQL("UPDATE #table <#list keys as key>SET ${key} = :${key}<#if key_has_next>,</#if></#list> WHERE id = :id")
    void update(${bean} object);

    @SQL("SELECT " + COLUMNS + " FROM #table WHERE id = :1 ")
    ${bean} get(Long id);

    @SQL("SELECT " + COLUMNS + " FROM #table")
    List<${bean}> list();

    @SQL("DELETE FROM #table WHERE id = :1")
    void del(Long id);
}