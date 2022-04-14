package com.example.smart3_25.ui.activty.csdt;

import java.util.List;

public class ZhanDIanLIstBean {

    private String msg;
    private int code;
    private List<DataDTO> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        private String name;
        private List<LinesDTO> lines;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<LinesDTO> getLines() {
            return lines;
        }

        public void setLines(List<LinesDTO> lines) {
            this.lines = lines;
        }

        public static class LinesDTO {
            private int lineId;
            private String lineName;

            public int getLineId() {
                return lineId;
            }

            public void setLineId(int lineId) {
                this.lineId = lineId;
            }

            public String getLineName() {
                return lineName;
            }

            public void setLineName(String lineName) {
                this.lineName = lineName;
            }
        }
    }
}
