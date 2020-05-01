package com.zc.bookmarkdb.crudrepository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zc.bookmarkdb.object.Tag;

@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag, Long> {
	
	Optional<Tag> findByName(String name);
	
}
