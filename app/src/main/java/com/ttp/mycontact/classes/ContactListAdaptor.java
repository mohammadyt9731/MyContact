package com.ttp.mycontact.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.mycontact.R;
import com.ttp.mycontact.fragments.ShowContactFragment;

import java.util.List;

public class ContactListAdaptor extends RecyclerView.Adapter<ContactListAdaptor.ViewHolder> {

    Context context;
    List<Contact> contactsList;
    View contactView;

    public ContactListAdaptor(Context context,List<Contact> contactsList){

        this.context=context;
        this.contactsList=contactsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        contactView=layoutInflater.inflate(R.layout.view_contact,parent,false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        holder.tvName.setText(contactsList.get(position).getName());
        holder.tvPhoneNumber.setText(contactsList.get(position).getPhoneNumber());

        contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowContactFragment contactFragment=new ShowContactFragment(contactsList.get(position));
                AppCompatActivity appCompatActivity= (AppCompatActivity) context;
                appCompatActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_fragment_container,contactFragment)
                        .addToBackStack(null).commit();


            }
        });

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView tvName;
        public TextView tvPhoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tv_name);
            tvPhoneNumber =itemView.findViewById(R.id.tv_phoneNumber);
        }
    }

}
