package com.example.navdrawerapp.ui.notes;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.navdrawerapp.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class NotesFragment extends Fragment {

    private EditText edtNotes;
    Button btnSaveNote;

    public NotesFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        edtNotes = view.findViewById(R.id.edtNotes);
        btnSaveNote = view.findViewById(R.id.btnSaveNote);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if(parseUser.get("Notes") == null){
            edtNotes.setText("");
        } else {
            edtNotes.setText(parseUser.get("Notes").toString());
        }

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseUser.put("Notes", edtNotes.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        final ProgressDialog progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("Saving note...");
                        progressDialog.show();

                        if (e == null) {
                            FancyToast.makeText(getContext(), "Note saved.",
                                    Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                        } else {
                            FancyToast.makeText(getContext(), e.getMessage(),
                                    Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }

                        progressDialog.dismiss();
                    }
                });

            }
        });

        return view;
    }
}
