package com.rcb.service;

import com.rcb.dto.StudentResponseDTO;
import com.rcb.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
     * @return
     */
    @Transactional
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(st -> new StudentResponseDTO(st.getId().toString(),
                        st.getFirstName().concat(" ").concat(st.getLastName()),
                        st.getAge().toString(),
                        st.getStatus()))
                .collect(Collectors.toList());
    }

}
