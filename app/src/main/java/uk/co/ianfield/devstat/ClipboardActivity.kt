package uk.co.ianfield.devstat

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import java.util.ArrayList

import uk.co.ianfield.devstat.model.StatItem

/**
 * Created by ianfield on 29/01/2017.
 */

class ClipboardActivity : AppCompatActivity() {

    @SuppressLint("NewApi") // Always going to be 25+
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val statHelper = StatHelper(this)
        var clipboardContents = ""
        var list: ArrayList<StatItem>

        list = statHelper.hardwareList
        clipboardContents += String.format("%s\n", getString(R.string.title_hardware))
        for (item in list) {
            clipboardContents += item.toString()
            clipboardContents += "\n"
        }
        list = statHelper.screenList
        clipboardContents += String.format("\n%s\n", getString(R.string.title_screen_metrics))
        for (item in list) {
            clipboardContents += item.toString()
            clipboardContents += "\n"
        }
        list = statHelper.softwareList
        clipboardContents += String.format("\n%s\n", getString(R.string.title_software))
        for (item in list) {
            clipboardContents += item.toString()
            clipboardContents += "\n"
        }
        list = statHelper.featureList
        clipboardContents += String.format("\n%s\n", getString(R.string.title_crypto))
        for (item in list) {
            clipboardContents += item.title
            clipboardContents += ":\n"
            clipboardContents += item.info
            clipboardContents += "\n"
        }

        list = statHelper.cryptoList
        clipboardContents += String.format("\n%s\n", getString(R.string.title_crypto))
        for (item in list) {
            clipboardContents += item.title
            clipboardContents += ":\n"
            clipboardContents += item.info
            clipboardContents += "\n"
        }

        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("text label", clipboardContents)
        clipboard.primaryClip = clip

        Toast.makeText(this, R.string.copied_to_clipboard, Toast.LENGTH_SHORT).show()
        finish()
    }
}
