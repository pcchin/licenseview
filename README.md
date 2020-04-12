# LicenseView
[![Download](https://api.bintray.com/packages/pcchin/licenseview/com.pcchin.licenseview/images/download.svg)](https://bintray.com/pcchin/licenseview/com.pcchin.licenseview/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-License%20View-green.svg?style=flat)](https://android-arsenal.com/details/1/8100)

## Library Info
This library is an extension of a LinearLayout that allows licenses to be displayed within its own Popup Views.
The default view would be blank and to add licences to it, you would need to use the addLicense or addMultipleLicenses functions.

Do note that the orientation of the view is automatically set to vertical.

Some licenses are included by default, such as:
- Apache 2.0
- MIT
- GNU GPL v3

and many more. The full list can be found under the [LicenseType](/licenseview/src/main/java/com/pcchin/licenseview/LicenseType.java) class.

## Installation
This library is available in JCenter. To install, you would need to include the following into your `project/build.gradle`:

```
implementation 'com.pcchin.licenseview:licenseview:1.1.0'
```

## Usage

This view can be used similar to a linear layout. To set up the licenseView, simply call `licenseView.addLicense` in your onCreate function. The possible arguments for `addLicense` include:

```
// Adds a license to the view. The license type is assumed to be None, and the license header and license text are assumed to be blank.
public void addLicense(String name)

// Adds a license to the view with a specified license type. The license header is assumed to be blank. The license text is assumed to be blank if none of the license types match that of any existing license types.
public void addLicense(String name, 
    @NotNull String licenseType)

// Adds a license to the view with a specified license type and license header. The license text is assumed to be blank if none of the license types match that of any existing license types.
// HTML tags will be parsed in the license header and the license text.
public void addLicense(String name, 
    @NotNull String licenseType, 
    @NotNull String licenseHeader)

// Adds a license to the view with a specified license type, license header and license text. The license text for any set licenses will be overwritten as well.
// HTML tags will be parsed in the license header and the license text.
public void addLicense(final String name, 
    @NotNull final String licenseType,
    @NotNull final String licenseHeader, 
    @NotNull final String licenseText)

// Adds a license to the view through an array. An IllegalArgumentException will be thrown if the array size does not match the argument size for any of the existing addLicense functions.
public void addLicense(@NotNull String[] license)
```

Multiple licenses could also be added at a time through the `addMultipleLicenses` function, taking a List of String arrays as an argument.

There are also setters for the following variables:

`setFragmentManager`: Set the Fragment manager if the view is initialized in an activity that does not have `getSupportFragmentManager()`.

`setTextSize`: Set the size of the text to be shown in the popup, defaults to 18.

`setPadding`: Sets the padding around the text in the popup, defaults to 20.

All the setters should be set before any `addLicense` functions are called.

For the layout XML, simply include the view as if it is an LinearLayout. e.g.:
```XML
<com.pcchin.licenseview.LicenseView
    android:width="match_parent"
    android:height="wrap_content"
/>
```

## Example Implementation
![](/example_implementation.png)

## Contribution
Any contribution is welcome, feel free to add any issues or pull requests to the repository.

## License
This library is licensed under the [Apache 2.0 License](/LICENSE).