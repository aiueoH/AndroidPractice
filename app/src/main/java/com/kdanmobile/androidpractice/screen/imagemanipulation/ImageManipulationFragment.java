package com.kdanmobile.androidpractice.screen.imagemanipulation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kdanmobile.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageManipulationFragment extends Fragment {
    @Bind(R.id.view)
    ManipulationContainer manipulationContainer;
    private String TAG = getClass().getSimpleName();

    public ImageManipulationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_manipulation, container, false);
        ButterKnife.bind(this, view);
        setManipulation();
        return view;
    }

    private void setManipulation() {
        ImageView imageView = new ImageView(getContext());
        manipulationContainer.addView(imageView);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_camera));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
