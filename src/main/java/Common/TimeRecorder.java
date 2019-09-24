package Common;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class TimeRecorder {
    private String recorderName;
    private ArrayList<String> pointNames = new ArrayList();
    private ArrayList<Long> timePoints = new ArrayList();

    private TimeRecorder() {
    }

    public TimeRecorder(String recorderName) {
        if (StringUtils.isEmpty(recorderName)) {
            throw new IllegalArgumentException("Name could not be empty!");
        } else {
            this.recorderName = recorderName;
            this.pointNames.add("Start");
            this.timePoints.add(System.currentTimeMillis());
        }
    }

    public void record(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Empty String");
        } else {
            this.pointNames.add(name);
            this.timePoints.add(System.currentTimeMillis());
        }
    }

    public String onTaskEndAndReturnReport() {
        int keySetSize = this.pointNames.size();
        StringBuilder reportToReturn = new StringBuilder();
        reportToReturn.append("Performance Report: TimerName:").append(this.recorderName).append("{");

        for(int i = 1; i < keySetSize; ++i) {
            reportToReturn.append((String)this.pointNames.get(i)).append(":").append((Long)this.timePoints.get(i) - (Long)this.timePoints.get(i - 1)).append("ms; ");
        }

        String lastPointName = (String)this.pointNames.get(keySetSize - 1);
        long lastPoint = (Long)this.timePoints.get(keySetSize - 1);
        reportToReturn.append(lastPointName).append(" to the end").append(":").append(System.currentTimeMillis() - lastPoint).append("ms; ");
        reportToReturn.append("total:").append(System.currentTimeMillis() - (Long)this.timePoints.get(0)).append("ms");
        reportToReturn.append("}");
        return reportToReturn.toString();
    }
}
