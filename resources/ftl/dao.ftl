package ${basePackage}.dao;

import ${basePackage}.bean.${bean};
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;

import java.util.List;

@DB(table="${table}")
@Results({
    <#assign keys=results?keys/>
    <#list keys as key>
        @Result(column = "${results[key]}", property = "${key}")<#if key_has_next>,</#if>
    </#list>
})
public interface I${bean}Dao {
    String COLUMNS = "<#list keys as key>${results[key]}<#if key_has_next>, </#if></#list>";

    @ReturnGeneratedId
    @SQL("INSERT INTO #table(" + COLUMNS + ") VALUES (<#list keys as key>:1.${key}<#if key_has_next>, </#if></#list>)")
    long save(${bean} object);

    @SQL("INSERT INTO #table(" + COLUMNS + ") VALUES (<#list keys as key>:1.${key}<#if key_has_next>, </#if></#list>)")
    void save(List<${bean}> objects);

    @SQL("UPDATE #table SET <#list keys as key>${results[key]} = :${key}<#if key_has_next>, </#if></#list> WHERE n_id = :id")
    void update(${bean} object);

    @SQL("SELECT " + COLUMNS + " FROM #table WHERE n_id = :1 ")
    ${bean} get(Long id);

    @SQL("SELECT " + COLUMNS + " FROM #table")
    List<${bean}> list();

    @SQL("DELETE FROM #table WHERE n_id = :1")
    void del(Long id);
}