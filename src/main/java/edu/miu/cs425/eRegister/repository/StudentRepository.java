package edu.miu.cs425.eRegister.repository;

import edu.miu.cs425.eRegister.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
