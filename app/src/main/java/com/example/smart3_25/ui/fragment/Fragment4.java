package com.example.smart3_25.ui.fragment;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smart3_25.R;
import com.example.smart3_25.base.BaseFragment;
import com.example.smart3_25.bean.sjfx.TccLIstBean;
import com.example.smart3_25.network.OKhttpUtil;
import com.example.smart3_25.network.Okhttp_code;
import com.example.smart3_25.sql.User;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Fragment4 extends BaseFragment {
    private static final String TAG = "Fragment4";
    private ImageView mLeftIcon;
    private TextView mTitle;
    private ImageView mRightIcon;
    private BarChart mBarchart;
    private LineChart mLinechart;
    private PieChart mPiechart;
    private HorizontalBarChart mHbarchart;

    @Override
    protected void initView() {

        mLeftIcon =  getView().findViewById(R.id.left_icon);
        mTitle =     getView().findViewById(R.id.title);
        mRightIcon = getView().findViewById(R.id.right_icon);
        mBarchart =  getView().findViewById(R.id.barchart);
        mLinechart = getView().findViewById(R.id.linechart);
        mPiechart =  getView().findViewById(R.id.piechart);
        mHbarchart = getView().findViewById(R.id.hbarchart);
    }

    @Override
    protected void initData() {
        setBarchart(mBarchart);
        setLinChart(mLinechart);
        setPieChart(mPiechart);
//        setHBarChart(mHbarchart);
        getHbarChartNetWorkXsring();

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,String.valueOf("onResume()"));
    }

    private void setHBarChart( List<TccLIstBean.RowsDTO> rowsDTOList) {
        mHbarchart.setBackgroundColor(Color.WHITE);
        mHbarchart.setDescription(null);
        mHbarchart.setTouchEnabled(false);
        List<String> xString=new ArrayList<>();
        for (int i = 0; i < rowsDTOList.size(); i++) {
            xString.add(rowsDTOList.get(i).getParkName().substring(0,3)+"..");
        }
            Log.d(TAG,"2:rowsDTOList:"+rowsDTOList);
//        XAxis xAxis = mHbarchart1.getXAxis();
//        YAxis axisLeft = mHbarchart1.getAxisLeft();
//        YAxis axisRight = mHbarchart1.getAxisRight();
//        initXY(xAxis,axisLeft,axisRight,xString);
        initHbrXy(mHbarchart,xString);
//        initXY(mHbarchart,xString);
        BarData hartData = getHbarCHartData(rowsDTOList);
        mHbarchart.setData(hartData);
        Log.d(TAG, String.valueOf(hartData));
    }

    private void initHbrXy(HorizontalBarChart mHbarchart1,List<String> stringList) {
        XAxis xAxis = mHbarchart1.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(stringList.size());
        YAxis axisRight = mHbarchart1.getAxisRight();
        YAxis axisLeft = mHbarchart1.getAxisLeft();
        axisRight.setDrawLabels(false);
        axisLeft.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int)value);
            }
        });
        axisRight.setDrawGridLines(false);
        axisLeft.setDrawGridLines(false);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return stringList.get((int)Math.abs(value)%stringList.size());
            }
        });
    }

    private BarData getHbarCHartData(List<TccLIstBean.RowsDTO> rowsDTOList) {
        List<BarEntry> barEntryList=new ArrayList<>();
        for (int i = 0; i < rowsDTOList.size(); i++) {
            barEntryList.add(new BarEntry(i, Float.parseFloat(rowsDTOList.get(i).getVacancy())));
        }
        BarDataSet barDataSet=new BarDataSet(barEntryList,"出现次数");
        BarData barData=new BarData(barDataSet);
        barData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int)value);
            }
        });
        Log.d(TAG,"barEntryList:"+barDataSet);
        initBarDataSet(barDataSet,Color.YELLOW);
        return barData;
    }
    private void getHbarChartNetWorkXsring() {
        List<TccLIstBean.RowsDTO> rowsDTOList=new ArrayList<>();
                OKhttpUtil oKhttpUtil = new OKhttpUtil(mActiivty);
                oKhttpUtil.setget(User.URI + "/prod-api/api/park/lot/list", "");
                oKhttpUtil.setOkhttp_code(new Okhttp_code() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ResponseBody body = response.body();
                        String string = body.string();
                        TccLIstBean tccLIstBean = new Gson().fromJson(string, TccLIstBean.class);
                        List<TccLIstBean.RowsDTO> rows = tccLIstBean.getRows();
                        Collections.sort(rows);
                                for (int i = 0; i < 5; i++) {
                                    rowsDTOList.add(rows.get(i));
                                }
                                mActiivty.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        setHBarChart(rowsDTOList);
                                        mHbarchart.invalidate();
                                    }
                                });

                        Log.d(TAG,"rowsDTOList3:"+rowsDTOList);
//                                Log.d(TAG,"rowsDTOList:"+String.valueOf(rowsDTOList));

                    }

                    @Override
                    public void getString(String s) {

                    }

                });







    }

    private void setPieChart(PieChart mPiechart) {
        mPiechart.setBackgroundColor(Color.WHITE);
        mPiechart.setDescription(null);
        mPiechart.setDrawHoleEnabled(false);
        mPiechart.setTouchEnabled(false);
        mPiechart.setEntryLabelColor(Color.BLACK);
        mPiechart.animateX(1000);
        mPiechart.animateY(1000);
        mPiechart.setCenterTextSize(15);
        PieData pieChartData = getPieChartData();
        mPiechart.setData(pieChartData);
    }

    private PieData getPieChartData() {
        List<PieEntry> pieNetWorkData = getPieNetWorkData();
        PieDataSet pieDataSet=new PieDataSet(pieNetWorkData,"分类");
        List<Integer> colors=new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.LTGRAY);
        colors.add(Color.CYAN);
        initPieDataSet(pieDataSet,colors);
        PieData pieData=new PieData();
        pieData.setDataSet(pieDataSet);
        pieData.setValueTextSize(20);
        pieData.setValueTextColor(Color.BLACK);
        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int)value+"0%";
            }
        });
        return pieData;
    }

    private void initPieDataSet(PieDataSet pieDataSet, List<Integer> colors) {
        pieDataSet.setColors(colors);
        pieDataSet.setGradientColor(Color.CYAN,Color.YELLOW);
        pieDataSet.setValueTextColor(Color.BLACK);
    }

    private List<PieEntry> getPieNetWorkData() {
        List<PieEntry> entryList=new ArrayList<>();
        entryList.add(new PieEntry(1,"分类一"));
        entryList.add(new PieEntry(2,"分类二"));
        entryList.add(new PieEntry(5,"分类三"));
        entryList.add(new PieEntry(2,"分类四"));
        return entryList;
    }

    private void setLinChart(LineChart mLinechart) {
        mLinechart.setBackgroundColor(Color.WHITE);
        mLinechart.setDescription(null);
        mLinechart.setTouchEnabled(false);
        mLinechart.animateX(1000);
        mLinechart.animateY(1000);
        List<String> linXstring = getLinXstring();
        XAxis xAxis = mLinechart.getXAxis();
        YAxis axisLeft = mLinechart.getAxisLeft();
        YAxis axisRight = mLinechart.getAxisRight();
        initXY(xAxis,axisLeft,axisRight,linXstring);
        List<Entry> linCharNetworkData = getLinCharNetworkData();
        LineData linData = getLinData(linCharNetworkData);
        mLinechart.setData(linData);
    }
    private void initXY(XAxis xAxis,YAxis Yleft,YAxis Yright,List<String> xstring){
        Yleft.setDrawGridLines(false);
        Yright.setDrawGridLines(false);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(xstring.size()-1);
        xAxis.setLabelCount(xstring.size());
        xAxis.setGranularity(1f);
        Yright.setDrawGridLines(false);
        Yright.setDrawLabels(false);
        Yleft.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int)value);
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xstring.get((int) value%xstring.size());
            }
        });
    }
    private LineData getLinData(List<Entry> linCharNetworkData) {
        LineDataSet lineDataSet=new LineDataSet(linCharNetworkData,"数量");
        initLinDataSet(lineDataSet,Color.YELLOW);
        LineData lineData=new LineData(lineDataSet);
        lineData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int)value);
            }
        });
        lineData.calcMinMaxY(0,10);
        return lineData;
    }

    private void initLinDataSet(LineDataSet lineDataSet, int color) {
        lineDataSet.setColor(color);
        lineDataSet.setLineWidth(5);
        lineDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int)value);
            }
        });

    }

    private List<Entry> getLinCharNetworkData() {
        List<Entry> entryList=new ArrayList<>();
        entryList.add(new Entry(0,10));
        entryList.add(new Entry(1,13));
        entryList.add(new Entry(2,12));
//        entryList.add(new Entry(3,14));

        return entryList;
    }

    private List<String> getLinXstring() {
        List<String> list=new ArrayList<>();
        list.add("甘A");
        list.add("甘B");
        list.add("甘C");

        return list;
    }


    private void setBarchart(BarLineChartBase mBarchart) {
        initBarchart(mBarchart);
    }
        private void initBarchart(BarLineChartBase mBarchart) {

        mBarchart.setDescription(null);
//        mBarchart.setDrawBarShadow(false);
        mBarchart.setBackgroundColor(Color.WHITE);
        mBarchart.setTouchEnabled(false);
        mBarchart.animateX(1000);
        mBarchart.animateY(1000);
        List<String> stringList = getBarXstring();
        initXY(mBarchart,stringList);
        BarData barChartData = getBarChartData();
        mBarchart.setData(barChartData);
    }


    private List<String> getBarXstring() {
        List<String> newstitles=new ArrayList<>();
                newstitles.add("新闻1");
                newstitles.add("新闻2");
                newstitles.add("新闻3");
                newstitles.add("新闻4");
                return newstitles;
    }

    private BarData getBarChartData() {
        List<BarEntry> barEntryList = getBachartnetworData("男");
        List<BarEntry> barEntryList1 = getBachartnetworData("女");
        BarDataSet barDataSet=new BarDataSet(barEntryList,"男");
        BarDataSet barDataSet1=new BarDataSet(barEntryList1,"女");
        initBarDataSet(barDataSet,Color.CYAN);
        initBarDataSet(barDataSet1,Color.YELLOW);
        BarData barData=new BarData(barDataSet);
        barData.addDataSet(barDataSet1);
        barData.setBarWidth(0.1f);
        barData.groupBars(0f,0.6f,0.1f);
        barData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });

        return barData;
    }

    private void initBarDataSet(BarDataSet dataSet,int color) {
        dataSet.setColor(color);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int)value);
            }
        });
    }

    private List<BarEntry> getBachartnetworData(String sex) {
        if (sex.equals("男")){
            List<BarEntry> barEntryList=new ArrayList<>();
            barEntryList.add(new BarEntry(0,10));
            barEntryList.add(new BarEntry(1,9));
            barEntryList.add(new BarEntry(2,12));
            barEntryList.add(new BarEntry(3,9));
            return barEntryList;
        }else {
            List<BarEntry> barEntryList=new ArrayList<>();
            barEntryList.add(new BarEntry(0,12));
            barEntryList.add(new BarEntry(0,13));
            barEntryList.add(new BarEntry(0,10));
            barEntryList.add(new BarEntry(0,14));
            return barEntryList;
        }

    }

    private void initXY(BarLineChartBase mBarchart,List<String> xstring) {
        XAxis x = mBarchart.getXAxis();
        YAxis yAxisright = mBarchart.getAxisRight();
        YAxis yAxisleft = mBarchart.getAxisLeft();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setDrawGridLines(false);
        x.setAxisMaximum(xstring.size());
        x.setLabelCount(xstring.size());
        x.setAxisMinimum(0f);
        x.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xstring.get((int) (Math.abs(value)%xstring.size()));
            }
        });
        x.setCenterAxisLabels(true);

        yAxisright.setDrawGridLines(false);
        yAxisright.setDrawLabels(false);
        yAxisleft.setDrawGridLines(false);

    }

    @Override
    protected void ininEvent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_4;
    }
}
