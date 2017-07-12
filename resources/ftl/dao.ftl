package ${basePackage}.dao;

import ${basePackage}.bean.${bean};
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;

import java.util.List;

/**
 * TODO 增加注释
 *
 * @author MangoG
 * @version 1.0
 * @since ${date}
 */
@DB(table="${table}")
@Results({
    <#list mFields as item>
        @Result(column = "${item.column}", property = "${item.name}")<#if item_has_next>,</#if>
    </#list>
})
public interface I${bean}Dao {
    String COLUMNS = "<#list mFields as item>${item.column}<#if item_has_next>, </#if></#list>";

    @ReturnGeneratedId
    @SQL("INSERT INTO #table(" + COLUMNS + ") VALUES (<#list mFields as item>:${item.name}<#if item_has_next>, </#if></#list>)")
    long save(${bean} ${bean?uncap_first});

    @SQL("INSERT INTO #table(" + COLUMNS + ") VALUES (<#list mFields as item>:${item.name}<#if item_has_next>, </#if></#list>)")
    void save(List<${bean}> ${bean?uncap_first}s);

    @SQL("UPDATE #table SET <#list mFields as item>${item.column} = :${item.name}<#if item_has_next>, </#if></#list> WHERE n_id = :id")
    void update(${bean} ${bean?uncap_first});

    @SQL("SELECT " + COLUMNS + " FROM #table WHERE n_id = :1 ")
    ${bean} get(Long id);

    @SQL("SELECT " + COLUMNS + " FROM #table")
    List<${bean}> list();

    @SQL("DELETE FROM #table WHERE n_id = :1")
    void del(Long id);
}