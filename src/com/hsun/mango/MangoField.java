package com.hsun.mango;

/**
 * @author hsun
 * @version 1.0
 * @since 2017/6/23 下午3:28
 */
public class MangoField {

    private String name;

    private String type;

    private String column;

    private String comment;

    private String sql;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
