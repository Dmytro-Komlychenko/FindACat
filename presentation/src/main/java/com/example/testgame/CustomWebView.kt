package com.example.testgame

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.*
import android.webkit.WebView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("SetJavaScriptEnabled")
class CustomWebView(
    private val webView: WebView,
    url: String,
    context: Context,
    private val uploadingImageResult: ActivityResultLauncher<Intent>
) {

    var fString: ValueCallback<Array<Uri>>? = null
    var camPath: String? = null
    lateinit var chooserIntent: Intent

    init {
        setSettings()
        initWebViewClient()
        initWebChromeClient(context)
        loadUrl(url)
    }

    fun loadUrl(url: String) {
        webView.loadUrl(url)
    }

    fun observeResult(result: ActivityResult) {
        var results: Array<Uri>? = null
        if (result.resultCode == Activity.RESULT_CANCELED) {
            fString?.onReceiveValue(null)
            return
        }
        if (result.resultCode == Activity.RESULT_OK) {
            if (fString == null) return

            var clipData: ClipData?
            var stringData: String?
            try {
                clipData = result.data?.clipData
                stringData = result.data?.dataString
            } catch (ex: java.lang.Exception) {
                clipData = null
                stringData = null
            }

            if (clipData == null && stringData == null && camPath != null) {
                results = arrayOf(Uri.parse(camPath))
            } else {
                if (clipData != null) {
                    val numSelectedFiles = clipData.itemCount
                    results = arrayOf()
                    for (i in 0 until numSelectedFiles) {
                        results[i] = clipData.getItemAt(i).uri
                    }
                }
                results = arrayOf(Uri.parse(stringData))
            }
        }
        fString?.onReceiveValue(results)
        fString = null
    }

    private fun setSettings() {
        webView.settings.apply {
            javaScriptEnabled = true
            allowFileAccess = true
            allowContentAccess = true


            useWideViewPort = false
            domStorageEnabled = true

        }
    }

    private fun initWebViewClient() {
        webView.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }

        }
    }

    private fun initWebChromeClient(context: Context) {
        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                fString = filePathCallback

                var takePictureIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (takePictureIntent?.resolveActivity(context.packageManager) != null) {
                    var photoFile: File? = null
                    try {
                        photoFile = createImage(context)
                        takePictureIntent.putExtra("PhotoPath", camPath)
                    } catch (e: IOException) {
                        Log.e(TAG, "Image file creation failed\n ${e.message}")
                    }
                    if (photoFile != null) {
                        camPath = "file:" + photoFile.absolutePath
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile))
                    } else {
                        takePictureIntent = null
                    }
                }
                val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
                contentSelectionIntent.apply {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "*/*"
                }
                val intentArray: ArrayList<Intent?> = arrayListOf(takePictureIntent)
                chooserIntent = Intent(Intent.ACTION_CHOOSER).apply {
                    putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
                    putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)
                }
                uploadingImageResult.launch(chooserIntent)
                return true
            }
        }
    }

    @Throws(IOException::class)
    private fun createImage(context: Context): File? {
        @SuppressLint("SimpleDateFormat")
        val fileName = SimpleDateFormat("yyyy-mm-ss".format(Date()))
        val newName = "file_${fileName}_"
        val sdDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(newName, ".jpg", sdDirectory)
    }

    companion object {
        const val TAG = "CustomWebView"
    }
}



























