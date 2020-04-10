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

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class LicenseViewTest {
    private Context appContext;

    @Before
    public void useAppContext() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void checkAssets() throws IOException, IllegalAccessException, NullPointerException {
        // Get all string values
        Field[] fields= LicenseType.class.getDeclaredFields();
        for(Field field:fields){
            if(field.getType().equals(String.class) &&
                    !Objects.requireNonNull(field.get(null)).equals(LicenseType.NONE)){
                System.out.println("checkAssets: "+field.getName());
                field.setAccessible(true);
                // Check if the asset exists
                appContext.getAssets().open(((String) Objects.requireNonNull(field.get(null)))
                        .toLowerCase().replaceAll(
                        "[^a-zA-Z\\d:]", "_") + ".txt");
            }
        }
    }

    @Test(expected = IOException.class)
    public void checkNonexistentAsset() throws IOException, IllegalAccessException {
        // Get all string values
        Field[] fields= LicenseType.class.getDeclaredFields();
        for(Field field:fields){
            if(field.getType().equals(String.class)){
                System.out.println("checkNonexistentAsset: "+field.getName());
                field.setAccessible(true);
                // Check if the asset exists
                appContext.getAssets().open(((String) Objects.requireNonNull(field.get(null)))
                        .toLowerCase().replaceAll(
                                "[^a-zA-Z\\d:]", "_") + ".txt");
            }
        }
    }

    @Test(expected = IOException.class)
    public void checkFakeAsset() throws IOException {
        System.out.println("checkFakeAsset");
        appContext.getAssets().open("fake.txt");
    }
}
