package com.jmaelagha.pc.androidcourseapp.Models;

public class Table {
    private int table_id;
    private String tableName;
    private boolean table_status;

    public Table(int table_id, String tableName, boolean table_status) {
        this.table_id = table_id;
        this.tableName = tableName;
        this.table_status = table_status;
    }

    public Table() {
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isTable_status() {
        return table_status;
    }

    public void setTable_status(boolean table_status) {
        this.table_status = table_status;
    }
}
