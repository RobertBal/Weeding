package com.sda.balys.robert.weeding.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sda.balys.robert.weeding.R;
import com.sda.balys.robert.weeding.model.Contact;

import java.io.PipedReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RENT on 2017-05-23.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    public List<Contact> mContactList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    public ContactAdapter(Context context){
        mLayoutInflater = mLayoutInflater.from(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row_contact,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        Contact contact =mContactList.get(position);
        holder.mIdContact.setText(contact.getmName());
        holder.mNameContact.setText(String.valueOf(contact.getmID()));

    }




    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.id_contact)
        TextView mIdContact;

        @BindView(R.id.name_contact)
        TextView mNameContact;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setData(List<Contact> contacts) {
        mContactList.clear();
        mContactList.addAll(contacts);
        notifyDataSetChanged();
    }

}
