Blaze
--------
Provide view for Moving Image and Zooming Image.
Easy to create continuosly moving background.

View Available
--------
#### Zoom View

![gif](Screenshots/zoom_view.gif)

-------

#### Motion View
![gif](Screenshots/motion_view.gif)


Download
--------
Grab via Gradle:
```groovy
compile 'com.intrusoft.library:blaze:1.0.0'
```

Implementation
-----------
### Motion View

#### XML Implementation:
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:blaze="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.intrusoft.blaze.app.MainActivity">

    <com.intrusoft.blaze.MotionView
        android:id="@+id/motion_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        blaze:src="@drawable/your_image"
        blaze:translation_factor="3" />
</FrameLayout>
```
```xml
blaze:translation_factor
<!-- defines the animation speed-->
```

#### Java Implementation:
```java
MotionView motionView = (MotionView) findViewById(R.id.motion_view);

// to set image from resources
motionView.setImageResource(R.drawable.your_image);

// to set bitmap
motionView.setImageBitmap(yourBitmap);

// to set the animation speed
motionView.setTranslationFactor(4);
```

### Zoom View

#### XML Implementation:
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:blaze="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.intrusoft.blaze.app.MainActivity">

    <com.intrusoft.blaze.ZoomView
        android:id="@+id/zoom_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        blaze:src="@drawable/place_holder"
        blaze:translation_factor="0.4" />
</FrameLayout>
```

```xml
blaze:translation_factor
<!-- defines the animation speed-->
```

#### Java Implementation:
```java
ZoomView zoomView = (MotionView) findViewById(R.id.motion_view);

// to set image from resources
zoomView.setImageResource(R.drawable.your_image);

// to set bitmap
zoomView.setImageBitmap(yourBitmap);

// to set the animation speed
zoomView.setTranslationFactor(0.4f);
```

Licence
--------

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
