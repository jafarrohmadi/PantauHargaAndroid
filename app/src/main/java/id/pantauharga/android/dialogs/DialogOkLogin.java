package id.pantauharga.android.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import id.pantauharga.android.R;
import id.pantauharga.android.aktivitas.LoginRegistersPengguna;

/**
 * Created by Gulajava Ministudio on 11/9/15.
 */
public class DialogOkLogin extends DialogFragment {


    private LoginRegistersPengguna mLoginRegistersPengguna;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        mLoginRegistersPengguna = (LoginRegistersPengguna) DialogOkLogin.this.getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(DialogOkLogin.this.getActivity());
        builder.setMessage(R.string.oklogin_masuk);
        builder.setPositiveButton(R.string.teks_ok, listenerok);

        Dialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        return dialog;
    }


    DialogInterface.OnClickListener listenerok = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            mLoginRegistersPengguna.setOkLogin();

            DialogOkLogin.this.dismiss();
        }
    };


}
