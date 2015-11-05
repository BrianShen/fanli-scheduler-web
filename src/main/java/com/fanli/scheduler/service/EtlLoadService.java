package com.fanli.scheduler.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fanli.scheduler.entity.EtlLoadCfg;
import com.fanli.scheduler.entity.EtlLoadCfgExample;
import com.fanli.scheduler.mapping.EtlLoadCfgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wei.shen on 2015/9/14.
 */
@Service
public class EtlLoadService {
    @Autowired
    private EtlLoadCfgMapper etlLoadCfgMapper;

    public String getReaderCfg(Integer taskid) {
        EtlLoadCfgExample etlLoadCfgExample = new EtlLoadCfgExample();
        etlLoadCfgExample.or().andTaskIdEqualTo(taskid).andTypeEqualTo("reader").andIsValidEqualTo(1);
        EtlLoadCfg etlLoadCfg = etlLoadCfgMapper.selectByExample(etlLoadCfgExample).get(0);
        //String readerCfg = etlLoadCfg.getParameterMap();
        return etlLoadCfg.getParameterMap();
    }

    public String getWriterCfg(Integer taskid) {
        EtlLoadCfgExample etlLoadCfgExample = new EtlLoadCfgExample();
        etlLoadCfgExample.or().andTaskIdEqualTo(taskid).andTypeEqualTo("writer").andIsValidEqualTo(1);
        EtlLoadCfg etlLoadCfg = etlLoadCfgMapper.selectByExample(etlLoadCfgExample).get(0);
        return etlLoadCfg.getParameterMap();
    }

    public int modifyTransferSql(Integer taskid,String paramMap) {
        EtlLoadCfgExample etlLoadCfgExample = new EtlLoadCfgExample();
        etlLoadCfgExample.or().andTaskIdEqualTo(taskid).andTypeEqualTo("reader").andIsValidEqualTo(1);
        EtlLoadCfg etlLoadCfg = new EtlLoadCfg();
        etlLoadCfg.setParameterMap(paramMap);
        int ret = etlLoadCfgMapper.updateByExampleSelective(etlLoadCfg, etlLoadCfgExample);
        return ret;
    }

    public int modifyPreSql(Integer taskid,String paramMap) {
        EtlLoadCfgExample etlLoadCfgExample = new EtlLoadCfgExample();
        etlLoadCfgExample.or().andTaskIdEqualTo(taskid).andTypeEqualTo("writer").andIsValidEqualTo(1);
        EtlLoadCfg etlLoadCfg = new EtlLoadCfg();
        etlLoadCfg.setParameterMap(paramMap);
        int ret = etlLoadCfgMapper.updateByExampleSelective(etlLoadCfg,etlLoadCfgExample);
        return ret;
    }

    private String parseSql(String paramMap) {
        JSONObject object = JSON.parseObject(paramMap);
        String sql = object.getString("sql");
        return sql;
    }

}
