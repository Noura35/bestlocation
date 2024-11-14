package com.example.bestlocation.ui.home;


import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bestlocation.Config;
import com.example.bestlocation.JSONParser;
import com.example.bestlocation.databinding.FragmentHomeBinding;
import com.example.bestlocation.Position;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;




public class HomeFragment extends Fragment {
    ArrayList<Position> data= new ArrayList<Position>();

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //connexion internet => thread
                Telechargement t=new Telechargement();
                t.execute();

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    class Telechargement extends AsyncTask
    {

        AlertDialog alert;


        @Override
        protected void onPreExecute() {
            //UI thread
            //getActivity() trj3lna l activity mtaa l Fragment
            AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
            dialog.setTitle("Téléchargement");




        }

        @Override
        protected Object doInBackground(Object[] objects) {
            JSONParser parser=new JSONParser();
            JSONObject response=parser.makeRequest(Config.Url_GetAll);
            try {
                int success=response.getInt("success");
                if (success==1)
                {
                    JSONArray tableau=response.getJSONArray("positions");
                    data.clear();
                    for(int i=0;i< tableau.length();i++)
                    {
                        JSONObject ligne=tableau.getJSONObject(i);
                        int idposition=ligne.getInt("id");
                        String pseudo=ligne.getString("pseudo");
                        String numero=ligne.getString("numero");
                        String longitude=ligne.getString("longitude");
                        String latitude=ligne.getString("latitude");
                        data.add(new Position(idposition,latitude,longitude,numero,pseudo));
                        //retrofit plugin fl java

                    }
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            alert.dismiss();

          /* binding.lv.setAdapter(new ArrayAdapter<>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    data
            ));*/

        }

    }


}