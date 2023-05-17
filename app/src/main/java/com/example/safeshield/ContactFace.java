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

    String contactName;
    String phoneNo;

    public ContactFace(String contactName, String phoneNo){
        this.contactName = contactName;
        this.phoneNo = phoneNo;
    }
}
