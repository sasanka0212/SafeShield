package com.example.safeshield;

import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContactFace {

    String name;
    String phoneNo;
    String email;

    public ContactFace(String name, String phoneNo, String email){
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
    }
}
