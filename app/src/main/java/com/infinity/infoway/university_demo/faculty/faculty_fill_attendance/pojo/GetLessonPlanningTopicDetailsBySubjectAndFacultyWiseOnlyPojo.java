package com.infinity.infoway.university_demo.faculty.faculty_fill_attendance.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo {

    @SerializedName("Table")
    @Expose
    private List<Table> table = null;

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

    public class Table {

        @SerializedName("topic_id")
        @Expose
        private String topicId;
        @SerializedName("topic_name")
        @Expose
        private String topicName;

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

    }

}
