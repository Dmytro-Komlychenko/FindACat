package com.example.findacat.ui.fragments.web

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SetJavaScriptEnabled")
class CustomWebView(
    context: Context,
    private val webView: WebView,
    private val uploadingImageResult: ActivityResultLauncher<Intent>,
) {

    private var fString: ValueCallback<Array<Uri>>? = null
    private var camPath: String? = null
    private lateinit var chooserIntent: Intent

    init {
        setSettings()
        initWebViewClient()
        initWebChromeClient(context)
        setDownloadListener(context)
    }


    fun loadUrl(url: String) {
        webView.loadUrl(url)
    }

    private fun setSettings() {
        webView.settings.apply {
            javaScriptEnabled = true
            allowFileAccess = true
            allowContentAccess = true
            useWideViewPort = false
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_DEFAULT
        }
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
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

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }
        }
    }

    private fun setDownloadListener(context: Context) {
        webView.setDownloadListener { url, _, _, _, l ->
            context.startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
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