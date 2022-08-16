package com.rcb.service;

import com.rcb.constants.CommonStatus;
import com.rcb.dto.StudentInsertDTO;
import com.rcb.dto.StudentResponseDTO;
import com.rcb.entity.Student;
import com.rcb.repository.StudentRepository;
import com.rcb.utill.CommonResponse;
import com.rcb.utill.CommonValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * ===========================================================
 * This service is dedicated to handling all the business
 * logic for students.
 * ===========================================================
 */
@Service
public class StudentService {

    private final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    /**
     * ========================================================================
     * This method is responsible get all students
     * ========================================================================
     *
     * @return StudentResponseDTO
     */
    @Transactional
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(st -> getStudentResponseDTO(st))
                .collect(Collectors.toList());
    }

    /**
     * ========================================================================
     * This method is responsible get students by status
     * ========================================================================
     *
     * @return
     */
    @Transactional
    public List<StudentResponseDTO> getByStatus(String status) {
        Predicate<Student> filterByStatus = status.equalsIgnoreCase("-1") ? student -> student.getId() != null :
                student -> student.getStatus().equals(CommonStatus.valueOf(status));
        return studentRepository
                .findAll()
                .stream()
                .filter(filterByStatus)
                .map(st -> getStudentResponseDTO(st))
                .collect(Collectors.toList());
    }

    /**
     * ========================================================================
     * This method is responsible convert StudentInsertDTO to Student Entity
     * ========================================================================
     *
     * @param student
     * @param dto
     * @return
     */
    public Student getStudentEntity(Student student, StudentInsertDTO dto) {
        if (!CommonValidation.stringNullValidation(dto.getId())) {
            student.setId(Long.parseLong(dto.getId()));
        }
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setAge(Integer.valueOf(dto.getAge()));
        student.setStatus(dto.getStatus());
        return student;
    }

    /**
     * ========================================================================
     * This method is responsible convert Student Entity to StudentResponseDTO
     * ========================================================================
     *
     * @param student
     * @return
     */
    public StudentResponseDTO getStudentResponseDTO(Student student) {
        return new StudentResponseDTO(student.getId().toString(),
                student.getFirstName().concat(" ").concat(student.getLastName()),
                student.getAge().toString(),
                student.getStatus());
    }

    /**
     * ========================================================================
     * This method is responsible save and update student details.
     * ========================================================================
     *
     * @param dto
     * @return
     */
    @Transactional
    public CommonResponse saveUpdateStudent(StudentInsertDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        Student student;
        try {
            /*List<String> validations = validateStudent(dto);
            if (!CollectionUtils.isEmpty(validations)) {
                commonResponse.setErrorMessages(validations);
                return commonResponse;
            }*/
            if (CommonValidation.stringNullValidation(dto.getId())) {
                student = new Student();
            } else {
                student = getStudentById(Long.parseLong(dto.getId()));
            }
            student = getStudentEntity(student, dto);
            studentRepository.save(student);
            commonResponse.setStatus(true);
        } catch (Exception e) {
            LOGGER.warn("/**************** Exception in StudentService -> saveUpdateStudent()" + e);
        }
        return commonResponse;
    }

    /**
     * ========================================================================
     * This method is responsible get student by id
     * ========================================================================
     *
     * @param studentId
     * @return
     */
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }
}
