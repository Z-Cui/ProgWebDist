package com.zc.bookmarkdb.crudrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zc.bookmarkdb.object.History;

@Repository
@Transactional
public interface HistoryRepository extends JpaRepository<History, Long>{
}
