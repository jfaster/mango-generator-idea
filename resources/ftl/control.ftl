package ${basePackage}.control;

import ${basePackage}.bean.${bean};
import ${basePackage}.service.I${bean}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/${bean?uncap_first}/")
public class ${bean}Control {

    @Autowired
    private I${bean}Service service;

    @RequestMapping("save")
    public void save(${bean} ${bean?uncap_first}) {
        this.service.save(${bean?uncap_first});
    }

    @RequestMapping("update")
    public void update(${bean} ${bean?uncap_first}) {
        this.service.update(${bean?uncap_first});
    }

    @RequestMapping("get")
    @ResponseBody
    public ${bean} get(Long id) {
        return this.service.get(id);
    }

    @RequestMapping("list")
    @ResponseBody
    public List<${bean}> list() {
        return this.service.list();
    }

    @RequestMapping("del")
    public void del(Long id) {
        this.service.del(id);
    }
}
