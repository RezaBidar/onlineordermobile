package ir.rezabidar.onlineorder;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.test.ActivityTestCase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import ir.rezabidar.onlineorder.models.ProductModel;

/**
 * Created by ReZaBiDaR on 3/29/2016.
 */
public class FireMissilesDialogFragment extends DialogFragment {
    Activity activity ;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
//        String pic = args.getString("pic");

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_signin, null) ;

        final EditText counter = (EditText) view.findViewById(R.id.counter);

//        ImageView image = (ImageView) view.findViewById(R.id.pic);
//        int resId = activity.getResources().getIdentifier(pic , "drawable", activity.getPackageName()) ;
//        image.setImageResource(resId);

        builder.setView(view)
                .setMessage(R.string.dialog_fire_missiles)
                .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((DialogCallback) activity).fireMissilesCallback(Integer.parseInt(counter.getText().toString()));
                        Toast.makeText(activity, "positive ", Toast.LENGTH_LONG).show();

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(activity, "Clicked ", Toast.LENGTH_LONG).show();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public interface DialogCallback {
        public void fireMissilesCallback(int count);
    }
}
