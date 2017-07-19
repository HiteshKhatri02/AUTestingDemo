package com.example.ranosys.autestingdemo.login

import android.app.LoaderManager
import android.database.Cursor

/**
 * @author Hitesh Khatri
 */
interface LoginPresenter :LoginContractor.Presenter,
        LoaderManager.LoaderCallbacks<Cursor> {
}