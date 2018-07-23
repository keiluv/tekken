package com.app.jiwon.tekken7_manual.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.app.jiwon.tekken7_manual.R;

public class PopupFragment extends DialogFragment {

    final public static String FRAGMENT_TAG = "DictionaryDialog";

    private String title;
    private String message;

    public PopupFragment() {
    }

    @SuppressLint("ValidFragment")
    public PopupFragment(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.dictionary_popup_fragment_complete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        PopupFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}

