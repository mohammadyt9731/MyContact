package com.ttp.mycontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ttp.mycontact.classes.Contact;
import com.ttp.mycontact.classes.ContactListAdaptor;
import com.ttp.mycontact.classes.DbHandler;
import com.ttp.mycontact.fragments.AddContactFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //
    RecyclerView recyclerViewContacts;
    FloatingActionButton floatingBtnAddContact;
    List<Contact> contactsList;
    ContactListAdaptor contactListAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        configuration();

    }

    @Override
    protected void onResume() {

        init();
        createContactsList();

        super.onResume();
    }

    private void findViews() {
        recyclerViewContacts=findViewById(R.id.rv_contacts);
        floatingBtnAddContact=findViewById(R.id.floating_btn_add_contact);

    }

    private void configuration() {

        floatingBtnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddContactFragment addContactFragment=new AddContactFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_fragment_container,addContactFragment)
                        .addToBackStack(null).commit();
            }
        });

    }

    private void init() {

        contactsList=new ArrayList<>();
        DbHandler dbHandler=new DbHandler(this);

        dbHandler.open();
        contactsList=dbHandler.getAllTodo();
        dbHandler.close();

    }

    private void createContactsList() {


        contactListAdaptor=new ContactListAdaptor(this,contactsList);

        recyclerViewContacts.setAdapter(contactListAdaptor);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));


    }



    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount()>0)
            onResume();


        super.onBackPressed();
    }
}