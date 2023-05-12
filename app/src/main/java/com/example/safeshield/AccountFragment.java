package com.example.safeshield;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView accountName, accountPhone, accountEmail, accountUserid, accountAddress;
    Button accountlogout, updateAccountBtn;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        accountlogout = view.findViewById(R.id.accountLogout);
        accountName = view.findViewById(R.id.accountName);
        accountPhone = view.findViewById(R.id.accountPhone);
        accountEmail = view.findViewById(R.id.accountEmail);
        accountUserid = view.findViewById(R.id.accountUserid);
        accountAddress = view.findViewById(R.id.accountAddress);
        updateAccountBtn = view.findViewById(R.id.updateAccountBtn);

        DBHelper db = new DBHelper(getActivity());
        User list = db.getUser();
        accountName.setText(list.name);
        accountPhone.setText(list.phone);
        accountUserid.setText(list.id);
        accountEmail.setText(list.email);
        accountAddress.setText(list.address);

        accountlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("flag", false);
                editor.apply();
                Intent iNext = new Intent(getActivity(), loginActivity.class);
                getActivity().startActivity(iNext);
            }
        });

        updateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UpdateAccountActivity.class);
                DBHelper db = new DBHelper(getActivity());
                User list = db.getUser();
                intent.putExtra("name", list.name);
                intent.putExtra("phone", list.phone);
                intent.putExtra("id", list.id);
                intent.putExtra("email", list.email);
                intent.putExtra("pass", list.pass);
                intent.putExtra("address", list.address);
                startActivity(intent);
            }
        });
    }
}