package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolderT;
import mk.ukim.finki.wp.lab.bootstrap.DataHolderW;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepository {

    public List<Teacher> findAll(){
        return DataHolderT.teachers;
    }

}
