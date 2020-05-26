package com.example.navdrawerapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.navdrawerapp.R;
import com.parse.ParseQuery;
import com.parse.ParseUser;

//public class HomeFragment extends Fragment {
//
//    private HomeViewModel homeViewModel;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
//    }
//}

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileViewFragment extends Fragment {

    public ProfileViewFragment() {
        // Required empty public constructor
    }

    TextView txtProfileName, txtProfileBio, txtProfileProfession, txtProfileHobbies, txtProfileContact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txtProfileName = view.findViewById(R.id.txtProfileName);
        txtProfileBio = view.findViewById(R.id.txtProfileBio);
        txtProfileProfession = view.findViewById(R.id.txtProfileProfession);
        txtProfileHobbies = view.findViewById(R.id.txtProfileHobbies);
        txtProfileContact = view.findViewById(R.id.txtProfileContact);
try {


    final ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();

    parseQuery.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
    final ParseUser parseUser = ParseUser.getCurrentUser();

    txtProfileName.setText(parseUser.get("ProfileName").toString());
    txtProfileBio.setText(parseUser.get("ProfileBio").toString());
    txtProfileProfession.setText(parseUser.get("ProfileProfession").toString());
    txtProfileHobbies.setText(parseUser.get("ProfileHobbies").toString());
    txtProfileContact.setText(parseUser.get("ProfileContact").toString());
}
 catch (Exception e) {
     txtProfileName.setText("No name entered.");
     txtProfileBio.setText("No bio entered.");
     txtProfileProfession.setText("No profession entered.");
     txtProfileHobbies.setText("No hobbies entered.");
     txtProfileContact.setText("No contact number entered.");
 }
        return view;
    }

}
