package com.infinity.infoway.atmiya.student.forgot_password.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.forgot_password.pojo.OtpBaseLoginDetailsForEmployeePojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;

import java.util.ArrayList;

public class RegisterEmployeeListAdapter extends RecyclerView.Adapter<RegisterEmployeeListAdapter.MyViewHolder> {

    Context context;
    ArrayList<OtpBaseLoginDetailsForEmployeePojo.TableBean> tableBeanArrayList;
    LayoutInflater layoutInflater;
    IRegisterEmployeeList iRegisterEmployeeList;
    String instituteId = "";

    public RegisterEmployeeListAdapter(Context context, ArrayList<OtpBaseLoginDetailsForEmployeePojo.TableBean> tableBeanArrayList,
                                       String instituteId) {
        this.context = context;
        this.tableBeanArrayList = tableBeanArrayList;
        iRegisterEmployeeList = (IRegisterEmployeeList) context;
        layoutInflater = LayoutInflater.from(context);
        this.instituteId = instituteId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_register_employee_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OtpBaseLoginDetailsForEmployeePojo.TableBean tableBean = tableBeanArrayList.get(position);

        if (position == tableBeanArrayList.size() - 1) {
            holder.line.setVisibility(View.GONE);
        }

        String userName = "";
        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getEmpName())) {
            userName = tableBean.getEmpName() + " ";
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getEmpUsername())) {
            userName += "(" + tableBean.getEmpUsername() + ")";
        }

        holder.tvRegisterEmpUsername.setText(userName);

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getEmpMobilePhone())) {
            holder.tvRegisterEmpMobileNo.setText(tableBean.getEmpMobilePhone() + "");
        }

        holder.cbSelectRegisterEmp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iRegisterEmployeeList.onRegisterEmployeeSelected(tableBean, instituteId);
                        }
                    }, 80);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tableBeanArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvRegisterEmpUsername, tvRegisterEmpMobileNo;
        AppCompatCheckBox cbSelectRegisterEmp;
        View line;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRegisterEmpUsername = itemView.findViewById(R.id.tvRegisterEmpUsername);
            tvRegisterEmpMobileNo = itemView.findViewById(R.id.tvRegisterEmpMobileNo);
            cbSelectRegisterEmp = itemView.findViewById(R.id.cbSelectRegisterEmp);
            line = itemView.findViewById(R.id.line);
        }
    }

    public interface IRegisterEmployeeList {
        void onRegisterEmployeeSelected(OtpBaseLoginDetailsForEmployeePojo.TableBean tableBean, String instituteId);
    }


}
