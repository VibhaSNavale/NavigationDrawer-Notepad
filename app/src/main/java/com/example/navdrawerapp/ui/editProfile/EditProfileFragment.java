package com.example.navdrawerapp.ui.editProfile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.navdrawerapp.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

    private EditText edtProfileName,edtProfileBio, edtProfileProfession, edtProfileHobbies, edtProfileContact;
    private Button btnEditInfo;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileContact = view.findViewById(R.id.edtProfileContact);

        btnEditInfo = view.findViewById(R.id.btnEditInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        //getting info from server
        if(parseUser.get("ProfileName") == null){
            edtProfileName.setText("");
        } else {
            edtProfileName.setText(parseUser.get("ProfileName").toString());
        }

        if(parseUser.get("ProfileBio") == null){
            edtProfileBio.setText("");
        } else {
            edtProfileBio.setText(parseUser.get("ProfileBio").toString());
        }

        if(parseUser.get("ProfileProfession") == null){
            edtProfileProfession.setText("");
        } else {
            edtProfileProfession.setText(parseUser.get("ProfileProfession").toString());
        }

        if(parseUser.get("ProfileHobbies") == null){
            edtProfileHobbies.setText("");
        } else {
            edtProfileHobbies.setText(parseUser.get("ProfileHobbies").toString());
        }

        if(parseUser.get("ProfileContact") == null){
            edtProfileContact.setText("");
        } else {
            edtProfileContact.setText(parseUser.get("ProfileContact").toString());
        }

        btnEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseUser.put("ProfileName",edtProfileName.getText().toString());
                parseUser.put("ProfileBio",edtProfileBio.getText().toString());
                parseUser.put("ProfileProfession",edtProfileProfession.getText().toString());
                parseUser.put("ProfileHobbies",edtProfileHobbies.getText().toString());

                if (edtProfileName.getText().toString().equals("") || edtProfileBio.getText().toString().equals("") || edtProfileProfession.getText().toString().equals("") || edtProfileHobbies.getText().toString().equals("") || edtProfileContact.getText().toString().equals(""))
                {
                    FancyToast.makeText(getContext(), "All fields are required.",
                            Toast.LENGTH_LONG, FancyToast.INFO, false).show();
                }
                else if(edtProfileContact.getText().toString().length()!=10) {
                    FancyToast.makeText(getContext(), "Please make sure the contact number you have entered is of 10 digits.",
                            Toast.LENGTH_LONG, FancyToast.INFO, false).show();
                }
                else {
                    parseUser.put("ProfileContact", edtProfileContact.getText().toString());
                    parseUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {

                            final ProgressDialog progressDialog = new ProgressDialog(getContext());
                            progressDialog.setMessage("Updating info");
                            progressDialog.show();

                            if (e == null) {
                                FancyToast.makeText(getContext(), "Info updated.", //getContext() is used in fragments
                                        Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                            } else {
                                FancyToast.makeText(getContext(), e.getMessage(),
                                        Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                            }

                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });

        return view;
    }

}
