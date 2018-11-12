package com.common.retrofit.entity.result;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by STH on 2018/6/9.
 */

public class BankListBean implements Parcelable{


    /**
     * list : [{"id":"15","bank_name":"中国建设银行","card_number":"6217000340000565980","card_logo":"ABC","card_bank_logo":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png","card_bank_background":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png"},{"id":"35","bank_name":"中国农业银行","card_number":"111145345","card_logo":"ABC","card_bank_logo":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png","card_bank_background":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png"},{"id":"36","bank_name":"中国民生银行","card_number":"111145345324234","card_logo":"CMBC","card_bank_logo":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png","card_bank_background":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png"}]
     * page : 1
     */

    private int page;
    private List<ListBean> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable{
        /**
         * id : 15
         * bank_name : 中国建设银行
         * card_number : 6217000340000565980
         * card_logo : ABC
         * card_bank_logo : https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png
         * card_bank_background : https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png
         * status 1为对私账户  2为对公账户
         */
        private String id;
        private String bank_name;
        private String card_number;
        private String card_logo;
        private String card_bank_logo;
        private String card_bank_background;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getCard_logo() {
            return card_logo;
        }

        public void setCard_logo(String card_logo) {
            this.card_logo = card_logo;
        }

        public String getCard_bank_logo() {
            return card_bank_logo;
        }

        public void setCard_bank_logo(String card_bank_logo) {
            this.card_bank_logo = card_bank_logo;
        }

        public String getCard_bank_background() {
            return card_bank_background;
        }

        public void setCard_bank_background(String card_bank_background) {
            this.card_bank_background = card_bank_background;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.bank_name);
            dest.writeString(this.card_number);
            dest.writeString(this.card_logo);
            dest.writeString(this.card_bank_logo);
            dest.writeString(this.card_bank_background);
            dest.writeString(this.status);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.id = in.readString();
            this.bank_name = in.readString();
            this.card_number = in.readString();
            this.card_logo = in.readString();
            this.card_bank_logo = in.readString();
            this.card_bank_background = in.readString();
            this.status = in.readString();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeTypedList(this.list);
    }

    public BankListBean() {
    }

    protected BankListBean(Parcel in) {
        this.page = in.readInt();
        this.list = in.createTypedArrayList(ListBean.CREATOR);
    }

    public static final Creator<BankListBean> CREATOR = new Creator<BankListBean>() {
        @Override
        public BankListBean createFromParcel(Parcel source) {
            return new BankListBean(source);
        }

        @Override
        public BankListBean[] newArray(int size) {
            return new BankListBean[size];
        }
    };
}
