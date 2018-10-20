package com.example.elihsm.contacts;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> contacts= new ArrayList<>();
    private Context context;
    public static final int   TYPE_ROW=0;
    public static  final int TYPE_GRID=1;
    private int type=TYPE_ROW;





    public ContactAdapter(Context context) {
        this.context=context;
    }
    public  void addContact(Contact contact){
        contacts.add(contact);
        notifyItemChanged(contacts.indexOf(contact));


    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);

       switch (type){
           case TYPE_ROW:
           view=layoutInflater.inflate(R.layout.item_contact_row,parent,false);
           break;
           case TYPE_GRID:
               view=layoutInflater.inflate(R.layout.item_contact_grid,parent,false);
               break;
               default:
                   view=null;
       }
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.bindContact(contacts.get(position));

    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
    public int getViewType() {
        return type;
    }

    public void setViewType(int viewType) {
        this.type = viewType;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        private TextView contactnametv;
        private TextView contactphonetv;
        private TextView firstchartv;


        public ContactViewHolder(View itemView) {
            super(itemView);
            contactnametv=itemView.findViewById(R.id.tv_contact_contactName);
            contactphonetv=itemView.findViewById(R.id.tv_contact_phoneNumber);
            firstchartv=itemView.findViewById(R.id.tv_contact_firstCharacter);
        }
        public void bindContact(Contact contact){
            firstchartv.setText(contact.getName().substring(0,1));
            contactnametv.setText(contact.getName());
            contactphonetv.setText((contact.getPhoneNumber()));

        }
    }


}
