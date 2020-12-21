package com.infinity.infoway.atmiya.student.fee_details.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.custom_class.Animations;
import com.infinity.infoway.atmiya.custom_class.TextViewMediumFont;
import com.infinity.infoway.atmiya.custom_class.TextViewRegularFont;
import com.infinity.infoway.atmiya.student.fee_details.pojo.FeeReceiptPojo;
import com.infinity.infoway.atmiya.student.fee_details.pojo.PrintFeeReceiptPojo;
import com.infinity.infoway.atmiya.utils.CommonUtil;
import com.infinity.infoway.atmiya.utils.DialogUtil;
import com.infinity.infoway.atmiya.utils.MySharedPreferences;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeReceiptListAdapter extends RecyclerView.Adapter<FeeReceiptListAdapter.MyViewHolderFeeReceipt> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FeeReceiptPojo> feeReceiptPojoArrayList;
    MySharedPreferences mySharedPreferences;

    public FeeReceiptListAdapter(Context context, ArrayList<FeeReceiptPojo> feeReceiptPojoArrayList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.feeReceiptPojoArrayList = feeReceiptPojoArrayList;
        mySharedPreferences = new MySharedPreferences(context);
    }

    @NonNull
    @Override
    public FeeReceiptListAdapter.MyViewHolderFeeReceipt onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_fee_receipt_list_item, parent, false);
        return new MyViewHolderFeeReceipt(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeeReceiptListAdapter.MyViewHolderFeeReceipt holder, int position) {


        FeeReceiptPojo feeReceiptPojo = feeReceiptPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeClassName())) {
            holder.tvClassName.setText(feeReceiptPojo.getFeeClassName());
            holder.tvClasName_.setText(feeReceiptPojo.getFeeClassName());

        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeAmt())) {
            holder.tvFeeAmount.setText(feeReceiptPojo.getFeeAmt() + "");
            holder.tvFeeAmount_.setText(feeReceiptPojo.getFeeAmt() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeReceiptNo())) {
            holder.tvFeeReceiptNo.setText(feeReceiptPojo.getFeeReceiptNo() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeReceiptDate())) {
            holder.tvFeeReceiptDate.setText(feeReceiptPojo.getFeeReceiptDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeBankDocumentType())) {
            holder.tvPaymentMode.setText(feeReceiptPojo.getFeeBankDocumentType() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeRefNo())) {
            holder.tvReferenceNo.setText(feeReceiptPojo.getFeeRefNo() + ", ");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeBankName())) {
            holder.tvBankName.setText(feeReceiptPojo.getFeeBankName() + "");
        }


        holder.llExpandedHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!feeReceiptPojoArrayList.get(position).isExpanded(), holder.ivViewMoreBtn, holder.llExpandableLayout);
                feeReceiptPojoArrayList.get(position).setExpanded(show);
            }
        });

        holder.tvPreintFeeReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFeeReceiptApiCall(feeReceiptPojoArrayList.get(position).getFeeReceiptNo());
            }
        });

    }


    private void downloadFeeReceiptApiCall(String feeReceiptNo) {
        DialogUtil.showProgressDialogNotCancelable(context, "");
        ApiImplementer.downloadFeeReceiptApiImplementer(mySharedPreferences.getStudentId(), feeReceiptNo, new Callback<PrintFeeReceiptPojo>() {
            @Override
            public void onResponse(Call<PrintFeeReceiptPojo> call, Response<PrintFeeReceiptPojo> response) {
                DialogUtil.hideProgressDialog();
                if (response.isSuccessful() && response.body() != null && response.body().getStatus() == 1) {
                    downloadFeeReceipt(response.body().getBase64string(), response.body().getFilename());
                } else {
                    Toast.makeText(context, "Some thing went wrong please try again later.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PrintFeeReceiptPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void downloadFeeReceipt(String getbytearr, String fileName) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), CommonUtil.FOLDER_NAME + "/" + "Fee_Receipt/" + "" + fileName);

        if (file.exists()) {
            File file11 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), CommonUtil.FOLDER_NAME + "/" + "Fee_Receipt/" + fileName);

            Intent target = new Intent(Intent.ACTION_VIEW);

            if (Build.VERSION.SDK_INT > 24) {
                Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file11);


                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                target.setDataAndType(uri, "application/pdf");
                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "No Apps can performs This action", Toast.LENGTH_LONG).show();
                }
            } else {
                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.setDataAndType(Uri.fromFile(file11), "application/pdf");
                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "No Apps can performs This action", Toast.LENGTH_LONG).show();
                }


            }
        } else {
            try {
                byte data[] = new byte[1024];

                long total = 0;
                data = getbytearr.getBytes("UTF-8");
                //data = response.body().getbytearr();
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + CommonUtil.FOLDER_NAME);
                dir.mkdirs();
                //File outputFile = new File(createfile, "Sample.pdf");
//                                      FileOutputStream fos = new FileOutputStream(dir);

                                     /* //Writing into the PDF File
                                      strByte = (xpp.getText().toString());
                                      bytes = Base64.decode(strByte.toString(),Base64.DEFAULT);//Converting Base64 to byte
                                     */

                String filepath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + CommonUtil.FOLDER_NAME + "/" + fileName;
                byte[] pdfAsBytes = Base64.decode(getbytearr, 0);
                FileOutputStream os;
                os = new FileOutputStream(filepath, false);
                os.write(pdfAsBytes);
                os.flush();
                os.close();
                File file11 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), CommonUtil.FOLDER_NAME + "/" + fileName);

                DialogUtil.hideProgressDialog();

                Intent target = new Intent(Intent.ACTION_VIEW);

                if (Build.VERSION.SDK_INT > 24) {
                    Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file11);

                    target.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    target.setDataAndType(uri, "application/pdf");
                    Intent intent = Intent.createChooser(target, "Open File");
                    try {
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context, "No Apps can performs This actton", Toast.LENGTH_LONG).show();
                    }
                } else {
                    target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    target.setDataAndType(Uri.fromFile(file11), "application/pdf");
                    target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Intent intent = Intent.createChooser(target, "Open File");
                    try {
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context, "No Apps can performs This action", Toast.LENGTH_LONG).show();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public int getItemCount() {
        return feeReceiptPojoArrayList.size();
    }

    class MyViewHolderFeeReceipt extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreBtn;
        LinearLayout llExpandableLayout;
        LinearLayout llExpandedHeader;
        TextViewRegularFont tvClassName, tvFeeAmount, tvClasName_, tvFeeAmount_;
        TextViewMediumFont tvFeeReceiptNo, tvFeeReceiptDate, tvPaymentMode, tvReferenceNo, tvBankName;
        TextViewRegularFont tvPreintFeeReceipt;

        public MyViewHolderFeeReceipt(@NonNull View itemView) {
            super(itemView);
            ivViewMoreBtn = itemView.findViewById(R.id.ivViewMoreBtn);
            llExpandableLayout = itemView.findViewById(R.id.llExpandableLayout);
            llExpandedHeader = itemView.findViewById(R.id.llExpandedHeader);
            tvClassName = itemView.findViewById(R.id.tvClassName);
            tvFeeAmount = itemView.findViewById(R.id.tvFeeAmount);
            tvClasName_ = itemView.findViewById(R.id.tvClasName_);
            tvFeeAmount_ = itemView.findViewById(R.id.tvFeeAmount_);
            tvFeeReceiptNo = itemView.findViewById(R.id.tvFeeReceiptNo);
            tvPreintFeeReceipt = itemView.findViewById(R.id.tvPreintFeeReceipt);
            tvFeeReceiptDate = itemView.findViewById(R.id.tvFeeReceiptDate);
            tvPaymentMode = itemView.findViewById(R.id.tvPaymentMode);
            tvReferenceNo = itemView.findViewById(R.id.tvReferenceNo);
            tvBankName = itemView.findViewById(R.id.tvBankName);
        }
    }

    private boolean toggleLayout(boolean isExpanded, View v, LinearLayout layoutExpand) {
        Animations.toggleArrow(v, isExpanded);
        if (isExpanded) {
            Animations.expand(layoutExpand);
        } else {
            Animations.collapse(layoutExpand);
        }
        return isExpanded;

    }
}
