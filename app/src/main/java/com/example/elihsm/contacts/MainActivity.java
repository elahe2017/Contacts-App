package com.example.elihsm.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private EditText contactNameEt;
    private EditText contactPhoneNumberEt;
    private Button   addContactBtn;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        recyclerView=findViewById(R.id.rv_main_contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        contactAdapter=new ContactAdapter(this);
        recyclerView.setAdapter(contactAdapter);

        contactNameEt=findViewById(R.id.et_main_name);
        contactPhoneNumberEt=findViewById(R.id.et_main_phone);


        addContactBtn=findViewById(R.id.button_main_addContact);
        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contactNameEt.length()>0) {
                    if (contactPhoneNumberEt.length() == 11) {
                        contactAdapter.addContact(new Contact(contactNameEt.getText().toString(), contactPhoneNumberEt
                                .getText().toString()
                        ));


                    } else {
                        contactPhoneNumberEt.setError("phone Number is not valid");
                    }
                }
                    else{
                        contactNameEt.setError("contact Name is not valid");


                }

            }
        });



        final ImageView viewTypeChanger=findViewById(R.id.iv_main_viewTypeChanger);
        viewTypeChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contactAdapter.getViewType()==ContactAdapter.TYPE_ROW) {
                    contactAdapter.setViewType(ContactAdapter.TYPE_GRID);
                    viewTypeChanger.setImageResource(R.drawable.ic_list_white_24dp);
                    recyclerView.setLayoutManager(new GridLayoutManager(
                            MainActivity.this, 2, LinearLayoutManager.VERTICAL, false
                    ));}else {
                    contactAdapter.setViewType(ContactAdapter.TYPE_ROW);
                    viewTypeChanger.setImageResource(R.drawable.ic_list_white_24dp);
                    recyclerView.setLayoutManager(new LinearLayoutManager(
                            MainActivity.this,LinearLayoutManager.VERTICAL,false
                    ));

                }

            }
        });
    }
}
