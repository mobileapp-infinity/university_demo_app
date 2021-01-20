package com.infinity.infoway.atmiya.forgot_password.pojo;

import java.util.List;

public class CheckLoginByOTPAndUsernamePojo {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * emp_id : 2270
         * emp_number : 123456
         * name : CMS Admin Infinity
         * emp_main_school_id : 6
         * college_code : 11
         * college_name : Atmiya Institute of Technology & Science (MCA)
         * ac_logo : 458afea2_3f9f_45d6_8ccb_acb765678fff.png
         * stud_photo : https://cms.atmiya.edu.in/images/Emp_photo/2270.jpg
         * status : 1
         * is_director : 0
         * emp_year_id : 5
         * institute_id : 1
         * im_domain_name : https://cms.atmiya.edu.in
         * emp_user_status : 1
         * emp_permenant_college : null
         * emp_department_id : 4
         * emp_username : infinitysupport@gmail.com
         * emp_password : null
         */

        private int emp_id;
        private String emp_number;
        private String name;
        private int emp_main_school_id;
        private String college_code;
        private String college_name;
        private String ac_logo;
        private String stud_photo;
        private int status;
        private int is_director;
        private int emp_year_id;
        private int institute_id;
        private String im_domain_name;
        private int emp_user_status;
        private Object emp_permenant_college;
        private int emp_department_id;
        private String emp_username;
        private Object emp_password;



        private int stud_id;
        private int dm_id;
        private String dm_full_name;
        private int course_id;
        private String course_fullname;
        private int sm_id;
        private String sm_name;
       /* private int college_code;
        private String college_name;*/
        private int swd_year_id;
       /* private int status;*/
        private String hostel_code;
      /*  private String name;*/
        private String stud_admission_no;
        private String Stud_Enrollment_no;
      /*  private String stud_photo;*/
        private int swd_division_id;
        private int swd_batch_id;
        private int shift_id;
       /* private String im_domain_name;*/
        private int intitute_id;
        private String fc_file;
        private String Stud_user_name;
        private String Stud_password;

        public int getEmp_id() {
            return emp_id;
        }

        public void setEmp_id(int emp_id) {
            this.emp_id = emp_id;
        }

        public String getEmp_number() {
            return emp_number;
        }

        public void setEmp_number(String emp_number) {
            this.emp_number = emp_number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getEmp_main_school_id() {
            return emp_main_school_id;
        }

        public void setEmp_main_school_id(int emp_main_school_id) {
            this.emp_main_school_id = emp_main_school_id;
        }

        public String getCollege_code() {
            return college_code;
        }

        public void setCollege_code(String college_code) {
            this.college_code = college_code;
        }

        public String getCollege_name() {
            return college_name;
        }

        public void setCollege_name(String college_name) {
            this.college_name = college_name;
        }

        public String getAc_logo() {
            return ac_logo;
        }

        public void setAc_logo(String ac_logo) {
            this.ac_logo = ac_logo;
        }

        public String getStud_photo() {
            return stud_photo;
        }

        public void setStud_photo(String stud_photo) {
            this.stud_photo = stud_photo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_director() {
            return is_director;
        }

        public void setIs_director(int is_director) {
            this.is_director = is_director;
        }

        public int getEmp_year_id() {
            return emp_year_id;
        }

        public void setEmp_year_id(int emp_year_id) {
            this.emp_year_id = emp_year_id;
        }

        public int getInstitute_id() {
            return institute_id;
        }

        public void setInstitute_id(int institute_id) {
            this.institute_id = institute_id;
        }

        public String getIm_domain_name() {
            return im_domain_name;
        }

        public void setIm_domain_name(String im_domain_name) {
            this.im_domain_name = im_domain_name;
        }

        public int getEmp_user_status() {
            return emp_user_status;
        }

        public void setEmp_user_status(int emp_user_status) {
            this.emp_user_status = emp_user_status;
        }

        public Object getEmp_permenant_college() {
            return emp_permenant_college;
        }

        public void setEmp_permenant_college(Object emp_permenant_college) {
            this.emp_permenant_college = emp_permenant_college;
        }

        public int getEmp_department_id() {
            return emp_department_id;
        }

        public void setEmp_department_id(int emp_department_id) {
            this.emp_department_id = emp_department_id;
        }

        public String getEmp_username() {
            return emp_username;
        }

        public void setEmp_username(String emp_username) {
            this.emp_username = emp_username;
        }

        public Object getEmp_password() {
            return emp_password;
        }

        public void setEmp_password(Object emp_password) {
            this.emp_password = emp_password;
        }

        //student
        public int getStud_id() {
            return stud_id;
        }

        public void setStud_id(int stud_id) {
            this.stud_id = stud_id;
        }

        public int getDm_id() {
            return dm_id;
        }

        public void setDm_id(int dm_id) {
            this.dm_id = dm_id;
        }

        public String getDm_full_name() {
            return dm_full_name;
        }

        public void setDm_full_name(String dm_full_name) {
            this.dm_full_name = dm_full_name;
        }

        public int getCourse_id() {
            return course_id;
        }

        public void setCourse_id(int course_id) {
            this.course_id = course_id;
        }

        public String getCourse_fullname() {
            return course_fullname;
        }

        public void setCourse_fullname(String course_fullname) {
            this.course_fullname = course_fullname;
        }

        public int getSm_id() {
            return sm_id;
        }

        public void setSm_id(int sm_id) {
            this.sm_id = sm_id;
        }

        public String getSm_name() {
            return sm_name;
        }

        public void setSm_name(String sm_name) {
            this.sm_name = sm_name;
        }

      /*  public int getCollege_code() {
            return college_code;
        }

        public void setCollege_code(int college_code) {
            this.college_code = college_code;
        }*/

       /* public String getCollege_name() {
            return college_name;
        }

        public void setCollege_name(String college_name) {
            this.college_name = college_name;
        }*/

        public int getSwd_year_id() {
            return swd_year_id;
        }

        public void setSwd_year_id(int swd_year_id) {
            this.swd_year_id = swd_year_id;
        }

       /* public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }*/

        public String getHostel_code() {
            return hostel_code;
        }

        public void setHostel_code(String hostel_code) {
            this.hostel_code = hostel_code;
        }

       /* public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }*/

        public String getStud_admission_no() {
            return stud_admission_no;
        }

        public void setStud_admission_no(String stud_admission_no) {
            this.stud_admission_no = stud_admission_no;
        }

        public String getStud_Enrollment_no() {
            return Stud_Enrollment_no;
        }

        public void setStud_Enrollment_no(String Stud_Enrollment_no) {
            this.Stud_Enrollment_no = Stud_Enrollment_no;
        }

       /* public String getStud_photo() {
            return stud_photo;
        }

        public void setStud_photo(String stud_photo) {
            this.stud_photo = stud_photo;
        }*/

        public int getSwd_division_id() {
            return swd_division_id;
        }

        public void setSwd_division_id(int swd_division_id) {
            this.swd_division_id = swd_division_id;
        }

        public int getSwd_batch_id() {
            return swd_batch_id;
        }

        public void setSwd_batch_id(int swd_batch_id) {
            this.swd_batch_id = swd_batch_id;
        }

        public int getShift_id() {
            return shift_id;
        }

        public void setShift_id(int shift_id) {
            this.shift_id = shift_id;
        }

       /* public String getIm_domain_name() {
            return im_domain_name;
        }*/

        /*public void setIm_domain_name(String im_domain_name) {
            this.im_domain_name = im_domain_name;
        }*/

        public int getIntitute_id() {
            return intitute_id;
        }

        public void setIntitute_id(int intitute_id) {
            this.intitute_id = intitute_id;
        }

        public String getFc_file() {
            return fc_file;
        }

        public void setFc_file(String fc_file) {
            this.fc_file = fc_file;
        }

        public String getStud_user_name() {
            return Stud_user_name;
        }

        public void setStud_user_name(String Stud_user_name) {
            this.Stud_user_name = Stud_user_name;
        }

        public String getStud_password() {
            return Stud_password;
        }

        public void setStud_password(String Stud_password) {
            this.Stud_password = Stud_password;
        }
    }
}
