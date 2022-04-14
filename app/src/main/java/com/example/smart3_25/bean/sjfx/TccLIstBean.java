package com.example.smart3_25.bean.sjfx;

import java.util.List;

public class TccLIstBean {

    private int total;
    private int code;
    private String msg;
    private List<RowsDTO> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public static class RowsDTO implements Comparable<TccLIstBean.RowsDTO>{
        private int id;
        private String parkName;
        private String vacancy;
        private String priceCaps;
        private String imgUrl;
        private String rates;
        private String address;
        private String distance;
        private String allPark;
        private String open;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getParkName() {
            return parkName;
        }

        public void setParkName(String parkName) {
            this.parkName = parkName;
        }

        public String getVacancy() {
            return vacancy;
        }

        public void setVacancy(String vacancy) {
            this.vacancy = vacancy;
        }

        public String getPriceCaps() {
            return priceCaps;
        }

        public void setPriceCaps(String priceCaps) {
            this.priceCaps = priceCaps;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getRates() {
            return rates;
        }

        public void setRates(String rates) {
            this.rates = rates;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getAllPark() {
            return allPark;
        }

        public void setAllPark(String allPark) {
            this.allPark = allPark;
        }

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        @Override
        public int compareTo(RowsDTO rowsDTO) {
            if ((Integer.valueOf(this.allPark)-Integer.valueOf(this.vacancy))>(Integer.valueOf(rowsDTO.allPark)-Integer.valueOf(rowsDTO.vacancy)))
            {
                return -1;
            }
            return 0;
        }
    }
}
