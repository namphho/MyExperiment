package com.example.myexperiment.experiments.contentprovider

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.provider.ContactsContract
import android.provider.UserDictionary
import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * Created by nampham on 5/30/21.
 */
class ContentProviderModel : ViewModel() {
    companion object {
        private val TAG = ContentProviderModel::class.java.simpleName
    }

    // A "projection" defines the columns that will be returned for each row
    private val mProjection: Array<String> = arrayOf(
        ContactsContract.Contacts.NAME_RAW_CONTACT_ID,    // Contract class constant for the _ID column name
        ContactsContract.Contacts.DISPLAY_NAME,   // Contract class constant for the word column name
        ContactsContract.Contacts.HAS_PHONE_NUMBER  // Contract class constant for the locale column name
    )

    // Defines a string to contain the selection clause
    private var selectionClause: String? = null

    // Declares an array to contain selection arguments
    private lateinit var selectionArgs: Array<String>




    @SuppressLint("Recycle")
    fun getData(contentResolver: ContentResolver, searchWord: String) {
        // Gets a word from the UI
        val searchString = searchWord

        // Remember to insert code here to check for invalid or malicious input.

        // If the word is the empty string, gets everything
        selectionArgs = searchString.takeIf { it.isNotEmpty() }?.let {
            selectionClause = "${ContactsContract.Contacts.DISPLAY_NAME} LIKE ?"
            arrayOf("%$it%")
        } ?: run {
            selectionClause = null
            emptyArray<String>()
        }

        // Does a query against the table and returns a Cursor object
        val mCursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,  // The content URI of the words table
            mProjection,                       // The columns to return for each row
            selectionClause,                  // Either null, or the word the user entered
            selectionArgs,                    // Either empty, or the string the user entered
            "${ContactsContract.Contacts.DISPLAY_NAME} ASC"                         // The sort order for the returned rows
        )

        // Some providers return null if an error occurs, others throw an exception
        when (mCursor?.count) {
            null -> {
                /*
                 * Insert code here to handle the error. Be sure not to use the cursor!
                 * You may want to call android.util.Log.e() to log this error.
                 *
                 */
                Log.e(TAG, ">>>>>  nulllll")
            }
            0 -> {
                /*
                 * Insert code here to notify the user that the search was unsuccessful. This isn't
                 * necessarily an error. You may want to offer the user the option to insert a new
                 * row, or re-type the search term.
                 */
                Log.e(TAG, ">>>>>  empty")
            }
            else -> {
                // Insert code here to do something with the results
                Log.e(TAG, ">>>>>  has count")
                //todo show data
            }
        }
    }
}