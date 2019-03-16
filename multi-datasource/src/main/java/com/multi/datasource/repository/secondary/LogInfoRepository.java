package com.multi.datasource.repository.secondary;

import com.multi.datasource.entity.secondary.LogInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zx
 * @see LogInfo
 */
@Repository
public interface LogInfoRepository extends JpaRepository<LogInfo, Long> {
}
