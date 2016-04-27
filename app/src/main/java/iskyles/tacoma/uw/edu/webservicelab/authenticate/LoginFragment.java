package iskyles.tacoma.uw.edu.webservicelab.authenticate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.text.TextUtilsCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import iskyles.tacoma.uw.edu.webservicelab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login,container,false);
        final EditText userIdText = (EditText) v.findViewById(R.id.userIdEditText);
        final EditText pwdText = (EditText) v.findViewById(R.id.userPasswordEditText);
        Button signInButton = (Button) v.findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = userIdText.getText().toString();
                String pwd = pwdText.getText().toString();
                //make sure user id is filled out
                if(TextUtils.isEmpty(userId)) {
                    Toast.makeText(v.getContext(),"Enter userid",Toast.LENGTH_SHORT).show();
                    userIdText.requestFocus();
                    return;
                }
                //make sure user id contains @
                if(!userId.contains("@")) {
                    Toast.makeText(v.getContext(), "Enter a valid email address",
                            Toast.LENGTH_SHORT).show();
                    userIdText.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(pwd)) {
                    Toast.makeText(v.getContext(),"Enter password",Toast.LENGTH_SHORT).show();
                    pwdText.requestFocus();
                    return;
                }
                if(pwd.length() < 6) {
                    Toast.makeText(v.getContext(),"Enter password of at least 6 characters", Toast.LENGTH_LONG).show();
                    pwdText.requestFocus();
                    return;
                }
                ((SignInActivity) getActivity()).login(userId,pwd);
            }
        });
        return v;
    }

    public interface LoginInteractionListener {
        public void login(String userId, String pwd);
    }
}
