package com.cn.liu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.cn.liu.entity.ImportRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestImportListener extends AnalysisEventListener<ImportRequest> {

    /**
     * 每隔100条处理下，然后清理list ，方便内存回收
     */
//    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
//    private List<ImportRequest> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private List<ImportRequest> dataList = new ArrayList<>();

    private Map<String, List<ImportRequest>> dataMap = new HashMap<>();

    /**
     * 当读取到一行数据时，会调用这个方法，并将读取到的数据以及上下文信息作为参数传入
     * 可以在这个方法中对读取到的数据进行处理和操作，处理数据时要注意异常错误，保证读取数据的稳定性
     */
    @Override
    public void invoke(ImportRequest importRequest, AnalysisContext analysisContext) {
//        cachedDataList.add(importRequest);
//        if (cachedDataList.size() >= BATCH_COUNT) {
//            // 处理缓存的数据
//
//            // 然后清空
//            cachedDataList.clear();
//        }
        dataList.add(importRequest);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<ImportRequest> getDataList() {
        return this.dataList;
    }

}
