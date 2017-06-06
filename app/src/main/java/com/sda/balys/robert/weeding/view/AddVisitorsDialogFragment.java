package com.sda.balys.robert.weeding.view;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sda.balys.robert.weeding.R;
import com.sda.balys.robert.weeding.model.Visitor;
import com.sda.balys.robert.weeding.repository.PeopleRepository;
import com.sda.balys.robert.weeding.repository.PeopleRepositoryImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2017-05-20.
 */

public class  AddVisitorsDialogFragment extends DialogFragment {
    public interface AddVisitorDialogInterface{
        void onDismisDialog();
    }

    private static final String BUNDLE_TEST = AddVisitorsDialogFragment.class.getSimpleName() + "Test";
    private static final String BUNDLE_VISITOR_ID = AddVisitorsDialogFragment.class.getSimpleName()+"Test";

    @BindView(R.id.label)
    TextView mTextview;

    @BindView(R.id.add_new_visitor) Button addNewVisitor;

    @BindView(R.id.cancelVisitor)
    Button mCancelVisitor;

    @BindView(R.id.amountVisitor)
    EditText mAmountVisitor;

    @BindView(R.id.nameVisitor)
    EditText mNameVisitor;

    @BindView(R.id.surnameVisitor)
    EditText mSurnameVisitor;

    @BindView(R.id.seekBar)
    SeekBar mSeekBar;

    @BindView(R.id.textCounter)
    TextView mTextCounter;


    private final PeopleRepository mPeopleRepository = PeopleRepositoryImpl.getmInstance();
    private AddVisitorDialogInterface mAddVisitorDialogInterface;

    private String mLabel;
    private Visitor mVisitor;



    public static AddVisitorsDialogFragment newInstance(String test){
        AddVisitorsDialogFragment dialog = new AddVisitorsDialogFragment();
        Bundle arguments=new Bundle();
        arguments.putString(BUNDLE_TEST,test);
        dialog.setArguments(arguments);
        return dialog;
    }
    public static AddVisitorsDialogFragment newInstance(int visitorId){
        AddVisitorsDialogFragment dialog = new AddVisitorsDialogFragment();
        Bundle arguments=new Bundle();
        arguments.putInt(BUNDLE_VISITOR_ID,visitorId);
        dialog.setArguments(arguments);
        return dialog;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        if(bundle.containsKey(BUNDLE_TEST)) {
            mLabel = bundle.getString(BUNDLE_TEST);
        }
        if(bundle.containsKey(BUNDLE_VISITOR_ID)){
            int visitorID = bundle.getInt(BUNDLE_VISITOR_ID);
            mVisitor = mPeopleRepository.getVisitor(visitorID);
        }
    }


    @ Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_visitors, container, false);
        ButterKnife.bind(this,view);

        if(mVisitor==null){
            mTextview.setText(mLabel);
        }
        else {

            mNameVisitor.setText(mVisitor.getmName());
            mAmountVisitor.setText(String.valueOf(mVisitor.getmAdditionalPerson()));
            mSurnameVisitor.setText(mVisitor.getmSurname());

        }

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAmountVisitor.setText(String.valueOf(progress));
                mTextCounter.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        //Bundle bundle = getArguments();
       // mLabel = bundle.getString(BUNDLE_TEST);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        final ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();




    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mAddVisitorDialogInterface.onDismisDialog();
    }

    @OnClick(R.id.add_new_visitor)
    public void onClickAddNewVisitor(){
        String name = mNameVisitor.getText().toString();
        String surname = mSurnameVisitor.getText().toString();
        int additional = mSeekBar.getProgress();
        if(mVisitor==null) {
            Visitor visitor = new Visitor(12, name, surname, additional, Visitor.VisitorStatus.NO_RESPONSE);
            mPeopleRepository.saveVisitor(visitor);
        }else{
            mVisitor.setmName(name);
            mVisitor.setmSurname(surname);
            mVisitor.setmAdditionalPerson(additional);
            mPeopleRepository.updateVisitor(mVisitor);


        }
        dismiss();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof AddVisitorDialogInterface){
            mAddVisitorDialogInterface = (AddVisitorDialogInterface) context;

        }else{
            throw new ClassCastException("Activity must implement AddVisitorDialogInterface");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof AddVisitorDialogInterface){
            mAddVisitorDialogInterface = (AddVisitorDialogInterface) activity;

        }else{
            throw new ClassCastException("Activity must implement AddVisitorDialogInterface");
        }
    }

    @OnClick(R.id.cancelVisitor)
    public void cancelVisitor(){
        dismiss();

    }



}
