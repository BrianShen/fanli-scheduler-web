package com.fanli.scheduler.mapping;

import com.fanli.scheduler.entity.EtlSystemFeedback;
import com.fanli.scheduler.entity.EtlSystemFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EtlSystemFeedbackMapper {
    List<EtlSystemFeedback> listAllFeedbacks();
    int insert(EtlSystemFeedback feedback);
}