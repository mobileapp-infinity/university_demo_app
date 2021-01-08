package com.infinity.infoway.atmiya.faculty.faculty_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewMediumFont;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.utils.CommonUtil;

import java.util.ArrayList;

public class FacultyNewsListAdapter extends RecyclerView.Adapter<FacultyNewsListAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyNewsPojo.GroupNewsDetail> facultyNewsPojoArrayList;

    public FacultyNewsListAdapter(Context context, ArrayList<FacultyNewsPojo.GroupNewsDetail> facultyNewsPojoArrayList) {
        this.context = context;
        this.facultyNewsPojoArrayList = facultyNewsPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_news_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FacultyNewsPojo.GroupNewsDetail groupNewsDetail = facultyNewsPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(groupNewsDetail.getCnSubject())) {
            holder.tvFacultyNewsTitle.setText(groupNewsDetail.getCnSubject() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(groupNewsDetail.getCnDate())) {
            holder.tvFacultyNewsDate.setText(groupNewsDetail.getCnDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(groupNewsDetail.getCnContent())) {
            holder.tvFacultyNewsDescription.setText(groupNewsDetail.getCnContent() + "");
        }

//        if (!CommonUtil.checkIsEmptyOrNullCommon(groupNewsDetail.getDownloadFile())){
//
//        }

    }

    @Override
    public int getItemCount() {
        return facultyNewsPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewMediumFont tvFacultyNewsTitle;
        TextViewRegularFont tvFacultyNewsDate;
        TextViewRegularFont tvFacultyNewsDescription;
        LinearLayout llDownloadFacultyNewsAttachment;
        TextViewRegularFont tvDownloadFacultyNews;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFacultyNewsTitle = itemView.findViewById(R.id.tvFacultyNewsTitle);
            tvFacultyNewsDate = itemView.findViewById(R.id.tvFacultyNewsDate);
            tvFacultyNewsDescription = itemView.findViewById(R.id.tvFacultyNewsDescription);
            llDownloadFacultyNewsAttachment = itemView.findViewById(R.id.llDownloadFacultyNewsAttachment);
            tvDownloadFacultyNews = itemView.findViewById(R.id.tvDownloadFacultyNews);
        }
    }

}
