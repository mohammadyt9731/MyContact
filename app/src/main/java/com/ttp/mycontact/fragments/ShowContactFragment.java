package com.ttp.mycontact.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ttp.mycontact.classes.Contact;
import com.ttp.mycontact.classes.DbHandler;
import com.ttp.mycontact.classes.EditDialog;
import com.ttp.mycontact.classes.EditDialogInterface;
import com.ttp.mycontact.R;

public class ShowContactFragment extends Fragment {

    Contact contact;

    TextView tvName;
    TextView tvPhoneNumber;
    TextView tvEdit;
    TextView tvDelete;
    ImageButton ibCall;
    ImageButton ibSms;

    public ShowContactFragment(Contact contact) {
        this.contact=contact;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        findViews(view);
        configuration();

    }


    private void findViews(View view) {

        tvName=view.findViewById(R.id.tv_name);
        tvPhoneNumber=view.findViewById(R.id.tv_phoneNumber);
        tvEdit=view.findViewById(R.id.btn_edit);
        tvDelete=view.findViewById(R.id.btn_delete);
        ibCall=view.findViewById(R.id.ib_call);
        ibSms=view.findViewById(R.id.ib_sms);

    }

    private void configuration() {

        tvName.setText(contact.getName());
        tvPhoneNumber.setText(contact.getPhoneNumber());

            ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+contact.getPhoneNumber()));
                getActivity().startActivity(intent);
            }
        });

            ibSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("sms:"+contact.getPhoneNumber()));
                getActivity().startActivity(intent);
            }
        });

            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder deleteDialog = new AlertDialog.Builder(getActivity());

                    deleteDialog.setTitle("Delete");
                    deleteDialog.setMessage("Do you want to delete this contact?");

                    deleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(getContext(), "Contact removed successfully ", Toast.LENGTH_SHORT).show();

                            DbHandler dbHandler=new DbHandler(getActivity());

                            dbHandler.open();
                            dbHandler.delete(contact);
                            dbHandler.close();

                            getActivity().onBackPressed();

                        }


                    });
                    deleteDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();
                        }
                    });



                    deleteDialog.create().show();




                }
            });

            tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    EditDialog dialog=new EditDialog(getActivity(), new EditDialogInterface() {
                        @Override
                        public void callBack(String newName, String newPhoneNumber) {

                            DbHandler dbHandler = new DbHandler(getContext());
                            dbHandler.open();

                            dbHandler.update(contact,new Contact(contact.getId(),newName,newPhoneNumber));
                            dbHandler.close();

                            tvName.setText(newName);
                            tvPhoneNumber.setText(newPhoneNumber);
                            Toast.makeText(getContext(), "Contact edited successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();

                }
            });
    }
}