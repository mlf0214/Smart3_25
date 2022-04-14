package com.example.smart3_25.bean.csdt;

import java.util.List;

public class SYLIstBean {

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
        private int lineId;
        private String lineName;
        private PreStepDTO preStep;
        private NextStepDTO nextStep;
        private String currentName;
        private int reachTime;

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

        public PreStepDTO getPreStep() {
            return preStep;
        }

        public void setPreStep(PreStepDTO preStep) {
            this.preStep = preStep;
        }

        public NextStepDTO getNextStep() {
            return nextStep;
        }

        public void setNextStep(NextStepDTO nextStep) {
            this.nextStep = nextStep;
        }

        public String getCurrentName() {
            return currentName;
        }

        public void setCurrentName(String currentName) {
            this.currentName = currentName;
        }

        public int getReachTime() {
            return reachTime;
        }

        public void setReachTime(int reachTime) {
            this.reachTime = reachTime;
        }

        public static class PreStepDTO {
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

        public static class NextStepDTO {
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
}
