package com.hsun.mango;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiType;
import com.intellij.psi.javadoc.PsiDocComment;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public void g(String bean, String basePackage, String path, PsiField[] fields) throws IOException, TemplateException {
        Map<String, Object> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        String date = sdf.format(new Date());

        map.put("bean", bean);
        map.put("table", "t" + c2_(bean));
        map.put("basePackage", basePackage);
        map.put("date", date);
        List<MangoField> mFields = new ArrayList<>();
//        Map<String, String> results = new LinkedHashMap<>();
        for (PsiField psiField : fields) {
            String text = psiField.getText();
            if (text.contains("static") || text.contains("final")) {
                continue;
            }
            MangoField field = new MangoField();
            PsiDocComment docComment = psiField.getDocComment();
            if (null != docComment) {
                String comment = docComment.getText();
                comment = comment.replaceAll("[\n, \r, *, /, \t]", "");
                field.setComment(comment);
            }
            String cname = psiField.getName();
            PsiType type = psiField.getType();
            field.setName(cname);
            field.setType(type.getCanonicalText());

            if (p2(field)) {
                mFields.add(field);
            }
//            results.put(cname, p(type.getCanonicalText(), cname));
        }
//        map.put("results", results);
        map.put("mFields", mFields);
        SourceGenerator.generate("dao.ftl", map, path + "/dao/I" + bean + "Dao.java");
        SourceGenerator.generate("service.ftl", map, path + "/service/I" + bean + "Service.java");
        SourceGenerator.generate("serviceImpl.ftl", map, path + "/service/impl/" + bean + "ServiceImpl.java");
        SourceGenerator.generate("control.ftl", map, path + "/control/" + bean + "Control.java");
        SourceGenerator.generate("sql.ftl", map, path + "/dao/" + bean + "-craete.sql");
    }


    private static boolean p2(MangoField field) {
        String column = null;
        String sql =null;
        switch (field.getType()) {
            case "java.lang.Long":
            case "Long":
            case "long":
                column = "n_" + c2_(field.getName());
                field.setColumn(column);

                sql = "`" + column  + "` bigint(20) ";
                if (field.getName().equals("id")) {
                    sql += "NOT NULL AUTO_INCREMENT ";
                }
                sql += comment(field.getComment());
                field.setSql(sql);
                return true;
            case "java.lang.Integer":
            case "Integer":
            case "int":
            case "java.lang.Double":
            case "Double":
            case "double":
            case "java.lang.Float":
            case "Float":
            case "float":
            case "java.lang.Short":
            case "Short":
            case "short":
            case "java.lang.Boolean":
            case "Boolean":
            case "boolean":
                column = "n_" + c2_(field.getName());
                field.setColumn(column);

                sql = "`" + column  + "` int(11) " + comment(field.getComment());
                field.setSql(sql);
                return true;
            case "java.lang.String":
            case "String":
                column = "c_" + c2_(field.getName());
                field.setColumn(column);

                sql = "`" + column  + "` varchar(255) " + comment(field.getComment());
                field.setSql(sql);
                return true;
            default:
                return false;
        }
    }

    /**
     * 注释
     * @param comment
     * @return
     */
    private static String comment(String comment) {
        return null == comment ? "" : ("COMMENT '" + comment + "'");
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
            case "String":
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
