package com.hsun.mango;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiType;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hsun
 * @version 1.0
 * @since 2017/4/26 14:30
 */
public class MangoComponent implements ApplicationComponent {
    public MangoComponent() {
    }

    @Override
    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "com.hsun.mango.MangoComponent";
    }

    public void g(String bean, String basePackage, String path, PsiField[] fields) {
        Map<String, Object> map = new HashMap();
        map.put("bean", bean);
        map.put("table", "t_" + bean.toLowerCase());
        map.put("basePackage", basePackage);
        Map<String, String> results = new HashMap();
        for (PsiField psiField : fields) {
            String cname = psiField.getName();
            PsiType type = psiField.getType();
            results.put(cname, p(type.getCanonicalText(), cname));
        }
        map.put("results", results);
        SourceGenerator.generate("dao.ftl", map, path + "/dao/" + bean + "Dao.java");
        SourceGenerator.generate("service.ftl", map, path + "/service/I" + bean + "Service.java");
        SourceGenerator.generate("serviceImpl.ftl", map, path + "/service/impl/" + bean + "ServiceImpl.java");
        SourceGenerator.generate("control.ftl", map, path + "/control/" + bean + "Control.java");
        SourceGenerator.generate("sql.ftl", map, path + "/dao/" + bean + "-craete.sql");
    }


    private static String p(String type, String name) {
        switch (type) {
            case "java.lang.Long":
            case "long":
                return "n_" + c2_(name);
            case "java.lang.Integer":
            case "int":
            case "java.lang.Double":
            case "double":
            case "java.lang.Float":
            case "float":
            case "java.lang.Short":
            case "short":
            case "java.lang.Boolean":
            case "boolean":
                return "n_" + c2_(name);
            case "java.lang.String":
                return "c_" + c2_(name);
            default:
                return "c_" + c2_(name);
        }
    }

    private static String c2_(String str){
        if (StringUtils.isEmpty(str)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++ ) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
