package com.ttp.mycontact.classes;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ttp.mycontact.R;

public class EditDialog extends Dialog {

    Context context;

    EditText etName;
    EditText etPhoneNumber;
    TextView tvOk;
    TextView tvCancel;

    EditDialogInterface editDialogInterface;

    public EditDialog(@NonNull Context context, EditDialogInterface editDialogInterface) {
        super(context);

        this.editDialogInterface=editDialogInterface;
        this.context=context;
        ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutInflater.from(context).inflate(R.layout.edit_contact_view,null));

        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        findViews();
        configuration();
    }

    private void findViews(){

        etName=findViewById(R.id.et_name);
        etPhoneNumber=findViewById(R.id.et_phone_number);
        tvOk =findViewById(R.id.tv_add);
        tvCancel=findViewById(R.id.tv_cancel);

    }

    private void configuration() {

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evaluate()) {

                    editDialogInterface.callBack(etName.getText().toString(),etPhoneNumber.getText().toString());
                    cancel();

                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }


    public boolean evaluate(){

        if (etName.getText().length()==0)
        {
            Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show();
            etName.requestFocus();
            return  false;
        }
        else if (etPhoneNumber.getText().length()==0)
        {
            Toast.makeText(context, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
            etPhoneNumber.requestFocus();
            return  false;
        }
        else
            return true;

    }

}
