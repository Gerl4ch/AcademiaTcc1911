package com.example.academiatcc;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomePersonalFragment extends Fragment {

    TextView nomePersonal;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        nomePersonal = view.findViewById(R.id.txtBemVindo);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        FirebaseUser usuario = auth.getCurrentUser();
        if (usuario != null) {
            String userId = usuario.getUid();
            DocumentReference userRef = firestore.collection("Personais").document(userId);
            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot personalDoc = task.getResult();
                        if (personalDoc.exists()) {
                            String userName = personalDoc.getString("nome");
                            if (userName != null) {
                                String text = "Bem vindo(a), " + userName + "!";
                                SpannableString spannable = new SpannableString(text);

                                int startIndex = text.indexOf(userName);
                                int endIndex = startIndex + userName.length();
                                int color = Color.parseColor("#BF0426");
                                spannable.setSpan(new ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                spannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                nomePersonal.setText(spannable);
                            } else {
                                nomePersonal.setText("Bem vindo!");
                            }
                        }
                    }
                }
            });
        }
        return view;
    }
}