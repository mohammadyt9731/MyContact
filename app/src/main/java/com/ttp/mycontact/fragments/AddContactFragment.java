package com.ttp.mycontact.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ttp.mycontact.classes.Contact;
import com.ttp.mycontact.classes.DbHandler;
import com.ttp.mycontact.R;

public class AddContactFragment extends Fragment {

    EditText etName;
    EditText etPhoneNumber;
    TextView tvAdd;
    TextView tvCancel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_contact,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        configuration();

    }

    private void configuration() {

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (evaluate()) {

                    DbHandler dbHandler = new DbHandler(getActivity());
                    dbHandler.open();


                  /*  dbHandler.insert(etName.getText().toString());
                    dbHandler.insert(etPhoneNumber.getText().toString());*/


                    Toast.makeText(getActivity(), String.valueOf(dbHandler.getLastId()), Toast.LENGTH_SHORT).show();
                    dbHandler.insert(new Contact(dbHandler.getLastId(),etName.getText().toString(),etPhoneNumber.getText().toString()));



                    dbHandler.close();
                    Toast.makeText(getActivity(), "Contact added successfully", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();

                }
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().onBackPressed();
            }
        });

    }

    private void findViews(View view) {

        etName=view.findViewById(R.id.et_name);
        etPhoneNumber=view.findViewById(R.id.et_phone_number);
        tvAdd=view.findViewById(R.id.tv_add);
        tvCancel=view.findViewById(R.id.tv_cancel);
    }


    public boolean evaluate(){

        if (etName.getText().length()==0)
        {
            Toast.makeText(getActivity(), "Please Enter Name", Toast.LENGTH_SHORT).show();
            etName.requestFocus();
            return  false;
        }
        else if (etPhoneNumber.getText().length()==0)
        {
            Toast.makeText(getActivity(), "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
            etPhoneNumber.requestFocus();
            return  false;
        }
        else
            return true;

    }
}
