package org.haraldfw.oving4;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.haraldfw.oving4.dummy.ImageContent;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String IMG_ID = "imageId";
    private static final String IMG_DESC = "imageDescription";
    private static final String IMG_DRAWABLE = "imageDrawable";

    private Button nextButton;
    private Button prevButton;


    // TODO: Rename and change types of parameters
    private ImageContent.Image image;

    public ImageFragment() {
        this.image = new ImageContent.Image("0", "http://www.designformusic.com/wp-content/uploads/2015/10/insurgency-digital-album-cover-design.jpg", "insurgency");
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id  Parameter 1.
     * @param dec Parameter 2.
     * @return A new instance of fragment ImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageFragment newInstance(String id, String dec, String imageDrawable) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(IMG_ID, id);
        args.putString(IMG_DESC, dec);
        args.putString(IMG_DRAWABLE, imageDrawable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String id = getArguments().getString(IMG_ID);
            String desc = getArguments().getString(IMG_DESC);
            String uri = getArguments().getString(IMG_DRAWABLE);
            this.image = new ImageContent.Image(id, uri, desc);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateInfo(view);

        nextButton = view.findViewById(R.id.nextImgBut);
        nextButton.setOnClickListener(this);

        prevButton = view.findViewById(R.id.prevImgBut);
        prevButton.setOnClickListener(this);
    }




    private void updateInfo(View view) {
        TextView textView = view.findViewById(R.id.imageDescription);
        textView.setText(this.image.description);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageDrawable(this.image.imageDrawable);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    public void setImage(ImageContent.Image image) {
        this.image = image;
        updateInfo(getView());
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.nextImgBut) {
            moveImageIndex(1);
        } else if (i == R.id.prevImgBut) {
            moveImageIndex(-1);
        } else {
            throw new IllegalStateException("Invalid button pressed");
        }
        updateInfo(getView());
    }

    private void moveImageIndex(int mod) {
        setImage(ImageContent.ITEMS.get(
                (Integer.parseInt(this.image.id) + mod + ImageContent.ITEMS.size())
                        % ImageContent.ITEMS.size()));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(ImageContent.Image image);
    }
}
