package com.kdanmobile.androidpractice.screen.imagemanipulation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kdanmobile.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageManipulationFragment extends Fragment {
    private static int SELECT_PICTURE = 1;
    @Bind(R.id.view)
    ManipulationContainer manipulationContainer;
    private String TAG = getClass().getSimpleName();

    public ImageManipulationFragment() {
        // Required empty public constructor
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            addImage(selectedImageUri);
        }
    }

    private void addImage(Uri imageUri) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageURI(imageUri);
        manipulationContainer.addView(imageView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_manipulation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void loadImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @OnClick(R.id.button_load)
    public void onLoadButtonClick() {
        loadImage();
    }

    @OnClick(R.id.button_clear)
    public void onClearButtonClick() {
        manipulationContainer.clear();
    }
}
