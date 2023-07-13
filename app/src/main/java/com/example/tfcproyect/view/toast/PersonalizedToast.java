package com.example.tfcproyect.view.toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfcproyect.R;

public class PersonalizedToast extends Toast {

    private Context context;


    public PersonalizedToast(Context context) {
        super(context);
        this.context = context;
    }

    public void makeToast(String textToast) {
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        View view = LayoutInflater.from(context).inflate(R.layout.item_toast, new ViewGroup(context) {
            @Override
            protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

            }
        }.findViewById(R.id.toastLinearLayout));
        TextView textView = view.findViewById(R.id.textToast);
        textView.setText(textToast);
        toast.setView(view);
        toast.show();
    }


}
