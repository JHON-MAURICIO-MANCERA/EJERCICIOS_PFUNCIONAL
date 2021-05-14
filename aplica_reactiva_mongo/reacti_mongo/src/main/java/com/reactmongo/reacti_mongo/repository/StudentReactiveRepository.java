package com.reactmongo.reacti_mongo.repository;

import com.reactmongo.reacti_mongo.objeto.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentReactiveRepository extends ReactiveCrudRepository<Student,String> {
}
