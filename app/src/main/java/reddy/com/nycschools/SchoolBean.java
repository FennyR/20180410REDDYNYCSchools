package reddy.com.nycschools;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mreddy on 4/10/2018.
 */

public class SchoolBean implements Serializable,Parcelable {

    private String dbn;
    private String schoolName;
    private String noOfTestsTaken;
    //private SatScores satScores;

    private String readingAvgScore;
    private String writingAvgScore;
    private String mathAvgScore;

    private String errTxt;
    private int errCode;


    public SchoolBean() {
    }

    public SchoolBean(Parcel source) {
        readFromParcel(source);
    }

    public static final Creator<SchoolBean> CREATOR = new Creator<SchoolBean>() {
        @Override
        public SchoolBean createFromParcel(Parcel in) {
            return new SchoolBean(in);
        }

        @Override
        public SchoolBean[] newArray(int size) {
            return new SchoolBean[size];
        }
    };

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }


    public String getDbn() {

        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getNoOfTestsTaken() {
        return noOfTestsTaken;
    }

    public void setNoOfTestsTaken(String noOfTestsTaken) {
        this.noOfTestsTaken = noOfTestsTaken;
    }

    public String getReadingAvgScore() {
        return readingAvgScore;
    }

    public void setReadingAvgScore(String readingAvgScore) {
        this.readingAvgScore = readingAvgScore;
    }

    public String getWritingAvgScore() {
        return writingAvgScore;
    }

    public void setWritingAvgScore(String writingAvgScore) {
        this.writingAvgScore = writingAvgScore;
    }

    public String getMathAvgScore() {
        return mathAvgScore;
    }

    public void setMathAvgScore(String mathAvgScore) {
        this.mathAvgScore = mathAvgScore;
    }

    public String getErrTxt() {
        return errTxt;
    }

    public void setErrTxt(String errTxt) {
        this.errTxt = errTxt;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dbn);
        dest.writeString(schoolName);
        dest.writeString(noOfTestsTaken);
        dest.writeString(mathAvgScore);
        dest.writeString(readingAvgScore);
        dest.writeString(writingAvgScore);
    }

    private void readFromParcel(Parcel in) {

        dbn  = in.readString();
        schoolName = in.readString();
        noOfTestsTaken = in.readString();
        mathAvgScore = in.readString();
        readingAvgScore = in.readString();
        writingAvgScore = in.readString();
    }
}
