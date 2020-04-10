/*
 * Copyright 2020 PC Chin.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pcchin.licenseview;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

/** An AlertDialog that would br dismissed when rotated.
 * This DialogFragment can only be accessed through
 * @see LicenseView and should not be accessed outside it. **/
public final class AutoDismissDialog extends DialogFragment {
    private String title;
    private View displayView;

    /** Default constructor, used only when the app is rotated. **/
    public AutoDismissDialog() {
    }

    /** Constructor used for showing a view with auto dismiss after a button press.
     * @param title is the title of the AlertDialog.
     * @param displayView is the view that would be displayed in the AlertDialog. **/
    AutoDismissDialog(String title, View displayView) {
        this.title = title;
        this.displayView = displayView;
    }

    /** Dismiss the dialog if the last one is still showing. **/
    @Override
    public void onStart() {
        if (getDialog() != null && getDialog().isShowing()) {
            getDialog().dismiss();
        }
        super.onStart();
    }

    /** Creates the actual AlertDialog based on the given parameters.
     * A NullPointerException is thrown if the context is null. **/
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getContext() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                    .setTitle(title);
            builder.setView(displayView);
            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            // Sets the dialog listeners if autoDismiss is false
            return builder.create();
        } else {
            throw new NullPointerException("Context for AutoDismissDialog is null");
        }
    }
}
