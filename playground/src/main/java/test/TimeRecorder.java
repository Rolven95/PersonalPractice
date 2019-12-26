package test;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * @author ruowen
 * @title: TimeRecorder
 * @projectName common_utils
 * @description: 记录所有步骤的耗时以供性能分析。
 * @date 2019/5/918:26
 */
public class TimeRecorder {
    private String recorderName;
    private ArrayList<String> pointNames = new ArrayList<>();
    private ArrayList<Long> timePoints = new ArrayList<>();

    private TimeRecorder() {
    }

    public TimeRecorder(String recorderName) {
        if (StringUtils.isEmpty(recorderName)) {
            throw new IllegalArgumentException("Name could not be empty!");
        }
        this.recorderName = recorderName;
        pointNames.add("Start");
        timePoints.add(System.currentTimeMillis());
    }

    public void record(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Empty String");
        }
        pointNames.add(name);
        timePoints.add(System.currentTimeMillis());
    }

    public String onTaskEndAndReturnReport() {
        int keySetSize = pointNames.size();
        StringBuilder reportToReturn = new StringBuilder();

        // 报告开头
        reportToReturn.append("Performance Report: TimerName:").append(recorderName).append("{");

        // 添加点数据
        for (int i = 1; i < keySetSize; i++) { // 中间点
            reportToReturn.append(pointNames.get(i)).append(":").append(timePoints.get(i) - timePoints.get(i - 1)).append("ms; ");
        }

        String lastPointName = pointNames.get(keySetSize - 1);
        long lastPoint = timePoints.get(keySetSize - 1);
        reportToReturn.append(lastPointName).append(" to the end").append(":").append(System.currentTimeMillis() - lastPoint).append("ms; ");
        reportToReturn.append("total:").append(System.currentTimeMillis() - timePoints.get(0)).append("ms");
        reportToReturn.append("}");
        return reportToReturn.toString();
    }

    public String end(String record) {
        this.record(recorderName);
        return this.onTaskEndAndReturnReport();
    }
}