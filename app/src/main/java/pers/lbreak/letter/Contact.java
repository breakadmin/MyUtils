package pers.lbreak.letter;


import java.io.Serializable;

import pers.lbreak.myutils.utils.ChineseLetterUtil;

/**
 * Created by 惠普 on 2018-01-14.
 */

public class Contact implements Serializable{
    private String account;//账号
    private String name_en;//姓名(英文)
    private String userIcon;//用户头像
    private String name_ch;//姓名(中文)
    ChineseLetterUtil utils =ChineseLetterUtil.getInstance();

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getName_ch() {
        return name_ch;
    }

    public void setName_ch(String name_ch) {
        this.name_ch = name_ch;
        name_en=utils.getFirstLetter(name_ch);//获取姓名首字母
    }

    public String getName_en() {
        return name_en;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "account='" + account + '\'' +
                ", name_en='" + name_en + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", name_ch='" + name_ch + '\'' +
                '}';
    }

}
