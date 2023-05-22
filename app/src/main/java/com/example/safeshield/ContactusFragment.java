package com.example.safeshield;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactusFragment extends Fragment {

    //views to be used
    EditText contactEdtProblem, contactEdtFeedback;
    CardView contactBtnSend;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactusFragment newInstance(String param1, String param2) {
        ContactusFragment fragment = new ContactusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // use only if any parameters are needed...
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contactus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactEdtProblem = view.findViewById(R.id.contactEdtProblem);
        contactEdtFeedback = view.findViewById(R.id.contactEdtFeedback);
        contactBtnSend = view.findViewById(R.id.contactBtnSend);

        contactBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email[] = {"sasankak2002@gmail.com", "snigdhachatterjee100@gmail.com"};
                String problem = contactEdtProblem.getText().toString();
                String feedback = contactEdtFeedback.getText().toString();
                if(problem.equals("") && feedback.equals("")){
                    Toast.makeText(getActivity(), "Fill up all details first", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent sendMail = new Intent(Intent.ACTION_SENDTO);
                    sendMail.setData(Uri.parse("mailto:"));
                    sendMail.putExtra(Intent.EXTRA_EMAIL, email);
                    sendMail.putExtra(Intent.EXTRA_SUBJECT, feedback);
                    sendMail.putExtra(Intent.EXTRA_TEXT, problem);
                    startActivity(Intent.createChooser(sendMail, "Mail via"));
                }
            }
        });
    }
}