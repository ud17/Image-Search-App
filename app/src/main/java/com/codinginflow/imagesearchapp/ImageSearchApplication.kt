package com.codinginflow.imagesearchapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//basically initializing Hilt with the help of this annotation and this step is mandatory. Also declare the name variable in the manifest file and mention this file name for this
//to be used...
@HiltAndroidApp
class ImageSearchApplication : Application() {
}