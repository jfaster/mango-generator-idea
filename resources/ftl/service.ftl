package ${basePackage}.service;

import ${basePackage}.bean.${bean};

import java.util.List;

/**
 * TODO 增加注释
 *
 * @author MangoG
 * @version 1.0
 * @since ${date}
 */
public interface I${bean}Service {

    /**
     * 保存${bean}
     * @param ${bean?uncap_first}
     * @return 保存成功后的主键ID
     */
    long save(${bean} ${bean?uncap_first});

    /**
     * 批量保存${bean}
     * @param ${bean?uncap_first}s
     */
    void save(List<${bean}> ${bean?uncap_first}s);

    /**
     * 修改${bean}
     * @param ${bean?uncap_first}
     */
    void update(${bean} ${bean?uncap_first});

    /**
     * 根据ID获取${bean}
     * @param id
     * @return
     */
    ${bean} get(Long id);

    /**
     * 获取${bean}列表
     * @return
     */
    List<${bean}> list();

    /**
     * 根据ID删除${bean}
     * @param id 要删除的${bean}主键ID
     */
    void del(Long id);
}