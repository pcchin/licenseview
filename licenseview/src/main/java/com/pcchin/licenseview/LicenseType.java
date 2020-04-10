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

/** The possible types of license being displayed. The list of licenses here are based on
 * the Open Source Initiative at https://opensource.org/licenses/category which only popular
 * licenses and certain miscellaneous licenses are included. **/
public final class LicenseType {
    public static final String NONE = "None";

    // Common licenses
    public static final String APACHE_2 = "Apache License 2.0";
    public static final String MIT = "MIT License";
    public static final String MOZILLA_PUBLIC_2 = "Mozilla Public License 2.0";
    public static final String CDDL_1 = "Common Development and Distribution License 1.0";
    public static final String EPL_2 = "Eclipse Public License version 2.0";
    
    // BSD Licenses
    public static final String BSD_0 = "Zero-Clause BSD / Free Public License 1.0.0";
    public static final String BSD_1 = "1-clause BSD License";
    public static final String BSD_2 = "2-clause BSD License";
    public static final String BSD_3 = "3-clause BSD License";

    // GNU Licenses
    public static final String GNU_AGPL_V3 = "GNU Affero General Public License Version 3";
    public static final String GNU_GPL_V3 = "GNU General Public License Version 3";
    public static final String GNU_LGPL_V3 = "GNU Lesser General Public License Version 3";

    // Misc Licenses
    public static final String BOOST_SOFTWARE_1 = "Boost Software License 1.0";
    public static final String ISC = "ISC License";
    public static final String NPOSL_3 = "The Non-Profit Open Software License version 3.0";
    public static final String OSL_3 = "The Open Software License 3.0";
    public static final String SIMPL_2 = "Simple Public License";
    public static final String UNLICENSE = "The Unlicense";
    public static final String UPL_1 = "The Universal Permissive License (UPL), Version 1.0";
}
