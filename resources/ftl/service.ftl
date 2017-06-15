package ${basePackage}.service;

import ${basePackage}.bean.${bean};

import java.util.List;

public interface I${bean}Service {

    long save(${bean} object);

    void save(List<${bean}> objects);

    void update(${bean} object);

    ${bean} get(Long id);

    List<${bean}> list();

    void del(Long id);
}