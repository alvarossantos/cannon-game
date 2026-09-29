// MainActivityFragment.java
// MainActivityFragment creates and manages a CannonView
package com.alvaro.cannongame;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class MainActivityFragment extends Fragment {
    private CannonView cannonView; // custom view to display the game

    // called when Fragment's view needs to be created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
       Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // inflate the fragment_main.xml layout
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // get a reference to the CannonView
        cannonView = view.findViewById(R.id.cannonView);
        return view;
    }

    // set up volume control once Activity is created
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // allow volume buttons to set game volume
        requireActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    // when MainActivity is paused, terminate the game
    @Override
    public void onPause() {
        super.onPause();
        cannonView.stopGame(); // terminates the game
    }

    // when MainActivity is destroyed, release the CannonView's resources
    @Override
    public void onDestroy() {
        super.onDestroy();
        cannonView.releaseResources();
    }
}
