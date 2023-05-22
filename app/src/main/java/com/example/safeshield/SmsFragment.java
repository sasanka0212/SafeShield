package com.example.safeshield;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SmsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText smsAutoTextView;
    private RecyclerView smsRecycler;
    private ArrayList<ContactFace> list;
    private ArrayList<String> numbers;
    private SmsAdapter adapter;
    FloatingActionButton smsSendAll;
    SharedPreferences pref;

    public SmsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SmsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SmsFragment newInstance(String param1, String param2) {
        SmsFragment fragment = new SmsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        smsAutoTextView = view.findViewById(R.id.smsAutoTextView);
        smsRecycler = view.findViewById(R.id.smsRecycler);
        smsSendAll = view.findViewById(R.id.smsSendAll);

        smsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        smsRecycler.setHasFixedSize(true);

        smsAutoTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        smsSendAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers = new ArrayList<String>();
                String separator = "; ";
                if(android.os.Build.MANUFACTURER.equalsIgnoreCase("samsung")){
                    separator = ", ";
                }
                for(int i = 0; i< list.size(); i++){
                    numbers.add(list.get(i).phoneNo + separator);
                }
                pref = getActivity().getSharedPreferences("login",MODE_PRIVATE);
                String msg = pref.getString("msg", "");
                try {
                    Uri uri = Uri.parse("smsto:" + numbers);
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
                    smsIntent.putExtra("sms_body", msg);
                    startActivity(smsIntent);
                }catch (Exception e) {
                    Toast.makeText(getActivity(),
                            "SMS faild, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        getData();
    }

    private void filter(String text) {
        ArrayList<ContactFace> filterList = new ArrayList<>();
        for(ContactFace items : list){
            if(items.name.toLowerCase().contains(text.toLowerCase())){
                filterList.add(items);
            }
            adapter.filterList(filterList);
        }
    }

    private void getData() {
        DBHelper db = new DBHelper(getActivity());
        list = db.getContactData();
        adapter = new SmsAdapter(getActivity(), list);
        smsRecycler.setAdapter(adapter);
    }
}