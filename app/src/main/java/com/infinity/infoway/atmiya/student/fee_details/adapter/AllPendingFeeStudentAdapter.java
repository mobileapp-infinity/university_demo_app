package com.infinity.infoway.atmiya.student.fee_details.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewMediumFont;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.fee_details.pojo.GetAllPendingFeeStudentPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.DownloadPdfFromUrl;

import java.util.ArrayList;

public class AllPendingFeeStudentAdapter extends RecyclerView.Adapter<AllPendingFeeStudentAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<GetAllPendingFeeStudentPojo> getAllPendingFeeStudentPojoArrayList;
    ISelectedPendingFeeListItem iSelectedPendingFeeListItem;

    public AllPendingFeeStudentAdapter(Context context, ArrayList<GetAllPendingFeeStudentPojo> getAllPendingFeeStudentPojoArrayList) {
        this.context = context;
        this.getAllPendingFeeStudentPojoArrayList = getAllPendingFeeStudentPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
        iSelectedPendingFeeListItem = (ISelectedPendingFeeListItem) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_get_all_pending_fee_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GetAllPendingFeeStudentPojo getAllPendingFeeStudentPojo = getAllPendingFeeStudentPojoArrayList.get(position);

        String fees_status = "";

        if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeePayStatus())) {
            fees_status = getAllPendingFeeStudentPojo.getFeePayStatus().toString();
        }


        if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeExName())) {
            holder.tvExamNamePayFee.setText(getAllPendingFeeStudentPojo.getFeeExName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeExStartDate())) {
            holder.tvStartDatePayFee.setText(getAllPendingFeeStudentPojo.getFeeExStartDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeExEndDate())) {
            holder.tvEndDatePayFee.setText(getAllPendingFeeStudentPojo.getFeeExEndDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeePendingFee())) {
            holder.tvPayableFeePayFee.setText(getAllPendingFeeStudentPojo.getFeePendingFee() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeLateFee())) {
            holder.tvLateFeePayFee.setText(getAllPendingFeeStudentPojo.getFeeLateFee() + "");
        }

        holder.tvFeeStatusPayFee.setText(fees_status);


        if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeViewFile())) {
            holder.llDownloadPayFeeDocument.setVisibility(View.VISIBLE);
            holder.tvDownloadPayFeeDocument.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fileUrl = getAllPendingFeeStudentPojo.getFeeViewFile().toString();
                    String fileExtension = fileUrl.substring(fileUrl.lastIndexOf("."));
                    new DownloadPdfFromUrl(context, fileUrl, fileExtension, "Pay Fee");
                }
            });
        } else {
            holder.llDownloadPayFeeDocument.setVisibility(View.GONE);
        }

        holder.cbSelectPendingFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getAllPendingFeeStudentPojoArrayList.get(position).setPendingFeeTypeToChecked(isChecked);
                iSelectedPendingFeeListItem.onPendingFeeListItemSelected(getAllPendingFeeStudentPojoArrayList);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getAllPendingFeeStudentPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewMediumFont tvExamNamePayFee, tvStartDatePayFee, tvEndDatePayFee,
                tvPayableFeePayFee, tvLateFeePayFee, tvFeeStatusPayFee;
        TextViewRegularFont tvDownloadPayFeeDocument;
        LinearLayout llDownloadPayFeeDocument;
        AppCompatCheckBox cbSelectPendingFee;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExamNamePayFee = itemView.findViewById(R.id.tvExamNamePayFee);
            tvStartDatePayFee = itemView.findViewById(R.id.tvStartDatePayFee);
            tvEndDatePayFee = itemView.findViewById(R.id.tvEndDatePayFee);
            tvPayableFeePayFee = itemView.findViewById(R.id.tvPayableFeePayFee);
            tvLateFeePayFee = itemView.findViewById(R.id.tvLateFeePayFee);
            tvFeeStatusPayFee = itemView.findViewById(R.id.tvFeeStatusPayFee);
            tvDownloadPayFeeDocument = itemView.findViewById(R.id.tvDownloadPayFeeDocument);
            llDownloadPayFeeDocument = itemView.findViewById(R.id.llDownloadPayFeeDocument);
            cbSelectPendingFee = itemView.findViewById(R.id.cbSelectPendingFee);
        }
    }


    public interface ISelectedPendingFeeListItem {
        void onPendingFeeListItemSelected(ArrayList<GetAllPendingFeeStudentPojo> getAllPendingFeeStudentPojoArrayList);
    }

}
