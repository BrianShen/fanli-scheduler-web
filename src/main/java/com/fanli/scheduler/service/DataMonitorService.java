package com.fanli.scheduler.service;

import com.fanli.scheduler.bean.DataMonitorDo;
import com.fanli.scheduler.entity.EtlMonitor;
import com.fanli.scheduler.entity.EtlMonitorDetail;
import com.fanli.scheduler.entity.EtlMonitorDetailExample;
import com.fanli.scheduler.entity.EtlMonitorExample;
import com.fanli.scheduler.mapping.EtlMonitorDetailMapper;
import com.fanli.scheduler.mapping.EtlMonitorMapper;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wei.shen on 2015/11/27.
 */
@Service
public class DataMonitorService {
    @Autowired
    private EtlMonitorDetailMapper etlMonitorDetailMapper;

    @Autowired
    private EtlMonitorMapper etlMonitorMapper;


    @Transactional(propagation= Propagation.REQUIRES_NEW,
            isolation= Isolation.READ_COMMITTED, timeout=3)
    public void SaveMonitorConfigs(DataMonitorDo dataMonitorDo){
        EtlMonitor etlMonitor = new EtlMonitor();
        etlMonitor.setMailCc(dataMonitorDo.getMailCc());
        etlMonitor.setMailTo(dataMonitorDo.getMailTo());
        etlMonitor.setMailSubject(dataMonitorDo.getMailSubject());
        etlMonitor.setMailMessage(dataMonitorDo.getMailMessage());
        etlMonitor.setMobile(dataMonitorDo.getMobile());
        etlMonitor.setOwnerId(dataMonitorDo.getOwnerId());
        etlMonitor.setOwnerName(dataMonitorDo.getOwnerName());
        etlMonitor.setTaskId(dataMonitorDo.getTaskId());
        etlMonitor.setInsDate(new Date());
        etlMonitorMapper.insertSelective(etlMonitor);
        EtlMonitorDetail etlMonitorDetailSource = new EtlMonitorDetail();
        EtlMonitorDetail etlMonitorDetailTarget = new EtlMonitorDetail();
        etlMonitorDetailSource.setMonitorId(etlMonitor.getId());
        etlMonitorDetailSource.setDatasource(dataMonitorDo.getSourceDatasource());
        etlMonitorDetailSource.setExcuteSql(dataMonitorDo.getSourceSql());
        etlMonitorDetailSource.setContrast(dataMonitorDo.getContrast());
        etlMonitorDetailSource.setInsDate(new Date());
        etlMonitorDetailTarget.setMonitorId(etlMonitor.getId());
        etlMonitorDetailTarget.setDatasource(dataMonitorDo.getTargetDatasource());
        etlMonitorDetailTarget.setExcuteSql(dataMonitorDo.getTargetSql());
        etlMonitorDetailTarget.setInsDate(new Date());
        etlMonitorDetailMapper.insertSelective(etlMonitorDetailSource);
        etlMonitorDetailMapper.insertSelective(etlMonitorDetailTarget);
    }

    public DataMonitorDo getDataMonitorConfig(Long taskId) {
        DataMonitorDo dataMonitorDo = new DataMonitorDo();
        EtlMonitorExample etlMonitorExample = new EtlMonitorExample();
        etlMonitorExample.or().andTaskIdEqualTo(taskId);
        List<EtlMonitor> etlMonitors = etlMonitorMapper.selectByExample(etlMonitorExample);
        if (etlMonitors.size() == 0) return null;
        EtlMonitor etlMonitor =  etlMonitors.get(0);
        EtlMonitorDetailExample etlMonitorDetailExample = new EtlMonitorDetailExample();
//        etlMonitorDetailExample.or().andMonitorIdEqualTo(etlMonitor.getId());
        EtlMonitorDetailExample.Criteria criteria = etlMonitorDetailExample.createCriteria();
        criteria.andMonitorIdEqualTo(etlMonitor.getId());
        criteria.andContrastNotEqualTo("");
        List<EtlMonitorDetail> list = etlMonitorDetailMapper.selectByExample(etlMonitorDetailExample);

        if (list.size() > 1) throw new RuntimeException("only one monitor is permitted");
        dataMonitorDo.setDetailId(list.get(0).getId());
        dataMonitorDo.setContrast(list.get(0).getContrast());
        dataMonitorDo.setTaskId(etlMonitor.getTaskId());
        dataMonitorDo.setMailCc(etlMonitor.getMailCc());
        dataMonitorDo.setMailMessage(etlMonitor.getMailMessage());
        dataMonitorDo.setMailSubject(etlMonitor.getMailSubject());
        dataMonitorDo.setMailTo(etlMonitor.getMailTo());
        dataMonitorDo.setMobile(etlMonitor.getMobile());
        dataMonitorDo.setOwnerId(etlMonitor.getOwnerId());
        dataMonitorDo.setOwnerName(etlMonitor.getOwnerName());
        return dataMonitorDo;
    }

    @Transactional(propagation= Propagation.REQUIRES_NEW,
            isolation= Isolation.READ_COMMITTED, timeout=3)
    public void updateMonitor(DataMonitorDo dataMonitorDo) {
        EtlMonitor etlMonitor = new EtlMonitor();
        etlMonitor.setMailCc(dataMonitorDo.getMailCc());
        etlMonitor.setMailTo(dataMonitorDo.getMailTo());
        etlMonitor.setMailSubject(dataMonitorDo.getMailSubject());
        etlMonitor.setMailMessage(dataMonitorDo.getMailMessage());
        etlMonitor.setMobile(dataMonitorDo.getMobile());
        etlMonitor.setTaskId(dataMonitorDo.getTaskId());
        EtlMonitorExample etlMonitorExample = new EtlMonitorExample();
        etlMonitorExample.or().andTaskIdEqualTo(dataMonitorDo.getTaskId());
        etlMonitorMapper.updateByExampleSelective(etlMonitor,etlMonitorExample);
        EtlMonitorDetail etlMonitorDetailSource = new EtlMonitorDetail();
        etlMonitorDetailSource.setContrast(dataMonitorDo.getContrast());
        etlMonitorDetailSource.setId(dataMonitorDo.getDetailId());
        etlMonitorDetailMapper.updateByPrimaryKeySelective(etlMonitorDetailSource);
    }
}
