package co.com.cesarnorena.pokedex.controller.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import static android.support.v7.app.AlertDialog.Builder;

/**
 * Created by Cesar Norena on 16/01/2016.
 */
public class CustomAlertDialog extends android.support.v4.app.DialogFragment {

    private static final String TITLE = "title";
    private static final String MESSAGE = "message";
    private static final String ACCEPT = "accept";

    private OnDismissListener listener;


    public static CustomAlertDialog create(String title, String message, String accept,
                                           OnDismissListener listener) {
        CustomAlertDialog dialog = new CustomAlertDialog();
        dialog.setListener(listener);

        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(MESSAGE, message);
        args.putString(ACCEPT, accept);

        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle args = getArguments();
        String title = args.getString(TITLE, null);
        String message = args.getString(MESSAGE, null);
        String accept = args.getString(ACCEPT, null);

        Builder builder = new Builder(getActivity());

        if (title != null)
            builder.setTitle(title);

        if (message != null)
            builder.setMessage(message);

        if (accept != null)
            builder.setPositiveButton(accept, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            });

        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (listener != null)
            listener.onDismiss();
    }

    public void setListener(OnDismissListener listener) {
        this.listener = listener;
    }

    public interface OnDismissListener {
        void onDismiss();
    }
}
