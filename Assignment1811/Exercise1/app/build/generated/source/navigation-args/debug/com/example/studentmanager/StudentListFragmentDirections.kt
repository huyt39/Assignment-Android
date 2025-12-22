package com.example.studentmanager

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class StudentListFragmentDirections private constructor() {
  private data class ActionStudentListFragmentToUpdateStudentFragment(
    public val mssv: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_studentListFragment_to_updateStudentFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("mssv", this.mssv)
        return result
      }
  }

  public companion object {
    public fun actionStudentListFragmentToAddStudentFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_studentListFragment_to_addStudentFragment)

    public fun actionStudentListFragmentToUpdateStudentFragment(mssv: String): NavDirections =
        ActionStudentListFragmentToUpdateStudentFragment(mssv)
  }
}
