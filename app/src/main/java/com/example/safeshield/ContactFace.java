package com.example.safeshield;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
