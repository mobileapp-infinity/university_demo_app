package com.infinity.infoway.university_demo.student.leave_application.pojo;

import java.util.List;

public class InsertStudentLeavePojo {

    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * insert_status : 14
         */

        private String insert_status;

        public String getInsert_status() {
            return insert_status;
        }

        public void setInsert_status(String insert_status) {
            this.insert_status = insert_status;
        }
    }

}
