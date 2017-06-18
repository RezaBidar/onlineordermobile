package ir.rezabidar.onlineorder.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ir.rezabidar.onlineorder.R;

/**
 * Created by ReZaBiDaR on 7/22/2016.
 */
public class AddCompanyDialogFragment extends DialogFragment {
    Activity activity ;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_addcompany, null) ;

        final EditText visitorId = (EditText) view.findViewById(R.id.visitorId);


        builder.setView(view)
                .setMessage(R.string.add_visitor_code)
                .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(!visitorId.getText().toString().isEmpty())
                            ((DialogCallback) activity).addCompanyCallback(Integer.parseInt(visitorId.getText().toString()));
                        else
                            getDialog().dismiss();

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
        // Create the AlertDialog object and return it
        return builder.create();

    }

    public interface DialogCallback {
        public void addCompanyCallback(int visitorId);
    }
}

