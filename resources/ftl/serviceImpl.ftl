package ${basePackage}.service.impl;

import ${basePackage}.dao.I${bean}Dao;
import ${basePackage}.bean.${bean};
import ${basePackage}.service.I${bean}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${bean}ServiceImpl implements I${bean}Service {

    @Autowired
    private I${bean}Dao dao;

    @Override
    public long save(${bean} ${bean?uncap_first}) {
        return this.dao.save(${bean?uncap_first});
    }

    @Override
    public void save(List<${bean}> ${bean?uncap_first}s) {
        this.dao.save(${bean?uncap_first}s);
    }

    @Override
    public void update(${bean} ${bean?uncap_first}) {
        this.dao.update(${bean?uncap_first});
    }

    @Override
    public ${bean} get(Long id) {
        return this.dao.get(id);
    }

    @Override
    public List<${bean}> list() {
        return this.dao.list();
    }

    @Override
    public void del(Long id) {
        this.dao.del(id);
    }
}
