package com.example.zomato.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.zomato.R;
import com.example.zomato.databinding.BottomSheetBinding;
import com.example.zomato.utils.SortRestaurant;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class SortBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {

    private static SortBottomSheet FRAGMENT;
    private SortBottomSheetListener mListener;
    private BottomSheetBinding mBinding;

    public SortBottomSheet() {
        // Required empty public constructor
    }

    public static SortBottomSheet getInstance() {
        FRAGMENT = new SortBottomSheet();
        Bundle args = new Bundle();
        FRAGMENT.setArguments(args);
        return FRAGMENT;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet, container, false);
        mBinding.btnSort.setOnClickListener(this);
        mBinding.radioGroup.check(R.id.rb_rat_high_to_Low);
        return mBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        SortRestaurant sortRestaurant;

        switch (v.getId()) {
            case R.id.btn_sort:
                switch (mBinding.radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_rat_high_to_Low:
                        sortRestaurant = new SortRestaurant(true, false, false, false);
                        break;
                    case R.id.rb_rat_low_to_high:
                        sortRestaurant = new SortRestaurant(false, true, false, false);
                        break;
                    case R.id.rb_price_high_to_low:
                        sortRestaurant = new SortRestaurant(false, false, true, false);
                        break;
                    case R.id.rb_price_low_to_high:
                        sortRestaurant = new SortRestaurant(false, false, false, true);
                        break;
                    default:
                        sortRestaurant = new SortRestaurant(false, false, false, false);
                        break;
                }
                if (mListener != null) {
                    mListener.processSort(sortRestaurant);
                }
                dismiss();
                break;
        }
    }

    public void setListener(SortBottomSheetListener listener) {
        this.mListener = listener;
    }

    public interface SortBottomSheetListener {
        void processSort(SortRestaurant sortRestaurant);
    }
}
