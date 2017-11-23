package org.haraldfw.oving4;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.haraldfw.oving4.dummy.ImageContent;

public class MainActivity extends AppCompatActivity implements ImageListFragment.OnListFragmentInteractionListener{

    private android.app.FragmentManager fragmentManager;
    private android.app.FragmentTransaction fragmentTransaction;
    private ImageListFragment listFragment;
    private ImageFragment imageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        imageFragment = (ImageFragment) getFragmentManager().findFragmentById(R.id.imageFragment);
        listFragment =(ImageListFragment) getFragmentManager().findFragmentById(R.id.imageList);
    }

    @Override
    public void onListFragmentInteraction(ImageContent.Image item) {
        imageFragment.setImage(item);
    }
}
