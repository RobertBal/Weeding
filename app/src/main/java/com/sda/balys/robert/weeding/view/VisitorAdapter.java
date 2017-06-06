package com.sda.balys.robert.weeding.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sda.balys.robert.weeding.R;
import com.sda.balys.robert.weeding.model.Visitor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RENT on 2017-05-20.
 */

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.VisitorViewHolder>{

    private final LayoutInflater mLayoutInlater;
    private final List<Visitor> mData = new ArrayList<>();
    private View.OnLongClickListener mOnLongClickListener;
    private View.OnClickListener mOnClickListener;

    public VisitorAdapter(Context context){
        mLayoutInlater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public VisitorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInlater.inflate(R.layout.row_viistor,parent,false);

        return new VisitorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VisitorViewHolder holder, int position) {
    //pamietac ok jezeli jest czlowiek odpowiedzial to zrobimy na zielon if elsa bo nastepny bedzie tak samo
        //pamieta stare stany
        Visitor visitor = mData.get(position);
        holder.mLabel.setText(visitor.getmName()+" "+visitor.getmSurname());
        holder.mAmount.setText(String.valueOf(visitor.getmAdditionalPerson()));
        int color = visitor.getmVisitorStatus().getmColor();
        int color1 = ContextCompat.getColor(holder.itemView.getContext(), color);
        holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(),visitor.getmVisitorStatus().getmColor()));

        switch (visitor.getmVisitorStatus()) {
            case NO_RESPONSE:
            holder.mResponse.setVisibility(View.INVISIBLE);
                break;
            case RESPONSE_NO:
                holder.mResponse.setChecked(true);
                break;
            case RESPONSE_OK:
                holder.mResponse.setChecked(false);
                break;
        }
        holder.itemView.setTag(visitor);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                return mOnLongClickListener.onLongClick(v);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 mOnClickListener.onClick(v);
            }
        });


    }
    public void setmOnLongClickListener(View.OnLongClickListener onLongClickListener){
        mOnLongClickListener = onLongClickListener;
    }

    public void setmOnClickListener(View.OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }


    public int getItemCount() {

        //tyle ile jesst elementow
        return mData.size();
    }

    public void setData(List<Visitor> visitors){
        mData.clear();
        mData.addAll(visitors);
        notifyDataSetChanged();

    }

    public class VisitorViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.label)
        TextView mLabel;

        @BindView(R.id.amount)
        TextView mAmount;

        @BindView(R.id.response)
        CheckBox mResponse;

        public VisitorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
