package co.com.cesarnorena.pokedex.views;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import co.com.cesarnorena.pokedex.R;

import static android.support.v7.app.AlertDialog.Builder;

public class CustomAlertDialog extends DialogFragment {

    private static final String TITLE_ID = "title";
    private static final String MESSAGE_ID = "message";

    private OnDismissListener listener;

    public static CustomAlertDialog create(String title, String message,
                                           OnDismissListener listener) {
        CustomAlertDialog dialog = new CustomAlertDialog();
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);
        dialog.setListener(listener);

        Bundle args = new Bundle();
        args.putString(TITLE_ID, title);
        args.putString(MESSAGE_ID, message);

        dialog.setArguments(args);
        return dialog;
    }

    public static CustomAlertDialog create(String message,
                                           OnDismissListener listener) {
        CustomAlertDialog dialog = new CustomAlertDialog();
        dialog.setListener(listener);

        Bundle args = new Bundle();
        args.putString(MESSAGE_ID, message);

        dialog.setArguments(args);
        return dialog;
    }

    public static CustomAlertDialog create(String title, String message) {
        CustomAlertDialog dialog = new CustomAlertDialog();

        Bundle args = new Bundle();
        args.putString(TITLE_ID, title);
        args.putString(MESSAGE_ID, message);

        dialog.setArguments(args);
        return dialog;
    }

    public static CustomAlertDialog create(String message) {
        CustomAlertDialog dialog = new CustomAlertDialog();

        Bundle args = new Bundle();
        args.putString(MESSAGE_ID, message);

        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle args = getArguments();
        String title = args.getString(TITLE_ID, null);
        String message = args.getString(MESSAGE_ID, null);

        Builder builder = new Builder(getActivity());
        if (title != null)
            builder.setTitle(title);

        builder.setMessage(message);

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
