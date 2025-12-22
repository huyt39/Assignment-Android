package com.example.studentmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.studentmanager.databinding.FragmentAddStudentBinding

class AddStudentFragment : Fragment() {
    private var _binding: FragmentAddStudentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StudentViewModel by activityViewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddStudentBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // xu ly nut them
        binding.buttonAdd.setOnClickListener {
            val mssv = binding.editTextMSSV.text.toString().trim()
            val hoTen = binding.editTextHoTen.text.toString().trim()
            val soDienThoai = binding.editTextSoDienThoai.text.toString().trim()
            val diaChi = binding.editTextDiaChi.text.toString().trim()
            
            if (mssv.isEmpty() || hoTen.isEmpty() || soDienThoai.isEmpty() || diaChi.isEmpty()) {
                Toast.makeText(requireContext(), "vui long nhap day du thong tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val student = Student(mssv, hoTen, soDienThoai, diaChi)
            
            if (viewModel.addStudent(student)) {
                Toast.makeText(requireContext(), "da them sinh vien thanh cong", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "mssv da ton tai", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

