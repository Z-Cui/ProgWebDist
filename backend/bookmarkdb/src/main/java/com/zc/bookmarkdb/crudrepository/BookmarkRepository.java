package com.zc.bookmarkdb.crudrepository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zc.bookmarkdb.object.Bookmark;

@Repository
@Transactional
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	
	List<Bookmark> findBySavedUser(String savedUser);
	
}
