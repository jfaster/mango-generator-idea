package ${basePackage}.service;

import ${basePackage}.bean.${bean};

import java.util.List;

public interface I${bean}Service {

    long save(${bean} ${bean?uncap_first});

    void save(List<${bean}> ${bean?uncap_first}s);

    void update(${bean} ${bean?uncap_first});

    ${bean} get(Long id);

    List<${bean}> list();

    void del(Long id);
}