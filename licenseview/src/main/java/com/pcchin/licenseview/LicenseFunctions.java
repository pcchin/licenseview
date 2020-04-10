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

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

final class LicenseFunctions {
    /** Returns the license text associated with the license type from a .txt file from the assets.
     * The title of the .txt file is the license type (all lowercase),
     * with all the non alpha-numeric characters replaced with underscores.
     * @return the associated license, or an empty string if the license type could not be found. **/
    @NotNull
    static String getLicense (@NotNull String licenseType, @NotNull Context context) {
        try {
            String text;
            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = context.getAssets().open(
                    licenseType.toLowerCase().replaceAll(
                            "[^a-zA-Z\\d:]", "_") + ".txt");
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while ((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            // File does not exist
            return "";
        }
    }

    /** Inserts a HTML text into a TextView. **/
    static void setHtml(TextView view, String htmlText) {
        Spanned output;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            output = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY);
        } else {
            output = Html.fromHtml(htmlText);
        }
        view.setText(output);
        view.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
