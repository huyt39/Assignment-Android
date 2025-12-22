package com.example.studentmanager

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class UpdateStudentFragmentArgs(
  public val mssv: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("mssv", this.mssv)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("mssv", this.mssv)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): UpdateStudentFragmentArgs {
      bundle.setClassLoader(UpdateStudentFragmentArgs::class.java.classLoader)
      val __mssv : String?
      if (bundle.containsKey("mssv")) {
        __mssv = bundle.getString("mssv")
        if (__mssv == null) {
          throw IllegalArgumentException("Argument \"mssv\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"mssv\" is missing and does not have an android:defaultValue")
      }
      return UpdateStudentFragmentArgs(__mssv)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): UpdateStudentFragmentArgs {
      val __mssv : String?
      if (savedStateHandle.contains("mssv")) {
        __mssv = savedStateHandle["mssv"]
        if (__mssv == null) {
          throw IllegalArgumentException("Argument \"mssv\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"mssv\" is missing and does not have an android:defaultValue")
      }
      return UpdateStudentFragmentArgs(__mssv)
    }
  }
}
