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
    public long save(${bean} object) {
        return this.dao.save(object);
    }

    @Override
    public void save(List<${bean}> objects) {
        this.dao.save(objects);
    }

    @Override
    public void update(${bean} object) {
        this.dao.update(object);
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
