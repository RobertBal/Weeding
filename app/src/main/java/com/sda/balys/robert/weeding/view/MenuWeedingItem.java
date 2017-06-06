package com.sda.balys.robert.weeding.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sda.balys.robert.weeding.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RENT on 2017-05-18.
 */

public class MenuWeedingItem extends RelativeLayout {
    @BindView(R.id.menu_item_image)
    ImageView mImageView;
    @BindView(R.id.menu_item_text)
    TextView mLabel;

    public MenuWeedingItem(Context context) {
        super(context);
        init(null);
    }

    public MenuWeedingItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MenuWeedingItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public MenuWeedingItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(final AttributeSet attrs) {
        inflate(getContext(), R.layout.view_menu_item, this);
        setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));

        if(isInEditMode()){
            return;
        }
        ButterKnife.bind(this);

        if(attrs!=null){
            final TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.MenuWeedingItem);
            CharSequence label= typedArray.getText(R.styleable.MenuWeedingItem_label);
            mLabel.setText(label);

            int icon = typedArray.getResourceId(R.styleable.MenuWeedingItem_icon_res_id,0);
            mImageView.setImageDrawable(ContextCompat.getDrawable(getContext(),icon));

            typedArray.recycle();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
