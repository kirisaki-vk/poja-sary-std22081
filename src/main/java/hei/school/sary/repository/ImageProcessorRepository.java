package hei.school.sary.repository;

import hei.school.sary.repository.model.ImageProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageProcessorRepository extends JpaRepository<ImageProcess, String> {}
