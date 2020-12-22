package com.infinity.infoway.atmiya.student.exam.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.TextViewMediumFont;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.exam.pojo.CIAExamResultPojo;
import com.infinity.infoway.atmiya.student.exam.pojo.CIAMarkstPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.GeneratePDFFileFromBase64String;

import java.util.ArrayList;

import javax.crypto.ExemptionMechanism;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CIAMarksListAdapter extends RecyclerView.Adapter<CIAMarksListAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<CIAMarkstPojo> ciaMarkstPojoArrayList;
    ProgressDialog progressDialog;

    public CIAMarksListAdapter(Context context, ArrayList<CIAMarkstPojo> ciaMarkstPojoArrayList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.ciaMarkstPojoArrayList = ciaMarkstPojoArrayList;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_cia_marks_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CIAMarkstPojo ciaMarkstPojo = ciaMarkstPojoArrayList.get(position);

//        if (position % 2 == 0) {
//            holder.llCIAMarksListMain.setBackground(ContextCompat.getDrawable(context, R.color.white));
//        } else {
//            holder.llCIAMarksListMain.setBackground(ContextCompat.getDrawable(context, R.color.exam_module_row_color));
//        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(ciaMarkstPojo.getProgramname())) {
            holder.tvCiaProgramName.setText(ciaMarkstPojo.getProgramname() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(ciaMarkstPojo.getSemesterName())) {
            holder.tvCIASemName.setText(ciaMarkstPojo.getSemesterName() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(ciaMarkstPojo.getYearName())) {
            holder.tvCIAExamination.setText(ciaMarkstPojo.getYearName() + "");
        }

        holder.tvCIAResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadCIAResultApiCall(ciaMarkstPojo.getSwdCollegeId() + "",
                        ciaMarkstPojo.getSwdYearId() + "", ciaMarkstPojo.getSwdSemId() + "",
                        ciaMarkstPojo.getExamId() + "", ciaMarkstPojo.getStudentId() + "");
            }
        });

    }

    private void downloadCIAResultApiCall(String Collegeid, String Yearid, String Semid,
                                          String Exam_id, String stud_id) {
        progressDialog.show();
        ApiImplementer.downloadCIAExamResultApiImplementer(Collegeid, Yearid, Semid, Exam_id, stud_id, new Callback<CIAExamResultPojo>() {
            @Override
            public void onResponse(Call<CIAExamResultPojo> call, Response<CIAExamResultPojo> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStatus() == 1 &&
                        response.body().getBase64string() != null && !response.body().getBase64string().isEmpty()) {
                    new GeneratePDFFileFromBase64String(context, "CIA Marks", CommonUtil.generateUniqueFileName(response.body().getFilename()), response.body().getBase64string(), progressDialog);
                } else {
                    progressDialog.hide();
                    Toast.makeText(context, "some thing went wrong please try again later.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CIAExamResultPojo> call, Throwable t) {
                progressDialog.hide();
                Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return ciaMarkstPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //        LinearLayout llCIAMarksListMain;
        TextViewMediumFont tvCiaProgramName, tvCIASemName, tvCIAExamination;
        TextViewRegularFont tvCIAResult;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCiaProgramName = itemView.findViewById(R.id.tvCiaProgramName);
            tvCIASemName = itemView.findViewById(R.id.tvCIASemName);
            tvCIAExamination = itemView.findViewById(R.id.tvCIAExamination);
            tvCIAResult = itemView.findViewById(R.id.tvCIAResult);
//            llCIAMarksListMain = itemView.findViewById(R.id.llCIAMarksListMain);
        }
    }

}
