package com.magicalign.OrthoLink.exam;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.magicalign.OrthoLink.CustomView.CustomScrollView;
import com.magicalign.OrthoLink.bean.Question;
import com.magicalign.OrthoLink.handlermessage.MsgWhat;
import com.magicalign.OrthoLink.network.NetworkService;
import com.magicalign.OrthoLink.protobuf.ExaminationProtos.ExaminationList;
import com.magicalign.OrthoLink.protobuf.GetExaminationProtos.GetExamination;
import com.vane.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ExamDetial extends Activity {
    public static ArrayList<Question> QuestionList = new ArrayList<Question>();
    public static ExaminationList eList;
    private static Context mContext;
    private Handler mServerSendMsgHandler;
    private Handler mLoadTwoQuestionsHandler;
    private Handler mServerConnectStateHandler;
    private int QuestionsIndex = 0;
    private TextView problems, ExamType, ExamAnswer, ExamAnswerDetail, mTvServerConnectFail;
    private ListView OptionLv;
    private String Title;
    private List<List<String>> list;
    private List<String> OptionList;
    private int num = 1;
    private LayoutInflater inflater;
    private LinearLayout mLlBelowQuestion, mLlBeforeConnectServer;
    private LinearLayout mLlExamView;
    private RadioButton radioA, radioB, radioC, radioD, radioE, radioF, radioG;
    private RadioGroup radioGroup;
    private int answer = 0;


    private IntentFilter examListfilter = new IntentFilter("EXAMLIST");
    private ExamListRecv examListRecv = new ExamListRecv();
    private RelativeLayout mRl;
    private ScrollView mSv;
    private CustomScrollView mCsv;

    //保证弹出的Toast唯一
    private Toast mToast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        mContext = this.getApplicationContext();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Title = bundle.getString("type");

        initView();
        //初始化handler
        intHandler();

        //加载好试题之前，先隐藏内容界面
        mSv.setVisibility(View.GONE);

        // 连接服务器
        connectServer();
        /*//测试才加上
        mLlBeforeConnectServer.setVisibility(View.GONE);
        init();*/

    }
    /**
     * 执行init之前将界面隐藏
     */
    /*public void beforeInit(){
        mRl.setVisibility(View.INVISIBLE);
    }*/

    /**
     * 初始化Handler对象
     */
    public void intHandler() {
        //1
        mServerSendMsgHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == MsgWhat.SERVER_CONNNECT_SUCCESS_BROADCAST) {
//			eList.getExaminationCount()获得试题总数
                    for (int i = 0; i < eList.getExaminationCount(); i++) {
                        Question question = new Question();
                        question.setAnswer(eList.getExamination(i).getTrueanswer());
                        question.setOptionA(eList.getExamination(i).getAnswera());
                        question.setOptionB(eList.getExamination(i).getAnswerb());
                        question.setOptionC(eList.getExamination(i).getAnswerc());
                        question.setOptionD(eList.getExamination(i).getAnswerd());
                        question.setOptionE(eList.getExamination(i).getAnswere());
                        question.setQuestion(eList.getExamination(i).getQuestion());
                        question.setExplain(eList.getExamination(i).getExplain());
                        QuestionList.add(question);
                    }

                    //加载好试题之后，消去对话框
//                    if (QuestionList.size() == 2) {
                    mLoadTwoQuestionsHandler.sendEmptyMessage(MsgWhat.LOAD_TWO_QUESTION);
//                    }
                }

            }

        };

        //2
        mLoadTwoQuestionsHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //加载好试题之后
                if (msg.what == MsgWhat.LOAD_TWO_QUESTION) {
                    //注意顺序
                    mLlBeforeConnectServer.setVisibility(View.GONE);
                    init();
                    mSv.setVisibility(View.VISIBLE);
                }
            }
        };
        //3
        mServerConnectStateHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MsgWhat.CONNECT_SERVER_SUCCESS) {
                    //连接成功什么都不做，当试题下载好之后再做
//                    mTvServerConnectFail.setText("连接成功");
//                    showToast("连接成功", 200);


                } else if (msg.what == MsgWhat.CONNECT_SERVER_FAIL) {
//                    showToast("服务器连接失败服务器连接失败", 200);
                    //题目界面不可见，连接界面可见，并重新设置连接信息或者点击重试
                    //网络不通时测试注释掉
                    mSv.setVisibility(View.GONE);
                    mTvServerConnectFail.setText("当前网络不可用，请检查网络");
                }
            }
        };


    }

    public void init() {
        // 设置标题


        mLlBelowQuestion.setVisibility(View.INVISIBLE);

        radioA.setEnabled(true);
        radioB.setEnabled(true);
        radioC.setEnabled(true);
        radioD.setEnabled(true);
        radioE.setEnabled(true);
        radioF.setEnabled(true);
        radioG.setEnabled(true);

        radioA.setTextColor(Color.BLACK);
        radioB.setTextColor(Color.BLACK);
        radioC.setTextColor(Color.BLACK);
        radioD.setTextColor(Color.BLACK);
        radioE.setTextColor(Color.BLACK);
        radioF.setTextColor(Color.BLACK);
        radioG.setTextColor(Color.BLACK);

        radioGroup.clearCheck();

        ExamAnswer.setText("");

        ExamAnswerDetail.setText("");

        problems.setText(num + " "
                + QuestionList.get(QuestionsIndex).getQuestion());

        radioA.setText(QuestionList.get(QuestionsIndex).getOptionA());
        radioB.setText(QuestionList.get(QuestionsIndex).getOptionB());

        if (QuestionList.get(QuestionsIndex).getOptionC() == null) {
            radioC.setVisibility(View.GONE);
        } else {
            radioC.setText(QuestionList.get(QuestionsIndex).getOptionC());
            radioC.setVisibility(View.VISIBLE);
        }

        if (QuestionList.get(QuestionsIndex).getOptionD() == null) {
            radioD.setVisibility(View.GONE);
        } else {
            radioD.setText(QuestionList.get(QuestionsIndex).getOptionD());
            radioD.setVisibility(View.VISIBLE);
        }

        if (QuestionList.get(QuestionsIndex).getOptionE() == null) {
            radioE.setVisibility(View.GONE);
        } else {
            radioE.setText(QuestionList.get(QuestionsIndex).getOptionE());
            radioE.setVisibility(View.VISIBLE);
        }

        if (QuestionList.get(QuestionsIndex).getOptionF() == null) {
            radioF.setVisibility(View.GONE);
        } else {
            radioF.setText(QuestionList.get(QuestionsIndex).getOptionF());
            radioF.setVisibility(View.VISIBLE);
        }

        if (QuestionList.get(QuestionsIndex).getOptionG() == null) {
            radioG.setVisibility(View.GONE);
        } else {
            radioG.setText(QuestionList.get(QuestionsIndex).getOptionG());
            radioG.setVisibility(View.VISIBLE);
        }

        if (QuestionList.get(QuestionsIndex).getSelectAnswer() != 0) {
            switch (QuestionList.get(QuestionsIndex).getSelectAnswer()) {
                case 1:
                    radioA.setChecked(true);
                    break;
                case 2:
                    radioB.setChecked(true);
                    break;
                case 3:
                    radioC.setChecked(true);
                    break;
                case 4:
                    radioD.setChecked(true);
                    break;
                case 5:
                    radioE.setChecked(true);
                    break;
                case 6:
                    radioF.setChecked(true);
                    break;
                case 7:
                    radioG.setChecked(true);
                    break;

                default:
                    break;
            }
        }

        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int id) {

                if (radioA.isChecked()) {
                    answer = 1;

                    // radioB.setOnClickListener(null);
                    // radioC.setOnClickListener(null);
                    // radioD.setOnClickListener(null);
                    // radioE.setOnClickListener(null);
                    // radioF.setOnClickListener(null);
                    // radioG.setOnClickListener(null);
                    radioB.setEnabled(false);
                    radioC.setEnabled(false);
                    radioD.setEnabled(false);
                    radioE.setEnabled(false);
                    radioF.setEnabled(false);
                    radioG.setEnabled(false);
                    if (resultTrueOrFalse()) {
                        radioA.setTextColor(getResources().getColor(
                                R.color.exam_true_option));
//						delayTimeAfterTrue();
                    } else {

                        radioA.setTextColor(getResources().getColor(
                                R.color.exam_false_option));
                        setTrueOptionColor();
                    }
                } else if (radioB.isChecked()) {
                    answer = 2;
                    radioA.setEnabled(false);
                    radioC.setEnabled(false);
                    radioD.setEnabled(false);
                    radioE.setEnabled(false);
                    radioF.setEnabled(false);
                    radioG.setEnabled(false);

                    if (resultTrueOrFalse()) {
                        radioB.setTextColor(getResources().getColor(
                                R.color.exam_true_option));
//						delayTimeAfterTrue();
                    } else {

                        radioB.setTextColor(getResources().getColor(
                                R.color.exam_false_option));
                        setTrueOptionColor();
                    }
                } else if (radioC.isChecked()) {
                    answer = 3;

                    radioB.setEnabled(false);
                    radioA.setEnabled(false);
                    radioD.setEnabled(false);
                    radioE.setEnabled(false);
                    radioF.setEnabled(false);
                    radioG.setEnabled(false);

                    if (resultTrueOrFalse()) {
                        radioC.setTextColor(getResources().getColor(
                                R.color.exam_true_option));
//						delayTimeAfterTrue();

                    } else {

                        radioC.setTextColor(getResources().getColor(
                                R.color.exam_false_option));
                        setTrueOptionColor();
                    }
                } else if (radioD.isChecked()) {
                    answer = 4;

                    radioB.setEnabled(false);
                    radioC.setEnabled(false);
                    radioA.setEnabled(false);
                    radioE.setEnabled(false);
                    radioF.setEnabled(false);
                    radioG.setEnabled(false);
                    if (resultTrueOrFalse()) {
                        radioD.setTextColor(getResources().getColor(
                                R.color.exam_true_option));
//						delayTimeAfterTrue();

                    } else {

                        radioD.setTextColor(getResources().getColor(
                                R.color.exam_false_option));
                        setTrueOptionColor();
                    }
                } else if (radioE.isChecked()) {
                    answer = 5;

                    radioB.setEnabled(false);
                    radioC.setEnabled(false);
                    radioD.setEnabled(false);
                    radioA.setEnabled(false);
                    radioF.setEnabled(false);
                    radioG.setEnabled(false);

                    if (resultTrueOrFalse()) {
                        radioE.setTextColor(getResources().getColor(
                                R.color.exam_true_option));
//						delayTimeAfterTrue();

                    } else {

                        radioE.setTextColor(getResources().getColor(
                                R.color.exam_false_option));
                        setTrueOptionColor();
                    }
                } else if (radioF.isChecked()) {
                    answer = 6;

                    radioB.setEnabled(false);
                    radioC.setEnabled(false);
                    radioD.setEnabled(false);
                    radioE.setEnabled(false);
                    radioA.setEnabled(false);
                    radioG.setEnabled(false);

                    if (resultTrueOrFalse()) {
                        radioF.setTextColor(getResources().getColor(
                                R.color.exam_true_option));
//						delayTimeAfterTrue();

                    } else {

                        radioF.setTextColor(getResources().getColor(
                                R.color.exam_false_option));
                        setTrueOptionColor();
                    }
                } else if (radioG.isChecked()) {
                    answer = 7;

                    radioB.setEnabled(false);
                    radioC.setEnabled(false);
                    radioD.setEnabled(false);
                    radioE.setEnabled(false);
                    radioF.setEnabled(false);
                    radioA.setEnabled(false);

                    if (resultTrueOrFalse()) {
                        radioG.setTextColor(getResources().getColor(
                                R.color.exam_true_option));
//						delayTimeAfterTrue();

                    } else {

                        radioG.setTextColor(getResources().getColor(
                                R.color.exam_false_option));
                        setTrueOptionColor();
                    }
                }
            }
        });

    }

    private void connectServer() {
        /*Question question = new Question();
        question.setAnswer(2);
        question.setOptionA("A:刷头小，转动自如");
        question.setOptionB("B:刷毛10-12束长");
        question.setOptionC("C:刷毛较短，顶端圆钝");
        question.setOptionD("D:刷毛密可清楚菌斑");
        question.setQuestion("合格牙具应具有的特点不包括：");
        question.setExplain("第一题原因基牙无倒凹时，箭头卡的箭头应卡在两临牙楔状隙内基牙无倒凹时，箭头卡的箭头应卡在两临牙楔状隙内");
        QuestionList.add(question);

        Question question1 = new Question();
        question1.setAnswer(1);
        question1.setOptionA("A：两临牙楔状隙内");
        question1.setOptionB("B：基牙轴面角");
        question1.setOptionC("C：临牙轴面角");
        question1.setOptionD("D：基牙牙冠颊面");
        question1.setOptionE("E：临牙牙冠颊面");
        question1.setQuestion("基牙无倒凹时，箭头卡的箭头应卡在：");
        question1.setExplain("基牙无倒凹时，箭头卡的箭头应卡在两临牙楔状隙内");
        QuestionList.add(question1);

        Question question2 = new Question();
        question2.setAnswer(2);
        question2.setOptionA("A：使组织受压均匀");
        question2.setOptionB("B：印模材料量要多");
        question2.setOptionC("C：边缘要圆钝，有一定厚度");
        question2.setOptionD("D：尽可能扩大印模面积");
        question2.setOptionE("E：采取功能性印模");
        question2.setQuestion("取印模的要求不包含：");
        question2
                .setExplain("取印模要求取适量的印模材料，制取功能性印模，在取的过程中应使组织均匀受压，边缘要圆钝，有一定厚度，并尽可能扩大印模面积");
        QuestionList.add(question2);*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 从服务器随机生成试题
                NetworkService.getInstance().onInit(mContext);
                NetworkService.getInstance().setupConnection();
                //连接成功
                if (NetworkService.getInstance().getIsConnected()) {
                    mServerConnectStateHandler.sendEmptyMessage(MsgWhat.CONNECT_SERVER_SUCCESS);
                    //向服务器端发送请求数据
                    GetExamination getExamination = GetExamination.newBuilder()
                            .setExaminationTypeId(1).setExaminationForm(1).build();
                    NetworkService.getInstance().getExam(getExamination);

                } else {
                    mServerConnectStateHandler.sendEmptyMessage(MsgWhat.CONNECT_SERVER_FAIL);
                }
            }
        }).start();

    }

    private void initView() {
        ExamType = (TextView) findViewById(R.id.title);
        problems = (TextView) findViewById(R.id.exam_problems);
        radioA = (RadioButton) findViewById(R.id.radioA);
        radioB = (RadioButton) findViewById(R.id.radioB);
        radioC = (RadioButton) findViewById(R.id.radioC);
        radioD = (RadioButton) findViewById(R.id.radioD);
        radioE = (RadioButton) findViewById(R.id.radioE);
        radioF = (RadioButton) findViewById(R.id.radioF);
        radioG = (RadioButton) findViewById(R.id.radioG);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mLlBelowQuestion = (LinearLayout) findViewById(R.id.layout_below_question);
        mLlBeforeConnectServer = (LinearLayout) findViewById(R.id.before_connect_server);

        mLlExamView = (LinearLayout) findViewById(R.id.examlv);
        mCsv = (CustomScrollView) findViewById(R.id.sv);

        //此句不能忘，否则onFling左右滑动不起作用
        /*mLlExamView.setLongClickable(true);
        mLlExamView.setOnTouchListener(new mGestureListener(this));*/

        mCsv.setLongClickable(true);
        mCsv.setOnTouchListener(new mGestureListener(this));

        ExamAnswer = (TextView) findViewById(R.id.exam_answer);
        ExamAnswerDetail = (TextView) findViewById(R.id.exam_answer_detial);
        mTvServerConnectFail = (TextView) findViewById(R.id.tv_server_connect_fail);

        mRl = (RelativeLayout) findViewById(R.id.rl);
        mSv = (ScrollView) findViewById(R.id.sv);
        ExamType.setText(Title);


    }


    private boolean resultTrueOrFalse() {
        QuestionList.get(QuestionsIndex).setSelectAnswer(answer);
        if (QuestionList.get(QuestionsIndex).getAnswer() == answer) {

            showToast("答案正确", 200);
            return true;
        } else {
            showToast("答案错误", 200);
            ExamAnswer.setText("试题解析：" + "答案："
                    + getA(QuestionList.get(QuestionsIndex).getAnswer()));
            ExamAnswerDetail.setText(QuestionList.get(QuestionsIndex)
                    .getExplain());
            mLlBelowQuestion.setVisibility(View.VISIBLE);
            return false;
        }

    }

    private String getA(int num) {
        String result = "";
        switch (num) {
            case 1:
                result = "A";
                break;
            case 2:
                result = "B";
                break;
            case 3:
                result = "C";
                break;
            case 4:
                result = "D";
                break;
            case 5:
                result = "E";
                break;
            case 6:
                result = "F";
                break;
            case 7:
                result = "G";
                break;

        }
        return result;
    }

    private void upTitle() {
        if (QuestionsIndex > 0) {
            QuestionsIndex--;
            num--;
            init();
        } else {
            showToast("已经是第一题了", Toast.LENGTH_SHORT);
        }
    }

    private void nextTitle() {
        if (QuestionsIndex < QuestionList.size() - 1) {
            QuestionsIndex++;
            num++;
            init();
        } else {
            showToast("已经是最后一题了", Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        registerReceiver(examListRecv, examListfilter);

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(examListRecv);
    }

    /**
     * 当选择答案错误时，显示并改变正确选项的颜色
     */
    private void setTrueOptionColor() {
        // 显示并改变正确选项的颜色
        int getTureId = QuestionList.get(QuestionsIndex).getAnswer();
        ((RadioButton) radioGroup.getChildAt(getTureId - 1))
                .setTextColor(getResources().getColor(R.color.exam_true_option));
    }

    /**
     * 选择答案正确后，休眠一段时间
     */
    private void delayTimeAfterTrue() {
        int delayedTime = 1000;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                /*radioGroup.clearCheck();
                radioGroup.invalidate();*/
//				nextTitle();
            }
        }, delayedTime);

    }

    /**
     * 在一个Toast没有结束的时候再显示Toast不累加时间，而是打断当前的Toast，显示新的Toast
     *
     * @param msg
     * @param showTime
     */
    private void showToast(String msg, int showTime) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, showTime);
        } else {
            mToast.setText(msg);
        }
        mToast.show();

    }

    /**
     * 继承GestureListener，重写left和right方法
     */
    private class mGestureListener extends GestureListener {
        public mGestureListener(Context context) {
            super(context);
        }

        @Override
        public boolean left() {
            nextTitle();
            return super.left();
        }

        @Override
        public boolean right() {
            upTitle();
            return super.right();
        }
    }

    public class ExamListRecv extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("EXAMLIST")) {
                mServerSendMsgHandler.sendEmptyMessage(MsgWhat.SERVER_CONNNECT_SUCCESS_BROADCAST);
            }
        }
    }


}
