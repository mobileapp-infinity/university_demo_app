package com.infinity.infoway.atmiya.utils;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;

import androidx.core.content.res.ResourcesCompat;

import com.infinity.infoway.atmiya.R;

public class DialogUtil {
    static ProgressDialog m_Dialog = null;


    public static ProgressDialog showProgressDialog(Context context, String text) {
        m_Dialog = new ProgressDialog(context);
        if (text == null || text.contentEquals("")) {
            text = "Please Wait...";
        }
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(ResourcesCompat.getFont(context, R.font.ubuntu_r), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        m_Dialog.setMessage(spannableString);

        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.setCancelable(true);
        m_Dialog.show();
        return m_Dialog;
    }

    public static void hideProgressDialog() {
        if (m_Dialog != null) {
            if (m_Dialog.isShowing()) {
                m_Dialog.dismiss();
            }
        }
    }

    public static ProgressDialog showProgressDialogNotCancelable(Context context, String text) {
        m_Dialog = new ProgressDialog(context);
        if (text == null || text.contentEquals("")) {
            text = "Please Wait...";
        }
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(ResourcesCompat.getFont(context, R.font.ubuntu_r), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        m_Dialog.setMessage(spannableString);
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.setCancelable(false);
        m_Dialog.show();
        return m_Dialog;
    }

}
