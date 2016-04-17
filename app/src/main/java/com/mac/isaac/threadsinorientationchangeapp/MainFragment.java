package com.mac.isaac.threadsinorientationchangeapp;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment {

    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.button_start)
    Button mybutton;
    MyTask mytask;
    View view;
    private FragmentListener myactivity;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        progressBar.setMax(100);
        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ButterKnife.bind(this, view);
        progressBar.setMax(100);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            myactivity = (FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myactivity = null;
    }

    @OnClick(R.id.button_start)
    public void startTask(View view) {
        mytask = new MyTask();
        mytask.execute("send it");
        progressBar.setProgress(0);
    }

    public interface FragmentListener {
        void sendProgress(int i);
    }

    public class MyTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                for (int i = 0; i <= 100; i+=1) {
                    Thread.sleep(125);
                    publishProgress(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return params[0]+" async task finished";
        }
    }
}
