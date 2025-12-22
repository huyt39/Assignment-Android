package com.example.studentmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.studentmanager.databinding.FragmentUpdateStudentBinding

class UpdateStudentFragment : Fragment() {
    private var _binding: FragmentUpdateStudentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StudentViewModel by activityViewModels()
    private var currentStudent: Student? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateStudentBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // lay mssv tu arguments
        val mssv = arguments?.getString("mssv")
        if (mssv == null) {
            Toast.makeText(requireContext(), "khong tim thay sinh vien", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
            return
        }
        
        // lay thong tin sinh vien
        currentStudent = viewModel.getStudentByMssv(mssv)
        if (currentStudent == null) {
            Toast.makeText(requireContext(), "khong tim thay sinh vien", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
            return
        }
        
        // hien thi thong tin sinh vien
        binding.editTextMSSV.setText(currentStudent!!.mssv)
        binding.editTextHoTen.setText(currentStudent!!.hoTen)
        binding.editTextSoDienThoai.setText(currentStudent!!.soDienThoai)
        binding.editTextDiaChi.setText(currentStudent!!.diaChi)
        
        // xu ly nut cap nhat
        binding.buttonUpdate.setOnClickListener {
            val newMssv = binding.editTextMSSV.text.toString().trim()
            val hoTen = binding.editTextHoTen.text.toString().trim()
            val soDienThoai = binding.editTextSoDienThoai.text.toString().trim()
            val diaChi = binding.editTextDiaChi.text.toString().trim()
            
            if (newMssv.isEmpty() || hoTen.isEmpty() || soDienThoai.isEmpty() || diaChi.isEmpty()) {
                Toast.makeText(requireContext(), "vui long nhap day du thong tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // kiem tra neu mssv thay doi va trung voi sinh vien khac
            if (newMssv != currentStudent!!.mssv) {
                val existingStudent = viewModel.getStudentByMssv(newMssv)
                if (existingStudent != null) {
                    Toast.makeText(requireContext(), "mssv da ton tai", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            
            val updatedStudent = Student(newMssv, hoTen, soDienThoai, diaChi)
            
            if (viewModel.updateStudent(currentStudent!!.mssv, updatedStudent)) {
                Toast.makeText(requireContext(), "da cap nhat thanh cong", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "cap nhat that bai", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

