package com.example.studentmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class StudentListFragment : Fragment() {
    private lateinit var listViewStudents: ListView
    private lateinit var adapter: StudentAdapter
    private val viewModel: StudentViewModel by activityViewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        listViewStudents = view.findViewById(R.id.listViewStudents)
        
        // khoi tao adapter
        adapter = StudentAdapter(
            requireContext(),
            viewModel.studentList.value ?: emptyList(),
            onItemClick = { position ->
                // mo fragment cap nhat sinh vien
                val student = viewModel.studentList.value!![position]
                val action = StudentListFragmentDirections.actionStudentListFragmentToUpdateStudentFragment(student.mssv)
                findNavController().navigate(action)
            },
            onDeleteClick = { position ->
                // xoa sinh vien
                val student = viewModel.studentList.value!![position]
                viewModel.deleteStudent(student.mssv)
            }
        )
        
        listViewStudents.adapter = adapter
        
        // quan sat thay doi danh sach
        viewModel.studentList.observe(viewLifecycleOwner) { students ->
            adapter.updateStudents(students)
            adapter.notifyDataSetChanged()
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuAdd -> {
                // mo fragment them sinh vien
                findNavController().navigate(R.id.action_studentListFragment_to_addStudentFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

