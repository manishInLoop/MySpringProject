package com.learning.youtube.LearningRestApis.service.impl;

import com.learning.youtube.LearningRestApis.dto.AddStudentRequestDto;
import com.learning.youtube.LearningRestApis.dto.StudentDto;
import com.learning.youtube.LearningRestApis.entity.Student;
import com.learning.youtube.LearningRestApis.repository.StudentRepository;
import com.learning.youtube.LearningRestApis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;
    private final ModelMapper modelMapper;
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        return students
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("student not found with id :" + id));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto, Student.class);
        Student student = studentRepo.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepo.existsById(id)){
            throw new IllegalArgumentException("Student does not exists by id :" +id);
        }
        studentRepo.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepo.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Student  not found with id :" +id));
        modelMapper.map(addStudentRequestDto, student);
        student = studentRepo.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepo.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Student not found with id " +id));

        updates.forEach((field, value)->{
            switch(field){
                case "name" :
                    student.setName((String) value);
                break;
                case "email" :
                    student.setEmail((String) value);
                break;
                default: throw new IllegalArgumentException("filed is not supported");
            }
        });
        Student savedStudent = studentRepo.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }
}
