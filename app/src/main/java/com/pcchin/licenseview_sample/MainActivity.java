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

package com.pcchin.licenseview_sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pcchin.licenseview.LicenseType;
import com.pcchin.licenseview.LicenseView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // No need to set fragment manager as this activity is a subclass of FragmentActivity
        LicenseView licenseView = findViewById(R.id.license);
        // This one has no license
        licenseView.addLicense("Example 1");
        // This one has no headers
        licenseView.addLicense("Example 2", LicenseType.CDDL_1);
        // This one has custom headers
        licenseView.addLicense("Example 3", LicenseType.MOZILLA_PUBLIC_2, "<b>Bold</b> and <i>Italic</i>");
        // This one will overwrite existing license text
        // By default there is no space between header and license text
        licenseView.addLicense("Example 4", LicenseType.APACHE_2, "Custom Header",
                "Custom License Text");
        // String arrays with different lengths can work, as long as it is between 1 and 4
        licenseView.addLicense(new String[]{"Example 5", LicenseType.NONE});
        licenseView.addLicense(new String[]{"Example 6", "My Own License", "Copyright Some Guy",
                "<br/>Here is my own license text with a newline in front"});
        licenseView.addLicense(new String[]{"CustomDialog", LicenseType.APACHE_2,
                "Copyright 2020 <a href='https://github.com/pc-chin'>PC Chin</a>"});
        // The actual license
        licenseView.addLicense(new String[]{"LicenseView", LicenseType.APACHE_2,
                "Copyright 2020 <a href='https://github.com/pc-chin'>PC Chin</a>"});
    }
}
