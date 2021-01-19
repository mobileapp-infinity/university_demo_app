package com.infinity.infoway.atmiya.forgot_password.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.forgot_password.adapter.RegisterStudentListIfOTPBasedVerificationAdapter;
import com.infinity.infoway.atmiya.forgot_password.pojo.OtpBaseLoginDetailsForStudentPojo;

import java.util.ArrayList;

public class RegisterStudentListIfOTPBasedVerificationBottomSheet extends BottomSheetDialogFragment {

    ForgotPasswordActivity context;
    RecyclerView rvRegisterStudentListIfOtpBasedLogin;
    ArrayList<OtpBaseLoginDetailsForStudentPojo.TableBean> tableBeanArrayList;
    String instituteId = "";

    RegisterStudentListIfOTPBasedVerificationBottomSheet() {

    }

    RegisterStudentListIfOTPBasedVerificationBottomSheet(Context context,
                                                         ArrayList<OtpBaseLoginDetailsForStudentPojo.TableBean> tableBeanArrayList,
                                                         String instituteId) {
        this.context = (ForgotPasswordActivity) context;
        this.tableBeanArrayList = tableBeanArrayList;
        this.instituteId = instituteId;
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_registered_student_list_if_otp_based_verification_bottom_sheet,
                container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvRegisterStudentListIfOtpBasedLogin = view.findViewById(R.id.rvRegisterStudentListIfOtpBasedLogin);
        RegisterStudentListIfOTPBasedVerificationAdapter registerStudentListAdapter = new RegisterStudentListIfOTPBasedVerificationAdapter(context, tableBeanArrayList,instituteId);
        rvRegisterStudentListIfOtpBasedLogin.setAdapter(registerStudentListAdapter);
    }

}
