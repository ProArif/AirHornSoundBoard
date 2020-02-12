package com.olivineltd.airhornsoundboard;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cunoraz.gifview.library.GifView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_one.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_one#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_one extends Fragment {
    Button button, button1;
    static MediaPlayer player;
    FrameLayout frameLayout;
    GifView gifView;
    ImageView backImage;
    String number;


    private OnFragmentInteractionListener mListener;

    public Fragment_one() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_one.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_one newInstance(String param1, String param2) {
        Fragment_one fragment = new Fragment_one();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        number = getArguments().getString("Number");
        frameLayout = view.findViewById(R.id.frameView);
        button = view.findViewById(R.id.buttonID);
        button1 = view.findViewById(R.id.buttonID1);
        addAnimation();//to add gif files
        backImage = view.findViewById(R.id.backPress);

       button1.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (player == null) {

//                            addPlayer();
                            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    if (player != null) {
                                        player.release();
                                        player = null;
                                    }
                                }
                            });
                        }
                        button1.setVisibility(View.VISIBLE);
                        button.setVisibility(View.INVISIBLE);
                        gifView.play();
                        player.start();
                        player.setLooping(true);
                        return true;


                    case MotionEvent.ACTION_UP:
                        if (player != null) {
                            button1.setVisibility(View.INVISIBLE);
                            button.setVisibility(View.VISIBLE);
                            player.release();
                            player = null;
                            addAnimation();//call for recreating the gif view

                        }
                        return true;

                }
                return false;
            }
        });

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });


    }

    private void addAnimation() {
        gifView = new GifView(getContext());
        frameLayout.removeAllViews();
        frameLayout.addView(gifView,
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        if (number.equals("one")) {
            gifView.setGifResource(R.drawable.amb);
            player = MediaPlayer.create(getActivity(), R.raw.police);
        } else if (number.equals("two")) {
            gifView.setGifResource(R.drawable.crying);
            player = MediaPlayer.create(getActivity(), R.raw.baby);
        } else if (number.equals("three")) {
            gifView.setGifResource(R.drawable.st);
            player = MediaPlayer.create(getActivity(), R.raw.traffic);
        } else {
            gifView.setGifResource(R.drawable.wave);
            player = MediaPlayer.create(getActivity(), R.raw.dixie);
        }
        gifView.pause();
    }

//    private void addPlayer() {
//        if (number.equals("one")) {
//            player = MediaPlayer.create(getActivity(), R.raw.police);
//        } else if (number.equals("two")) {
//            player = MediaPlayer.create(getActivity(), R.raw.baby);
//        } else if (number.equals("three")) {
//            player = MediaPlayer.create(getActivity(), R.raw.traffic);
//        } else {
//            player = MediaPlayer.create(getActivity(), R.raw.dixie);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        void onFragmentInteraction(Uri uri);
    }
}
