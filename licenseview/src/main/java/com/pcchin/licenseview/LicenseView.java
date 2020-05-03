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

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.pcchin.customdialog.DefaultDialogFragment;

import java.util.List;
import java.util.Objects;

/** An extension of a LinearLayout that allows licenses to be displayed within its own Popup Views.
 * The default view would be blank and to add licences to it, you would need to use the
 * addLicense or addMultipleLicenses functions.
 * Do note that the orientation of the view can only be vertical.**/
public class LicenseView extends LinearLayout {
    // TextView attributes
    private int textSize = 18;
    private int paddingSize = 20;
    private Typeface typeface;
    private int typefaceStyle = Typeface.NORMAL;
    private int textColor = Color.TRANSPARENT;
    private int linkColor = Color.TRANSPARENT;

    // Other attributes
    private int alertDialogStyle = R.style.Theme_AppCompat_Light_Dialog_Alert;
    private FragmentManager fragmentManager;

    //****** CONSTRUCTORS ******//

    public LicenseView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
    }

    public LicenseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
    }

    public LicenseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
    }

    //****** SETTERS ******//

    /** Sets the default text size to be shown in the popup.
     * This method will override the text size set within setTextAppearance.
     * This method should be called before any licenses are added. **/
    public void setTextSize(int size) {
        textSize = size;
    }

    /** Sets the typeface of the text in the popup.
     * This method should be called before any licenses are added. **/
    public void setTypeface(Typeface typeface) {
        setTypeface(typeface, Typeface.NORMAL);
    }

    /** Sets the typeface of the text in the popup with style included.
     * This method should be called before any licenses are added. **/
    public void setTypeface(Typeface typeface, int style) {
        this.typeface = typeface;
        typefaceStyle = style;
    }

    /** Sets the default text color to be shown in the popup.
     * This method should be called before any licenses are added. **/
    public void setTextColor(int color) {
        textColor = color;
    }

    /** Sets the default link color to be shown in the popup.
     * This method should be called before any licenses are added. **/
    public void setLinkColor(int color) {
        linkColor = color;
    }

    /** Sets the size of the padding for the popup.
     * This method should be called before any licenses are added. **/
    public void setPadding(int size) {
        paddingSize = size;
    }

    /** Sets the style of the AlertDialog shown.
     * This method should be called before any licenses are made. **/
    public void setAlertDialogStyle(int style) {
        alertDialogStyle = style;
    }

    /** Sets the fragment manager used to display the popups.
     * If it is not specified, the fragment manager will be derived from the context,
     * which assumes that it is a FragmentActivity. **/
    public void setFragmentManager(FragmentManager manager) {
        fragmentManager = manager;
    }

    //****** ADD SINGLE LICENSE ******//

    /** Adds a license to the view. The license type is assumed to be None, and the
     * license header and text are assumed to be blank. **/
    public void addLicense(String name) {
        addLicense(name, LicenseType.NONE);
    }

    /** Adds a license to the view with a specified license type.
     * The license header is assumed to be blank. The license text is assumed to be blank
     * if none of the license types match that of any existing license types.**/
    public void addLicense(String name, String licenseType) {
        addLicense(name, licenseType, "");
    }

    /** Adds a license to the view with a specified license type and license header.
     * The license text is assumed to be blank if none of the license types match that of
     * any existing license types.
     *
     * HTML tags will be parsed in the license header and the license text. **/
    public void addLicense(String name, String licenseType, String licenseHeader) {
        addLicense(name, licenseType, licenseHeader, LicenseFunctions.getLicense(licenseType, getContext()));
    }

    /** Adds a license to the view with a specified license type, license header and license text.
     * The license text for any set licenses will be overwritten as well.
     *
     * HTML tags will be parsed in the license header and the license text. **/
    public void addLicense(final String name, final String licenseType,
                           final String licenseHeader, final String licenseText) {
        // The main function for adding a license to the view.
        @SuppressLint("InflateParams") LinearLayout licenseDisplay = (LinearLayout)
                ((LayoutInflater) Objects.requireNonNull(getContext().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE)))
                        .inflate(R.layout.com_pcchin_licenseview_ubei678ejq58i89v7qep, null);
        ((TextView) licenseDisplay.findViewById(R.id.ld_lib)).setText(name);
        ((TextView) licenseDisplay.findViewById(R.id.ld_type)).setText(licenseType);
        licenseDisplay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Copies the properties of the default TextView over
                TextView textView = new TextView(getContext());
                textView.setTextAppearance(getContext(), appearance);
                textView.setTextSize(textSize);
                textView.setPadding(paddingSize, paddingSize, paddingSize, paddingSize);
                textView.setTypeface(typeface, typefaceStyle);
                if (textColor != Color.TRANSPARENT) {
                    textView.setTextColor(textColor);
                }
                if (linkColor != Color.TRANSPARENT) {
                    textView.setLinkTextColor(linkColor);
                }
                // Display dialog fragment
                LicenseFunctions.setHtml(textView, licenseHeader + licenseText);
                new DefaultDialogFragment(new AlertDialog.Builder(new ContextThemeWrapper(getContext(), alertDialogStyle))
                        .setTitle(name)
                        .setView(textView)
                        .setPositiveButton("Close", null)
                        .create(),
                        fragmentManager, "LicenseView." + name).show();
            }
        });
        addView(licenseDisplay);
    }

    /** Adds a license to the view through an array.
     * @throws IllegalArgumentException will be thrown if the array size does not match the argument
     * size for any of the existing addLicense functions. **/
    public void addLicense(String[] license) {
        if (license.length == 1) {
            addLicense(license[0]);
        } else if (license.length == 2) {
            addLicense(license[0], license[1]);
        } else if (license.length == 3) {
            addLicense(license[0], license[1], license[2]);
        } else if (license.length == 4) {
            addLicense(license[0], license[1], license[2], license[3]);
        } else {
            throw new IllegalArgumentException("Size of license provided invalid, expected " +
                    "between 1 & 4 but got " + license.length);
        }
    }

    //****** ADD MULTIPLE LICENSES ******//

    /** Adds multiple licenses to the view through a list of String arrays.
     * @throws IllegalArgumentException will be thrown if any of the array size does not match the argument
     * size for any of the existing addLicense functions. **/
    public void addMultipleLicenses(List<String[]> licensesAdded) {
        for (String[] license: licensesAdded) {
            addLicense(license);
        }
    }
}
