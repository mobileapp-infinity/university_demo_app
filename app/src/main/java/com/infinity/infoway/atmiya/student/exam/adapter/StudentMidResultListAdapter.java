package com.infinity.infoway.atmiya.student.exam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.CustomAnimationForDefaultExpandableCard;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.exam.pojo.DownloadStudentMidResultPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.MidResultPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentMidResultListAdapter extends RecyclerView.Adapter<StudentMidResultListAdapter.MyViewHolder> {

    Context context;
    ArrayList<MidResultPojo> midResultPojoArrayList;
    LayoutInflater layoutInflater;
    MySharedPreferences mySharedPreferences;

    public StudentMidResultListAdapter(Context context, ArrayList<MidResultPojo> midResultPojoArrayList) {
        this.context = context;
        this.midResultPojoArrayList = midResultPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
        mySharedPreferences = new MySharedPreferences(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_mid_result_marks_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MidResultPojo midResultPojo = midResultPojoArrayList.get(position);
        if (!CommonUtil.checkIsEmptyOrNullCommon(midResultPojo.getSmName())) {
            holder.tvSemNameMidResult.setText(midResultPojo.getSmName() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(midResultPojo.getYearName())) {
            holder.tvYearNameMidResult.setText(midResultPojo.getYearName() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(midResultPojo.getSrpcResultName())) {
            holder.tvResultNameMidResult.setText(midResultPojo.getSrpcResultName() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(midResultPojo.getSrpcResultDisplayDate())) {
            holder.tvResultDateMidResult.setText(midResultPojo.getSrpcResultDisplayDate() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(midResultPojo.getSrpcRemarks())) {
            holder.tvRemarksMidResult.setText(midResultPojo.getSrpcRemarks() + "");
        }

        holder.llExpandedHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!midResultPojoArrayList.get(position).isExpanded(), holder.ivViewMoreMidResult, holder.llExpandableLayout);
                midResultPojoArrayList.get(position).setExpanded(show);
            }
        });

    }

    @Override
    public int getItemCount() {
        return midResultPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreMidResult;
        LinearLayout llExpandableLayout;
        LinearLayout llExpandedHeader;

        LinearLayout llDownloadMidResult;
        TextViewRegularFont btnDownloadMidResult;
        TextViewRegularFont tvSemNameMidResult;
        TextViewRegularFont tvYearNameMidResult;
        TextViewRegularFont tvResultNameMidResult;
        TextViewRegularFont tvResultDateMidResult;
        TextViewRegularFont tvRemarksMidResult;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivViewMoreMidResult = itemView.findViewById(R.id.ivViewMoreMidResult);
            llExpandableLayout = itemView.findViewById(R.id.llExpandableLayout);
            llExpandedHeader = itemView.findViewById(R.id.llExpandedHeader);
            llDownloadMidResult = itemView.findViewById(R.id.llDownloadMidResult);
            btnDownloadMidResult = itemView.findViewById(R.id.btnDownloadMidResult);
            tvSemNameMidResult = itemView.findViewById(R.id.tvSemNameMidResult);
            tvYearNameMidResult = itemView.findViewById(R.id.tvYearNameMidResult);
            tvResultNameMidResult = itemView.findViewById(R.id.tvResultNameMidResult);
            tvResultDateMidResult = itemView.findViewById(R.id.tvResultDateMidResult);
            tvRemarksMidResult = itemView.findViewById(R.id.tvRemarksMidResult);


        }
    }

    private boolean toggleLayout(boolean isExpanded, View v, LinearLayout layoutExpand) {
        CustomAnimationForDefaultExpandableCard.toggleArrow(v, isExpanded);
        if (isExpanded) {
            CustomAnimationForDefaultExpandableCard.expand(layoutExpand);
        } else {
            CustomAnimationForDefaultExpandableCard.collapse(layoutExpand);
        }
        return isExpanded;

    }

    private void downloadStudentMidResultApiCall(String srpcId) {
        ApiImplementer.downloadStudentMidResultApiImplementer(mySharedPreferences.getSmId(), srpcId, mySharedPreferences.getStudentId(), mySharedPreferences.getDmId(),
                mySharedPreferences.getCourseId(), new Callback<DownloadStudentMidResultPojo>() {
                    @Override
                    public void onResponse(Call<DownloadStudentMidResultPojo> call, Response<DownloadStudentMidResultPojo> response) {

                    }

                    @Override
                    public void onFailure(Call<DownloadStudentMidResultPojo> call, Throwable t) {

                    }
                });
    }

}
